package com.android.szparag.github_graphql_doodle.decorators;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.szparag.github_graphql_doodle.utils.Utils;

/**
 * Created by ciemek on 06/11/2016.
 */

public class HorizontalSeparator extends RecyclerView.ItemDecoration{

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private Drawable separator;
    private int padding = 15;


    public HorizontalSeparator(Context context) {
        final TypedArray styledAttributes = context.obtainStyledAttributes(ATTRS);
        separator = styledAttributes.getDrawable(0);
        styledAttributes.recycle();
    }

    public HorizontalSeparator(Context context, int separatorDrawableId) {
        separator = ContextCompat.getDrawable(context, separatorDrawableId);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        left += padding;
        int right = parent.getWidth() - parent.getPaddingRight();
        right -= padding;

        onDrawTopSeparator(c, parent, left, right);
        onDrawBetweenRecyclerItems(c, parent, left, right);
        onDrawBottomSeparator(c, parent, left, right);
    }

    private void onDrawTopSeparator(Canvas canvas, RecyclerView parent, int left, int right) {
        View child;
        RecyclerView.LayoutParams params;
        int top, bottom;

        try {
            child = parent.getChildAt(0);
            params = (RecyclerView.LayoutParams) child.getLayoutParams();

            top = child.getTop() - params.bottomMargin;
            bottom = top + separator.getIntrinsicHeight();

            separator.setBounds(left, top, right, bottom);
            separator.draw(canvas);
        } catch (NullPointerException exc) {
            Utils.logException(exc);
        }
    }

    private void onDrawBottomSeparator(Canvas canvas, RecyclerView parent, int left, int right) {
        View child;
        RecyclerView.LayoutParams params;
        int top, bottom;

        try {
            child = parent.getChildAt(parent.getChildCount() - 1);
            params = (RecyclerView.LayoutParams) child.getLayoutParams();

            top = child.getTop() - params.bottomMargin;
            bottom = top + separator.getIntrinsicHeight();

            separator.setBounds(left, top, right, bottom);
            separator.draw(canvas);
        } catch (NullPointerException exc) {
            Utils.logException(exc);
        }
    }

    private void onDrawBetweenRecyclerItems(Canvas canvas, RecyclerView parent, int left, int right) {
        int childCount = parent.getChildCount();
        View child;
        RecyclerView.LayoutParams params;
        int top, bottom;

        for (int i = 0; i < childCount; i++) {
            child = parent.getChildAt(i);

            params = (RecyclerView.LayoutParams) child.getLayoutParams();

            top = child.getBottom() + params.bottomMargin;
            bottom = top + separator.getIntrinsicHeight();

            separator.setBounds(left, top, right, bottom);
            separator.draw(canvas);
        }
    }
}
