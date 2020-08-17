package com.plekhanov.entity.product;

import java.math.BigDecimal;
/**
 * entity for products table
 */
public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private ProductCategory category;
    private String manufacturer;
    private String description;
    private String imgPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category.toString();
    }

    public void setCategory(int category) {
        this.category = ProductCategory.valueOf(category);
    }


    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
