package com.webcrs.machinetest.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.webcrs.machinetest.Entity.ProductEntity;

import java.util.List;

@Dao
public interface localDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProducts(List<ProductEntity> entities);

}
