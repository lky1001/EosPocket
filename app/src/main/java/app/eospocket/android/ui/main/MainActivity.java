package app.eospocket.android.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;

import javax.inject.Inject;

import app.eospocket.android.R;
import app.eospocket.android.common.CommonActivity;
import app.eospocket.android.ui.main.setting.SettingFragment;
import app.eospocket.android.ui.main.stake.StakeFragment;
import app.eospocket.android.ui.main.token.TokenFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends CommonActivity implements MainView {

    @Inject
    MainPresenter mMainPresenter;

    @BindView(R.id.navigation)
    BottomNavigationView mBottomNavigationView;

    TokenFragment mTokenFragment = new TokenFragment();
    StakeFragment mStakeFragment = new StakeFragment();
    SettingFragment mSettingFragment = new SettingFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initUi();

        mMainPresenter.onCreate();
    }

    private void initUi() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, mTokenFragment)
                .add(R.id.fragment_container, mStakeFragment)
                .add(R.id.fragment_container, mSettingFragment)
                .commit();

        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mBottomNavigationView.setSelectedItemId(0);
        changeFragment(R.id.navigation_token);
    }

    private boolean changeFragment(int itemId) {
        switch (itemId) {
            case R.id.navigation_token:
                getSupportFragmentManager()
                        .beginTransaction()
                        .show(mTokenFragment)
                        .hide(mStakeFragment)
                        .hide(mSettingFragment)
                        .commit();
                return true;
            case R.id.navigation_stake:
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mTokenFragment)
                        .show(mStakeFragment)
                        .hide(mSettingFragment)
                        .commit();
                return true;
            case R.id.navigation_setting:
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mTokenFragment)
                        .hide(mStakeFragment)
                        .show(mSettingFragment)
                        .commit();
                return true;
        }

        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener = (item) -> {
        return changeFragment(item.getItemId());
    };
}
