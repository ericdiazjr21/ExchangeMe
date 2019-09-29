package ericdiaz.program.data.model

import com.google.gson.annotations.SerializedName

data class ExchangeRateResponse(@SerializedName("base")
                                val baseCurrency: String,

                                val ratesMap: Map<String, Double>,

                                val date: String) {
    companion object {
        val EMPTY = ExchangeRateResponse("emptyBaseCurrency", emptyMap(), "emptyDate")
    }
}