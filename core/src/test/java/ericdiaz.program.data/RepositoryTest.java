package ericdiaz.program.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ericdiaz.program.data.network.CurrencyProfileService;
import ericdiaz.program.data.repository.BaseRepository;
import ericdiaz.program.data.repository.ExchangeRateNetworkRepository;
import ericdiaz.program.data.model.ExchangeRateResponse;
import ericdiaz.program.data.network.ExchangeRateService;
import io.reactivex.Single;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RepositoryTest {

    private BaseRepository testSubject;
    private ExchangeRateService exchangeRateServiceMock;
    private CurrencyProfileService currencyProfileServiceMock;

    @Before
    public void setUp() {
        exchangeRateServiceMock = mock(ExchangeRateService.class);
        currencyProfileServiceMock = mock(CurrencyProfileService.class);
        testSubject = new ExchangeRateNetworkRepository(exchangeRateServiceMock, currencyProfileServiceMock);
    }

    @Test
    public void testRequestExchangeRatesMethodCallWhenViewModelGetRatesMethodCalled() {
        //given
        String date = "2000-10-10";
        String baseCurrency = "USD";

        Single<ExchangeRateResponse> expectedResponse = Single.just(ExchangeRateResponse.Companion.getEMPTY());

        //when
        when(exchangeRateServiceMock.getExchangeRates(date, baseCurrency)).thenReturn(expectedResponse);

        //then
        assertThat(testSubject.requestExchangeRates(date, baseCurrency)).isEqualTo(expectedResponse);
    }



    @After
    public void tearDown() {
    }
}
