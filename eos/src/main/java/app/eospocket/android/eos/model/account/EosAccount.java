package app.eospocket.android.eos.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EosAccount {

    @JsonProperty("account_name")
    @SerializedName("account_name")
    public String accountName;

    @JsonProperty("core_liquid_balance")
    @SerializedName("core_liquid_balance")
    public String coreLiquidBalance;

    @JsonProperty("cpu_limit")
    @SerializedName("cpu_limit")
    public Resource cpuLimit;

    @JsonProperty("cpu_weight")
    @SerializedName("cpu_weight")
    public long cpuWeight;

    public String created;

    @JsonProperty("head_block_num")
    @SerializedName("head_block_num")
    public long headBlockNum;

    @JsonProperty("head_block_time")
    @SerializedName("head_block_time")
    public String headBlockTime;

    @JsonProperty("last_code_update")
    @SerializedName("last_code_update")
    public String lastCodeUpdate;

    @JsonProperty("net_limit")
    @SerializedName("net_limit")
    public Resource netLimit;

    @JsonProperty("net_weight")
    @SerializedName("net_weight")
    public long netWeight;

    public List<Permission> permissions;

    public boolean priviledged;

    @JsonProperty("ram_quota")
    @SerializedName("ram_quota")
    public long ramQuota;

    @JsonProperty("ram_usage")
    @SerializedName("ram_usage")
    public long ramUsage;

    @JsonProperty("refund_request")
    @SerializedName("refund_request")
    public Refund refundRequest;

    @JsonProperty("self_delegated_bandwidth")
    @SerializedName("self_delegated_bandwidth")
    public DelegatedBandwidth selfDelegatedBandwidth;

    @JsonProperty("total_resources")
    @SerializedName("total_resources")
    public TotalResources totalResources;

    @JsonProperty("voter_info")
    @SerializedName("voter_info")
    public VoterInfo voterInfo;
}
