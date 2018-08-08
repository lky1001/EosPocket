package app.eospocket.android.eos.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AccountList {

    @JsonProperty("account_names")
    public List<String> accounts;
}
