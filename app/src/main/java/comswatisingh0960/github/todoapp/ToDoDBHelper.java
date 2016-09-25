package comswatisingh0960.github.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Swati on 9/22/2016.
 */

public class ToDoDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "taskerManager";

    // tasks table name
    private static final String TABLE_TASKS = "tasks";

    // tasks Table Columns names
    private static final String KEY_ID = "idno";
    private static final String KEY_TASKNAME = "taskName";
    private static final String KEY_STATUS = "status";
    {
}
    public ToDoDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_TASKS + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_TASKNAME
                + " TEXT, " + KEY_STATUS + " INTEGER)";
        db.execSQL(sql);

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        // Create tables again
        onCreate(db);
    }

    // Adding new task
    public void addTask(ToDo task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TASKNAME, task.getTaskName()); // task name
        // status of task- can be 0 for not done and 1 for done
        values.put(KEY_STATUS, task.getStatus());

        // Inserting Row
        db.insert(TABLE_TASKS, null, values);
        db.close(); // Closing database connection
    }

    public List<ToDo> getAllTasks() {
        List<ToDo> taskList = new ArrayList<ToDo>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TASKS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ToDo task = new ToDo();
                task.setIdno(cursor.getInt(0));
                task.setTaskName(cursor.getString(1));
                task.setStatus(cursor.getString(2));
                // Adding contact to list
                taskList.add(task);
            } while (cursor.moveToNext());
        }

        // return task list
        return taskList;
    }

    public void updateTask(ToDo task) {
        // updating row
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TASKNAME, task.getTaskName());
        values.put(KEY_STATUS, task.getStatus());
        db.update(TABLE_TASKS, values, KEY_ID + " = ?",new String[] {String.valueOf(task.getIdno())});
        db.close();
    }
    public void deleteTask(ToDo idno) {
        // deleting row
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASKS, KEY_ID+"  = ?",
                new String[] { String.valueOf(idno) });
        db.close();
    }


    public int getTotalTodos() {

        // Select query
        String countQuery = "SELECT  * FROM " + TABLE_TASKS;

        // Getting access for reading to the SQLite Database
        SQLiteDatabase db = this.getReadableDatabase();

        // Cursor initiation
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}