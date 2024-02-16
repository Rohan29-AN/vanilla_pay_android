package com.vpi.module.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionsStatusResponse {

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

        @SerializedName("reference_VPI")
        @Expose
        private String referenceVPI;
        @SerializedName("panier")
        @Expose
        private String panier;
        @SerializedName("reference")
        @Expose
        private String reference;
        @SerializedName("montant")
        @Expose
        private Double montant;
        @SerializedName("etat")
        @Expose
        private String etat;

        public String getReferenceVPI() {
            return referenceVPI;
        }

        public void setReferenceVPI(String referenceVPI) {
            this.referenceVPI = referenceVPI;
        }

        public String getPanier() {
            return panier;
        }

        public void setPanier(String panier) {
            this.panier = panier;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public Double getMontant() {
            return montant;
        }

        public void setMontant(Double montant) {
            this.montant = montant;
        }

        public String getEtat() {
            return etat;
        }

        public void setEtat(String etat) {
            this.etat = etat;
        }

    }

    @Override
    public String toString() {
        return "TransactionsStatusResponse{" +
                "codeRetour=" + codeRetour +
                ", descRetour='" + descRetour + '\'' +
                ", detailRetour='" + detailRetour + '\'' +
                ", data=" + data +
                '}';
    }
}
