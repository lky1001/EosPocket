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
import app.eospocket.android.wallet.repository.EosAccountRepository;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StakeFragment extends CommonFragment implements MainNavigationFragment, StakeView {

    @Inject
    StakePresenter mStakePresenter;

    @Inject
    LoginAccountManager mLoginAccountManager;

    @Inject
    EosAccountRepository mEosAccountRepository;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.stake_list_view)
    RecyclerView mStakeListView;


    private StakeAdapter mStakeAdapter;
    private StakeClickListener mStakeClickListener = new StakeClickListener() {
        @Override
        public void onClickStakeCpu() {


            StakeDialog dialog = new StakeDialog(getContext());
            dialog.setStakeDialogCallback(new StakeDialog.StakeDialogCallback() {
                @Override
                public void onConfirm(String to, double cpuStake, double netStake, boolean isTransfer) {

                    int accountId = mLoginAccountManager.getSelectedId();
                    String pw = "1234567890abcd!@#";
                    // send eos to account
                    mStakePresenter.stakeCpu(accountId, pw, to, cpuStake, netStake, isTransfer ? 1 : 0);

                }
            });
            dialog.show();



//            //TODO dialog
//            int accountId = mLoginAccountManager.getSelectedId();
//            String to = "received account name";
//            double cpuAmount = 0.1000;
//            double netAmount = 0.1000;
//            String pw = "1234567890abcd!@#";
//            // send eos to account
//            boolean isTransfer = false;
//            //TODO transfer check
//            mStakePresenter.stakeCpu(accountId, pw, to, cpuAmount, netAmount, isTransfer ? 1 : 0);
        }

        @Override
        public void onClickStakeNetwork() {

        }

        @Override
        public void onClickRefund() {

        }

        @Override
        public void onClickBuyRam() {

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stake, container, false);
        ButterKnife.bind(this, view);

        mStakeAdapter = new StakeAdapter(mStakeClickListener);
        mStakeListView.setLayoutManager(new LinearLayoutManager(getContext()));
        mStakeListView.setAdapter(mStakeAdapter);

        mStakePresenter.onCreate();
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
        mStakeAdapter.refresh(eosAccount);
    }

    @Override
    public void loadEosAccountFail(Throwable t) {
        Toast.makeText(getContext(), "loadEosAccountFail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mStakePresenter.onDestroy();
    }

    interface StakeClickListener {
        void onClickStakeCpu();
        void onClickStakeNetwork();
        void onClickRefund();
        void onClickBuyRam();
    }
}
