package app.eospocket.android.ui.intro;

import app.eospocket.android.eos.EosManager;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class IntroActivityModule {

    @Binds
    public abstract IntroView view(IntroActivity introActivity);

    @Provides
    static IntroPresenter provideIntroPresenter(IntroView introView, EosManager eosManager) {
        return new IntroPresenter(introView, eosManager);
    }
}
