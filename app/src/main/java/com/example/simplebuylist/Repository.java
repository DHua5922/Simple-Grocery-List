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
    public Boolean insert(Store store) throws ExecutionException, InterruptedException {
        return new InsertStoreAsyncTask(storeDao).execute(store).get();
    }

    public Boolean update(Store store) throws ExecutionException, InterruptedException {
        return new UpdateStoreAsyncTask(storeDao).execute(store).get();
    }

    public Boolean delete(Store store) throws ExecutionException, InterruptedException {
        return new DeleteStoreAsyncTask(storeDao).execute(store).get();
    }

    public LiveData<Store> getStore(String storeName) {
        return storeDao.getStore(storeName);
    }

    public List<Store> getAllStores() throws ExecutionException, InterruptedException {
        return new GetAllStoresAsyncTask(storeDao).execute().get();
    }






    /* DEFAULT OPERATIONS FOR ITEM TABLE */
    public Boolean insert(Item item) throws ExecutionException, InterruptedException {
        return new InsertItemAsyncTask(itemDao).execute(item).get();
    }

    public Boolean update(Item item) throws ExecutionException, InterruptedException {
        return new UpdateItemAsyncTask(itemDao).execute(item).get();
    }

    public Boolean delete(Item item) throws ExecutionException, InterruptedException {
        return new DeleteItemAsyncTask(itemDao).execute(item).get();
    }

    public LiveData<Item> getItem(Item item) {
        return itemDao.getItem(item.getId());
    }




    /* SORT ITEMS BY NAMES */
    public List<Item> sortAToZ(String storeName) throws ExecutionException, InterruptedException {
        return new SortAToZAsyncTask(itemDao).execute(storeName).get();
    }

    public List<Item> sortZToA(String storeName) throws ExecutionException, InterruptedException {
        return new SortZToAAsyncTask(itemDao).execute(storeName).get();
    }

    public List<Item> sortPurchasedAToZ(String storeName) throws ExecutionException, InterruptedException {
        return new SortPurchasedAToZAsyncTask(itemDao).execute(storeName).get();
    }

    public List<Item> sortPurchasedZToA(String storeName) throws ExecutionException, InterruptedException {
        return new SortPurchasedZToAAsyncTask(itemDao).execute(storeName).get();
    }

    public List<Item> sortUnpurchasedAToZ(String storeName) throws ExecutionException, InterruptedException {
        return new SortUnpurchasedAToZAsyncTask(itemDao).execute(storeName).get();
    }

    public List<Item> sortUnpurchasedZToA(String storeName) throws ExecutionException, InterruptedException {
        return new SortUnpurchasedZToAAsyncTask(itemDao).execute(storeName).get();
    }





    /* SORT ITEMS BY PRICE */
    public List<Item> sortCheapest(String storeName) throws ExecutionException, InterruptedException {
        return new SortCheapestAsyncTask(itemDao).execute(storeName).get();
    }

    public List<Item> sortMostExpensive(String storeName) throws ExecutionException, InterruptedException {
        return new SortMostExpensiveAsyncTask(itemDao).execute(storeName).get();
    }

    public List<Item> sortCheapestPurchase(String storeName) throws ExecutionException, InterruptedException {
        return new SortCheapestPurchaseAsyncTask(itemDao).execute(storeName).get();
    }

    public List<Item> sortMostExpensivePurchase(String storeName) throws ExecutionException, InterruptedException {
        return new SortMostExpensivePurchaseAsyncTask(itemDao).execute(storeName).get();
    }

    public List<Item> sortCheapestUnpurchased(String storeName) throws ExecutionException, InterruptedException {
        return new SortCheapestUnpurchasedAsyncTask(itemDao).execute(storeName).get();
    }

    public List<Item> sortMostExpensiveUnpurchased(String storeName) throws ExecutionException, InterruptedException {
        return new SortMostExpensiveUnpurchasedAsyncTask(itemDao).execute(storeName).get();
    }





    /* DELETE ITEMS */
    public void deleteEverything() {
        new DeleteEverythingAsyncTask(itemDao).execute();
    }

    public void deleteAllItems(String storeName) {
        new DeleteAllItemsAsyncTask(itemDao).execute(storeName);
    }

    public void deleteAllPurchased(String storeName) {
        new DeleteAllPurchasedAsyncTask(itemDao).execute(storeName);
    }

    public void deleteAllUnpurchased(String storeName) {
        new DeleteAllUnpurchasedAsyncTask(itemDao).execute(storeName);
    }




    /* FILTER ITEMS BY NAME, KEYWORD, OR TYPE */
    public List<Item> getItemsByName(String storeName, String name) throws ExecutionException, InterruptedException {
        return new GetItemsByNameAsyncTask(itemDao).execute(storeName, name).get();
    }

    public List<Item> getItemsByKeyword(String storeName, String keyword) throws ExecutionException, InterruptedException {
        return new GetItemsByKeywordAsyncTask(itemDao).execute(storeName, keyword).get();
    }

    public List<Item> getPurchasedItems(String storeName) throws ExecutionException, InterruptedException {
        return new GetPurchasedItemsAsyncTask(itemDao).execute(storeName).get();
    }

    public List<Item> getUnpurchasedItems(String storeName) throws ExecutionException, InterruptedException {
        return new GetUnpurchasedItemsAsyncTask(itemDao).execute(storeName).get();
    }

    public List<Item> getAllItems(String storeName) throws ExecutionException, InterruptedException {
        return new GetAllItemsAsyncTask(itemDao).execute(storeName).get();
    }




    /* FILTER ITEMS BY PRICE */
    public List<Item> getItemsWithPriceEqualTo(String storeName, double price) throws ExecutionException, InterruptedException {
        return new GetItemsWithPriceEqualToAsyncTask(itemDao).execute(new PriceSearch(storeName, price)).get();
    }

    public List<Item> getItemsWithPriceNotEqualTo(String storeName, double price) throws ExecutionException, InterruptedException {
        return new GetItemsWithPriceNotEqualToAsyncTask(itemDao).execute(new PriceSearch(storeName, price)).get();
    }

    public List<Item> getItemsWithPriceLessThan(String storeName, double price) throws ExecutionException, InterruptedException {
        return new GetItemsWithPriceLessThanAsyncTask(itemDao).execute(new PriceSearch(storeName, price)).get();
    }

    public List<Item> getItemsWithPriceLessThanOrEqualTo(String storeName, double price) throws ExecutionException, InterruptedException {
        return new GetItemsWithPriceLessThanOrEqualToAsyncTask(itemDao).execute(new PriceSearch(storeName, price)).get();
    }

    public List<Item> getItemsWithPriceGreaterThan(String storeName, double price) throws ExecutionException, InterruptedException {
        return new GetItemsWithPriceGreaterThanAsyncTask(itemDao).execute(new PriceSearch(storeName, price)).get();
    }

    public List<Item> getItemsWithPriceGreaterThanOrEqualTo(String storeName, double price) throws ExecutionException, InterruptedException {
        return new GetItemsWithPriceGreaterThanOrEqualToAsyncTask(itemDao).execute(new PriceSearch(storeName, price)).get();
    }

    /* ASYNCTASKS */
    private static class InsertStoreAsyncTask extends AsyncTask<Store, Void, Boolean> {
        private StoreDao storeDao;

        private InsertStoreAsyncTask(StoreDao storeDao) {
            this.storeDao = storeDao;
        }

        @Override
        protected Boolean doInBackground(Store...stores) {
            return storeDao.insert(stores[0]);
        }
    }

    private static class UpdateStoreAsyncTask extends AsyncTask<Store, Void, Boolean> {
        private StoreDao storeDao;

        private UpdateStoreAsyncTask(StoreDao storeDao) {
            this.storeDao = storeDao;
        }

        @Override
        protected Boolean doInBackground(Store...stores) {
            return storeDao.update(stores[0]);
        }
    }

    private static class DeleteStoreAsyncTask extends AsyncTask<Store, Void, Boolean> {
        private StoreDao storeDao;

        private DeleteStoreAsyncTask(StoreDao storeDao) {
            this.storeDao = storeDao;
        }

        @Override
        protected Boolean doInBackground(Store...stores) {
            return storeDao.delete(stores[0]);
        }
    }

    private static class GetAllStoresAsyncTask extends AsyncTask<Void, Void, List<Store>> {
        private StoreDao storeDao;

        private GetAllStoresAsyncTask(StoreDao storeDao) {
            this.storeDao = storeDao;
        }

        @Override
        protected List<Store> doInBackground(Void...voids) {
            return storeDao.getAllStores();
        }
    }





    private static class InsertItemAsyncTask extends AsyncTask<Item, Void, Boolean> {
        private ItemDao itemDao;

        private InsertItemAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Boolean doInBackground(Item...items) {
            return itemDao.insert(items[0]);
        }
    }

    private static class UpdateItemAsyncTask extends AsyncTask<Item, Void, Boolean> {
        private ItemDao itemDao;

        private UpdateItemAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Boolean doInBackground(Item...items) {
            return itemDao.update(items[0]);
        }
    }

    private static class DeleteItemAsyncTask extends AsyncTask<Item, Void, Boolean> {
        private ItemDao itemDao;

        private DeleteItemAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Boolean doInBackground(Item...items) {
            return itemDao.delete(items[0]);
        }
    }




    /* ASYNCTASKS FOR SORTING BY NAME */
    private static class SortAToZAsyncTask extends AsyncTask<String, Void, List<Item>> {
        private ItemDao itemDao;

        private SortAToZAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected List<Item> doInBackground(String...strings) {
            return itemDao.sortAToZ(strings[0]);
        }
    }

    private static class SortZToAAsyncTask extends AsyncTask<String, Void, List<Item>> {
        private ItemDao itemDao;

        private SortZToAAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected List<Item> doInBackground(String...strings) {
            return itemDao.sortZToA(strings[0]);
        }
    }

    private static class SortPurchasedAToZAsyncTask extends AsyncTask<String, Void, List<Item>> {
        private ItemDao itemDao;

        private SortPurchasedAToZAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected List<Item> doInBackground(String...strings) {
            return itemDao.sortPurchasedAToZ(strings[0]);
        }
    }

    private static class SortPurchasedZToAAsyncTask extends AsyncTask<String, Void, List<Item>> {
        private ItemDao itemDao;

        private SortPurchasedZToAAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected List<Item> doInBackground(String...strings) {
            return itemDao.sortPurchasedZToA(strings[0]);
        }
    }

    private static class SortUnpurchasedAToZAsyncTask extends AsyncTask<String, Void, List<Item>> {
        private ItemDao itemDao;

        private SortUnpurchasedAToZAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected List<Item> doInBackground(String...strings) {
            return itemDao.sortUnpurchasedAToZ(strings[0]);
        }
    }

    private static class SortUnpurchasedZToAAsyncTask extends AsyncTask<String, Void, List<Item>> {
        private ItemDao itemDao;

        private SortUnpurchasedZToAAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected List<Item> doInBackground(String...strings) {
            return itemDao.sortUnpurchasedZToA(strings[0]);
        }
    }




    /* ASYNCTASKS FOR SORTING BY PRICE */
    private static class SortCheapestAsyncTask extends AsyncTask<String, Void, List<Item>> {
        private ItemDao itemDao;

        private SortCheapestAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected List<Item> doInBackground(String...strings) {
            return itemDao.sortCheapest(strings[0]);
        }
    }

    private static class SortMostExpensiveAsyncTask extends AsyncTask<String, Void, List<Item>> {
        private ItemDao itemDao;

        private SortMostExpensiveAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected List<Item> doInBackground(String...strings) {
            return itemDao.sortMostExpensive(strings[0]);
        }
    }

    private static class SortCheapestPurchaseAsyncTask extends AsyncTask<String, Void, List<Item>> {
        private ItemDao itemDao;

        private SortCheapestPurchaseAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected List<Item> doInBackground(String...strings) {
            return itemDao.sortCheapestPurchase(strings[0]);
        }
    }

    private static class SortMostExpensivePurchaseAsyncTask extends AsyncTask<String, Void, List<Item>> {
        private ItemDao itemDao;

        private SortMostExpensivePurchaseAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected List<Item> doInBackground(String...strings) {
            return itemDao.sortMostExpensivePurchase(strings[0]);
        }
    }

    private static class SortCheapestUnpurchasedAsyncTask extends AsyncTask<String, Void, List<Item>> {
        private ItemDao itemDao;

        private SortCheapestUnpurchasedAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected List<Item> doInBackground(String...strings) {
            return itemDao.sortCheapestUnpurchased(strings[0]);
        }
    }

    private static class SortMostExpensiveUnpurchasedAsyncTask extends AsyncTask<String, Void, List<Item>> {
        private ItemDao itemDao;

        private SortMostExpensiveUnpurchasedAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected List<Item> doInBackground(String...strings) {
            return itemDao.sortMostExpensiveUnpurchased(strings[0]);
        }
    }




    /* ASYNCTASKS FOR DELETING ITEMS */
    private static class DeleteEverythingAsyncTask extends AsyncTask<Void, Void, Void> {
        private ItemDao itemDao;

        private DeleteEverythingAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(Void...voids) {
            itemDao.deleteEverything();
            return null;
        }
    }

    private static class DeleteAllItemsAsyncTask extends AsyncTask<String, Void, Void> {
        private ItemDao itemDao;

        private DeleteAllItemsAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(String...strings) {
            itemDao.deleteAllItems(strings[0]);
            return null;
        }
    }

    private static class DeleteAllPurchasedAsyncTask extends AsyncTask<String, Void, Void> {
        private ItemDao itemDao;

        private DeleteAllPurchasedAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(String...strings) {
            itemDao.deleteAllPurchased(strings[0]);
            return null;
        }
    }

    private static class DeleteAllUnpurchasedAsyncTask extends AsyncTask<String, Void, Void> {
        private ItemDao itemDao;

        private DeleteAllUnpurchasedAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(String...strings) {
            itemDao.deleteAllUnpurchased(strings[0]);
            return null;
        }
    }





    /* ASYNCTASKS FOR FILTERING BY NAME, KEYWORD, OR TYPE */
    private static class GetItemsByNameAsyncTask extends AsyncTask<String, Void, List<Item>> {
        private ItemDao itemDao;

        private GetItemsByNameAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected List<Item> doInBackground(String...strings) {
            return itemDao.getItemsByName(strings[0], strings[1]);
        }
    }

    private static class GetItemsByKeywordAsyncTask extends AsyncTask<String, Void, List<Item>> {
        private ItemDao itemDao;

        private GetItemsByKeywordAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected List<Item> doInBackground(String...strings) {
            return itemDao.getItemsByKeyword(strings[0], strings[1]);
        }
    }

    private static class GetPurchasedItemsAsyncTask extends AsyncTask<String, Void, List<Item>> {
        private ItemDao itemDao;

        private GetPurchasedItemsAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected List<Item> doInBackground(String...strings) {
            return itemDao.getPurchasedItems(strings[0]);
        }
    }

    private static class GetUnpurchasedItemsAsyncTask extends AsyncTask<String, Void, List<Item>> {
        private ItemDao itemDao;

        private GetUnpurchasedItemsAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected List<Item> doInBackground(String...strings) {
            return itemDao.getUnpurchasedByName(strings[0]);
        }
    }

    private static class GetAllItemsAsyncTask extends AsyncTask<String, Void, List<Item>> {
        private ItemDao itemDao;

        private GetAllItemsAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected List<Item> doInBackground(String...strings) {
            return itemDao.getAllItems(strings[0]);
        }
    }





    private static class PriceSearch {
        private String storeName;
        private double price;

        private PriceSearch(String storeName, double price) {
            setStoreName(storeName);
            setPrice(price);
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
    /* ASYNCTASKS FOR FILTERING BY PRICE */
    private static class GetItemsWithPriceEqualToAsyncTask extends AsyncTask<PriceSearch, Void, List<Item>> {
        private ItemDao itemDao;

        private GetItemsWithPriceEqualToAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected List<Item> doInBackground(PriceSearch...priceSearches) {
            return itemDao.getItemsWithPriceEqualTo(priceSearches[0].getStoreName(), priceSearches[0].getPrice());
        }
    }

    private static class GetItemsWithPriceNotEqualToAsyncTask extends AsyncTask<PriceSearch, Void, List<Item>> {
        private ItemDao itemDao;

        private GetItemsWithPriceNotEqualToAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected List<Item> doInBackground(PriceSearch...priceSearches) {
            return itemDao.getItemsWithPriceNotEqualTo(priceSearches[0].getStoreName(), priceSearches[0].getPrice());
        }
    }

    private static class GetItemsWithPriceLessThanAsyncTask extends AsyncTask<PriceSearch, Void, List<Item>> {
        private ItemDao itemDao;

        private GetItemsWithPriceLessThanAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected List<Item> doInBackground(PriceSearch...priceSearches) {
            return itemDao.getItemsWithPriceLessThan(priceSearches[0].getStoreName(), priceSearches[0].getPrice());
        }
    }

    private static class GetItemsWithPriceLessThanOrEqualToAsyncTask extends AsyncTask<PriceSearch, Void, List<Item>> {
        private ItemDao itemDao;

        private GetItemsWithPriceLessThanOrEqualToAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected List<Item> doInBackground(PriceSearch...priceSearches) {
            return itemDao.getItemsWithPriceLessThanOrEqualTo(priceSearches[0].getStoreName(), priceSearches[0].getPrice());
        }
    }

    private static class GetItemsWithPriceGreaterThanAsyncTask extends AsyncTask<PriceSearch, Void, List<Item>> {
        private ItemDao itemDao;

        private GetItemsWithPriceGreaterThanAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected List<Item> doInBackground(PriceSearch...priceSearches) {
            return itemDao.getItemsWithPriceGreaterThan(priceSearches[0].getStoreName(), priceSearches[0].getPrice());
        }
    }

    private static class GetItemsWithPriceGreaterThanOrEqualToAsyncTask extends AsyncTask<PriceSearch, Void, List<Item>> {
        private ItemDao itemDao;

        private GetItemsWithPriceGreaterThanOrEqualToAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected List<Item> doInBackground(PriceSearch...priceSearches) {
            return itemDao.getItemsWithPriceGreaterThanOrEqualTo(priceSearches[0].getStoreName(), priceSearches[0].getPrice());
        }
    }
}
