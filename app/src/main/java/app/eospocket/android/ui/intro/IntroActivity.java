package app.eospocket.android.ui.intro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import javax.inject.Inject;

import app.eospocket.android.R;
import app.eospocket.android.common.CommonActivity;
import app.eospocket.android.ui.createwallet.CreateWalletActivity;
import app.eospocket.android.ui.main.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroActivity extends CommonActivity implements IntroView {

    @Inject
    IntroPresenter mIntroPresenter;

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
}
