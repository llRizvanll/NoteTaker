package com.droidrank.checklist.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface ItemsDao {

    @Query("select * from items order by item_check_status = 1 , item_save_time DESC  ")
    List<Items> getAllItems();

    @Insert(onConflict = IGNORE)
    void insertItem(Items item);

    @Insert
    void insertAllItems(Items... items);

    @Query("select * from items where item_key =:itemText COLLATE NOCASE")
    List<Items> getItemDetail(String itemText);

    @Update
    void updateItem(Items item);

    @Delete
    void delete(Items item);
}
