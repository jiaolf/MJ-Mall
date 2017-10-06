package com.mijing.mide.mall.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.mijing.mide.mall.R;
import com.mijing.mide.mall.base.BaseActivity;
import com.mijing.mide.mall.ui.category.AllCategoryFragment;
import com.mijing.mide.mall.ui.home.MainTabFragment;
import com.mijing.mide.mall.ui.mycenter.MyFragment;
import com.mijing.mide.mall.ui.shoppingcart.CartFragment;
import com.mijing.mide.mall.ui.social.SocialFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @Bind(R.id.btn_tab_home)
    RadioButton btnTabHome;
    @Bind(R.id.btn_tab_category)
    RadioButton btnTabCategory;
    @Bind(R.id.btn_tab_social)
    RadioButton btnTabSocial;
    @Bind(R.id.btn_tab_shopping_cart)
    RadioButton btnTabShoppingCart;
    @Bind(R.id.btn_tab_my_center)
    RadioButton btnTabMyCenter;

    MainTabFragment mainTabFragment;
    AllCategoryFragment categoryFragment;
    SocialFragment socialFragment;
    CartFragment cartFragment;
    MyFragment myFragment;

    private int curTabIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();

        //百度推送
        //PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, "YfAffwb0LTKPwRVXFjIzkDzV"); // api_key
    }

    private void initView() {
        mainTabFragment = new MainTabFragment();
        mainTabFragment.setRetainInstance(true);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, mainTabFragment);
        transaction.commit();
    }

    @OnClick({R.id.btn_tab_home, R.id.btn_tab_category, R.id.btn_tab_social, R.id.btn_tab_shopping_cart, R.id.btn_tab_my_center})
    public void onViewClicked(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (view.getId()) {
            case R.id.btn_tab_home:
                if (mainTabFragment.isVisible()) return;
                transaction.show(mainTabFragment);

                if (categoryFragment != null) {
                    transaction.hide(categoryFragment);
                }
                if (socialFragment != null) {
                    transaction.hide(socialFragment);
                }
                if (cartFragment != null) {
                    transaction.hide(cartFragment);
                }
                if (myFragment != null) {
                    transaction.hide(myFragment);
                }
                break;

            case R.id.btn_tab_category:
                if (categoryFragment == null) {
                    categoryFragment = new AllCategoryFragment();
                    transaction.add(R.id.fragment_container, categoryFragment);
                } else {
                    transaction.show(categoryFragment);
                }

                if (mainTabFragment != null) {
                    transaction.hide(mainTabFragment);
                }
                if (socialFragment != null) {
                    transaction.hide(socialFragment);
                }
                if (cartFragment != null) {
                    transaction.hide(cartFragment);
                }
                if (myFragment != null) {
                    transaction.hide(myFragment);
                }
                break;

            case R.id.btn_tab_social:
                if (socialFragment == null) {
                    socialFragment = new SocialFragment();
                    transaction.add(R.id.fragment_container, socialFragment);
                } else {
                    transaction.show(socialFragment);
                }

                if (categoryFragment != null) {
                    transaction.hide(categoryFragment);
                }
                if (mainTabFragment != null) {
                    transaction.hide(mainTabFragment);
                }
                if (cartFragment != null) {
                    transaction.hide(cartFragment);
                }
                if (myFragment != null) {
                    transaction.hide(myFragment);
                }
                break;

            case R.id.btn_tab_shopping_cart:
                if (cartFragment == null) {
                    cartFragment = new CartFragment();
                    transaction.add(R.id.fragment_container, cartFragment);
                } else {
                    transaction.show(cartFragment);
                }
                if (categoryFragment != null) {
                    transaction.hide(categoryFragment);
                }
                if (socialFragment != null) {
                    transaction.hide(socialFragment);
                }
                if (mainTabFragment != null) {
                    transaction.hide(mainTabFragment);
                }
                if (myFragment != null) {
                    transaction.hide(myFragment);
                }
                break;

            case R.id.btn_tab_my_center:
                if (myFragment == null) {
                    myFragment = new MyFragment();
                } else {
                    transaction.show(myFragment);
                }

                if (categoryFragment != null) {
                    transaction.hide(categoryFragment);
                }
                if (socialFragment != null) {
                    transaction.hide(socialFragment);
                }
                if (cartFragment != null) {
                    transaction.hide(cartFragment);
                }
                if (mainTabFragment != null) {
                    transaction.hide(mainTabFragment);
                }
                break;
        }

        transaction.commitAllowingStateLoss();
    }
}
