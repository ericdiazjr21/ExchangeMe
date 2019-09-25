package ericdiaz.program.currencyconveterlive2019.viewmodel

import ericdiaz.program.currencyconveterlive2019.model.ExchangeRateResponse

sealed class State {

    data class Success(val exchangeRateResponse: ExchangeRateResponse) : State()

    data class Failure(val throwable: Throwable) : State()

    object Loading : State()
}