package app.eospocket.android.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;

import app.eospocket.android.common.mvp.BasePresenter;
import dagger.android.support.DaggerAppCompatActivity;

public class CommonActivity extends DaggerAppCompatActivity {

    protected void startActivity(Class<? extends Activity> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    public void finishActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }
}
