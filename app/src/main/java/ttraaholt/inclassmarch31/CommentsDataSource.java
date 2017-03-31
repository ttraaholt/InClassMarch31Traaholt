package ttraaholt.inclassmarch31;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 *  CommentsDataSource used as the Data Access Object. It maintains the database connection and supports adding new comments and fetching all comments.
 */

public class CommentsDataSource {

    // Database fields
    //SQLiteDatabase object called database
    private SQLiteDatabase database;
    //MySQLiteHelper object called dbHelper
    private MySQLiteHelper dbHelper;
    //String array called allColumns that contains the MySQLiteHelper as the COLUMN_ID and the MySQLiteHelper as the COLUMN_COMMENT.
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_COMMENT };

    //CommentsDataSource method that uses context. It takes the dbHelper and creates a new one.
    public CommentsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }
    //Open method that throws the SQLException. It takes the database object and makes it writable.
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    //close method that closes the dbHelper.
    public void close() {
        dbHelper.close();
    }
    //Comment method that inserts a comment and moves it to the first in the list.
    public Comment createComment(String comment) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_COMMENT, comment);
        //Insert ID query that takes the TABLE_COMMENTS from MySQLiteHelper and gives it null values.
        //INSERT INTO TABLE_NAME VALUES TEXT WHERE COLUMN VALUE_COMMENTS.
        long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
                values);
        //Query that selects an item with primary key COLUMN_ID to the table.
        //String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        //moves item to top of the list
        cursor.moveToFirst();
        Comment newComment = cursorToComment(cursor);
        cursor.close();
        return newComment;
    }
    //deleteComment method that uses the id from the comment to delete
    public void deleteComment(Comment comment) {
        long id = comment.getId();
        //Prints out Deleted comment with its respective id.
        System.out.println("Comment deleted with id: " + id);
        //Delete query that deletes TABLE_COMMENTS and COLUMN_ID
        //DELETE FROM TABLE_NAME WHERE ID = "".
        database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }
    //Comments List array that creates a new ArrayList.
    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<Comment>();
        //Query that gets all comments.
        //String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, null, null, null, null, null);
        //Moves new comment to top, and takes the rest and moves them down a row.
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Comment comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }
    //cursorToComment method that creates a new comment and returns the comment.
    private Comment cursorToComment(Cursor cursor) {
        Comment comment = new Comment();
        comment.setId(cursor.getLong(0));
        comment.setComment(cursor.getString(1));
        return comment;
    }
}
