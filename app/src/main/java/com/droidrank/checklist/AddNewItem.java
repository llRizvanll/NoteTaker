package com.droidrank.checklist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.droidrank.checklist.database.Items;
import com.droidrank.checklist.database.ItemsDatabase;

import java.util.Observable;
import java.util.Observer;

public class AddNewItem extends AppCompatActivity implements Observer{

    // To take the user input for the new item
    EditText itemName;
    // To add the new item to the list
    Button addItem;
    // To cancel this task
    Button cancel;

    private Context context;

    private Observable mDataItemObservable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);
        itemName = (EditText) findViewById(R.id.et_item_name);
        cancel = (Button) findViewById(R.id.bt_cancel);
        addItem = (Button) findViewById(R.id.bt_ok);
        context = this;
        mDataItemObservable = DataListHolder.getInstance();
        mDataItemObservable.addObserver(this);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 *Save the new item, if it does not exist in the list
                 *else display appropriate error message
                 */
                // finish the current activity

                String text = itemName.getText().toString().trim();
                if(ItemsDatabase.getInstance(context).itemsDao().getItemDetail(text).size() > 0){
                    Toast.makeText(context,"Duplicate entry!",Toast.LENGTH_SHORT).show();
                    return;
                }

                Items items = new Items();
                items.setItem_key(text);
                items.setItem_save_time(System.currentTimeMillis());
                items.setItem_check_status(false);
                ItemsDatabase.getInstance(context).itemsDao().insertItem(items);
                DataListHolder.getInstance().updateListItems();
                mDataItemObservable.notifyObservers();
                finish();
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * Do nothing and close this activity
                 */
                finish();
            }
        });
    }

    @Override
    public void update(Observable observable, Object data) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDataItemObservable.deleteObserver(this);
    }
}
