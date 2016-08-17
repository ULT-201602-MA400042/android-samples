package androidcourse.exercise_1;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;

import androidcourse.exercise_1.Models.Employee;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText phone;
    private EditText email;
    private EditText dob;
    private RadioGroup gender;
    private RadioButton genderSex;
    private TextView details;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        dob = (EditText) findViewById(R.id.dob);
        gender = (RadioGroup) findViewById(R.id.radioGroup);
        Button add = (Button) findViewById(R.id.addId);
        details = (TextView) findViewById(R.id.details);

        ImageView date = (ImageView) findViewById(R.id.calendar);

        //listener for calendar image

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Process to get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                };
                DatePickerDialog dp = new DatePickerDialog(MainActivity.this, listener, mYear, mMonth, mDay);
                dp.show();
            }
        });
        //listener for Add button
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String empName = name.getText().toString();
                String empPhone = phone.getText().toString();
                String empEmail = email.getText().toString();
                String empDob = dob.getText().toString();
                int selectedId = gender.getCheckedRadioButtonId();

                // find the RadioButton by id
                genderSex = (RadioButton) findViewById(selectedId);
                String empGender = genderSex.getText().toString();

                Employee emp = new Employee(empName, empPhone, empEmail, empDob, empGender);
                String text = emp.getDetails();
                details.setText(details.getText() + text);
            }
        });
    }
}
