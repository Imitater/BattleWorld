package ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.zhonglian.battleworld.R;

import java.util.ArrayList;

import anim.MzTransformer;
import base.MVPBaseFragment;
import contract.fragment.ExchangeContract;
import model.bean.PageBean;
import presenter.fragment.ExchangePresenter;
import ui.widget.BannerViewPager;
import ui.widget.ZoomIndicator;
import ui.activity.FiatDealActivity;
import ui.adapter.DealRecyclerviewAdapter;
import ui.callback.PageHelperListener;


public class ExchangeFragment extends MVPBaseFragment<ExchangeContract.View, ExchangePresenter> implements ExchangeContract.View, View.OnTouchListener,View.OnClickListener{

    private BannerViewPager eXchangeLoopViewpager;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_exchange;
    }

    @Override
    protected void setUpView() {
        ImageButton eXchangeSearch = (ImageButton) getContentView().findViewById(R.id.exchange_search);
        eXchangeLoopViewpager = getContentView().findViewById(R.id.exchange_loop_viewpager);
        ZoomIndicator eXchangeBottomZoomArc = getContentView().findViewById(R.id.exchange_bottom_zoom_arc);

        ImageView image1 = getContentView().findViewById(R.id.exchange_image1);
        ImageView image2 = getContentView().findViewById(R.id.exchange_image2);
        Button eXchangeNext=getContentView().findViewById(R.id.exchange_next);

        RecyclerView eXchangeDealRecyclerview = getContentView().findViewById(R.id.exchange_deal_recyclerview);
        RecyclerView eXchangeGainsRecyclerview = (RecyclerView) getContentView().findViewById(R.id.exchange_gains_recyclerview);

        eXchangeNext.setOnClickListener(this);
        image2.setOnClickListener(this);
        eXchangeSearch.setOnClickListener(this);


        //轮播图 数据
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(R.mipmap.viewpager_test1);
        integers.add(R.mipmap.viewpager_test2);
        integers.add(R.mipmap.viewpager_test3);
        integers.add(R.mipmap.viewpager_test4);
        PageBean bean = new PageBean.Builder<Integer>()
                .setDataObjects(integers)
                .setIndicator(eXchangeBottomZoomArc)
                .builder();
        //轮播动画
        eXchangeLoopViewpager.setPageTransformer(true, new MzTransformer());
        eXchangeLoopViewpager.setOffscreenPageLimit(3);//轮播一屏幕显示3个item
        eXchangeLoopViewpager.setPageMargin(30);//pager间距
        //viewpager监听
        eXchangeLoopViewpager.setPageListener(bean, R.layout.loop_layout, new PageHelperListener() {
            @Override
            public void getItemView(View view, Object o) {
                ImageView lOopIcon = view.findViewById(R.id.loop_icon);
                int i = (int) o;
                lOopIcon.setBackgroundResource(i);
            }
        });
        //交易lable 图片
        Glide.with(getContext()).load(R.mipmap.viewpager_test4).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(image1);
        Glide.with(getContext()).load(R.mipmap.viewpager_test4).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(image2);

        //设置deal adapter
        //设置Recyclerview 列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(getMContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        //设置布局管理器
        eXchangeDealRecyclerview.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        eXchangeDealRecyclerview.setAdapter(new DealRecyclerviewAdapter(getMContext()));

    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onPause() {
        super.onPause();
        eXchangeLoopViewpager.stopAnim();
    }

    @Override
    public void onResume() {
        super.onResume();
        eXchangeLoopViewpager.startAnim();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.exchange_search:
                Toast.makeText(getContext(),"123",Toast.LENGTH_SHORT).show();
                break;
            case R.id.exchange_image2:
                Intent intent=new Intent(getMContext(),FiatDealActivity.class);
                startActivity(intent);
                break;
            case R.id.exchange_next:
                break;
        }
    }


}
