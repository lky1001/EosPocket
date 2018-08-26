package app.eospocket.android.ui.main.stake;

import app.eospocket.android.common.rxjava.RxJavaSchedulers;
import app.eospocket.android.eos.EosManager;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class StakeFragmentModule {

    @Binds
    public abstract StakeView view(StakeFragment stakeFragment);

    @Provides
    static StakePresenter provideStakePresenter(StakeView stakeView, EosManager eosManager,
            RxJavaSchedulers rxJavaSchedulers) {
        return new StakePresenter(stakeView, eosManager, rxJavaSchedulers);
    }
}
