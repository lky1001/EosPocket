package app.eospocket.android.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EosSetting {

    private String nodeosHost;

    private int nodeosPort;

    private boolean usePinCode;

    private String pinCode;

    private boolean initWallet;

    private int keyStoreVersion;

    private long parseActionSeq;
}