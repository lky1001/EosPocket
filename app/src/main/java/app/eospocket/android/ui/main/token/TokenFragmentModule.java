package app.eospocket.android.ui.main.token;

import app.eospocket.android.eos.EosManager;
import app.eospocket.android.wallet.PocketAppManager;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class TokenFragmentModule {

    @Binds
    public abstract TokenView view(TokenFragment tokenFragment);

    @Provides
    static TokenPresenter provideTokenPresenter(TokenView tokenView, EosManager eosManager,
            PocketAppManager pocketAppManager) {
        return new TokenPresenter(tokenView, eosManager, pocketAppManager);
    }
}
