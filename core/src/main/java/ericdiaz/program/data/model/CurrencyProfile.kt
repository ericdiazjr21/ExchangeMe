package ericdiaz.program.data.model

import com.google.gson.annotations.SerializedName

data class CurrencyProfile(
        @SerializedName("image_url")
        val flag: String,
        @SerializedName("currency_name")
        val currencyName: String,
        val country: String
)