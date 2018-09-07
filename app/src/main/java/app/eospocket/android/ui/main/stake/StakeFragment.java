package app.eospocket.android.ui.main.stake;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import app.eospocket.android.R;
import app.eospocket.android.common.CommonFragment;
import app.eospocket.android.eos.model.account.EosAccount;
import app.eospocket.android.ui.main.MainNavigationFragment;
import app.eospocket.android.wallet.LoginAccountManager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StakeFragment extends CommonFragment implements MainNavigationFragment, StakeView {

    private static final String TEST_ACCOUNT_NAME = "faceostest12";

    @Inject
    StakePresenter mStakePresenter;

    @Inject
    LoginAccountManager loginAccountManager;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.stake_list_view)
    RecyclerView mStakeListView;

    private StakeAdapter stakeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stake, container, false);
        ButterKnife.bind(this, view);

        stakeAdapter = new StakeAdapter();
        mStakeListView.setLayoutManager(new LinearLayoutManager(getContext()));
        mStakeListView.setAdapter(stakeAdapter);

        mStakePresenter.onCreate();
        mStakePresenter.loadEosAccount(TEST_ACCOUNT_NAME);

        loginAccountManager.getChangeAccountId()
                .subscribe(changeId -> {

                });

        return view;
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onUserInteraction() {

    }

    @Override
    public void loadEosAccountSuccess(EosAccount eosAccount) {
        stakeAdapter.refresh(eosAccount);
    }

    @Override
    public void loadEosAccountFail(Throwable t) {
        Toast.makeText(getContext(), "loadEosAccountFail", Toast.LENGTH_SHORT).show();
    }
}
