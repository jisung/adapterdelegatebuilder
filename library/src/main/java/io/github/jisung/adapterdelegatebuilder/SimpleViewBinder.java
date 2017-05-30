package io.github.jisung.adapterdelegatebuilder;

import android.support.annotation.NonNull;

import java.util.List;

public interface SimpleViewBinder<T> {
    void onBindViewHolder(SimpleViewHolder vh, @NonNull T item, @NonNull List<Object> payloads);
}
