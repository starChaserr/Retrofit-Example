package com.webcrs.machinetest.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.webcrs.machinetest.DB.localDB;
import com.webcrs.machinetest.Entity.ProductEntity;
import com.webcrs.machinetest.Model.Product;
import com.webcrs.machinetest.Remote.service;
import com.webcrs.machinetest.Remote.client;

import com.webcrs.machinetest.Dao.localDao;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class repo {
    private localDao dao;
    private service apiService;
    private MutableLiveData<Integer> onRefresh = new MutableLiveData<>();

    public repo(Application application) {
        apiService = client.getClient().create(service.class);
        localDB db = localDB.getInstance(application);
        dao = db.dao();
        onRefresh.setValue(0);
    }

    public LiveData<List<ProductEntity>> getProducts() {
        // Use Retrofit to make API call
        MutableLiveData<List<ProductEntity>> data = new MutableLiveData<>();
        apiService.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    // Convert the response to entities and update LiveData
                    data.setValue(response.body().stream().map(ProductEntity::fromProduct)
                            .collect(Collectors.toList()));
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                // Handle error
                Log.e("ProductRepository", "Failed to fetch products: " + t.getMessage());
            }
        });
        return data;
    }

    public CompletableFuture<Void> refreshProducts() {
        onRefresh.setValue(1);
        CompletableFuture<Void> c = CompletableFuture.runAsync(() -> {
            try {
                List<Product> products = apiService.getProducts().execute().body();

                List<ProductEntity> productEntities = ProductEntity.fromProductList(products);
                dao.insertProducts(productEntities);
                new InsertProductsAsyncTask(dao).execute(productEntities);
            } catch (Exception e) {
                // Handle errors, e.g., log or throw a custom exception
                e.printStackTrace();
            }
        });
        onRefresh.setValue(2);
        return c;
    }

    public MutableLiveData<Integer> getOnRefresh(){
        return onRefresh;
    }

    private static class InsertProductsAsyncTask extends AsyncTask<List<ProductEntity>, Void, Void> {
        private localDao dao;

        private InsertProductsAsyncTask(localDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(List<ProductEntity>... entities) {
            dao.insertProducts(entities[0]);
            return null;
        }
    }
}
