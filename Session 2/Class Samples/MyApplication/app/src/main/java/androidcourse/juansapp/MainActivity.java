package androidcourse.juansapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button colour;
    private Button reset;
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colour = (Button)findViewById(R.id.ChangeColorBtnId);
        //Register the button with the onClick listener
        colour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = (EditText)findViewById(R.id.changeId);
                text.setBackgroundColor(Color.GREEN);
                text.setText("Hello class");
            }
        });
        reset = (Button)findViewById(R.id.ResetId);
        //register a onclick event
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 text.setText("");
                text.setBackgroundColor(Color.WHITE);
            }
        });
    }
}
