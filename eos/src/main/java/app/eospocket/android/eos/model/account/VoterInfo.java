package app.eospocket.android.eos.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VoterInfo {

    @JsonProperty("is_proxy")
    @SerializedName("is_proxy")
    @Expose
    public int isProxy;

    @JsonProperty("last_vote_weight")
    @SerializedName("last_vote_weight")
    @Expose
    public String lastVoteWeight;

    @Expose
    public String owner;

    @Expose
    public List<Object>producers;

    @JsonProperty("proxied_vote_weight")
    @SerializedName("proxied_vote_weight")
    @Expose
    public String proxiedVoteWeight;

    @Expose
    public String proxy;

    @Expose
    public long staked;
}
