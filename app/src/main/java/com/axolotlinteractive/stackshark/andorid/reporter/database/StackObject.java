package com.axolotlinteractive.stackshark.andorid.reporter.database;

import com.axolotlinteractive.android.database.DatabaseObject;

import java.util.HashMap;

/**
 * Created by brycemeyer on 11/2/14.
 */
public class StackObject extends DatabaseObject
{

    public int stack_id;
    public String file_name;
    public String class_name;
    public String method_name;
    public int line_number;
    private StackObject(HashMap<String, Object> data)
    {
        super(data);
    }

    @Override
    public void save()
    {

    }
}
