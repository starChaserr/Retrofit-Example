package com.webcrs.machinetest.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.webcrs.machinetest.Dao.localDao;
import com.webcrs.machinetest.Entity.ProductEntity;

@Database(entities = ProductEntity.class, version = 1)
public abstract class localDB extends RoomDatabase {
    private static localDB instance;
    public abstract localDao dao();

    public static synchronized localDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            localDB.class, "Database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
