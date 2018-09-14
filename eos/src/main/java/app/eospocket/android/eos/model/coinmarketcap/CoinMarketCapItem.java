package app.eospocket.android.eos.model.coinmarketcap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinMarketCapItem {

    public long id;

    public String name;

    public String symbol;

    @JsonProperty("website_slug")
    @SerializedName("website_slug")
    public String websiteSlug;
}
