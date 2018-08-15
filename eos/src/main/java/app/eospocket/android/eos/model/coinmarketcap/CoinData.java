package app.eospocket.android.eos.model.coinmarketcap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinData {

    @JsonProperty("id")
    public String id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("symbol")
    public String symbol;

    @JsonProperty("website_slug")
    public String websiteSlug;

    @JsonProperty("rank")
    public String rank;

    @JsonProperty("circulating_supply")
    public String circulatingSupply;

    @JsonProperty("total_supply")
    public String totalSupply;

    @JsonProperty("max_supply")
    public Object maxSupply;

    public Map<String, CoinQuotes> quotes;

    @JsonProperty("last_updated")
    public long lastUpdated;
}
