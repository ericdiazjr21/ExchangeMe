package ericdiaz.program.currencyconveterlive2019.extensions

import java.text.NumberFormat

//TODO : add parsing logic for missing locales.
private val numberFormatter = (NumberFormat.getCurrencyInstance()).apply { this.isParseIntegerOnly = true }

fun Int.cashAppCurrencyFormat(): String {
    return numberFormatter.format(this).replace(Regex("\\.\\d+"), "")

}

fun String.cashAppCurrencyFormatParser(): String {
    return numberFormatter.parse(this).toString()
}