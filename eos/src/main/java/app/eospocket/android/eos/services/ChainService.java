package app.eospocket.android.eos.services;

import io.mithrilcoin.eos.data.remote.model.api.EosChainInfo;
import io.reactivex.Single;
import retrofit2.http.POST;

public interface ChainService {

    @POST("/v1/chain/get_info")
    Single<EosChainInfo> getInfo();
}
