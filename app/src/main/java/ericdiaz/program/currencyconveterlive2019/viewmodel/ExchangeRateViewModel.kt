package ericdiaz.program.currencyconveterlive2019.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ericdiaz.program.currencyconveterlive2019.extensions.getExchangeValue
import ericdiaz.program.data.repository.ExchangeRateDatabaseRepository
import ericdiaz.program.data.repository.ExchangeRateNetworkRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class ExchangeRateViewModel(private val exchangeRateNetworkRepository: ExchangeRateNetworkRepository,
                            private val exchangeRateDatabaseRepository: ExchangeRateDatabaseRepository) : BaseViewModel() {

    private val exchangeRateData = MutableLiveData<State>()
    private val currencyProfilesData = MutableLiveData<State>()
    lateinit var baseCurrency: String
    lateinit var foreignCurrency: String
    lateinit var baseCurrencyAmount: String

    fun getConversionValue(date: String = "latest") {
        addDisposables(
                exchangeRateDatabaseRepository
                        .requestExchangeRates(date, baseCurrency)

                        .delaySubscription(
                                Completable.fromAction { exchangeRateData.setValue(State.Loading) }
                        )

                        .onErrorResumeNext {
                            exchangeRateNetworkRepository.requestExchangeRates(date, baseCurrency)
                                    .doOnSuccess { exchangeRateResponse ->
                                        exchangeRateResponse?.run {
                                            exchangeRateDatabaseRepository.insertExchangeRateResponse(exchangeRateResponse)
                                        }
                                    }
                        }

                        .map { (_, ratesMap) ->

                            val conversionRate = ratesMap[foreignCurrency]

                            conversionRate?.getExchangeValue(baseCurrencyAmount)
                                    ?: "Error, value is null"
                        }

                        .observeOn(AndroidSchedulers.mainThread())

                        .subscribeBy(
                                onError = { throwable -> exchangeRateData.value = State.Failure(throwable) },
                                onSuccess = { response -> exchangeRateData.value = State.Success(response) }
                        )
        )
    }

    fun getCurrencyProfiles() {
        addDisposables(exchangeRateNetworkRepository
                .requestCurrencyProfiles()
                .doOnSuccess {
                    exchangeRateDatabaseRepository.insertCurrencyProfileMap(it)
                }.subscribeBy(
                        onSuccess = { State.CurrencyProfileSuccess(it) },
                        onError = { State.Failure(it) }
                ))
    }

    fun getExchangeRateData(): LiveData<State> {
        return exchangeRateData
    }

    fun getCurrencyProfilesData(): LiveData<State> {
        return currencyProfilesData
    }
}
