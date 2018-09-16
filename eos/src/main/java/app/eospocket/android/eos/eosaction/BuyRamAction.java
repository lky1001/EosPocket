package app.eospocket.android.eos.eosaction;

import io.mithrilcoin.eos.data.remote.model.types.EosType;
import io.mithrilcoin.eos.data.remote.model.types.TypeAccountName;

public class BuyRamAction implements EosType.Packer, EosAction {

    private TypeAccountName payer;
    private TypeAccountName receiver;
    private String quant;

    public BuyRamAction(TypeAccountName payer, TypeAccountName receiver, String quant) {
        this.payer = payer;
        this.receiver = receiver;
        this.quant = quant;
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

    }
}
