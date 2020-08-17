package com.plekhanov.constant.servlet.product;

public class ProductServletConstants {
    public static final String PRODUCTS = "products";
    public static final String MANUFACTURERS = "manufacturers";
    public static final String NAMES = "names";
    public static final String CATEGORIES = "categories";
    public static final String CHECK_BOX_MANUFACTURERS = "manufacturer";
    public static final String CHECK_BOX_NAMES = "name";
    public static final String CHECK_BOX_CATEGORIES = "category";
    public static final String MAX_PRICE = "maxprice";
    public static final String MIN_PRICE = "minprice";
    public static final String MAX_INPUT = "inputmax";
    public static final String MIN_INPUT = "inputmin";

    public static final String SORT_MODE = "sortmode";
    public static final String COMPARATOR_MODE_NAME_FROM_A_Z = "by name from A-Z";
    public static final String COMPARATOR_MODE_NAME_FROM_Z_A = "by name from Z-A";
    public static final String COMPARATOR_MODE_PRICE_MORE_LESS = "by price from from larger to smaller";
    public static final String COMPARATOR_MODE_PRICE_LESS_MORE = "by price from from smaller to larger";

    public static final String MIN_CANNOT_BE_MORE_THEN_MAX = "from cannot be more then to";
    public static final String RANGE_ERROR = "rangeerror";

    public static final String NO_PRODUCTS = "No products";

    public static final String PAGINATION_START = "<div class=\"pagination pagination-centered pagination-large\">\n" +
            "     <ul class=\"pagination\">\n" +
            "        <li data-page=\"-\" class=\"page-item btn-success\"><a class=\"page-link\" href=\"#\"><</a></li>";

    public static final String PAGINATION_END = "<li  data-page=\"+\"class=\"page-item\"><a class=\"page-link\" href=\"#\">></a></li>\n" +
            "      </ul>\n" +
            "      </div>";
    public static final String LI_OPEN = " <li ";
    public static final String LI_CLOSE = " </li> ";
    public static final String DATA_PAGE = " data-page= ";
    public static final String CLOSE_TAG = ">";
    public static final String ACTIVE = " class=\"active\" ";
    public static final String A_OPEN = " <a href=\"#\" >";
    public static final String A_CLOSE = " </a> ";

    public static final String DIV_PAGE_OPEN = "<div ";
    public static final String STYLE_DISPLAY_NONE = "style=\"display:none;\"";

    public static final String DIV_ROW_OPEN = "\n" +
            "       <div  class=\"row justify-content-sm-around\"> ";

    public static final String DIV_ROW_CLOSE = " </div>\n" +
            "      <div class=\"indent\"></div>";

    public static final String DIV_CLOSE = "</div>";

    public static final String DIV_COL_OPEN = "<div class=\"col-sm\">";
    public static final String COL_IMG_OPEN = " <img class=\"p-img\" alt=\"no photo\" src=\"img/pictures/";
    public static final String COL_IMG_CLOSE = " /> ";
    public static final String COL_P_NAME_OPEN = "<div class=\"p-name\">";
    public static final String COL_P_PRICE_OPEN = "<div class=\"p-price\">";
    public static final String COL_P_DESC_OPEN = "<div class=\"p-desc\">";
    // public static final String BTN_ADD_TO_CART = " <button class=\"p-add \">Add to Cart</button>";
    public static final String BTN_ADD_TO_CART_OPEN = "<button class=\"p-add \"";
    public static final String BTN_ADD_TO_CART_ID = "data-id=\"";
    public static final String BTN_ADD_TO_CART_CLOSE = ">index.product.btn.addtocart</button>";
    public static final String ADD_TO_CART = "index.product.btn.addtocart";

    public static final String NUMBER_PRODUCTS_ON_PAGE = "productOnPage";
    public static final int DEFAULT_NUMBER_ON_PAGE = 8;
    public static final int DEFAULT_COLUMN_NUMBER = 4;
    public static final String FILTER_BUTTON_OPEN = " <div class=\"row\">\n" +
            "        <div class=\"col-md-1\">\n" +
            "          <button type=\"button\" class=\"p-add\" data-toggle=\"modal\" data-target=\"#exampleModal\">\n";
    public static final String INDEX_FILTER_FILTER  = "index.filter.filter";

    public static final String FILTER_BUTTON_CLOSE =  "</button>\n" +
            "           </div>\n" +
            "      </div>" +
            "<div class=\"indent\"></div>";
    public static final String SORT_SELECT_OPEN = "<div class=\"row\"><div class=\"col-md-1\">\n" +
            "<form action = \"productsort.do\"><select class = \"custom-select\" name = \"sortmode\" onchange=\"this.form.submit()\">" +
            "<option></option>";
    public static final String SORT_SELECT_CLOSE =  "</select></form>" +
            "        </div>\n" +
            "</div>";
    public static final String MODE = "mode";
    public static final String OPTION_OPEN = "<option value=\"mode\">";
    public static final String OPTION_CLOSE= "</option>";
    public static final String INDEX_SORT_FROM_A_TO_Z = "index.sort.fromA2Z";
    public static final String INDEX_SORT_FROM_Z_TO_A = "index.sort.fromZ2A";
    public static final String INDEX_SORT_FROM_L_TO_S = "index.sort.fromL2S";
    public static final String INDEX_SORT_FROM_S_TO_L = "index.sort.fromS2L";

}
