package ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.zhonglian.battleworld.R;

import base.BasePresenter;
import base.MVPBaseFragment;
import contract.fragment.ComplaintOrderFContract;
import presenter.fragment.ComplaintOrderFPresenter;
import ui.adapter.ComplaintRecyclerviewAdapter;

public class ComplaintOrderFragment extends MVPBaseFragment<ComplaintOrderFContract.View, ComplaintOrderFPresenter> implements ComplaintOrderFContract.View {
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_complaintorder;
    }

    @Override
    protected void setUpView() {
        RecyclerView complaintRecyclerview=getContentView().findViewById(R.id.complaint_recyclerview);

        //设置Recyclerview 列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(getMContext());
        //设置布局管理器
        complaintRecyclerview.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        complaintRecyclerview.setAdapter(new ComplaintRecyclerviewAdapter(getMContext()));
    }

    @Override
    protected void setUpData() {

    }


}
