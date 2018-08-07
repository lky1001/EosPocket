package app.eospocket.android.wallet.repository;

import app.eospocket.android.wallet.db.AppDatabase;

public class EosAccountRepositoryImpl implements EosAccountRepository {

    private AppDatabase mAppDatabase;

    public EosAccountRepositoryImpl(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

}
