package ericdiaz.program.data.network

import ericdiaz.program.data.model.CurrencyProfile
import io.reactivex.Single
import retrofit2.http.GET

interface CurrencyProfileService {

    @GET("ericdiazjr21/c41446b8d723a6ac616df65cd134e647/raw/200e6107a2e6a61022fc9161842dcf088c1c162f/currency_converter_server.txt")
    fun getCurrencyProfileMap(): Single<Map<String, CurrencyProfile>>
}