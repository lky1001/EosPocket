package app.eospocket.android.eos.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DelegatedBandwidth {

    @JsonProperty("cpu_weight")
    @SerializedName("cpu_weight")
    public String cpuWeight;

    @JsonProperty("net_weight")
    @SerializedName("net_weight")
    public String netWeight;

    public String from;

    public String to;
}
