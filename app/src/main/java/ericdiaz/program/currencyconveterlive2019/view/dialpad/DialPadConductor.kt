package ericdiaz.program.currencyconveterlive2019.view.dialpad

import android.util.Log
import android.widget.TextView
import ericdiaz.program.currencyconveterlive2019.extensions.cashAppCurrencyFormat
import ericdiaz.program.currencyconveterlive2019.extensions.cashAppCurrencyFormatParser

class DialPadConductor(private val dialPad: DialPad, private val baseCurrencyAmountTextView: TextView) : OnDialPressedListener {


    init {
        setDialPadDialPressedListener()
        baseCurrencyAmountTextView.text = 0.cashAppCurrencyFormat()
    }

    private fun setDialPadDialPressedListener() {
        dialPad.onDialPressedListener = this
    }

    override fun onDialPressed(dial: Dial) {

        val baseCurrencyAmountText = baseCurrencyAmountTextView.text.toString().trim()
        val unformattedBaseCurrency = baseCurrencyAmountText.cashAppCurrencyFormatParser()

        with(unformattedBaseCurrency) {

            Log.d("DialConductor", this)

            val dialSymbol = dial.dialSymbol

            if (dial is Dial.Delete) {

                if (this != "0") {
                    this.let { it.subSequence(0..it.length - 2).toString() }.toInt().cashAppCurrencyFormat()
                } else {
                    0.cashAppCurrencyFormat()
                }
            } else {
                this.let {
                    when (it) {
                        "" -> 0.cashAppCurrencyFormat()
                        "0" -> it.replace("0", dialSymbol).toInt().cashAppCurrencyFormat()
                        else -> (it + dialSymbol).toInt().cashAppCurrencyFormat()
                    }
                }

            }.let {
                baseCurrencyAmountTextView.text = it
            }
        }


    }
}
