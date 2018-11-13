package ui.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.zhonglian.battleworld.R;

import java.util.ArrayList;

import base.MVPBaseActivity;
import contract.activity.PurchaseContract;
import presenter.activity.PurchasePresenter;
import ui.widget.MyActionBar;
import utils.entity.TabEntity;
import ui.adapter.FiatDealPagerAdapter;

import ui.fragment.PurchaseFragment;
import ui.fragment.SellFragment;


public class PurchaseActivity extends MVPBaseActivity<PurchaseContract.View, PurchasePresenter> implements PurchaseContract.View {
    private int[] mIconSelectIds = {0, 0, 0, 0, 0, 0};
    private int[] mIconUnselectIds = {0, 0, 0, 0, 0, 0};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList tabIndicators;
    private ArrayList tabFragments;
    private ViewPager purchaseViewpager;
    private CommonTabLayout purchaseCommontablayout;
    private String selectItem = "";

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_purchase;
    }

    @Override
    protected void setUpView() {
        //获取数据
        Intent intent = getIntent();
        int selectButton = intent.getIntExtra("SELECT_BUTTON", 0);
        int selectPage = intent.getIntExtra("SELECT_PAGE", 0);

        MyActionBar purchaseActionbar = (MyActionBar) findViewById(R.id.purchase_actionbar);
        purchaseCommontablayout = findViewById(R.id.purchase_commontablayout);
        purchaseViewpager = findViewById(R.id.purchase_viewpager);

        //判断当前页面
        if (selectPage == 0) {
            selectItem = "USDT";
            purchaseActionbar.setTitle("发布交易(USDT/CNY)");
        } else if (selectPage == 1) {
            selectItem = "BGC";
            purchaseActionbar.setTitle("发布交易(BGC/CNY)");
        } else if (selectPage == 2) {
            selectItem = "BTC";
            purchaseActionbar.setTitle("发布交易(BTC/CNY)");
        } else if (selectPage == 3) {
            selectItem = "ETH";
            purchaseActionbar.setTitle("发布交易(ETH/CNY)");
        }

        //设置 pager页
        initContent();
        //设置tablayout
        initTab();
        //设置当前page页
        purchaseViewpager.setCurrentItem(selectButton);

    }

    private void initTab() {
        purchaseViewpager.setOffscreenPageLimit(tabIndicators.size());
        for (int i = 0; i < tabIndicators.size(); i++) {
            mTabEntities.add(new TabEntity((String) tabIndicators.get(i), mIconSelectIds[i], mIconUnselectIds[i]));
        }
        purchaseCommontablayout.setTabData(mTabEntities);
        purchaseViewpager.setAdapter(new FiatDealPagerAdapter(getSupportFragmentManager(), tabIndicators, tabFragments));
        purchaseViewpager.setCurrentItem(0);
        purchaseCommontablayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                //选中Tab position项 在此做操作
                purchaseCommontablayout.setCurrentTab(position);
                purchaseViewpager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                //在此选中
            }
        });
        //添加ViewPage适配监听
        purchaseViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                purchaseCommontablayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initContent() {
        tabIndicators = new ArrayList<String>();
        tabIndicators.add("我要购买");
        tabIndicators.add("我要出售");


        tabFragments = new ArrayList<>();
        tabFragments.add(PurchaseFragment.newInstance(selectItem));
        tabFragments.add(SellFragment.newInstance(selectItem));
    }

    @Override
    protected void setUpData() {

    }

}
