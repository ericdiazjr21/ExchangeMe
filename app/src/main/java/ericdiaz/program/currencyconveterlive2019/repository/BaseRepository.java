package ericdiaz.program.currencyconveterlive2019.repository;

import android.support.annotation.NonNull;

import ericdiaz.program.currencyconveterlive2019.model.ExchangeRateResponse;
import io.reactivex.Single;

public interface BaseRepository {
    Single<ExchangeRateResponse> requestExchangeRates(@NonNull final String date,
                                                      @NonNull final String baseCurrency);
}
