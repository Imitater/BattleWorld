package ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhonglian.battleworld.R;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import base.MVPBaseFragment;
import contract.fragment.BGCContract;
import presenter.fragment.BGCPresenter;
import ui.activity.DealDialgActivity;
import ui.adapter.UsdtRecyclerviewAdapter;
import utils.DialogUtils;
import event.EventBusUtils;
import event.EventMessage;
import utils.StatusUtils;
import ui.widget.TextEditTextView;
import ui.activity.BuyOrderInfoActivity;
import ui.activity.SellOrderInfoActivity;
import ui.adapter.BgcRecyclerviewAdapter;

public class BGCFragment extends MVPBaseFragment<BGCContract.View, BGCPresenter> implements BGCContract.View , TextEditTextView.OnKeyBoardHideListener{
    private int item=0;
    private SwipeRefreshLayout usdtSwipereFreshlayout;
    private RecyclerView usdtRecyclerview;
    private int mCurrentCounter;
    //刷新标志
    boolean isErr=true;
    List list=new ArrayList();
    private BgcRecyclerviewAdapter bgcRecyclerviewAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册 eventbus
        EventBusUtils.register(this);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //注销 eventbus
        EventBusUtils.unregister(this);
    }
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_bgc;
    }

    @Override
    protected void setUpView() {

        //假数据设置
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }


        usdtRecyclerview = getContentView().findViewById(R.id.bgc_recyclerview);
        usdtSwipereFreshlayout = getContentView().findViewById(R.id.bgc_swiperefreshlayout);


        //设置Recyclerview 列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(getMContext());
        //设置布局管理器
        usdtRecyclerview.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置列表
        initRecyclerview();

    }


    //设置列表
    private void initRecyclerview() {
        if (item==0||item==1) {
            bgcRecyclerviewAdapter = new BgcRecyclerviewAdapter(R.layout.adapter_bgc_layout, list);
            usdtRecyclerview.setAdapter(bgcRecyclerviewAdapter);
        }else{
            bgcRecyclerviewAdapter = new BgcRecyclerviewAdapter(R.layout.adapter_deal_type_layout, list);
            usdtRecyclerview.setAdapter(bgcRecyclerviewAdapter);
        }

        //设置Adapter
        bgcRecyclerviewAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//设置recyclerview 动画
        bgcRecyclerviewAdapter.isFirstOnly(false);//设置动画一直使用
        View view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_bgc_head_layout, null, false);//自定义头布局
        //头布局添加
        bgcRecyclerviewAdapter.addHeaderView(view);

        //设置上拉加载
        bgcRecyclerviewAdapter.setPreLoadNumber(bgcRecyclerviewAdapter.getData().size() - 10);
        bgcRecyclerviewAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                usdtRecyclerview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mCurrentCounter >= 20) {
                            //数据全部加载完毕
                            bgcRecyclerviewAdapter.loadMoreEnd();
                        } else {
                            if (isErr) {
                                //成功获取更多数据
//                                usdtRecyclerviewAdapter.addData(list1);
                               // Toast.makeText(getContext(), "上拉加载", Toast.LENGTH_SHORT).show();
                                mCurrentCounter = bgcRecyclerviewAdapter.getData().size();
                                bgcRecyclerviewAdapter.loadMoreComplete();
                            } else {
                                //获取更多数据失败
                                isErr = false;
                                bgcRecyclerviewAdapter.loadMoreFail();
                            }
                        }
                    }

                }, 2000);
            }
        }, usdtRecyclerview);
        //设置下拉刷新
        usdtSwipereFreshlayout.setColorSchemeResources(R.color.colorAccent);
        usdtSwipereFreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                usdtRecyclerview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // usdtRecyclerviewAdapter.addData(0, list2);
                      //  Toast.makeText(getContext(), "下拉刷新", Toast.LENGTH_SHORT).show();
                        bgcRecyclerviewAdapter.setUpFetching(false);
                        bgcRecyclerviewAdapter.setUpFetchEnable(false);
                        usdtSwipereFreshlayout.setRefreshing(false);
                    }
                },2000);
            }
        });



        //adapter 点击事件
        bgcRecyclerviewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(item!=2) {
                    //显示底部弹出框
                    Intent intent = new Intent(getMContext(), DealDialgActivity.class);
                    intent.putExtra("Type",item);
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    protected void setUpData() {

    }

    /**
     * 接收到分发的事件
     *
     * @param event 事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(EventMessage event) {
        if (event != null)
            item= (int) event.getData();
    }

}
