package app.eospocket.android.eos.eosaction;

import io.mithrilcoin.eos.crypto.util.HexUtils;
import io.mithrilcoin.eos.data.remote.model.types.EosByteWriter;
import io.mithrilcoin.eos.data.remote.model.types.EosType;
import io.mithrilcoin.eos.data.remote.model.types.TypeAccountName;

public class TokenTransfer implements EosType.Packer, EosAction {

    private String contract;
    private TypeAccountName from;
    private TypeAccountName to;
    private String quantity;
    private String memo;

    public TokenTransfer(String contract, String from, String to, String quantity, String memo ) {
        this(contract, new TypeAccountName(from), new TypeAccountName(to), quantity, memo );
    }

    public TokenTransfer(String contract, TypeAccountName from, TypeAccountName to, String quantity, String memo ) {
        this.contract = contract;
        this.from = from;
        this.to = to;
        this.quantity = quantity;
        this.memo = memo != null ? memo : "";
    }

    @Override
    public String getContract() {
        return contract;
    }

    @Override
    public String getActionName() {
        return "transfer";
    }

    @Override
    public void pack(EosType.Writer writer) {

        from.pack(writer);
        to.pack(writer);

        writer.putString(quantity);

        writer.putString(memo);
    }

    public String getAsHex() {
        EosType.Writer writer = new EosByteWriter(128);
        pack(writer);

        return HexUtils.toHex( writer.toBytes() );
    }
}
