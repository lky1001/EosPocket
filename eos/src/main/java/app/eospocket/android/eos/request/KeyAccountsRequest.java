package app.eospocket.android.eos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KeyAccountsRequest {

    @JsonProperty("public_key")
    public String publicKey;
}
