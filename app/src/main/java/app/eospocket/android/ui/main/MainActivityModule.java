package app.eospocket.android.ui.main;

import app.eospocket.android.eos.EosManager;
import app.eospocket.android.wallet.PocketAppManager;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MainActivityModule {

    @Binds
    public abstract MainView view(MainActivity mainActivity);

    @Provides
    static MainPresenter provideMainPresenter(MainView  mainView, EosManager eosManager,
            PocketAppManager pocketAppManager) {
        return new MainPresenter(mainView, eosManager, pocketAppManager);
    }
}
