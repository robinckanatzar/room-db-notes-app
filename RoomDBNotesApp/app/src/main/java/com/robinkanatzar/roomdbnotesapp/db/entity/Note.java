package com.robinkanatzar.roomdbnotesapp.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.robinkanatzar.roomdbnotesapp.utils.Constants;

import java.io.Serializable;
import java.util.Date;

/*
Entity: Represents a table within the database.

For each entity, a table is created within the associated Database object to hold the items.

@Ignore
By default, Room creates a column for each field that's defined in the entity. If an entity has
fields that you don't want to persist, you can annotate them using @Ignore

To persist a field, Room must have access to it. You can make a field public, or you can provide a
getter and setter for it.

@PrimaryKey
Each entity must define at least 1 field as a primary key. Even when there is only 1 field, you
still need to annotate the field with the @PrimaryKey annotation

Also, if you want Room to assign automatic IDs to entities, you can set the @PrimaryKey's autoGenerate
property

Composite primary Key:
If the entity has a composite primary key, you can use the primaryKeys property of the @Entity

@Entity(primaryKeys = {"firstName", "lastName"})
public class User {
    public String firstName;
    public String lastName;
    ...
}

By default, Room uses the class name as the database table name. If you want the table to have a
different name, set the tableName property

Room uses the field names as the column names in the database. If you want a column to have a
different name, add the @ColumnInfo annotation to a field
    @ColumnInfo(name = "first_name")
    public String firstName;

Entities can have either an empty constructor (if the corresponding DAO class can access each persisted
field) or a constructor whose parameters contain types and names that match those of the fields in the
entity. Room can also use full or partial constructors, such as a constructor that receives only some
of the fields.

 */
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