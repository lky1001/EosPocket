package app.eospocket.android.eos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChainInfo {

    @JsonProperty("block_cpu_limit")
    public long blockCpuLimit;

    @JsonProperty("block_net_limit")
    public long blockNetLimit;

    @JsonProperty("chain_id")
    public String chainId;

    @JsonProperty("head_block_id")
    public String headBlockId;

    @JsonProperty("head_block_num")
    public long headBlockNum;

    @JsonProperty("head_block_producer")
    public String headBlockProducer;

    @JsonProperty("head_block_time")
    public String headBlockTime;

    @JsonProperty("last_irreversible_block_id")
    public String lastIrreversibleBlockId;

    @JsonProperty("last_irreversible_block_num")
    public long lastIrreversibleBlockNum;

    @JsonProperty("server_version")
    public String serverVersion;

    @JsonProperty("virtual_block_cpu_limit")
    public long virtualBlockCpuLimit;

    @JsonProperty("virtual_block_net_limit")
    public long virtualBlockNetLimit;
}
