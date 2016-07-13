package edu.uoregon.cnf.thedecider;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

/**
 * Created by Chris on 7/11/2016.
 */
public class TheDeciderDB {

    // Database Constants
    public static final String DB_NAME = "thedecider.db";
    public static final int DB_VERSION = 1;

    // Table constants
    public static final String DECISIONS_TABLE = "decisions";
    public static final String CHOICES_TABLE = "choices";

    public static final String DECISION_ID = "decisionID";
    public static final int DECISION_ID_COL = 0;

    public static final String DECISION_DESCRIPTION = "description";
    public static final int DECISION_DESCRIPTION_COL = 1;

    public static final String DECISION_NUMCHOICES = "numchoices";
    public static final int DECISION_NUMCHOICES_COL = 2;

    public static final String CHOICE_ID = "choiceID";
    public static final int CHOICE_ID_COL = 0;

    public static final String CHOICE_DECISION_ID = "decisionID";
    public static final int CHOICE_DECISION_ID_COL = 1;

    public static final String CHOICE_DESCRIPTION = "description";
    public static final int CHOICE_DESCRIPTION_COL = 2;

    public static final String CREATE_DECISIONS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + DECISIONS_TABLE + " (" +
                    DECISION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DECISION_DESCRIPTION + " TEXT NOT NULL, " +
                    DECISION_NUMCHOICES + " INTEGER NOT NULL);";

    public static final String CREATE_CHOICES_TABLE =
            "CREATE TABLE IF NOT EXISTS " + CHOICES_TABLE + " (" +
                    CHOICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CHOICE_DECISION_ID + " INTEGER NOT NULL, " +
                    CHOICE_DESCRIPTION + " TEXT NOT NULL);";

    public static final String DROP_DECISIONS_TABLE =
            "DROP TABLE IF EXISTS " + DECISIONS_TABLE;

    public static final String DROP_CHOICES_TABLE =
            "DROP TABLE IF EXISTS " + CHOICES_TABLE;



    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
        {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DECISIONS_TABLE);
            db.execSQL(CREATE_CHOICES_TABLE);
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL(TheDeciderDB.DROP_DECISIONS_TABLE);
            db.execSQL(TheDeciderDB.DROP_CHOICES_TABLE);
            onCreate(db);
        }
    }

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    // constructor
    public TheDeciderDB(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    // private methods
    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDB() {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if (db != null)
            db.close();
    }

    private void closeCursor(Cursor cursor) {
        if (cursor != null)
            cursor.close();
    }

    // Get all Decisions
    public ArrayList<Decision> getDecisions() {
        ArrayList<Decision> decisions = new ArrayList<Decision>();
        openReadableDB();
        Cursor cursor = db.query(DECISIONS_TABLE,
                null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Decision decision = new Decision();
            decision.setDecisionID(cursor.getInt(DECISION_ID_COL));
            decision.setDescription(cursor.getString(DECISION_DESCRIPTION_COL));
            decision.setNumChoices(cursor.getInt(DECISION_NUMCHOICES_COL));
            decisions.add(decision);
        }
        closeCursor(cursor);
        closeDB();
        return decisions;
    }

    public ArrayList<Choice> getChoices(int decisionID) {
        ArrayList<Choice> choices = new ArrayList<Choice>();
        openReadableDB();
        String[] whereArgs = new String[]{
                String.valueOf(decisionID)
        };
        String queryString = "SELECT * " +
                " FROM " + CHOICES_TABLE +
                " WHERE " + CHOICE_DECISION_ID + " = ?;";
        Cursor cursor = db.rawQuery(queryString, whereArgs);
        while (cursor.moveToNext()) {
            Choice choice = new Choice();
            choice.setChoiceID(cursor.getInt(CHOICE_ID_COL));
            choice.setDecisionID(cursor.getInt(CHOICE_DECISION_ID_COL));
            choice.setDescription(cursor.getString(CHOICE_DESCRIPTION_COL));
            choices.add(choice);
        }
        closeCursor(cursor);
        closeDB();

        return choices;
    }

    public ArrayList<Decision> getDecisions() {
        ArrayList<Decision> decisions = new ArrayList<Decision>();
        openReadableDB();
        String queryString = "SELECT * " +
                "FROM " + DECISIONS_TABLE + ";"
        Cursor cursor = db.rawQuery(queryString, null);
        while (cursor.moveToNext()) {
            Decision decision = new Decision();
            decision.setDecisionID(cursor.getInt(DECISION_ID_COL));
            decision.setNumChoices(cursor.getInt(DECISION_NUMCHOICES_COL));
            decision.setDescription(cursor.getString(DECISION_DESCRIPTION_COL));

            decisions.add(decision);
        }
        closeCursor(cursor);
        closeDB();

        return decisions;
    }
}
