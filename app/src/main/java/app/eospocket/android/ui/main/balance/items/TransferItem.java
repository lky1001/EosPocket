package app.eospocket.android.ui.main.balance.items;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TransferItem {

    private long id;

    private long blockNum;

    private String trxId;

    private String from;

    private String to;

    private String symbol;

    private double quantity;

    private String memo;

    private String created;

    private boolean send;
}
