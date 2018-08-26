package app.eospocket.android.ui.main.balance;

import app.eospocket.android.common.CustomPreference;
import app.eospocket.android.common.rxjava.RxJavaSchedulers;
import app.eospocket.android.eos.EosManager;
import app.eospocket.android.wallet.PocketAppManager;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class BalanceFragmentModule {

    @Binds
    public abstract BalanceView view(BalanceFragment balanceFragment);

    @Provides
    static BalancePresenter provideTokenPresenter(BalanceView balanceView, EosManager eosManager,
            PocketAppManager pocketAppManager, CustomPreference customPreference, RxJavaSchedulers rxJavaSchedulers) {
        return new BalancePresenter(balanceView, eosManager, pocketAppManager, customPreference,
                rxJavaSchedulers);
    }
}
