package androidcourse.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidcourse.myapplication.Models.Car;

public class Main2Activity extends AppCompatActivity {
    private TextView make;
    private TextView model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent i = getIntent();
        int carId = i.getIntExtra("id",0);
        Toast.makeText(Main2Activity.this, ""+carId, Toast.LENGTH_SHORT).show();
        make= (TextView)findViewById(R.id.makeDetails);
        model= (TextView)findViewById(R.id.ModelDetails);

        //make.setText(c.getMake().toString());
        //model.setText(c.getModel().toString());

    }
}
