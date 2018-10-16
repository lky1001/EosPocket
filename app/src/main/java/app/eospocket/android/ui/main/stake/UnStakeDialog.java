package app.eospocket.android.ui.main.stake;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import javax.inject.Inject;

import app.eospocket.android.R;
import app.eospocket.android.ui.view.FormatInputEditText;
import app.eospocket.android.wallet.LoginAccountManager;
import app.eospocket.android.wallet.repository.EosAccountRepository;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UnStakeDialog extends Dialog {

    @BindView(R.id.edit_to)
    FormatInputEditText editTo;

    @BindView(R.id.edit_cpu_unstake)
    FormatInputEditText editCpuUnStake;

    @BindView(R.id.edit_net_unstake)
    FormatInputEditText editNetUnStake;

    private UnStakeDialog.DialogCallback stakeDialogCallback;
    private float maxCpuUnstake;
    private float maxNetUnstake;
    private String defaultAccount;

    public UnStakeDialog(@NonNull Context context, @Nullable String defaultAccount, float maxCpuUnstake, float maxNetUnstake) {
        this(context, R.style.TransparentDialog);
        this.defaultAccount = defaultAccount;
        this.maxCpuUnstake = maxCpuUnstake;
        this.maxNetUnstake = maxNetUnstake;
    }

    private UnStakeDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public void setStakeDialogCallback(UnStakeDialog.DialogCallback stakeDialogCallback) {
        this.stakeDialogCallback = stakeDialogCallback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_unstake);

        ButterKnife.bind(this);
        initUi();
    }

    private void initUi() {
        editTo.setText(defaultAccount);
        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }

    @OnClick(R.id.btn_confirm)
    public void onClickConfirm() {
        if (editTo.checkValid() && editCpuUnStake.checkValid() && editNetUnStake.checkValid()) {
            String to = editTo.getInputValue();
            float unStakeCpu = Float.parseFloat(editCpuUnStake.getInputValue());
            float unStakeNet = Float.parseFloat(editNetUnStake.getInputValue());

            if (unStakeCpu > maxCpuUnstake) {
                Toast.makeText(getContext(), "unStakeCpu > Staking Cpu value", Toast.LENGTH_SHORT).show();
                return;
            }

            if (unStakeNet > maxNetUnstake) {
                Toast.makeText(getContext(), "unStakeNet > Staking Network value", Toast.LENGTH_SHORT).show();
                return;
            }

            stakeDialogCallback.onConfirm(to, unStakeCpu, unStakeNet);
            dismiss();
        }
    }

    @OnClick(R.id.btn_cancel)
    public void onClickCancel() {
        dismiss();
    }

    interface DialogCallback {
        void onConfirm(String to, double unStakeCpu, double unStakeNet);
    }
}
