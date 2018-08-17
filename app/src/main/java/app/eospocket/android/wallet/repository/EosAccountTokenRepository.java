package app.eospocket.android.wallet.repository;

import android.support.annotation.NonNull;

import java.util.List;

import app.eospocket.android.wallet.db.model.EosAccountTokenModel;
import io.reactivex.Single;

public interface EosAccountTokenRepository {

    void insert(EosAccountTokenModel eosAccountTokenModel);

    void insertAll(List<EosAccountTokenModel> eosAccountTokenModels);

    Single<List<EosAccountTokenModel>> getAllTokens(String accountName);

    EosAccountTokenModel getToken(@NonNull String accountName, @NonNull String contract);

    void updateToken(@NonNull EosAccountTokenModel eosAccountTokenModels);
}
