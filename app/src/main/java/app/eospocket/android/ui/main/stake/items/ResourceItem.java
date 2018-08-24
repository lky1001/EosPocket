package app.eospocket.android.ui.main.stake.items;

import app.eospocket.android.eos.model.account.EosAccount;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceItem extends StakeItem {

    private long max;
    private long used;


    public ResourceItem(EosAccount eosAccount, StakeUiInfo stakeUiInfo) {
        super(eosAccount, stakeUiInfo);
    }
}
