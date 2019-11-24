package com.example.simplebuylist;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Dialog {

    public static void confirmItemDeletion (final Context context, final String message, final ItemAdapter itemAdapter, final Item item) {
        ConfirmationBuilder dialogBuilder = new ConfirmationBuilder(context, R.layout.dialog_confirmation);
        dialogBuilder.setMessage(message);
        final AlertDialog alertDialog = dialogBuilder.getAlertDialog();

        dialogBuilder.getButtonCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        dialogBuilder.getButtonOK().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(itemAdapter.delete(item)) {
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

    public static void confirmListDeletion (final Context context, String message, final ViewModel viewModel) {
        ConfirmationBuilder dialogBuilder = new ConfirmationBuilder(context, R.layout.dialog_confirmation);
        dialogBuilder.setMessage(message);
        final AlertDialog alertDialog = dialogBuilder.getAlertDialog();

        dialogBuilder.getButtonCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        dialogBuilder.getButtonOK().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(viewModel.delete(MainActivity.currentStore)) {
                        MainActivity.currentStore = null;
                        Text.printMessage(context, "List deleted");
                    }
                    else {
                        Text.printMessage(context, "Could not delete this list");
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

    public static void confirmAllListDeletion (final Context context, String message, final ViewModel viewModel) {
        ConfirmationBuilder dialogBuilder = new ConfirmationBuilder(context, R.layout.dialog_confirmation);
        dialogBuilder.setMessage(message);
        final AlertDialog alertDialog = dialogBuilder.getAlertDialog();

        dialogBuilder.getButtonCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        dialogBuilder.getButtonOK().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(viewModel.deleteAll()) {
                        MainActivity.currentStore = null;
                        Text.printMessage(context, "All lists deleted");
                    }
                    else {
                        Text.printMessage(context, "Could not delete all lists");
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

    public static void confirmAllItemDeletion (final Context context, String message, final ItemAdapter itemAdapter, final ViewModel viewModel) {
        ConfirmationBuilder dialogBuilder = new ConfirmationBuilder(context, R.layout.dialog_confirmation);
        dialogBuilder.setMessage(message);
        final AlertDialog alertDialog = dialogBuilder.getAlertDialog();

        dialogBuilder.getButtonCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        dialogBuilder.getButtonOK().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(viewModel.deleteAllItems(MainActivity.STORE_NAME)) {
                        itemAdapter.setItemList(viewModel.getItemList(MainActivity.STORE_NAME));
                        Text.printMessage(context, "All items deleted");
                    }
                    else {
                        Text.printMessage(context, "Could not delete all items");
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

    public static void confirmAllCheckedItemDeletion (final Context context, String message, final ItemAdapter itemAdapter, final ViewModel viewModel) {
        ConfirmationBuilder dialogBuilder = new ConfirmationBuilder(context, R.layout.dialog_confirmation);
        dialogBuilder.setMessage(message);
        final AlertDialog alertDialog = dialogBuilder.getAlertDialog();

        dialogBuilder.getButtonCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        dialogBuilder.getButtonOK().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(viewModel.deleteAllCheckedItems(MainActivity.STORE_NAME)) {
                        itemAdapter.setItemList(viewModel.getItemList(MainActivity.STORE_NAME));
                        Text.printMessage(context, "All purchased items deleted");
                    }
                    else {
                        Text.printMessage(context, "Could not delete all purchased items");
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

    public static void confirmAllUncheckedItemDeletion (final Context context, String message, final ItemAdapter itemAdapter, final ViewModel viewModel) {
        ConfirmationBuilder dialogBuilder = new ConfirmationBuilder(context, R.layout.dialog_confirmation);
        dialogBuilder.setMessage(message);
        final AlertDialog alertDialog = dialogBuilder.getAlertDialog();

        dialogBuilder.getButtonCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        dialogBuilder.getButtonOK().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(viewModel.deleteAllUncheckedItems(MainActivity.STORE_NAME)) {
                        itemAdapter.setItemList(viewModel.getItemList(MainActivity.STORE_NAME));
                        Text.printMessage(context, "All unpurchased items deleted");
                    }
                    else {
                        Text.printMessage(context, "Could not delete all unpurchased items");
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

    public static void createList(final Context context, String message, final ViewModel viewModel) throws ExecutionException, InterruptedException {
        final ListPromptBuilder listPromptBuilder =
                new ListPromptBuilder(context, R.layout.dialog_list_prompt);
        final AlertDialog alertDialog = listPromptBuilder.getAlertDialog();
        listPromptBuilder.setDropdown(R.layout.dropdown, viewModel.getAllStoreNames());
        listPromptBuilder.setHint(R.string.hint_list_name_input);
        listPromptBuilder.setMessage(message);

        alertDialog.findViewById(R.id.btn_dialog_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.findViewById(R.id.btn_dialog_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newStoreName = listPromptBuilder.getInputAutoComplete().getText().toString();
                if(!newStoreName.isEmpty()) {
                    try {
                        if (viewModel.getGivenStore(newStoreName) == null) {
                            Store unnamedStore = viewModel.getGivenStore("Unnamed");
                            if(unnamedStore != null)
                                viewModel.delete(unnamedStore);

                            Store newStore = new Store(0, newStoreName, 0);
                            long id = viewModel.insert(newStore);
                            if(id > 0) {
                                newStore.setId(id);
                                MainActivity.currentStore = newStore;
                                alertDialog.dismiss();
                            } else {
                                Text.printMessage(context, "Could not create new list: " + newStoreName);
                            }
                        } else {
                            Text.printMessage(context, newStoreName + " already exists");
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Text.printMessage(context, "Name cannot be empty");
                }
            }
        });
    }

    public static void renameList(final Context context, String message, final ViewModel viewModel) throws ExecutionException, InterruptedException {
        final ListPromptBuilder listPromptBuilder =
                new ListPromptBuilder(context, R.layout.dialog_list_prompt);
        final AlertDialog alertDialog = listPromptBuilder.getAlertDialog();
        listPromptBuilder.setDropdown(R.layout.dropdown, viewModel.getAllStoreNames());
        listPromptBuilder.setHint(R.string.hint_list_name_input);
        listPromptBuilder.setMessage(message);

        alertDialog.findViewById(R.id.btn_dialog_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.findViewById(R.id.btn_dialog_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newStoreName = listPromptBuilder.getInputAutoComplete().getText().toString();
                if(!newStoreName.isEmpty()) {
                    try {
                        Store updatedStore = viewModel.getGivenStore(newStoreName);
                        if (updatedStore == null) {
                            updatedStore = new Store(MainActivity.currentStore);
                            updatedStore.setStoreName(newStoreName);
                            if(viewModel.update(updatedStore)) {
                                MainActivity.currentStore = updatedStore;
                                alertDialog.dismiss();
                            } else {
                                Text.printMessage(context, "Could not rename this list to " + newStoreName);
                            }
                        } else {
                            Text.printMessage(context, newStoreName + " already exists");
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Text.printMessage(context, "Name cannot be empty");
                }
            }
        });
    }

    public static void openList(final Context context,
                                String message,
                                final ViewModel viewModel,
                                final ItemAdapter itemAdapter,
                                final TextView listLabelView) throws ExecutionException, InterruptedException {
        final ListPromptBuilder listPromptBuilder = new ListPromptBuilder(
                context,
                R.layout.dialog_list_prompt);
        final AlertDialog alertDialog = listPromptBuilder.getAlertDialog();
        listPromptBuilder.setDropdown(R.layout.dropdown, viewModel.getAllStoreNames());
        listPromptBuilder.setHint(R.string.hint_list_name_input);
        listPromptBuilder.setMessage(message);

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
                    String storeNameToSee = listPromptBuilder.getInputAutoComplete().getText().toString();
                    Store storeToSee = viewModel.getGivenStore(storeNameToSee);
                    if(storeToSee != null) {
                            MainActivity.currentStore = storeToSee;
                            MainActivity.STORE_NAME = storeNameToSee;
                            listLabelView.setText(storeNameToSee);

                            ArrayList<Item> listToBeOpened = (ArrayList<Item>) viewModel.getItemList(storeNameToSee);
                            itemAdapter.setItemList(listToBeOpened);

                            alertDialog.dismiss();
                    } else {
                        Text.printMessage(context, storeNameToSee + " does not exist");
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void searchName(final Context context, String message, final ItemAdapter itemAdapter, final ViewModel viewModel) throws ExecutionException, InterruptedException {
        final ListPromptBuilder listPromptBuilder =
                new ListPromptBuilder(context, R.layout.dialog_list_prompt);
        final AlertDialog alertDialog = listPromptBuilder.getAlertDialog();
        listPromptBuilder.setDropdown(R.layout.dropdown, viewModel.getAllItemNames(MainActivity.STORE_NAME));
        listPromptBuilder.setHint(R.string.hint_list_name_input);
        listPromptBuilder.setMessage(message);

        alertDialog.findViewById(R.id.btn_dialog_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.findViewById(R.id.btn_dialog_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameInput = listPromptBuilder.getInputAutoComplete().getText().toString();
                if(!nameInput.isEmpty()) {
                    try {
                        itemAdapter.setItemList(viewModel.getItemsByName(MainActivity.STORE_NAME, nameInput));
                        alertDialog.dismiss();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Text.printMessage(context, "Name cannot be empty");
                }
            }
        });
    }

    public static void searchKeyword(final Context context, String message, final ItemAdapter itemAdapter, final ViewModel viewModel) {
        final ListPromptBuilder listPromptBuilder =
                new ListPromptBuilder(context, R.layout.dialog_list_prompt);
        final AlertDialog alertDialog = listPromptBuilder.getAlertDialog();
        listPromptBuilder.setHint(R.string.hint_list_name_input);
        listPromptBuilder.setMessage(message);

        alertDialog.findViewById(R.id.btn_dialog_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.findViewById(R.id.btn_dialog_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = listPromptBuilder.getInputAutoComplete().getText().toString();
                if(!keyword.isEmpty()) {
                    try {
                        itemAdapter.setItemList(viewModel.getItemsByKeyword(MainActivity.STORE_NAME, keyword));
                        alertDialog.dismiss();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Text.printMessage(context, "Field cannot be empty");
                }
            }
        });
    }
}
