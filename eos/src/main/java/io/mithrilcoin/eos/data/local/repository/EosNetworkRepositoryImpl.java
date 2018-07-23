package io.mithrilcoin.eos.data.local.repository;

import android.support.annotation.NonNull;

import io.mithrilcoin.eos.data.local.db.AppDatabase;

public class EosNetworkRepositoryImpl implements EosNetworkRepository {

    private AppDatabase mAppDatabase;

    public EosNetworkRepositoryImpl(@NonNull AppDatabase appDatabase ) {
        this.mAppDatabase = appDatabase;
    }
}
