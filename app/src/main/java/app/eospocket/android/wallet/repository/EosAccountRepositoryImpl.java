package app.eospocket.android.wallet.repository;

import android.support.annotation.NonNull;

import java.util.List;

import app.eospocket.android.wallet.db.AppDatabase;
import app.eospocket.android.wallet.db.model.EosAccountModel;
import app.eospocket.android.wallet.db.model.EosAccountTokenModel;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class EosAccountRepositoryImpl implements EosAccountRepository {

    private AppDatabase mAppDatabase;

    public EosAccountRepositoryImpl(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    @Override
    public Single<List<EosAccountModel>> findAccount(String accountName) {
        return mAppDatabase.eosAccountDao().findAccount(accountName);
    }

    @Override
    public void insert(EosAccountModel eosAccountModel) {
        mAppDatabase.eosAccountDao().insert(eosAccountModel);
    }

    @Override
    public void delete(String accountName) {
        mAppDatabase.eosAccountDao().delete(accountName);
    }

    @Override
    public void deleteAll() {
        mAppDatabase.eosAccountDao().deleteAll();
    }

    @Override
    public Flowable<List<EosAccountModel>> getEosAccounts() {
        return mAppDatabase.eosAccountDao().getEosAccounts();
    }

    @Override
    public EosAccountTokenModel getToken(@NonNull String accountName, @NonNull String contract) {
        return mAppDatabase.eosAccountTokenDao().getToken(accountName, contract);
    }

    @Override
    public void insertToken(@NonNull EosAccountTokenModel eosAccountTokenModel) {
        mAppDatabase.eosAccountTokenDao().insert(eosAccountTokenModel);
    }
}
