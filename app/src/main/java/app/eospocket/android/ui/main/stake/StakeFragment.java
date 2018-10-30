package app.eospocket.android.ui.main.stake;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import app.eospocket.android.R;
import app.eospocket.android.common.CommonFragment;
import app.eospocket.android.eos.model.account.EosAccount;
import app.eospocket.android.eos.model.account.Refund;
import app.eospocket.android.ui.main.MainNavigationFragment;
import app.eospocket.android.utils.Utils;
import app.eospocket.android.wallet.LoginAccountManager;
import app.eospocket.android.wallet.repository.EosAccountRepository;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class StakeFragment extends CommonFragment
        implements MainNavigationFragment, StakeView, SwipeRefreshLayout.OnRefreshListener {

    private static final long REFUND_AVAILABLE_DAY = 1000 * 60 * 60 * 24 * 3;
    private static final String REFUND_REQUEST_TIME_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    @Inject
    StakePresenter mStakePresenter;

    @Inject
    LoginAccountManager mLoginAccountManager;

    @Inject
    EosAccountRepository mEosAccountRepository;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.txt_stake_balance_eos)
    TextView txtBalanceEos;
    @BindView(R.id.txt_unstake_eos)
    TextView txtStakedEos;

    @BindView(R.id.layout_cpu_stake)
    View mParentCpuStake;
    TextView mTxtCpuTitle;
    TextView mCpuDesc;
    TextView mTxtCpuPercent;
    RoundCornerProgressBar mCpuProgress;

    @BindView(R.id.layout_network_stake)
    View mParentNetworkStake;
    TextView mTxtNetworkTitle;
    TextView mNetworkDesc;
    TextView mTxtNetworkPercent;
    RoundCornerProgressBar mNetworkProgress;

    @BindView(R.id.layout_ram_stake)
    View mParentRamStake;
    TextView mRamDesc;
    TextView mTxtRamPercent;
    RoundCornerProgressBar mRamProgress;

    @BindView(R.id.layout_refund)
    View mParentRefund;
    TextView mRefundDesc;
    TextView mRefundPercent;
    RoundCornerProgressBar mRefundProgress;
    Button mRefundButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stake, container, false);
        ButterKnife.bind(this, view);

        initUi();


        mStakePresenter.onCreate();
        return view;
    }

    private void initUi() {
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mTxtCpuTitle = mParentCpuStake.findViewById(R.id.txt_title);
        mCpuDesc = mParentCpuStake.findViewById(R.id.txt_desc);
        mTxtCpuPercent = mParentCpuStake.findViewById(R.id.txt_percentage);
        mCpuProgress = mParentCpuStake.findViewById(R.id.progress);
        mCpuProgress.setProgressColor(getResources().getColor(R.color.stake_resource_cpu_color));

        mTxtNetworkTitle = mParentNetworkStake.findViewById(R.id.txt_title);
        mNetworkDesc = mParentNetworkStake.findViewById(R.id.txt_desc);
        mTxtNetworkPercent = mParentNetworkStake.findViewById(R.id.txt_percentage);
        mNetworkProgress = mParentNetworkStake.findViewById(R.id.progress);
        mNetworkProgress.setProgressColor(getResources().getColor(R.color.stake_resource_network_color));

        ((TextView) mParentRamStake.findViewById(R.id.txt_title)).setText("RAM");
        mRamDesc = mParentRamStake.findViewById(R.id.txt_desc);
        mTxtRamPercent = mParentRamStake.findViewById(R.id.txt_percentage);
        mRamProgress = mParentRamStake.findViewById(R.id.progress);
        mRamProgress.setProgressColor(getResources().getColor(R.color.stake_resource_ram_color));

        ((TextView) mParentRefund.findViewById(R.id.txt_title)).setText("Refund");
        mRefundDesc = mParentRefund.findViewById(R.id.txt_desc);
        mRefundPercent = mParentRefund.findViewById(R.id.txt_percentage);
        mRefundProgress = mParentRefund.findViewById(R.id.progress);
        mRefundButton = mParentRefund.findViewById(R.id.feature_button);
        mRefundButton.setVisibility(View.VISIBLE);
        mRefundButton.setText("REQUEST");
        mRefundProgress.setProgressColor(getResources().getColor(R.color.stake_resource_refunding_color));
    }

    @Override
    public void onRefresh() {
        int accountId = mLoginAccountManager.getSelectedId();
        mEosAccountRepository
                .findOneById(accountId)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        eosAccountModel -> mStakePresenter.loadEosAccount(eosAccountModel.getName()),
                        t -> {
                        }
                );
    }

    @Override
    public void onUserInteraction() {

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    private float availableStakeEos;    //스태이크 가능한 이오스(순수 밸런스)
//    private float totalStakedEos;
    private float stakedCputEos;
    private float stakedNetEos;

    @Override
    public void loadEosAccountSuccess(EosAccount eosAccount) {
        String cpuWeight = eosAccount.totalResources.cpuWeight;
        String netWeight = eosAccount.totalResources.netWeight;

        txtBalanceEos.setText(eosAccount.coreLiquidBalance);
        availableStakeEos = Float.parseFloat(eosAccount.coreLiquidBalance.split(" ")[0]);

        stakedCputEos = Float.parseFloat(eosAccount.totalResources.cpuWeight.split(" ")[0]);
        stakedNetEos = Float.parseFloat(eosAccount.totalResources.netWeight.split(" ")[0]);
        float totalStakedEos = stakedCputEos + stakedNetEos;

        txtStakedEos.setText(Utils.formatBalanceWithEOSSymbol(totalStakedEos));

        mTxtCpuTitle.setText("CPU " + cpuWeight);
        mTxtNetworkTitle.setText("Network " + netWeight);

        mCpuDesc.setText(getStakeResouceDesc(eosAccount.cpuLimit.used, eosAccount.cpuLimit.max, "ms", R.color.stake_resource_cpu_color));
        mNetworkDesc.setText(getStakeResouceDesc(eosAccount.netLimit.used, eosAccount.netLimit.max, "bytes", R.color.stake_resource_network_color));
        mRamDesc.setText(getStakeResouceDesc(eosAccount.ramUsage, eosAccount.ramQuota, "KiB", R.color.stake_resource_ram_color));

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        double percent = eosAccount.cpuLimit.used / (double) eosAccount.cpuLimit.max * 100;
        mTxtCpuPercent.setText(decimalFormat.format(percent) + "%");
        mCpuProgress.setProgress((float) percent);

        percent = eosAccount.netLimit.used / (double) eosAccount.netLimit.max * 100;
        mTxtNetworkPercent.setText(decimalFormat.format(percent) + "%");
        mNetworkProgress.setProgress((float) percent);

        percent = eosAccount.ramUsage / (double) eosAccount.ramQuota * 100;
        mTxtRamPercent.setText(decimalFormat.format(percent) + "%");
        mRamProgress.setProgress((float) percent);

        //Bind refund resource
        Refund refund = eosAccount.refundRequest;
        float refundUsed = 0f;
        if (refund != null) {
            float cpuUsedAmount = Float.parseFloat(refund.cpu_amount.split(" ")[0]);
            float netUsedAmount = Float.parseFloat(refund.net_amount.split(" ")[0]);
            refundUsed = cpuUsedAmount + netUsedAmount;

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat(REFUND_REQUEST_TIME_DATE_FORMAT);
                Date requestDate = dateFormat.parse(refund.request_time);
                mRefundButton.setEnabled(requestDate.getTime() + REFUND_AVAILABLE_DAY
                        < System.currentTimeMillis());
            } catch (ParseException e) {
                mRefundButton.setEnabled(false);
                e.printStackTrace();
            }

        } else {
            mRefundButton.setEnabled(false);
        }

        if (totalStakedEos > 0) {
            percent = refundUsed / (double) totalStakedEos * 100;
        } else {
            percent = 0d;
        }
        String strRefundUsed = Utils.formatBalanceWithEOSSymbol(refundUsed);
        String strRefundMax = Utils.formatBalanceWithEOSSymbol(totalStakedEos);
        SpannableString resultStr = new SpannableString(strRefundUsed + " / " + strRefundMax);
        resultStr.setSpan(
                new ForegroundColorSpan(getContext().getResources().getColor(R.color.stake_resource_refunding_color)),
                0,
                strRefundUsed.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        mRefundDesc.setText(resultStr);
        mRefundPercent.setText(decimalFormat.format(percent) + "%");
        mRefundProgress.setProgress((float) percent);

        mSwipeRefreshLayout.setRefreshing(false);
    }

    private Spannable getStakeResouceDesc(long used, long max, String unit, int strColor) {
        String strUsed = used + " " + unit;
        String strMax = max + " " + unit;

        SpannableString resultStr = new SpannableString(strUsed + " / " + strMax);
        resultStr.setSpan(
                new ForegroundColorSpan(getContext().getResources().getColor(strColor)),
                0,
                strUsed.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        return resultStr;
    }

    @Override
    public void loadEosAccountFail(Throwable t) {
        Toast.makeText(getContext(), "loadEosAccountFail", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_stake)
    public void onClickStake() {
        StakeDialog dialog = new StakeDialog(getContext(), availableStakeEos);
        dialog.setStakeDialogCallback(new StakeDialog.DialogCallback() {
            @Override
            public void onConfirm(String to, double cpuStake, double netStake, boolean isTransfer) {

                int accountId = mLoginAccountManager.getSelectedId();
                String pw = "1234567890abcd!@#";
                // send eos to account
                mStakePresenter.stakeEos(accountId, pw, to, cpuStake, netStake, isTransfer ? 1 : 0);

            }
        });
        dialog.show();
    }

    @OnClick(R.id.btn_unstake)
    public void onClickUnStake() {
        int selectedId = mLoginAccountManager.getSelectedId();
        mEosAccountRepository.findOneById(selectedId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(eosAccountModel -> {
                    UnStakeDialog dialog = new UnStakeDialog(getContext(), eosAccountModel.getName(),
                            stakedCputEos, stakedNetEos);
                    dialog.setStakeDialogCallback(new UnStakeDialog.DialogCallback() {
                        @Override
                        public void onConfirm(String to, double unStakeCpu, double unStakeNet) {
                            int accountId = mLoginAccountManager.getSelectedId();
                            String pw = "1234567890abcd!@#";
                            mStakePresenter.unStakeEos(accountId, pw, to, unStakeCpu, unStakeNet);
                        }
                    });
                    dialog.show();
                });
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
