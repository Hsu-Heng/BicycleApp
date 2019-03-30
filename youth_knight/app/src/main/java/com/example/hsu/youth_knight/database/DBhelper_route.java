package com.example.hsu.youth_knight.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by hsu on 2017/2/6.
 */
public class DBhelper_route extends SQLiteOpenHelper {
    public static final String DBname = "my_route.db";
    public static final String DBlocation = "/data/data/com.example.hsu.youth_knight/databases/";
    public static final int  DBversion = 1;
    private Context mcontext;
    private static String sql = "CREATE TABLE ROUTE (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "CITY TEXT,"+
            "CONTENT TEXT);";
    private SQLiteDatabase mDatabase;

    public DBhelper_route(Context context)throws IOException {
        super(context,DBname,null,DBversion);
        this.mcontext = context;

    }
    public void createdatabase() throws IOException {
        boolean dbexist = checkdatabase();
        if(dbexist) {
            System.out.println(" creatDatabase exists.");
        } else {
            this.getReadableDatabase();
            try {
                copydatabase();
            } catch(IOException e) {
                System.out.println(" fail");
            }
        }
    }
    public boolean checkdatabase() {
        //SQLiteDatabase checkdb = null;
        boolean checkdb = false;
        try {
            String myPath = DBlocation + DBname;
            File dbfile = new File(myPath);
            //checkdb = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
            checkdb = dbfile.exists();
            System.out.println("xxDatabase is exist");
        } catch(SQLiteException e) {
            System.out.println("Database doesn't exist");
        }
        return checkdb;
    }
    public void copydatabase() throws IOException {
        //Open your local db as the input stream
        InputStream myinput = mcontext.getAssets().open(DBname);

        // Path to the just created empty db
        String outfilename = DBlocation + DBname;

        //Open the empty db as the output stream
        OutputStream myoutput = new FileOutputStream(DBlocation+DBname);

        // transfer byte to inputfile to outputfile
        byte[] buffer = new byte[10000];
        int length;
        while ((length = myinput.read(buffer))>0) {
            myoutput.write(buffer,0,length);
        }

        //Close the streams
        myoutput.flush();
        myoutput.close();
        myinput.close();
    }

    public void opendatabase() throws SQLException {
        //Open the database
        String mypath = DBlocation + DBname;
        mDatabase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void close() {
        if(mDatabase != null) {
            mDatabase.close();
        }
        super.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public long schuleinsertitem(SQLiteDatabase db,String city,String content)
    {
        ContentValues cv = new ContentValues();
        cv.put("CITY",city);
        cv.put("CONTENT",content);

        return db.insert("ROUTE",null,cv);
    }
    public Cursor get(SQLiteDatabase db, long rowId) throws SQLiteException
    {
        Cursor mcursor = db.query("ROUTE",new String[]{"_id","CITY","CONTENT"},"_id="+rowId,null, null, null, null, null);
        if(mcursor != null)
        {
            mcursor.moveToFirst();
        }
        return  mcursor;
    }
    public Cursor getdata(String city)
    {
        Cursor mcursor = mDatabase.query("ROUTE",new String[]{"CITY","CONTENT"},"CITY = ?",new String[]{city},null,null,null);
        if(mcursor != null)
        {
            mcursor.moveToFirst();
        }
        return mcursor;

    }
    public Cursor QueryData(String query)
    {
        return mDatabase.rawQuery(query,null);
    }
}
