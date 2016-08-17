package androidcourse.employeesapp;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.joda.time.LocalDate;
import org.joda.time.Years;

public class MainActivity extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private EditText dob;
    private ImageView calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendar = (ImageView)findViewById(R.id.calendarId);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dob = (EditText)findViewById(R.id.dob_id);
                        dob.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                        LocalDate birthdate = new LocalDate (year, (monthOfYear+1), dayOfMonth);
                        LocalDate now = new LocalDate();
                        Years age = Years.yearsBetween(birthdate, now);
                        TextView ageLbl = (TextView)findViewById(R.id.age_id);
                        ageLbl.setText(age.getYears()+"");


                    }
                };

                DatePickerDialog dpd = new DatePickerDialog(MainActivity.this,listener,2016,8,17);
                dpd.show();


            }
        });




    }
}
