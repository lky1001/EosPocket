package app.eospocket.android.eos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BlockInfoRequest {

    @JsonProperty("block_num_or_id")
    public long blockNumOrId;
}
