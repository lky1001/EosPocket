package app.eospocket.android.ui.main.balance;

import app.eospocket.android.common.CustomPreference;
import app.eospocket.android.eos.EosManager;
import app.eospocket.android.wallet.PocketAppManager;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public abstract class BalanceFragmentModule {

    @Binds
    public abstract BalanceView view(BalanceFragment balanceFragment);

    @Provides
    static BalancePresenter provideTokenPresenter(BalanceView balanceView, EosManager eosManager,
            PocketAppManager pocketAppManager, CustomPreference customPreference) {
        return new BalancePresenter(balanceView, eosManager, pocketAppManager, customPreference,
                Schedulers.io(), AndroidSchedulers.mainThread());
    }
}
