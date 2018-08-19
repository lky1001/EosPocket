package app.eospocket.android.ui.action;

import app.eospocket.android.eos.EosManager;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ActionActivityModule {

    @Binds
    public abstract ActionView view(ActionActivity actionActivity);

    @Provides
    static ActionPresenter provideTransactionPresenter(ActionView actionView, EosManager eosManager) {
        return new ActionPresenter(actionView, eosManager);
    }
}
