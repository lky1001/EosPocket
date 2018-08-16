package app.eospocket.android.ui.main.token;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokenTO {

    private String name;

    private float balance;
}
