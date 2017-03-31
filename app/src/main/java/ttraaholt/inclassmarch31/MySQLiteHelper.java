package ttraaholt.inclassmarch31;

/**
 * Created by ttraaholt on 3/31/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * MySQLiteHelper class identifies the field names for the database as well as uses the OnCreate and onUpgrade to create the database and let
 * user know that database is upgrading.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {
    //String for each of the fields. Name of field is identified.
    //comments field is identified with the TABLE_COMMENTS
    public static final String TABLE_COMMENTS = "comments";
    //_id field is identified with the COLUMN_ID identifier.
    public static final String COLUMN_ID = "_id";
    //comment field that is identified with the COLUMN_COMMENT identifier.
    public static final String COLUMN_COMMENT = "comment";
    //version and database name are identified.
    private static final String DATABASE_NAME = "commments.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    //Database is created with the fields identified above. DATABASE_CREATE is used with TABLE_COMMENTS, COLUMN_ID, and COLUMN_COMMENT
    private static final String DATABASE_CREATE = "create table "
            + TABLE_COMMENTS + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_COMMENT
            + " text not null);";
    //MySQLiteHelper method that creates the MySQLiteHelper object
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //OnCreate method that once it is called, the database is created.
    //Basic SQL create command. CREATE table comments
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }
    //OnUpgrade method that uses the database and the version to display in the logs the version (if it's being upgraded), as well as drops the table TABLE_COMMENTS, if it exists.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }

}
