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
import contract.fragment.USDTContract;
import presenter.fragment.USDTPresenter;
import ui.activity.DealDialgActivity;
import utils.DialogUtils;
import event.EventBusUtils;
import event.EventMessage;
import utils.StatusUtils;
import ui.widget.TextEditTextView;
import ui.activity.BuyOrderInfoActivity;
import ui.activity.SellOrderInfoActivity;
import ui.adapter.UsdtRecyclerviewAdapter;

public class USDTFragment extends MVPBaseFragment<USDTContract.View, USDTPresenter> implements USDTContract.View {
    private int item = 0;
    private UsdtRecyclerviewAdapter usdtRecyclerviewAdapter;
    List list=new ArrayList();
    private int mCurrentCounter;
    //刷新标志
    boolean isErr=true;
    private SwipeRefreshLayout usdtSwipeRefreshalayout;
    private RecyclerView usdtRecyclerview;

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
        return R.layout.fragment_usdt;
    }

    @Override
    protected void setUpView() {
        usdtRecyclerview = getContentView().findViewById(R.id.usdt_recyclerview);
        usdtSwipeRefreshalayout = getContentView().findViewById(R.id.usdt_swiperrefreshalayout);

        //假数据设置
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }


        //设置Recyclerview 列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(getMContext());
        //设置布局管理器
        usdtRecyclerview.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //列表设置
        initRecyclerview();

    }




    //设置列表
    private void initRecyclerview() {
        //判断是否是交易
        if (item==0||item==1) {
            usdtRecyclerviewAdapter = new UsdtRecyclerviewAdapter(R.layout.adapter_usdt_layout, list);
            usdtRecyclerview.setAdapter(usdtRecyclerviewAdapter);
        }else{
            usdtRecyclerviewAdapter = new UsdtRecyclerviewAdapter(R.layout.adapter_deal_type_layout, list);
            usdtRecyclerview.setAdapter(usdtRecyclerviewAdapter);
        }

        //设置Adapter
        usdtRecyclerviewAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//设置recyclerview 动画
        usdtRecyclerviewAdapter.isFirstOnly(false);//设置动画一直使用
        View view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_usdt_head_layout, null, false);//自定义头布局
        //头布局添加
        usdtRecyclerviewAdapter.addHeaderView(view);

        //设置上拉加载
        usdtRecyclerviewAdapter.setPreLoadNumber(usdtRecyclerviewAdapter.getData().size() - 10);
        usdtRecyclerviewAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                usdtRecyclerview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mCurrentCounter >= 20) {
                            //数据全部加载完毕
                            usdtRecyclerviewAdapter.loadMoreEnd();
                        } else {
                            if (isErr) {
                                //成功获取更多数据
                                  mCurrentCounter = usdtRecyclerviewAdapter.getData().size();
                                usdtRecyclerviewAdapter.loadMoreComplete();
                            } else {
                                //获取更多数据失败
                                isErr = false;
                                usdtRecyclerviewAdapter.loadMoreFail();
                            }
                        }
                    }
                }, 2000);
            }
        }, usdtRecyclerview);
        //设置下拉刷新
        usdtSwipeRefreshalayout.setColorSchemeResources(R.color.colorAccent);
        usdtSwipeRefreshalayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                usdtRecyclerview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        usdtRecyclerviewAdapter.setUpFetching(false);
                        usdtRecyclerviewAdapter.setUpFetchEnable(false);
                        usdtSwipeRefreshalayout.setRefreshing(false);
                    }
                },2000);
            }
        });



        //adapter 点击事件
        usdtRecyclerviewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(item!=2) {
                    showButtomDialog();
                }
            }
        });
    }

    @Override
    protected void setUpData() {

    }


    //显示底部弹出框
    private void showButtomDialog() {
        Intent intent = new Intent(getMContext(), DealDialgActivity.class);
        intent.putExtra("Type", item);
        getMContext().startActivity(intent);
    }

    /**
     * 接收到分发的事件
     *
     * @param event 事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(EventMessage event) {
        if (event != null)
            item = (int) event.getData();
    }

}





