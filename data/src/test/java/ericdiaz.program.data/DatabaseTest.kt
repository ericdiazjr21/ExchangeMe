package ericdiaz.program.data

import com.google.common.truth.Truth.assertThat
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver.Companion.IN_MEMORY
import ericdiaz.program.data.db.di.DatabaseModule
import ericdiaz.program.data.model.ExchangeRateResponse
import org.junit.Test

class DatabaseTest {
    private val inMemorySqlDriver = JdbcSqliteDriver(IN_MEMORY).apply {
        Database.Schema.create(this)
    }

    private val queries = Database(inMemorySqlDriver, DatabaseModule.exchangeRateDatabaseAdapter).exchangeRatesQueries

    @Test
    fun addDummyDataToDB() {
        // given
        val mockResponse = ExchangeRateResponse.EMPTY

        //when
        queries.insertByDate(
                mockResponse.date,
                mockResponse.ratesMap,
                mockResponse.baseCurrency)

        //then
        val result = queries.selectByDate(ExchangeRateResponse.EMPTY.date).executeAsOne()
        assertThat(result.exchangeRates_map).isEqualTo(mockResponse.ratesMap)
    }
}


