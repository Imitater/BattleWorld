package ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.zhonglian.battleworld.R;

import base.BasePresenter;
import base.MVPBaseFragment;
import contract.fragment.CancelOrderContract;
import presenter.fragment.CancelOrderPresenter;
import ui.adapter.CancelRecyclerviewAdapter;

public class CancelOrderFragment extends MVPBaseFragment<CancelOrderContract.View, CancelOrderPresenter> implements CancelOrderContract.View{
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_cancelorder;
    }

    @Override
    protected void setUpView() {
        RecyclerView cAncelRecycler=getContentView().findViewById(R.id.cancel_recyclerview);

        //设置Recyclerview 列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(getMContext());
        //设置布局管理器
        cAncelRecycler.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        cAncelRecycler.setAdapter(new CancelRecyclerviewAdapter(getMContext()));
    }

    @Override
    protected void setUpData() {

    }

}
