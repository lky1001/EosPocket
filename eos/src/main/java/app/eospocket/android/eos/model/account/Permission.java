package app.eospocket.android.eos.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Permission {

    @Expose
    public String parent;

    @JsonProperty("perm_name")
    @SerializedName("perm_name")
    @Expose
    public String permName;

    @JsonProperty("required_auth")
    @SerializedName("required_auth")
    @Expose
    public RequiredAuth requiredAuth;
}
