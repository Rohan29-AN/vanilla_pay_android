

# Vanilla Pay International Android Library

By using this module, the developer can integrate Vanilla Pay International's payment service into Android apps.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)

## Introduction

This library simplifies the process of integrating Vanilla Pay International payment functionalities into Android applications. The system provides methods for creating tokens, making payments, and retrieving transaction statuses asynchronously.

## Features

- Generate tokens for authentication and authorization.
- Initiate payment processes by generating payment links.
- Retrieve transaction statuses using payment links.
- Validate the authenticity of provided data.

## Requirements

- Android SDK 21 (Android 5.0 Lollipop) or higher.
- Retrofit library for handling network requests.
- Java 8 features support.
- Vanilla Pay International  API credentials (client ID, client secret, key secret).

## Installation

To integrate the VanillaPayInternational Android Library into your Android project, follow these steps:

1. Add the library to your project's dependencies.
> Step 1. Add it in your root build.gradle at the end of repositories:
```gradle  
dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://www.jitpack.io' }
		}
	} 
```  

> Step 2. Add the dependency

```gradle  
 dependencies {
	        implementation 'com.github.Rohan29-AN:vanilla_pay_android:1.0.2'
	}
````  

2. Ensure that your project meets the requirements mentioned above.
3. Obtain VanillaPayInternational API credentials (client ID, client secret, key secret) from the VanillaPayInternational dashboard.

## Usage

### Initialization

To use the VanillaPayInternational library, initialize it with your VanillaPayInternational API credentials:

```java  
VanillaPayInternational vanillaPayInternational = new VanillaPayInternational(clientId, clientSecret, keySecret, vpiVersion);  
```  

### Generating Tokens

Use the `generateToken()` method to generate a token, which remains valid for 20 minutes:

```java  
vanillaPayInternational.generateToken().thenAccept(token -> {  
 // Token generation successful
    token=response .getData().getToken()
 }).exceptionally(exception -> {  
 // Token generation failed 
 return null;
 });  
```  

Response (token):
```json
{
    "CodeRetour": 200,
    "DescRetour": "Génération TOKEN.",
    "DetailRetour": "",
    "Data": {
    	"Token": "Bearer <token>"
 	}
}
```


### Initiating Payment

Initiate a payment process using the `initPayment()` method:

- `token`: The generated token.
- `montant`: The amount of the transaction.
- `devise`: The currency of the transaction.
- `reference`: The pro external reference.
- `panier`: The identifier for the transaction.
- `notifUrl`: URL called when the payment is finished.
- `redirectUrl`: URL to redirect the customer after completing the payment.

```java  
vanillaPayInternational.initPayement(token, montant, devise, reference, panier, notifUrl, redirectUrl).thenAccept(initPayementResponse -> {  
 // Payment initialization successful
	 linkPayment=initPayementResponse.getData().getUrl();
 }).exceptionally(exception -> {  
 // Payment initialization failed return null;
 });  
```  

Response (initPayementResponse):
```json
{
    "CodeRetour": 200,
    "DescRetour": "Génération lien de paiement.",
    "DetailRetour": "",
    "Data": {
        "url": "https://link.com"
    }
}
```

### Retrieving Transaction Status

Retrieve the status of a transaction using the `getTransactionsStatus()` method:

- `token`: The generated token.
- `paymentLink`: The payment link.


```java  
vanillaPayInternational.getTransactionsStatus(token, paymentLink).thenAccept(status -> {  
 // Transaction status retrieved successfully
	 linkPayment=initPayementResponse.getData().getUrl();
 }).exceptionally(exception -> {  
 // Failed to retrieve transaction status
  return null;
  });  
```  

Response (status):
```json
{
    "CodeRetour": 200,
    "DescRetour": "Transaction status.",
    "DetailRetour": "",
    "Data": {
        "reference_VPI": "VPI23011201010101",
	    "panier" : "panier123",
        "reference": "ABC-1234",
        "montant": 58.5,
        "etat": "SUCCESS"
    }
}
```

### Data Authenticity Validation

Validate the authenticity of provided data using the `validateDataAuthenticity()` method:

- `vpi_signature`: The signature extracted from the headers.
- `body`: The data to be hashed and compared against the signature.

```java  
Boolean isDataAuthentic = vanillaPayInternational.validateDataAuthenticity(vpi_signature, body);  
```  
---
**Copyright**   © 2024  Vanilla Pay. All rights reserved.  