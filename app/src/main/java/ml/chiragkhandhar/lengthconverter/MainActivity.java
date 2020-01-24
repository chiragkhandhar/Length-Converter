package ml.chiragkhandhar.lengthconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView dynamicLabel, dynamicAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void convert(View v)
    {


    }

    public double mtk(double miles)
    {
        return miles * 1.60934;
    }

    public double ktm(double kms)
    {
        return kms * 0.621371;
    }
}
