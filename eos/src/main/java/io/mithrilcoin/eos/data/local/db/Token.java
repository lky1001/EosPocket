package io.mithrilcoin.eos.data.local.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "tokens", indices={@Index(value = "account_name")})
public class Token {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String accountName;

    private String code;

    private String symbol;

    private double quantity;

    private Date created;

}
