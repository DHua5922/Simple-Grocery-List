package com.example.simplebuylist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Text {

    public static final int SUCCESS = 1;
    public static final int FAIL = 0;

    public static void printMessage(Context context, String message, int status) {
        View layout = LayoutInflater.from(context).inflate(R.layout.toast, null);

        TextView toastMessage = layout.findViewById(R.id.toast_msg);
        int imgId = (status == SUCCESS) ? R.drawable.icon_check : R.drawable.icon_fail;
        toastMessage.setCompoundDrawablePadding(20);
        toastMessage.setCompoundDrawablesWithIntrinsicBounds(imgId, 0, 0, 0);
        toastMessage.setText(message);

        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public static String formatPrice(double price) {
        return String.format(Locale.getDefault(), "Price: $%.2f", price);
    }

}
