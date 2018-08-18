package app.eospocket.android.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;

import javax.inject.Inject;

import app.eospocket.android.R;
import app.eospocket.android.common.CommonActivity;
import app.eospocket.android.ui.main.action.ActionFragment;
import app.eospocket.android.ui.main.setting.SettingFragment;
import app.eospocket.android.ui.main.stake.StakeFragment;
import app.eospocket.android.ui.main.balance.BalanceFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends CommonActivity implements MainView {

    @Inject
    MainPresenter mMainPresenter;

    @BindView(R.id.navigation)
    BottomNavigationView mBottomNavigationView;

    BalanceFragment mBalanceFragment = new BalanceFragment();
    StakeFragment mStakeFragment = new StakeFragment();
    SettingFragment mSettingFragment = new SettingFragment();
    ActionFragment mActionFragment = new ActionFragment();

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
                .add(R.id.fragment_container, mBalanceFragment)
                .add(R.id.fragment_container, mStakeFragment)
                .add(R.id.fragment_container, mSettingFragment)
                .add(R.id.fragment_container, mActionFragment)
                .commit();

        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mBottomNavigationView.setSelectedItemId(0);
        changeFragment(R.id.navigation_balance);
    }

    private boolean changeFragment(int itemId) {
        switch (itemId) {
            case R.id.navigation_balance:
                getSupportFragmentManager()
                        .beginTransaction()
                        .show(mBalanceFragment)
                        .hide(mStakeFragment)
                        .hide(mActionFragment)
                        .hide(mSettingFragment)
                        .commit();
                return true;
            case R.id.navigation_stake:
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mBalanceFragment)
                        .show(mStakeFragment)
                        .hide(mActionFragment)
                        .hide(mSettingFragment)
                        .commit();
                return true;
            case R.id.navigation_action:
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mBalanceFragment)
                        .hide(mStakeFragment)
                        .show(mActionFragment)
                        .hide(mSettingFragment)
                        .commit();
            case R.id.navigation_setting:
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mBalanceFragment)
                        .hide(mStakeFragment)
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
