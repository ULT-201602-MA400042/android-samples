package androidcourse.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidcourse.myapplication.Models.Car;
import io.realm.Realm;

public class Main2Activity extends AppCompatActivity {
    private EditText make;
    private EditText model;
    private ImageButton update;
    private ImageButton delete;
    private Realm _context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //get realm instance
        _context = Realm.getDefaultInstance();

        Intent i = getIntent();
        int carId = i.getIntExtra("id",0);
        //get car object from DB using id
        final Car c = getCarById(carId);
        make= (EditText) findViewById(R.id.MakeEditId);
        model= (EditText) findViewById(R.id.modelEditId);
        make.setText(c.getMake().toString());
        model.setText(c.getModel().toString());

        update = (ImageButton)findViewById(R.id.imageButton2);
        delete = (ImageButton)findViewById(R.id.imageButton);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCar(c.getId(),make.getText().toString(),model.getText().toString());
                returnToMain();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCar(c.getId());
                returnToMain();
            }
        });
    }

    private Car getCarById(int id){
        Car c = (Car)_context.where(Car.class).equalTo("id",id).findFirst();
        return c;
    }

    private void returnToMain(){
        Intent i = new Intent(Main2Activity.this,MainActivity.class);
        startActivity(i);
    }

    private void updateCar(int id, String make, String model){
        Car c = getCarById(id);
        _context.beginTransaction();
        c.setMake(make);
        c.setModel(model);
        _context.commitTransaction();
    }

    private void deleteCar(int id){
        Car c = getCarById(id);
        _context.beginTransaction();
        c.deleteFromRealm();
        _context.commitTransaction();
    }
}
