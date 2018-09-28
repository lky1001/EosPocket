package app.eospocket.android.ui.main.stake;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.EditText;

import app.eospocket.android.R;
import app.eospocket.android.ui.view.DoubleEditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StakeDialog extends Dialog {

    @BindView(R.id.edit_to)
    EditText editTo;

    @BindView(R.id.edit_cpu_stake)
    DoubleEditText editCpuStake;
    @BindView(R.id.edit_net_stake)
    DoubleEditText editNetStake;

    @BindView(R.id.checkbox_transfer)
    CheckBox checkBoxTransfer;

    private StakeDialogCallback stakeDialogCallback;

    public StakeDialog(@NonNull Context context) {
        this(context, R.style.TransparentDialog);
    }

    public StakeDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public void setStakeDialogCallback(StakeDialogCallback stakeDialogCallback) {
        this.stakeDialogCallback = stakeDialogCallback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_stake);

        ButterKnife.bind(this);
        setCancelable(true);
    }

    @OnClick(R.id.btn_confirm)
    public void onClickConfirm() {
        String to  = editTo.getText().toString();
        double cpuAmount = editCpuStake.getDoubleValue();
        double netAmout = editNetStake.getDoubleValue();
        boolean isTransfer = checkBoxTransfer.isChecked();

        if (cpuAmount == DoubleEditText.INVAILD_VALUE || netAmout == DoubleEditText.INVAILD_VALUE) {
            return;
        }

        stakeDialogCallback.onConfirm(to, cpuAmount, netAmout, isTransfer);
        dismiss();
    }

    @OnClick(R.id.btn_cancel)
    public void onClickCancel() {
        dismiss();
    }

    interface StakeDialogCallback {
        void onConfirm(String to, double cpuStake, double netStake, boolean isTransfer);
    }
}
