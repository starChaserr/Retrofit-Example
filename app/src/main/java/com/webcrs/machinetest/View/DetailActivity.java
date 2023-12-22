package com.webcrs.machinetest.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.webcrs.machinetest.Entity.ProductEntity;
import com.webcrs.machinetest.R;

public class DetailActivity extends AppCompatActivity {

    private ImageView image;
    private TextView title, category, review, desc, price;
    private RatingView ratingView;
    private ProductEntity entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        entity = (ProductEntity) intent.getSerializableExtra("entity");
        initView();
    }

    private void initView(){
        findView();
        Glide.with(this).load(entity.getImage()).into(image);
        title.setText(entity.getTitle());
        price.setText(String.valueOf(entity.getPrice()));
        String r = entity.getRatingCount() +" Reviews";
        review.setText(r);
        ratingView.setRating((float) entity.getRating());
        category.setText(entity.getCategory());
        desc.setText(entity.getDescription());
    }

    private void findView(){
        ImageButton reload = findViewById(R.id.refresh);
        ProgressBar progressBar = findViewById(R.id.progress);
        reload.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        ImageButton back = findViewById(R.id.back);
        back.setOnClickListener(v-> finish());

        image = findViewById(R.id.image);
        title = findViewById(R.id.title);
        category = findViewById(R.id.category);
        review = findViewById(R.id.count);
        desc = findViewById(R.id.description);
        price = findViewById(R.id.price);
        ratingView = findViewById(R.id.rating);
    }
}