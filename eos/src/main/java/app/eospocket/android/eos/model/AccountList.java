package app.eospocket.android.eos.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AccountList {

    @JsonProperty("account_names")
    @SerializedName("account_names")
    public List<String> accounts;
}
