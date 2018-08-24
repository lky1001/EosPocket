package app.eospocket.android.ui.main.stake.items;

import app.eospocket.android.R;

public enum StakeUiInfo {
    BALANCE(false, null, null, R.layout.list_item_balance, -1),
    CPU(true, "CPU", "ms", R.layout.list_item_stake_resource, R.color.stake_resource_cpu_color),
    NETWORK(true, "Network", "bytes", R.layout.list_item_stake_resource, R.color.stake_resource_network_color),
    REFUNDING(true, "Refunding", "EOS", R.layout.list_item_stake_resource, R.color.stake_resource_refunding_color),
    RAM(true, "RAM", "KiB", R.layout.list_item_stake_resource, R.color.stake_resource_ram_color);

    private String title;
    private String unit;
    private int layoutResId;
    private int colorResId;
    private boolean isResourceItem;

    StakeUiInfo(boolean isResourceItem, String title, String unit, int layoutResId, int colorResId) {
        this.isResourceItem = isResourceItem;
        this.title = title;
        this.unit = unit;
        this.layoutResId = layoutResId;
        this.colorResId = colorResId;
    }

    public boolean isResourceItem() {
        return isResourceItem;
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

    public int getColorResId() {
        return colorResId;
    }
}
