package com.plekhanov.db;

import java.sql.SQLException;
import java.util.List;

/**
 * interface for dao
 *
 * @param <T> - type of entity
 */
public interface DAO<T> {
    /**
     * method for add item into database
     *
     * @param item - that adding to database
     * @throws SQLException - because works with database able to throw SQLException
     */
    int  add(T item) throws SQLException;

    /**
     * method for get items from database
     *
     * @return List of items from table
     * @throws SQLException - because works with database able to throw SQLException
     */
    List<T> getAll() throws SQLException;

    /**
     * method for get item from database
     *
     * @param id  - unique id
     * @param <E> - type of id
     * @return Item from table
     * @throws SQLException - because works with database able to throw SQLException
     */
    <E> T getById(E id) throws SQLException;
}
