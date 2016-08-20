package androidcourse.listviewexample.Models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DevAdmin on 20/08/2016.
 */
public class Person {
    private String imageName;
    private String name;
    private Date dob;

    public Person(String imageName, String name, Date dob) {
        this.imageName = imageName;
        this.name = name;
        this.dob = dob;
    }
    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public String getDobAsString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");
        String date = sdf.format(dob);
        return date;
    }


    public void setDob(Date dob) {
        this.dob = dob;
    }
}
