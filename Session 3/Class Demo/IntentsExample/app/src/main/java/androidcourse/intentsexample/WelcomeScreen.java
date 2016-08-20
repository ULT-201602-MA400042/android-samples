package androidcourse.intentsexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class WelcomeScreen extends AppCompatActivity {
    private TextView welcome;
    private ListView partsListView;
    private ArrayList<String> parts = new ArrayList<String>();
    private Button newItem;
    private int ADD = 1;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome_screen);
        adapter = new ArrayAdapter(WelcomeScreen.this,
                android.R.layout.simple_list_item_1, parts);
        partsListView = (ListView) findViewById(R.id.partsId);
        partsListView.setAdapter(adapter);
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        welcome = (TextView) findViewById(R.id.nameId);
        welcome.setText("Welcome - " + name);
        newItem = (Button) findViewById(R.id.newItemId);
        newItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addItem = new Intent(WelcomeScreen.this, AddItem.class);
                //start activity for result
                startActivityForResult(addItem, ADD);
            }
        });

        partsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                parts.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD) {
            if (resultCode == RESULT_OK) {
                String pcPart = data.getStringExtra("newItem");
                parts.add(pcPart);
                //forces the adapter to refresh the list view
                adapter.notifyDataSetChanged();
            }
        }
    }
}
