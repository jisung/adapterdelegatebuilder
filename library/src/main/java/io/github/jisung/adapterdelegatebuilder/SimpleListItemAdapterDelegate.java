package io.github.jisung.adapterdelegatebuilder;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate;

import java.util.List;

/**
 * More simple way to create {@link AbsListItemAdapterDelegate} with less boiler plate code
 * @param <T> The type of the item that is managed by this AdapterDelegate.
 */
public class SimpleListItemAdapterDelegate<I extends T, T> extends AbsListItemAdapterDelegate<I, T, SimpleViewHolder> {

    @LayoutRes
    private int layoutId;
    private ViewTypeChecker<T> typeChecker;
    private ViewHolderCreateListener onViewHolderCreate;
    private SimpleViewBinder<I> simpleViewBinder;

    private SimpleListItemAdapterDelegate(Builder<I,T> builder) {
        layoutId = builder.layoutId;
        typeChecker = builder.typeChecker;
        onViewHolderCreate = builder.onViewHolderCreate;
        simpleViewBinder = builder.simpleViewBinder;
    }

    @Override
    protected boolean isForViewType(@NonNull T item, @NonNull List<T> items, int position) {
        return typeChecker.isForViewType(item);
    }

    @NonNull
    @Override
    protected SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        SimpleViewHolder viewHolder = SimpleViewHolder.create(parent, layoutId);
        if (onViewHolderCreate != null) {
            onViewHolderCreate.onViewHolderCreate(viewHolder);
        }
        return viewHolder;
    }

    @Override
    protected void onBindViewHolder(@NonNull I item, @NonNull SimpleViewHolder viewHolder, @NonNull List<Object> payloads) {
        if (simpleViewBinder != null) {
            simpleViewBinder.onBindViewHolder(viewHolder, item);
        }
    }

    public static class Builder<I extends T, T> {
        private int layoutId;
        private ViewTypeChecker<T> typeChecker;
        private ViewHolderCreateListener onViewHolderCreate;
        private SimpleViewBinder<I> simpleViewBinder;

        public Builder() {
        }

        public Builder<I,T> typeChecker(ViewTypeChecker<T> val) {
            typeChecker = val;
            return this;
        }

        public Builder<I,T> viewLayout(@LayoutRes int layoutId) {
            this.layoutId = layoutId;
            return this;
        }

        public Builder<I,T> onViewHolderCreate(ViewHolderCreateListener callback) {
            this.onViewHolderCreate = callback;
            return this;
        }

        public Builder<I,T> viewBinder(SimpleViewBinder<I> val) {
            simpleViewBinder = val;
            return this;
        }

        public SimpleListItemAdapterDelegate<I,T> build() {
            return new SimpleListItemAdapterDelegate<I,T>(this);
        }
    }
}
