package app.eospocket.android.ui.main.stake;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;

import com.xw.repo.BubbleSeekBar;

import app.eospocket.android.R;
import app.eospocket.android.ui.view.FormatInputEditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StakeDialog extends Dialog {

    @BindView(R.id.edit_to)
    FormatInputEditText editTo;

    @BindView(R.id.cpu_seek_bar)
    BubbleSeekBar cpuStakeSeekBar;
    @BindView(R.id.network_seek_bar)
    BubbleSeekBar netStakeSeekBar;

    @BindView(R.id.btn_confirm)
    View btnConfirm;

    @BindView(R.id.checkbox_transfer)
    CheckBox checkBoxTransfer;

    private StakeDialogCallback stakeDialogCallback;

    private float balance;

    public StakeDialog(Context context, float balance) {
        super(context);
        this.balance = balance;
    }

    private StakeDialog(@NonNull Context context) {
        this(context, R.style.TransparentDialog);
    }

    private StakeDialog(@NonNull Context context, int themeResId) {
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

        cpuStakeSeekBar.getConfigBuilder()
                .max(balance)
                .min(0f)
                .build();
        netStakeSeekBar.getConfigBuilder()
                .max(balance)
                .min(0f)
                .build();

        cpuStakeSeekBar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListenerAdapter() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
                float remainEos = balance - netStakeSeekBar.getProgressFloat();
                if (remainEos < 0f) {
                    remainEos = 0f;
                }
                cpuStakeSeekBar.setProgress(remainEos);
            }
        });

        netStakeSeekBar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListenerAdapter() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
                float remainEos = balance - cpuStakeSeekBar.getProgressFloat();
                if (remainEos < 0f) {
                    remainEos = 0f;
                }
                cpuStakeSeekBar.setProgress(remainEos);
            }
        });
    }

    @OnClick(R.id.btn_confirm)
    public void onClickConfirm() {
//        if (editTo.checkValid() && editCpuStake.checkValid() && editNetStake.checkValid()) {
//            String to = editTo.getInputValue();
//            double cpuAmount = Double.parseDouble(editCpuStake.getInputValue());
//            double netAmout = Double.parseDouble(editNetStake.getInputValue());
//            boolean isTransfer = checkBoxTransfer.isChecked();
//
//            stakeDialogCallback.onConfirm(to, cpuAmount, netAmout, isTransfer);
//            dismiss();
//        }
    }

    @OnClick(R.id.btn_cancel)
    public void onClickCancel() {
        dismiss();
    }

    interface StakeDialogCallback {
        void onConfirm(String to, double cpuStake, double netStake, boolean isTransfer);
    }
}
