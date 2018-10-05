package app.eospocket.android.ui.main.balance.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.eospocket.android.R;
import app.eospocket.android.ui.AdapterDataModel;
import app.eospocket.android.ui.AdapterView;
import app.eospocket.android.ui.main.balance.items.TransferItem;
import app.eospocket.android.utils.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TransferAdapter extends RecyclerView.Adapter<TransferAdapter.TransferViewHolder> implements AdapterDataModel<TransferItem>, AdapterView {

    private List<TransferItem> mList = new ArrayList<>();

    private Context mContext;

    private View.OnClickListener mOnItemClickListener;

    public TransferAdapter(Context context, View.OnClickListener onClickListener) {
        this.mContext = context;
        this.mOnItemClickListener = onClickListener;
    }

    @NonNull
    @Override
    public TransferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_transfer, null);
        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT));
        v.setOnClickListener(mOnItemClickListener);
        return new TransferViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TransferViewHolder transferViewHolder, int position) {
        TransferItem transfer = mList.get(position);

        // 2018-07-02T02:14:22.500

        if (transfer.isSend()) {
            transferViewHolder.sendLayout.setVisibility(View.VISIBLE);
            transferViewHolder.receiveLayout.setVisibility(View.GONE);
            transferViewHolder.sendAccountNameText.setText(transfer.getTo());
            transferViewHolder.sendQuantityText.setText(Utils.formatBalanceWithSymbol(transfer.getQuantity(), transfer.getSymbol()));
            transferViewHolder.sendMemoText.setText(transfer.getMemo());
            transferViewHolder.sendDateText.setText(transfer.getCreated());
        } else {
            transferViewHolder.sendLayout.setVisibility(View.GONE);
            transferViewHolder.receiveLayout.setVisibility(View.VISIBLE);
            transferViewHolder.receiveAccountNameText.setText(transfer.getFrom());
            transferViewHolder.receiveQuantityText.setText(Utils.formatBalanceWithSymbol(transfer.getQuantity(), transfer.getSymbol()));
            transferViewHolder.receiveMemoTest.setText(transfer.getMemo());
            transferViewHolder.receiveDateText.setText(transfer.getCreated());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void add(TransferItem model) {
        mList.add(model);
        notifyDataSetChanged();
    }

    @Override
    public void addAll(List<TransferItem> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void remove(int position) {
        mList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public TransferItem getModel(int position) {
        return mList.get(position);
    }

    @Override
    public int getSize() {
        return mList.size();
    }

    @Override
    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    public class TransferViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.send_layout)
        LinearLayout sendLayout;

        @BindView(R.id.receive_layout)
        LinearLayout receiveLayout;

        @BindView(R.id.send_account_name_text)
        TextView sendAccountNameText;

        @BindView(R.id.receive_account_name_text)
        TextView receiveAccountNameText;

        @BindView(R.id.send_date_text)
        TextView sendDateText;

        @BindView(R.id.receive_date_text)
        TextView receiveDateText;

        @BindView(R.id.send_quantity_text)
        TextView sendQuantityText;

        @BindView(R.id.receive_quantity_text)
        TextView receiveQuantityText;

        @BindView(R.id.copy_from_account_name)
        ImageView copyFromAccountNameView;

        @BindView(R.id.copy_to_account_name)
        ImageView copyToAccountNameView;

        @BindView(R.id.send_memo_text)
        TextView sendMemoText;

        @BindView(R.id.receive_memo_text)
        TextView receiveMemoTest;

        public TransferViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
