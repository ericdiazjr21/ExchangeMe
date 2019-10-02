package ericdiaz.program.currencyconveterlive2019.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import ericdiaz.program.currencyconveterlive2019.R;
import ericdiaz.program.currencyconveterlive2019.view.dialpad.DialPad;
import ericdiaz.program.currencyconveterlive2019.viewmodel.ExchangeRateViewModel;
import ericdiaz.program.currencyconveterlive2019.viewmodel.State;

public class ConversionActivity extends AppCompatActivity {

    @Inject
    ExchangeRateViewModel exchangeRateViewModel;
    private Button convertButton;
    private EditText baseCurrencyAmountEditText;
    private TextView foreignCurrencyTextView;
    private String[] currencyList;
    private String baseCurrency;
    private String foreignCurrency;
    private String baseCurrencyTextAmount;
    private static final String TAG = "ConversionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        initCurrencySpinners();

        foreignCurrencyTextView = findViewById(R.id.foreign_currency_text_view);

        currencyList = getResources().getStringArray(R.array.currency_list);

        DialPad dialPad = findViewById(R.id.dial_pad_view);
        baseCurrencyAmountEditText = findViewById(R.id.base_currency_amount_edit_text);
        dialPad.connectInputTo(baseCurrencyAmountEditText);


        convertButton = findViewById(R.id.convert_button);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                baseCurrencyTextAmount = baseCurrencyAmountEditText.getText().toString();
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime());
                Log.d(TAG, "onClick: " + date + baseCurrency + foreignCurrency + baseCurrencyTextAmount);
                exchangeRateViewModel.getConversionValue(date, baseCurrency, foreignCurrency, baseCurrencyTextAmount);
            }
        });

        exchangeRateViewModel.getExchangeRateData().observe(this, new Observer<State>() {
            @Override
            public void onChanged(State state) {
                if (state instanceof State.Success) {
                    foreignCurrencyTextView.setText(((State.Success) state).getConversionValue());
                } else if (state instanceof State.Failure) {
                    Log.d(TAG, "onChanged: " + ((State.Failure) state).getThrowable().getLocalizedMessage());
                }
            }
        });

    }

    private void initCurrencySpinners() {
        Spinner baseCurrencySpinner = findViewById(R.id.base_currency_spinner);
        Spinner foreignCurrencySpinner = findViewById(R.id.foreign_currency_spinner);

        ArrayAdapter<CharSequence> currencyAdapter = ArrayAdapter
          .createFromResource(
            this,
            R.array.currency_list,
            R.layout.support_simple_spinner_dropdown_item);

        baseCurrencySpinner.setAdapter(currencyAdapter);
        foreignCurrencySpinner.setAdapter(currencyAdapter);

        baseCurrencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                baseCurrency = currencyList[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        foreignCurrencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                foreignCurrency = currencyList[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
