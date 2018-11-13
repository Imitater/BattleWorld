package ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhonglian.battleworld.R;

import base.BasePresenter;
import base.MVPBaseFragment;
import contract.fragment.AllOrdersContract;
import presenter.fragment.AllOrdersPresenter;
import ui.adapter.AllOrdersRecyclerviewAdapter;

public class AllOrdersFragment extends MVPBaseFragment<AllOrdersContract.View, AllOrdersPresenter> implements AllOrdersContract.View  {
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_allorders;
    }

    @Override
    protected void setUpView() {
        RecyclerView allOrdersRecyclerview=getContentView().findViewById(R.id.allorders_recyclerview);


        //设置Recyclerview 列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(getMContext());
        //设置布局管理器
        allOrdersRecyclerview.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        AllOrdersRecyclerviewAdapter allOrdersRecyclerviewAdapter = new AllOrdersRecyclerviewAdapter(getMContext());
        allOrdersRecyclerview.setAdapter(allOrdersRecyclerviewAdapter);

        //adapter item点击事件
       allOrdersRecyclerviewAdapter.buttonSetOnclick(new AllOrdersRecyclerviewAdapter.ButtonInterface() {
           @Override
           public void onclick(View view, int position) {

           }
       });

    }

    @Override
    protected void setUpData() {

    }

}
