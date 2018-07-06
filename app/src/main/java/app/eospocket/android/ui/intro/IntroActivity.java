package app.eospocket.android.ui.intro;

import android.os.Bundle;
import android.support.annotation.Nullable;

import app.eospocket.android.R;
import app.eospocket.android.common.CommonActivity;

public class IntroActivity extends CommonActivity implements IntroView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
    }
}
