package com.vpi.module.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vpi.module.config.utilities;
import com.vpi.module.contract.paymentContract;
import com.vpi.module.model.ApiError;
import com.vpi.module.model.InitPayementResponse;
import com.vpi.module.model.InitPaymentBody;
import com.vpi.module.model.TokenResponse;
import com.vpi.module.model.TransactionsStatusResponse;
import com.vpi.module.network.APIClient;
import com.vpi.module.network.APIInterface;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class payment_service implements paymentContract.Model {
    String env = "";

    public payment_service(String env) {
        //Set env to "PROD" if it is null or empty
        this.env = env == null || env.isEmpty() ? "PROD" : env;
    }

    APIInterface apiService = APIClient.getClient(this.env).create(APIInterface.class);

    @Override
    public void generateToken(paymentContract.Presenter.OnTokenGeneratedListener listener, String clientId, String clientSecret, String vpiVersion) {
        Call<TokenResponse> call = this.apiService.getToken(clientId, clientSecret, vpiVersion);
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()) {
                    TokenResponse responseAPI = response.body();
                    if (responseAPI != null) {
                        listener.onTokenGenerated(responseAPI);
                    } else {
                        try {
                            listener.onFailure("Empty response body");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    String errorMessage = "Error: " + response.code() + " " + response.message();
                    try {
                        listener.onFailure(errorMessage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                t.printStackTrace();
                try {
                    listener.onFailure("Network call failed: " + t.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void initializePayment(paymentContract.Presenter.OnInitPaymentListener listener, String token, String vpiVersion, InitPaymentBody body) {
        Call<InitPayementResponse> call = this.apiService.initPayment(token, vpiVersion, body);
        call.enqueue(new Callback<InitPayementResponse>() {
            @Override
            public void onResponse(Call<InitPayementResponse> call, Response<InitPayementResponse> response) {
                if (response.isSuccessful()) {
                    InitPayementResponse responseAPI = response.body();
                    listener.onInitPayment(responseAPI);
                } else {
                    try {
                        listener.onFailure(response.message());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<InitPayementResponse> call, Throwable t) {
                t.printStackTrace();
                try {
                    listener.onFailure(t.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void checkTransactionStatus(paymentContract.Presenter.OnTransactionStatusCheckedListener listener, String token, String vpiVersion, String paymentLink) {
        String idValue = utilities.getIdFromLink(paymentLink);
        Call<TransactionsStatusResponse> call = this.apiService.getTransactionsStatus(token, vpiVersion, idValue);
        call.enqueue(new Callback<TransactionsStatusResponse>() {
            @Override
            public void onResponse(Call<TransactionsStatusResponse> call, Response<TransactionsStatusResponse> response) {
                if (response.isSuccessful()) {
                    TransactionsStatusResponse responseAPI = response.body();
                    listener.onTransactionStatusChecked(responseAPI);
                } else {
                    try {
                        listener.onFailure(response.message());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<TransactionsStatusResponse> call, Throwable t) {
                t.printStackTrace();
                try {
                    listener.onFailure(t.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
