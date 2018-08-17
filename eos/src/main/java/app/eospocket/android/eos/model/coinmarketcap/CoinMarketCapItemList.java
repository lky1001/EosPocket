package app.eospocket.android.eos.model.coinmarketcap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinMarketCapItemList {

    public List<CoinMarketCapItem> data;
}
