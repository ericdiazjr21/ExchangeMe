package ericdiaz.program.currencyconveterlive2019.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.inject.Inject;

import ericdiaz.program.currencyconveterlive2019.R;
import ericdiaz.program.currencyconveterlive2019.databinding.ActivityConversionBinding;
import ericdiaz.program.currencyconveterlive2019.di.DaggerConversionActivityComponent;
import ericdiaz.program.currencyconveterlive2019.extensions.StringArrayExtensionsKt;
import ericdiaz.program.currencyconveterlive2019.extensions.SystemServiceExtensionsKt;
import ericdiaz.program.currencyconveterlive2019.view.adapter.CurrencyAdapter;
import ericdiaz.program.currencyconveterlive2019.view.dialpad.DialPad;
import ericdiaz.program.currencyconveterlive2019.viewmodel.ExchangeRateViewModel;
import ericdiaz.program.currencyconveterlive2019.viewmodel.State;
import ericdiaz.program.currencyconveterlive2019.viewmodel.di.ViewModelModule;
import ericdiaz.program.data.di.CurrencyConverterApplication;
import ericdiaz.program.data.model.CurrencyProfile;

public class ConversionActivity extends AppCompatActivity {

    private static final String TAG = "ConversionActivity";
    private ActivityConversionBinding activityConversionBinding;

    @Inject
    ExchangeRateViewModel exchangeRateViewModel;
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

        initViews();
        loadCurrencyProfiles();
        setConvertButtonListener();
        observeExchangeRateViewModel();
    }

    private void initViews() {
        DialPad dialPad = findViewById(R.id.dial_pad_view);
        dialPad.connectInputTo((TextView) activityConversionBinding.baseCurrencyAmountTextView);

        TextViewCompat
          .setAutoSizeTextTypeWithDefaults(
            activityConversionBinding.foreignCurrencyTextView,
            TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
    }

    private void loadCurrencyProfiles() {
        exchangeRateViewModel.getCurrencyProfiles();
        exchangeRateViewModel.getCurrencyProfilesData().observe(this, state -> {
            if (state instanceof State.CurrencyProfileSuccess) {
                initCurrencySpinners(((State.CurrencyProfileSuccess) state).getCurrencyProfileMap());
            } else if (state instanceof State.Failure) {
                Log.d(TAG, "onChanged: " + ((State.Failure) state).getThrowable().getLocalizedMessage());
            }
        });
    }


    private void initCurrencySpinners(Map<String, CurrencyProfile> currencyProfileMap) {
        Spinner baseCurrencySpinner = activityConversionBinding.baseCurrencySpinner;
        Spinner foreignCurrencySpinner = activityConversionBinding.foreignCurrencySpinner;

        String[] reversedCurrencyList = StringArrayExtensionsKt.reverse(currencyList);
        CurrencyAdapter baseCurrencyAdapter = new CurrencyAdapter(
          getLayoutInflater(),
          reversedCurrencyList,
          currencyProfileMap);

        CurrencyAdapter foreignCurrencyAdapter = new CurrencyAdapter(
          getLayoutInflater(),
          currencyList,
          currencyProfileMap);

        baseCurrencySpinner.setAdapter(baseCurrencyAdapter);
        foreignCurrencySpinner.setAdapter(foreignCurrencyAdapter);

        baseCurrencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                exchangeRateViewModel.baseCurrency = reversedCurrencyList[position];

                activityConversionBinding.baseCurrencySymbolTextView
                  .setText(
                    getCurrencySymbolText(
                      currencyProfileMap.get(reversedCurrencyList[position]).getCurrencySymbol(),
                      reversedCurrencyList[position]));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        foreignCurrencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                exchangeRateViewModel.foreignCurrency = currencyList[position];
                activityConversionBinding.foreignCurrencySymbolTextView
                  .setText(
                    getCurrencySymbolText(
                      currencyProfileMap.get(currencyList[position]).getCurrencySymbol(),
                      currencyList[position]));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private String getCurrencySymbolText(String currencySymbol, String currencyAbbreviation) {
        return currencySymbol + " - " + currencyAbbreviation;
    }

    private void setConvertButtonListener() {
        activityConversionBinding.convertButton.setOnClickListener(v -> {

            exchangeRateViewModel.baseCurrencyAmount =
              ((TextView) activityConversionBinding.baseCurrencyAmountTextView).getText().toString();

            exchangeRateViewModel.getConversionValue(
              new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime()));
        });
    }

    private void observeExchangeRateViewModel() {
        exchangeRateViewModel.getExchangeRateData().observe(this, state -> {

            if (state instanceof State.Success) {
                activityConversionBinding.foreignCurrencyTextView
                  .setText(((State.Success) state).getConversionValue());
            } else if (state instanceof State.Failure) {
                Log.d(TAG, "onChanged: " + ((State.Failure) state).getThrowable().getLocalizedMessage());
            } else if (state instanceof State.Zero) {
                SystemServiceExtensionsKt.vibrate(this);
            }
        });
    }
}
