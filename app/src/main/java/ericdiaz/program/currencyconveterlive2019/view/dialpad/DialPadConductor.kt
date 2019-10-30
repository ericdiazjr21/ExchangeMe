package ericdiaz.program.currencyconveterlive2019.view.dialpad

import android.widget.TextView
import androidx.core.widget.TextViewCompat
import ericdiaz.program.currencyconveterlive2019.extensions.currencySymbolFormat
import ericdiaz.program.currencyconveterlive2019.extensions.currencySymbolFormatParser
import ericdiaz.program.currencyconveterlive2019.extensions.decreaseDecimalValue
import ericdiaz.program.currencyconveterlive2019.extensions.increaseDecimalValue

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
                        BASIC_DECIMAL_FORMAT, CURRENCY_SYMBOL_FORMAT -> zeroDecimalFormat()
                        else -> throw IllegalArgumentException("Invalid format mode input")
                    }
            TextViewCompat.setAutoSizeTextTypeWithDefaults(this, TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM)
        }
    }

    override fun onDialPressed(dial: Dial) {

        with(getReceiverViewText()) {

            when (formatMode) {

                BASIC_DECIMAL_FORMAT ->
                    getNewFormattedDecimalTextAmount(this.toDouble(), dial.dialSymbol)
                            .let { receiverTextView.text = it }

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
            when (initialDecimalValue) {
                in 0.0..0.1 -> zeroDecimalFormat()
                else -> initialDecimalValue.decreaseDecimalValue().toString()
            }
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

    private fun getReceiverViewText(): String {
        return receiverTextView.text.toString().trim()
    }

    private fun zeroDecimalFormat() = "0.00"

}
