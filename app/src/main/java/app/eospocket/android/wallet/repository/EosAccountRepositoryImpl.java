package app.eospocket.android.wallet.repository;

import android.support.annotation.NonNull;

import java.util.List;

import app.eospocket.android.wallet.db.AppDatabase;
import app.eospocket.android.wallet.db.model.EosAccountModel;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class EosAccountRepositoryImpl implements EosAccountRepository {

    private AppDatabase mAppDatabase;

    public EosAccountRepositoryImpl(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    @Override
    public Single<List<EosAccountModel>> findAccount(@NonNull String accountName) {
        return mAppDatabase.eosAccountDao().findAccount(accountName);
    }

    @Override
    public void insert(@NonNull EosAccountModel eosAccountModel) {
        mAppDatabase.eosAccountDao().insert(eosAccountModel);
    }

    @Override
    public void update(@NonNull EosAccountModel eosAccountModel) {
        mAppDatabase.eosAccountDao().update(eosAccountModel);
    }

    @Override
    public void delete(@NonNull EosAccountModel eosAccountModel) {
        mAppDatabase.eosAccountDao().delete(eosAccountModel);
    }

    @Override
    public void delete(@NonNull String accountName) {
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
    public Single<EosAccountModel> findOneById(int id) {
        return mAppDatabase.eosAccountDao().findOneById(id);
    }
}
