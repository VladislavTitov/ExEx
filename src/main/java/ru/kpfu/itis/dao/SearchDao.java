package ru.kpfu.itis.dao;

import java.util.List;

public interface SearchDao<T> {
    List<T> search(String s) throws InterruptedException;
}
