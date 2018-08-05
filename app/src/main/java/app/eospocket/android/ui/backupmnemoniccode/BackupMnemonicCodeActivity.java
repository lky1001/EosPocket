package app.eospocket.android.ui.backupmnemoniccode;

import android.os.Bundle;

import javax.inject.Inject;

import app.eospocket.android.common.CommonActivity;

public class BackupMnemonicCodeActivity extends CommonActivity implements BackupMnemonicCodeView {

    @Inject
    BackupMnemonicCodePresenter mBackupMnemonicCodePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
