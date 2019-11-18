package com.example.simplebuylist;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.List;

public class ListPromptBuilder extends DialogBuilder {

    private AutoCompleteTextView autoCompleteTextView;

    public ListPromptBuilder(Context context, int dialogLayoutID) {
        super(context, dialogLayoutID);
        setInputAutoComplete();
    }

    private void setInputAutoComplete() {
        autoCompleteTextView = getDialogLayout().findViewById(R.id.input_autocomplete);
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
