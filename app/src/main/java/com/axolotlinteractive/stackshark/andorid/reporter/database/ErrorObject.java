package com.axolotlinteractive.stackshark.andorid.reporter.database;

import com.axolotlinteractive.android.database.DatabaseHelper;
import com.axolotlinteractive.android.database.DatabaseObject;
import com.axolotlinteractive.android.database.TableStructure;
import com.axolotlinteractive.stackshark.andorid.reporter.ErrorReporter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by brycemeyer on 11/2/14.
 */
public class ErrorObject extends DatabaseObject
{
    public String error_id;

    public String type;
    public String message;
    public ArrayList<StackObject> stackTrace;
    public String offline = "0";
    /**
     * int = whether or not this is synced with the server 0 for no, 1 for yes
     */
    public String synced = "0";
    private ErrorObject(HashMap<String, String> data)
    {
        super(data);
    }


    public void setReceived()
    {
        synced = "1";
        ErrorReporter.dbHelper.update("error", "error_id", "" + error_id, "synced", "1");
    }

    public void setFailed()
    {
        //TODO email
        synced = "1";
        ErrorReporter.dbHelper.update("error", "error_id", "" + error_id, "synced", "1");
    }

    public void setNetworkDown()
    {
        offline = "1";
        ErrorReporter.dbHelper.update("error", "error_id", "" + error_id, new String[]{"offline", "synced"}, new String[]{"1", "0"});
    }

    public void loadStack()
    {
        stackTrace = StackObject.fetchStackForError(this);
    }

    public static ErrorObject fetchUnsyncedError()
    {
        List<HashMap<String, String>> rawData = ErrorReporter.dbHelper.getTable("error", "synced = ?", new String[]{"0"});
        ErrorObject error = new ErrorObject(rawData.get(0));
        error.loadStack();
        return error;
    }

    public static ErrorObject createError(Throwable thrown)
    {
        ErrorObject error = new ErrorObject(new HashMap<String, String>());
        error.message = thrown.getMessage();
        error.type = thrown.getClass().getName();
        error.error_id = "" +  ErrorReporter.dbHelper.insert("error", new String[] {"message", "type"}, new String[]{error.message, error.type});
        error.stackTrace = new ArrayList<StackObject>();
        if(thrown.getCause() != null && thrown.getCause().getStackTrace() != null)
            error.stackTrace.addAll(StackObject.createStacks(thrown.getCause().getStackTrace(), error));
        if(thrown.getStackTrace() != null)
            error.stackTrace.addAll(StackObject.createStacks(thrown.getStackTrace(), error));

        return error;
    }

    public static TableStructure getStructure()
    {
        return new TableStructure("error", new String[]{
                "error_id", "message", "offline", "synced", "type"
        },new String[]{
                "INTEGER PRIMARY KEY", "TEXT", "INTEGER DEFAULT 0", "INTEGER DEFAULT 1", "TEXT"
        });
    }
}
