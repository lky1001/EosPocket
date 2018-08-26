package app.eospocket.android.ui.main.balance;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import app.eospocket.android.common.Constants;
import app.eospocket.android.common.CustomPreference;
import app.eospocket.android.common.rxjava.RxJavaSchedulers;
import app.eospocket.android.eos.EosManager;
import app.eospocket.android.eos.model.coinmarketcap.CoinData;
import app.eospocket.android.eos.model.coinmarketcap.CoinMarketCap;
import app.eospocket.android.wallet.PocketAppManager;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BalancePresenterTest {

    @Mock
    private BalanceView view;

    @Mock
    private EosManager eosManager;

    @Mock
    private PocketAppManager pocketAppManager;

    @Mock
    private CustomPreference customPreference;

    private TestScheduler testScheduler;

    private BalancePresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        testScheduler = new TestScheduler();

        presenter = new BalancePresenter(view, eosManager, pocketAppManager, customPreference,
                new RxJavaSchedulers() {
                    @Override
                    public Scheduler getNewThread() {
                        return testScheduler;
                    }

                    @Override
                    public Scheduler getComputation() {
                        return testScheduler;
                    }

                    @Override
                    public Scheduler getIo() {
                        return testScheduler;
                    }

                    @Override
                    public Scheduler getMainThread() {
                        return testScheduler;
                    }
                });
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