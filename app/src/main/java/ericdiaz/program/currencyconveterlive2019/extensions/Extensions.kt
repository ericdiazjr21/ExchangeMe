package ericdiaz.program.currencyconveterlive2019.extensions

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat

// Context
fun Context.vibrate() {
    val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        vibrator.vibrate(500)
    }
}


// String Array
fun Array<String>.reverse(): Array<String> {
    return this.reversedArray()
}


// Double
fun Double.getExchangeValue(baseCurrencyAmount: String): String {
    return (baseCurrencyAmount.toDouble() * this).format().toString()
}


// Conversions
//TODO : add parsing logic for missing locales.
private val numberFormatter = (NumberFormat.getCurrencyInstance()).apply { this.isParseIntegerOnly = true }

fun Int.currencySymbolFormat(): String {
    return numberFormatter.format(this).replace(Regex("\\.\\d+"), "")

}

fun String.currencySymbolFormatParser(): String {
    return numberFormatter.parse(this).toString()
}


// Decimal Formatting
private val basicDecimalFormatDecrease = DecimalFormat("#.##").apply { roundingMode = RoundingMode.FLOOR }
private val basicDecimalFormatIncrease = DecimalFormat("#.##")

fun Int.basicDecimalFormat(): String {
    return basicDecimalFormatDecrease.format(this).toString()
}

fun Double.decreaseDecimalValue(): Double {
    return basicDecimalFormatDecrease.format(this.times(.10)).toDouble()
}

fun Double.increaseDecimalValue(dialValue: String): Double {
    return basicDecimalFormatIncrease
            .format(this.times(10)
                    .plus(dialValue.toDouble().times(.01)))
            .toDouble()
}

fun Double.format(): Double {
    return basicDecimalFormatDecrease.format(this).toDouble()
}

