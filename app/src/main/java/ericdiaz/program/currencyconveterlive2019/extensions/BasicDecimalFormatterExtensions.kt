package ericdiaz.program.currencyconveterlive2019.extensions

import java.math.RoundingMode
import java.text.DecimalFormat

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