package com.droidrank.checklist;

import java.util.Observable;

public class DataListHolder extends Observable{

    private static DataListHolder instance = null;

    private DataListHolder() {
    }

    public static synchronized DataListHolder getInstance() {
        if (instance == null) {
            instance = new DataListHolder();
        }
        return instance;
    }

    public void updateListItems(){
        setChanged();
        notifyObservers();
    }
}