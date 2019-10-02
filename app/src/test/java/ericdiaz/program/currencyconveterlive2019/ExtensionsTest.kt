package ericdiaz.program.currencyconveterlive2019

import com.google.common.truth.Truth.assertThat
import ericdiaz.program.currencyconveterlive2019.extensions.decreaseDecimalValue
import ericdiaz.program.currencyconveterlive2019.extensions.getExchangeValue
import ericdiaz.program.currencyconveterlive2019.extensions.increaseDecimalValue
import org.junit.Test

class ExtensionsTest {

    @Test
    fun givenValidInputsWhenGetExchangeValueIsCalledThenAssertExpectedResult() {
        //given
        val exchangeRate = 1.5
        val baseCurrencyAmount = "100"
        val expectedResult = "150.0"

        //when
        val actualResult = exchangeRate.getExchangeValue(baseCurrencyAmount)

        //then
        assertThat(actualResult).matches(expectedResult)
    }


    @Test
    fun basicIncrease2() {
        val inputDouble = 100.00
        val expectedResult = 10000.98

        val actualResult = inputDouble
                        .increaseDecimalValue("9")
                        .increaseDecimalValue("8")

        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun basicIncrease4() {
        val inputDouble = 0.00
        val expectedResult = 98.76

        val actualResult = inputDouble
                .increaseDecimalValue("9")
                .increaseDecimalValue("8")
                .increaseDecimalValue("7")
                .increaseDecimalValue("6")

        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun basicIncrease8() {
        val inputDouble = 0.00
        val expectedResult = 987654.32

        val actualResult = inputDouble
                .increaseDecimalValue("9")
                .increaseDecimalValue("8")
                .increaseDecimalValue("7")
                .increaseDecimalValue("6")
                .increaseDecimalValue("5")
                .increaseDecimalValue("4")
                .increaseDecimalValue("3")
                .increaseDecimalValue("2")

        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun basicTextFormatterExtensions() {
        val inputDouble = 100.00
        val expectedResult = 10.00

        val actualResult = inputDouble.decreaseDecimalValue()

        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun basicDecrease() {
        val inputDouble = 10000.98
        val expectedResult = 1000.09

        val actualResult = inputDouble.decreaseDecimalValue()

        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun basicDecrease1() {
        val inputDouble = 10023.44
        val expectedResult = 1002.34

        val actualResult = inputDouble.decreaseDecimalValue()

        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun basicDecrease2() {
        val inputDouble = 11223.44
        val expectedResult = 112.23

        val actualResult = inputDouble
                .decreaseDecimalValue()
                .decreaseDecimalValue()

        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun basicDecrease5() {
        val inputDouble = 11223.44
        val expectedResult = 0.11

        val actualResult = inputDouble
                .decreaseDecimalValue()
                .decreaseDecimalValue()
                .decreaseDecimalValue()
                .decreaseDecimalValue()
                .decreaseDecimalValue()

        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun basicDecrease7() {
        val inputDouble = 11223.44
        val expectedResult = 0.0

        val actualResult = inputDouble
                .decreaseDecimalValue()
                .decreaseDecimalValue()
                .decreaseDecimalValue()
                .decreaseDecimalValue()
                .decreaseDecimalValue()
                .decreaseDecimalValue()
                .decreaseDecimalValue()

        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun basicDecrease8() {
        val inputDouble = 987654.32
        val expectedResult = 0.0

        val actualResult = inputDouble
                .decreaseDecimalValue()
                .decreaseDecimalValue()
                .decreaseDecimalValue()
                .decreaseDecimalValue()
                .decreaseDecimalValue()
                .decreaseDecimalValue()
                .decreaseDecimalValue()
                .decreaseDecimalValue()

        assertThat(actualResult).isEqualTo(expectedResult)
    }
}