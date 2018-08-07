package app.eospocket.android.wallet.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import app.eospocket.android.wallet.db.model.EosAccountToken;
import io.mithrilcoin.eos.data.local.db.Converters;

@Database(entities = {
    EosAccountDao.class,
    EosAccountToken.class
}, version = AppDatabase.VERSION, exportSchema = false)
@TypeConverters({ Converters.class })
public abstract class AppDatabase extends RoomDatabase {

    static final int VERSION = 1;

    public abstract EosAccountDao eosAccountDao();

    public abstract EosAccountToken eosAccountToken();
}
