package com.mijing.mide.mall.ui.home.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/9/28.
 */

public class HomeProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private Context context;
    private List<String> data;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public HomeProductAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(context).inflate(R.layout.header_home_main, parent, false);
            return new HeaderViewHolder(view, context);
        } else if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_home_main_product, parent, false);
            return new ViewHolder(view);
        }
        return null;*/

        /*View view = LayoutInflater.from(context).inflate(R.layout.item_home_main_product, parent, false);
        return new ViewHolder(view);*/

        return ProductViewHolder.newInstance(parent);
    }

    /*@Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }else {
            return TYPE_ITEM;
        }
    }*/

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        /*if (holder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) holder).init();
        }else if(holder instanceof ViewHolder){
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.tvProductInfo.setText(data.get(position-1));
        }*/

        //((ViewHolder)holder).tvProductInfo.setText(data.get(position));
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size() ;
    }

    /*public HomeProductAdapter(Context context, List<String> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_main_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, String data) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvProductInfo.setText(data);
    }*/

    /*static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.image_product)
        ImageView imageProduct;
        @Bind(R.id.tv_product_info)
        TextView tvProductInfo;
        @Bind(R.id.tv_price)
        TextView tvPrice;
        @Bind(R.id.btn_buy)
        TextView btnBuy;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }*/

}
