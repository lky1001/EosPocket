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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import app.eospocket.android.R;
import app.eospocket.android.common.CommonFragment;
import app.eospocket.android.eos.model.account.EosAccount;
import app.eospocket.android.ui.main.MainNavigationFragment;
import app.eospocket.android.wallet.LoginAccountManager;
import app.eospocket.android.wallet.db.model.EosAccountModel;
import app.eospocket.android.wallet.repository.EosAccountRepository;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StakeFragment extends CommonFragment implements MainNavigationFragment, StakeView {

    private static final String TEST_ACCOUNT_NAME = "faceostest12";

    @Inject
    StakePresenter mStakePresenter;

    @Inject
    LoginAccountManager loginAccountManager;

    @Inject
    EosAccountRepository mEosAccountRepository;

    @BindView(R.id.account_spinner)
    Spinner mAccountSpinner;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.stake_list_view)
    RecyclerView mStakeListView;


    private StakeAdapter mStakeAdapter;
    private ArrayAdapter<EosAccountModel> mAccountAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stake, container, false);
        ButterKnife.bind(this, view);

        mStakeAdapter = new StakeAdapter();
        mStakeListView.setLayoutManager(new LinearLayoutManager(getContext()));
        mStakeListView.setAdapter(mStakeAdapter);

        mStakePresenter.onCreate();
        mStakePresenter.loadEosAccount(TEST_ACCOUNT_NAME);

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
    public void loadEosAccountListSuccess(List<EosAccountModel> eosAccountModelList) {
        if (getContext() == null) {
            return;
        }
        mAccountAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,
                eosAccountModelList);
        mAccountAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAccountSpinner.setAdapter(mAccountAdapter);
        mAccountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loginAccountManager.changeSelectedAccountId(mAccountAdapter.getItem(i).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        loginAccountManager.getChangeAccountId()
                .subscribe(changeId -> {
                    for (int i=0; i<eosAccountModelList.size(); i++) {
                        EosAccountModel model = eosAccountModelList.get(i);
                        if (model.getId() == changeId) {
                            mAccountSpinner.setSelection(i);
                            mStakePresenter.loadEosAccount(model.getName());
                        }
                    }
                });
    }

    @Override
    public void loadEosAccountListFail(Throwable t) {
        Toast.makeText(getContext(), "loadEosAccountListFail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadEosAccountSuccess(EosAccount eosAccount) {
        mStakeAdapter.refresh(eosAccount);
    }

    @Override
    public void loadEosAccountFail(Throwable t) {
        Toast.makeText(getContext(), "loadEosAccountFail", Toast.LENGTH_SHORT).show();
    }
}
