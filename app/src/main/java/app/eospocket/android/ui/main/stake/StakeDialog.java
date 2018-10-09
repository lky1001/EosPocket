package app.eospocket.android.ui.main.stake;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;

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
        setCanceledOnTouchOutside(false);
    }

    @OnClick(R.id.btn_confirm)
    public void onClickConfirm() {
        if (editTo.checkValid() && editCpuStake.checkValid() && editNetStake.checkValid()) {
            String to = editTo.getInputValue();
            double cpuAmount = Double.parseDouble(editCpuStake.getInputValue());
            double netAmout = Double.parseDouble(editNetStake.getInputValue());
            boolean isTransfer = checkBoxTransfer.isChecked();

            stakeDialogCallback.onConfirm(to, cpuAmount, netAmout, isTransfer);
            dismiss();
        }
    }

    @OnClick(R.id.btn_cancel)
    public void onClickCancel() {
        dismiss();
    }

    interface StakeDialogCallback {
        void onConfirm(String to, double cpuStake, double netStake, boolean isTransfer);
    }
}
