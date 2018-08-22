package app.eospocket.android.ui.main.stake.items;

import app.eospocket.android.R;

public enum Title {
    BALANCE_STAKE(null, null, R.layout.list_item_stake),
    CPU("CPU", "ms", R.layout.list_item_stake_resource),
    NETWORK("Network", "bytes", R.layout.list_item_stake_resource),
    REFUNDING("Refunding", "EOS", R.layout.list_item_stake_resource),
    RAM("RAM", "KiB", R.layout.list_item_stake_resource);

    private String title;
    private String unit;
    private int layoutResId;

    Title(String title, String unit, int layoutResId) {
        this.title = title;
        this.unit = unit;
        this.layoutResId = layoutResId;
    }

    public String getTitle() {
        return title;
    }

    public String getUnit() {
        return unit;
    }

    public int getLayoutResId() {
        return layoutResId;
    }
}
