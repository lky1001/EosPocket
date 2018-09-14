package app.eospocket.android.eos.model.action;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActionList {

    public List<Action> actions;

    @JsonProperty("last_irreversible_block")
    @SerializedName("last_irreversible_block")
    public long lastIrreversibleBlock;
}
