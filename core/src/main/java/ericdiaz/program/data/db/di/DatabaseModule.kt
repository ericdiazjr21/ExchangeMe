package ericdiaz.program.data.db.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.sqldelight.ColumnAdapter
import com.squareup.sqldelight.android.AndroidSqliteDriver
import dagger.Module
import dagger.Provides
import ericdiaz.program.data.CurrencyProfiles
import ericdiaz.program.data.ExchangeRates
import ericdiaz.program.data.db.ExchangeRateDatabase
import ericdiaz.program.data.model.CurrencyProfile
import javax.inject.Singleton

@Module
class DatabaseModule {

    companion object {
        private const val DATA_BASE_NAME = "exchange_rates.db"
    }

    @Provides
    @Singleton
    fun providesExchangeRateDatabase(
            androidSQLiteDriver: AndroidSqliteDriver,
            exchangeRateDatabaseAdapter: ExchangeRates.Adapter,
            currencyProfilesAdapter: CurrencyProfiles.Adapter
    ): ExchangeRateDatabase {

        return ExchangeRateDatabase(androidSQLiteDriver, exchangeRateDatabaseAdapter, currencyProfilesAdapter)
    }

    @Provides
    @Singleton
    fun providesAndroidSqlDriver(context: Context): AndroidSqliteDriver {
        return AndroidSqliteDriver(ExchangeRateDatabase.Schema, context, DATA_BASE_NAME)
    }

    @Provides
    @Singleton
    fun providesExchangeRateDatabaseAdapter(
            columnAdapter: ColumnAdapter<Map<String, Double>, String>
    ): ExchangeRates.Adapter {
        return ExchangeRates.Adapter(columnAdapter)
    }

    @Provides
    @Singleton
    fun providesCurrencyProfilesAdapter(
            columnAdapter: ColumnAdapter<Map<String, CurrencyProfile>, String>
    ): CurrencyProfiles.Adapter {
        return CurrencyProfiles.Adapter(columnAdapter)
    }


    @Provides
    @Singleton
    fun providesExchangeRateColumnAdapter(gson: Gson)
            : ColumnAdapter<Map<String, Double>, String> {

        return object : ColumnAdapter<Map<String, Double>, String> {
            override fun decode(databaseValue: String): Map<String, Double> {
                return gson.fromJson(databaseValue, object : TypeToken<Map<String, Double>>() {}.type)
            }

            override fun encode(value: Map<String, Double>): String {
                return gson.toJson(value)
            }
        }
    }

    @Provides
    @Singleton
    fun providesCurrencyProfileColumnAdapter(gson: Gson): ColumnAdapter<Map<String, CurrencyProfile>, String> {
        return object : ColumnAdapter<Map<String, CurrencyProfile>, String> {
            override fun decode(databaseValue: String): Map<String, CurrencyProfile> {
                return gson.fromJson(databaseValue, object : TypeToken<Map<String, CurrencyProfile>>() {}.type)
            }

            override fun encode(value: Map<String, CurrencyProfile>): String {
                return gson.toJson(value)
            }
        }

    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }
}