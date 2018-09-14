package app.eospocket.android.eos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class BlockInfoRequest {

    @JsonProperty("block_num_or_id")
    @SerializedName("block_num_or_id")
    public long blockNumOrId;
}
