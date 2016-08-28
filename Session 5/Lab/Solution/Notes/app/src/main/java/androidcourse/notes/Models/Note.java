package androidcourse.notes.Models;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Juan on 19/08/2016.
 */
public class Note extends RealmObject {

    @PrimaryKey
    private int id;
    @Required
    private String title;
    @Required
    private String content;
    @Required
    private Date lastModified;
    private String password;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        lastModified = new Date();
    }

    public Note(String title, String content, String password) {
        this.title = title;
        this.content = content;
        this.password = password;
        lastModified = new Date();
    }

    public Note() {
    }

    public void setId(int id) {
        this.id = id;
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
