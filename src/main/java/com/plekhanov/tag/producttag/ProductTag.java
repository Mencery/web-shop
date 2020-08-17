package com.plekhanov.tag.producttag;

import com.plekhanov.constant.servlet.product.ProductConstants;
import com.plekhanov.entity.product.Product;
import com.plekhanov.constant.filter.LocaleFilterConstants;
import com.plekhanov.constant.servlet.product.ProductServletConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * handles {@link Product} tag
 */
public class ProductTag extends SimpleTagSupport {
    private int index = 0;
    private int counter = 0;
    private ResourceBundle bundle;


    /**
     * print {@link Product}s on index.jsp
     */
    @SuppressWarnings("unchecked")
    public void doTag() {
        try {
            PageContext pc = (PageContext) getJspContext();
            JspWriter jspWriter = getJspContext().getOut();
            HttpServletRequest request = (HttpServletRequest) pc.getRequest();
            List<Product> products = (List<Product>) request.getAttribute(ProductServletConstants.PRODUCTS);
            bundle = ResourceBundle.getBundle(LocaleFilterConstants.RESOURCES, request.getLocale());
            int productOnPage = (int) request.getSession().getAttribute(ProductServletConstants.NUMBER_PRODUCTS_ON_PAGE);
            jspWriter.print(ProductServletConstants.FILTER_BUTTON_OPEN);
            jspWriter.println(bundle.getString(ProductServletConstants.INDEX_FILTER_FILTER));
            jspWriter.print(ProductServletConstants.FILTER_BUTTON_CLOSE);
            if (products == null || products.size() == 0) {
                jspWriter.print(ProductServletConstants.NO_PRODUCTS);
                return;
            }
            jspWriter.print(ProductServletConstants.SORT_SELECT_OPEN);
            jspWriter.print(
                    ProductServletConstants.OPTION_OPEN.replaceAll(ProductServletConstants.MODE, ProductServletConstants.COMPARATOR_MODE_NAME_FROM_A_Z)
                    + bundle.getString(ProductServletConstants.INDEX_SORT_FROM_A_TO_Z) + ProductServletConstants.OPTION_CLOSE);
            jspWriter.print(ProductServletConstants.OPTION_OPEN.replaceFirst(ProductServletConstants.MODE,
                    ProductServletConstants.COMPARATOR_MODE_NAME_FROM_Z_A )
                    + bundle.getString(ProductServletConstants.INDEX_SORT_FROM_Z_TO_A) + ProductServletConstants.OPTION_CLOSE);
            jspWriter.print(ProductServletConstants.OPTION_OPEN.replaceFirst(
                    ProductServletConstants.MODE, ProductServletConstants.COMPARATOR_MODE_PRICE_MORE_LESS)
                    + bundle.getString(ProductServletConstants.INDEX_SORT_FROM_L_TO_S) + ProductServletConstants.OPTION_CLOSE);
            jspWriter.print(ProductServletConstants.OPTION_OPEN.replaceFirst(
                    ProductServletConstants.MODE, ProductServletConstants.COMPARATOR_MODE_PRICE_LESS_MORE)
                    + bundle.getString(ProductServletConstants.INDEX_SORT_FROM_S_TO_L) + ProductServletConstants.OPTION_CLOSE);

            jspWriter.print(ProductServletConstants.SORT_SELECT_CLOSE);
            jspWriter.print(createPagination(products.size(), productOnPage));
            jspWriter.print(createProductGrid(products, productOnPage));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param size          - number of products
     * @param productOnPage - number of products on page
     * @return html pagination code
     */
    private String createPagination(int size, double productOnPage) {
        StringBuilder pagination = new StringBuilder(ProductServletConstants.PAGINATION_START);
        int pageNumber = (int) Math.ceil(size / productOnPage);
        for (int i = 0; i < pageNumber; i++) {
            pagination.append(ProductServletConstants.LI_OPEN);
            if (i == 0) {
                pagination.append(ProductServletConstants.ACTIVE);
            }
            pagination.append(ProductServletConstants.DATA_PAGE).append("\"").append(i + 1).append("\"").append(ProductServletConstants.CLOSE_TAG);
            pagination.append(ProductServletConstants.A_OPEN).append(i + 1).append(ProductServletConstants.A_CLOSE);
            pagination.append(ProductServletConstants.LI_CLOSE);
        }
        pagination.append(ProductServletConstants.PAGINATION_END);
        return pagination.toString();
    }

    /**
     * @param products      - list of {@link Product}
     * @param productOnPage - number of products on page
     * @return html grid of products code
     */
    private String createProductGrid(List<Product> products, double productOnPage) {
        StringBuilder productContainer = new StringBuilder();
        int pageNumber = (int) Math.ceil(products.size() / productOnPage);
        for (int k = 0; k < pageNumber; k++) {
            productContainer.append(ProductServletConstants.DIV_PAGE_OPEN);
            if (k > 0) {
                productContainer.append(ProductServletConstants.STYLE_DISPLAY_NONE);
            }
            productContainer.append(ProductServletConstants.DATA_PAGE).append("\"").append(k + 1).append("\"").append(
                    ProductServletConstants.CLOSE_TAG);
            for (int i = 0; i < (int) Math.ceil(productOnPage / ProductServletConstants.DEFAULT_COLUMN_NUMBER); i++) {
                productContainer.append(ProductServletConstants.DIV_ROW_OPEN);
                productContainer.append(createProductColumns(products, productOnPage));
                productContainer.append(ProductServletConstants.DIV_ROW_CLOSE);

            }
            counter = 0;
            productContainer.append(ProductServletConstants.DIV_CLOSE);
        }
        return productContainer.toString();
    }

    /**
     * @param products      - list of {@link Product}
     * @param productOnPage - number of products on page
     * @return html columns of products code
     */
    private String createProductColumns(List<Product> products, double productOnPage) {

        StringBuilder productColumns = new StringBuilder();
        for (int j = 0; j < ProductServletConstants.DEFAULT_COLUMN_NUMBER; j++) {
            try {
                if (counter >= productOnPage) {
                    setEmptyColumn(productColumns);
                    continue;
                }
                Product product = products.get(index++);
                productColumns.append(ProductServletConstants.DIV_COL_OPEN);
                productColumns.append(ProductServletConstants.COL_IMG_OPEN).append(product.getImgPath()).append("\"").append(
                        ProductServletConstants.COL_IMG_CLOSE);
                productColumns.append(ProductServletConstants.COL_P_NAME_OPEN).append(product.getName()).append(ProductServletConstants.DIV_CLOSE);
                productColumns.append(ProductServletConstants.COL_P_NAME_OPEN).append(product.getManufacturer()).append(
                        ProductServletConstants.DIV_CLOSE);
                productColumns.append(ProductServletConstants.COL_P_NAME_OPEN).append(product.getCategory()).append(ProductServletConstants.DIV_CLOSE);
                productColumns.append(ProductServletConstants.COL_P_PRICE_OPEN).append(product.getPrice().toString())
                        .append(ProductConstants.DOLLAR).append(ProductServletConstants.DIV_CLOSE);
                productColumns.append(ProductServletConstants.COL_P_DESC_OPEN).append(product.getDescription()).append(
                        ProductServletConstants.DIV_CLOSE);
                productColumns.append(ProductServletConstants.BTN_ADD_TO_CART_OPEN)
                        .append(ProductServletConstants.BTN_ADD_TO_CART_ID).append(product.getId()).append("\"")
                        .append(ProductServletConstants.BTN_ADD_TO_CART_CLOSE.replaceFirst(
                                ProductServletConstants.ADD_TO_CART, bundle.getString(ProductServletConstants.ADD_TO_CART)));
                productColumns.append(ProductServletConstants.DIV_CLOSE);
                counter++;
            } catch (IndexOutOfBoundsException e) {
                setEmptyColumn(productColumns);
            }

        }
        return productColumns.toString();
    }

    private void setEmptyColumn(StringBuilder productColumns) {
        productColumns.append(ProductServletConstants.DIV_COL_OPEN);
        productColumns.append(ProductServletConstants.DIV_CLOSE);
    }
}
