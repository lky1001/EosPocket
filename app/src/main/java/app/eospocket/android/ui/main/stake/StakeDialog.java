package app.eospocket.android.ui.main.stake;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import app.eospocket.android.R;
import app.eospocket.android.ui.view.FormatInputEditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StakeDialog extends Dialog {

    @BindView(R.id.edit_to)
    FormatInputEditText editTo;

    @BindView(R.id.edit_cpu_stake)
    FormatInputEditText editCpuStake;

    @BindView(R.id.edit_net_stake)
    FormatInputEditText editNetStake;

    @BindView(R.id.btn_confirm)
    View btnConfirm;

    @BindView(R.id.checkbox_transfer)
    CheckBox checkBoxTransfer;

    private StakeDialog.DialogCallback stakeDialogCallback;
    private float maxValue;

    public StakeDialog(@NonNull Context context, float maxValue) {
        this(context, R.style.TransparentDialog);
        this.maxValue = maxValue;
    }

    private StakeDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public void setStakeDialogCallback(StakeDialog.DialogCallback stakeDialogCallback) {
        this.stakeDialogCallback = stakeDialogCallback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_stake);

        ButterKnife.bind(this);
        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }

    @OnClick(R.id.btn_confirm)
    public void onClickConfirm() {
        if (editTo.checkValid() && editCpuStake.checkValid() && editNetStake.checkValid()) {
            String to = editTo.getInputValue();
            float cpuAmount = Float.parseFloat(editCpuStake.getInputValue());
            float netAmout = Float.parseFloat(editNetStake.getInputValue());
            boolean isTransfer = checkBoxTransfer.isChecked();

            if (cpuAmount + netAmout > maxValue) {
                Toast.makeText(getContext(), "cputAmount + netAmount > BALANCE", Toast.LENGTH_SHORT).show();
                return;
            }

            stakeDialogCallback.onConfirm(to, cpuAmount, netAmout, isTransfer);
            dismiss();
        }
    }

    @OnClick(R.id.btn_cancel)
    public void onClickCancel() {
        dismiss();
    }

    interface DialogCallback {
        void onConfirm(String to, double cpuStake, double netStake, boolean isTransfer);
    }
}
