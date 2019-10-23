package ericdiaz.program.currencyconveterlive2019.viewmodel

import ericdiaz.program.data.model.CurrencyProfile

sealed class State {

    data class Success(val conversionValue: String) : State()

    data class CurrencyProfileSuccess(val currencyProfileMap: Map<String, CurrencyProfile>) : State()

    data class Failure(val throwable: Throwable) : State()

    object Loading : State()
}