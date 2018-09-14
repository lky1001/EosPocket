package app.eospocket.android.eos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class KeyAccountsRequest {

    @JsonProperty("public_key")
    @SerializedName("public_key")
    public String publicKey;
}
