package com.dylanhua.simplebuylist;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public abstract class Dialog {

    private Context context;
    private View dialogLayout;
    
    public Dialog(Context context, int dialogLayoutID) {
        setContext(context);
        setDialogLayout(dialogLayoutID);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public View getDialogLayout() {
        return dialogLayout;
    }

    public void setDialogLayout(int dialogLayoutID) {
        this.dialogLayout = LayoutInflater.from(context).inflate(dialogLayoutID, null);
    }

    public TextView getMessageView() {
        return dialogLayout.findViewById(R.id.message_view);
    }

    public void setMessage(String message) {
        getMessageView().setText(message);
    }


    public Button getButtonCancel() {
        return dialogLayout.findViewById(R.id.btn_dialog_cancel);
    }

    public Button getButtonOK() {
        return dialogLayout.findViewById(R.id.btn_dialog_ok);
    }
    
    public AlertDialog getAlertDialog() {
        return new AlertDialog.Builder(context).setView(dialogLayout).show();
    }
}
