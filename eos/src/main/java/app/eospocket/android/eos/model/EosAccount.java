package app.eospocket.android.eos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EosAccount {

    @JsonProperty("account_name")
    public String accountName;

    @JsonProperty("core_liquid_balance")
    public String coreLiquidBalance;
}
