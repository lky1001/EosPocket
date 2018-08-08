package app.eospocket.android.eos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountRequest {

    @JsonProperty("account_name")
    public String accountName;
}
