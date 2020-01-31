package ml.chiragkhandhar.lengthconverter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView dynamicLabel1, dynamicLabel2, dynamicAns, historyBox;
    private RadioGroup rg;
    private RadioButton rb, rb1,rb2;
    private EditText et;
    private int radioFlag;
    private String sb = new String("");
    String finalAns;

    private static final String TAG = "Radio Check";



    private void fetchComponents()
    {
        dynamicLabel1 = findViewById(R.id.dynamicLabel1);
        dynamicLabel2 = findViewById(R.id.dynamicLabel2);
        dynamicAns = findViewById(R.id.dynamicAns);
        historyBox = findViewById(R.id.historyBox);
        et = findViewById(R.id.inputValue);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fetchComponents();
        radioFlag = 1;                          // Default value
        dynamicLabel1.setText(getString(R.string.miles));
        dynamicLabel2.setText(getString(R.string.kms));
        et.setHint(getString(R.string.em));
        // This next line is required for proper scrolling behavior
        historyBox.setMovementMethod(new ScrollingMovementMethod());
    }

    public void radioBtnClick(View v)
    {
        boolean checked = ((RadioButton)v).isChecked();
        dynamicAns.setText("");

        switch(v.getId()) {
            case R.id.mtkBtn:
                if (checked)
                    dynamicLabel1.setText(getString(R.string.miles));
                    dynamicLabel2.setText(getString(R.string.kms));
                    et.setHint(getString(R.string.em));
                    radioFlag = 1;
                    break;
            case R.id.ktmBtn:
                if (checked)
                    dynamicLabel2.setText(getString(R.string.miles));
                    dynamicLabel1.setText(getString(R.string.kms));
                    et.setHint(getString(R.string.ek));
                    radioFlag = 2;
                    break;
        }
    }

    public void convert(View v)
    {
        if (et.getText().toString().equals(""))
        {
            et.setError("Required");
        }
        else
        {
            double miles, kms;

            String temp = "";
            switch (radioFlag)
            {
                case 1:
                    miles = Double.parseDouble(et.getText().toString());
                    kms = mtk(miles);
                    finalAns = String.format("%.1f",kms);
                    temp = Double.toString(miles) + " Mi" + " ==> "+finalAns+" Km\n";
                    dynamicAns.setText(finalAns);
                    break;
                case 2:
                    kms = Double.parseDouble(et.getText().toString());
                    miles = ktm(kms);
                    finalAns = String.format("%.1f",miles);
                    temp = Double.toString(kms) + " Km" + " ==> "+finalAns+" Mi\n";
                    dynamicAns.setText(finalAns);
                    break;
            }

            sb = temp + sb;
            et.setText("");
            historyBox.setText(sb.toString());

        }

    }

    public double mtk(double miles)
    {
        return miles * 1.60934;
    }

    public double ktm(double kms)
    {
        return kms * 0.621371;
    }

    public void clear(View view)
    {
        dynamicAns.setText("");
        finalAns="";
        et.setText("");
    }

    public void clearHist(View view)
    {
        sb = "";
        historyBox.setText("");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putString("histBox",sb);
        outState.putString("finalAns",finalAns);
        // Call this Last
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        // Call this First
        super.onRestoreInstanceState(savedInstanceState);

        finalAns = savedInstanceState.getString("finalAns");
        sb = savedInstanceState.getString("histBox");
        historyBox.setText(sb);
        dynamicAns.setText(finalAns);

    }
}
