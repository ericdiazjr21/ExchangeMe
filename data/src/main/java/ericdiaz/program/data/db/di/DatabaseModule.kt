package ericdiaz.program.data.db.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.sqldelight.ColumnAdapter
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import ericdiaz.program.data.Database
import ericdiaz.program.data.ExchangeRates
import javax.inject.Singleton

@Module
class DatabaseModule {

    companion object {
        private const val DATA_BASE_NAME = "exchange_rates.db"
    }

    @Provides
    @Singleton
    fun providesExchangeRateDatabase(context: Context,
                                     exchangeRateDatabaseAdapter: ExchangeRates.Adapter): Database {
        val driver: SqlDriver = AndroidSqliteDriver(Database.Schema, context, DATA_BASE_NAME)
        return Database(driver, exchangeRateDatabaseAdapter)
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
    fun providesColumnAdapter(): ColumnAdapter<Map<String, Double>, String> {
        return object : ColumnAdapter<Map<String, Double>, String> {
            override fun decode(databaseValue: String): Map<String, Double> {
                return Gson().fromJson(databaseValue, object : TypeToken<Map<String, Double>>() {}.type)
            }

            override fun encode(value: Map<String, Double>): String {
                return Gson().toJson(value)
            }
        }
    }
}