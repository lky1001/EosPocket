package app.eospocket.android.ui.main.stake.items;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StakeItem {
    private int id;
    private int layoutResId;
    private Title title;

    public StakeItem(Title title) {
        this.id = title.ordinal();
        this.title = title;
        this.layoutResId = title.getLayoutResId();
    }
}
