package app.eospocket.android.ui.main.token;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.eospocket.android.common.Constants;
import app.eospocket.android.common.CustomPreference;
import app.eospocket.android.common.mvp.BasePresenter;
import app.eospocket.android.eos.EosManager;
import app.eospocket.android.eos.model.action.Action;
import app.eospocket.android.eos.model.action.ActionList;
import app.eospocket.android.eos.model.coinmarketcap.CoinMarketCapItem;
import app.eospocket.android.eos.request.AccountRequest;
import app.eospocket.android.eos.request.ActionRequest;
import app.eospocket.android.eos.request.CurrencyRequest;
import app.eospocket.android.ui.AdapterDataModel;
import app.eospocket.android.ui.main.token.items.EosTransferResponse;
import app.eospocket.android.ui.main.token.items.TokenItem;
import app.eospocket.android.ui.main.token.items.TransferItem;
import app.eospocket.android.wallet.PocketAppManager;
import app.eospocket.android.wallet.db.model.EosAccountModel;
import app.eospocket.android.wallet.db.model.EosAccountTokenModel;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TokenPresenter extends BasePresenter<TokenView> {

    private EosManager mEosManager;

    private PocketAppManager mPocketAppManager;

    private CustomPreference mCustomPreference;
    private Scheduler mProcessScheduler;
    private Scheduler mAndroidScheduler;
    private AdapterDataModel<TokenItem> mTokenAdapterDataModel;
    private AdapterDataModel<TransferItem> mTransferAdapterDataModel;

    public TokenPresenter(TokenView view, EosManager eosManager, PocketAppManager pocketAppManager,
            CustomPreference customPreference, Scheduler processScheduler, Scheduler androidScheduler) {
        super(view);
        this.mEosManager = eosManager;
        this.mPocketAppManager = pocketAppManager;
        this.mCustomPreference = customPreference;
        this.mProcessScheduler = processScheduler;
        this.mAndroidScheduler = androidScheduler;
    }

    @Override
    public void onCreate() {

    }

    public void getTokens(@NonNull String accountName) {
        Single.fromCallable(() -> {
            // get total action count
            ActionRequest request = new ActionRequest();
            request.accountName = accountName;
            request.pos = -1;
            request.offset = -1;
            return request;
        })
        .flatMap((request) -> mEosManager.getAccountActions(request))
        .flatMap(actionList -> {
            return Single.fromCallable(() -> {
                if (!actionList.actions.isEmpty()) {
                    long totalActions = actionList.actions.get(0).accountActionSeq;

                    if (totalActions <= mCustomPreference.getParseActionSeq()) {
                        return new ArrayList<EosAccountTokenModel>();
                    }

                    // paging
                    int totalPage = (int) (totalActions / Constants.ACTIONS_PER_PAGE);

                    if (totalActions % Constants.ACTIONS_PER_PAGE != 0) {
                        totalPage++;
                    }

                    List<EosAccountTokenModel> eosAccountTokenModels = new ArrayList<>();
                    Map<String, Boolean> tokens = new HashMap<>();

                    for (int i = 0; i < totalPage; i++) {
                        long pos = i * Constants.ACTIONS_PER_PAGE + mCustomPreference.getParseActionSeq();
                        long offset = Constants.ACTIONS_PER_PAGE - 1;

                        ActionRequest request = new ActionRequest();
                        request.accountName = accountName;
                        request.pos = pos;
                        request.offset = offset;

                        ActionList actions = mEosManager.getAccountActions(request).blockingGet();

                        /*
                            get received token
                            mayajuni(itam_ma) in itam netrowk
                            https://eoscanner.io
                         */
                        for (Action action : actions.actions) {
                            if ("transfer".equalsIgnoreCase(action.actionTrace.act.name)
                                    && accountName.equalsIgnoreCase(action.actionTrace.act.data.to)) {
                                if (!tokens.containsKey(action.actionTrace.act.account)
                                        && !Constants.EOS_TOKEN_CONTRACT.equalsIgnoreCase(action.actionTrace.act.account)) {
                                    tokens.put(action.actionTrace.act.account, true);

                                    EosAccountTokenModel eosAccountTokenModel = new EosAccountTokenModel();
                                    eosAccountTokenModel.setAccountName(accountName);
                                    eosAccountTokenModel.setTokenName(action.actionTrace.act.data.quantity.split(" ")[1]);
                                    eosAccountTokenModel.setSymbol(eosAccountTokenModel.getTokenName());
                                    eosAccountTokenModel.setContract(action.actionTrace.act.account);

                                    eosAccountTokenModels.add(eosAccountTokenModel);
                                }
                            }
                        }
                    }

                    mCustomPreference.setParseActionSeq(totalActions);
                    return eosAccountTokenModels;
                }

                return new ArrayList<EosAccountTokenModel>();
            });
        })
        .flatMap(eosAccountTokenModels -> {
            return Single.fromCallable(() -> {
                if (!eosAccountTokenModels.isEmpty()) {

                    List<CoinMarketCapItem> coinMarketCapItem = mEosManager
                            .getCoinMarketCapListing()
                            .blockingGet()
                            .data;

                    for (EosAccountTokenModel eosAccountTokenModel : eosAccountTokenModels) {
                        EosAccountTokenModel tokenModel = mPocketAppManager
                                .getToken(accountName, eosAccountTokenModel.getContract());

                        if (tokenModel != null && tokenModel.getId() > 0) {
                            if (TextUtils.isEmpty(tokenModel.getCoinmarketcapId())) {
                                for (CoinMarketCapItem data : coinMarketCapItem) {
                                    if (data.symbol.equalsIgnoreCase(tokenModel.getSymbol())) {
                                        tokenModel.setCoinmarketcapId(String.valueOf(data.id));
                                        mPocketAppManager.updateToken(tokenModel);
                                        break;
                                    }
                                }
                            }
                        } else {
                            for (CoinMarketCapItem data : coinMarketCapItem) {
                                if (data.symbol.equalsIgnoreCase(eosAccountTokenModel.getSymbol())) {
                                    eosAccountTokenModel.setCoinmarketcapId(String.valueOf(data.id));
                                    break;
                                }
                            }

                            mPocketAppManager.insertToken(eosAccountTokenModel);
                        }
                    }
                }
                return true;
            });
        })
        .flatMap(result -> {
            return mPocketAppManager.getAllTokens(accountName)
                    .map(tokens -> {
                        List<TokenItem> tokenTOList = new ArrayList<>();

                        for (EosAccountTokenModel token : tokens) {
                            // get token balance
                            CurrencyRequest request = new CurrencyRequest();
                            request.account = accountName;
                            request.code = token.getContract();
                            request.symbol = token.getSymbol();

                            double balance = mEosManager.getTokenBalance(request).blockingGet();

                            TokenItem tokenTO = TokenItem.builder()
                                    .name(token.getTokenName())
                                    .balance(balance)
                                    .build();

                            tokenTOList.add(tokenTO);
                        }

                        return tokenTOList;
                    });
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(tokens -> {
            mTokenAdapterDataModel.clear();
            mTokenAdapterDataModel.addAll(tokens);
            mView.showTokens();
        }, e -> {
            e.printStackTrace();
        });
    }

    public void getAccount(@NonNull String accountName) {
        Single.fromCallable(() -> {
            AccountRequest request = new AccountRequest();
            request.accountName = accountName;
            return request;
        })
        .flatMap((request) -> mEosManager.findAccountByName(request))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(account -> {

        }, e -> {
            e.printStackTrace();
        });
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    public void getEosBalance(@NonNull EosAccountModel eosAccountModel) {
        getTokenBalance(eosAccountModel.getName(), Constants.EOS_TOKEN_CONTRACT, Constants.EOS_SYMBOL);
    }

    public void getTokenBalance(@NonNull String account, @NonNull String code, @NonNull String symbol) {
        Single.fromCallable(() -> {
            CurrencyRequest request = new CurrencyRequest();
            request.account = account;
            request.code = code;
            request.symbol = symbol;

            return request;
        })
        .flatMap((request) -> {
            return mEosManager.getTokenBalance(request);
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(balance -> {
            mView.setEosBalance(balance);
        }, e -> {
            e.printStackTrace();
        });
    }

    public void getMarketPrice(@NonNull String id) {
        mEosManager.getMarketPrice(id)
        .subscribeOn(mProcessScheduler)
        .observeOn(mAndroidScheduler)
        .subscribe(coinMarketCapData -> {
            if (coinMarketCapData.data == null) {
                mView.noMarketPrice();
            } else {
                mView.setMarketPrice(coinMarketCapData);
            }
        }, e -> {
            e.printStackTrace();
            mView.noMarketPrice();
        });
    }

    public void setTokenAdapterDataModel(@NonNull AdapterDataModel<TokenItem> tokenAdapterDataModel) {
        this.mTokenAdapterDataModel = tokenAdapterDataModel;
    }

    public void setTransferAdapterDataModel(@NonNull AdapterDataModel<TransferItem> transferAdapterDataModel) {
        this.mTransferAdapterDataModel = transferAdapterDataModel;
    }

    public void getTransfers(@NonNull String accountName, int page, int perPage) {
        Single.fromCallable(() -> {
            ActionRequest request = new ActionRequest();
            request.accountName = accountName;
            request.pos = -1;
            request.offset = -1;
            return request;
        })
        .flatMap((request) -> mEosManager.getAccountActions(request))
        .flatMap(actionList -> {
            return Single.fromCallable(() -> {
                EosTransferResponse response = new EosTransferResponse();

                if (!actionList.actions.isEmpty()) {
                    long totalActions = actionList.actions.get(0).accountActionSeq;
                    response.setTotalCount(totalActions);
                    List<TransferItem> items = new ArrayList<>();
                    response.setTransfers(items);

                    long pos = page * perPage;
                    long offset = perPage - 1;

                    ActionRequest request = new ActionRequest();
                    request.accountName = accountName;
                    request.pos = pos;
                    request.offset = offset;

                    ActionList actions = mEosManager.getAccountActions(request).blockingGet();

                    for (Action action : actions.actions) {
                        if ("transfer".equalsIgnoreCase(action.actionTrace.act.name)) {
                            TransferItem item = TransferItem.builder()
                                    .id(action.globalActionSeq)
                                    .blockNum(action.blockNum)
                                    .trxId(action.actionTrace.trxId)
                                    .from(action.actionTrace.act.data.from)
                                    .to(action.actionTrace.act.data.to)
                                    .symbol(action.actionTrace.act.data.quantity.split(" ")[1])
                                    .quantity(Double.parseDouble(action.actionTrace.act.data.quantity.split(" ")[0]))
                                    .memo(action.actionTrace.act.data.memo)
                                    .created(action.blockTime)
                                    .send(accountName.equals(action.actionTrace.act.data.from))
                                    .build();

                            items.add(item);
                        }
                    }
                }

                return response;
            });
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(transferResponse -> {
            if (transferResponse.getTotalCount() == 0) {
                mView.noTransferItem();
            } else {
                mTransferAdapterDataModel.addAll(transferResponse.getTransfers());
                mView.showTransferItem();
            }
        }, e -> {
            e.printStackTrace();
            mView.getTransferError();
        });
    }
}
