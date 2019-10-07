package ericdiaz.program.data

import com.google.common.truth.Truth.assertThat
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver.Companion.IN_MEMORY
import ericdiaz.program.data.model.ExchangeRateResponse
import org.junit.Test

class DatabaseTest {
    private val inMemorySqlDriver = JdbcSqliteDriver(IN_MEMORY).apply {
        Database.Schema.create(this)
    }

    private val queries = Database(inMemorySqlDriver).exchangeRatesQueries

    @Test
    fun addDummyDataToDB() {
        // given
        val exchangeRateMapByteArray = ExchangeRateResponse.EMPTY.ratesMap.toString().toByteArray()

        //when
        queries.insertByDate(ExchangeRateResponse.EMPTY.date, exchangeRateMapByteArray)

        //then

        val result = queries.selectByDate(ExchangeRateResponse.EMPTY.date).executeAsList()

        assertThat(result.size).isEqualTo(1)
        assertThat(result[0].exchangeRates_map).isEqualTo(exchangeRateMapByteArray)
    }
}


