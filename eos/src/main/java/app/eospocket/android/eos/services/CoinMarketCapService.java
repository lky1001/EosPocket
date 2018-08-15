package app.eospocket.android.eos.services;

import app.eospocket.android.eos.model.coinmarketcap.CoinMarketCap;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CoinMarketCapService {

    @GET("v2/ticker/{id}")
    Single<CoinMarketCap> getPrice(@Path("id") String id);
}
