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

public class MainActivity extends AppCompatActivity {
    private ListView listview;
    private EditText make;
    private EditText model;
    List<Car> cars = new ArrayList<Car>();
    private CarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView)findViewById(R.id.listView);
        adapter = new CarAdapter(MainActivity.this,R.layout.row,cars);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Car c = cars.get(position);
                Intent i = new Intent(MainActivity.this,Main2Activity.class);
                i.putExtra("carObject",c);
                startActivity(i);
            }
        });


    }



    public void Add(View v){
        String mak = ((EditText)(findViewById(R.id.makeInput))).getText().toString();
        String mod = ((EditText)(findViewById(R.id.modelInput))).getText().toString();
        Car c = new Car(mak,mod);
        cars.add(c);
        adapter.notifyDataSetChanged();
    }
}
