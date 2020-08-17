package com.plekhanov.db;

import com.plekhanov.constant.db.DBCommands;
import com.plekhanov.constant.user.UserConstants;
import com.plekhanov.entity.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.plekhanov.constant.user.UserConstants.AVATAR_PATH;
import static com.plekhanov.constant.user.UserConstants.ROLE;

/**
 * dao for table {@link User}
 */
public class UsersDAO implements DAO<User> {
    private TransactionManager transactionManager;

    /**
     * initialisation transaction manager
     */
    public UsersDAO() {
        transactionManager = new TransactionManager();
    }

    /**
     * method for add item into database
     *
     * @param item - that adding to database
     * @throws SQLException - because works with database able to throw SQLException
     */
    @Override
    public int add(User item) throws SQLException {
        Connection con = transactionManager.getConnection();
        PreparedStatement pstmt = transactionManager.getPreparedStatement(con, DBCommands.SQL_ADD_USER);
        setUserParameters(pstmt, item);
        pstmt.executeUpdate();
        transactionManager.commit(con);
        transactionManager.close(con, pstmt);
        return 0;
    }

    /**
     * method for get items from database
     *
     * @return List of items from table
     * @throws SQLException - because works with database able to throw SQLException
     */
    @Override
    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection con = transactionManager.getConnection();
        Statement stmt = transactionManager.getStatement(con);
        ResultSet rs = stmt.executeQuery(DBCommands.SQL_FIND_ALL_USERS);
        while (rs.next()) {
            User user = parse(rs);
            users.add(user);
        }
        transactionManager.close(con, stmt, rs);
        return users;
    }

    /**
     * method for get item from database
     *
     * @param id  - unique id
     * @param <E> - type of id
     * @return Item from table
     * @throws SQLException - because works with database able to throw SQLException
     */
    @Override
    public <E> User getById(E id) throws SQLException {
        User user = null;
        Connection con = transactionManager.getConnection();
        PreparedStatement pstmt = transactionManager.getPreparedStatement(con, DBCommands.SQL_FIND_USER_BY_EMAIL);
        pstmt.setString(1, (String) id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            user = parse(rs);
        }
        transactionManager.close(con, pstmt, rs);
        return user;
    }

    /**
     * change path to {@link User} avatar
     *
     * @param email    - id of user
     * @param fileName - file name
     * @throws SQLException - because works with database able to throw SQLException
     */
    public void updateAvatarPath(String email, String fileName) throws SQLException {
        Connection con = transactionManager.getConnection();
        PreparedStatement pstmt = transactionManager.getPreparedStatement(con, DBCommands.SQL_UPDATE_AVATAR);
        int k = 1;
        pstmt.setString(k++, fileName);
        pstmt.setString(k, email);
        pstmt.executeUpdate();
        transactionManager.commit(con);
        transactionManager.close(con,pstmt);
    }

    /**
     * set {@link User} parameters in {@link PreparedStatement}
     *
     * @param pstmt - statement
     * @param user  - get parameters
     * @throws SQLException - because works with database able to throw SQLException
     */
    private void setUserParameters(PreparedStatement pstmt, User user) throws SQLException {
        int k = 1;
        pstmt.setString(k++, user.getEmail());
        pstmt.setString(k++, user.getPassword());
        pstmt.setString(k++, user.getName());
        pstmt.setString(k++, user.getSurname());
        pstmt.setBoolean(k, user.isMailings());
    }

    /**
     * fill {@link User} by {@link ResultSet}
     *
     * @param rs - result set that contains field of sql query
     * @return filled user
     * @throws SQLException - because works with database able to throw SQLException
     */
    private User parse(ResultSet rs) throws SQLException {
        User user = new User();
        user.setEmail(rs.getString(UserConstants.EMAIL));
        user.setPassword(rs.getString(UserConstants.PASSWORD));
        user.setName(rs.getString(UserConstants.NAME));
        user.setSurname(rs.getString(UserConstants.SURNAME));
        user.setMailings(rs.getBoolean(UserConstants.MAILING));
        user.setAvatarPath(rs.getString(AVATAR_PATH));
        user.setRole(rs.getString(ROLE));
        return user;
    }
}
