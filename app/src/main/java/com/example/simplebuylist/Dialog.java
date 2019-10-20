package com.example.simplebuylist;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class Dialog {

    public static void confirmDeletion (final Context context, String message, final ItemAdapter itemAdapter, final int clickedMenuPos) {

        View dialogLayout = LayoutInflater.from(context).inflate(R.layout.dialog_confirmation, null);
        TextView msgView = dialogLayout.findViewById(R.id.message_view);
        msgView.setText(message);

        final AlertDialog alertDialog = new AlertDialog.Builder(context).setView(dialogLayout).show();
        alertDialog.findViewById(R.id.btn_dialog_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.findViewById(R.id.btn_dialog_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(itemAdapter.delete(clickedMenuPos)) {
                        Text.printMessage(context, "Item deleted");
                    }
                    else {
                        Text.printMessage(context, "Could not delete item");
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                alertDialog.dismiss();
            }
        });
    }
}
