package app.eospocket.android.ui.main.action;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import app.eospocket.android.R;
import app.eospocket.android.common.CommonFragment;
import app.eospocket.android.ui.main.MainNavigationFragment;
import butterknife.ButterKnife;

public class ActionFragment extends CommonFragment implements MainNavigationFragment, ActionView {

    @Inject
    ActionPresenter mActionPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_action, container, false);
        ButterKnife.bind(this, view);

        mActionPresenter.onCreate();

        return view;
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onUserInteraction() {

    }
}
