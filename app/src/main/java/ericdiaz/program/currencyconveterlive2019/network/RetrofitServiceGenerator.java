package ericdiaz.program.currencyconveterlive2019.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitServiceGenerator {

    private static final String BASE_URL = "https://api.exchangeratesapi.io";
    private static Retrofit singleInstance;
    private static ExchangeRateService exchangeRateService;

    private static Retrofit getRetrofit() {
        if (singleInstance == null) {
            singleInstance = new Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
              .addConverterFactory(GsonConverterFactory.create())
              .build();
        }
        return singleInstance;
    }

    public static ExchangeRateService getExchangeRateService() {
        if (exchangeRateService == null) {
            exchangeRateService = getRetrofit().create(ExchangeRateService.class);
        }
        return exchangeRateService;
    }

}
