package app.eospocket.android.ui.action;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import app.eospocket.android.R;
import app.eospocket.android.common.CommonActivity;

public class ActionActivity extends CommonActivity implements ActionView {

    @Inject
    ActionPresenter mActionPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        this.mActionPresenter.onCreate();
    }
}
