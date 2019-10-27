package com.example.simplebuylist;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Repository {
    private ItemDao itemDao;
    private StoreDao storeDao;

    public Repository(Application application) {
        Database database = Database.getInstance(application);
        this.itemDao = database.itemDao();
        this.storeDao = database.storeDao();
    }




    /* OPERATIONS FOR STORE TABLE */
    public long insert(Store store) throws ExecutionException, InterruptedException {
        return new InsertStoreAsyncTask(storeDao).execute(store).get();
    }

    public int update(Store store) throws ExecutionException, InterruptedException {
        return new UpdateStoreAsyncTask(storeDao).execute(store).get();
    }

    public int delete(Store store) throws ExecutionException, InterruptedException {
        return new DeleteStoreAsyncTask(storeDao).execute(store).get();
    }

    public LiveData<Store> getStore(String storeName) {
        return storeDao.getStore(storeName);
    }

    public List<String> getAllStoreNames() throws ExecutionException, InterruptedException {
        return new GetAllStoreNamesAsyncTask(storeDao).execute().get();
    }

    public LiveData<List<Store>> getAllStores() {
        return storeDao.getAllStores();
    }


    /* DEFAULT OPERATIONS FOR ITEM TABLE */
    public long insert(Item item) throws ExecutionException, InterruptedException {
        return new InsertItemAsyncTask(itemDao).execute(item).get();
    }

    public int update(Item item) throws ExecutionException, InterruptedException {
        return new UpdateItemAsyncTask(itemDao).execute(item).get();
    }

    public int delete(Item item) throws ExecutionException, InterruptedException {
        return new DeleteItemAsyncTask(itemDao).execute(item).get();
    }

    public List<Item> getItemList(String storeName) throws ExecutionException, InterruptedException {
        return new GetItemListAsyncTask(itemDao).execute(storeName).get();
    }

    public LiveData<List<Item>> getAllItems(String storeName) throws ExecutionException, InterruptedException {
        return new GetAllItemsAsyncTask(itemDao).execute(storeName).get();
    }

    /* ASYNCTASKS */
    private static class InsertStoreAsyncTask extends AsyncTask<Store, Void, Long> {
        private StoreDao storeDao;

        private InsertStoreAsyncTask(StoreDao storeDao) {
            this.storeDao = storeDao;
        }

        @Override
        protected Long doInBackground(Store...stores) {
            return storeDao.insert(stores[0]);
        }
    }

    private static class UpdateStoreAsyncTask extends AsyncTask<Store, Void, Integer> {
        private StoreDao storeDao;

        private UpdateStoreAsyncTask(StoreDao storeDao) {
            this.storeDao = storeDao;
        }

        @Override
        protected Integer doInBackground(Store...stores) {
            return storeDao.update(stores[0]);
        }
    }

    private static class DeleteStoreAsyncTask extends AsyncTask<Store, Void, Integer> {
        private StoreDao storeDao;

        private DeleteStoreAsyncTask(StoreDao storeDao) {
            this.storeDao = storeDao;
        }

        @Override
        protected Integer doInBackground(Store...stores) {
            return storeDao.delete(stores[0]);
        }
    }

    private static class GetAllStoreNamesAsyncTask extends AsyncTask<Void, Void, List<String>> {
        private StoreDao storeDao;

        private GetAllStoreNamesAsyncTask(StoreDao storeDao) {
            this.storeDao = storeDao;
        }

        @Override
        protected List<String> doInBackground(Void...voids) {
            return storeDao.getAllNames();
        }
    }



    private static class InsertItemAsyncTask extends AsyncTask<Item, Void, Long> {
        private ItemDao itemDao;

        private InsertItemAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Long doInBackground(Item...items) {
            return itemDao.insert(items[0]);
        }
    }

    private static class UpdateItemAsyncTask extends AsyncTask<Item, Void, Integer> {
        private ItemDao itemDao;

        private UpdateItemAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Integer doInBackground(Item...items) {
            return itemDao.update(items[0]);
        }
    }

    private static class DeleteItemAsyncTask extends AsyncTask<Item, Void, Integer> {
        private ItemDao itemDao;

        private DeleteItemAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Integer doInBackground(Item...items) {
            return itemDao.delete(items[0]);
        }
    }

    private static class GetItemListAsyncTask extends AsyncTask<String, Void, List<Item>> {
        private ItemDao itemDao;

        private GetItemListAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected List<Item> doInBackground(String...strings) {
            return itemDao.getItemList(strings[0]);
        }
    }

    private static class GetAllItemsAsyncTask extends AsyncTask<String, Void, LiveData<List<Item>>> {
        private ItemDao itemDao;

        private GetAllItemsAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected LiveData<List<Item>> doInBackground(String...strings) {
            return itemDao.getAllItems(strings[0]);
        }
    }


}
