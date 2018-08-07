package app.eospocket.android.wallet;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.eospocket.android.utils.EncryptUtil;
import app.eospocket.android.wallet.repository.EosAccountRepository;
import app.eospocket.android.wallet.repository.EosAccountTokenRepository;

@Singleton
public class PocketAppManager {

    private EosAccountRepository mEosAccountRepository;

    private EosAccountTokenRepository mEosAccountTokenRepository;

    private EncryptUtil mEncryptUtil;

    @Inject
    public PocketAppManager(EosAccountRepository eosAccountRepository,
            EosAccountTokenRepository eosAccountTokenRepository, EncryptUtil encryptUtil) {
        this.mEosAccountRepository = eosAccountRepository;
        this.mEosAccountTokenRepository = eosAccountTokenRepository;
        this.mEncryptUtil = encryptUtil;
    }
}
