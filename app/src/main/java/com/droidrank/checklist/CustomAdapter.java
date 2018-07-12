package com.droidrank.checklist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.droidrank.checklist.database.Items;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter implements View.OnClickListener {

    private List<Items> dataSet;
    private Context context;
    private DataListListener dataListListener;

    public CustomAdapter(@NonNull Context context, List<Items> dataSet, DataListListener dataListListener) {
        this.dataSet = dataSet;
        this.context = context;
        this.dataListListener = dataListListener;
    }

    public List<Items> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<Items> dataSet) {
        this.dataSet = dataSet;
    }

    private static class ViewHolder{
        TextView textView;
        CheckBox checkBox;
        Button button;
    }

    @Nullable
    @Override
    public Items getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Items Items = getItem(position);
        ViewHolder viewHolder;
        final View result;

        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.check_list_item, parent, false);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.cb_item_status);
            viewHolder.button  = (Button) convertView.findViewById(R.id.bt_item_delete);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_item_name);
            result = convertView;
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.textView.setText(Items.getItem_key());

        if(Items.getItem_check_status()) viewHolder.checkBox.setChecked(true);
        else
            viewHolder.checkBox.setChecked(false);


        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataListListener.removeItemUpdate(Items);

            }
        });

        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                final Items items = getItem(position);
                items.setItem_check_status(checked);
                dataListListener.updateCheckedItem(items);
            }
        });

        return result;
    }
}
