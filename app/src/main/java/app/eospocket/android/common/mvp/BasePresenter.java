package app.eospocket.android.common.mvp;

import android.content.Context;
import android.support.v4.app.Fragment;

public abstract class BasePresenter<T extends IView> {

    protected T mView;

    public BasePresenter(T view) {
        this.mView = view;
    }

    public abstract void onCreate();
    public abstract void onPause();
    public abstract void onResume();
    public abstract void onDestroy();

}