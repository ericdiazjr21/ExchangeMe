package ericdiaz.program.currencyconveterlive2019.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitServiceGenerator {

    private static Retrofit singleInstance;

    private static Retrofit getRetrofit() {
        if (singleInstance == null) {
            singleInstance = new Retrofit.Builder()
              .baseUrl("https://api.exchangeratesapi.io")
              .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
              .addConverterFactory(GsonConverterFactory.create())
              .build();
        }
        return singleInstance;
    }

    public static ExchangeRateService getExchangeRateService() {
        return getRetrofit().create(ExchangeRateService.class);
    }

}
