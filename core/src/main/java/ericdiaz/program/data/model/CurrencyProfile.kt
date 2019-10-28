package ericdiaz.program.data.model

import com.google.gson.annotations.SerializedName

data class CurrencyProfile(
        @SerializedName("image_url")
        val flag: String,
        @SerializedName("currency_name")
        val currencyName: String,
        val country: String,
        @SerializedName("currency_symbol")
        val currencySymbol :String
) {
    companion object {
        val EMPTY = mutableMapOf<String, CurrencyProfile>().apply {
            this["ASR"] = CurrencyProfile(
                    "ASR Flag",
                    "Asiria Ruble",
                    "Bae's Country",
            "^"
            )

            this["ERC"] = CurrencyProfile(
                    "ERC Flag",
                    "Eric Pound",
                    "My Country",
            "$"
            )
        }
    }
}