package app.eospocket.android.ui.main.token;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import javax.inject.Inject;

import app.eospocket.android.R;
import app.eospocket.android.common.CommonFragment;
import app.eospocket.android.common.Constants;
import app.eospocket.android.ui.importaccount.ImportAccountActivity;
import app.eospocket.android.ui.main.MainNavigationFragment;
import app.eospocket.android.wallet.repository.EosAccountRepository;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TokenFragment extends CommonFragment implements MainNavigationFragment, TokenView {

    @Inject
    TokenPresenter mTokenPresenter;

    @BindView(R.id.btn_import_account)
    Button mImportAccountButton;

    @BindView(R.id.eos_balance_text)
    TextView mEosBalanceText;

    @Inject
    EosAccountRepository mEosAccountRepository;

    private Disposable mAccountDisposable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_token, container, false);
        ButterKnife.bind(this, view);

        mTokenPresenter.onCreate();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        subscribeAccounts();
    }

    @Override
    public void onDestroy() {
        disposableAccounts();
        super.onDestroy();
    }

    private void disposableAccounts() {
        if (mAccountDisposable != null && !mAccountDisposable.isDisposed()) {
            mAccountDisposable.isDisposed();
            mAccountDisposable = null;
        }
    }

    private void subscribeAccounts() {
        disposableAccounts();

        mAccountDisposable = mEosAccountRepository.getEosAccounts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((eosAccountModels) -> {
                    if (!eosAccountModels.isEmpty()) {
                        mImportAccountButton.setVisibility(View.GONE);
                        mTokenPresenter.getEosBalance(eosAccountModels.get(0));
                        mTokenPresenter.getTokens(eosAccountModels.get(0).getName());
                    } else {
                        mImportAccountButton.setVisibility(View.VISIBLE);
                    }
                }, e -> {

                }, () -> {

                });
    }

    private void getAccount() {
        String accountName = "";

        mTokenPresenter.getAccount(accountName);
    }

    @OnClick(R.id.btn_import_account)
    public void onImportAccountClick() {
        startActivity(ImportAccountActivity.class);
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onUserInteraction() {

    }

    @Override
    public void showTokens() {

    }

    @Override
    public void setEosBalance(Float balance) {
        mEosBalanceText.setText(balance + Constants.EOS_SYMBOL);
    }
}
