package ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.zhonglian.battleworld.R;

import java.util.ArrayList;

import base.MVPBaseActivity;
import contract.activity.MyOrderContract;
import presenter.activity.MyOrderPresenter;
import ui.widget.MyActionBar;
import utils.entity.TabEntity;
import ui.fragment.AllOrdersFragment;
import ui.fragment.CancelOrderFragment;
import ui.fragment.ComplaintOrderFragment;
import ui.fragment.OrderCompleteFragment;
import ui.fragment.PaymentFragment;
import ui.fragment.UnPaymentFragment;

public class MyOrderActivity extends MVPBaseActivity<MyOrderContract.View, MyOrderPresenter> implements MyOrderContract.View{
    private int[] mIconSelectIds = {0,0,0,0,0,0};
    private int[] mIconUnselectIds = {0,0,0,0,0,0};
    private CommonTabLayout oRderFlaycoLayout;
    private ViewPager oRderViewPager;
    private ArrayList<String> tabIndicators;
    private ArrayList tabFragments;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_myorder;
    }

    @Override
    protected void setUpView() {
        MyActionBar oRderActionbar = findViewById(R.id.order_actionbar);
        oRderViewPager = findViewById(R.id.order_viewpager);
        oRderFlaycoLayout=findViewById(R.id.order_flycolayout);


        oRderActionbar.setTitle(getString(R.string.my_list));

        //设置 pager页
        initContent();
        //设置tablayout
        initTab();
    }

    private void initTab() {
        oRderViewPager.setOffscreenPageLimit(tabIndicators.size());
        for (int i = 0; i < tabIndicators.size(); i++) {
            mTabEntities.add(new TabEntity(tabIndicators.get(i), mIconSelectIds[i], mIconUnselectIds[i]));
        }
        oRderFlaycoLayout.setTabData(mTabEntities);
        oRderViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        oRderViewPager.setCurrentItem(0);
        oRderFlaycoLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                //选中Tab position项 在此做操作
                oRderFlaycoLayout.setCurrentTab(position);
                oRderViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                //在此选中
            }
        });
        //添加ViewPage适配监听
        oRderViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                oRderFlaycoLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initContent() {
        tabIndicators = new ArrayList<String>();
        tabIndicators.add(getString(R.string.order_all));
        tabIndicators.add(getString(R.string.order_unpay));
        tabIndicators.add(getString(R.string.order_pay));
        tabIndicators.add(getString(R.string.order_complain));
        tabIndicators.add(getString(R.string.order_cancel));
        tabIndicators.add(getString(R.string.order_complete));

        tabFragments = new ArrayList<>();
        tabFragments.add(new AllOrdersFragment());
        tabFragments.add(new UnPaymentFragment());
        tabFragments.add(new PaymentFragment());
        tabFragments.add(new ComplaintOrderFragment());
        tabFragments.add(new CancelOrderFragment());
        tabFragments.add(new OrderCompleteFragment());

    }

    @Override
    protected void setUpData() {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return tabIndicators.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabIndicators.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return (Fragment) tabFragments.get(position);
        }
    }
}
