package com.plekhanov.db;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

import static com.plekhanov.constant.db.DAOConstants.*;

/**
 * class manages {@link Connection} to database
 */
public class TransactionManager {
    /**
     * create connection to database
     *
     * @return connection
     */
    public Connection getConnection() {
        Connection con = null;
        try {
            MysqlDataSource mds = new MysqlDataSource();
            mds.setURL(URL);
            mds.setUser(USERNAME);
            mds.setPassword(PASSWORD);
            con = mds.getConnection();
            con.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    /**
     * create {@link PreparedStatement}
     *
     * @param con      - connection to database
     * @param sqlQuery - sql query
     * @return prepared statement for sql query
     */
    public PreparedStatement getPreparedStatement(Connection con,int statement, String sqlQuery) {
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(sqlQuery,statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstmt;
    }
    /**
     * create {@link PreparedStatement}
     *
     * @param con      - connection to database
     * @param sqlQuery - sql query
     * @return prepared statement for sql query
     */
    public PreparedStatement getPreparedStatement(Connection con, String sqlQuery) {
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstmt;
    }

    /**
     * create {@link Statement}
     *
     * @param con - connection to database
     * @return statement created from connection
     */
    public Statement getStatement(Connection con) {
        Statement statement = null;
        try {
            statement = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }


    /**
     * closes a {@link Connection}
     *
     * @param con - connection that need to close
     */
    public void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * closes a {@link Statement}
     *
     * @param stmt - statement that need to close
     */
    public void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * closes a {@link ResultSet}
     *
     * @param rs - result set that need to close
     */
    public void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * closes resources.
     *
     * @param con  - connection that need to close
     * @param stmt - statement that need to close
     * @param rs   - result set that need to close
     */
    public void close(Connection con, Statement stmt, ResultSet rs) {
        close(rs);
        close(stmt);
        close(con);
    }

    /**
     * closes resources.
     *
     * @param con  - connection that need to close
     * @param stmt - statement that need to close
     */
    public void close(Connection con, Statement stmt) {
        close(stmt);
        close(con);
    }

    /**
     *
     * @param con - connection where execute commit
     */
    public void commit(Connection con) {
        try {
            con.commit();
        } catch (SQLException e) {
            rollback(con);
            e.printStackTrace();
        }
    }

    /**
     *
     * @param con - connection where execute rollback
     */
    private void rollback(Connection con){
        try {
            con.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
