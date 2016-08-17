package androidcourse.employeesapp.Models;

import java.util.Date;

/**
 * Created by DevAdmin on 17/08/2016.
 */
public class Employee {
    private String firstName;
    private String lastName;
    private Date dob;

    public Employee(String firstName, String lastName, Date dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public String getDetails(){
        String details = "Name: "+firstName+" "+lastName+"\nDob: "+dob;
        return details;
    }

}
