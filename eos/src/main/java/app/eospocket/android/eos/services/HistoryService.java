package app.eospocket.android.eos.services;

import app.eospocket.android.eos.model.AccountList;
import app.eospocket.android.eos.request.KeyAccountsRequest;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HistoryService {

    @POST("/v1/history/get_key_accounts")
    Single<AccountList> getAccountsByKey(@Body KeyAccountsRequest keyAccountsRequest);
}
