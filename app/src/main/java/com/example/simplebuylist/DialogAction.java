package com.example.simplebuylist;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

import java.util.concurrent.ExecutionException;

public class DialogAction {

    public static void confirmItemDeletion (final Context context, final String message, final ItemAdapter itemAdapter, final Item item) {
        ConfirmationDialog dialogBuilder = new ConfirmationDialog(context, R.layout.dialog_confirmation);
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
                String message = "Could not delete " + item.getName();
                int status = Text.FAIL;

                try {
                    if(itemAdapter.delete(item)) {
                        message = item.getName() + " deleted";
                        status = Text.SUCCESS;
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Text.printMessage(context, message, status);
                alertDialog.dismiss();
            }
        });
    }

    public static void confirmListDeletion (final Context context, String message, final ViewModel viewModel) {
        ConfirmationDialog dialogBuilder = new ConfirmationDialog(context, R.layout.dialog_confirmation);
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
                String message = "Could not delete " + MainActivity.STORE_NAME;
                int status = Text.FAIL;

                try {
                    if(viewModel.delete(MainActivity.currentStore)) {
                        message = MainActivity.STORE_NAME + " has been deleted";
                        status = Text.SUCCESS;
                        MainActivity.currentStore = null;
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Text.printMessage(context, message, status);
                alertDialog.dismiss();
            }
        });
    }

    public static void confirmAllListDeletion (final Context context, String message, final ViewModel viewModel) {
        ConfirmationDialog dialogBuilder = new ConfirmationDialog(context, R.layout.dialog_confirmation);
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
                String message = "Could not delete all lists";
                int status = Text.FAIL;

                try {
                    if(viewModel.deleteAll()) {
                        MainActivity.currentStore = null;
                        message = "All lists deleted";
                        status = Text.SUCCESS;
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Text.printMessage(context, message, status);
                alertDialog.dismiss();
            }
        });
    }

    public static void confirmAllItemDeletion (final Context context, String message, final ItemAdapter itemAdapter, final ViewModel viewModel) {
        ConfirmationDialog dialogBuilder = new ConfirmationDialog(context, R.layout.dialog_confirmation);
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
                String message = "Could not delete all items";
                int status = Text.FAIL;

                try {
                    if(viewModel.deleteAllItems(MainActivity.STORE_NAME)) {
                        itemAdapter.setItemList(viewModel.getItemList(MainActivity.STORE_NAME));
                        message = "All items deleted";
                        status = Text.SUCCESS;
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Text.printMessage(context, message, status);
                alertDialog.dismiss();
            }
        });
    }

    public static void confirmAllCheckedItemDeletion (final Context context, String message, final ItemAdapter itemAdapter, final ViewModel viewModel) {
        ConfirmationDialog dialogBuilder = new ConfirmationDialog(context, R.layout.dialog_confirmation);
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
                String message = "Could not delete all purchased items";
                int status = Text.FAIL;

                try {
                    if(viewModel.deleteAllCheckedItems(MainActivity.STORE_NAME)) {
                        itemAdapter.setItemList(viewModel.getItemList(MainActivity.STORE_NAME));
                        message = "All purchased items deleted";
                        status = Text.SUCCESS;
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Text.printMessage(context, message, status);
                alertDialog.dismiss();
            }
        });
    }

    public static void confirmAllUncheckedItemDeletion (final Context context, String message, final ItemAdapter itemAdapter, final ViewModel viewModel) {
        ConfirmationDialog dialogBuilder = new ConfirmationDialog(context, R.layout.dialog_confirmation);
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
                String message = "Could not delete all unpurchased items";
                int status = Text.FAIL;

                try {
                    if(viewModel.deleteAllUncheckedItems(MainActivity.STORE_NAME)) {
                        itemAdapter.setItemList(viewModel.getItemList(MainActivity.STORE_NAME));
                        message = "All unpurchased items deleted";
                        status = Text.SUCCESS;
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Text.printMessage(context, message, status);
                alertDialog.dismiss();
            }
        });
    }

    public static void createList(final Context context, String message, final ViewModel viewModel) throws ExecutionException, InterruptedException {
        final ListPromptDialog listPromptDialog =
                new ListPromptDialog(context, R.layout.dialog_list_prompt);
        final AlertDialog alertDialog = listPromptDialog.getAlertDialog();
        listPromptDialog.setDropdown(R.layout.dropdown, viewModel.getAllStoreNames());
        listPromptDialog.setHint(R.string.hint_list_name_input);
        listPromptDialog.setMessage(message);

        listPromptDialog.getButtonCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        listPromptDialog.getButtonOK().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newStoreName = listPromptDialog.getInputAutoComplete().getText().toString();
                String message = "Could not create new list: " + newStoreName;
                int status = Text.FAIL;

                if(!newStoreName.isEmpty()) {
                    try {
                        if (viewModel.getGivenStore(newStoreName) == null) {
                            Store unnamedStore = viewModel.getGivenStore("Unnamed");
                            if(unnamedStore != null) {
                                unnamedStore.setStoreName(newStoreName);
                                if(viewModel.update(unnamedStore)) {
                                    MainActivity.currentStore = unnamedStore;
                                    message = newStoreName + " has been created";
                                    status = Text.SUCCESS;
                                    alertDialog.dismiss();
                                }
                            } else {
                                Store newStore = new Store(0, newStoreName);
                                long id = viewModel.insert(newStore);
                                if(id > 0) {
                                    newStore.setId(id);
                                    MainActivity.currentStore = newStore;
                                    message = newStoreName + " has been created";
                                    status = Text.SUCCESS;
                                    alertDialog.dismiss();
                                }
                            }
                        } else {
                            message = newStoreName + " already exists";
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    message = "New store name cannot be empty";
                }
                Text.printMessage(context, message, status);
            }
        });
    }

    public static void renameList(final Context context, String message, final ViewModel viewModel) throws ExecutionException, InterruptedException {
        final ListPromptDialog listPromptDialog =
                new ListPromptDialog(context, R.layout.dialog_list_prompt);
        final AlertDialog alertDialog = listPromptDialog.getAlertDialog();
        listPromptDialog.setDropdown(R.layout.dropdown, viewModel.getAllStoreNames());
        listPromptDialog.setHint(R.string.hint_list_name_input);
        listPromptDialog.setMessage(message);

        listPromptDialog.getButtonCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        listPromptDialog.getButtonOK().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newStoreName = listPromptDialog.getInputAutoComplete().getText().toString();
                String message = "Name cannot be empty";
                int status = Text.FAIL;

                if(!newStoreName.isEmpty()) {
                    try {
                        Store updatedStore = viewModel.getGivenStore(newStoreName);
                        if (updatedStore == null) {
                            updatedStore = new Store(MainActivity.currentStore);
                            updatedStore.setStoreName(newStoreName);
                            if(viewModel.update(updatedStore)) {
                                message = "Renamed " + MainActivity.STORE_NAME + " to " + newStoreName;
                                status = Text.SUCCESS;
                                MainActivity.currentStore = updatedStore;
                                alertDialog.dismiss();
                            } else {
                                message = "Could not rename " + MainActivity.STORE_NAME + " to " + newStoreName;
                            }
                        } else {
                            message = newStoreName + " already exists";
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Text.printMessage(context, message, status);
            }
        });
    }

    public static void searchName(final Context context, String message, final ItemAdapter itemAdapter, final ViewModel viewModel) throws ExecutionException, InterruptedException {
        final ListPromptDialog listPromptDialog = new ListPromptDialog(context, R.layout.dialog_list_prompt);
        final AlertDialog alertDialog = listPromptDialog.getAlertDialog();
        listPromptDialog.setDropdown(R.layout.dropdown, viewModel.getAllItemNames(MainActivity.STORE_NAME));
        listPromptDialog.setHint(R.string.hint_list_name_input);
        listPromptDialog.setMessage(message);

        listPromptDialog.getButtonCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        listPromptDialog.getButtonOK().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameInput = listPromptDialog.getInputAutoComplete().getText().toString();

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
                    Text.printMessage(context, "Name cannot be empty", Text.FAIL);
                }
            }
        });
    }

    public static void searchKeyword(final Context context, String message, final ItemAdapter itemAdapter, final ViewModel viewModel) {
        final ListPromptDialog listPromptDialog = new ListPromptDialog(context, R.layout.dialog_list_prompt);
        final AlertDialog alertDialog = listPromptDialog.getAlertDialog();
        listPromptDialog.setHint(R.string.hint_list_name_input);
        listPromptDialog.setMessage(message);

        listPromptDialog.getButtonCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        listPromptDialog.getButtonOK().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = listPromptDialog.getInputAutoComplete().getText().toString();

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
                    Text.printMessage(context, "Keyword cannot be empty", Text.FAIL);
                }
            }
        });
    }
}
