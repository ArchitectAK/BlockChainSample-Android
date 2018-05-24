# BlockChainSample-Android
A sample for learning blockchain technology implementation with Android


|<img src="https://github.com/AnkitDroidGit/BlockChainSample-Android/blob/master/art/2.png"  width="200" height="330">| <img src="https://github.com/AnkitDroidGit/BlockChainSample-Android/blob/master/art/1.png"  width="200" height="330">|<img src="https://github.com/AnkitDroidGit/BlockChainSample-Android/blob/master/art/3.png"  width="200" height="330">


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



### Would you like to buy me a cup of coffee?
#### I'd appreciate even your little contribution to my work, it helps me keep this Open Source updated. If this project helped you or your business and if you feel like donating some bucks, you can Paypal me - ankitdroiddeveloper@gmail.com

<a href='https://ko-fi.com/L3L1DM19' target='_blank'><img height='36' style='border:0px;height:36px;' src='https://az743702.vo.msecnd.net/cdn/kofi2.png?v=0' border='0' alt='Buy Me a Coffee at ko-fi.com' /></a>



### Contact - Let's connect to learn together
- [Twitter](https://twitter.com/KumarAnkitRKE)
- [Github](https://github.com/AnkitDroidGit)
- [LinkedIn](https://www.linkedin.com/in/kumarankitkumar/)
- [Facebook](https://www.facebook.com/freeankit)
- [Slack](https://ankitdroid.slack.com)
- [Stackoverflow](https://stackoverflow.com/users/3282461/android)
- [Android App](https://play.google.com/store/apps/details?id=com.freeankit.ankitprofile)


### License

    Copyright 2018 Ankit Kumar
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.




