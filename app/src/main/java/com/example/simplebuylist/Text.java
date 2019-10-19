package com.example.simplebuylist;

import android.content.Context;
import android.widget.Toast;

public class Text {

    public static void printMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

}
