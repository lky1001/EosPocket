package app.eospocket.android.eos.services;

import java.util.List;

import app.eospocket.android.eos.model.BlockInfo;
import app.eospocket.android.eos.model.ChainInfo;
import app.eospocket.android.eos.model.account.EosAccount;
import app.eospocket.android.eos.request.AccountRequest;
import app.eospocket.android.eos.request.BlockInfoRequest;
import app.eospocket.android.eos.request.CurrencyRequest;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ChainService {

    @POST("/v1/chain/get_info")
    Single<ChainInfo> getInfo();

    @POST("/v1/chain/get_block")
    Single<BlockInfo> getBlock(@Body BlockInfoRequest blockInfoRequest);

    @POST("/v1/chain/get_account")
    Single<EosAccount> getAccount(@Body AccountRequest accountRequest);

    @POST("/v1/chain/get_currency_balance")
    Single<List<String>> getCurrencyBalance(@Body CurrencyRequest currencyRequest);
}
