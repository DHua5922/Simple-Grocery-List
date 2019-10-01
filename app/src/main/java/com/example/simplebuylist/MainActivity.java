package com.example.simplebuylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView itemList = findViewById(R.id.item_list);
        ViewModel viewModel = new ViewModel(getApplication());
        ItemAdapter listAdapter = new ItemAdapter();

        try {
            List<Store> storeList = viewModel.getAllStores();
            if(storeList.size() > 0)
                listAdapter = new ItemAdapter(viewModel.getAllItems(storeList.get(0).getStoreName()));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        // Example of a call to a native method
        //tv.setText(stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
