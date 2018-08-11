package app.eospocket.android.eos.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Permission {

    public String parent;

    @JsonProperty("perm_name")
    public String permName;

    @JsonProperty("perm_name")
    public RequiredAuth requiredAuth;
}
