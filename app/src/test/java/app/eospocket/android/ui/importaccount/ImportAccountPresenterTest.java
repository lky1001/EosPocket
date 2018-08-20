package app.eospocket.android.ui.importaccount;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import app.eospocket.android.eos.EosManager;
import app.eospocket.android.eos.model.AccountList;
import app.eospocket.android.security.keystore.KeyStore;
import app.eospocket.android.utils.EncryptUtil;
import app.eospocket.android.wallet.PocketAppManager;
import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class ImportAccountPresenterTest {

    private static final String TESTABLE_PRIVATE_KEY = "5KQwrPbwdL6PhXujxW37FSSQZ1JiwsST4cqQzDeyXtP79zkvFD3";

    @Mock
    private ImportAccountView view;

    @Mock
    private EosManager eosManager;

    @Mock
    private EncryptUtil encryptUtil;

    @Mock
    private KeyStore keyStore;

    @Mock
    private PocketAppManager pocketAppManager;

    private TestScheduler testScheduler;

    private ImportAccountPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        testScheduler = new TestScheduler();

        presenter = new ImportAccountPresenter(view, eosManager, encryptUtil, keyStore,
                pocketAppManager, testScheduler, testScheduler);
    }

    @Test
    public void testFindAccount_success() {
        AccountList accountList = new AccountList();
        accountList.accounts = Arrays.asList("test");

        doReturn(Single.just(accountList)).when(eosManager).findAccountByPublicKey(any());

        presenter.findAccount(TESTABLE_PRIVATE_KEY);
        testScheduler.triggerActions();

        verify(view).getAccount(any());
    }

    @Test
    public void testNoAccount_success() {
        AccountList accountList = new AccountList();

        doReturn(Single.just(accountList)).when(eosManager).findAccountByPublicKey(any());

        presenter.findAccount(TESTABLE_PRIVATE_KEY);
        testScheduler.triggerActions();

        verify(view).noAccount();
    }

    @Test
    public void testInvalidPrivateKey_success() {
        AccountList accountList = new AccountList();

        doReturn(Single.just(accountList)).when(eosManager).findAccountByPublicKey(any());

        presenter.findAccount("test");
        testScheduler.triggerActions();

        verify(view).noAccount();
    }

    @Test
    public void findAccountName() {
    }

    @Test
    public void importAccount() {
    }

    @Test
    public void importAccount1() {
    }
}