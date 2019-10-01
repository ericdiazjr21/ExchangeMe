package ericdiaz.program.currencyconveterlive2019.view.dialpad

import android.util.Log
import android.widget.TextView
import java.text.DecimalFormat
import java.text.NumberFormat

class DialPadConductor(private val dialPad: DialPad, private val baseCurrencyAmountTextView: TextView) : OnDialPressedListener {

    private val numberFormatter: DecimalFormat = NumberFormat.getCurrencyInstance() as DecimalFormat

    init {
        setDialPadDialPressedListener()
        numberFormatter.isParseIntegerOnly = true
        baseCurrencyAmountTextView.text = numberFormatter.format(0).replace(Regex("\\.\\d+"), "")
    }

    private fun setDialPadDialPressedListener() {
        dialPad.onDialPressedListener = this
    }

    override fun onDialPressed(dial: Dial) {

        val baseCurrencyAmountText = baseCurrencyAmountTextView.text.toString().trim()
        val unformattedBaseCurrency = numberFormatter.parse(baseCurrencyAmountText).toString()

        with(unformattedBaseCurrency) {

            Log.d("DialConductor", this)

            val dialSymbol = dial.dialSymbol

            if (dial is Dial.Delete) {

                if (this != "0") {
                    numberFormatter.format(this.let { it.subSequence(0..it.length - 2).toString() }.toInt()).replace(Regex("\\.\\d+"), "")
                } else {
                    numberFormatter.format(0).replace(Regex("\\.\\d+"), "")
                }
            } else {
                this.let {
                    when (it) {
                        "" -> numberFormatter.format(0).replace(Regex("\\.\\d+"), "")
                        "0" -> numberFormatter.format(it.replace("0", dialSymbol).toInt()).replace(Regex("\\.\\d+"), "")
                        else -> numberFormatter.format((it + dialSymbol).toInt()).replace(Regex("\\.\\d+"), "")
                    }
                }

            }.let {
                baseCurrencyAmountTextView.text = it.toString()
            }
        }


    }
}
