package app.eospocket.android.ui.main.stake.items;

import app.eospocket.android.eos.model.account.EosAccount;

public class BalanceItem extends StakeItem {

    public BalanceItem(EosAccount eosAccount, StakeUiInfo stakeUiInfo) {
        super(eosAccount, stakeUiInfo);
    }
}
