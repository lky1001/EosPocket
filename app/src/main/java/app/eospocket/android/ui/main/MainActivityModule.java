package app.eospocket.android.ui.main;

import app.eospocket.android.common.rxjava.RxJavaSchedulers;
import app.eospocket.android.wallet.repository.EosAccountRepository;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MainActivityModule {

    @Binds
    public abstract MainView view(MainActivity mainActivity);

    @Provides
    static MainPresenter provideMainPresenter(MainView mainView, RxJavaSchedulers rxJavaSchedulers,
                                              EosAccountRepository eosAccountRepository) {
        return new MainPresenter(mainView, rxJavaSchedulers, eosAccountRepository);
    }
}
