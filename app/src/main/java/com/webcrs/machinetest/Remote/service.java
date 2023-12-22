package com.webcrs.machinetest.Remote;

import com.webcrs.machinetest.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface service {
        @GET("products")
        Call<List<Product>> getProducts();

        @GET("products/{id}")
        Call<Product> getProduct(@Path("id") long productId);
}
