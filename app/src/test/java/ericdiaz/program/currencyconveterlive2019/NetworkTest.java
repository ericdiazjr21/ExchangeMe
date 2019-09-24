package ericdiaz.program.currencyconveterlive2019;

import org.junit.Before;
import org.junit.Test;

import ericdiaz.program.currencyconveterlive2019.model.ExchangeRateResponse;
import ericdiaz.program.currencyconveterlive2019.network.ExchangeRateService;
import ericdiaz.program.currencyconveterlive2019.network.di.NetworkModule;
import io.reactivex.observers.TestObserver;


public class NetworkTest {

    private ExchangeRateService exchangeRateService;

    @Before
    public void setUp() {
        NetworkModule networkModule = new NetworkModule();
        exchangeRateService =
          networkModule.providesExchangeRateService(
            networkModule.providesRetrofit(
              networkModule.providesOkHttpClient(
                networkModule.providesHttpLoggingInterceptor())));
    }

    @Test
    public void testExchangeRateServiceWithNullInputs() {
        //given
        String nullDate = null;
        String nullBaseCurrency = null;

        //when
        TestObserver<ExchangeRateResponse> responseTestObserver = exchangeRateService.
          getExchangeRates(nullDate, nullBaseCurrency).test();

        //then
        responseTestObserver
          .assertError(IllegalArgumentException.class);
    }

    @Test
    public void testExchangeRateServiceWithNullBaseCurrency() throws InterruptedException {
        //given
        String date = "2010-10-11";
        String nullBaseCurrency = null;

        //when
        TestObserver<ExchangeRateResponse> responseTestObserver =
          exchangeRateService.getExchangeRates(date, nullBaseCurrency).test();

        //then
        responseTestObserver
          .await()
          .assertNoErrors()
          .assertValue(response -> response.getDate().equals(date))
          .assertValue(response -> response.getBaseCurrency().equals("EUR"));
    }

    @Test
    public void testExchangeRateServiceWithNullDate() throws InterruptedException {
        //given
        String nullDate = null;
        String baseCurrency = "USD";

        //when
        TestObserver<ExchangeRateResponse> responseTestObserver = exchangeRateService.
          getExchangeRates(nullDate, baseCurrency).test();

        //then
        responseTestObserver
          .await()
          .assertError(IllegalArgumentException.class);
    }

    @Test
    public void testExchangeRateServiceWithValidInputs() throws InterruptedException {
        //given
        String weekdayDate = "2015-10-12";
        String baseCurrency = "USD";

        //when
        TestObserver<ExchangeRateResponse> responseTestObserver = exchangeRateService.
          getExchangeRates(weekdayDate, baseCurrency).test();

        //then
        responseTestObserver
          .await()
          .assertNoErrors()
          .assertValue(response -> response.getBaseCurrency().equals(baseCurrency))
          .assertValue(response -> response.getDate().equals(weekdayDate));
    }

    @Test
    public void testExchangeRateServiceWithWeekendDate() throws InterruptedException {
        //given
        String weekendDate = "2015-10-10";
        String expectedShiftedDateResponse = "2015-10-09";
        String baseCurrency = "USD";

        //when
        TestObserver<ExchangeRateResponse> responseTestObserver = exchangeRateService.
          getExchangeRates(weekendDate, baseCurrency).test();

        //then
        responseTestObserver
          .await()
          .assertNoErrors()
          .assertValue(response -> response.getBaseCurrency().equals(baseCurrency))
          .assertValue(response -> response.getDate().equals(expectedShiftedDateResponse));
    }


}