package ericdiaz.program.data.network

import ericdiaz.program.data.model.CurrencyProfile
import io.reactivex.Single
import retrofit2.http.GET

interface CurrencyProfileService {

    @GET("ericdiazjr21/c41446b8d723a6ac616df65cd134e647/raw/c53d5734029c3f14ee2b420453cadda3e6cf0780/currency_converter_server.txt")
    fun getCurrencyProfileMap(): Single<Map<String, CurrencyProfile>>
}