package app.eospocket.android.common;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;

import app.eospocket.android.common.mvp.BasePresenter;
import dagger.android.support.DaggerAppCompatActivity;

public class CommonActivity extends DaggerAppCompatActivity {

    public void finishActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }
}
