package ericdiaz.program.currencyconveterlive2019.view.dialpad

import android.util.Log
import android.widget.TextView
import ericdiaz.program.currencyconveterlive2019.extensions.cashAppCurrencyFormat
import ericdiaz.program.currencyconveterlive2019.extensions.cashAppCurrencyFormatParser

class DialPadConductor(private val receiverTextView: TextView) : OnDialPressedListener {

    init {
        receiverTextView.text = zeroAmount()
    }

    override fun onDialPressed(dial: Dial) {

        val baseCurrencyAmountText = receiverTextView.text.toString().trim()
        val unformattedBaseCurrency = baseCurrencyAmountText.cashAppCurrencyFormatParser()

        with(unformattedBaseCurrency) {

            Log.d("DialConductor", this)

            val dialSymbol = dial.dialSymbol

            if (dial is Dial.Delete) {

                if (this != "0" && this.length - 1 != 0) {
                    this.subSequence(0..this.length - 2)
                            .toString()
                            .toInt()
                            .cashAppCurrencyFormat()
                } else {
                    zeroAmount()
                }
            } else {
                when (this) {
                    "" -> zeroAmount()

                    "0" -> this.replace("0", dialSymbol)
                            .toInt()
                            .cashAppCurrencyFormat()

                    else ->
                        try {
                            (this + dialSymbol)
                                    .toInt()
                                    .cashAppCurrencyFormat()
                        } catch (e: NumberFormatException) {
                            Int.MAX_VALUE.cashAppCurrencyFormat()
                        }
                }
            }.let {
                receiverTextView.text = it
            }
        }
    }

    private fun zeroAmount() = 0.cashAppCurrencyFormat()
}
