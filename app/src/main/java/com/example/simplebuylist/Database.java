package com.example.simplebuylist;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;

@androidx.room.Database(entities = {Item.class, Store.class}, version = 1)
public abstract class Database extends RoomDatabase {

    private static Database instance;

    public abstract ItemDao itemDao();
    public abstract StoreDao storeDao();

    public static synchronized Database getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    Database.class, "database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ItemDao itemDao;
        private StoreDao storeDao;

        private PopulateDbAsyncTask(Database db) {
            itemDao = db.itemDao();
            storeDao = db.storeDao();
        }

        @Override
        protected Void doInBackground(Void ... voids) {
            /*Store firstStore = storeDao.getFirstStore();
            if(firstStore != null) {
                MainActivity.STORE_NAME = firstStore.getStoreName();
                itemDao.getAllItems(MainActivity.STORE_NAME);
            }*/
            List<Store> storeList = storeDao.getAllStores();
            if(!storeList.isEmpty()) {
                itemDao.getAllItems(storeList.get(0).getStoreName());
            } else {
                itemDao.getAllItems(MainActivity.STORE_NAME);
            }
            return null;
        }
    }
}
