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
    private String accountName;

    @ColumnInfo(name = "token_name")
    @NonNull
    private String tokenName;

    @ColumnInfo(name = "token_symbol")
    @NonNull
    private String symbol;

    @ColumnInfo(name = "balance")
    @NonNull
    private float balance;

    @ColumnInfo(name = "token_contract")
    @NonNull
    private String contract;

    @ColumnInfo(name = "coinmarketcap_id")
    private String coinmarketcapId;
}
