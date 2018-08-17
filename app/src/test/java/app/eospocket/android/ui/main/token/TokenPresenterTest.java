package app.eospocket.android.ui.main.token;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import app.eospocket.android.common.Constants;
import app.eospocket.android.common.CustomPreference;
import app.eospocket.android.eos.EosManager;
import app.eospocket.android.eos.model.coinmarketcap.CoinData;
import app.eospocket.android.eos.model.coinmarketcap.CoinMarketCap;
import app.eospocket.android.wallet.PocketAppManager;
import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TokenPresenterTest {

    @Mock
    private TokenView view;

    @Mock
    private EosManager eosManager;

    @Mock
    private PocketAppManager pocketAppManager;

    @Mock
    private CustomPreference customPreference;

    private TestScheduler testScheduler;

    private TokenPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        testScheduler = new TestScheduler();

        presenter = new TokenPresenter(view, eosManager, pocketAppManager, customPreference,
                testScheduler, testScheduler);
    }

    @Test
    public void testGetMarketPrice_success() {
        CoinMarketCap coinMarketCap = new CoinMarketCap();
        coinMarketCap.data = new CoinData();

        when(eosManager.getMarketPrice(anyString())).thenReturn(Single.just(coinMarketCap));

        presenter.getMarketPrice(Constants.EOS_COINMARKETCAP_ID);
        testScheduler.triggerActions();

        verify(view, times(1)).setMarketPrice(any());
    }

    @Test
    public void returnNullCoinMarkertCap_success() {
        CoinMarketCap coinMarketCap = new CoinMarketCap();

        when(eosManager.getMarketPrice(anyString())).thenReturn(Single.just(coinMarketCap));

        presenter.getMarketPrice(Constants.EOS_COINMARKETCAP_ID);
        testScheduler.triggerActions();

        verify(view, times(1)).noMarketPrice();
    }

    @Test
    public void testGetCoinMarketCap_exception() {
        when(eosManager.getMarketPrice(anyString())).thenReturn(Single.create(emitter -> {
            emitter.onError(new IOException());
        }));

        presenter.getMarketPrice(Constants.EOS_COINMARKETCAP_ID);
        testScheduler.triggerActions();

        verify(view, times(1)).noMarketPrice();
    }
}