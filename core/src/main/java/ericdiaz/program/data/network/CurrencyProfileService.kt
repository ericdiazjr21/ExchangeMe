package ericdiaz.program.data.network

import ericdiaz.program.data.model.CurrencyProfile
import io.reactivex.Single
import retrofit2.http.GET

interface CurrencyProfileService {

    @GET("ericdiazjr21/c41446b8d723a6ac616df65cd134e647/raw/082f51ae2af30b342dd39c46b6a5be05590ea5d8/currency_converter_server.txt")
    fun getCurrencyProfileMap(): Single<Map<String, CurrencyProfile>>
}