package ericdiaz.program.currencyconveterlive2019.repository;

import android.support.annotation.NonNull;

import ericdiaz.program.currencyconveterlive2019.model.ExchangeRateResponse;
import ericdiaz.program.currencyconveterlive2019.network.RetrofitGenerator;
import io.reactivex.Single;

public class ExchangeRateRepository implements BaseRepository {

    @Override
    public Single<ExchangeRateResponse> requestExchangeRates(@NonNull String date, @NonNull String baseCurrency) {
        return RetrofitGenerator.getExchangeRateService().getExchangeRates(date,baseCurrency);
    }
}
