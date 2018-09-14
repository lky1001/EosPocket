package app.eospocket.android.eos.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Key {

    @JsonProperty("key")
    @SerializedName("key")
    public String publicKey;

    public int weight;
}
