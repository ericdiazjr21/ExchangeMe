package ericdiaz.program.data.network.di

import dagger.Module
import dagger.Provides
import ericdiaz.program.data.network.CurrencyProfileRetrofit
import ericdiaz.program.data.network.CurrencyProfileService
import ericdiaz.program.data.network.ExchangeRateRetrofit
import ericdiaz.program.data.network.ExchangeRateService
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BASIC
        }
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient()
                .newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .build()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    }

    @Provides
    @Singleton
    fun providesExchangeRateRetrofit(okHttpClient: OkHttpClient,
                                     gsonConverterFactory: GsonConverterFactory,
                                     rxJava2CallAdapterFactory: RxJava2CallAdapterFactory): ExchangeRateRetrofit {
        return ExchangeRateRetrofit(okHttpClient, gsonConverterFactory, rxJava2CallAdapterFactory)
    }

    @Provides
    @Singleton
    fun providesCurrencyProfileRetrofit(okHttpClient: OkHttpClient,
                                        gsonConverterFactory: GsonConverterFactory,
                                        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory): CurrencyProfileRetrofit {
        return CurrencyProfileRetrofit(okHttpClient, gsonConverterFactory, rxJava2CallAdapterFactory)
    }

    @Provides
    @Singleton
    fun providesExchangeRateService(exchangeRateRetrofit: ExchangeRateRetrofit): ExchangeRateService {
        return exchangeRateRetrofit.retrofit.create(ExchangeRateService::class.java)
    }

    @Provides
    @Singleton
    fun providesCurrencyProfileService(currencyProfileRetrofit: CurrencyProfileRetrofit): CurrencyProfileService {
        return currencyProfileRetrofit.retrofit.create(CurrencyProfileService::class.java)
    }

//    @Singleton
//    @Provides
//    fun providesRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
//        return Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
//                .build()
//    }
//
//    @Singleton
//    @Provides
//    fun providesExchangeRateService(retrofit: Retrofit): ExchangeRateService {
//        return retrofit.create(ExchangeRateService::class.java)
//    }
}