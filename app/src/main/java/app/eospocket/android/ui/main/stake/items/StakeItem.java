package app.eospocket.android.ui.main.stake.items;

import app.eospocket.android.eos.model.account.EosAccount;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class StakeItem {
    private int id;
    private int layoutResId;
    private EosAccount eosAccount;
    private StakeUiInfo stakeUiInfo;

    public StakeItem(EosAccount eosAccount, StakeUiInfo stakeUiInfo) {
        this.id = stakeUiInfo.ordinal();
        this.eosAccount = eosAccount;
        this.stakeUiInfo = stakeUiInfo;
        this.layoutResId = stakeUiInfo.getLayoutResId();
    }
}
