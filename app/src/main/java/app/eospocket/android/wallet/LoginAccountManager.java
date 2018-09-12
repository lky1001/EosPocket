package app.eospocket.android.wallet;

import app.eospocket.android.common.CustomPreference;
import app.eospocket.android.wallet.db.model.EosAccountModel;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import lombok.Getter;
import lombok.Setter;

public class LoginAccountManager {
    private CustomPreference mCustomPreference;

    private final EosAccountModel NULL_MODEL;
    private final BehaviorSubject<EosAccountModel> mChangeAccount;


    public LoginAccountManager(CustomPreference customPreference) {
        mCustomPreference = customPreference;

        NULL_MODEL = new EosAccountModel();
        NULL_MODEL.setId(-1);

        mChangeAccount = BehaviorSubject.createDefault(NULL_MODEL);
    }

    public int getSelectedId() {
        return mCustomPreference.getSelectedEosAccountId();
    }

    public void changeSelectedAccountId(EosAccountModel eosAccountModel) {
        if (mCustomPreference.getSelectedEosAccountId() != eosAccountModel.getId()) {
            mCustomPreference.changeSelectedEosAccountId(eosAccountModel.getId());
            mChangeAccount.onNext(eosAccountModel);
        }
    }

    public Observable<EosAccountModel> getChangeAccount() {
        return mChangeAccount
                .filter(account -> account.getId() != NULL_MODEL.getId());
    }
}
