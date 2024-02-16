

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
	        implementation 'com.github.Rohan29-AN:vanilla_pay_android:1.0.0'
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
    	"Token": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGllbnRJRCI6ImMzNDJhNWUxZTViZWI3NDIzZjk0OGZiYWJiMzZiYzY4ZDJlYzk4ZTNkNTY0ZjNmODU1ODEzMjBlNGY0MjM1NjIiLCJjbGllbnRTRUNSRVQiOiJkZWMxNGZkYTg3ZTVlOTIyOTNjM2M0NzY2Yzk2MjQyODk4MmY1Njg2NWI0MGEzMTNhMDBkMGJmNzJlZWUwZDQxIiwiZGF0ZUV4cGlyYXRpb24iOiIyMDIzLTEyLTIwVDA5OjA5OjM1LjIyMFoiLCJpYXQiOjE3MDMwNjIxNzV9.nb4qp0EYzCISHj9X3q_h9uKt-Qy3m3_DKmltytOINO8"
 	}
}
```


### Initiating Payment

Initiate a payment process using the `initPayment()` method:

```java  
vanillaPayInternational.initPayement(token, montant, reference, panier, notifUrl, redirectUrl).thenAccept(initPayementResponse -> {  
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
        "url": "https://bo.vanilla-pay.net/webpayment?id=eyJhbGciOiJIUzI1NiJ9.VlBJMjMxMjIxMTA1MjUzOTQ.MThKzznZYh6x9gLoCEt6-c5zED62KXmBjitbp5_dmQE"
    }
}
```

### Retrieving Transaction Status

Retrieve the status of a transaction using the `getTransactionsStatus()` method:

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
    "DescRetour": 'Transaction status.',
    "DetailRetour": '',
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

```java  
Boolean isDataAuthentic = vanillaPayInternational.validateDataAuthenticity(vpi_signature, body);  
```  
---
**Copyright**   © 2024  Vanilla Pay. All rights reserved.  