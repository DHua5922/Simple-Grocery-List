package com.example.simplebuylist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {

    private ArrayList<Item> itemList;

    public static class ItemHolder extends RecyclerView.ViewHolder {
        public CheckBox checkBox;
        public TextView itemName;
        public TextView itemPrice;

        public ItemHolder(View itemDisplay) {
            super(itemDisplay);
        }
    }

    public ItemAdapter() {
        this.itemList = new ArrayList<>();
    }

    public ItemAdapter(List<Item> itemList) {
        this.itemList = new ArrayList<>();
        this.itemList.addAll(itemList);
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemDisplay = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_display, parent, false);
        return (new ItemHolder(itemDisplay));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        //onBindCheckbox();
        //onBindNameView();
        //onBindPriceView();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

}
