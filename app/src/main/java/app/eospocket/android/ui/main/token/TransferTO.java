package app.eospocket.android.ui.main.token;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TransferTO {

    private long id;

    private String from;

    private String to;

    private String symbol;

    private float quantity;

    private String memo;

    private Date created;

    private boolean send;
}
