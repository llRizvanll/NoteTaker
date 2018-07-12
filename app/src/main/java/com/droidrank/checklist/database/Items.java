package com.droidrank.checklist.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Items {

    @PrimaryKey
    @NonNull
    private String item_key;

    @ColumnInfo(name = "item_check_status")
    private Boolean item_check_status;

    @ColumnInfo(name = "item_save_time")
    private long item_save_time;


    @NonNull
    public String getItem_key() {
        return item_key;
    }

    public void setItem_key(@NonNull String item_key) {
        this.item_key = item_key;
    }

    public Boolean getItem_check_status() {
        return item_check_status;
    }

    public void setItem_check_status(Boolean item_check_status) {
        this.item_check_status = item_check_status;
    }

    public long getItem_save_time() {
        return item_save_time;
    }

    public void setItem_save_time(long item_save_time) {
        this.item_save_time = item_save_time;
    }
}
