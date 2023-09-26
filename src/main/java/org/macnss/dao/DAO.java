package org.macnss.dao;

import org.macnss.Database.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    final Connection connection = Database.getConnection();

    T insert(T t);
    T update(T t);
    boolean delete(String slag) throws SQLException;
    T get(String id) throws SQLException;
    List<T> getAll() throws SQLException;
}
