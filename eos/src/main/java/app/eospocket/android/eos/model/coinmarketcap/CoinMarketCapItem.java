package app.eospocket.android.eos.model.coinmarketcap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinMarketCapItem {

    public long id;

    public String name;

    public String symbol;

    @JsonProperty("website_slug")
    public String websiteSlug;
}
