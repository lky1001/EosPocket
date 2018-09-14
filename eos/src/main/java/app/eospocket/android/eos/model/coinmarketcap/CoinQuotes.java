package app.eospocket.android.eos.model.coinmarketcap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinQuotes {

    public String price;

    @JsonProperty("price_btc")
    @SerializedName("price_btc")
    public String priceBtc;

    @JsonProperty("volume_24h")
    @SerializedName("volume_24h")
    public String volume24h;

    @JsonProperty("market_cap")
    @SerializedName("market_cap")
    public String marketCap;

    @JsonProperty("percent_change_1h")
    @SerializedName("percent_change_1h")
    public String percentChange1h;

    @JsonProperty("percent_change_24h")
    @SerializedName("percent_change_24h")
    public String percentChange24h;

    @JsonProperty("percent_change_7d")
    @SerializedName("percent_change_7d")
    public String percentChange7d;
}
