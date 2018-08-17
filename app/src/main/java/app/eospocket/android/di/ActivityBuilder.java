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
import app.eospocket.android.ui.main.action.ActionModule;
import app.eospocket.android.ui.main.setting.SettingModule;
import app.eospocket.android.ui.main.stake.StakeModule;
import app.eospocket.android.ui.main.token.TokenModule;
import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;

@Module(includes = AndroidInjectionModule.class)
public abstract class ActivityBuilder {

    @ActivityScoped
    @ContributesAndroidInjector(modules = IntroActivityModule.class)
    abstract IntroActivity bindIntroActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {
            MainActivityModule.class,
            TokenModule.class,
            StakeModule.class,
            ActionModule.class,
            SettingModule.class
    })
    abstract MainActivity bindMainActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = CreateWalletActivityModule.class)
    abstract CreateWalletActivity bindCreateWalletActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = BackupMnemonicCodeActivityModule.class)
    abstract BackupMnemonicCodeActivity bindBackupMnemonicCodeActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity bindLoginActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = ImportAccountModule.class)
    abstract ImportAccountActivity bindImportAccountActivity();
}