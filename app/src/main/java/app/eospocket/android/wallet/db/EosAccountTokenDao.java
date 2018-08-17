package app.eospocket.android.wallet.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.NonNull;

import java.util.List;

import app.eospocket.android.wallet.db.model.EosAccountTokenModel;
import io.reactivex.Single;

@Dao
public interface EosAccountTokenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EosAccountTokenModel eosAccountTokenModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<EosAccountTokenModel> eosAccountTokenModels);

    @Query("SELECT * FROM eos_account_token WHERE account_name = :accountName")
    Single<List<EosAccountTokenModel>> getAllTokens(String accountName);

    @Query("SELECT * FROM eos_account_token WHERE account_name = :accountName AND token_contract = :contract")
    EosAccountTokenModel getToken(@NonNull String accountName, @NonNull String contract);

    @Update
    void update(@NonNull EosAccountTokenModel eosAccountTokenModels);
}
