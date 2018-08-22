package app.eospocket.android.ui.main.stake.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import app.eospocket.android.R;
import app.eospocket.android.eos.model.account.EosAccount;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StakeResourceViewHolder extends StakeViewHolder {

    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_desc)
    TextView txtDesc;
    @BindView(R.id.progress)
    View progress;
    @BindView(R.id.txt_percentage)
    TextView txtPercentage;


    public StakeResourceViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(EosAccount eosAccount) {

    }
}
