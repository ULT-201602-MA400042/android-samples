package androidcourse.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import androidcourse.myapplication.Models.Car;

public class Main2Activity extends AppCompatActivity {
    private TextView make;
    private TextView model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent i = getIntent();
        Car c = (Car)i.getSerializableExtra("carObject");
        make= (TextView)findViewById(R.id.makeDetails);
        model= (TextView)findViewById(R.id.ModelDetails);

        make.setText(c.getMake().toString());
        model.setText(c.getModel().toString());

    }
}
