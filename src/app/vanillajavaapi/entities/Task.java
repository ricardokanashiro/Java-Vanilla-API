package app.vanillajavaapi.entities;

import java.util.Date;

public class Task {

    private static int incrementerID = 0;

    private int id;
    private String title;
    private Date date;
    private boolean done;

    public Task(String title) {
        this.id = ++incrementerID;
        this.title = title;
        this.date = new Date();
        this.done = false;
    }

    public int getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String newTitle) { this.title = newTitle; }

    public Date getDate() { return date; }

    public boolean getDone() { return done; }
    public void setDone(boolean newDone) { this.done = newDone; }
}
