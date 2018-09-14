package app.eospocket.android.eos.model.action;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Receipt {

    @JsonProperty("abi_sequence")
    @SerializedName("abi_sequence")
    public long abiSequence;

    @JsonProperty("act_digest")
    @SerializedName("act_digest")
    public String actDigest;

    @JsonProperty("auth_sequence")
    @SerializedName("auth_sequence")
    public List<List<Object>> authSequence;

    @JsonProperty("code_sequence")
    @SerializedName("code_sequence")
    public long codeSequence;

    @JsonProperty("global_sequence")
    @SerializedName("global_sequence")
    public long globalSequence;

    public String receiver;

    @JsonProperty("recv_sequence")
    @SerializedName("recv_sequence")
    public long recvSequence;
}
