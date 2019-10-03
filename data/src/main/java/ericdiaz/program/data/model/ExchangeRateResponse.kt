package ericdiaz.program.data.model

import com.google.gson.annotations.SerializedName

data class ExchangeRateResponse(@SerializedName("base")
                                val baseCurrency: String,
                                @SerializedName("rates")
                                val ratesMap: Map<String, Double>,

                                val date: String) {
    companion object {
        val DUMMY_RATES = mutableMapOf<String, Double>().apply {
            put("CAD", 1.3253772291)
            put("HKD", 7.8396890718)
            put("ISK", 123.3653406493)
            put("PHP", 51.8847736626)
            put("DKK", 6.8277091907)
            put("HUF", 306.849565615)
            put("CZK", 23.6323731139)
            put("GBP", 0.8118701417)
            put("RON", 4.345221765)
            put("SEK", 9.7860082305)
            put("IDR", 14165.0022862369)
            put("INR", 70.4787379973)
            put("BRL", 4.1602194787)
            put("RUB", 64.3560128029)
            put("HRK", 6.7748513946)
            put("JPY", 108.1024234111)
            put("THB", 30.6447187929)
            put("CHF", 0.9931412894)
            put("EUR", 0.9144947417)
            put("MYR", 4.1880201189)
            put("BGN", 1.7885688157)
            put("TRY", 5.6602652035)
            put("CNY", 7.1282121628)
            put("NOK", 9.0676726109)
            put("NZD", 1.5901234568)
            put("ZAR", 15.0737082762)
            put("USD", 1.00)
            put("MXN", 19.6287151349)
            put("SGD", 1.3808870599)
            put("AUD", 1.4802926383)
            put("ILS", 3.4838591678)
            put("KRW", 1198.3996342021)
            put("PLN", 4.011431184)
        }
        val EMPTY = ExchangeRateResponse("emptyBaseCurrency", DUMMY_RATES, "emptyDate")
    }
}