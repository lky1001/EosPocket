package app.eospocket.android.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import javax.inject.Inject;

import app.eospocket.android.R;
import app.eospocket.android.common.CommonActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.Binds;

public class LoginActivity extends CommonActivity implements LoginView {

    @Inject
    LoginPresenter mLoginPresenter;

    @BindView(R.id.input_password)
    EditText mInputPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mLoginPresenter.onCreate();
    }

    @OnClick(R.id.btn_login)
    public void onLoginClick() {
        mLoginPresenter.doLogin(mInputPassword.getText().toString());
    }

    @Override
    public void successLogin() {
        
    }

    @Override
    public void failLogin() {

    }
}
