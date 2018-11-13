package ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.zhonglian.battleworld.R;

import base.MVPBaseFragment;
import contract.fragment.OrderCompleteContract;
import presenter.fragment.OrderCompletePresenter;
import ui.adapter.CompleteRecyclerviewAdapter;

public class OrderCompleteFragment extends MVPBaseFragment<OrderCompleteContract.View, OrderCompletePresenter> implements OrderCompleteContract.View {
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_ordercomplete;
    }

    @Override
    protected void setUpView() {
        RecyclerView cOmpleteRecyclerView = getContentView().findViewById(R.id.complete_recyclerview);



        //设置Recyclerview 列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(getMContext());
        //设置布局管理器
        cOmpleteRecyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        cOmpleteRecyclerView.setAdapter(new CompleteRecyclerviewAdapter(getMContext()));


    }

    @Override
    protected void setUpData() {

    }

}
