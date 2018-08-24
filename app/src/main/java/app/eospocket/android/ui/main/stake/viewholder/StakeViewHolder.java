package app.eospocket.android.ui.main.stake.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import app.eospocket.android.eos.model.account.EosAccount;
import app.eospocket.android.ui.main.stake.items.StakeItem;

public abstract class StakeViewHolder<T extends StakeItem> extends RecyclerView.ViewHolder {

    public StakeViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bind(T stakeItem);
}
