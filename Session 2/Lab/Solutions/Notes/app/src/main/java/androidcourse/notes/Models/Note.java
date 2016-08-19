package androidcourse.notes.Models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Juan on 19/08/2016.
 */
public class Note {

    private int id;
    private String title;
    private String content;
    private Date lastModified;
    private String password;
    private static int NEXT_ID = 0;

    public Note(String title, String content) {
        id = NEXT_ID++;
        title = title;
        content = content;
        lastModified = new Date();
    }

    public Note(String title, String content, String password) {
        id = NEXT_ID++;
        title = title;
        content = content;
        password = password;
        lastModified = new Date();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public String getPassword() {
        return password;
    }

    public String dateFormatted() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd - HH:mm");
        return "Last edited on: " + sdf.format(lastModified);
    }

}
