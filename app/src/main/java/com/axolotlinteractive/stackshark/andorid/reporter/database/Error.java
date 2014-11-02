package com.axolotlinteractive.stackshark.andorid.reporter.database;

import com.axolotlinteractive.android.database.DatabaseObject;

import java.util.HashMap;

/**
 * Created by brycemeyer on 11/2/14.
 */
public class Error extends DatabaseObject
{
    public int error_id;

    public String message;
    public Stack[] stackTrace;
    public int offline;
    public int synced;
//    public static final String COLUMN_ERROR_ID = "error_id";
//    public static final String COLUMN_
    private Error(HashMap<String, Object> data)
    {
        super(data);
    }


    @Override
    public void save() {

    }

    public static Error createError(Throwable thrown)
    {

    }
}
