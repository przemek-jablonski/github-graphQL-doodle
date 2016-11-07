package com.android.szparag.github_graphql_doodle.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ciemek on 26/09/2016.
 *
 * Base Adapter for RecyclerViews that I tend to always paste into my projects.
 *
 * @param <T> Mapped objects type
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected RecyclerOnPosClickListener    recyclerOnPosClickListener;
    protected List<T> items;


    public BaseRecyclerViewAdapter() {
        super();
        items = new ArrayList<T>();
    }

    public BaseRecyclerViewAdapter(@Nullable RecyclerOnPosClickListener clickListener) {
        this();
        if (clickListener != null) {
            setRecyclerOnPosClickListener(clickListener);
        }
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


    public void setRecyclerOnPosClickListener(RecyclerOnPosClickListener clickListener) {
        recyclerOnPosClickListener = clickListener;
    }

    public void addItem(T item) {
        items.add(item);
        notifyItemInserted(items.size()-1);
    }

    public void addItemNoNotify(T item) {
        items.add(item);
    }

    public void addItems(List<T> items) {
        items.addAll(items);
        notifyDataSetChanged();
    }

    public void updateItems(List<T> item) {
        items.clear();
        items.addAll(item);
        notifyDataSetChanged();
    }


    public List<T> getItems() {
        return items;
    }

    public T getItem(int position) {
        return items.get(position);
    }
}