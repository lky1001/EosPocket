package app.eospocket.android.eos;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.eospocket.android.eos.services.ChainService;
import app.eospocket.android.eos.services.HistoryService;
import app.eospocket.android.eos.services.WalletService;
import io.mithrilcoin.eos.data.local.repository.EosAccountRepository;
import io.mithrilcoin.eos.data.local.repository.EosNetworkRepository;
import io.mithrilcoin.eos.data.remote.model.api.EosChainInfo;
import io.mithrilcoin.eos.data.wallet.EosWalletManager;
import io.mithrilcoin.eos.util.Consts;
import io.reactivex.Single;

/**
 * https://developers.eos.io/eosio-nodeos/reference
 */
@Singleton
public class EosManager {

    private EosWalletManager mEosWalletManager;

    private ChainService mChainService;

    private HistoryService mHistoryService;

    private WalletService mWalletService;

    private EosAccountRepository mEosAccountRepository;

    private EosNetworkRepository mEosNetworkRepository;

    @Inject
    public EosManager(EosWalletManager eosWalletManager,
            ChainService chainService,
            HistoryService historyService,
            WalletService walletService,
            EosAccountRepository eosAccountRepository,
            EosNetworkRepository eosNetworkRepository) {
        this.mEosWalletManager = eosWalletManager;
        this.mChainService = chainService;
        this.mHistoryService = historyService;
        this.mWalletService = walletService;
        this.mEosAccountRepository = eosAccountRepository;
        this.mEosNetworkRepository = eosNetworkRepository;
    }

    public Single<Boolean> hasWallet(String walletName) {
        return Single.fromCallable(() -> mEosWalletManager.walletExists(walletName));
    }

    public Single<EosChainInfo> getChainInfo() {
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
}
