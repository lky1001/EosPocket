package app.eospocket.android.common;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;

import com.afollestad.materialdialogs.MaterialDialog;

import app.eospocket.android.R;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;

public class CommonActivity extends DaggerAppCompatActivity {

    protected MaterialDialog mMaterialDialog;
    private CompositeDisposable mAllDisposables = new CompositeDisposable();

    protected boolean checkAllPermissionGranted(@NonNull int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finishActivity();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

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

    protected void addDisposable(Disposable disposable) {
        mAllDisposables.add(disposable);
    }

    @Override
    protected void onDestroy() {
        mAllDisposables.clear();
        hideDialog();
        super.onDestroy();
    }

    protected void hideDialog() {
        if (mMaterialDialog != null) {
            mMaterialDialog.dismiss();
            mMaterialDialog = null;
        }
    }

    public void showProgressDialog(@NonNull int msgResId) {
        showProgressDialog(null, getString(msgResId));
    }

    public void showProgressDialog(@NonNull String msg) {
        showProgressDialog(null, msg);
    }

    public void showProgressDialog(@NonNull int titleResId, @NonNull int msgResId) {
        showProgressDialog(getString(titleResId), getString(msgResId));
    }

    public void showProgressDialog(@Nullable String title, @NonNull String msg) {
        hideDialog();

        MaterialDialog.Builder builder = new MaterialDialog.Builder(this);
        if (!TextUtils.isEmpty(title)) {
            builder.title(title);
        }

        mMaterialDialog = builder
                .titleColorRes(R.color.colorAccent)
                .contentColorRes(R.color.colorAccent)
                .backgroundColorRes(android.R.color.white)
                .content(msg)
                .progress(true,0)
                .canceledOnTouchOutside(false)
                .show();
    }

    protected void setTitle(Toolbar toolbar, int titleRes) {
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(titleRes);
        }
    }
}
