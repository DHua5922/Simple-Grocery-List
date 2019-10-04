package com.example.simplebuylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

public class ItemEdit extends AppCompatActivity {

    public static final int FLAG_ADD = 0;
    public static final int FLAG_EDIT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_edit);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        Intent intent = getIntent();
        int flag = intent.getIntExtra(MainActivity.EXTRA_ADD, 0);

        switch(flag) {
            case 0:
                break;
            case 1:
                break;
        }
    }
}
