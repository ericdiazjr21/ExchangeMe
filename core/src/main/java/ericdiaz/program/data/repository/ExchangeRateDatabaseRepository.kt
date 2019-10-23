package ericdiaz.program.data.repository

import com.squareup.sqldelight.runtime.rx.asObservable
import com.squareup.sqldelight.runtime.rx.mapToList
import com.squareup.sqldelight.runtime.rx.mapToOne
import ericdiaz.program.data.ExchangeRatesQueries
import ericdiaz.program.data.db.ExchangeRateDatabase
import ericdiaz.program.data.model.CurrencyProfile
import ericdiaz.program.data.model.ExchangeRateResponse
import io.reactivex.Observable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

//TODO: address threading in these function
open class ExchangeRateDatabaseRepository @Inject constructor(exchangeRateDatabase: ExchangeRateDatabase) : BaseRepository {

    private val exchangeRatesDatabaseQueries: ExchangeRatesQueries = exchangeRateDatabase.exchangeRatesQueries

    override fun requestExchangeRates(date: String, baseCurrency: String): Single<ExchangeRateResponse> {
        return exchangeRatesDatabaseQueries
                .selectByDateAndBaseCurrency(
                        exchangeRates_date = date,
                        exchangeRates_baseCurrency = baseCurrency,
                        mapper = { cachedDate, cachedRateMap, cachedBaseCurrency ->
                            ExchangeRateResponse(
                                    date = cachedDate,
                                    ratesMap = cachedRateMap,
                                    baseCurrency = cachedBaseCurrency)
                        })
                .asObservable()
                .mapToOne()
                .firstOrError()
                .doOnEvent { data, throwable -> Timber.d(throwable, "From Database - $data") }
    }

    override fun requestCurrencyProfiles(): Single<Map<String, CurrencyProfile>> {
        return exchangeRatesDatabaseQueries
                .selectCurrencyProfileMap()
                .asObservable()
                .mapToOne()
                .firstOrError()
                .doOnEvent { t1, t2 -> Timber.d(t2, "From Database - $t1") }
    }

    fun requestAllExchangeRates(): Observable<List<ExchangeRateResponse>> {
        return exchangeRatesDatabaseQueries.selectAll(
                mapper = { cachedDate, cachedMap, cachedBaseCurrency ->
                    ExchangeRateResponse(
                            date = cachedDate,
                            ratesMap = cachedMap,
                            baseCurrency = cachedBaseCurrency
                    )
                })
                .asObservable()
                .mapToList()
    }

    fun insertExchangeRateResponse(exchangeRateResponse: ExchangeRateResponse) {
        exchangeRatesDatabaseQueries?.run {
            insertExchangeRates(
                    exchangeRateResponse.date,
                    exchangeRateResponse.ratesMap,
                    exchangeRateResponse.baseCurrency
            )
        }

    }

    fun insertCurrencyProfileMap(currencyProfileMap: Map<String, CurrencyProfile>) {
        exchangeRatesDatabaseQueries?.run {
            insertCurrencyProfileMap(currencyProfileMap)
        }
    }

}