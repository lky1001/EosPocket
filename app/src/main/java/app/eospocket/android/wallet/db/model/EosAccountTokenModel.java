package app.eospocket.android.wallet.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity( tableName = "eos_account_token", indices={@Index(value = "account_name")})
public class EosAccountTokenModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int id;

    @ColumnInfo(name = "account_name")
    @NonNull
    public String name;

    @ColumnInfo(name = "token_name")
    @NonNull
    public String tokenName;

    @ColumnInfo(name = "token_symbol")
    @NonNull
    public String symbol;

    @ColumnInfo(name = "balance")
    @NonNull
    public float balance;

    @ColumnInfo(name = "token_contract")
    @NonNull
    public String contract;
}
