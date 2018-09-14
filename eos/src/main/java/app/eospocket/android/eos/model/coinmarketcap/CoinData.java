package app.eospocket.android.eos.model.coinmarketcap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinData {

    public String id;

    @JsonProperty("name")
    @SerializedName("name")
    public String name;

    @JsonProperty("symbol")
    @SerializedName("symbol")
    public String symbol;

    @JsonProperty("website_slug")
    @SerializedName("website_slug")
    public String websiteSlug;

    public String rank;

    @JsonProperty("circulating_supply")
    @SerializedName("circulating_supply")
    public String circulatingSupply;

    @JsonProperty("total_supply")
    @SerializedName("total_supply")
    public String totalSupply;

    @JsonProperty("max_supply")
    @SerializedName("max_supply")
    public Object maxSupply;

    public Map<String, CoinQuotes> quotes;

    @JsonProperty("last_updated")
    @SerializedName("last_updated")
    public long lastUpdated;
}
