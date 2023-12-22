package com.webcrs.machinetest.View;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.webcrs.machinetest.R;

public class RatingView extends LinearLayout {
    private ImageView star1, star2, star3, star4, star5;

    public RatingView(Context context) {
        super(context);
        init(context);
    }

    public RatingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RatingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.rating_view, this);
        setOrientation(LinearLayout.HORIZONTAL);
        star1 = findViewById(R.id.star1);
        star2 = findViewById(R.id.star2);
        star3 = findViewById(R.id.star3);
        star4 = findViewById(R.id.star4);
        star5 = findViewById(R.id.star5);
    }

    public void setRating(float rating) {
        star1.setImageDrawable(getStarDrawable(1, rating));
        star2.setImageDrawable(getStarDrawable(2, rating));
        star3.setImageDrawable(getStarDrawable(3, rating));
        star4.setImageDrawable(getStarDrawable(4, rating));
        star5.setImageDrawable(getStarDrawable(5, rating));
    }

    private Drawable getStarDrawable(int starNumber, float rating) {
        int roundedRating = Math.round(rating);
        if (starNumber <= roundedRating) {
            return getResources().getDrawable(R.drawable.baseline_star_rate_24);
        } else if (starNumber == roundedRating + 1 && rating - roundedRating >= 0.5) {
            return getResources().getDrawable(R.drawable.baseline_star_half_24);
        } else {
            return getResources().getDrawable(R.drawable.baseline_star_outline_24);
        }
    }
}

