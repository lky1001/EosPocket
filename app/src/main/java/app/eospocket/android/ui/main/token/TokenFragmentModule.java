package app.eospocket.android.ui.main.token;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class TokenFragmentModule {

    @Binds
    public abstract TokenView view(TokenFragment tokenFragment);

    @Provides
    static TokenPresenter provideTokenPresenter(TokenView tokenView) {
        return new TokenPresenter(tokenView);
    }
}
