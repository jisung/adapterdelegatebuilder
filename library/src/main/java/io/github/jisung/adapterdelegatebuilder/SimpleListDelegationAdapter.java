package io.github.jisung.adapterdelegatebuilder;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter;

import java.util.List;


public class SimpleListDelegationAdapter<T> extends ListDelegationAdapter<List<T>> {

    public <I extends T> SimpleListItemAdapterDelegate.Builder<I, T> createDelegateBuilder(Class<I> itemCls) {
        return new SimpleListItemAdapterDelegate.Builder<>();
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
