package com.vpi.module;

import static com.vpi.module.config.utilities.hashData;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.vpi.module.contract.paymentContract;
import com.vpi.module.model.InitPayementResponse;
import com.vpi.module.model.InitPaymentBody;
import com.vpi.module.model.TokenResponse;
import com.vpi.module.model.TransactionsStatusResponse;
import com.vpi.module.service.payment_service;

import java.util.concurrent.CompletableFuture;

import retrofit2.http.Body;

public class VanillaPayInternational implements paymentContract.Presenter{

    private String clientId,clientSecret,keySecret,vpiVersion;
    private paymentContract.Model vanillaService;


    public VanillaPayInternational(String clientId, String clientSecret, String keySecret, String vpiVersion) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.keySecret = keySecret;
        this.vpiVersion = vpiVersion;
        this.vanillaService=new payment_service();
    }

    /**
     * This function is used to generate the token used during transactions, which remains valid for 20 minutes.
     *
     * @return CompletableFuture<TokenResponse> A CompletableFuture that will be completed with the generated token or an exception if token generation fails.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<TokenResponse> generateToken(){
        CompletableFuture<TokenResponse> future = new CompletableFuture<>();
        this.vanillaService.generateToken(new OnTokenGeneratedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTokenGenerated(TokenResponse token) {
                future.complete(token);
            }

            @Override
            public void onFailure(String errorMessage)  {
                future.completeExceptionally(new Exception(errorMessage));
            }
        }, this.getClientID(), this.getClientSECRET(), this.getVpiVersion());

        return future ;
    }


    /**
     * Initiates a payment process by generating a payment link for the customer to access and complete the payment.
     *
     * @param token       The generated token used for authentication and authorization
     * @param montant      The amount of the transaction
     * @param reference   The external reference associated with the transaction
     * @param panier       the identifier for the transaction
     * @param notifUrl    The URL to be called when the payment is finished
     * @param redirectUrl The URL to redirect the customer after completing the payment
     * @return CompletableFuture<InitPayementResponse> A CompletableFuture that will be completed with the response of the payment initialization or an exception if initialization fails.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<InitPayementResponse> initPayement(String token, Double montant, String reference, String panier, String notifUrl, String redirectUrl) {
        CompletableFuture<InitPayementResponse> future = new CompletableFuture<>();
        InitPaymentBody body=new InitPaymentBody();
        body.setMontant(montant);
        body.setReference(reference);
        body.setPanier(panier);
        body.setNotifUrl(notifUrl);
        body.setRedirectUrl(redirectUrl);

        this.vanillaService.initializePayment(new OnInitPaymentListener() {
            @Override
            public void onInitPayment(InitPayementResponse response) {
                future.complete(response);
            }

            @Override
            public void onFailure(String errorMessage){
                future.completeExceptionally(new Exception(errorMessage));
            }
        }, token, this.getVpiVersion(), body);
        return future;
    }

    /**
     * Retrieves the status of a transaction using the provided payment link.
     *
     * @param token      The generated token used for authentication and authorization.
     * @param paymentLink The payment link associated with the transaction.
     * @return CompletableFuture<TransactionsStatusResponse> A CompletableFuture that will be completed with the status of the transaction or an exception if retrieval fails.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<TransactionsStatusResponse> getTransactionsStatus(String token, String paymentLink) {
        CompletableFuture<TransactionsStatusResponse> future = new CompletableFuture<>();

        this.vanillaService.checkTransactionStatus(new OnTransactionStatusCheckedListener() {
            @Override
            public void onTransactionStatusChecked(TransactionsStatusResponse statusResponse) {
                future.complete(statusResponse);
            }

            @Override
            public void onFailure(String errorMessage) {
                future.completeExceptionally(new Exception(errorMessage));
            }
        }, token, this.getVpiVersion(), paymentLink);
        return future;
    }



    /**
     * Validates the authenticity of the provided data by verifying the signature against the hashed body using the ClientSECRET
     * @param vpi_signature : The signature extracted from the headers
     * @param body : The data to be hashed and compared against the signature
     * @returns  Returns true if the data is authentic, otherwise returns false
     */
    public Boolean validateDataAuthenticity(String vpi_signature, String body) {
        // Hash the provided body using the KeySecret
        String hashedData = hashData(getKeySECRET(), body);
        // compare the hashed body with the provided signature
        return hashedData == vpi_signature;
    }

    //getters for constructor
    public String getClientID() {
        return clientId;
    }

    public String getClientSECRET() {
        return clientSecret;
    }

    public String getKeySECRET() {
        return keySecret;
    }

    public String getVpiVersion() {
        return vpiVersion;
    }

}
