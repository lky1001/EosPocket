package app.eospocket.android.eos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActionRequest {

    @JsonProperty("account_name")
    public String accountName;

    public long offset;

    public long pos;
}
