package io.mithrilcoin.eos.data.remote.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetBlockInfoRequest {

    @SerializedName("block_num_or_id")
    @Expose
    public String blockNumOrId;
}
