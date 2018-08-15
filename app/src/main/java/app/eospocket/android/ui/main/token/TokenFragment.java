package app.eospocket.android.ui.main.token;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import app.eospocket.android.R;
import app.eospocket.android.ui.main.MainNavigationFragment;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

public class TokenFragment extends DaggerFragment implements MainNavigationFragment, TokenView {

    @Inject
    TokenPresenter mTokenPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_token, container, false);
        ButterKnife.bind(this, view);

        mTokenPresenter.onCreate();

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
