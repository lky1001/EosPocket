package app.eospocket.android.ui.main.more;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MoreFragmentModule {

    @Binds
    public abstract MoreView view(MoreFragment moreFragment);

    @Provides
    static MorePresenter provideSettingPresenter(MoreView moreView) {
        return new MorePresenter(moreView);
    }
}
