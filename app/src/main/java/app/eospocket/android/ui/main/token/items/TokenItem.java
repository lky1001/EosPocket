package app.eospocket.android.ui.main.token.items;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokenItem {

    private String name;

    private float balance;
}
