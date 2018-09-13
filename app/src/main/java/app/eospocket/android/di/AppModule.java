package app.eospocket.android.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Build;
import android.os.Environment;

import java.io.File;

import javax.inject.Singleton;

import app.eospocket.android.common.Constants;
import app.eospocket.android.common.CustomPreference;
import app.eospocket.android.common.rxjava.RxJavaSchedulers;
import app.eospocket.android.common.rxjava.RxJavaSchedulersImpl;
import app.eospocket.android.di.ApplicationContext;
import app.eospocket.android.eos.EosManager;
import app.eospocket.android.eos.ServiceBuilder;
import app.eospocket.android.eos.services.ChainService;
import app.eospocket.android.eos.services.CoinMarketCapService;
import app.eospocket.android.eos.services.HistoryService;
import app.eospocket.android.eos.services.WalletService;
import app.eospocket.android.security.AuthManager;
import app.eospocket.android.security.keystore.KeyStore;
import app.eospocket.android.security.keystore.KeyStoreApi18Impl;
import app.eospocket.android.security.keystore.KeyStoreApi23Impl;
import app.eospocket.android.utils.EncryptUtil;
import app.eospocket.android.wallet.LoginAccountManager;
import app.eospocket.android.wallet.PocketAppManager;
import app.eospocket.android.wallet.db.AppDatabase;
import app.eospocket.android.wallet.repository.EosAccountRepository;
import app.eospocket.android.wallet.repository.EosAccountRepositoryImpl;
import app.eospocket.android.wallet.repository.EosAccountTokenRepository;
import app.eospocket.android.wallet.repository.EosAccountTokenRepositoryImpl;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.mithrilcoin.eos.data.wallet.EosWalletManager;

@Module
public abstract class AppModule {

    @Binds
    @ApplicationContext
    abstract Context bindContext(Application application);

    @Provides
    @Singleton
    static RxJavaSchedulers provideRxJavaSchedulers() {
        return new RxJavaSchedulersImpl();
    }

    @Provides
    @Singleton
    static CustomPreference provideCustomPreference(@ApplicationContext Context context) {
        CustomPreference customPreference = new CustomPreference(context);
        customPreference.loadSettings();

        return customPreference;
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
    static EosAccountTokenRepository provideEosAccountTokenRepository(AppDatabase database) {
        return new EosAccountTokenRepositoryImpl(database);
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
    static CoinMarketCapService provideCoinMarketCapService() {
        return ServiceBuilder.createService(CoinMarketCapService.class, Constants.COINMARKETCAP_HOST);
    }

    @Provides
    @Singleton
    static EosWalletManager provideEosWalletManager() {
        EosWalletManager eosWalletManager = new EosWalletManager();
        eosWalletManager.setDir(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + Constants.DEFAULT_WALLET_DIR));
        return eosWalletManager;
    }

    @Provides
    @Singleton
    static EosManager provideEosManager(EosWalletManager eosWalletManager,
            ChainService chainService,
            HistoryService historyService,
            WalletService walletService,
            CoinMarketCapService coinMarketCapService) {
        return new EosManager(eosWalletManager, chainService, historyService, walletService, coinMarketCapService);
    }

    @Provides
    @Singleton
    static KeyStore provideKeyStore(@ApplicationContext Context context, CustomPreference customPreference) {
        KeyStore keyStore = null;

        if (!customPreference.getInitWallet()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                keyStore = new KeyStoreApi23Impl(customPreference);
            } else {
                keyStore = new KeyStoreApi18Impl(context);
            }
        } else {
            // check os update
            if (customPreference.getKeyStoreVersion() >= Build.VERSION_CODES.M) {
                keyStore = new KeyStoreApi23Impl(customPreference);
            } else {
                keyStore = new KeyStoreApi18Impl(context);
            }
        }

        keyStore.init();
        keyStore.createKeys(Constants.KEYSTORE_ALIAS);
        keyStore.createKeys(Constants.KEYSTORE_PRIV_KEY_ALIAS);

        return keyStore;
    }


    @Provides
    @Singleton
    static EncryptUtil provideEncryptUtil() {
        return new EncryptUtil();
    }

    @Provides
    @Singleton
    static AuthManager provideAuthManager(CustomPreference customPreference, KeyStore keyStore) {
        AuthManager authManager = new AuthManager(customPreference, keyStore);
        authManager.init();

        return authManager;
    }

    @Provides
    @Singleton
    static PocketAppManager providePocketAppManager(EosAccountRepository eosAccountRepository,
            EosAccountTokenRepository eosAccountTokenRepository, EncryptUtil encryptUtil) {
        return new PocketAppManager(eosAccountRepository, eosAccountTokenRepository, encryptUtil);
    }

    @Provides
    @Singleton
    static LoginAccountManager provideLoginAccountManager(CustomPreference customPreference) {
        LoginAccountManager accountManager = new LoginAccountManager(customPreference);

        return accountManager;
    }
}
