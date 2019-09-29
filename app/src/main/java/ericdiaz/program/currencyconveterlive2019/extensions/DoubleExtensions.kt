package ericdiaz.program.currencyconveterlive2019.extensions

fun Double.getExchangeValue(baseCurrencyAmount: String): String {
    return (baseCurrencyAmount.toDouble() * this).toString()
}