package com.mijing.mide.mall.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mijing.mide.mall.R;
import com.mijing.mide.mall.app.UI;

import java.util.List;


/**
 * Created by jiaolongfei on 2016/10/26.
 * <p>
 * RecyclerView的下拉刷新、加载更多的封装(以替代ListView)
 * 使用时结合 R.layout.xcr_recyclerview
 */
public class RecyclerViewHandler {

    private View rootView;
    private Context mContext;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView tipText;   // 无数据或错误提示

    private RecyclerView mRecycler;
    private DefaultRecyclerAdapter mAdapter;

    private boolean enableSwipeRefresh;
    private boolean enableBottomRefresh;

    private boolean isEmptyStatus;
    private boolean loading;
    private boolean hasMoreData;
    private boolean isHadLoadMore;
    private boolean tipedAllDataLoaded;

    LinearLayoutManager layoutManager;

    public RecyclerViewHandler(Context context) {
        this.mContext = context;
        initView();
    }

    public RecyclerViewHandler(View rootView) {
        this.rootView = rootView;
        mContext = rootView.getContext();
        initView();
    }

    public void setAdapter(DefaultRecyclerAdapter adapter) {
        this.mAdapter = adapter;
        mRecycler.setAdapter(adapter);
    }

    private void initView() {
        if (rootView == null) {
            rootView = LayoutInflater.from(mContext).inflate(R.layout.xcr_recyclerview, null);
        }

        // 下拉刷新组件
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.xcr_swipe_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setEnabled(enableSwipeRefresh);

        // RecyclerView初始化
        mRecycler = (RecyclerView) rootView.findViewById(R.id.xcr_recycler_view);
        layoutManager = new LinearLayoutManager(mContext); // 线性布局
        mRecycler.setLayoutManager(layoutManager);
        //mRecycler.addItemDecoration(new DefaultItemDiver(mContext));

        tipText = (TextView) rootView.findViewById(R.id.xcr_listview_tip_text);
        tipText.setVisibility(View.GONE);

        // 加载更多的事件监听
        mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                int totalCount = layoutManager.getItemCount();

                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                if (mBottomRefreshCallback != null && !loading) {
                    if (lastVisibleItem == totalCount - 1) {
                        if (hasMoreData) {
                            mBottomRefreshCallback.onLoadMore();
                            loading = true;
                            isHadLoadMore = true;
                        } else {
                            if (!tipedAllDataLoaded && isHadLoadMore) {
                                tipedAllDataLoaded = true;

                                if (layoutManager.getItemCount() > 10) {
                                    UI.showToast("没有更多数据");
                                }
                            }
                        }
                    }
                }

            }

        });

    }

    public View getRootView() {
        return rootView;
    }

    public RecyclerView getRecyclerView() {
        return this.mRecycler;
    }

    /**
     * 显示没有数据的提示
     *
     * @param emptyTip
     */
    public void showEmptyText(CharSequence emptyTip) {
        isEmptyStatus = true;
        swipeRefreshLayout.setEnabled(false);
        mRecycler.setVisibility(View.GONE);
        tipText.setVisibility(View.VISIBLE);
        tipText.setText(emptyTip);
    }

    /**
     * 显示内容，如当页面显示“没有数据”到刷新后有数据，需要显示内容
     */
    public void showContent() {
        isEmptyStatus = false;
        swipeRefreshLayout.setEnabled(enableSwipeRefresh);
        mRecycler.setVisibility(View.VISIBLE);
        tipText.setVisibility(View.GONE);
    }

    /**
     * 下拉刷新完成
     */
    public void onSwipeRefreshCompleted() {
        if (mRefreshListener != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    /**
     * 底部加载完成
     */
    public void onLoadMoreCompleted() {
        loading = false;
    }

    public void setHasMoreData(boolean hasMoreData) {
        this.hasMoreData = hasMoreData;
        if (mAdapter != null) {
            mAdapter.setHasMoreData(hasMoreData);
        }
    }

    // 下拉刷新
    SwipeRefreshLayout.OnRefreshListener mRefreshListener;

    public void setRefreshListener(SwipeRefreshLayout.OnRefreshListener callback) {
        if (callback != null) {
            this.mRefreshListener = callback;
            enableSwipeRefresh = true;
            swipeRefreshLayout.setEnabled(true);
            swipeRefreshLayout.setOnRefreshListener(mRefreshListener);
        }else {
            enableBottomRefresh = false;
            this.mRefreshListener = null;
            swipeRefreshLayout.setEnabled(false);
            swipeRefreshLayout.setOnRefreshListener(null);
        }
    }

    public interface LoadMoreListener {
        void onLoadMore();
    }

    // 底部加载更多
    LoadMoreListener mBottomRefreshCallback;

    public void setLoadMoreListener(LoadMoreListener callback) {
        if (callback != null) {
            this.mBottomRefreshCallback = callback;
            enableBottomRefresh = true;
        }else {
            this.mBottomRefreshCallback = null;
            enableBottomRefresh = false;
        }
    }

    //*************************************************************************************

    public interface OnRecycItemClickListener<T> {
        void onClick(View itemView, T itemData);
    }

    //*************************************** Adapter **********************************************

    /**
     * 继承此类来快速实现Adapter功能，附加下拉刷新、加载更多的处理
     * 结合RecyclerViewHandler使用
     *
     * @param <T>
     */
    public static abstract class DefaultRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        public static final int TYPE_NORMAL = 0;
        public static final int TYPE_FOOTER = 1;

        private Context context;
        private List<T> data;
        private OnRecycItemClickListener onItemClickListener;
        private boolean hasMoreData;

        public DefaultRecyclerAdapter(Context context, List<T> data) {
            this.context = context;
            this.data = data;
        }

        public void setOnItemClickListener(OnRecycItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        void setHasMoreData(boolean hasMoreData) {
            this.hasMoreData = hasMoreData;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_FOOTER) {
                View footerView = LayoutInflater.from(context).inflate(R.layout.xcr_list_footer, parent, false);
                return new FooterViewHolder(footerView);
            }

            final RecyclerView.ViewHolder holder = onCreateViewHolder(parent);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onClick(holder.itemView, data.get(holder.getAdapterPosition()));
                    }
                }
            });
            return holder;
        }

        /**
         * @param parent
         * @return
         */
        public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent);

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (position < data.size()) {
                onBindViewHolder(holder, data.get(position));
            }
        }

        /**
         * 绑定ViewHolder和item数据
         *
         * @param holder
         * @param data
         */
        public abstract void onBindViewHolder(RecyclerView.ViewHolder holder, T data);

        @Override
        public int getItemViewType(int position) {
            if (hasMoreData && position == data.size()) {
                return TYPE_FOOTER;
            }

            return TYPE_NORMAL;
        }

        @Override
        public int getItemCount() {
            return hasMoreData ? data.size() + 1 : data.size();
        }

        // footer
        class FooterViewHolder extends RecyclerView.ViewHolder {
            //ProgressBar progressBar;

            public FooterViewHolder(View itemView) {
                super(itemView);
                //progressBar = (ProgressBar) itemView.findViewById(R.id.re_progress);
            }
        }
    }

    //***************************** RecyclerView分割线 ***********************************

    class DefaultItemDiver extends RecyclerView.ItemDecoration{

        private final int[] ATTRS = new int[]{
                android.R.attr.listDivider
        };

        public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

        public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

        private Drawable mDivider;

        private int mOrientation;

        public DefaultItemDiver(Context context) {
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();
            // 这里设置为水平分割线
            setOrientation(VERTICAL_LIST);
        }

        public void setOrientation(int orientation) {
            if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
                throw new IllegalArgumentException("invalid orientation");
            }
            mOrientation = orientation;
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent) {
            if (mOrientation == VERTICAL_LIST) {
                drawVertical(c, parent);
            } else {
                drawHorizontal(c, parent);
            }
        }

        public void drawVertical(Canvas c, RecyclerView parent) {
            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int top = child.getBottom() + params.bottomMargin +
                        Math.round(ViewCompat.getTranslationY(child));
                final int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        public void drawHorizontal(Canvas c, RecyclerView parent) {
            final int top = parent.getPaddingTop();
            final int bottom = parent.getHeight() - parent.getPaddingBottom();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int left = child.getRight() + params.rightMargin +
                        Math.round(ViewCompat.getTranslationX(child));
                final int right = left + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
            if (mOrientation == VERTICAL_LIST) {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            }
        }
    }
}
