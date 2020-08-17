package com.plekhanov.db;

import com.plekhanov.constant.db.DBCommands;
import com.plekhanov.entity.order.Order;

import java.sql.*;
import java.util.List;

public class OrderDAO implements DAO<Order>{
    private TransactionManager transactionManager;

    /**
     * initialisation {@link TransactionManager}
     */
    public OrderDAO() {
        transactionManager = new TransactionManager();
    }

    /**
     *
     * @param item - that adding to database
     * @return order id in db
     * @throws SQLException - because works with database able to throw {@link SQLException}
     */
    @Override
    public int add(Order item) throws SQLException {
        int id;
        Connection con = transactionManager.getConnection();
        PreparedStatement pstmt = transactionManager
                .getPreparedStatement(con, Statement.RETURN_GENERATED_KEYS, DBCommands.SQL_ADD_ORDER);
        setOrderParameters(pstmt,item);
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        if(rs.next()){
            id = rs.getInt(1);
        }else{
            throw new SQLException();
        }
        transactionManager.commit(con);
        transactionManager.close(con, pstmt, rs);
        return id;
    }

    /**
     *
     * @return {@link UnsupportedOperationException}
     */
    @Override
    public List<Order> getAll()  {
        throw new UnsupportedOperationException();
    }
    /**
     *
     * @return {@link UnsupportedOperationException}
     */
    @Override
    public <E> Order getById(E id)  {
        throw new UnsupportedOperationException();
    }

    /**
     * set {@link Order} parameters in {@link PreparedStatement}
     *
     * @param pstmt - statement
     * @param order  - get parameters
     * @throws SQLException - because works with database able to throw SQLException
     */
    private void setOrderParameters(PreparedStatement pstmt, Order order) throws SQLException {
        int k = 1;
        pstmt.setInt(k++, order.getStatus().getValue());
        pstmt.setDate(k++, order.getDate());
        pstmt.setString(k, order.getUserEmail());
    }
}
