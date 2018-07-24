package app.eospocket.android.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import java.io.File;

import javax.inject.Singleton;

import app.eospocket.android.common.Constants;
import app.eospocket.android.common.CustomPreference;
import app.eospocket.android.di.ApplicationContext;
import app.eospocket.android.eos.EosManager;
import app.eospocket.android.eos.ServiceBuilder;
import app.eospocket.android.eos.services.ChainService;
import app.eospocket.android.eos.services.HistoryService;
import app.eospocket.android.eos.services.WalletService;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.mithrilcoin.eos.data.local.db.AppDatabase;
import io.mithrilcoin.eos.data.local.repository.EosAccountRepository;
import io.mithrilcoin.eos.data.local.repository.EosAccountRepositoryImpl;
import io.mithrilcoin.eos.data.local.repository.EosNetworkRepository;
import io.mithrilcoin.eos.data.local.repository.EosNetworkRepositoryImpl;
import io.mithrilcoin.eos.data.wallet.EosWalletManager;

@Module
public abstract class AppModule {

    @Binds
    @ApplicationContext
    abstract Context bindContext(Application application);

    @Provides
    @Singleton
    static CustomPreference provideCustomPreference(@ApplicationContext Context context) {
        return new CustomPreference(context);
    }

    @Provides
    @Singleton
    static AppDatabase provideAppDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, Constants.DB_NAME)
                .build();
    }

    @Provides
    @Singleton
    static EosAccountRepository provideEosAccountRepository(AppDatabase database) {
        return new EosAccountRepositoryImpl(database);
    }

    @Provides
    @Singleton
    static EosNetworkRepository provideEosNetworkRepository(AppDatabase database) {
        return new EosNetworkRepositoryImpl(database);
    }

    @Provides
    @Singleton
    static ChainService provideChainService() {
        return ServiceBuilder.createService(ChainService.class, Constants.DEFAULT_URL);
    }

    @Provides
    @Singleton
    static HistoryService provideHistoryService() {
        return ServiceBuilder.createService(HistoryService.class, Constants.DEFAULT_URL);
    }

    @Provides
    @Singleton
    static WalletService provideWalletService() {
        return ServiceBuilder.createService(WalletService.class, Constants.DEFAULT_URL);
    }

    @Provides
    @Singleton
    static EosWalletManager provideEosWalletManager() {
        EosWalletManager eosWalletManager = new EosWalletManager();
        eosWalletManager.setDir(new File(Constants.DEFAULT_WALLET_DIR));
        return eosWalletManager;
    }

    @Provides
    @Singleton
    static EosManager provideEosManager(EosWalletManager eosWalletManager,
            ChainService chainService,
            HistoryService historyService,
            WalletService walletService,
            EosAccountRepository eosAccountRepository,
            EosNetworkRepository eosNetworkRepository) {
        return new EosManager(eosWalletManager, chainService, historyService, walletService,
                eosAccountRepository, eosNetworkRepository);
    }
}
