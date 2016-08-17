package androidcourse.exercise_1.Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Employee {
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
    private static int NEXT_ID = 1;
    private int id;
    private String name;
    private String phone;
    private String email;
    private Date dob;
    private String gender;

    public Employee(String name, String phone, String email, String dob, String gender) {
        this.id = NEXT_ID;
        NEXT_ID++;
        this.name = name;
        this.phone = phone;
        this.email = email;
        try {
            this.dob = sdf.parse(dob);
        } catch (ParseException error) {
        }
        this.gender = gender;
    }

    public String getDetails() {
        String details = "Id: " + id;
        details += "\nName: " + name;
        details += "\nPhone: " + phone;
        details += "\nEmail: " + email;
        details += String.format("\nDate Of Birth: " + sdf.format(dob));
        details += "\nGender: " + gender + "\n\n";
        return details;
    }

}
