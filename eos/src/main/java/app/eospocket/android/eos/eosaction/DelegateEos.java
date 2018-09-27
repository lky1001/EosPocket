package app.eospocket.android.eos.eosaction;

import com.google.gson.annotations.SerializedName;

import io.mithrilcoin.eos.data.remote.model.types.EosType;
import io.mithrilcoin.eos.data.remote.model.types.TypeAccountName;

public class DelegateEos implements EosType.Packer, EosAction {

    private TypeAccountName from;
    private TypeAccountName receiver;

    @SerializedName("stake_net_quantity")
    private String stakeCpuQuantity;

    @SerializedName("stake_cpu_quantity")
    private String stakeNetQuantity;

    private int transfer;

    public DelegateEos(TypeAccountName from, TypeAccountName receiver, String stakeCpuQuantity, String stakeNetQuantity, int transfer) {
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
        writer.putIntLE(transfer);
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
