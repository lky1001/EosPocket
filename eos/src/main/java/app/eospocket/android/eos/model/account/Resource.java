package app.eospocket.android.eos.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Resource {

    @Expose
    public long available;

    @Expose
    public long max;

    @Expose
    public long used;
}
