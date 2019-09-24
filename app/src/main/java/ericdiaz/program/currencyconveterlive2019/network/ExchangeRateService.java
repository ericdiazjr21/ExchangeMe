package ericdiaz.program.currencyconveterlive2019.network;

import androidx.annotation.NonNull;

import ericdiaz.program.currencyconveterlive2019.model.ExchangeRateResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ExchangeRateService {

    @GET("{date}")
    Single<ExchangeRateResponse> getExchangeRates(@Path("date") @NonNull final String date,
                                                  @Query("base") @NonNull final String base);
}
