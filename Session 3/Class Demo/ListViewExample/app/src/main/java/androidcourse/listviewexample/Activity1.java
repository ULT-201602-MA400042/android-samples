package androidcourse.listviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Date;

import androidcourse.listviewexample.Adapters.PersonAdapter;
import androidcourse.listviewexample.Models.Person;

public class Activity1 extends AppCompatActivity {

    private Person[] people = {
            new Person("male","Juan",new Date()),
            new Person("female","Caroline",new Date()),
            new Person("female","Trish",new Date())
    };
    private ListView namesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        namesList = (ListView) findViewById(R.id.NamesListId);
        //Create list Adapter using in built simple list item 1
        PersonAdapter adapter = new PersonAdapter(Activity1.this,R.layout.row_template,people);
        namesList.setAdapter(adapter);

        namesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Activity1.this, people[position].getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
