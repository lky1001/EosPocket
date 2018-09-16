package app.eospocket.android.eos.eosaction;

import io.mithrilcoin.eos.data.remote.model.types.EosType;
import io.mithrilcoin.eos.data.remote.model.types.TypeAccountName;

public class DelegateEos implements EosType.Packer, EosAction {

    private TypeAccountName from;
    private TypeAccountName receiver;
    private String stakeCpuQuantity;
    private String stakeNetQuantity;
    private boolean transfer;

    public DelegateEos(TypeAccountName from, TypeAccountName receiver, String stakeCpuQuantity, String stakeNetQuantity, boolean transfer) {
        this.from = from;
        this.receiver = receiver;
        this.stakeCpuQuantity = stakeCpuQuantity;
        this.stakeNetQuantity = stakeNetQuantity;
        this.transfer = transfer;
    }

    @Override
    public void pack(EosType.Writer writer) {
        from.pack(writer);
        receiver.pack(writer);
        writer.putString(stakeCpuQuantity);
        writer.putString(stakeNetQuantity);
    }

    @Override
    public String getContract() {
        return "eosio";
    }

    @Override
    public String getActionName() {
        return "delegatebw";
    }
}
