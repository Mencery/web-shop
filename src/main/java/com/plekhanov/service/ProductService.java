package com.plekhanov.service;

import com.plekhanov.db.ProductDAO;
import com.plekhanov.entity.product.Product;
import com.plekhanov.repository.ProductRepository;

import javax.servlet.ServletContext;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Predicate;

import static com.plekhanov.constant.listener.ListenerConstants.CONTEXT_PRODUCTS_DAO;

public class ProductService {
    private ProductRepository productRepository;
    private ServletContext servletContext;

    /**
     *
     * @param servletContext - context of site
     */
    public ProductService(ServletContext servletContext) {
        this.servletContext = servletContext;
        productRepository = new ProductRepository();
    }

    /**
     * loads product from db to repository
     */
    public void loadDAOProducts() {
        try {
            ProductDAO dao = (ProductDAO) servletContext.getAttribute(CONTEXT_PRODUCTS_DAO);
            List<Product> products = dao.getAll();
            productRepository.setProducts(products);
            productRepository.setCurrentProducts(products);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return list of current products
     */
    public List<Product> getCurrentProducts() {
        return productRepository.getCurrentProducts();
    }
    /**
     *
     * @return list of current products
     */
    public List<Product> getProducts() {
        return productRepository.getProducts();
    }
    /**
     *
     * @return set of manufacturers
     */
    public Set<String> getManufacturers() {
        Set<String> manufacturers = new HashSet<>();
        productRepository.getProducts().forEach(product -> manufacturers.add(product.getManufacturer()));
        return manufacturers;
    }

    /**
     *
     * @return set of names
     */
    public Set<String> getNames() {
        Set<String> names = new TreeSet<>();
        productRepository.getProducts().forEach(product -> names.add(product.getName()));
        return names;
    }

    /**
     *
     * @return set of categories
     */
    public Set<String> getCategories() {
        Set<String> categories = new HashSet<>();
        productRepository.getProducts().forEach(product -> categories.add(product.getCategory()));
        return categories;
    }

    /**
     *
     * @return min price from products
     */
    public int getMinPrice() {
        Optional<Product> optional = productRepository.getProducts().stream().min(Comparator.comparing(Product::getPrice));
        return optional.map(product -> product.getPrice().intValue()).orElse(0);
    }

    /**
     *
     * @return max price from products
     */
    public int getMaxPrice() {
        Optional<Product> optional = productRepository.getProducts().stream().max(Comparator.comparing(Product::getPrice));
        return optional.map(product -> product.getPrice().intValue()).orElse(0);
    }

    /**
     *
     * @param manufacturers - list of available manufacturers
     * @return predicate for {@link Product} manufacturers
     */
    public Predicate<Product> filterProductByManufacturers(List<String> manufacturers) {
        if (manufacturers.size() > 0) {
            return product -> manufacturers.contains(product.getManufacturer());
        } else {
            return product -> true;
        }
    }
    /**
     *
     * @param categories - list of available categories
     * @return predicate for {@link Product} categories
     */
    public Predicate<Product> filterProductByCategories(List<String> categories) {
        if (categories.size() > 0) {
            return product -> categories.contains(product.getCategory());
        } else {
            return product -> true;
        }
    }
    /**
     *
     * @param names - list of available names
     * @return predicate for {@link Product} names
     */
    public Predicate<Product> filterProductByNames(List<String> names) {
        if (names.size() > 0) {
            return product -> names.contains(product.getName());
        } else {
            return product -> true;
        }
    }

    /**
     *
     * @param min - min price
     * @param max - max price
     * @return predicate for {@link Product} price
     */
    public Predicate<Product> filterProductByPriceRange(int min, int max) {
        return product -> product.getPrice().intValue() >= min && product.getPrice().intValue() <= max;
    }

    /**
     *filter products by predicate
     * @param predicate - predicate is sent to {@link ProductRepository}
     */
    public void filterProducts(Predicate<Product> predicate) {
        productRepository.setCurrentProducts(productRepository.filterProducts(predicate));
    }

    /**
     * sort products by comparator
     * @param comparator - is sent to {@link ProductRepository}
     */
    public void sortProducts(Comparator<Product> comparator){
       productRepository.getCurrentProducts().sort(comparator);
    }
}
