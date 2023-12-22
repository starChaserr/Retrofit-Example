package com.webcrs.machinetest.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.webcrs.machinetest.Model.Product;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Entity(tableName = "products")
public class ProductEntity implements Serializable {
    @PrimaryKey
    private long id;

    private String title;
    private double price;
    private String description;
    private String category;
    private String image;

    private double rating;
    private int ratingCount;

    public ProductEntity(long id, String title, double price, String description,
                         String category, String image, double rating, int ratingCount) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.image = image;
        this.rating = rating;
        this.ratingCount = ratingCount;
    }
    public ProductEntity(){}

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    public double getRating() {
        return rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }
    public static List<ProductEntity> fromProductList(List<Product> productList) {
        return productList.stream()
                .map(ProductEntity::fromProduct)
                .collect(Collectors.toList());
    }
    public static ProductEntity fromProduct(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setTitle(product.getTitle());
        productEntity.setPrice(product.getPrice());
        productEntity.setDescription(product.getDescription());
        productEntity.setCategory(product.getCategory());
        productEntity.setImage(product.getImage());

        // Extracting values from the nested Rating object
        Product.Rating rating = product.getRating();
        if (rating != null) {
            productEntity.setRating(rating.getRate());
            productEntity.setRatingCount(rating.getCount());
        }

        return productEntity;
    }
}

