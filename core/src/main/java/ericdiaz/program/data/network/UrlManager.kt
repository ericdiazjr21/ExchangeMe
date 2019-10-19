package ericdiaz.program.data.network

class UrlManager() {

    companion object {
        const val EXCHANGE_RATE_SERVICE = 1
        const val CURRENCY_PROFILE_SERVICE = 2
        private const val EXCHANGE_RATE_BASE_URL = "https://api.exchangeratesapi.io"
        private const val CURRENCY_PROFILE_BASE_URL = "https://gist.githubusercontent.com"

        fun getService(service: Int): String {
            return when (service) {
                1 -> EXCHANGE_RATE_BASE_URL
                2 -> CURRENCY_PROFILE_BASE_URL
                else -> throw IllegalArgumentException("No Base Url assigned for that input")
            }
        }
    }

}