package app.eospocket.android.wallet;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.eospocket.android.utils.EncryptUtil;
import app.eospocket.android.wallet.db.model.EosAccountModel;
import app.eospocket.android.wallet.db.model.EosAccountTokenModel;
import app.eospocket.android.wallet.repository.EosAccountRepository;
import app.eospocket.android.wallet.repository.EosAccountTokenRepository;
import io.reactivex.Single;

@Singleton
public class PocketAppManager {

    private EosAccountRepository mEosAccountRepository;

    private EosAccountTokenRepository mEosAccountTokenRepository;

    private EncryptUtil mEncryptUtil;

    @Inject
    public PocketAppManager(@NonNull EosAccountRepository eosAccountRepository,
            @NonNull EosAccountTokenRepository eosAccountTokenRepository,
            @NonNull EncryptUtil encryptUtil) {
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

    public void insertAllTokens(@NonNull List<EosAccountTokenModel> eosAccountTokenModels) {
        mEosAccountTokenRepository.insertAll(eosAccountTokenModels);
    }

    public Single<List<EosAccountTokenModel>> getAllTokens(@NonNull String accountName) {
        return mEosAccountTokenRepository.getAllTokens(accountName);
    }

    public EosAccountTokenModel getToken(@NonNull String accountName, @NonNull String contract) {
        return mEosAccountTokenRepository.getToken(accountName, contract);
    }

    public void insertToken(@NonNull EosAccountTokenModel eosAccountTokenModel) {
        mEosAccountTokenRepository.insert(eosAccountTokenModel);
    }

    public void updateToken(@NonNull EosAccountTokenModel tokenModel) {
        mEosAccountTokenRepository.updateToken(tokenModel);
    }
}
