package com.plekhanov.constant.db;

public class DBCommands {
    public static final String SQL_FIND_USER_BY_EMAIL = "SELECT email, password, name, surname, mailings, avatarpath, role FROM users WHERE email = ?";
    public static final String SQL_FIND_ALL_USERS = "Select email, password, name, surname, mailings, avatarpath, role FROM users ";
    public static final String SQL_ADD_USER =
            "insert into users(email, password, name, surname, mailings) " +
                    "values(?,?,?,?,?)";
    public static final String SQL_UPDATE_AVATAR = "update users set avatarpath = ? where email = ?";

    public static final String SQL_FIND_ALL_PRODUCTS = "Select id, name, manufacturer, price, description, imgpath, category_id FROM products ";

    public static final String SQL_ADD_ORDER = "insert into orders( status_id, date, user_email) values(?,?,?)";

    public static final String SQL_ADD_ORDERED_PRODUCT = "insert into ordered_products(product_id, order_id, amount, price) values(?,?,?,?)";

}
