package io.github.jisung.adapterdelegatebuilder;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SimpleViewHolder extends RecyclerView.ViewHolder {

    private final SparseArray<View> viewCache = new SparseArray<>();

    public static SimpleViewHolder create(ViewGroup parent, @LayoutRes int layoutId) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new SimpleViewHolder(itemView);
    }

    private SimpleViewHolder(View itemView) {
        super(itemView);
    }

    public View getView(@IdRes int viewId) {
        return getView(viewId, View.class);
    }

    @SuppressWarnings("unchecked, UnusedParameters")
    public <T extends View> T getView(@IdRes int viewId, Class<T> viewType) {
        T view = (T) viewCache.get(viewId);
        if (view == null) {
            view = (T) itemView.findViewById(viewId);
            viewCache.put(viewId, view);
        }
        return view;
    }
}
