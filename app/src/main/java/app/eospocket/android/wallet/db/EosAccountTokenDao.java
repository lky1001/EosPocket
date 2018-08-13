package app.eospocket.android.wallet.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import java.util.List;

import app.eospocket.android.wallet.db.model.EosAccountTokenModel;

@Dao
public interface EosAccountTokenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EosAccountTokenModel eosAccountTokenModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<EosAccountTokenModel> eosAccountTokenModels);
}
