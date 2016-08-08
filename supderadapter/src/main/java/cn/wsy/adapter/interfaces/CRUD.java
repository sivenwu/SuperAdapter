package cn.wsy.adapter.interfaces;

import java.util.List;

/**
 * Created by wsy on 2016/7/20.
 */
public interface CRUD<T> {
    void add(T item);

    void add(int location, T item);

    @Deprecated
    void insert(int location, T item);

    void addAll(List<T> items);

    void addAll(int location, List<T> items);

    void remove(T item);

    void remove(int location);

    void removeAll(List<T> items);

    void retainAll(List<T> items);

    void set(T oldItem, T newItem);

    void set(int location, T item);

    void replaceAll(List<T> items);

    boolean contains(T item);

    boolean containsAll(List<T> items);

    void clear();
}
