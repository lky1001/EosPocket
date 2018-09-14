package app.eospocket.android.wallet.repository;

import android.support.annotation.NonNull;

import java.util.List;

import app.eospocket.android.wallet.db.model.EosAccountModel;
import app.eospocket.android.wallet.db.model.EosAccountTokenModel;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface EosAccountRepository {

    Single<List<EosAccountModel>> findAccount(@NonNull String accountName);

    void insert(@NonNull EosAccountModel eosAccountModel);

    void delete(@NonNull String accountName);

    void deleteAll();

    Flowable<List<EosAccountModel>> getEosAccounts();

    Single<EosAccountModel> findOneById(int id);
}
