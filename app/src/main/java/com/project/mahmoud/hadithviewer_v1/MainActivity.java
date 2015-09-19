package com.project.mahmoud.hadithviewer_v1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    Button next , prev  ;
    TextView subject, title ,body ,matn ;
    public Cursor cursor ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        next = (Button) findViewById(R.id.next);
        prev = (Button) findViewById(R.id.prev);

        subject = (TextView) findViewById(R.id.hadith_subject);
        title = (TextView) findViewById(R.id.hadith_title);
        body = (TextView) findViewById(R.id.hadith_body);
        matn = (TextView) findViewById(R.id.hadith_matn);

        MyDatabase myDatabase = new MyDatabase(this);
        Toast.makeText(this,myDatabase.getHadithCount()+"",Toast.LENGTH_SHORT).show();

//        textview.setText(myDatabase.getHadith());

        int count = 1;
        cursor = myDatabase.getHadith();
        startManagingCursor(cursor);
        DisplayHadith();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cursor.moveToNext()){
                    DisplayHadith();
                }
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(cursor.moveToPrevious()){
                        DisplayHadith();
                    }
                }

        });

    }

    private void DisplayHadith() {
        if(cursor!=null){

        subject.setText(cursor.getString(0));
            title.setText(cursor.getString(1));
            body.setText(cursor.getString(2));
            matn.setText(cursor.getString(3));

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
