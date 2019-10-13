package com.example.simplebuylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;

public class ItemEdit extends AppCompatActivity {

    public static final int FLAG_ADD = 0;
    public static final int FLAG_EDIT = 1;
    public static int FLAG = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_edit);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        Intent intent = getIntent();
        FLAG = intent.getIntExtra(MainActivity.EXTRA_ADD, 0);
        switch(FLAG) {
            case FLAG_ADD:
                Button btnAddItem = findViewById(R.id.btn_add_item);
                break;
            case FLAG_EDIT:
                //btnAddSaveItem.setText(R.string.btn_save_item_text);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(FLAG == FLAG_ADD)
            getMenuInflater().inflate(R.menu.menu_add_item, menu);
        else
            getMenuInflater().inflate(R.menu.menu_edit_item, menu);

        return true;
    }
}
