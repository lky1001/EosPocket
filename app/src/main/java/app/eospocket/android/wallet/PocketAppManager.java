package app.eospocket.android.wallet;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.eospocket.android.common.Constants;
import app.eospocket.android.security.keystore.KeyStore;
import app.eospocket.android.utils.EncryptUtil;
import app.eospocket.android.wallet.db.model.EosAccountModel;
import app.eospocket.android.wallet.db.model.EosAccountTokenModel;
import app.eospocket.android.wallet.repository.EosAccountRepository;
import app.eospocket.android.wallet.repository.EosAccountTokenRepository;
import io.mithrilcoin.eos.crypto.ec.EosPrivateKey;
import io.mithrilcoin.eos.data.remote.model.chain.PackedTransaction;
import io.mithrilcoin.eos.data.remote.model.chain.SignedTransaction;
import io.mithrilcoin.eos.data.remote.model.types.TypeChainId;
import io.reactivex.Single;

@Singleton
public class PocketAppManager {

    private EosAccountRepository mEosAccountRepository;

    private EosAccountTokenRepository mEosAccountTokenRepository;

    private EncryptUtil mEncryptUtil;

    private KeyStore mKeyStore;

    @Inject
    public PocketAppManager(@NonNull EosAccountRepository eosAccountRepository,
            @NonNull EosAccountTokenRepository eosAccountTokenRepository,
            @NonNull EncryptUtil encryptUtil, @NonNull KeyStore keyStore) {
        this.mEosAccountRepository = eosAccountRepository;
        this.mEosAccountTokenRepository = eosAccountTokenRepository;
        this.mEncryptUtil = encryptUtil;
        this.mKeyStore = keyStore;
    }

    public Single<List<EosAccountModel>> findAccount(@NonNull String accountName) {
        return mEosAccountRepository.findAccount(accountName);
    }

    public void insert(@NonNull EosAccountModel eosAccountModel) {
        mEosAccountRepository.insert(eosAccountModel);
    }

    public void update(@NonNull EosAccountModel eosAccountModel) {
        mEosAccountRepository.update(eosAccountModel);
    }

    public void delete(@NonNull EosAccountModel eosAccountModel) {
        mEosAccountRepository.delete(eosAccountModel);
    }

    public void insertAllTokens(@NonNull List<EosAccountTokenModel> eosAccountTokenModels) {
        mEosAccountTokenRepository.insertAll(eosAccountTokenModels);
    }

    public Single<List<EosAccountTokenModel>> getAllTokens(@NonNull String accountName) {
        return mEosAccountTokenRepository.getAllTokens(accountName);
    }

    public EosAccountTokenModel getToken(@NonNull String accountName, @NonNull String contract) {
        return mEosAccountTokenRepository.getToken(accountName, contract);
    }

    public void insertToken(@NonNull EosAccountTokenModel eosAccountTokenModel) {
        mEosAccountTokenRepository.insert(eosAccountTokenModel);
    }

    public void updateToken(@NonNull EosAccountTokenModel tokenModel) {
        mEosAccountTokenRepository.updateToken(tokenModel);
    }

    public Single<PackedTransaction> signAndPackTransaction(final int accountId, final String password, final SignedTransaction txnBeforeSign) {
        return mEosAccountRepository.findOneById(accountId)
                .map(account -> {
                    if (account != null) {
                        // todo decrypt
                        String encPk = account.getPrivateKey();
                        String decPk = mKeyStore.decryptString(encPk, Constants.KEYSTORE_PRIV_KEY_ALIAS);
                        String pk = mEncryptUtil.getDecryptString(decPk, password);

                        if (TextUtils.isEmpty(pk)) {
                            throw new IllegalAccessException("Invalid password.");
                        }

                        EosPrivateKey eosPrivateKey = new EosPrivateKey(pk);
                        SignedTransaction stxn = new SignedTransaction(txnBeforeSign);
                        stxn.sign(eosPrivateKey, new TypeChainId(Constants.EOS_MAINNET_CHAIN_ID));

                        return new PackedTransaction(stxn);
                    }

                    return null;
                });
    }
}
