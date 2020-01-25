package ml.chiragkhandhar.lengthconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView dynamicLabel1, dynamicLabel2, dynamicAns;
    private RadioGroup rg;
    private RadioButton rb, rb1,rb2;
    private EditText et;
    private int radioFlag;

    private static final String TAG = "Radio Check";

    private void fetchComponents()
    {
        dynamicLabel1 = findViewById(R.id.dynamicLabel1);
        dynamicLabel2 = findViewById(R.id.dynamicLabel2);
        dynamicAns = findViewById(R.id.dynamicAns);
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
    }

    public void radioBtnClick(View v)
    {
        boolean checked = ((RadioButton)v).isChecked();

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
            String finalAns;
            switch (radioFlag)
            {
                case 1:
                    miles = Double.parseDouble(et.getText().toString());
                    kms = mtk(miles);
                    finalAns = String.format("%.1f",kms);
                    dynamicAns.setText(finalAns);
                    break;
                case 2:
                    kms = Double.parseDouble(et.getText().toString());
                    miles = ktm(kms);
                    finalAns = String.format("%.1f",miles);
                    dynamicAns.setText(finalAns);
                    break;
            }
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
        et.setText("");
    }
}
