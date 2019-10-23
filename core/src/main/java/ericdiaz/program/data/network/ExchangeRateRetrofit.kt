package ericdiaz.program.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ExchangeRateRetrofit @Inject constructor(okHttpClient: OkHttpClient,
                                               converterFactory: GsonConverterFactory,
                                               callAdapterFactory: RxJava2CallAdapterFactory) {

    val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(UrlManager.getService(UrlManager.EXCHANGE_RATE_SERVICE))
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build()
}