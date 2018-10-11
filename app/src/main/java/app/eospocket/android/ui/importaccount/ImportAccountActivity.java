package app.eospocket.android.ui.importaccount;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import app.eospocket.android.R;
import app.eospocket.android.common.CommonActivity;
import app.eospocket.android.eos.model.account.EosAccount;
import app.eospocket.android.utils.PasswordChecker;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ImportAccountActivity extends CommonActivity implements ImportAccountView {

    public static final String EXTRA_IMPORT_KEY = "import_key";
    public static final String EXTRA_EXIST_ACCOUNT_NAME = "exist_account_name";

    @Inject
    ImportAccountPresenter mImportAccountPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.private_key_layout)
    View mPrivateKeyLayout;

    @BindView(R.id.account_layout)
    View mAccountLayout;

    @BindView(R.id.account_name_text)
    TextView mAccountNameText;

    @BindView(R.id.balance_text)
    TextView mBalanceText;

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

    @BindView(R.id.reg_later_layout)
    LinearLayout mRegLaterLayout;

    @BindView(R.id.reg_later_checkbox)
    CheckBox mRegLaterCheckBox;

    @BindView(R.id.btn_next)
    Button mNextButton;

    @BindView(R.id.btn_import_account)
    Button mImportAccountButton;

    private EosAccount mEosAccount;
    private String mName;
    private boolean mImportKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_account);

        ButterKnife.bind(this);

        mImportKey = getIntent().getBooleanExtra(EXTRA_IMPORT_KEY, false);

        if (mImportKey) {
            mName = getIntent().getStringExtra(EXTRA_EXIST_ACCOUNT_NAME);

            if (TextUtils.isEmpty(mName)) {
                finishActivity();
            }
        }

        if (mImportKey && !TextUtils.isEmpty(mName)) {
            showProgressDialog(R.string.loading_msg);
            mInputAccountName.setEnabled(false);
            mInputAccountName.setText(mName);
            mImportAccountPresenter.findAccountName(mName);
            mRegLaterLayout.setVisibility(View.GONE);
            mToolbar.setTitle(R.string.title_import_private_key);
        } else {
            mToolbar.setTitle(R.string.title_import_account);
            mRegLaterLayout.setVisibility(View.VISIBLE);
        }

        mImportAccountPresenter.onCreate();

        addDisposable(RxTextView.textChanges(mInputAccountName)
                .debounce(1, TimeUnit.SECONDS)
                .map(CharSequence::toString)
                .subscribe(accountName -> {
                    if (!TextUtils.isEmpty(accountName)) {
                        mImportAccountPresenter.findAccountName(accountName);
                    }
                }));

        addDisposable(RxTextView.textChanges(mInputPassword)
                .debounce(1, TimeUnit.SECONDS)
                .map(input -> {
                    String password = input.toString();

                    if (password.isEmpty()) {

                        return -1;
                    }

                    return PasswordChecker.calculatePasswordStrength(password);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(score -> {
                    if (score < 0) {
                        mPasswordStrengthView.setText("");
                        mPasswordStrengthBar.setProgress(0);
                        return;
                    }

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
                }));

        addDisposable(RxCompoundButton.checkedChanges(mRegLaterCheckBox)
                .subscribe(isChecked -> {
                    mInputPassword.setEnabled(!isChecked);
                })
        );

        addDisposable(RxView.clicks(mNextButton)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(view -> {
                    if (mEosAccount == null) {
                        Toast.makeText(ImportAccountActivity.this, getString(R.string.invalid_account_msg),
                                Toast.LENGTH_SHORT).show();
                    } else {
                        mAccountLayout.setVisibility(View.GONE);
                        mPrivateKeyLayout.setVisibility(View.VISIBLE);
                        mNextButton.setVisibility(View.GONE);
                        mImportAccountButton.setVisibility(View.VISIBLE);
                        mInputPrivateKey.setEnabled(true);
                    }
                }));

        addDisposable(RxView.clicks(mImportAccountButton)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(view -> {
                    if (mRegLaterCheckBox.isChecked() && !mImportKey) {
                        mImportAccountPresenter.importAccount(mEosAccount.accountName);
                    } else {
                        String pk = mInputPrivateKey.getText().toString();

                        if (TextUtils.isEmpty(pk)) {
                            Toast.makeText(ImportAccountActivity.this, getString(R.string.required_private_key_text),
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        mInputPassword.setEnabled(false);

                        String password = mInputPassword.getText().toString();

                        int score = PasswordChecker.calculatePasswordStrength(password);

                        if (score < PasswordChecker.STRONG) {
                            Toast.makeText(ImportAccountActivity.this, getString(R.string.error_password_weak),
                                    Toast.LENGTH_SHORT).show();
                            mInputPassword.setEnabled(true);
                            return;
                        }

                        mImportAccountPresenter.importAccount(mEosAccount.accountName,
                                pk,
                                password);
                    }
                }));
    }

    @Override
    public void noAccount() {
        hideDialog();
        mNextButton.setEnabled(false);
        mEosAccount = null;
        mAccountNameText.setText("-");
        mBalanceText.setText("-");
    }

    @Override
    public void foundAccount(EosAccount result) {
        hideDialog();
        mNextButton.setEnabled(true);
        mEosAccount = result;
        mAccountNameText.setText(mEosAccount.accountName);
        mBalanceText.setText(mEosAccount.coreLiquidBalance);
    }

    @Override
    public void successImport() {
        Toast.makeText(ImportAccountActivity.this, getString(R.string.success_import_account),
                Toast.LENGTH_SHORT).show();
        finishActivity();
    }

    @Override
    public void existAccount() {
        Toast.makeText(ImportAccountActivity.this, getString(R.string.account_name_aleady_exist),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void privateKeyNotMatched() {
        Toast.makeText(ImportAccountActivity.this, getString(R.string.private_key_does_not_match),
                Toast.LENGTH_SHORT).show();
    }
}
