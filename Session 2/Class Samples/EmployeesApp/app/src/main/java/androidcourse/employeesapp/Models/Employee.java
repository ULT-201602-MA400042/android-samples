package androidcourse.employeesapp.Models;

import java.util.Date;

/**
 * Created by DevAdmin on 17/08/2016.
 */
public class Employee {
    private String firstName;
    private String lastName;
    private String age;

   public Employee(String firstName, String lastName, String age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getDetails() {
        String details = "Name: " + firstName + " " + lastName + "\nAge: " + age;
        return details;
    }

}
