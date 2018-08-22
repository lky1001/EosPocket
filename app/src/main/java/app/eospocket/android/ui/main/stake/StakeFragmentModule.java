package app.eospocket.android.ui.main.stake;

import app.eospocket.android.eos.EosManager;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public abstract class StakeFragmentModule {

    @Binds
    public abstract StakeView view(StakeFragment stakeFragment);

    @Provides
    static StakePresenter provideStakePresenter(StakeView stakeView, EosManager eosManager) {
        return new StakePresenter(stakeView, eosManager, Schedulers.io(), AndroidSchedulers.mainThread());
    }
}
