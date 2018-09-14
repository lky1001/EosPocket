package app.eospocket.android.eos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class AccountRequest {

    @JsonProperty("account_name")
    @SerializedName("account_name")
    public String accountName;
}
