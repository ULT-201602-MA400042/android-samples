package androidcourse.listviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Activity1 extends AppCompatActivity {

    private String[] names = {"Juan", "Josh", "Joselyn", "Martha", "Carl", "Ruby", "Angela",
            "Josh", "Joselyn", "Martha", "Carl", "Ruby", "Angela"};
    private ListView namesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        namesList = (ListView) findViewById(R.id.NamesListId);
        //Create list Adapter using in built simple list item 1
        ArrayAdapter adapter = new ArrayAdapter(Activity1.this,
                android.R.layout.simple_list_item_1,
                names);
        namesList.setAdapter(adapter);
        //Set list Listener
        namesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Activity1.this, names[position], Toast.LENGTH_LONG).show();
            }
        });
        //set on long click listener
        namesList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Activity1.this, id + " - " + names[position], Toast.LENGTH_LONG).show();
                return true;
            }
        });


    }
}
