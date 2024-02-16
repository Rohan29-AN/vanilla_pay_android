package com.vpi.module.network;

import com.vpi.module.config.config;
import com.vpi.module.model.InitPayementResponse;
import com.vpi.module.model.InitPaymentBody;
import com.vpi.module.model.TokenResponse;
import com.vpi.module.model.TransactionsStatusResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {

    @Headers({
            "Content-Type: application/json",
    })
    @GET(config.GET_TOKEN_ENDPOINT)
    Call<TokenResponse> getToken(@Header("Client-Id") String ClientID, @Header("Client-Secret") String ClientSECRET, @Header("VPI-Version") String VpiVersion);


    @Headers({
            "Content-Type: application/json",
    })
    @POST(config.INIT_PAYMENT_ENDPOINT)
    Call<InitPayementResponse> initPayment(@Header("Authorization") String token, @Header("VPI-Version") String VpiVersion, @Body InitPaymentBody body);


    @GET(config.TRANSACTION_STATUS_ENDPOINT+"/{id}")
    Call<TransactionsStatusResponse> getTransactionsStatus(@Header("Authorization") String token, @Header("VPI-Version") String VpiVersion, @Path("id") String id);

}
