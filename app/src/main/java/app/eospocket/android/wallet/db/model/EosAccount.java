package app.eospocket.android.wallet.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

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
@Entity( tableName = "eos_account", indices={@Index(value = "account_name")})
public class EosAccount {

    public static final int ACCOUNT_TYPE_ALL = 0;
    public static final int ACCOUNT_TYPE_USER = 1;
    public static final int ACCOUNT_TYPE_CONTRACT = 2;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int id;

    @ColumnInfo(name = "account_name")
    @NonNull
    public String name;

    @ColumnInfo(name = "account_type")
    public Integer type;

    @ColumnInfo(name = "account_pub_key")
    @NonNull
    public String publicKey;

    @ColumnInfo(name = "account_priv_key")
    @NonNull
    public String privateKey;

    private Date created;

    private Date updated;
}
