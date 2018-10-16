package app.eospocket.android.eos.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Refund {

    public String owner;

    public String request_time;

    public String net_amount;

    public String cpu_amount;
}
