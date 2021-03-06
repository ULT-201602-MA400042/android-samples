package androidcourse.notes.Models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Juan on 19/08/2016.
 */
public class Note implements Serializable {

    private int id;
    private String title;
    private String content;
    private Date lastModified;
    private String password;
    private static int NEXT_ID = 0;

    public Note(String title, String content) {
        id = NEXT_ID++;
        this.title = title;
        this.content = content;
        lastModified = new Date();
    }

    public Note(String title, String content, String password) {
        id = NEXT_ID++;
        this.title = title;
        this.content = content;
        this.password = password;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String dateFormatted() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd - HH:mm");
        return "Last edited on: " + sdf.format(lastModified);
    }

}
