package app.eospocket.android.ui.intro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.beautycoder.pflockscreen.PFFLockScreenConfiguration;
import com.beautycoder.pflockscreen.fragments.PFLockScreenFragment;

import javax.inject.Inject;

import app.eospocket.android.R;
import app.eospocket.android.common.CommonActivity;
import app.eospocket.android.common.Constants;
import app.eospocket.android.common.CustomPreference;
import app.eospocket.android.ui.createwallet.CreateWalletActivity;
import app.eospocket.android.ui.main.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroActivity extends CommonActivity implements IntroView {

    @Inject
    IntroPresenter mIntroPresenter;

    @Inject
    CustomPreference mCustomPreference;

    @BindView(R.id.intro_image)
    ImageView mIntroImage;

    private boolean mIsBackClick = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        ButterKnife.bind(this);

        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.intro);
        mIntroImage.startAnimation(anim);

        mIntroPresenter.onCreate();
        mIntroPresenter.initWallet();
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
            startActivity(CreateWalletActivity.class);
            finishActivity();
        }
    }

    @Override
    public void startMainActivity() {
        if (!mIsBackClick && !isFinishing()) {
            startActivity(MainActivity.class);
            finishActivity();
        }
    }

    @Override
    public void initPinCode() {
        if (!mIsBackClick && !isFinishing()) {
            showLockFragment(getString(R.string.init_lock_title), false);
        }
    }

    @Override
    public void login() {
        if (!mIsBackClick && !isFinishing()) {
            showLockFragment(getString(R.string.unlock_title), true);
        }
    }

    private void showLockFragment(String title, boolean isCreated) {
        PFFLockScreenConfiguration.Builder builder = new PFFLockScreenConfiguration.Builder(this)
                .setTitle(title)
                .setCodeLength(Constants.PIN_CODE_LENGTH)
                .setUseFingerprint(true);

        PFLockScreenFragment fragment = new PFLockScreenFragment();

        builder.setMode(isCreated
                ? PFFLockScreenConfiguration.MODE_AUTH
                : PFFLockScreenConfiguration.MODE_CREATE);
        if (isCreated) {
            fragment.setEncodedPinCode(mIntroPresenter.getPinCode());
            fragment.setLoginListener(mLoginListener);
        }

        mIntroImage.setVisibility(View.GONE);

        fragment.setConfiguration(builder.build());
        fragment.setCodeCreateListener(mCodeCreateListener);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_view, fragment).commit();
    }

    private PFLockScreenFragment.OnPFLockScreenCodeCreateListener mCodeCreateListener =
            new PFLockScreenFragment.OnPFLockScreenCodeCreateListener() {
                @Override
                public void onCodeCreated(String encodedCode) {
                    mIntroPresenter.createWallet(true, encodedCode);
                    startMainActivity();
                }
            };

    private PFLockScreenFragment.OnPFLockScreenLoginListener mLoginListener =
            new PFLockScreenFragment.OnPFLockScreenLoginListener() {

                @Override
                public void onCodeInputSuccessful() {
                    startMainActivity();
                }

                @Override
                public void onFingerprintSuccessful() {
                    startMainActivity();
                }

                @Override
                public void onPinLoginFailed() {
                    Toast.makeText(IntroActivity.this, getString(R.string.invalid_pin_code), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFingerprintLoginFailed() {
                    Toast.makeText(IntroActivity.this, getString(R.string.invalid_fingerprint), Toast.LENGTH_SHORT).show();
                }
            };
}
