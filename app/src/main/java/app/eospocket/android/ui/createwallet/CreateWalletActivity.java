package app.eospocket.android.ui.createwallet;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
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

    private static final int STORAGE_PERMISSION_REQ = 9929;

    @Inject
    CreateWalletPresenter mCreateWalletPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

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

        setTitle(mToolbar, R.string.create_wallet_title);

        mCreateWalletPresenter.onCreate();
    }

    private void checkCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    // todo - explain dialog
                    ActivityCompat.requestPermissions(this,
                            new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE },
                            STORAGE_PERMISSION_REQ);
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE },
                            STORAGE_PERMISSION_REQ);
                }
            } else {

            }
        } else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (checkAllPermissionGranted(grantResults)) {
            if (requestCode == STORAGE_PERMISSION_REQ) {

            }
        } else {
            // todo - explain dialog
        }
    }

    @OnClick(R.id.btn_create_wallet)
    public void onCreateWalletClick() {
        String password = mInputPassword.getText().toString();
        String confirmPassword = mInputConfirmPassword.getText().toString();

        // check password validation

        checkCameraPermission();

        mCreateWalletPresenter.createWallet(password);
    }
}
