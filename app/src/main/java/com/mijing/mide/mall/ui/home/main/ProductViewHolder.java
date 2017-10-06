package com.mijing.mide.mall.ui.home.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mijing.mide.mall.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/1.
 */

class ProductViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.image_product)
    ImageView imageProduct;
    @Bind(R.id.tv_product_info)
    TextView tvProductInfo;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.btn_buy)
    TextView btnBuy;

    private ProductViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public static ProductViewHolder newInstance(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_main_product, parent, false);
        return new ProductViewHolder(view);
    }

    void bind(@NonNull Object data) {
        // set data to view
        tvProductInfo.setText((String) data);
    }
}
