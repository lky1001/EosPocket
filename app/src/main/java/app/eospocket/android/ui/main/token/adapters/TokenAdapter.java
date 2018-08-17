package app.eospocket.android.ui.main.token.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.eospocket.android.R;
import app.eospocket.android.ui.AdapterDataModel;
import app.eospocket.android.ui.AdapterView;
import app.eospocket.android.ui.main.token.items.TokenItem;
import app.eospocket.android.utils.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TokenAdapter extends RecyclerView.Adapter<TokenAdapter.TokenViewHolder> implements AdapterDataModel<TokenItem>, AdapterView {

    private List<TokenItem> mList;

    public TokenAdapter() {
        this.mList = new ArrayList<>();
    }

    @NonNull
    @Override
    public TokenViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_token, null);

        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT));

        return new TokenViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TokenViewHolder tokenViewHolder, int position) {
        TokenItem item = mList.get(position);

        tokenViewHolder.tokenNameText.setText(item.getName());
        tokenViewHolder.tokenBalanceText.setText(Utils.formatBalance(item.getBalance()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void add(TokenItem model) {
        mList.add(model);
        notifyItemInserted(getItemCount() - 1);
    }

    @Override
    public void addAll(List<TokenItem> list) {
        mList.addAll(list);
        notifyItemInserted(getItemCount() - 1);
    }

    @Override
    public void remove(int position) {
        mList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public TokenItem getModel(int position) {
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

    public class TokenViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.token_name_text)
        TextView tokenNameText;

        @BindView(R.id.token_balance_text)
        TextView tokenBalanceText;

        public TokenViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
