package com.axolotlinteractive.stackshark.andorid.reporter;

import android.content.Context;

import com.axolotlinteractive.android.database.DatabaseHelper;
import com.axolotlinteractive.android.database.TableStructure;
import com.axolotlinteractive.stackshark.andorid.reporter.database.ErrorObject;
import com.axolotlinteractive.stackshark.andorid.reporter.database.StackObject;

import java.util.ArrayList;

/**
 * Created by brycemeyer on 11/2/14.
 */
public class ErrorReporter
{
    private static String ProjectKey;

    public static DatabaseHelper dbHelper;

    public static Context mContext;

    public static void setProjectKey(String projectKey)
    {
        ProjectKey = projectKey;
    }

    public static void init(Context context)
    {
        mContext = context;

        ArrayList<TableStructure> tables = new ArrayList<TableStructure>();
        tables.add(ErrorObject.getStructure());
        tables.add(StackObject.getStructure());
        dbHelper = new DatabaseHelper(mContext, "stack_shark", 1, tables);

        new StackSharkExceptionHandler();

        ErrorObject oldError = ErrorObject.fetchUnsyncedError();
        if(oldError != null)
            new ErrorReport(oldError);
    }

    public static void handleCaughtException(Throwable thrown)
    {
        ErrorObject error = ErrorObject.createError(thrown);
        new ErrorReport(error);
    }
}
