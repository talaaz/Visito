package dtu.app.visito;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Currency extends AppCompatActivity {

    private EditText amount;
    private Spinner spinner1;
    private TextView ResultEUR1, ResultUSD1, ResultDKK1;

    String[] currencies = {"DKK","USD","EURO", "Select a currency"};
//25d92a2015d15aad09473eee3be3f3d2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        amount = findViewById(R.id.edit);

        spinner1 = findViewById(R.id.spinSearch);

        ResultEUR1 = findViewById(R.id.resultEUR);
        ResultUSD1 =findViewById(R.id.resultUSD);
        ResultDKK1 =findViewById(R.id.resultDKK);


        ArrayAdapter adapter = new ArrayAdapter(Currency.this, android.R.layout.simple_list_item_1){
            @Override
            public int getCount() {
                int count = super.getCount();
                if (count > 0)
                    return count - 1;
                else{
                    return count;
                }
            }
        };

        adapter.addAll(currencies);
        spinner1.setAdapter(adapter);
        spinner1.setSelection(adapter.getCount());


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                amount.addTextChangedListener(new TextWatcher() {

                    public void afterTextChanged(Editable s) {
                    }

                    public void beforeTextChanged(CharSequence s, int start,
                                                  int count, int after) {
                    }

                    public void onTextChanged(CharSequence s, int start,
                                              int before, int count) {
                        double finalAmount;
                        if (amount.getText().length() > 1) {
                            finalAmount = Double.valueOf(amount.getText().toString());

                            if (spinner1.getSelectedItem() == "Select a currency") {
                            }
                            if (spinner1.getSelectedItem() == "DKK") {
                                double ResultEUR = finalAmount / 0.13392;
                                double ResultUSD = finalAmount / 0.15113;

                                ResultDKK1.setVisibility(View.GONE);
                                ResultEUR1.setText("EUR: \n" +Double.toString(ResultEUR));
                                ResultUSD1.setText("USD: \n" +Double.toString(ResultUSD));

                            }
                            if (spinner1.getSelectedItem() == "EURO") {

                                double ResultDKK = finalAmount * 7.46563;
                                double ResultUSD = finalAmount * 1.12847;

                                ResultEUR1.setVisibility(View.GONE);
                                ResultDKK1.setText("DKK: \n" +Double.toString(ResultDKK));
                                ResultUSD1.setText("USD: \n" +Double.toString(ResultUSD));

                            }
                            if (spinner1.getSelectedItem() == "USD") {
                                double ResultUSD = finalAmount * 1;
                                double ResultDKK = finalAmount * 6.61517;
                                double ResultEUR = finalAmount * 0.88605;

                                ResultUSD1.setVisibility(View.GONE);
                                ResultDKK1.setText("DKK: \n" + Double.toString(ResultDKK));
                                ResultEUR1.setText("EUR: \n" +Double.toString(ResultEUR));

                            } else {
                                Toast.makeText(Currency.this, spinner1.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                            }
                        }

                    }
                });

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}