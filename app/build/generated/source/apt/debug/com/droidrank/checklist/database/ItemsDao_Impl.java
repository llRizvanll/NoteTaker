package com.droidrank.checklist.database;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class ItemsDao_Impl implements ItemsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfItems;

  private final EntityInsertionAdapter __insertionAdapterOfItems_1;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfItems;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfItems;

  public ItemsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfItems = new EntityInsertionAdapter<Items>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Items`(`item_key`,`item_check_status`,`item_save_time`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Items value) {
        if (value.getItem_key() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getItem_key());
        }
        final Integer _tmp;
        _tmp = value.getItem_check_status() == null ? null : (value.getItem_check_status() ? 1 : 0);
        if (_tmp == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, _tmp);
        }
        stmt.bindLong(3, value.getItem_save_time());
      }
    };
    this.__insertionAdapterOfItems_1 = new EntityInsertionAdapter<Items>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Items`(`item_key`,`item_check_status`,`item_save_time`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Items value) {
        if (value.getItem_key() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getItem_key());
        }
        final Integer _tmp;
        _tmp = value.getItem_check_status() == null ? null : (value.getItem_check_status() ? 1 : 0);
        if (_tmp == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, _tmp);
        }
        stmt.bindLong(3, value.getItem_save_time());
      }
    };
    this.__deletionAdapterOfItems = new EntityDeletionOrUpdateAdapter<Items>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Items` WHERE `item_key` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Items value) {
        if (value.getItem_key() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getItem_key());
        }
      }
    };
    this.__updateAdapterOfItems = new EntityDeletionOrUpdateAdapter<Items>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Items` SET `item_key` = ?,`item_check_status` = ?,`item_save_time` = ? WHERE `item_key` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Items value) {
        if (value.getItem_key() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getItem_key());
        }
        final Integer _tmp;
        _tmp = value.getItem_check_status() == null ? null : (value.getItem_check_status() ? 1 : 0);
        if (_tmp == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, _tmp);
        }
        stmt.bindLong(3, value.getItem_save_time());
        if (value.getItem_key() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getItem_key());
        }
      }
    };
  }

  @Override
  public void insertItem(Items item) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfItems.insert(item);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAllItems(Items... items) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfItems_1.insert(items);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Items item) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfItems.handle(item);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateItem(Items item) {
    __db.beginTransaction();
    try {
      __updateAdapterOfItems.handle(item);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Items> getAllItems() {
    final String _sql = "select * from items order by item_check_status = 1 , item_save_time DESC  ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfItemKey = _cursor.getColumnIndexOrThrow("item_key");
      final int _cursorIndexOfItemCheckStatus = _cursor.getColumnIndexOrThrow("item_check_status");
      final int _cursorIndexOfItemSaveTime = _cursor.getColumnIndexOrThrow("item_save_time");
      final List<Items> _result = new ArrayList<Items>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Items _item;
        _item = new Items();
        final String _tmpItem_key;
        _tmpItem_key = _cursor.getString(_cursorIndexOfItemKey);
        _item.setItem_key(_tmpItem_key);
        final Boolean _tmpItem_check_status;
        final Integer _tmp;
        if (_cursor.isNull(_cursorIndexOfItemCheckStatus)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getInt(_cursorIndexOfItemCheckStatus);
        }
        _tmpItem_check_status = _tmp == null ? null : _tmp != 0;
        _item.setItem_check_status(_tmpItem_check_status);
        final long _tmpItem_save_time;
        _tmpItem_save_time = _cursor.getLong(_cursorIndexOfItemSaveTime);
        _item.setItem_save_time(_tmpItem_save_time);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Items> getItemDetail(String itemText) {
    final String _sql = "select * from items where item_key =? COLLATE NOCASE";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (itemText == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, itemText);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfItemKey = _cursor.getColumnIndexOrThrow("item_key");
      final int _cursorIndexOfItemCheckStatus = _cursor.getColumnIndexOrThrow("item_check_status");
      final int _cursorIndexOfItemSaveTime = _cursor.getColumnIndexOrThrow("item_save_time");
      final List<Items> _result = new ArrayList<Items>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Items _item;
        _item = new Items();
        final String _tmpItem_key;
        _tmpItem_key = _cursor.getString(_cursorIndexOfItemKey);
        _item.setItem_key(_tmpItem_key);
        final Boolean _tmpItem_check_status;
        final Integer _tmp;
        if (_cursor.isNull(_cursorIndexOfItemCheckStatus)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getInt(_cursorIndexOfItemCheckStatus);
        }
        _tmpItem_check_status = _tmp == null ? null : _tmp != 0;
        _item.setItem_check_status(_tmpItem_check_status);
        final long _tmpItem_save_time;
        _tmpItem_save_time = _cursor.getLong(_cursorIndexOfItemSaveTime);
        _item.setItem_save_time(_tmpItem_save_time);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
