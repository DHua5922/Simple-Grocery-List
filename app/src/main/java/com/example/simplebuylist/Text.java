package com.example.simplebuylist;

import android.content.Context;
import android.widget.Toast;

import java.util.Locale;

public class Text {

    public static void printMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static String formatPrice(double price) {
        return String.format(Locale.getDefault(), "Price: $%.2f", price);
    }

}
