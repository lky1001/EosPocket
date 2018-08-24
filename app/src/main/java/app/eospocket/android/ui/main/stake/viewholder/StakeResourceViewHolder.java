package app.eospocket.android.ui.main.stake.viewholder;

import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import app.eospocket.android.R;
import app.eospocket.android.ui.main.stake.items.ResourceItem;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StakeResourceViewHolder extends StakeViewHolder<ResourceItem> {

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
