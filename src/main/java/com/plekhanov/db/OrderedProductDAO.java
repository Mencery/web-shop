package com.plekhanov.db;

import com.plekhanov.constant.db.DBCommands;
import com.plekhanov.entity.order.OrderedProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderedProductDAO implements DAO<OrderedProduct> {
    private TransactionManager transactionManager;

    /**
     * initialisation {@link TransactionManager}
     */
    public OrderedProductDAO() {
        transactionManager = new TransactionManager();
    }

    /**
     * insert {@link OrderedProduct} object in ordered_products table
     * @param item - that adding to database
     * @return ordered product id in db
     * @throws SQLException - because works with database able to throw {@link SQLException}
     */
    @Override
    public int add(OrderedProduct item) throws SQLException {
        Connection con = transactionManager.getConnection();
        PreparedStatement pstmt = transactionManager
                .getPreparedStatement(con, DBCommands.SQL_ADD_ORDERED_PRODUCT);
        setOrderedProductParameters(pstmt, item);
        pstmt.executeUpdate();
        transactionManager.commit(con);
        transactionManager.close(con, pstmt);
        return 0;
    }

    /**
     *
     * @return {@link UnsupportedOperationException}
     */
    @Override
    public List<OrderedProduct> getAll() {
        throw new UnsupportedOperationException();
    }

    /**
     * insert list of {@link OrderedProduct}  in ordered_products table
     * @param items - that insert in db
     * @throws SQLException - can throw {@link SQLException}
     */
    public void addAll(List<OrderedProduct> items) throws SQLException {
        Connection con = transactionManager.getConnection();
        PreparedStatement pstmt = null;
        for (OrderedProduct orderedProduct : items) {
            pstmt = transactionManager
                    .getPreparedStatement(con, DBCommands.SQL_ADD_ORDERED_PRODUCT);
            setOrderedProductParameters(pstmt, orderedProduct);
            pstmt.executeUpdate();
        }

        transactionManager.commit(con);
        transactionManager.close(con, pstmt);
    }

    /**
     *
     * @param id  - unique id
     * @param <E> - type of id
     * @return {@link UnsupportedOperationException}
     */
    @Override
    public <E> OrderedProduct getById(E id) {
        throw new UnsupportedOperationException();
    }
    /**
     * set {@link OrderedProduct} parameters in {@link PreparedStatement}
     *
     * @param pstmt - statement
     * @param orderedProduct  - get parameters
     * @throws SQLException - because works with database able to throw SQLException
     */
    private void setOrderedProductParameters(PreparedStatement pstmt, OrderedProduct orderedProduct) throws SQLException {
        int k = 1;
        pstmt.setInt(k++, orderedProduct.getProductId());
        pstmt.setInt(k++, orderedProduct.getOrderId());
        pstmt.setInt(k++, orderedProduct.getAmount());
        pstmt.setBigDecimal(k, orderedProduct.getPrice());
    }
}
