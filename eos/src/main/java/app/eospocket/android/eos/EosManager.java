package app.eospocket.android.eos;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.eospocket.android.eos.model.AccountList;
import app.eospocket.android.eos.model.ChainInfo;
import app.eospocket.android.eos.model.account.EosAccount;
import app.eospocket.android.eos.model.action.ActionList;
import app.eospocket.android.eos.model.chain.CurrencyStat;
import app.eospocket.android.eos.model.coinmarketcap.CoinMarketCap;
import app.eospocket.android.eos.model.coinmarketcap.CoinMarketCapItemList;
import app.eospocket.android.eos.request.AccountRequest;
import app.eospocket.android.eos.request.ActionRequest;
import app.eospocket.android.eos.request.CurrencyRequest;
import app.eospocket.android.eos.request.CurrencyStatsRequest;
import app.eospocket.android.eos.request.KeyAccountsRequest;
import app.eospocket.android.eos.services.ChainService;
import app.eospocket.android.eos.services.CoinMarketCapService;
import app.eospocket.android.eos.services.HistoryService;
import app.eospocket.android.eos.services.WalletService;
import io.mithrilcoin.eos.crypto.ec.EosPrivateKey;
import io.mithrilcoin.eos.data.wallet.EosWalletManager;
import io.reactivex.Single;

/**
 * https://developers.eos.io/eosio-nodeos/reference
 */
@Singleton
public class EosManager {

    public static final int SUCCESS = 0;
    public static final int INVALID_PASSWORD = -1;

    private static final int TX_EXPIRATION_IN_MS = 30 * 1000;

    private EosWalletManager mEosWalletManager;

    private ChainService mChainService;

    private HistoryService mHistoryService;

    private WalletService mWalletService;

    private CoinMarketCapService mCoinMarketCapService;

    @Inject
    public EosManager(@NonNull EosWalletManager eosWalletManager,
            @NonNull ChainService chainService, @NonNull HistoryService historyService,
            @NonNull WalletService walletService, @NonNull CoinMarketCapService coinMarketCapService) {
        this.mEosWalletManager = eosWalletManager;
        this.mChainService = chainService;
        this.mHistoryService = historyService;
        this.mWalletService = walletService;
        this.mCoinMarketCapService = coinMarketCapService;
    }

    @NonNull
    public Single<Boolean> hasWallet(@NonNull String walletName) {
        return Single.fromCallable(() -> mEosWalletManager.walletExists(walletName));
    }

    @NonNull
    public Single<ChainInfo> getChainInfo() {
        return mChainService.getInfo();
    }

    public Single<String> createWallet(@NonNull String walletName) {
        return Single.create(emitter -> {
            try {
                String pw = mEosWalletManager.create(walletName);

                if (!emitter.isDisposed()) {
                    emitter.onSuccess(pw);
                }
            } catch (Exception e) {
                if (!emitter.isDisposed()) {
                    emitter.onError(e);
                }
            }
        });
    }

    @NonNull
    public Single<Integer> unlock(@NonNull String walletName, @NonNull String password) {
        return Single.fromCallable(() -> mEosWalletManager.unlock(walletName, password) ? SUCCESS : INVALID_PASSWORD);
    }

    @NonNull
    public EosPrivateKey genPrivateKey() {
        return new EosPrivateKey();
    }

    @NonNull
    public Single<AccountList> findAccountByPublicKey(@NonNull KeyAccountsRequest request) {
        return mHistoryService.getAccountsByKey(request);
    }

    @NonNull
    public Single<EosAccount> findAccountByName(@NonNull AccountRequest request) {
        return mChainService.getAccount(request);
    }

    @NonNull
    public Single<ActionList> getAccountActions(@NonNull ActionRequest request) {
        return mHistoryService.getAccountActions(request);
    }

    public Single<Double> getTokenBalance(@NonNull CurrencyRequest request) {
        return mChainService.getCurrencyBalance(request)
                .map(balance -> {
                   if (!balance.isEmpty()) {
                       return Double.parseDouble(balance.get(0).split(" ")[0]);
                   }

                   return 0.0;
                });
    }

    public Single<CoinMarketCap> getMarketPrice(@NonNull String id) {
        return mCoinMarketCapService.getPrice(id);
    }

    public Single<CoinMarketCapItemList> getCoinMarketCapListing() {
        return mCoinMarketCapService.getListing();
    }

    public Single<CurrencyStat> getCurrencyStats(@NonNull CurrencyStatsRequest request) {
        return mChainService.getCurrencyStats(request);
    }

//    public Single<JsonObject> transferToken(String contract, String authorization,
//            String from, String to, String amount, String memo) {
//        EosTransfer transfer = new EosTransfer(from, to, amount, memo);
//
//        return pushActionRetJson(contract, transfer.getActionName(), Utils.prettyPrintJson(transfer),
//                new String[] {authorization});
//    }

//    private Single<JsonObject> pushActionRetJson(String contract, String action, String data, String[] permissions) {
//        return this.mChainService.jsonToBin(new JsonToBinRequest(contract, action, data))
//                .flatMap(jsonToBinResp -> getChainInfo().map(info -> createTransaction(contract, action, jsonToBinResp.getBinargs(), permissions, info)))
//                .flatMap(this::signAndPackTransaction)
//                .flatMap(mChainService::pushTransactionRetJson);
//    }
//
//    private SignedTransaction createTransaction(String contract, String actionName, String dataAsHex, String[] permissions, EosChainInfo chainInfo) {
//        Action action = new Action(contract, actionName);
//        action.setAuthorization(permissions);
//        action.setData(dataAsHex);
//
//        SignedTransaction txn = new SignedTransaction();
//        txn.addAction(action);
//        txn.putSignatures(new ArrayList<>());
//
//        if (null != chainInfo) {
//            txn.setReferenceBlock(chainInfo.getHeadBlockId());
//            txn.setExpiration(chainInfo.getTimeAfterHeadBlockTime(TX_EXPIRATION_IN_MS));
//        }
//
//        return txn;
//    }
//
//    private Single<PackedTransaction> signAndPackTransaction(final SignedTransaction txnBeforeSign) {
//        return Single.fromCallable(() -> {
//            SignedTransaction stxn = new SignedTransaction(txnBeforeSign);
//            stxn.sign(eosPrivateKey, new TypeChainId(chainId));
//
//            return new PackedTransaction(stxn);
//        });
//    }
}
