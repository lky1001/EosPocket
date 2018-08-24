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
import app.eospocket.android.ui.main.stake.items.BalanceItem;
import app.eospocket.android.ui.main.stake.items.ResourceItem;
import app.eospocket.android.ui.main.stake.items.StakeItem;
import app.eospocket.android.ui.main.stake.items.StakeUiInfo;
import app.eospocket.android.ui.main.stake.viewholder.StakeViewHolder;
import app.eospocket.android.ui.main.stake.viewholder.StakeResourceViewHolder;
import app.eospocket.android.ui.main.stake.viewholder.StakeBalanceViewHolder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class StakeAdapter extends RecyclerView.Adapter<StakeViewHolder> {

    private List<StakeItem> mItems = new ArrayList<>();

    @NonNull
    @Override
    public StakeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int layoutResId) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutResId, viewGroup, false);

        if (layoutResId == R.layout.list_item_balance) {
            return new StakeBalanceViewHolder(view);
        } else if (layoutResId == R.layout.list_item_stake_resource) {
            return new StakeResourceViewHolder(view);
        }
        return null;
    }

    public void refresh(EosAccount eosAccount) {
        mItems.clear();


        Observable.fromArray(StakeUiInfo.values())
                .flatMap(stakeModel -> Observable.just(createItem(eosAccount, stakeModel)))
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        items -> {
                            mItems.addAll(items);
                            notifyDataSetChanged();
                        },
                        Throwable::printStackTrace
                );
    }

    private StakeItem createItem(EosAccount eosAccount, StakeUiInfo stakeUiInfo) {
        if (!stakeUiInfo.isResourceItem()) {
            return new BalanceItem(eosAccount, stakeUiInfo);
        }
        ResourceItem item = new ResourceItem(eosAccount, stakeUiInfo);
        long max = 0L;
        long used = 0L;

        switch (stakeUiInfo) {
            case CPU:
                max = eosAccount.cpuLimit.max;
                used = eosAccount.cpuLimit.used;
                break;
            case NETWORK:
                max = eosAccount.netLimit.max;
                used = eosAccount.netLimit.used;
                break;
            case RAM:
                max = eosAccount.ramQuota;
                used = eosAccount.ramUsage;
                break;
            case REFUNDING:
                if (eosAccount.refundRequest != null) {
                    //TODO
                }
                break;
            default:
                throw new RuntimeException("StakeItem ResourceType is not availble");
        }

        item.setMax(max);
        item.setUsed(used);

        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull StakeViewHolder stakeViewHolder, int i) {
        stakeViewHolder.bind(mItems.get(i));
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
