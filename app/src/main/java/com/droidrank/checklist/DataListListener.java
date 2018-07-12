package com.droidrank.checklist;

import com.droidrank.checklist.database.Items;

public interface DataListListener {

    void updateCheckedItem(Items item);

    void removeItemUpdate(Items dataModel);
}
