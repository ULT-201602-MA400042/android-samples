package androidcourse.simplelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final Person[] people = {
            new Person("Juan", "male"),
            new Person("Carl", "male"),
            new Person("Josh", "male"),
            new Person("Maria", "female")};
    private ListView list;
    private PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get the ListView from our xml layout
        list = (ListView) findViewById(R.id.listView);
        // Create an Adapter to bind our data to a row template (View), and populate the
        //ListView with the row template
        adapter = new PersonAdapter(this, R.layout.row, people);
        // Context -> Activity (this class is an Activity)
        // int Resource -> The id of a View (row template)  we use android's default view
        // items -> The collection (our data)

        // Set the ListView's Adapter
        list.setAdapter(adapter);

        // Set an OnItemClickListener for the ListView, and use the default anonymous class...
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // When an item (row) is clicked, lets display a Toast
                // First lets get the String value of the item using the position passed to this callback
                Toast.makeText(getApplicationContext(), people[position].getName(), Toast.LENGTH_SHORT).show();
                // Context -> rather than using MainActivity.this (to refer to the context/activity)
                //            we can use getApplicationContext()

            }
        });
    }
}
