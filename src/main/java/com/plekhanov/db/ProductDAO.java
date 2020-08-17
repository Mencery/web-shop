package com.plekhanov.db;

import com.plekhanov.constant.db.DBCommands;
import com.plekhanov.constant.servlet.product.ProductConstants;
import com.plekhanov.entity.product.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * dao for table {@link Product}
 */
public class ProductDAO implements DAO<Product> {
    private TransactionManager transactionManager;

    /**
     * initialisation {@link TransactionManager}
     */
    public ProductDAO() {
        transactionManager = new TransactionManager();
    }

    /**
     * add {@link Product} to db
     * @param item - that adding to database
     */
    @Override
    public int add(Product item) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @return all {@link Product} from db
     * @throws SQLException - because works with database able to throw {@link SQLException}
     */
    @Override
    public List<Product> getAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        Connection con = transactionManager.getConnection();
        Statement stmt = transactionManager.getStatement(con);
        ResultSet rs = stmt.executeQuery(DBCommands.SQL_FIND_ALL_PRODUCTS);
        while (rs.next()) {
            Product product = parse(rs);
            products.add(product);
        }
        transactionManager.close(con, stmt, rs);
        return products;
    }

    /**
     *
     * @param id  - unique id
     * @return {@link Product} by id
     */
    @Override
    public Product getById(Object id) {
        throw new UnsupportedOperationException();
    }

    /**
     * fill {@link Product} by {@link ResultSet}
     *
     * @param rs - result set that contains field of sql query
     * @return filled user
     * @throws SQLException - because works with database able to throw SQLException
     */
    private Product parse(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt(ProductConstants.ID));
        product.setName(rs.getString(ProductConstants.NAME));
        product.setManufacturer(rs.getString(ProductConstants.MANUFACTURER));
        product.setPrice(rs.getBigDecimal(ProductConstants.PRICE));
        product.setDescription(rs.getString(ProductConstants.DESCRIPTION));
        product.setImgPath(rs.getString(ProductConstants.IMGPATH));
        product.setCategory(rs.getInt(ProductConstants.CATEGORY));
        return product;
    }
}
