package com.axolotlinteractive.stackshark.andorid.reporter;

import com.axolotlinteractive.stackshark.andorid.reporter.database.ErrorObject;

/**
 * Created by brycemeyer on 11/2/14.
 */
public class ErrorReporter
{
    private static String ProjectKey;

    public static void setProjectKey(String projectKey)
    {
        ProjectKey = projectKey;
    }

    public static void init()
    {
        //TODO initialize error handling

    }

    public static void handleCaughtException(Throwable thrown)
    {
        ErrorObject error = ErrorObject.createError(thrown);
    }
}
