package io.mithrilcoin.eos.data.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

/**
 * Created by swapnibble on 2017-12-11.
 */
@Database(entities = {
        EosAccount.class,
        EosNetwork.class
}, version = AppDatabase.VERSION, exportSchema = false)
@TypeConverters({ Converters.class })
public abstract class AppDatabase extends RoomDatabase {

    static final int VERSION = 1;

    public abstract EosAccountDao eosAccountDao();

    public abstract EosNetworkDao eosNetworkDao();
}
