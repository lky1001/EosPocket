package app.eospocket.android.wallet.repository;

import app.eospocket.android.wallet.db.AppDatabase;

public class EosAccountTokenRepositoryImpl implements EosAccountTokenRepository {

    private AppDatabase mAppDatabase;

    public EosAccountTokenRepositoryImpl(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }
}
