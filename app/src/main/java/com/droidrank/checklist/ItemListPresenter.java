package com.droidrank.checklist;

import android.content.Context;

import com.droidrank.checklist.database.Items;
import com.droidrank.checklist.database.ItemsDatabase;

import java.util.ArrayList;
import java.util.List;

public class ItemListPresenter {

    private ItemListListener itemListListener;
    private Context context;

    public ItemListPresenter(ItemListListener itemListListener, Context context) {
        this.itemListListener = itemListListener;
        this.context          = context;
    }


    public void onLoadItems() {
        if (ItemsDatabase.getInstance(context).itemsDao().getAllItems().size() < 1) {
            String[] arr = context.getResources().getStringArray(R.array.default_items);

            for (int i = 0; i < arr.length; i++) {
                Items item = new Items();
                item.setItem_key(arr[i]);
                item.setItem_check_status(false);
                item.setItem_save_time(System.currentTimeMillis());
                //insert items
                ItemsDatabase.getInstance(context).itemsDao().insertItem(item);
            }
        }
        itemListListener.onLoadItems(ItemsDatabase.getInstance(context).itemsDao().getAllItems());
    }
}
