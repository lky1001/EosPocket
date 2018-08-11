package app.eospocket.android.eos.model.action;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Action {

    @JsonProperty("account_action_seq")
    private long accountActionSeq;

    @JsonProperty("block_num")
    private long blockNum;

    @JsonProperty("block_time")
    private String blockTime;

    @JsonProperty("global_action_seq")
    private long globalActionSeq;
}
