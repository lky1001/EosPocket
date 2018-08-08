package app.eospocket.android.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import app.eospocket.android.R;
import app.eospocket.android.common.CommonActivity;
import app.eospocket.android.ui.importaccount.ImportAccountActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends CommonActivity implements MainView {

    @Inject
    MainPresenter mMainPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mMainPresenter.onCreate();
    }

    @OnClick(R.id.btn_import_account)
    public void onImportAccountClick() {
        startActivity(ImportAccountActivity.class);
    }
}
