package ericdiaz.program.currencyconveterlive2019.view.dialpad

import android.util.Log
import android.widget.TextView

class DialPadConductor(private val dialPad: DialPad, private val baseCurrencyAmountTextView: TextView) : OnDialPressedListener {


    init {
        setDialPadDialPressedListener()
    }

    private fun setDialPadDialPressedListener() {
        dialPad.onDialPressedListener = this
    }

    override fun onDialPressed(dial: Dial) {

        val baseCurrencyAmountText = baseCurrencyAmountTextView.text.toString().trim()

        with(baseCurrencyAmountText) {

            Log.d("DialConductor", this)

            val dialSymbol = dial.dialSymbol

            if (dial is Dial.Delete) {

                if (this.length - 1 == 0 || this == "0") {
                    "0"
                } else {
                    this.subSequence(0..this.length - 2).toString()
                }
            } else {
                when (this) {
                    "" -> "0"
                    "0" -> this.replace(Regex("0"), dialSymbol)
                    else -> "${this + dialSymbol} "
                }
            }.let {
                baseCurrencyAmountTextView.text = it
            }
        }
    }

}