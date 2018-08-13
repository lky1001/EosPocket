package app.eospocket.android.eos.model.action;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Receipt {

    @JsonProperty("abi_sequence")
    public long abiSequence;

    @JsonProperty("act_digest")
    public String actDigest;

    @JsonProperty("auth_sequence")
    public List<List<Object>> authSequence;

    @JsonProperty("code_sequence")
    public long codeSequence;

    @JsonProperty("global_sequence")
    public long globalSequence;

    public String receiver;

    @JsonProperty("recv_sequence")
    public long recvSequence;
}
