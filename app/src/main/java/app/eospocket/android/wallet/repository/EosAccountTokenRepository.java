package app.eospocket.android.wallet.repository;

import java.util.List;

import app.eospocket.android.wallet.db.model.EosAccountTokenModel;

public interface EosAccountTokenRepository {

    void insert(EosAccountTokenModel eosAccountTokenModel);

    void insertAll(List<EosAccountTokenModel> eosAccountTokenModels);
}
