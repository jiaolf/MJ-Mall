package com.mijing.mide.mall.ui.home.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.mijing.mide.mall.R;
import com.mijing.mide.mall.app.UI;
import com.mijing.mide.mall.widget.autoscrollviewpager.AutoScrollViewPager;
import com.mijing.mide.mall.widget.autoscrollviewpager.RecyclingPagerAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/30.
 */

public class HeaderViewHolder {

    private View headerView;
    private Context context;

    CirclePageIndicator indicator;
    AutoScrollViewPager bannerPager;

    public HeaderViewHolder(View headerView, Context context) {
        this.headerView = headerView;
        this.context = context;
        indicator = (CirclePageIndicator) headerView.findViewById(R.id.indicator);
        bannerPager = (AutoScrollViewPager) headerView.findViewById(R.id.bannerPager);
        init();
    }

    void init() {
        // 滚动 banner
        List<String> images = new ArrayList<>();
        images.add("http://h.hiphotos.baidu.com/image/w%3D1920%3Bcrop%3D0%2C0%2C1920%2C1080/sign=fed1392e952bd40742c7d7f449b9a532/e4dde71190ef76c6501a5c2d9f16fdfaae5167e8.jpg");
        images.add("http://a.hiphotos.baidu.com/image/w%3D1920%3Bcrop%3D0%2C0%2C1920%2C1080/sign=25d477ebe51190ef01fb96d6fc2ba675/503d269759ee3d6df51a20cd41166d224e4adedc.jpg");
        images.add("http://c.hiphotos.baidu.com/image/w%3D1920%3Bcrop%3D0%2C0%2C1920%2C1080/sign=70d2b81e60d0f703e6b291d53aca6a5e/0ff41bd5ad6eddc4ab1b5af23bdbb6fd5266333f.jpg");
        images.add("http://pic.nipic.com/pic/2007-09-19/200791919491854_4.jpg");
        images.add("http://pic.nipic.com/pic/2007-09-19/2007919193749310_4.jpg");
        images.add("http://pic.nipic.com/pic/2007-09-18/200791815388718_4.jpg");
        images.add("http://pic24.nipic.com/pic/20121025/8534374_120408983000_4.jpg");
        images.add("http://pic130.nipic.com/pic/20170505/25234336_203835734037_4.jpg");
        images.add("http://pic136.nipic.com/pic/20170713/11753421_194727168000_4.jpg");
        BannerPagerAdapter adapter = new BannerPagerAdapter(images);
        bannerPager.setAdapter(adapter);
        indicator.setViewPager(bannerPager);
        indicator.setSnap(true);
        bannerPager.setScrollFactgor(5);
        bannerPager.startAutoScroll(3000);
        bannerPager.setOnPageClickListener(new AutoScrollViewPager.OnPageClickListener() {
            @Override
            public void onPageClick(AutoScrollViewPager pager, int position) {
                UI.showToast("click position " + position);
            }
        });
    }

    class BannerPagerAdapter extends RecyclingPagerAdapter {
        List<String> images;

        public BannerPagerAdapter(List<String> imageList) {
            images = imageList;
        }

        @Override
        public View getView(int position, View view, ViewGroup container) {
            final ViewHolder holder;
            if (view == null) {
                holder = new ViewHolder();
                view = LayoutInflater.from(context).
                        inflate(R.layout.layout_auto_scroll_image, container, false);
                holder.imageView = (ImageView) view.findViewById(R.id.imageView);
                holder.progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            Glide.with(context).load(images.get(position))
                    .placeholder(R.drawable.empty_banner)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            holder.progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(holder.imageView);

            return view;
        }

        @Override
        public int getCount() {
            return images.size();
        }

        private class ViewHolder {
            ImageView imageView;
            ProgressBar progressBar;
        }
    }

}
