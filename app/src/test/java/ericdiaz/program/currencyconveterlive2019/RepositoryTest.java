package ericdiaz.program.currencyconveterlive2019;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ericdiaz.program.currencyconveterlive2019.model.ExchangeRateResponse;
import ericdiaz.program.currencyconveterlive2019.repository.BaseRepository;
import ericdiaz.program.currencyconveterlive2019.repository.ExchangeRateRepository;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

public class RepositoryTest {

    private BaseRepository exchangeRateRepository;
    private CompositeDisposable compositeDisposable;

    @Before
    public void setUp() {
        exchangeRateRepository = new ExchangeRateRepository();
        compositeDisposable = new CompositeDisposable();
    }

    @Test
    public void testRequestExchangeRatesMethod() {
        //given
        String date = "2000-10-10";
        String baseCurrency = "USD";

        //when
        Single<ExchangeRateResponse> exchangeRateResponse = exchangeRateRepository.requestExchangeRates(date, baseCurrency);

        //then
        compositeDisposable.add(exchangeRateResponse.subscribe(response ->
          Assert.assertEquals("USD", response.getBaseCurrency())));
    }

    @Test
    public void testRequestExchangeRatesMethodWithDate2010_10_10() {
        //given
        String date = "2010-10-10";
        String baseCurrency = "USD";
        double verifiedCanadianDollarValue = 1.0211907164;

        //when
        Single<ExchangeRateResponse> exchangeRateResponse = exchangeRateRepository.requestExchangeRates(date, baseCurrency);

        //then
        compositeDisposable.add(exchangeRateResponse.subscribe(response ->
          Assert.assertEquals(verifiedCanadianDollarValue, response.getRates().getCanadianDollar(), 0)));
    }

    @After
    public void tearDown() {
        compositeDisposable.dispose();
    }
}
