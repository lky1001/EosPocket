package app.eospocket.android.di;

import app.eospocket.android.ui.backupmnemoniccode.BackupMnemonicCodeActivity;
import app.eospocket.android.ui.backupmnemoniccode.BackupMnemonicCodeActivityModule;
import app.eospocket.android.ui.createwallet.CreateWalletActivity;
import app.eospocket.android.ui.createwallet.CreateWalletActivityModule;
import app.eospocket.android.ui.importaccount.ImportAccountActivity;
import app.eospocket.android.ui.importaccount.ImportAccountModule;
import app.eospocket.android.ui.intro.IntroActivity;
import app.eospocket.android.ui.intro.IntroActivityModule;
import app.eospocket.android.ui.login.LoginActivity;
import app.eospocket.android.ui.login.LoginActivityModule;
import app.eospocket.android.ui.main.MainActivity;
import app.eospocket.android.ui.main.MainActivityModule;
import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

@Module(includes = AndroidInjectionModule.class)
public abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = IntroActivityModule.class)
    abstract IntroActivity bindIntroActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = CreateWalletActivityModule.class)
    abstract CreateWalletActivity bindCreateWalletActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = BackupMnemonicCodeActivityModule.class)
    abstract BackupMnemonicCodeActivity bindBackupMnemonicCodeActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity bindLoginActivity();

    @PerActivity
    @ContributesAndroidInjector(modules = ImportAccountModule.class)
    abstract ImportAccountActivity bindImportAccountActivity();
}