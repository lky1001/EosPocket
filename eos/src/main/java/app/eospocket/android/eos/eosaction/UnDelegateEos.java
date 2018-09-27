package app.eospocket.android.eos.eosaction;

import com.google.gson.annotations.SerializedName;

import io.mithrilcoin.eos.data.remote.model.types.EosType;
import io.mithrilcoin.eos.data.remote.model.types.TypeAccountName;

public class UnDelegateEos implements EosType.Packer, EosAction {

    private TypeAccountName from;
    private TypeAccountName receiver;

    @SerializedName("unstake_net_quantity")
    private String unStakeCpuQuantity;

    @SerializedName("unstake_cpu_quantity")
    private String unStakeNetQuantity;

    public UnDelegateEos(TypeAccountName from, TypeAccountName receiver, String unStakeCpuQuantity, String unStakeNetQuantity) {
        this.from = from;
        this.receiver = receiver;
        this.unStakeCpuQuantity = unStakeCpuQuantity;
        this.unStakeNetQuantity = unStakeNetQuantity;
    }

    @Override
    public void pack(EosType.Writer writer) {
        from.pack(writer);
        receiver.pack(writer);
        writer.putString(unStakeCpuQuantity);
        writer.putString(unStakeNetQuantity);
    }

    @Override
    public String getContract() {
        return "eosio";
    }

    @Override
    public String getActionName() {
        return "undelegatebw";
    }
}
