package app.eospocket.android.wallet.repository;

import java.util.List;

import app.eospocket.android.wallet.db.model.EosAccountModel;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface EosAccountRepository {

    Single<List<EosAccountModel>> findAccount(String accountName);

    void insert(EosAccountModel eosAccountModel);

    void delete(String accountName);

    void deleteAll();

    Flowable<List<EosAccountModel>> getEosAccounts();
}
