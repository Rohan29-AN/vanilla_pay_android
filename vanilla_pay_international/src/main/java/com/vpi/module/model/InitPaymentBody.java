package com.vpi.module.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InitPaymentBody {
    @SerializedName("montant")
    @Expose
    private Double montant;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("panier")
    @Expose
    private String panier;
    @SerializedName("notif_url")
    @Expose
    private String notifUrl;
    @SerializedName("redirect_url")
    @Expose
    private String redirectUrl;

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getPanier() {
        return panier;
    }

    public void setPanier(String panier) {
        this.panier = panier;
    }

    public String getNotifUrl() {
        return notifUrl;
    }

    public void setNotifUrl(String notifUrl) {
        this.notifUrl = notifUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
