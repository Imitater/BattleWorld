package ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import java.util.ArrayList;
import java.util.List;

import utils.StatusUtils;
import ui.adapter.MainPageAdapter;
import butterknife.ButterKnife;
import ui.widget.CustomViewPager;
import utils.LanguageUtil;
import ui.fragment.ExchangeFragment;
import ui.fragment.MyFragment;
import ui.fragment.TestFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    LinearLayout mainSocial;
    LinearLayout mainTransaction;
    LinearLayout mainGame;
    LinearLayout mainMoney;
    LinearLayout mainMy;

    private List<Fragment> fragments;
    private CustomViewPager viewPager;
    private TextView mainTvSocial;
    private TextView mainTvTransaction;
    private TextView mainTvGame;
    private TextView mainTvMoney;
    private TextView mainTvMy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        //获取语言设置
        if (!LanguageUtil.isSetValue(this)) {
            LanguageUtil.resetDefaultLanguage(this);
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //绑定控件
        initView();
        //数据设置
        initData();
        //点击事件监听
        initListener();
    }

    private void initListener() {
        mainTransaction.setOnClickListener(this);
        mainSocial.setOnClickListener(this);
        mainGame.setOnClickListener(this);
        mainMoney.setOnClickListener(this);
        mainMy.setOnClickListener(this);
    }

    private void initView() {
        viewPager = findViewById(R.id.main_vp);
        mainTransaction = (LinearLayout) findViewById(R.id.main_transaction);
        mainSocial = (LinearLayout) findViewById(R.id.main_social);
        mainGame = (LinearLayout) findViewById(R.id.main_game);
        mainMoney = (LinearLayout) findViewById(R.id.main_money);
        mainMy = (LinearLayout) findViewById(R.id.main_my);

        mainTvSocial = (TextView) findViewById(R.id.main_tv_social);
        mainTvTransaction = (TextView) findViewById(R.id.main_tv_transaction);
        mainTvGame = (TextView) findViewById(R.id.main_tv_game);
        mainTvMoney = (TextView) findViewById(R.id.main_tv_money);
        mainTvMy = (TextView) findViewById(R.id.main_tv_my);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //设置底部导航按钮消失
        StatusUtils.hideBottomUIMenu(this);
    }



    private void initData() {
        fragments = new ArrayList<Fragment>();
        fragments.add(new TestFragment());
        fragments.add(new ExchangeFragment());
        fragments.add(new TestFragment());
        fragments.add(new TestFragment());
        fragments.add(new MyFragment());
        viewPager.setAdapter(new MainPageAdapter(getSupportFragmentManager(), fragments));
        //初始颜色及页面
        viewPager.setCurrentItem(1);
        setButtonColor(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_social:
                viewPager.setCurrentItem(0);
                setButtonColor(0);
                break;
            case R.id.main_transaction:
                setButtonColor(1);
                viewPager.setCurrentItem(1);
                break;
            case R.id.main_game:
                setButtonColor(2);
                viewPager.setCurrentItem(2);
                break;
            case R.id.main_money:
                setButtonColor(3);
                viewPager.setCurrentItem(3);
                break;
            case R.id.main_my:
                setButtonColor(4);
                viewPager.setCurrentItem(4);
                break;
        }
    }


    //设置底部按钮颜色
    @SuppressLint("ResourceType")
    public void setButtonColor(int position){
        switch (position){
            case 0:
                mainTvSocial.setTextColor(getResources().getColor(R.color.colorButtomTabPress));
                mainTvTransaction.setTextColor(getResources().getColor(R.color.colorButtomTab));
                mainTvGame.setTextColor(getResources().getColor(R.color.colorButtomTab));
                mainTvMoney.setTextColor(getResources().getColor(R.color.colorButtomTab));
                mainTvMy.setTextColor(getResources().getColor(R.color.colorButtomTab));
                break;
            case 1:
                mainTvSocial.setTextColor(getResources().getColor(R.color.colorButtomTab));
                mainTvTransaction.setTextColor(getResources().getColor(R.color.colorButtomTabPress));
                mainTvGame.setTextColor(getResources().getColor(R.color.colorButtomTab));
                mainTvMoney.setTextColor(getResources().getColor(R.color.colorButtomTab));
                mainTvMy.setTextColor(getResources().getColor(R.color.colorButtomTab));
                break;
            case 2:
                mainTvSocial.setTextColor(getResources().getColor(R.color.colorButtomTab));
                mainTvTransaction.setTextColor(getResources().getColor(R.color.colorButtomTab));
                mainTvGame.setTextColor(getResources().getColor(R.color.colorButtomTabPress));
                mainTvMoney.setTextColor(getResources().getColor(R.color.colorButtomTab));
                mainTvMy.setTextColor(getResources().getColor(R.color.colorButtomTab));
                break;
            case 3:
                mainTvSocial.setTextColor(getResources().getColor(R.color.colorButtomTab));
                mainTvTransaction.setTextColor(getResources().getColor(R.color.colorButtomTab));
                mainTvGame.setTextColor(getResources().getColor(R.color.colorButtomTab));
                mainTvMoney.setTextColor(getResources().getColor(R.color.colorButtomTabPress));
                mainTvMy.setTextColor(getResources().getColor(R.color.colorButtomTab));
                break;
            case 4:
                mainTvSocial.setTextColor(getResources().getColor(R.color.colorButtomTab));
                mainTvTransaction.setTextColor(getResources().getColor(R.color.colorButtomTab));
                mainTvGame.setTextColor(getResources().getColor(R.color.colorButtomTab));
                mainTvMoney.setTextColor(getResources().getColor(R.color.colorButtomTab));
                mainTvMy.setTextColor(getResources().getColor(R.color.colorButtomTabPress));
                break;
        }
    }


}
