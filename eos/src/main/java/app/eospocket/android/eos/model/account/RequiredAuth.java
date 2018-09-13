package app.eospocket.android.eos.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequiredAuth {

    @Expose
    public List<Object> accounts;

    @Expose
    public List<Key> keys;

    @Expose
    public int threshold;

    @Expose
    public List<Object> waits;
}
