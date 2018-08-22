package app.eospocket.android.ui.main.stake;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.eospocket.android.R;
import app.eospocket.android.eos.model.account.EosAccount;
import app.eospocket.android.ui.main.stake.items.StakeItem;
import app.eospocket.android.ui.main.stake.items.Title;
import app.eospocket.android.ui.main.stake.viewholder.BaseViewHolder;
import app.eospocket.android.ui.main.stake.viewholder.StakeResourceViewHolder;
import app.eospocket.android.ui.main.stake.viewholder.StakeViewHolder;

public class StakeAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<StakeItem> mItems = new ArrayList<>();
    private EosAccount mEosAccount;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int layoutResId) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutResId, viewGroup, false);

        if (layoutResId == R.layout.list_item_stake) {
            return new StakeViewHolder(view);
        } else if (layoutResId == R.layout.list_item_stake_resource) {
            return new StakeResourceViewHolder(view);
        }
        return null;
    }

    public void refresh(EosAccount eosAccount) {
        mEosAccount = eosAccount;
        mItems.clear();

        for (Title title : Title.values()) {
            mItems.add(new StakeItem(title));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {
        baseViewHolder.bind(mEosAccount);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getLayoutResId();
    }

    @Override
    public long getItemId(int position) {
        return mItems.get(position).getId();
    }
}
