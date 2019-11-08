package ericdiaz.program.currencyconveterlive2019.view.dialpad

import android.widget.TextView
import ericdiaz.program.currencyconveterlive2019.extensions.currencySymbolFormat
import ericdiaz.program.currencyconveterlive2019.extensions.currencySymbolFormatParser
import ericdiaz.program.currencyconveterlive2019.extensions.decreaseDecimalValue
import ericdiaz.program.currencyconveterlive2019.extensions.increaseDecimalValue

class DialPadConductor(
        private val connectedView: TextView,
        private val dialResponseListener: DialResponseListener,
        private val formatMode: Int = BASIC_DECIMAL_FORMAT) : OnDialPressedListener {

    companion object {
        const val BASIC_DECIMAL_FORMAT = 1
        const val CURRENCY_SYMBOL_FORMAT = 2
    }

    override fun onDialPressed(dial: Dial) {

        if (dial == Dial.Clear) dialResponseListener.onChange("clear")
        else with(connectedView.text.toString()) {

            when (formatMode) {

                BASIC_DECIMAL_FORMAT ->
                    getNewFormattedDecimalTextAmount(this.toDouble(), dial.dialSymbol)
                            .let { dialResponseListener.onChange(it) }

                CURRENCY_SYMBOL_FORMAT ->
                    getNewFormattedCurrencyTextAmount(this.currencySymbolFormatParser(), dial.dialSymbol)
                            .let { dialResponseListener.onChange(it) }

                else -> throw IllegalArgumentException("Invalid format mode input")
            }
        }
    }

    private fun getNewFormattedDecimalTextAmount(initialDecimalValue: Double,
                                                 dialSymbol: String): String {
        return if (dialSymbol == Dial.Delete.dialSymbol) {
            when (initialDecimalValue) {
                in 0.0..0.1 -> zeroDecimalFormat()
                else -> initialDecimalValue.decreaseDecimalValue().toString()
            }
        } else {
            if (initialDecimalValue == 0.0 && dialSymbol == Dial.Zero.dialSymbol) zeroDecimalFormat()
            else initialDecimalValue.increaseDecimalValue(dialSymbol).toString()
        }
    }

    private fun getNewFormattedCurrencyTextAmount(unformattedAmountText: String,
                                                  dialSymbol: String): String {
        return if (dialSymbol == Dial.Delete.dialSymbol) {

            if (unformattedAmountText != "0" && unformattedAmountText.length - 1 != 0) {
                unformattedAmountText.subSequence(0..unformattedAmountText.length - 2)
                        .toString()
                        .toInt()
                        .currencySymbolFormat()
            } else {
                zeroDecimalFormat()
            }
        } else {
            when (unformattedAmountText) {
                "" -> zeroDecimalFormat()

                "0" -> unformattedAmountText.replace("0", dialSymbol)
                        .toInt()
                        .currencySymbolFormat()

                else ->
                    try {
                        (unformattedAmountText + dialSymbol)
                                .toInt()
                                .currencySymbolFormat()
                    } catch (e: NumberFormatException) {
                        Int.MAX_VALUE.currencySymbolFormat()
                    }
            }
        }
    }

    private fun zeroDecimalFormat() = "0.00"

}
