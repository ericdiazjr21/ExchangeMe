package ericdiaz.program.currencyconveterlive2019.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import dagger.android.AndroidInjection;
import ericdiaz.program.currencyconveterlive2019.R;
import ericdiaz.program.currencyconveterlive2019.view.dialpad.DialPad;

public class ConversionActivity extends AppCompatActivity {

    private static final String TAG = "ConversionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        DialPad dialPad = findViewById(R.id.dial_pad_view);
        EditText baseCurrencyAmountEditText = findViewById(R.id.base_currency_amount_edit_text);

        dialPad.observe(baseCurrencyAmountEditText);

        initCurrencySpinners();

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

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        foreignCurrencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
