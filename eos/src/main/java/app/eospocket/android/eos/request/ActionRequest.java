package app.eospocket.android.eos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class ActionRequest {

    @JsonProperty("account_name")
    @SerializedName("account_name")
    public String accountName;

    public long offset;

    public long pos;
}
