package app.eospocket.android.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import app.eospocket.android.R;
import app.eospocket.android.common.CommonActivity;
import butterknife.ButterKnife;

public class LoginActivity extends CommonActivity implements LoginView {

    @Inject
    LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        ButterKnife.bind(this);

        mLoginPresenter.onCreate();
    }
}
