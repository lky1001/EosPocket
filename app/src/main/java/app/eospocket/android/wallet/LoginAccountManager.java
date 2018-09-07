package app.eospocket.android.wallet;

import app.eospocket.android.common.CustomPreference;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginAccountManager {
    private CustomPreference mCustomPreference;

    private final BehaviorSubject<Integer> mChangeAccountId;
    private int mSelectedAccountId;

    public LoginAccountManager(CustomPreference customPreference) {
        mCustomPreference = customPreference;
        mSelectedAccountId = customPreference.getSelectedEosAccountId();
        mChangeAccountId = BehaviorSubject.createDefault(mSelectedAccountId);
    }

    public void changeSelectedAccountId(int selectedAccountId) {
        mSelectedAccountId = selectedAccountId;
        mCustomPreference.changeSelectedEosAccountId(mSelectedAccountId);
        mChangeAccountId.onNext(mSelectedAccountId);
    }

    public Observable<Integer> getChangeAccountId() {
        return mChangeAccountId;
    }
}
