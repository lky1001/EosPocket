package app.eospocket.android.wallet;

import app.eospocket.android.common.CustomPreference;
import app.eospocket.android.common.rxjava.RxJavaSchedulers;
import app.eospocket.android.eos.EosManager;
import app.eospocket.android.wallet.db.model.EosAccountModel;
import app.eospocket.android.wallet.repository.EosAccountRepository;
import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;
import lombok.Getter;
import lombok.Setter;

public class LoginAccountManager {
    private CustomPreference mCustomPreference;

    private final EosAccountModel NULL_MODEL;
    private final BehaviorSubject<EosAccountModel> mChangeAccount;


    public LoginAccountManager(CustomPreference customPreference, EosAccountRepository eosAccountRepository, RxJavaSchedulers rxJavaSchedulers) {
        mCustomPreference = customPreference;

        NULL_MODEL = new EosAccountModel();
        NULL_MODEL.setId(-1);

        mChangeAccount = BehaviorSubject.createDefault(NULL_MODEL);

        if (mCustomPreference.getSelectedEosAccountId() > 0) {
            eosAccountRepository.findOneById(mCustomPreference.getSelectedEosAccountId())
                    .subscribeOn(rxJavaSchedulers.getIo())
                    .observeOn(rxJavaSchedulers.getMainThread())
                    .subscribe(eosAccountModel-> {
                        if (eosAccountModel != null) {
                            mChangeAccount.onNext(eosAccountModel);
                        }
                    }, e-> {

                    });
        }
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
