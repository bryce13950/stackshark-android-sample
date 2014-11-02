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

    public static void setProjectKey(String projectKey)
    {
        ProjectKey = projectKey;
    }

    public static void init(Context ctx)
    {
        ArrayList<TableStructure> tables = new ArrayList<TableStructure>();
        tables.add(ErrorObject.getStructure());
        tables.add(StackObject.getStructure());
        dbHelper = new DatabaseHelper(ctx, "stack_shark", 1, tables);
        new StackSharkExceptionHandler();
    }

    public static void handleCaughtException(Throwable thrown)
    {
        ErrorObject error = ErrorObject.createError(thrown);
    }
}
