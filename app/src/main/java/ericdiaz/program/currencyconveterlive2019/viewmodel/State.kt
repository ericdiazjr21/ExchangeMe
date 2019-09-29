package ericdiaz.program.currencyconveterlive2019.viewmodel

sealed class State {

    data class Success(val conversionValue: String) : State()

    data class Failure(val throwable: Throwable) : State()

    object Loading : State()
}