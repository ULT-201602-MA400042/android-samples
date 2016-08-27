package androidcourse.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import androidcourse.myapplication.Adapter.CarAdapter;
import androidcourse.myapplication.Models.Car;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    private ListView listview;
    private EditText make;
    private EditText model;
    private CarAdapter adapter;
    private Realm _context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the Realm configuration
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        // Open the Realm for the UI thread.
        _context = Realm.getInstance(realmConfig);

        adapter = new CarAdapter(MainActivity.this,R.layout.row,getAllCarsFromDb());



        listview = (ListView) findViewById(R.id.listView);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this,Main2Activity.class);
                Car c = getAllCarsFromDb().get(position);
                i.putExtra("id",c.getId());
                startActivity(i);
            }
        });
    }

    public void Add(View v) {
        String mak = ((EditText) (findViewById(R.id.makeInput))).getText().toString();
        String mod = ((EditText) (findViewById(R.id.modelInput))).getText().toString();
        Car c = new Car(mak, mod);
        SaveCarInDb(c);
        adapter.notifyDataSetChanged();
    }

    private void SaveCarInDb(Car carToSave) {
        _context.beginTransaction();
        //save to database
        //get next id
        int id = getNextCarId();
        carToSave.setId(id);
        _context.copyToRealm(carToSave);
        //creating realm object
        //Car c1 = _context.createObject(Car.class);
        _context.commitTransaction();
    }

    private List<Car> getAllCarsFromDb(){
        return _context.where(Car.class).findAll().sort("id");
    }

    private int getNextCarId() {
        int id = 1;
        if (_context.where(Car.class).findAll().size() > 0)
            id = _context.where(Car.class).max("id").intValue() + 1;
        return id;
    }

}
