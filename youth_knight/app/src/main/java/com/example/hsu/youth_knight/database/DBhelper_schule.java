package com.example.hsu.youth_knight.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hsu on 2017/1/16.
 */
public class DBhelper_schule extends SQLiteOpenHelper {
    SQLiteDatabase database;
    private static String DB_NAME = "schule";
    private static int DB_VERSION = 1;
    private static String sql = "CREATE TABLE SCHULE (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "NAME TEXT,"+
            "DATESTART TEXT,"+
            "DATEEND TEXT,"+
            "DAY INTEGER,"+
            "ImageResourceid TEXT,"+
            "JSON TEXT);";
    public DBhelper_schule(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
        schuleinsertitem(db,"EXAMPLE","2017/1/20","2017/1/20",1,"https://picjumbo.imgix.net/HNCK8749.jpg?q=40&w=1650&sharp=30","");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long schuleinsertitem(SQLiteDatabase db,String name,String datestart,String dateend,int day,String ImageResourceid,String json)
    {
        ContentValues cv = new ContentValues();
        cv.put("NAME",name);
        cv.put("DATESTART",datestart);
        cv.put("DATEEND",dateend);
        cv.put("DAY",day);
        cv.put("ImageResourceid",ImageResourceid);
        cv.put("JSON",json);
        return db.insert("SCHULE",null,cv);
    }
    public boolean delete(SQLiteDatabase db,long rowID)
    {
        return db.delete("SCHULE","_id = "+rowID,null)>0;
    }
    //SQLite裡的修改
    public boolean update (SQLiteDatabase db,long rowId,String name,String datestart,String dateend,int day,String ImageResourceid,String json)
    {
        ContentValues cv = new ContentValues();
        cv.put("NAME",name);
        cv.put("DATESTART",datestart);
        cv.put("DATEEND",dateend);
        cv.put("DAY",day);
        cv.put("ImageResourceid",ImageResourceid);
        cv.put("JSON",json);
        return db.update("SCHULE",cv,"_id="+rowId,null) > 0;
    }
    //取得游標
    public Cursor get(SQLiteDatabase db, long rowId) throws SQLiteException
    {
        Cursor mcursor = db.query("SCHULE",new String[]{"_id","NAME","DATESTART","DATEEND","DAY","ImageResourceid","JSON"},"_id="+rowId,null, null, null, null, null);
        if(mcursor != null)
        {
            mcursor.moveToFirst();
        }
        return  mcursor;
    }
    public Cursor querydata(SQLiteDatabase db,String query)
    {
        return db.rawQuery(query,null);
    }
}
