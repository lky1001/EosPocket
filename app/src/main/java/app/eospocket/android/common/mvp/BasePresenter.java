package app.eospocket.android.common.mvp;

import app.eospocket.android.ui.AdapterDataModel;

public abstract class BasePresenter<T extends IView> {

    protected T mView;
    protected AdapterDataModel mAdapterDataModel;

    public BasePresenter(T view) {
        this.mView = view;
    }

    public abstract void onCreate();
    public abstract void onPause();
    public abstract void onResume();
    public abstract void onDestroy();

    public void setAdapterDataModel(AdapterDataModel adapterDataModel) {
        this.mAdapterDataModel = adapterDataModel;
    }
}