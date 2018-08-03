package app.eospocket.android.ui.createwallet;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import app.eospocket.android.R;
import app.eospocket.android.common.CommonActivity;
import app.eospocket.android.ui.main.MainActivity;
import app.eospocket.android.utils.PasswordChecker;
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

    @BindView(R.id.agree_lost_password)
    CheckBox mAgreeLostPasswordChkBox;

    @BindView(R.id.progressBar)
    ProgressBar mPasswordStrengthBar;

    @BindView(R.id.password_strength)
    TextView mPasswordStrengthView;

    @BindView(R.id.btn_create_wallet)
    Button mCreateWalletButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createwallet);

        ButterKnife.bind(this);

        setTitle(mToolbar, R.string.create_wallet_title);

        mInputPassword.addTextChangedListener(mPasswordWatcher);

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
                createWallet();
            }
        } else {
            createWallet();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (checkAllPermissionGranted(grantResults)) {
            if (requestCode == STORAGE_PERMISSION_REQ) {
                createWallet();
            }
        } else {
            // todo - explain dialog
        }
    }

    private TextWatcher mPasswordWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String password = s.toString();

            if (TextView.VISIBLE != mPasswordStrengthView.getVisibility()) {
                return;
            }

            if (password.isEmpty()) {
                mPasswordStrengthView.setText("");
                mPasswordStrengthBar.setProgress(0);
                return;
            }

            int score = PasswordChecker.calculatePasswordStrength(password);
            String strength = getString(R.string.password_strength_weak);
            int color = ContextCompat.getColor(CreateWalletActivity.this, R.color.password_strength_weak);
            int progress = 25;

            if (score == PasswordChecker.MEDIUM) {
                strength = getString(R.string.password_strength_medium);
                color = ContextCompat.getColor(CreateWalletActivity.this, R.color.password_strength_medium);
                progress = 50;
            } else if (score == PasswordChecker.STRONG) {
                strength = getString(R.string.password_strength_strong);
                color = ContextCompat.getColor(CreateWalletActivity.this, R.color.password_strength_strong);
                progress = 75;
            } else if (score == PasswordChecker.VERY_STRONG) {
                strength = getString(R.string.password_strength_very_strong);
                color = ContextCompat.getColor(CreateWalletActivity.this, R.color.password_strength_very_strong);
                progress = 100;
            }

            mPasswordStrengthView.setText(strength);
            mPasswordStrengthView.setTextColor(color);

            mPasswordStrengthBar.getProgressDrawable().setColorFilter(color, android.graphics.PorterDuff.Mode.SRC_IN);
            mPasswordStrengthBar.setProgress(progress);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @OnClick(R.id.btn_create_wallet)
    public void onCreateWalletClick() {
        mInputPassword.setEnabled(false);

        String password = mInputPassword.getText().toString();

        int score = PasswordChecker.calculatePasswordStrength(password);

        if (score < PasswordChecker.STRONG) {
            Toast.makeText(CreateWalletActivity.this, getString(R.string.error_password_weak), Toast.LENGTH_SHORT).show();
            mInputPassword.setEnabled(true);
            return;
        }

        boolean isAgree = mAgreeLostPasswordChkBox.isChecked();

        if (!isAgree) {
            Toast.makeText(CreateWalletActivity.this, getString(R.string.need_all_agree), Toast.LENGTH_SHORT).show();
            mInputPassword.setEnabled(true);
            return;
        }

        checkCameraPermission();
    }

    private void createWallet() {
        showProgressDialog(getString(R.string.loading_msg));
        String password = mInputPassword.getText().toString();

        mCreateWalletPresenter.createWallet(password);
    }

    @Override
    public void successCreateWallet() {
        if (isFinishing()) {
            return;
        }
        hideDialog();

        startActivity(MainActivity.class);
        finishActivity();
    }

    @Override
    public void existWallet() {
        if (isFinishing()) {
            return;
        }
        hideDialog();

        Toast.makeText(CreateWalletActivity.this, getString(R.string.exist_wallet_error), Toast.LENGTH_SHORT).show();
        mInputPassword.setEnabled(true);
    }

    @Override
    public void failCreateWallet() {
        if (isFinishing()) {
            return;
        }
        hideDialog();

        Toast.makeText(CreateWalletActivity.this, getString(R.string.fail_create_wallet), Toast.LENGTH_SHORT).show();
        mInputPassword.setEnabled(true);
    }
}
