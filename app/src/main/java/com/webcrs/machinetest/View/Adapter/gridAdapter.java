package com.webcrs.machinetest.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.webcrs.machinetest.Entity.ProductEntity;
import com.webcrs.machinetest.R;
import com.webcrs.machinetest.View.RatingView;

import java.util.ArrayList;
import java.util.List;

public class gridAdapter extends RecyclerView.Adapter<gridAdapter.ViewHolder> {

    private List<ProductEntity> productList = new ArrayList<>();
    private onItemClickListener listener;
    private Context c;
    public gridAdapter(Context c) {
        this.c = c;
    }
    public void setProducts(List<ProductEntity> productList){
        this.productList = productList;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(productList.get(position));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title, price, count;
        private RatingView rating;

        public ViewHolder(@NonNull View v) {
            super(v);
            image = v.findViewById(R.id.image);
            title = v.findViewById(R.id.title);
            price = v.findViewById(R.id.price);
            count = v.findViewById(R.id.count);
            rating = v.findViewById(R.id.rating);

            itemView.setOnClickListener(v1 -> {
                int pos = getAdapterPosition();
                if (listener != null && pos != RecyclerView.NO_POSITION) {
                    listener.onItemClick(productList.get(pos));
                }
            });
        }

        public void bind(ProductEntity product) {
            Glide.with(c).load(product.getImage()).into(image);
            title.setText(product.getTitle());
            price.setText(String.valueOf(product.getPrice()));
            String review = product.getRatingCount() +" Reviews";
            count.setText(review);
            rating.setRating((float) product.getRating());
        }
    }

    public interface onItemClickListener {
        void onItemClick(ProductEntity entity);
    }
    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }
}
