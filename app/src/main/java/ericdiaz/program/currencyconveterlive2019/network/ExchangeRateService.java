package ericdiaz.program.currencyconveterlive2019.network;

import ericdiaz.program.currencyconveterlive2019.model.ExchangeRateResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ExchangeRateService {

    @GET("{date}")
    Single<ExchangeRateResponse> getExchangeRates(@Path("date")
                                                  @Query("base") String base);
}
