package app.eospocket.android.eos.model.action;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Action {

    @JsonProperty("account_action_seq")
    @SerializedName("account_action_seq")
    public long accountActionSeq;

    @JsonProperty("action_trace")
    @SerializedName("action_trace")
    public ActionTrace actionTrace;

    @JsonProperty("block_num")
    @SerializedName("block_num")
    public long blockNum;

    @JsonProperty("block_time")
    @SerializedName("block_time")
    public String blockTime;

    @JsonProperty("global_action_seq")
    @SerializedName("global_action_seq")
    public long globalActionSeq;
}
