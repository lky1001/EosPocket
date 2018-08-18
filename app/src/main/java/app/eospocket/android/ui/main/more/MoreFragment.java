package app.eospocket.android.ui.main.more;

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

public class MoreFragment extends CommonFragment implements MainNavigationFragment, MoreView {

    @Inject
    MorePresenter mMorePresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.bind(this, view);

        mMorePresenter.onCreate();

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
