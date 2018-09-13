package app.eospocket.android.eos.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EosAccount {

    @JsonProperty("account_name")
    @SerializedName("account_name")
    @Expose
    public String accountName;

    @JsonProperty("core_liquid_balance")
    @SerializedName("core_liquid_balance")
    @Expose
    public String coreLiquidBalance;

    @JsonProperty("cpu_limit")
    @SerializedName("cpu_limit")
    @Expose
    public Resource cpuLimit;

    @JsonProperty("cpu_weight")
    @SerializedName("cpu_weight")
    @Expose
    public long cpuWeight;

    @Expose
    public String created;

    @JsonProperty("head_block_num")
    @SerializedName("head_block_num")
    @Expose
    public long headBlockNum;

    @JsonProperty("head_block_time")
    @SerializedName("head_block_time")
    @Expose
    public String headBlockTime;

    @JsonProperty("last_code_update")
    @SerializedName("last_code_update")
    @Expose
    public String lastCodeUpdate;

    @JsonProperty("net_limit")
    @SerializedName("net_limit")
    @Expose
    public Resource netLimit;

    @JsonProperty("net_weight")
    @SerializedName("net_weight")
    @Expose
    public long netWeight;

    @Expose
    public List<Permission> permissions;

    @Expose
    public boolean priviledged;

    @JsonProperty("ram_quota")
    @SerializedName("ram_quota")
    @Expose
    public long ramQuota;

    @JsonProperty("ram_usage")
    @SerializedName("ram_usage")
    @Expose
    public long ramUsage;

    @JsonProperty("refund_request")
    @SerializedName("refund_request")
    @Expose
    public Refund refundRequest;

    @JsonProperty("self_delegated_bandwidth")
    @SerializedName("self_delegated_bandwidth")
    @Expose
    public DelegatedBandwidth selfDelegatedBandwidth;

    @JsonProperty("total_resources")
    @SerializedName("total_resources")
    @Expose
    public TotalResources totalResources;

    @JsonProperty("voter_info")
    @SerializedName("voter_info")
    @Expose
    public VoterInfo voterInfo;
}
