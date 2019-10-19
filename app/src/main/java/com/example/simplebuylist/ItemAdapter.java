package com.example.simplebuylist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {

    private ArrayList<Item> itemList;
    private ViewModel viewModel;
    private MainActivity context;

    public ItemAdapter(MainActivity context, ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private TextView itemNameView;
        private TextView itemPriceView;

        public ItemHolder(View itemDisplay) {
            super(itemDisplay);
            this.checkBox = itemDisplay.findViewById(R.id.checkbox_isBought);
            this.itemNameView = itemDisplay.findViewById(R.id.nameView);
            this.itemPriceView = itemDisplay.findViewById(R.id.priceView);

        }

        public CheckBox getCheckBox() {
            return checkBox;
        }

        public TextView getItemNameView() {
            return itemNameView;
        }

        public TextView getItemPriceView() {
            return itemPriceView;
        }
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
        onBindNameView(holder, position);
        onBindPriceView(holder, position);
    }

    public void onBindNameView(ItemHolder holder, int position) {
        TextView itemNameView = holder.getItemNameView();
        itemNameView.setText(itemList.get(position).getName());
    }

    public void onBindPriceView(ItemHolder holder, int position) {
        TextView itemPriceView = holder.getItemPriceView();
        itemPriceView.setText("" + itemList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public boolean add(Item item) throws ExecutionException, InterruptedException {
        if(viewModel.insert(item)) {
            itemList.add(item);
            notifyItemInserted(getItemCount() - 1);
            return true;
        }

        return false;
    }

    public int getHighestOrder() {
        return itemList.size() == 0 ? 0 : itemList.get(itemList.size() - 1).getOrder();
    }

    public void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }
}
