package app.eospocket.android.eos.model.action;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InlineTrace {

    public Act act;

    public String console;

    @JsonProperty("cpu_usage")
    @SerializedName("cpu_usage")
    public long cpuUsage;

    public long elapsed;

    public Receipt receipt;

    @JsonProperty("total_cpu_usage")
    @SerializedName("total_cpu_usage")
    public long totalCpuUsage;

    @JsonProperty("trx_id")
    @SerializedName("trx_id")
    public String trxId;
}
