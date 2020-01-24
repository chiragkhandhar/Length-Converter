package ml.chiragkhandhar.lengthconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView ans_text;
    private EditText kms_string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void convert(View v){
        ans_text = findViewById(R.id.ans);
        kms_string = findViewById(R.id.kms);
        int kms = Integer.parseInt(kms_string.getText().toString());
        double ans;

        ans = kms * 0.621371;

        String final_ans = ""+String.format("%.2f", ans)+" miles";
        ans_text.setText(final_ans);

    }
}
