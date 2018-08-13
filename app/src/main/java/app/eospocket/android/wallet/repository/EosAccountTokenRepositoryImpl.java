package app.eospocket.android.wallet.repository;

import java.util.List;

import app.eospocket.android.wallet.db.AppDatabase;
import app.eospocket.android.wallet.db.model.EosAccountTokenModel;

public class EosAccountTokenRepositoryImpl implements EosAccountTokenRepository {

    private AppDatabase mAppDatabase;

    public EosAccountTokenRepositoryImpl(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    @Override
    public void insert(EosAccountTokenModel eosAccountTokenModel) {
        this.mAppDatabase.eosAccountTokenDao().insert(eosAccountTokenModel);
    }

    @Override
    public void insertAll(List<EosAccountTokenModel> eosAccountTokenModels) {
        this.mAppDatabase.eosAccountTokenDao().insertAll(eosAccountTokenModels);
    }
}
