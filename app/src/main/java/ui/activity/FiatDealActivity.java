package ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.zhonglian.battleworld.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import event.EventBusUtils;
import event.EventCode;
import event.EventMessage;
import utils.StatusUtils;
import utils.entity.TabEntity;
import ui.adapter.FiatDealPagerAdapter;
import ui.fragment.BGCFragment;
import ui.fragment.BTCFragment;
import ui.fragment.ETHFragment;
import ui.fragment.USDTFragment;


public class FiatDealActivity extends FragmentActivity implements View.OnClickListener,View.OnTouchListener {

    Button fiatBack;
    RadioButton fiatBuy;
    RadioButton fiatSell;
    RadioButton fiatDeal;
    ImageButton fiatOrder;
    ImageButton fiatScreening;
    CommonTabLayout fiatFlycolayout;
    ViewPager fiatViewpager;
    FrameLayout fiatFramelayout;
    DrawerLayout fiatDrawerlayout;
    Button menuBusinessTypeAll;
    Button menuBusinessTypeHigh;
    Button menuMoneyAll;
    Button menuMoneyFive;
    Button menuMoneyTen;
    Button menuMoneyTwenty;
    Button menuPayAll;
    Button menuPayCard;
    Button menuPayAlipay;
    Button menuPayWeixing;
    private ActionBarDrawerToggle drawerbar;
    private Button menuScreen;
    private Button menuReset;
    private ArrayList<String> tabIndicators;
    private ArrayList tabFragments;
    private int[] mIconSelectIds = {0, 0, 0, 0, 0, 0};
    private int[] mIconUnselectIds = {0, 0, 0, 0, 0, 0};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    //购买 当前的 viewpage item
    private int buyItem = 0;
    //出售 当前的 viewper item
    private int sellItem = 0;
    //交易单 当前的 viewpager item
    private int dealItem = 0;
    private String pastItem = "buy";
    private FloatingActionButton fiatFlatButton;
    private int selectButton = 0;
    private FiatDealPagerAdapter fiatDealPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiatdeal);

        fiatBack = (Button) findViewById(R.id.fiat_back);
        fiatBuy = (RadioButton) findViewById(R.id.fiat_buy);
        fiatSell = (RadioButton) findViewById(R.id.fiat_sell);
        fiatDeal = (RadioButton) findViewById(R.id.fiat_deal);
        fiatOrder = (ImageButton) findViewById(R.id.fiat_order);
        fiatFlycolayout = (CommonTabLayout) findViewById(R.id.fiat_flycolayout);
        fiatScreening = (ImageButton) findViewById(R.id.fiat_screening);
        fiatViewpager = (ViewPager) findViewById(R.id.fiat_viewpager);
        fiatFramelayout = (FrameLayout) findViewById(R.id.fiat_framelayout);
        menuBusinessTypeAll = (Button) findViewById(R.id.menu_business_type_all);
        menuBusinessTypeHigh = (Button) findViewById(R.id.menu_business_type_high);
        menuMoneyAll = (Button) findViewById(R.id.menu_money_all);
        menuMoneyFive = (Button) findViewById(R.id.menu_money_five);
        menuMoneyTen = (Button) findViewById(R.id.menu_money_ten);
        menuMoneyTwenty = (Button) findViewById(R.id.menu_money_twenty);
        menuPayAll = (Button) findViewById(R.id.menu_pay_all);
        menuPayCard = (Button) findViewById(R.id.menu_pay_card);
        menuPayAlipay = (Button) findViewById(R.id.menu_pay_alipay);
        menuPayWeixing = (Button) findViewById(R.id.menu_pay_weixing);
        fiatDrawerlayout = (DrawerLayout) findViewById(R.id.fiat_drawerlayout);
        menuScreen = (Button) findViewById(R.id.menu_screening);
        menuReset = (Button) findViewById(R.id.menu_reset);
        fiatFlatButton = findViewById(R.id.fiat_floatbutton);
        //设置沉浸式
        StatusUtils.hideBottomUIMenu(this);
        //侧拉菜单设置
        initDrawlayout();

        //顶部导航默认设置
        initDefaul();

        //侧拉菜单中按钮
        initMenu();
        //按钮点击事件
        initListener();

        //设置 pager页
        initContent();
        //设置tablayout
        initTab();
        //注册 eventbus
        EventBusUtils.register(this);

    }

    /**
     * 接收到分发的事件
     *
     * @param event 事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(EventMessage event) {
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销eventbus
        EventBusUtils.unregister(this);
    }



    @Override
    protected void onResume() {
        super.onResume();
        StatusUtils.hideBottomUIMenu(this);
    }

    private void initDefaul() {
        fiatBuy.setTextSize(22);
        fiatDeal.setTextSize(18);
        fiatSell.setTextSize(18);
        fiatBuy.setTextColor(getResources().getColor(R.color.write));
        fiatDeal.setTextColor(getResources().getColor(R.color.colorEditHint));
        fiatSell.setTextColor(getResources().getColor(R.color.colorEditHint));
    }

    //设置 tab
    private void initTab() {
        fiatViewpager.setOffscreenPageLimit(tabIndicators.size());
        for (int i = 0; i < tabIndicators.size(); i++) {
            mTabEntities.add(new TabEntity(tabIndicators.get(i), mIconSelectIds[i], mIconUnselectIds[i]));
        }
        fiatFlycolayout.setTabData(mTabEntities);
        fiatDealPagerAdapter = new FiatDealPagerAdapter(getSupportFragmentManager(), tabIndicators, tabFragments);
        fiatViewpager.setAdapter(fiatDealPagerAdapter);
        //设置 page页  默认选中 为第一页
        fiatViewpager.setCurrentItem(0);

        //tab点击选中监听
        fiatFlycolayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                //选中Tab position项 在此做操作
                fiatFlycolayout.setCurrentTab(position);
                fiatViewpager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                //在此选中
            }
        });
        //添加ViewPage适配监听
        fiatViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                fiatFlycolayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    //设置 tab title  and page
    private void initContent() {
        tabIndicators = new ArrayList<String>();
        tabIndicators.add("USDT");
        tabIndicators.add("BGC");
        tabIndicators.add("BTC");
        tabIndicators.add("ETH");


        tabFragments = new ArrayList<>();
        tabFragments.add(new USDTFragment());
        tabFragments.add(new BGCFragment());
        tabFragments.add(new BTCFragment());
        tabFragments.add(new ETHFragment());
    }

    private void initListener() {
        fiatBack.setOnClickListener(this);
        fiatScreening.setOnClickListener(this);
        menuBusinessTypeHigh.setOnClickListener(this);
        menuMoneyFive.setOnClickListener(this);
        menuMoneyTen.setOnClickListener(this);
        menuMoneyTwenty.setOnClickListener(this);
        menuPayCard.setOnClickListener(this);
        menuPayAll.setOnClickListener(this);
        menuPayAlipay.setOnClickListener(this);
        menuBusinessTypeAll.setOnClickListener(this);
        menuMoneyAll.setOnClickListener(this);
        menuPayWeixing.setOnClickListener(this);
        menuScreen.setOnClickListener(this);
        menuReset.setOnClickListener(this);
        fiatBuy.setOnClickListener(this);
        fiatDeal.setOnClickListener(this);
        fiatSell.setOnClickListener(this);
        fiatFlatButton.setOnClickListener(this);
        fiatOrder.setOnClickListener(this);
        fiatFramelayout.setOnTouchListener(this);
    }

    //设置 抽屉菜单 默认选项
    private void initMenu() {
        //默认选中
        menuBusinessTypeAll.setSelected(true);
        menuMoneyAll.setSelected(true);
        menuPayAll.setSelected(true);
    }

    private void initDrawlayout() {
        //设置菜单内容之外其他区域的背景色
        fiatDrawerlayout.setScrimColor(getResources().getColor(R.color.colorview));
        drawerbar = new ActionBarDrawerToggle(this, fiatDrawerlayout, null, R.string.open, R.string.close) {
            //菜单打开
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            // 菜单关闭
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        fiatDrawerlayout.setDrawerListener(drawerbar);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fiat_screening:
                fiatDrawerlayout.openDrawer(fiatFramelayout);
                break;
            case R.id.menu_money_five:
                //五万
                menuMoneyFive.setSelected(true);
                menuMoneyTen.setSelected(false);
                menuMoneyTwenty.setSelected(false);
                menuMoneyAll.setSelected(false);
                break;
            case R.id.menu_money_ten:
                //十万
                menuMoneyFive.setSelected(false);
                menuMoneyTen.setSelected(true);
                menuMoneyTwenty.setSelected(false);
                menuMoneyAll.setSelected(false);
                break;
            case R.id.menu_money_twenty:
                //二十万
                menuMoneyFive.setSelected(false);
                menuMoneyTen.setSelected(false);
                menuMoneyTwenty.setSelected(true);
                menuMoneyAll.setSelected(false);
                break;
            case R.id.menu_pay_card:
                //银行卡
                menuPayCard.setSelected(true);
                menuPayAlipay.setSelected(false);
                menuPayWeixing.setSelected(false);
                menuPayAll.setSelected(false);
                break;
            case R.id.menu_pay_alipay:
                //支付宝
                menuPayCard.setSelected(false);
                menuPayAlipay.setSelected(true);
                menuPayWeixing.setSelected(false);
                menuPayAll.setSelected(false);
                break;
            case R.id.menu_pay_weixing:
                //微信
                menuPayCard.setSelected(false);
                menuPayAlipay.setSelected(false);
                menuPayWeixing.setSelected(true);
                menuPayAll.setSelected(false);
                break;
            case R.id.menu_business_type_high:
                //高级认证
                menuBusinessTypeHigh.setSelected(true);
                menuBusinessTypeAll.setSelected(false);
                break;
            case R.id.menu_business_type_all:
                //商家分类全部
                menuBusinessTypeHigh.setSelected(false);
                menuBusinessTypeAll.setSelected(true);
                break;
            case R.id.menu_money_all:
                //交易金额全部
                menuMoneyFive.setSelected(false);
                menuMoneyTen.setSelected(false);
                menuMoneyTwenty.setSelected(false);
                menuMoneyAll.setSelected(true);
                break;
            case R.id.menu_pay_all:
                //支付方式全部
                menuPayCard.setSelected(false);
                menuPayAlipay.setSelected(false);
                menuPayWeixing.setSelected(false);
                menuPayAll.setSelected(true);
                break;
            case R.id.menu_reset:
                //重置
                menuMoneyFive.setSelected(false);
                menuMoneyTen.setSelected(false);
                menuMoneyTwenty.setSelected(false);
                menuMoneyAll.setSelected(true);
                menuPayCard.setSelected(false);
                menuPayAlipay.setSelected(false);
                menuPayWeixing.setSelected(false);
                menuPayAll.setSelected(true);
                menuBusinessTypeHigh.setSelected(false);
                menuBusinessTypeAll.setSelected(true);
                break;
            case R.id.menu_screening:
                //筛选

                break;
            case R.id.fiat_buy:
                //购买
                fiatBuy.setTextSize(22);
                fiatDeal.setTextSize(18);
                fiatSell.setTextSize(18);
                fiatBuy.setTextColor(getResources().getColor(R.color.write));
                fiatDeal.setTextColor(getResources().getColor(R.color.colorEditHint));
                fiatSell.setTextColor(getResources().getColor(R.color.colorEditHint));
                //设置 购买 viewpager 上一次页面
                // setViewpagerItem();
                //pastItem = "buy";
                //刷新页面 并切换
                // setRefreshItem(buyItem,1);
                //设置 tab 下 fragment type
                selectButton=0;
                setFragmentType(selectButton);
                break;
            case R.id.fiat_sell:
                //出售
                fiatBuy.setTextSize(18);
                fiatDeal.setTextSize(18);
                fiatSell.setTextSize(22);
                fiatBuy.setTextColor(getResources().getColor(R.color.colorEditHint));
                fiatDeal.setTextColor(getResources().getColor(R.color.colorEditHint));
                fiatSell.setTextColor(getResources().getColor(R.color.write));
                //设置 出售 viewpager 上一次页面
                // setViewpagerItem();
                //pastItem = "sell";
                //刷新页面 并切换
                //  setRefreshItem(sellItem,2);
                //设置 tab 下 fragment type
                selectButton=1;
                setFragmentType(selectButton);
                break;
            case R.id.fiat_deal:
                //交易单
                fiatBuy.setTextSize(18);
                fiatDeal.setTextSize(22);
                fiatSell.setTextSize(18);
                fiatBuy.setTextColor(getResources().getColor(R.color.colorEditHint));
                fiatDeal.setTextColor(getResources().getColor(R.color.write));
                fiatSell.setTextColor(getResources().getColor(R.color.colorEditHint));
                //设置 交易单 上次页面
//                setViewpagerItem();
//                pastItem = "deal";
                //刷新页面 并切换
//                setRefreshItem(dealItem,3);
                //设置 tab 下 fragment type
                selectButton=2;
                setFragmentType(selectButton);
                break;
            case R.id.fiat_back:
                finish();
                break;
            case R.id.fiat_floatbutton:
                //显示 弹出框
                Intent intent = new Intent(FiatDealActivity.this, PurchaseActivity.class);
                //跳转并传递数据
                intent.putExtra("SELECT_BUTTON", selectButton);
                intent.putExtra("SELECT_PAGE", fiatViewpager.getCurrentItem());
                startActivity(intent);
                break;
            case R.id.fiat_order:
                Intent intent1 = new Intent(FiatDealActivity.this, MyOrderActivity.class);
                startActivity(intent1);
                break;
        }
    }

    private void setFragmentType(int selectButton) {
        fiatViewpager.setCurrentItem(0);
        //发送消息  当前页面0
        EventMessage eventMessage = new EventMessage(EventCode.EVENT_A, selectButton);
        post(eventMessage);
        //刷新tab 下的fragment  重新设置adapter
        fiatDealPagerAdapter = new FiatDealPagerAdapter(getSupportFragmentManager(), tabIndicators, tabFragments);
        fiatViewpager.setAdapter(fiatDealPagerAdapter);
    }

//    private void setRefreshItem(int item,int page) {
//        fiatViewpager.setCurrentItem(item);
//        //1 购买 2 售出 3交易
//        if (page==1){
//
//        }else if (page==2){
//
//        }else{
//
//        }
//    }
//
//    private void setViewpagerItem() {
//        if (pastItem.equals("buy")) {
//            buyItem = fiatViewpager.getCurrentItem();
//        } else if (pastItem.equals("sell")) {
//            sellItem = fiatViewpager.getCurrentItem();
//        } else if (pastItem.equals("deal")) {
//            dealItem = fiatViewpager.getCurrentItem();
//        }
//    }

    /**
     * 发送事件消息
     *
     * @param event
     */
    public void post(EventMessage event) {
        EventBus.getDefault().post(event);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }
}
