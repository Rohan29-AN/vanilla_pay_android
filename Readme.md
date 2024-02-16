

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
CompletableFuture<TokenResponse> tokenFuture = vanillaPayInternational.generateToken();  
tokenFuture.thenAccept(token -> {  
 // Token generation successful
 }).exceptionally(exception -> {  
 // Token generation failed 
 return null;
 });  
```  

### Initiating Payment

Initiate a payment process using the `initPayment()` method:

```java  
vanillaPayInternational.initPayement(token, montant, reference, panier, notifUrl, redirectUrl);  
initPaymentFuture.thenAccept(response -> {  
 // Payment initialization successful
	 token=response .getData().getToken()
 }).exceptionally(exception -> {  
 // Payment initialization failed return null;
 });  
```  

### Retrieving Transaction Status

Retrieve the status of a transaction using the `getTransactionsStatus()` method:

```java  
vanillaPayInternational.getTransactionsStatus(token, paymentLink);  
statusFuture.thenAccept(status -> {  
 // Transaction status retrieved successfully
	 linkPayment=initPayementResponse.getData().getUrl();
 }).exceptionally(exception -> {  
 // Failed to retrieve transaction status
  return null;
  });  
```  

### Data Authenticity Validation

Validate the authenticity of provided data using the `validateDataAuthenticity()` method:

```java  
Boolean isDataAuthentic = vanillaPayInternational.validateDataAuthenticity(vpi_signature, body);  
```  
---
**Copyright**   © 2024  Vanilla Pay. All rights reserved.  