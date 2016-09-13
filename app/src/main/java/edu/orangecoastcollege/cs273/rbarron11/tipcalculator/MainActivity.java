package edu.orangecoastcollege.cs273.rbarron11.tipcalculator;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static NumberFormat percent = NumberFormat.getPercentInstance();

    //Associate the controller with the needed views
    private EditText amountEditText;
    private TextView amountTextView;
    private TextView percentTextView;
    private TextView totalTextView;
    private TextView tipTextView;
    private SeekBar percentSeekBar;
    private TextView totalTaxView;

    //Associate the controller with the needed model

    RestaurantBill currentBill = new RestaurantBill();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Associate (connect) the controller with the widgets;
        amountEditText = (EditText) findViewById(R.id.amountEditText);
        amountTextView = (TextView) findViewById(R.id.amountTextView);
        percentTextView = (TextView) findViewById(R.id.percentTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);
        tipTextView = (TextView) findViewById(R.id.tipTextView);
        percentSeekBar = (SeekBar) findViewById(R.id.percentSeekBar);
        totalTaxView = (TextView) findViewById(R.id.totalTaxView);

        //Define a listener for the amountEditText
        amountEditText.addTextChangedListener(amountTextChangedListener);
        //Define a listener for the percentSeekBar (onProgressChanged)\
        percentSeekBar.setOnSeekBarChangeListener(percentChangedListner);

    }

    private TextWatcher amountTextChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //Do nothing
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
        {
        //Try to get the amount from amountEditText
            try{
                double amount;
                if(charSequence.length() == 0)
                    amount = 0.0;
                else
                    amount = Double.parseDouble(charSequence.toString()) / 100.0;
                currentBill.setAmount(amount);
                double taxAmount = amount * 0.08;
                currentBill.setTaxAmount(taxAmount);
            }
            catch(NumberFormatException e)
            {
                amountEditText.setText("");
            }
            //No exception, input is valid:
            //1)set bill amount (amountTextView)
            amountTextView.setText(currency.format(currentBill.getAmount()));
            updateViews();

        }

        @Override
        public void afterTextChanged(Editable editable) {
            //Do nothing
        }
    };

    private SeekBar.OnSeekBarChangeListener percentChangedListner = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            //update the model with the new tip
            currentBill.setTipPercent(i/100.0);
            //update the percentTextView
            percentTextView.setText(percent.format(currentBill.getTipPercent()));

            updateViews();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    public void updateViews()
    {

        tipTextView.setText(currency.format(currentBill.getTipAmount()));
        totalTextView.setText(currency.format(currentBill.getTotalAmount()));
        totalTaxView.setText(currency.format(currentBill.getTaxAmount()));

    }
}
