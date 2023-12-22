package com.webcrs.machinetest.Model;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("id")
    private long id;

    @SerializedName("title")
    private String title;

    @SerializedName("price")
    private double price;

    @SerializedName("description")
    private String description;

    @SerializedName("category")
    private String category;

    @SerializedName("image")
    private String image;

    @SerializedName("rating")
    private Rating rating;

    public Product(long id, String title, double price, String description, String category, String image, Rating rating) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.image = image;
        this.rating = rating;
    }

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

    public Rating getRating() {
        return rating;
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

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public static class Rating {
        @SerializedName("rate")
        private double rate;

        @SerializedName("count")
        private int count;

        public Rating(double rate, int count) {
            this.rate = rate;
            this.count = count;
        }

        public double getRate() {
            return rate;
        }

        public int getCount() {
            return count;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    public static Product fromProduct(Product product) {
        return new Product(product.getId(), product.getTitle(), product.getPrice(),
                product.getDescription(), product.getCategory(), product.getImage(), product.getRating());
    }
}

// Accessing rating example

// Accessing the rating object
//    Product.Rating rating = product.getRating();
//
//    // Accessing the rate and count values
//    double rate = rating.getRate();
//    int count = rating.getCount();
//
//// Now you can use the 'rate' and 'count' variables as needed
//System.out.println("Rate: " + rate);
//System.out.println("Count: " + count);
