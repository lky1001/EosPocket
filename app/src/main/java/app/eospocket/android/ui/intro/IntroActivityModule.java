package app.eospocket.android.ui.intro;

import app.eospocket.android.common.rxjava.RxJavaSchedulers;
import app.eospocket.android.security.AuthManager;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class IntroActivityModule {

    @Binds
    public abstract IntroView view(IntroActivity introActivity);

    @Provides
    static IntroPresenter provideIntroPresenter(IntroView introView, AuthManager authManager,
            RxJavaSchedulers rxJavaSchedulers) {
        return new IntroPresenter(introView, authManager, rxJavaSchedulers);
    }
}
