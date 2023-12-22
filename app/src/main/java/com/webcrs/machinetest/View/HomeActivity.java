package com.webcrs.machinetest.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.webcrs.machinetest.Entity.ProductEntity;
import com.webcrs.machinetest.R;
import com.webcrs.machinetest.View.Adapter.gridAdapter;
import com.webcrs.machinetest.ViewModel.homeVM;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView view;
    private ImageButton refresh;
    private ProgressBar progress;
    private homeVM viewModel;
    private gridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        getProducts();
    }

    private void getProducts(){
        viewModel.getProducts().observe(this, list->{
            if (!list.isEmpty()){
                adapter.setProducts(list);
            }
        });
    }

    private void initView(){
        findViews();
        viewModel = new ViewModelProvider(this).get(homeVM.class);
        adapter = new gridAdapter(this);
        view.setAdapter(adapter);
//      Refresh
        viewModel.getOnRefresh().observe(this, i->{
            if(i==1){
                progress.setVisibility(View.VISIBLE);
                refresh.setVisibility(View.GONE);
            }else if(i==2){
                progress.setVisibility(View.GONE);
                refresh.setVisibility(View.VISIBLE);
            }
        });
        adapter.setOnItemClickListener(entity -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("entity", entity);
            startActivity(intent);
        });
    }

    private void findViews(){
        view = findViewById(R.id.view);
        refresh = findViewById(R.id.refresh);
        ImageButton back = findViewById(R.id.back);
        back.setVisibility(View.GONE);
        progress = findViewById(R.id.progress);
        progress.setVisibility(View.GONE);
        refresh.setOnClickListener(v-> viewModel.refresh());
    }
}