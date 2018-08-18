package app.eospocket.android.ui.main.balance.items;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EosTransferResponse {

    private long totalCount;

    private List<TransferItem> transfers;
}
