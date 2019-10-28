package ericdiaz.program.currencyconveterlive2019.view.dialpad

import android.widget.EditText
import android.widget.TextView
import ericdiaz.program.currencyconveterlive2019.extensions.*

class DialPadConductor(private val receiverTextView: TextView,
                       private val formatMode: Int = BASIC_DECIMAL_FORMAT) : OnDialPressedListener {

    companion object {
        const val BASIC_DECIMAL_FORMAT = 1
        const val CURRENCY_SYMBOL_FORMAT = 2
    }

    init {
        receiverTextView.apply {
            this.text =
                    when (formatMode) {
                        BASIC_DECIMAL_FORMAT -> zeroDecimalFormat()
                        CURRENCY_SYMBOL_FORMAT -> zeroCurrencySymbolFormat()
                        else -> throw IllegalArgumentException("Invalid format mode input")
                    }
            (this as EditText).setSingleLine(true)
        }
    }

    override fun onDialPressed(dial: Dial) {

        with(getReceiverViewText()) {

            when (formatMode) {

                BASIC_DECIMAL_FORMAT ->
                    getNewFormattedDecimalTextAmount(this.toDouble(), dial.dialSymbol)
                            .let {
                                (receiverTextView as EditText).text.clear()
                                receiverTextView.text.insert(0, it)
                            }

                CURRENCY_SYMBOL_FORMAT ->
                    getNewFormattedCurrencyTextAmount(this.currencySymbolFormatParser(), dial.dialSymbol)
                            .let { receiverTextView.text = it }

                else -> throw IllegalArgumentException("Invalid format mode input")
            }
        }
    }

    private fun getNewFormattedDecimalTextAmount(initialDecimalValue: Double,
                                                 dialSymbol: String): String {
        return if (dialSymbol == Dial.Delete.dialSymbol) {
            initialDecimalValue.decreaseDecimalValue().toString()
        } else {
            initialDecimalValue.increaseDecimalValue(dialSymbol).toString()
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
                zeroCurrencySymbolFormat()
            }
        } else {
            when (unformattedAmountText) {
                "" -> zeroCurrencySymbolFormat()

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

    private fun getReceiverViewText(): String {
        return receiverTextView.text.toString().trim()
    }

    private fun zeroCurrencySymbolFormat() = 0.currencySymbolFormat()

    private fun zeroDecimalFormat() = 0.basicDecimalFormat()
}
