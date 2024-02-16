package com.vpi.module.contract;

import com.vpi.module.model.InitPayementResponse;
import com.vpi.module.model.InitPaymentBody;
import com.vpi.module.model.TokenResponse;
import com.vpi.module.model.TransactionsStatusResponse;

public interface paymentContract {

    interface Model {
        void generateToken(Presenter.OnTokenGeneratedListener listener, String clientId, String clientSecret, String vpiVersion);
        void initializePayment(Presenter.OnInitPaymentListener listener, String token, String vpiVersion, InitPaymentBody body);
        void checkTransactionStatus(Presenter.OnTransactionStatusCheckedListener listener, String token, String vpiVersion, String paymentLink);
    }

    interface Presenter {
        interface OnTokenGeneratedListener {
            void onTokenGenerated(TokenResponse tokenResponse);
            void onFailure(String errorMessage) throws Exception;
        }

        interface OnInitPaymentListener {
            void onInitPayment(InitPayementResponse paymentResponse);
            void onFailure(String errorMessage) throws Exception;
        }

        interface OnTransactionStatusCheckedListener {
            void onTransactionStatusChecked(TransactionsStatusResponse statusResponse);
            void onFailure(String errorMessage) throws Exception;
        }
    }
}
