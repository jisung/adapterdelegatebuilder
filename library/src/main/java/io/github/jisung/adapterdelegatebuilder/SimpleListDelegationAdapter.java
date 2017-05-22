package io.github.jisung.adapterdelegatebuilder;

import android.support.annotation.NonNull;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter;

import java.util.List;

public class SimpleListDelegationAdapter<T> extends ListDelegationAdapter<List<T>> {

    public <I extends T>
    SimpleListItemAdapterDelegate.Builder<I, T> createDelegateBuilder(final Class<I> itemCls) {
        SimpleListItemAdapterDelegate.Builder<I, T> builder = new SimpleListItemAdapterDelegate.Builder<>();
        builder = builder.typeChecker(new ViewTypeChecker<T>() {
            @Override
            public boolean isForViewType(@NonNull T item) {
                return itemCls.isInstance(item);
            }
        });
        return builder;
    }

    public <I extends T>
    SimpleListItemAdapterDelegate.Builder<I, T> createDelegateBuilder(final Class<I> itemCls, final ViewTypeChecker<I> viewTypeChecker) {
        SimpleListItemAdapterDelegate.Builder<I, T> builder = new SimpleListItemAdapterDelegate.Builder<>();
        builder = builder.typeChecker(new ViewTypeChecker<T>() {
            @Override
            public boolean isForViewType(@NonNull T item) {
                if (itemCls.isInstance(item)) {
                    return viewTypeChecker.isForViewType(itemCls.cast(item));
                }
                return false;
            }
        });
        return builder;
    }

    public void addDelegate(AdapterDelegate<List<T>> delegate) {
        delegatesManager.addDelegate(delegate);
    }

    public void setFallbackDelegate(AdapterDelegate<List<T>> fallbackDelegate) {
        delegatesManager.setFallbackDelegate(fallbackDelegate);
    }

    public void setItems(List<T> items, boolean notifyDataSetChanged) {
        super.setItems(items);
        if (notifyDataSetChanged) {
            notifyDataSetChanged();
        }
    }
}
