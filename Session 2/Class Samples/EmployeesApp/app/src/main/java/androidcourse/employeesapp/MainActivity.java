package androidcourse.employeesapp;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import androidcourse.employeesapp.Models.Employee;

public class MainActivity extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private TextView age;
    private EditText dob;
    private ImageView calendar;
    private Button add;
    private TextView output;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendar = (ImageView) findViewById(R.id.calendarId);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dob = (EditText) findViewById(R.id.dob_id);
                        dob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        //calculate age of the employee using Joda Time library
                        LocalDate birthdate = new LocalDate(year, (monthOfYear + 1), dayOfMonth);
                        LocalDate now = new LocalDate();
                        Years age = Years.yearsBetween(birthdate, now);
                        TextView ageLbl = (TextView) findViewById(R.id.age_id);
                        //Update Age label
                        ageLbl.setText(age.getYears() + "");
                    }
                };

                DatePickerDialog dpd = new DatePickerDialog(MainActivity.this, listener, 2016, 8, 17);
                dpd.show();
            }
        });

        add = (Button) findViewById(R.id.addBtnId);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = (EditText) findViewById(R.id.first_name_id);
                lastName = (EditText) findViewById(R.id.last_name_id);
                age = (TextView) findViewById(R.id.age_id);
                String fn = firstName.getText().toString();
                String ln = lastName.getText().toString();
                String a = age.getText().toString();
                //Create employee object
                Employee e = new Employee(fn, ln, a);
                output = (TextView) findViewById(R.id.outputId);
                //get existing value for the output
                String temp = output.getText().toString();
                //concatenate new values to existing value
                temp = temp +"\n"+ e.getDetails();
                output.setText(temp);

            }
        });
    }
}
