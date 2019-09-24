package ericdiaz.program.currencyconveterlive2019.repository;

import androidx.annotation.NonNull;

import ericdiaz.program.currencyconveterlive2019.model.ExchangeRateResponse;
import ericdiaz.program.currencyconveterlive2019.network.RetrofitServiceGenerator;
import io.reactivex.Single;

public class ExchangeRateRepository implements BaseRepository {

    @Override
    public Single<ExchangeRateResponse> requestExchangeRates(@NonNull final String date,
                                                             @NonNull final String baseCurrency) {
        return RetrofitServiceGenerator.getExchangeRateService().getExchangeRates(date, baseCurrency);
    }
}
