package androidcourse.intentexamples;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Intent intent = getIntent();
        if (intent.hasExtra("message")) {
            text = (TextView) findViewById(R.id.TextMsg);
            String msg = intent.getStringExtra("message");
            text.setText(msg);
        }

        Button response = (Button) findViewById(R.id.responseBtn);
        response.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String responseTxt = text.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("response", responseTxt);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }
}
