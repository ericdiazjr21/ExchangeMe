package ericdiaz.program.currencyconveterlive2019;

import com.google.common.truth.Truth;
import com.google.common.truth.TruthFailureSubject;
import com.google.common.truth.TruthJUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ericdiaz.program.currencyconveterlive2019.model.ExchangeRateResponse;
import ericdiaz.program.currencyconveterlive2019.network.ExchangeRateService;
import ericdiaz.program.currencyconveterlive2019.repository.BaseRepository;
import ericdiaz.program.currencyconveterlive2019.repository.ExchangeRateNetworkRepository;
import ericdiaz.program.currencyconveterlive2019.viewmodel.ExchangeRateViewModel;
import io.reactivex.Single;

import static com.google.common.truth.Truth.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RepositoryTest {

    private BaseRepository testSubject;
    private ExchangeRateViewModel exchangeRateViewModelMock;
    private ExchangeRateService serviceMock;

    @Before
    public void setUp() {
        serviceMock = mock(ExchangeRateService.class);
        testSubject = new ExchangeRateNetworkRepository(serviceMock);
        exchangeRateViewModelMock = mock(ExchangeRateViewModel.class);
    }

    @Test
    public void testRequestExchangeRatesMethodCallWhenViewModelGetRatesMethodCalled() {
        //given
        String date = "2000-10-10";
        String baseCurrency = "USD";
        Single<ExchangeRateResponse> expectedResponse = Single.just(ExchangeRateResponse.EMPTY);

        //when
        when(serviceMock.getExchangeRates(date, baseCurrency)).thenReturn(expectedResponse);
        exchangeRateViewModelMock.getRates(date, baseCurrency);

        //then
        assertThat(testSubject.requestExchangeRates(date, baseCurrency)).isEqualTo(expectedResponse);
    }

    @After
    public void tearDown() {
    }
}
