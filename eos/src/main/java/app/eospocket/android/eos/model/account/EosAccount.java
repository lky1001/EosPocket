package app.eospocket.android.eos.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EosAccount {

    @JsonProperty("account_name")
    public String accountName;

    @JsonProperty("core_liquid_balance")
    public String coreLiquidBalance;

    @JsonProperty("cpu_limit")
    public Resource cpuLimit;

    @JsonProperty("cpu_weight")
    public long cpuWeight;

    public String created;

    @JsonProperty("head_block_num")
    public long headBlockNum;

    @JsonProperty("head_block_time")
    public String headBlockTime;

    @JsonProperty("last_code_update")
    public String lastCodeUpdate;

    @JsonProperty("net_limit")
    public Resource netLimit;

    @JsonProperty("net_weight")
    public long netWeight;

    public List<Permission> permissions;

    public boolean priviledged;

    @JsonProperty("ram_quota")
    public long ramQuota;

    @JsonProperty("ram_usage")
    public long ramUsage;

    @JsonProperty("refund_request")
    public Refund refundRequest;

    @JsonProperty("self_delegated_bandwidth")
    public DelegatedBandwidth selfDelegatedBandwidth;

    @JsonProperty("total_resources")
    public TotalResources totalResources;

    @JsonProperty("voter_info")
    public VoterInfo voterInfo;
}
