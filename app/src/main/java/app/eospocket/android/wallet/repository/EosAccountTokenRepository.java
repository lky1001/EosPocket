package app.eospocket.android.wallet.repository;

import java.util.List;

import app.eospocket.android.wallet.db.model.EosAccountTokenModel;
import io.reactivex.Single;

public interface EosAccountTokenRepository {

    void insert(EosAccountTokenModel eosAccountTokenModel);

    void insertAll(List<EosAccountTokenModel> eosAccountTokenModels);

    Single<List<EosAccountTokenModel>> getAllTokens(String accountName);
}
