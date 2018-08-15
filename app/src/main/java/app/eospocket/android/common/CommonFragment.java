package app.eospocket.android.common;

import android.app.Activity;
import android.content.Intent;

import dagger.android.support.DaggerFragment;

public class CommonFragment extends DaggerFragment {

    protected void startActivity(Class<? extends Activity> activity) {
        Intent intent = new Intent(getActivity(), activity);
        startActivity(intent);
    }
}
