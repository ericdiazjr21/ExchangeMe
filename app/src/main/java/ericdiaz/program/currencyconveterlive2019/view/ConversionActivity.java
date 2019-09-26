package ericdiaz.program.currencyconveterlive2019.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import dagger.android.AndroidInjection;
import ericdiaz.program.currencyconveterlive2019.R;

public class ConversionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);
    }
}
