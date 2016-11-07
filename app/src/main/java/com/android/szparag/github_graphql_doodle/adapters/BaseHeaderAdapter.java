package com.android.szparag.github_graphql_doodle.adapters;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;

/**
 * Created by ciemek on 07/11/2016.
 */

public abstract class BaseHeaderAdapter<T> {

    private T item;

    public abstract void bindView();

    public void updateItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

}
