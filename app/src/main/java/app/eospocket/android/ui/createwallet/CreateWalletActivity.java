package app.eospocket.android.ui.createwallet;

import android.os.Bundle;

import javax.inject.Inject;

import app.eospocket.android.R;
import app.eospocket.android.common.CommonActivity;

public class CreateWalletActivity extends CommonActivity implements CreateWalletView {

    @Inject
    private CreateWalletPresenter mCreateWalletPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createwallet);
    }
}
