package io.github.jisung.adapterdelegatebuilder;

import android.support.annotation.NonNull;

public interface SimpleViewBinderNoPayload<T> {
    void onBindViewHolder(SimpleViewHolder vh, @NonNull T item);
}
