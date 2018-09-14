package app.eospocket.android.eos.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequiredAuth {

    public List<Object> accounts;

    public List<Key> keys;

    public int threshold;

    public List<Object> waits;
}
