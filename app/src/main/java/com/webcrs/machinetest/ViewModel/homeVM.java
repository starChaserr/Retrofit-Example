package com.webcrs.machinetest.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.webcrs.machinetest.Entity.ProductEntity;
import com.webcrs.machinetest.Repository.repo;

import java.util.List;

public class homeVM extends AndroidViewModel {

    private repo r;
    private final LiveData<List<ProductEntity>> products;
    private MutableLiveData<Integer> onRefresh = new MutableLiveData<>();
    public homeVM(@NonNull Application application) {
        super(application);
        r = new repo(application);
        products = r.getProducts();
        onRefresh = r.getOnRefresh();
    }

    public LiveData<List<ProductEntity>> getProducts(){
        return products;
    }

    public void refresh(){
        r.refreshProducts();
    }

    public MutableLiveData<Integer> getOnRefresh() {
        return onRefresh;
    }
}
