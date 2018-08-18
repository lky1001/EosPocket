package app.eospocket.android.ui.main.balance;

import app.eospocket.android.di.FragmentScoped;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BalanceModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = {BalanceFragmentModule.class})
    public abstract BalanceFragment contributeTokenFragment();
}
