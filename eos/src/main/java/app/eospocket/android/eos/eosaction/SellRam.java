package app.eospocket.android.eos.eosaction;

import io.mithrilcoin.eos.data.remote.model.types.EosType;
import io.mithrilcoin.eos.data.remote.model.types.TypeAccountName;

public class SellRam implements EosType.Packer, EosAction {

    private TypeAccountName account;
    private String bytes;

    public SellRam(TypeAccountName account, String bytes) {
        this.account = account;
        this.bytes = bytes;
    }

    @Override
    public String getContract() {
        return "eosio";
    }

    @Override
    public String getActionName() {
        return "sellram";
    }

    @Override
    public void pack(EosType.Writer writer) {
        account.pack(writer);
        writer.putString(bytes);
    }
}
