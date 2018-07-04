package io.mithrilcoin.eos.data.local.repository;

import java.util.ArrayList;
import java.util.List;

import io.mithrilcoin.eos.data.local.db.AppDatabase;
import io.mithrilcoin.eos.data.local.db.EosAccount;

/**
 * Created by swapnibble on 2017-12-14.
 */

public class EosAccountRepositoryImpl implements EosAccountRepository {

    private AppDatabase mAppDatabase;

    public EosAccountRepositoryImpl( AppDatabase appDatabase ) {
        mAppDatabase = appDatabase;
    }


    @Override
    public void addAll(String... accountNames) {

        ArrayList<EosAccount> eosAccounts = new ArrayList<>(accountNames.length);

        for ( String name : accountNames ) {
            eosAccounts.add( EosAccount.from(name ) );
        }

        mAppDatabase.eosAccountDao().insertAll(eosAccounts);
    }

    @Override
    public void addAll(List<String> accountNames){
        ArrayList<EosAccount> eosAccounts = new ArrayList<>(accountNames.size());

        for ( String name : accountNames ) {
            eosAccounts.add( EosAccount.from(name ) );
        }

        mAppDatabase.eosAccountDao().insertAll(eosAccounts);
    }

    @Override
    public void addAccount(String accountName) {
        mAppDatabase.eosAccountDao().insert( EosAccount.from(accountName) );
    }

    @Override
    public void deleteAll() {
        mAppDatabase.eosAccountDao().deleteAll();
    }

    @Override
    public void delete(String accountName) {
        mAppDatabase.eosAccountDao().delete( EosAccount.from(accountName));
    }

    @Override
    public List<String> getAll() {
        return mAppDatabase.eosAccountDao().getAll(); // get accounts from db
    }

    @Override
    public List<String> searchName( String nameStarts) {
        return mAppDatabase.eosAccountDao().getAll( nameStarts + "%%");
    }
}
