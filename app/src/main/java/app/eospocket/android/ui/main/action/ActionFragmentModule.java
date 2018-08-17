package app.eospocket.android.ui.main.action;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ActionFragmentModule {

    @Binds
    public abstract ActionView view(ActionFragment actionFragment);

    @Provides
    static ActionPresenter provideActionPresenter(ActionView actionView) {
        return new ActionPresenter(actionView);
    }
}
