package app.eospocket.android.eos.model.coinmarketcap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinQuotes {

    public String price;

    @JsonProperty("price_btc")
    public String priceBtc;

    @JsonProperty("volume_24h")
    public String volume24h;

    @JsonProperty("market_cap")
    public String marketCap;

    @JsonProperty("percent_change_1h")
    public String percentChange1h;

    @JsonProperty("percent_change_24h")
    public String percentChange24h;

    @JsonProperty("percent_change_7d")
    public String percentChange7d;
}
