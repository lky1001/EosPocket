package app.eospocket.android.ui.intro;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import javax.inject.Inject;

import app.eospocket.android.R;
import app.eospocket.android.common.CommonActivity;
import app.eospocket.android.ui.main.MainActivity;

public class IntroActivity extends CommonActivity implements IntroView {

    private static final int STORAGE_PERMISSION_REQ = 9929;

    @Inject
    IntroPresenter mIntroPresenter;

    private boolean mIsBackClick = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        checkCameraPermission();

        mIntroPresenter.onCreate();
    }

    private void checkCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    // todo - explain dialog
                    ActivityCompat.requestPermissions(this,
                            new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                            STORAGE_PERMISSION_REQ);
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                            STORAGE_PERMISSION_REQ);
                }
            } else {
                mIntroPresenter.checkWalletExist();
            }
        } else {
            mIntroPresenter.checkWalletExist();
        }
    }

    @Override
    public void onBackPressed() {
        mIsBackClick = true;

        super.onBackPressed();
    }

    @Override
    public void startLoginActivity() {
        if (!mIsBackClick && !isFinishing()) {
        }
    }

    @Override
    public void startCreateWalletActivity() {
        if (!mIsBackClick && !isFinishing()) {
        }
    }
}
