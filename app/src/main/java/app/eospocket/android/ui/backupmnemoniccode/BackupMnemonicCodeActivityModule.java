package app.eospocket.android.ui.backupmnemoniccode;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class BackupMnemonicCodeActivityModule {

    @Binds
    public abstract BackupMnemonicCodeView view(BackupMnemonicCodeActivity backupMnemonicCodeActivity);

    @Provides
    static BackupMnemonicCodePresenter provideBackupMnemonicCodePresenter(BackupMnemonicCodeView backupMnemonicCodeView) {
        return new BackupMnemonicCodePresenter(backupMnemonicCodeView);
    }
}
