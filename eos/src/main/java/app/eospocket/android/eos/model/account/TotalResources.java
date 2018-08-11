package app.eospocket.android.eos.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TotalResources {

    @JsonProperty("cpu_weight")
    public String cpuWeight;

    @JsonProperty("net_weight")
    public String netWeight;

    public String owner;

    @JsonProperty("ram_bytes")
    public long ramBytes;
}
