package com.example.simplebuylist;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> implements PopupMenu.OnMenuItemClickListener {

    private List<Item> itemList;
    private ViewModel viewModel;
    private MainActivity context;

    public static final String EXTRA_ACTION = "com.example.simplebuylist.EXTRA_ACTION";
    public static final String EXTRA_ITEM_ID = "com.example.simplebuylist.EXTRA_ITEM_ID";
    public static final String EXTRA_ITEM_NAME = "com.example.simplebuylist.EXTRA_ITEM_NAME";
    public static final String EXTRA_ITEM_PRICE = "com.example.simplebuylist.EXTRA_ITEM_PRICE";
    public static final String EXTRA_ITEM_IS_BOUGHT = "com.example.simplebuylist.EXTRA_ITEM_IS_BOUGHT";
    public static final int EDIT_ITEM_REQUEST = 1;
    private int clickedMenuPos = -1;

    public ItemAdapter(MainActivity context, ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private TextView itemNameView;
        private TextView itemPriceView;
        private ImageView btnItemActions;

        public ItemHolder(View itemDisplay) {
            super(itemDisplay);
            this.checkBox = itemDisplay.findViewById(R.id.checkbox_isBought);
            this.itemNameView = itemDisplay.findViewById(R.id.nameView);
            this.itemPriceView = itemDisplay.findViewById(R.id.priceView);
            this.btnItemActions = itemDisplay.findViewById(R.id.img_item_action);
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

        public ImageView getItemActionsView() {return btnItemActions; }
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
        onBindNameView(holder);
        onBindPriceView(holder);
        onBindAction(holder);
    }

    public void onBindNameView(final ItemHolder holder) {
        int position = holder.getAdapterPosition();
        Item item = itemList.get(position);
        itemList.set(position, item);
        holder.getItemNameView().setText(item.getName());
    }

    public void onBindPriceView(final ItemHolder holder) {
        int position = holder.getAdapterPosition();
        Item item = itemList.get(position);
        itemList.set(position, item);
        holder.getItemPriceView().setText(Text.formatPrice(item.getPrice()));

    }

    public void onBindAction(final ItemHolder holder) {
        holder.btnItemActions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedMenuPos = holder.getAdapterPosition();
                showMenu(view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    public boolean add(Item item) throws ExecutionException, InterruptedException {
        return viewModel.insert(item);
    }

    public boolean update(Item item) throws ExecutionException, InterruptedException {
        return viewModel.update(item);
    }

    public boolean delete(Item item) throws ExecutionException, InterruptedException {
        /*if(viewModel.delete(item)) {
            itemList.remove(clickedMenuPos);
            notifyItemRemoved(clickedMenuPos);
            return true;
        }
        return false;*/
        return viewModel.delete(item);
    }

    public int getHighestOrder() {
        return itemList.size() == 0 ? 0 : itemList.get(itemList.size() - 1).getOrder();
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    public void showMenu(View v) {
        PopupMenu popup = new PopupMenu(context, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.menu_item_actions);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Item chosenItem = itemList.get(clickedMenuPos);
        switch (item.getItemId()) {
            case R.id.option_edit_item:
                Intent intent = new Intent(context, ItemEdit.class);
                intent.putExtra(EXTRA_ACTION, "Editing " + chosenItem.getName());
                intent.putExtra(EXTRA_ITEM_ID, chosenItem.getId());
                intent.putExtra(EXTRA_ITEM_NAME, chosenItem.getName());
                intent.putExtra(EXTRA_ITEM_PRICE, chosenItem.getPrice());
                intent.putExtra(EXTRA_ITEM_IS_BOUGHT, chosenItem.getWasPurchased());
                context.startActivityForResult(intent, EDIT_ITEM_REQUEST);
                return true;
            case R.id.option_delete_item:
                Dialog.confirmItemDeletion(context, context.getString(R.string.confirmation_deletion_msg), this, chosenItem);
                return true;
            default:
                return false;
        }
    }
}
