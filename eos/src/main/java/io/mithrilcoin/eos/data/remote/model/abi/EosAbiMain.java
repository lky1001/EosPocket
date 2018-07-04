package io.mithrilcoin.eos.data.remote.model.abi;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by swapnibble on 2017-12-22.
 */

public class EosAbiMain {

    @Expose
    public List<EosAbiTypeDef> types;

    @Expose
    public List<EosAbiAction> actions;

    @Expose
    public List<EosAbiStruct> structs;

    @Expose
    public List<EosAbiTable> tables;
}
