package app.eospocket.android.eos;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.eospocket.android.eos.services.ChainService;
import app.eospocket.android.eos.services.HistoryService;
import app.eospocket.android.eos.services.WalletService;
import io.mithrilcoin.eos.data.local.repository.EosAccountRepository;
import io.mithrilcoin.eos.data.local.repository.EosNetworkRepository;
import io.mithrilcoin.eos.data.wallet.EosWalletManager;

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
}
