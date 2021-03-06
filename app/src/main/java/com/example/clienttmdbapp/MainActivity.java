package com.example.clienttmdbapp;

import android.database.Cursor;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView myText1;
    private Button show1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        initListeners();
    }

    private void initUI() {
        myText1 = findViewById(R.id.myText);
        show1 = findViewById(R.id.show);
    }

    private void initListeners() {
        show1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show:
                queryAndDisplayAll();
                break;
        }
    }

    private void queryAndDisplayAll() {
        Uri uri = Uri.parse("content://eliorcohen.com.tmdbapp.MovieContentProvider");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String movieName = cursor.getString(cursor.getColumnIndex("title"));
                myText1.append(movieName + "\n");
            }
            cursor.close();
        }
    }

}
