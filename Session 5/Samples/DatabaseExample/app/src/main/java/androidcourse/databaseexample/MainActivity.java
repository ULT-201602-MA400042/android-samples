package androidcourse.databaseexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import androidcourse.databaseexample.DataAccess.ContactDAO;
import androidcourse.databaseexample.Models.Contact;

public class MainActivity extends AppCompatActivity {

    ContactDAO dao;
    Button add;
    Button remove;
    EditText name;
    EditText phone;
    EditText deleteId;
    TextView list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        dao = new ContactDAO(getApplicationContext());
        updateList();
        add = (Button)findViewById(R.id.addBtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ((EditText)findViewById(R.id.name)).getText().toString();
                String phone = ((EditText)findViewById(R.id.phone)).getText().toString();

                dao.addContact(new Contact(name, phone));
                updateList();
            }
        });



    }
    private void updateList(){
        list = (TextView)findViewById(R.id.list);
        ArrayList<Contact> contacts = dao.getAllContacts();
        String values="";
        for (Contact c : contacts)
        {
            values+=c.getName()+", "+c.getPhoneNumber()+"\n";
        }
        list.setText(values);
    }
}
