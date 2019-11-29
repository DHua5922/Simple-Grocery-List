package com.dylanhua.simplebuylist;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.List;

public class ListPromptDialog extends Dialog {

    private AutoCompleteTextView autoCompleteTextView;

    public ListPromptDialog(Context context, int dialogLayoutID) {
        super(context, dialogLayoutID);
        autoCompleteTextView = getDialogLayout().findViewById(R.id.input_autocomplete);
    }

    public void fillInputAutoComplete(String text) {
        autoCompleteTextView.setText(text);
    }

    public AutoCompleteTextView getInputAutoComplete() {
        return autoCompleteTextView;
    }

    public void setDropdown(int dropDownLayoutID, List<String> storeNames) {
        ArrayAdapter<String> storeNamesAdapter = new ArrayAdapter<>(getContext(), dropDownLayoutID, storeNames);
        autoCompleteTextView.setAdapter(storeNamesAdapter);
    }

    public void setHint(int hint) {
        autoCompleteTextView.setHint(getContext().getString(hint));
    }
}
