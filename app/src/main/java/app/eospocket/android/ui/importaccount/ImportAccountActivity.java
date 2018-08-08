package app.eospocket.android.ui.importaccount;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import app.eospocket.android.R;
import app.eospocket.android.common.CommonActivity;
import app.eospocket.android.utils.PasswordChecker;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImportAccountActivity extends CommonActivity implements ImportAccountView {

    @Inject
    ImportAccountPresenter mImportAccountPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.input_private_key)
    EditText mInputPrivateKey;

    @BindView(R.id.input_account_name)
    EditText mInputAccountName;

    @BindView(R.id.input_password)
    EditText mInputPassword;

    @BindView(R.id.progressBar)
    ProgressBar mPasswordStrengthBar;

    @BindView(R.id.password_strength)
    TextView mPasswordStrengthView;

    @BindView(R.id.btn_import_account)
    Button mImportAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_account);

        ButterKnife.bind(this);

        mImportAccountPresenter.onCreate();

        mInputPrivateKey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mImportAccountPresenter.findAccount(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mInputPassword.addTextChangedListener(new TextWatcher() {
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
                int color = ContextCompat.getColor(ImportAccountActivity.this,
                        R.color.password_strength_weak);
                int progress = 25;

                if (score == PasswordChecker.MEDIUM) {
                    strength = getString(R.string.password_strength_medium);
                    color = ContextCompat.getColor(ImportAccountActivity.this,
                            R.color.password_strength_medium);
                    progress = 50;
                } else if (score == PasswordChecker.STRONG) {
                    strength = getString(R.string.password_strength_strong);
                    color = ContextCompat.getColor(ImportAccountActivity.this,
                            R.color.password_strength_strong);
                    progress = 75;
                } else if (score == PasswordChecker.VERY_STRONG) {
                    strength = getString(R.string.password_strength_very_strong);
                    color = ContextCompat.getColor(ImportAccountActivity.this,
                            R.color.password_strength_very_strong);
                    progress = 100;
                }

                mPasswordStrengthView.setText(strength);
                mPasswordStrengthView.setTextColor(color);

                mPasswordStrengthBar.getProgressDrawable()
                        .setColorFilter(color, android.graphics.PorterDuff.Mode.SRC_IN);
                mPasswordStrengthBar.setProgress(progress);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick(R.id.btn_import_account)
    public void onImportAccountClick() {
        mInputPassword.setEnabled(false);

        String password = mInputPassword.getText().toString();

        int score = PasswordChecker.calculatePasswordStrength(password);

        if (score < PasswordChecker.STRONG) {
            Toast.makeText(ImportAccountActivity.this, getString(R.string.error_password_weak),
                    Toast.LENGTH_SHORT).show();
            mInputPassword.setEnabled(true);
            return;
        }
    }

    @Override
    public void getAccount(String account) {
        mInputAccountName.setText(account);
    }
}
