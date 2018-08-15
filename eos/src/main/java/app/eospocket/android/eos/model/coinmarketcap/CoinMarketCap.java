package app.eospocket.android.eos.model.coinmarketcap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinMarketCap {

    public CoinData data;
}