package com.droidrank.checklist.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Items.class}, version = 1)
public abstract class ItemsDatabase extends RoomDatabase {

        private static final String DB_NAME = "Itemsdatabase.db";
        private static volatile ItemsDatabase itemsDatabase;

        public static synchronized ItemsDatabase getInstance(Context context){
            if (itemsDatabase == null) itemsDatabase = create(context);
            return itemsDatabase;
        }

        private static ItemsDatabase create(final Context context){
            return Room.databaseBuilder(context,ItemsDatabase.class,DB_NAME).allowMainThreadQueries().build();
        }

        public abstract ItemsDao itemsDao();
}
