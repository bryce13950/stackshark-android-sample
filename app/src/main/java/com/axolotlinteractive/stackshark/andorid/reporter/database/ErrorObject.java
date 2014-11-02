package com.axolotlinteractive.stackshark.andorid.reporter.database;

import com.axolotlinteractive.android.database.DatabaseObject;

import java.util.HashMap;

/**
 * Created by brycemeyer on 11/2/14.
 */
public class ErrorObject extends DatabaseObject
{
    public int error_id;

    public String message;
    public StackObject[] stackTrace;
    public int offline;
    /**
     * int = whether or not this is synced with the server 0 for no, 1 for yes
     */
    public int synced;
//    public static final String COLUMN_ERROR_ID = "error_id";
//    public static final String COLUMN_
    private ErrorObject(HashMap<String, Object> data)
    {
        super(data);
    }


    @Override
    public void save() {

    }

    public static ErrorObject createError(Throwable thrown)
    {

        return null;
    }
}
