package com.droidrank.checklist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.droidrank.checklist.database.Items;
import com.droidrank.checklist.database.ItemsDatabase;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends Activity implements DataListListener,ItemListListener,Observer{

    // To display the items in the list
    ListView listView;
    // To add a new item
    Button addNew;

    Context context;

    CustomAdapter customAdapter;

    private ItemListPresenter itemListPresenter;

    private Observable mDataItemObservable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        itemListPresenter = new ItemListPresenter(this,context);
        listView = (ListView) findViewById(R.id.list_view);
        addNew = (Button) findViewById(R.id.bt_add_new_item);

        mDataItemObservable = DataListHolder.getInstance();
        mDataItemObservable.addObserver(this);

        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * Add a new item to the checklist
                 */
                Intent intent = new Intent(MainActivity.this, AddNewItem.class);
                startActivity(intent);
            }
        });

        itemListPresenter.onLoadItems();

    }


    private void updateListView(){
        if (customAdapter!=null) {
            customAdapter.setDataSet(ItemsDatabase.getInstance(context).itemsDao().getAllItems());
            customAdapter.notifyDataSetChanged();

        }else {
            customAdapter = new CustomAdapter(context, ItemsDatabase.getInstance(context).itemsDao().getAllItems(),this);
            listView.setAdapter(customAdapter);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void updateCheckedItem(Items item) {
        ItemsDatabase.getInstance(context).itemsDao().updateItem(item);
        updateListView();
    }

    @Override
    public void removeItemUpdate(Items dataModel) {
        ItemsDatabase.getInstance(context).itemsDao().delete(dataModel);
        updateListView();
    }

    @Override
    public void onErrorLoading(String error) {

    }

    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof DataListHolder){
            //Refresh the checklist
            updateListView();
        }
    }

    @Override
    public void onLoadItems(List<Items> itemsArrayList) {
        updateListView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDataItemObservable.deleteObserver(this);
    }
}
