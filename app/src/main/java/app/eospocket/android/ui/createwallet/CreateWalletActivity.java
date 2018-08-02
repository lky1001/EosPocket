package app.eospocket.android.ui.createwallet;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import javax.inject.Inject;

import app.eospocket.android.R;
import app.eospocket.android.common.CommonActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateWalletActivity extends CommonActivity implements CreateWalletView {

    @Inject
    private CreateWalletPresenter mCreateWalletPresenter;

    @BindView(R.id.input_password)
    EditText mInputPassword;

    @BindView(R.id.input_confirm_password)
    EditText mInputConfirmPassword;

    @BindView(R.id.agree_lost_password)
    CheckBox mAgreeLostPasswordChkBox;

    @BindView(R.id.btn_create_wallet)
    Button mCreateWalletButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createwallet);

        ButterKnife.bind(this);

        mCreateWalletPresenter.onCreate();
    }

    @OnClick(R.id.btn_create_wallet)
    public void onCreateWalletClick() {

    }
}
