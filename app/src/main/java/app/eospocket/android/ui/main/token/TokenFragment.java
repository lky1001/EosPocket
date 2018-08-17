package app.eospocket.android.ui.main.token;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import app.eospocket.android.R;
import app.eospocket.android.common.CommonFragment;
import app.eospocket.android.common.Constants;
import app.eospocket.android.eos.model.coinmarketcap.CoinMarketCap;
import app.eospocket.android.eos.model.coinmarketcap.CoinQuotes;
import app.eospocket.android.ui.AdapterView;
import app.eospocket.android.ui.importaccount.ImportAccountActivity;
import app.eospocket.android.ui.main.MainNavigationFragment;
import app.eospocket.android.utils.Utils;
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

    @BindView(R.id.usd_balance_text)
    TextView mUsdBalanceText;

    @BindView(R.id.percent_text)
    TextView mPriceChangeRateText;

    @BindView(R.id.account_name_text)
    TextView mAccountNameText;

    @BindView(R.id.nested_scroll_view)
    NestedScrollView mNestedScrollView;

    @BindView(R.id.token_loading_bar)
    ProgressBar mTokenLoadingBar;

    @BindView(R.id.token_listview)
    RecyclerView mTokenListView;

    private LinearLayoutManager mLayoutManager;

    private AdapterView mAdapterView;

    private TokenAdapter mTokenAdapter;

    @Inject
    EosAccountRepository mEosAccountRepository;

    private Disposable mAccountDisposable;

    private Float mAccountEosBalance;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_token, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        subscribeAccounts();

        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mTokenAdapter = new TokenAdapter();
        mTokenListView.setAdapter(mTokenAdapter);
        mTokenListView.setLayoutManager(mLayoutManager);
        mAdapterView = mTokenAdapter;

        mTokenPresenter.setAdapterDataModel(mTokenAdapter);
        mTokenPresenter.onCreate();
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
                        mNestedScrollView.setVisibility(View.VISIBLE);
                        mTokenPresenter.getEosBalance(eosAccountModels.get(0));
                        mTokenPresenter.getTokens(eosAccountModels.get(0).getName());
                        mAccountNameText.setText(eosAccountModels.get(0).getName());
                    } else {
                        mImportAccountButton.setVisibility(View.VISIBLE);
                        mNestedScrollView.setVisibility(View.GONE);
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
        mTokenLoadingBar.setVisibility(View.GONE);
        mTokenListView.setVisibility(View.VISIBLE);
        mAdapterView.refresh();
    }

    @Override
    public void setEosBalance(Float balance) {
        if (isAdded()) {
            mAccountEosBalance = balance;
            mEosBalanceText.setText(balance + " " + Constants.EOS_SYMBOL);
            mTokenPresenter.getMarketPrice(Constants.EOS_COINMARKETCAP_ID);
        }
    }

    @Override
    public void setMarketPrice(CoinMarketCap coinMarketCapData) {
        if (isAdded() && coinMarketCapData != null) {
            CoinQuotes quotes = coinMarketCapData.data.quotes.get("USD");
            double usd = Double.parseDouble(quotes.price);
            mUsdBalanceText.setText("$ " + Utils.formatUsd(usd * mAccountEosBalance) + " USD");
            mPriceChangeRateText.setText(quotes.percentChange24h);

            if (quotes.percentChange24h.startsWith("-")) {
                mPriceChangeRateText.setBackgroundResource(R.drawable.down_percent_bg);
            } else {
                mPriceChangeRateText.setBackgroundResource(R.drawable.up_percent_bg);
            }
        }
    }
}
