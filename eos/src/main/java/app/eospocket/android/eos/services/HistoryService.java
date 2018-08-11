package app.eospocket.android.eos.services;

import app.eospocket.android.eos.model.AccountList;
import app.eospocket.android.eos.model.action.ActionList;
import app.eospocket.android.eos.request.ActionRequest;
import app.eospocket.android.eos.request.KeyAccountsRequest;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HistoryService {

    @POST("/v1/history/get_key_accounts")
    Single<AccountList> getAccountsByKey(@Body KeyAccountsRequest keyAccountsRequest);

    @POST("/v1/history/get_actions")
    Single<ActionList> getAccountActions(@Body ActionRequest request);
}
