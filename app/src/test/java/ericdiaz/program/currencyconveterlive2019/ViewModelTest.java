package ericdiaz.program.currencyconveterlive2019;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import ericdiaz.program.currencyconveterlive2019.model.ExchangeRateResponse;
import ericdiaz.program.currencyconveterlive2019.repository.BaseRepository;
import ericdiaz.program.currencyconveterlive2019.repository.ExchangeRateNetworkRepository;
import ericdiaz.program.currencyconveterlive2019.viewmodel.ExchangeRateViewModel;
import ericdiaz.program.currencyconveterlive2019.viewmodel.State;
import io.reactivex.Single;

import static org.mockito.Mockito.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ViewModelTest {

    @Rule // -> allows liveData to work on different thread besides main, must be public!
    public InstantTaskExecutorRule executorRule = new InstantTaskExecutorRule();

    private BaseRepository mockRepository;
    private ExchangeRateViewModel testSubject;
    private Observer<State> mockObserver;


    @Before
    public void setUp() {

        //setup view model with dependencies mocked
        mockRepository = mock(ExchangeRateNetworkRepository.class);
        testSubject = new ExchangeRateViewModel(mockRepository);

        //mock the live data observer
        mockObserver = mock(Observer.class);
        testSubject.getExchangeRateData().observeForever(mockObserver);

    }

    @Test
    public void givenValidInputsWhenGetRatesCalledThenLiveDataObserverEmitsSuccessState() {
        //given
        String date = "2000-10-10";
        String baseCurrency = "USD";
        Single<ExchangeRateResponse> expectedResponse = Single.just(ExchangeRateResponse.EMPTY);

        //when
        when(mockRepository.requestExchangeRates(date, baseCurrency)).thenReturn(expectedResponse);
        testSubject.getRates(date, baseCurrency);

        //then
        verify(mockObserver).onChanged(isA(State.Success.class));
        verify(mockObserver, times(1)).onChanged(isA(State.Success.class));
        verify(mockObserver).onChanged(new State.Success(ExchangeRateResponse.EMPTY));
    }

    @Test
    public void givenInvalidInputsWhenGetRatesCalledThenLiveDataObserverEmitsFailureState() {
        //given
        String date = "2000-10-10";
        String baseCurrency = "USD";
        Single<ExchangeRateResponse> expectedError = Single.error(new Exception());

        //when
        when(mockRepository.requestExchangeRates(date, baseCurrency)).thenReturn(expectedError);
        testSubject.getRates(date, baseCurrency);

        //then
        verify(mockObserver).onChanged(isA(State.Failure.class));
        verify(mockObserver, times(1)).onChanged(isA(State.Failure.class));
        verify(mockObserver, never()).onChanged(isA(State.Success.class));
        verify(mockObserver, never()).onChanged(isA(State.Loading.class));
    }
}
