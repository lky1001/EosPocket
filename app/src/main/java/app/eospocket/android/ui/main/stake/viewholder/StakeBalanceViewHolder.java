package app.eospocket.android.ui.main.stake.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import app.eospocket.android.R;
import app.eospocket.android.eos.model.account.EosAccount;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StakeBalanceViewHolder extends StakeViewHolder {

    @BindView(R.id.txt_balance_title)
    TextView txtBalanceTitle;
    @BindView(R.id.txt_balance_eos)
    TextView txtBalanceEos;
    @BindView(R.id.btn_stake)
    TextView btnStake;

    @BindView(R.id.txt_stake_title)
    TextView txtStakeTitle;
    @BindView(R.id.txt_stake_eos)
    TextView txtStakeEos;
    @BindView(R.id.btn_unstake)
    TextView btnUnStake;

    public StakeBalanceViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(EosAccount eosAccount) {

    }
}
