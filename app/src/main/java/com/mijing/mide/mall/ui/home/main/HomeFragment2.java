package com.mijing.mide.mall.ui.home.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mijing.mide.mall.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment2 extends Fragment {

    ListView listView;

    List<String> mDatas = new ArrayList<>();

    public HomeFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_home, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.header_home_main, null);
        HeaderViewHolder headerViewHolder = new HeaderViewHolder(header, getContext());
        listView.addHeaderView(header);

        // test...
        for (int i = 0; i < 50; i++) {
            mDatas.add("测试测试----" + i);
        }

        MyListAdapter adapter = new MyListAdapter();
        listView.setAdapter(adapter);
    }

    class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_home_main_product, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvProductInfo.setText(mDatas.get(position));

            return convertView;
        }


        class ViewHolder {
            @Bind(R.id.image_product)
            ImageView imageProduct;
            @Bind(R.id.tv_product_info)
            TextView tvProductInfo;
            @Bind(R.id.tv_price)
            TextView tvPrice;
            @Bind(R.id.btn_buy)
            TextView btnBuy;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
