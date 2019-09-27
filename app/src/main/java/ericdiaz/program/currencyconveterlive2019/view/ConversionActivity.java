package ericdiaz.program.currencyconveterlive2019.view;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

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

        dialPad.setOnDialPressedListener(dial -> Log.d(TAG, "onCreate: " + dial));
    }
}
