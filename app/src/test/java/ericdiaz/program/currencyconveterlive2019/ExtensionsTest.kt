package ericdiaz.program.currencyconveterlive2019

import com.google.common.truth.Truth.assertThat
import ericdiaz.program.currencyconveterlive2019.extensions.getExchangeValue
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
}