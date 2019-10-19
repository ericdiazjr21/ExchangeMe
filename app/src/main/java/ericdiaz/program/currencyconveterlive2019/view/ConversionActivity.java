package ericdiaz.program.currencyconveterlive2019.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import ericdiaz.program.currencyconveterlive2019.R;
import ericdiaz.program.currencyconveterlive2019.databinding.ActivityConversionBinding;
import ericdiaz.program.currencyconveterlive2019.di.DaggerConversionActivityComponent;
import ericdiaz.program.currencyconveterlive2019.view.adapter.CurrencyAdapter;
import ericdiaz.program.currencyconveterlive2019.view.dialpad.DialPad;
import ericdiaz.program.currencyconveterlive2019.viewmodel.ExchangeRateViewModel;
import ericdiaz.program.currencyconveterlive2019.viewmodel.State;
import ericdiaz.program.currencyconveterlive2019.viewmodel.di.ViewModelModule;
import ericdiaz.program.data.di.CurrencyConverterApplication;

public class ConversionActivity extends AppCompatActivity {

    private static final String TAG = "ConversionActivity";
    private ActivityConversionBinding activityConversionBinding;

    @Inject
    ExchangeRateViewModel exchangeRateViewModel;
    @Inject
    CurrencyAdapter newCurrencyAdapter;
    @Inject
    String[] currencyList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityConversionBinding = ActivityConversionBinding.inflate(getLayoutInflater());
        setContentView(activityConversionBinding.getRoot());

        DaggerConversionActivityComponent
          .builder()
          .viewModelModule(new ViewModelModule(this))
          .appComponent(((CurrencyConverterApplication) getApplication()).appComponent)
          .build()
          .inject(this);

        connectDialPad();
        initCurrencySpinners();
        setConvertButtonListener();
        observeExchangeRateViewModel();
    }

    private void connectDialPad() {
        DialPad dialPad = findViewById(R.id.dial_pad_view);

        dialPad.connectInputTo(activityConversionBinding.baseCurrencyAmountEditText);
    }

    private void initCurrencySpinners() {
        Spinner baseCurrencySpinner = activityConversionBinding.baseCurrencySpinner;
        Spinner foreignCurrencySpinner = activityConversionBinding.foreignCurrencySpinner;

        baseCurrencySpinner.setAdapter(newCurrencyAdapter);
        foreignCurrencySpinner.setAdapter(newCurrencyAdapter);

        baseCurrencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                exchangeRateViewModel.baseCurrency = currencyList[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        foreignCurrencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                exchangeRateViewModel.foreignCurrency = currencyList[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setConvertButtonListener() {
        activityConversionBinding.convertButton.setOnClickListener(v -> {

            exchangeRateViewModel.baseCurrencyAmount =
              activityConversionBinding.baseCurrencyAmountEditText.getText().toString();

            exchangeRateViewModel.getConversionValue(
              new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime()));
        });
    }

    private void observeExchangeRateViewModel() {
        exchangeRateViewModel.getExchangeRateData().observe(this, state -> {

            if (state instanceof State.Success) {
                activityConversionBinding.foreignCurrencyTextView.setText(((State.Success) state).getConversionValue());
            } else if (state instanceof State.Failure) {
                Log.d(TAG, "onChanged: " + ((State.Failure) state).getThrowable().getLocalizedMessage());
            }
        });
    }
}
