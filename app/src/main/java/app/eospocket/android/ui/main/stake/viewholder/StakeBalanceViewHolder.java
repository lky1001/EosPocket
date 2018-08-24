package app.eospocket.android.ui.main.stake.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import app.eospocket.android.R;
import app.eospocket.android.eos.model.account.EosAccount;
import app.eospocket.android.ui.main.stake.items.BalanceItem;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StakeBalanceViewHolder extends StakeViewHolder<BalanceItem> {

    @BindView(R.id.txt_stake_balance_eos)
    TextView txtStakeBalanceEos;
    @BindView(R.id.btn_stake)
    TextView btnStake;

    @BindView(R.id.txt_unstake_eos)
    TextView txtUnStakeEos;
    @BindView(R.id.btn_unstake)
    TextView btnUnStake;

    public StakeBalanceViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(BalanceItem item) {
        EosAccount eosAccount = item.getEosAccount();

        long totalEos = eosAccount.cpuWeight + eosAccount.netWeight;
        txtStakeBalanceEos.setText(String.valueOf(totalEos));
        txtUnStakeEos.setText(eosAccount.coreLiquidBalance);
    }
}
