# BlockChainSample-Android
A sample for learning blockchain technology implementation with Android


|<img src="https://github.com/AnkitDroidGit/BlockChainSample-Android/blob/master/art/1.png"  width="400" height="600">| <img src="https://github.com/AnkitDroidGit/BlockChainSample-Android/blob/master/art/2.png"  width="400" height="600">|<img src="https://github.com/AnkitDroidGit/BlockChainSample-Android/blob/master/art/3.png"  width="400" height="600">


# Service Information
Instruction of the installing API service: https://github.com/blockchain/service-my-wallet-v3#installation

In class `Constants` type your API key:
``` */
   Constants file
   
   const val BASE_URL = "http://192.168.88.100:3000/" //Your server
   const val API_KEY = "5rtyughjbtyughbjnbtyuhbj" //Your API KEY
  
```

App using <a href="https://github.com/blockchain/api-v1-client-java">java</a> library from Blockchain.

In `build.gradle`:
```
    dependencies {
        implementation 'info.blockchain:api:1.1.4'
    }
```

Example use methods from it library .All methods in main thread. For background thread used Rx:

#### Create Wallet
```
    Observable<CreateWalletResponse> createWallet(String email, String password) {
        return Observable.fromCallable(() -> CreateWallet.create(
                    Constants.BASE_URL,
                    password,
                    Constants.API_KEY,
                    null, null,
                    email))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }
```

#### Exchange Rates
```
    Observable<Map<String, Currency>> getExchangeRates() {
        return Observable.fromCallable(() -> ExchangeRates.getTicker(Constants.API_KEY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());
    }
```

##For get access need API key.
* Receive Payments API V2: https://api.blockchain.info/v2/apikey/request
* Global key (available Create Wallet,... etc): https://blockchain.info/ru/api/api_create_code





