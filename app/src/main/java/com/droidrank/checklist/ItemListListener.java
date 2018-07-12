package com.droidrank.checklist;

import com.droidrank.checklist.database.Items;
import java.util.List;

public interface ItemListListener {

    void onErrorLoading(String error);

    void onLoadItems(List<Items> itemsArrayList);

}
