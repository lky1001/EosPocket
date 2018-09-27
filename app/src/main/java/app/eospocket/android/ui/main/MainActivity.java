package app.eospocket.android.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import javax.inject.Inject;

import app.eospocket.android.R;
import app.eospocket.android.common.CommonActivity;
import app.eospocket.android.ui.importaccount.ImportAccountActivity;
import app.eospocket.android.ui.main.action.ActionFragment;
import app.eospocket.android.ui.main.balance.BalanceFragment;
import app.eospocket.android.ui.main.more.MoreFragment;
import app.eospocket.android.ui.main.stake.StakeFragment;
import app.eospocket.android.wallet.LoginAccountManager;
import app.eospocket.android.wallet.db.model.EosAccountModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends CommonActivity implements MainView, NavigationView.OnNavigationItemSelectedListener {

    @Inject
    MainPresenter mMainPresenter;

    @Inject
    LoginAccountManager loginAccountManager;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @BindView(R.id.navigation)
    BottomNavigationView mBottomNavigationView;

    @BindView(R.id.nav_view)
    NavigationView mSideMenu;

    Spinner mAccountSpinner;
    private ArrayAdapter<EosAccountModel> mAccountAdapter;

    BalanceFragment mBalanceFragment = new BalanceFragment();
    StakeFragment mStakeFragment = new StakeFragment();
    MoreFragment mMoreFragment = new MoreFragment();
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
                .add(R.id.fragment_container, mMoreFragment)
                .add(R.id.fragment_container, mActionFragment)
                .commit();

        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mBottomNavigationView.setSelectedItemId(0);
        changeFragment(R.id.navigation_balance);



        //init Side Menu(NavigationView)

        View header = LayoutInflater.from(this).inflate(R.layout.layout_navigation_header, mSideMenu);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        mSideMenu.setNavigationItemSelectedListener(this);

        mAccountSpinner = header.findViewById(R.id.account_spinner);
        mSideMenu.inflateMenu(R.menu.side_navigation_menu);
    }

    private boolean changeFragment(int itemId) {
        switch (itemId) {
            case R.id.navigation_balance:
                getSupportFragmentManager()
                        .beginTransaction()
                        .show(mBalanceFragment)
                        .hide(mStakeFragment)
                        .hide(mActionFragment)
                        .hide(mMoreFragment)
                        .commit();
                mToolbar.setTitle(R.string.title_balance);
                return true;
            case R.id.navigation_stake:
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mBalanceFragment)
                        .show(mStakeFragment)
                        .hide(mActionFragment)
                        .hide(mMoreFragment)
                        .commit();
                mToolbar.setTitle(R.string.title_stake);
                return true;
            case R.id.navigation_action:
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mBalanceFragment)
                        .hide(mStakeFragment)
                        .show(mActionFragment)
                        .hide(mMoreFragment)
                        .commit();
                mToolbar.setTitle(R.string.title_action);
            case R.id.navigation_more:
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mBalanceFragment)
                        .hide(mStakeFragment)
                        .hide(mStakeFragment)
                        .show(mMoreFragment)
                        .commit();
                mToolbar.setTitle(R.string.title_more);
                return true;
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawers();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.drawer_item_import_account:
                Intent intent = new Intent(this, ImportAccountActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener = (item) -> changeFragment(item.getItemId());

    @Override
    public void loadEosAccountListSuccess(List<EosAccountModel> eosAccountModelList) {
        if (eosAccountModelList.isEmpty()) {
            mBalanceFragment.visibleImportAccountButton();
            return;
        }
        mAccountAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, eosAccountModelList);
        mAccountAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mAccountSpinner.setAdapter(mAccountAdapter);
        int selectedId = loginAccountManager.getSelectedId();
        for (int i=0; i<eosAccountModelList.size(); i++) {
            EosAccountModel model = eosAccountModelList.get(i);
            if (model.getId() == selectedId) {
                mAccountSpinner.setSelection(i);
                break;
            }
        }
        mAccountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loginAccountManager.changeSelectedAccountId(mAccountAdapter.getItem(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void loadEosAccountListFail(Throwable t) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
