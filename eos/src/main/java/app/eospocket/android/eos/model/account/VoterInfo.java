package app.eospocket.android.eos.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VoterInfo {

    @JsonProperty("is_proxy")
    @SerializedName("is_proxy")
    public int isProxy;

    @JsonProperty("last_vote_weight")
    @SerializedName("last_vote_weight")
    public String lastVoteWeight;

    public String owner;

    public List<Object>producers;

    @JsonProperty("proxied_vote_weight")
    @SerializedName("proxied_vote_weight")
    public String proxiedVoteWeight;

    public String proxy;

    public long staked;
}
