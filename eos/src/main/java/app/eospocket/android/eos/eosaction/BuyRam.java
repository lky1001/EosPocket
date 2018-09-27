package app.eospocket.android.eos.eosaction;

import io.mithrilcoin.eos.data.remote.model.types.EosType;
import io.mithrilcoin.eos.data.remote.model.types.TypeAccountName;

public class BuyRam implements EosType.Packer, EosAction {

    private TypeAccountName payer;
    private TypeAccountName receiver;
    private String quant;
    private String bytes;

    public BuyRam(TypeAccountName payer, TypeAccountName receiver, String quant, String bytes) {
        this.payer = payer;
        this.receiver = receiver;
        this.quant = quant;
        this.bytes = bytes;
    }

    @Override
    public String getContract() {
        return "eosio";
    }

    @Override
    public String getActionName() {
        return "buyram";
    }

    @Override
    public void pack(EosType.Writer writer) {
        payer.pack(writer);
        receiver.pack(writer);
        writer.putString(quant);
        writer.putString(bytes);
    }
}
