package app.eospocket.android.wallet.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import app.eospocket.android.wallet.db.model.EosAccountModel;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface EosAccountDao {

    @Query("SELECT * FROM eos_account WHERE account_name = :accountName")
    Single<List<EosAccountModel>> findAccount(String accountName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EosAccountModel eosAccountModel);

    @Query("DELETE FROM eos_account WHERE account_name = :accountName")
    void delete(String accountName);

    @Query("DELETE FROM eos_account")
    void deleteAll();

    @Query("SELECT * FROM eos_account")
    Flowable<List<EosAccountModel>> getEosAccounts();

    @Query("SELECT * FROM eos_account WHERE _id = :id LIMIT 1")
    Single<EosAccountModel> findOneById(int id);
}
