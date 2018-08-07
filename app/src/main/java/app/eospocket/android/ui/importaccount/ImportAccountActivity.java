package app.eospocket.android.ui.importaccount;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import app.eospocket.android.R;
import app.eospocket.android.common.CommonActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ImportAccountActivity extends CommonActivity implements ImportAccountView {

    @Inject
    ImportAccountPresenter mImportAccountPresenter;

    @BindView(R.id.input_private_key)
    EditText mInputPrivateKey;

    @BindView(R.id.input_account_name)
    EditText mInputAccountName;

    @BindView(R.id.input_password)
    EditText mInputPassword;

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
    }
}
