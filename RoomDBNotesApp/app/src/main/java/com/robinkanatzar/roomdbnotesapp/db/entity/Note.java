package com.robinkanatzar.roomdbnotesapp.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.robinkanatzar.roomdbnotesapp.utils.Constants;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = Constants.NOTES_TABLE_NAME)
public class Note implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String title;
    private String content;
    private Date date;

    public Note(String title, String content) {
        this.content = content;
        this.title = title;
        this.date = new Date(System.currentTimeMillis());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}