package com.project.mahmoud.hadithviewer_v1;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Mahmoud on 9/19/2015.
 */
public class DBHelper extends SQLiteOpenHelper {


    private final Context context ;

    private SQLiteDatabase dataBase ;

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "DB";

    public final static String DATABASE_PATH = "data/data/com.project.mahmoud.hadithviewer_v1/databases/";

    // Contacts table name
    private static final String TABLE_Authors = "Authors";

    // Contacts table name
    private static final String TABLE_Books = "Books";

    // Contacts table name
    private static final String TABLE_Hadith = "Hadith";

    // Author Table Columns names
    private static final String Author_ID = "ID";
    private static final String Author_NAME = "Name";

    //Author Table Columns names
    private static final String Book_ID = "ID";
    private static final String Book_Title = "Title";
    private static final String Book_Author = Author_ID;

    //Hadith Table Columns names
    private static final String Hadith_ID = "ID";
    private static final String Hadith_BID = "BID";
    private static final String Hadith_Subject = "Subject";
    private static final String Hadith_Title = "Title";
    private static final String Hadith_Hadith = "Hadith";
    private static final String Hadith_Matn = "Matn";

    public DBHelper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);

        if(checkDataBase()){
            openDataBase();
        }else{
            Toast.makeText(context,"false",Toast.LENGTH_SHORT).show();
            try{
                this.getReadableDatabase();
                copyDataBase();
            }catch (IOException e){
                throw new Error("Error Coping database");

            }
            Toast.makeText(context,"Intial Database is created",Toast.LENGTH_SHORT).show();
        }
        this.context = context;
        Toast.makeText(context,"constr",Toast.LENGTH_SHORT).show();
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = context.getAssets().open(DATABASE_NAME);
        String outFileName =DATABASE_PATH+DATABASE_NAME ;
        OutputStream myOutput = new FileOutputStream(outFileName);

        byte [] buffer = new byte[1024];
        int lenght ;
        while ((lenght=myInput.read(buffer))>0){
            myOutput.write(buffer,0,lenght);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {
        String dbPath = DATABASE_PATH+DATABASE_NAME ;
        dataBase = SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READWRITE);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db=this.getReadableDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null ;
        boolean exist = false ;
        try{
            String dbPath = DATABASE_PATH + DATABASE_NAME ;
            checkDB = SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READONLY);

        }catch (SQLiteException e){

        }
        if(checkDB != null){
            exist = true;
            checkDB.close();
        }
        return exist;

    }

    public int getHadithCount (){

       String countQuery = "SELECT * FROM "+TABLE_Books;

        Cursor cursor = dataBase.rawQuery(countQuery,null);
        cursor.close();
        return cursor.getCount();

    }
}
