package com.vpi.module.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenResponse {
    @SerializedName("CodeRetour")
    @Expose
    private Integer codeRetour;
    @SerializedName("DescRetour")
    @Expose
    private String descRetour;
    @SerializedName("DetailRetour")
    @Expose
    private String detailRetour;
    @SerializedName("Data")
    @Expose
    private Data data;

    public Integer getCodeRetour() {
        return codeRetour;
    }

    public void setCodeRetour(Integer codeRetour) {
        this.codeRetour = codeRetour;
    }

    public String getDescRetour() {
        return descRetour;
    }

    public void setDescRetour(String descRetour) {
        this.descRetour = descRetour;
    }

    public String getDetailRetour() {
        return detailRetour;
    }

    public void setDetailRetour(String detailRetour) {
        this.detailRetour = detailRetour;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public class Data {

        @SerializedName("Token")
        @Expose
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }}

    @Override
    public String toString() {
        return "TokenResponse{" +
                "codeRetour=" + codeRetour +
                ", descRetour='" + descRetour + '\'' +
                ", detailRetour='" + detailRetour + '\'' +
                ", data=" + data +
                '}';
    }
}
