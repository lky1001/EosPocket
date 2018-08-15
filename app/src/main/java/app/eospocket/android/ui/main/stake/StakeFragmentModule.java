package app.eospocket.android.ui.main.stake;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class StakeFragmentModule {

    @Binds
    public abstract StakeView view(StakeFragment stakeFragment);

    @Provides
    static StakePresenter provideStakePresenter(StakeView stakeView) {
        return new StakePresenter(stakeView);
    }
}
