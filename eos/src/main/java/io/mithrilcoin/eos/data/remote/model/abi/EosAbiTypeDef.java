package io.mithrilcoin.eos.data.remote.model.abi;

import com.google.gson.annotations.Expose;

/**
 * Created by swapnibble on 2018-01-03.
 */

public class EosAbiTypeDef {
    @Expose
    public String new_type_name; // fixed_string32

    @Expose
    public String type;
}
