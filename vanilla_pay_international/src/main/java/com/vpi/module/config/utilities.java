package com.vpi.module.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.ExemptionMechanismException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class utilities {

    /**
     * Returns the base URL based on the specified environment.
     *
     * @param env A string that specifies the environment.
     *            Valid values are "PROD" for production or "PREPROD" for pre-production.
     * @return The base URL corresponding to the specified environment.
     * Returns {@code config.BASE_URL} if the environment is "PROD",
     * or {@code config.PREPROD_URL} if the environment is "PREPROD".
     * If the input does not match either value, the method may return null or
     * an undefined behavior (consider handling this case).
     */
    public static String getBaseUrl(String env) {
        return (env.equals("PROD")) ? config.BASE_URL : config.PREPROD_URL;
    }


    /**
     * Hashes the payload using SHA256 algorithm and a secret key.
     *
     * @param {string} secret - The secret key used for hashing.
     * @param {string} payload - The value to be hashed.
     * @returns {string} - The hashed value.
     */
    public static String hashData(String secret, String payload) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(payload.getBytes());
            StringBuilder hash = new StringBuilder();
            for (byte b : bytes) {
                hash.append(String.format("%02x", b));
            }
            return hash.toString().toUpperCase();
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String getIdFromLink(String url) {
        String idValue = "";
        try {
            URI uri = new URI(url);
            // get the query part of the URL
            String query = uri.getQuery();
            if (query != null) {
                // split the query string by '&' to get individual parameters
                String[] queryParams = query.split("&");
                for (String param : queryParams) {
                    // split each parameter by '=' to get the key-value pair
                    String[] keyValue = param.split("=");
                    if (keyValue.length == 2 && keyValue[0].equals("id")) {
                        idValue = keyValue[1];
                    }
                }
            }
            return idValue;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
