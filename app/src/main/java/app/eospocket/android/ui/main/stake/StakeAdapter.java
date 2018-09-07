package app.eospocket.android.ui.main.stake;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.eospocket.android.R;
import app.eospocket.android.eos.model.account.EosAccount;
import app.eospocket.android.ui.base.ViewHolder;
import app.eospocket.android.ui.main.stake.items.BalanceItem;
import app.eospocket.android.ui.main.stake.items.ResourceItem;
import app.eospocket.android.ui.main.stake.items.StakeItem;
import app.eospocket.android.ui.main.stake.items.StakeUiInfo;
import app.eospocket.android.utils.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class StakeAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<StakeItem> mItems = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int layoutResId) {
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
    public void onBindViewHolder(@NonNull ViewHolder stakeViewHolder, int i) {
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


    public class StakeResourceViewHolder extends ViewHolder<ResourceItem> {
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
        public void bind(ResourceItem item) {
            try {
                txtTitle.setText(item.getStakeUiInfo().getTitle());
                txtDesc.setText(getDescription(item));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private Spannable getDescription(ResourceItem item) throws Exception {
            int strColor = itemView.getContext().getResources().getColor(item.getStakeUiInfo().getColorResId());

            String used = item.getUsed() + " " + item.getStakeUiInfo().getUnit();
            String max = item.getMax() + " " + item.getStakeUiInfo().getUnit();

            SpannableString usedStr = new SpannableString(used + " / " + max);
            usedStr.setSpan(
                    new ForegroundColorSpan(strColor),
                    0,
                    used.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            );

            return usedStr;
        }
    }

    public class StakeBalanceViewHolder extends ViewHolder<BalanceItem> {

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

            double cpuWeight = Double.parseDouble(eosAccount.totalResources.cpuWeight.split(" ")[0]);
            double netWeight = Double.parseDouble(eosAccount.totalResources.netWeight.split(" ")[0]);

            txtStakeBalanceEos.setText(Utils.formatBalance(cpuWeight + netWeight) + " EOS");
            txtUnStakeEos.setText(eosAccount.coreLiquidBalance);
        }
    }
}
