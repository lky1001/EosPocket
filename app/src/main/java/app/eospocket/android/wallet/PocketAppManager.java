package app.eospocket.android.wallet;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.eospocket.android.utils.EncryptUtil;
import app.eospocket.android.wallet.db.model.EosAccountModel;
import app.eospocket.android.wallet.repository.EosAccountRepository;
import app.eospocket.android.wallet.repository.EosAccountTokenRepository;
import io.reactivex.Single;

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

    public Single<List<EosAccountModel>> findAccount(@NonNull String accountName) {
        return mEosAccountRepository.findAccount(accountName);
    }

    public void insert(@NonNull EosAccountModel eosAccountModel) {
        mEosAccountRepository.insert(eosAccountModel);
    }
}
