package ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.zhonglian.battleworld.R;

import base.MVPBaseFragment;
import contract.fragment.UnPaymentContract;
import presenter.fragment.UnPaymentPresenter;
import ui.adapter.UnPaymentRecyclerviewAdapter;

public class UnPaymentFragment extends MVPBaseFragment<UnPaymentContract.View, UnPaymentPresenter> implements UnPaymentContract.View{
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_unpayment;
    }

    @Override
    protected void setUpView() {
        RecyclerView pAymentRecyclerview=getContentView().findViewById(R.id.unpayment_recyclerview);

        //设置Recyclerview 列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(getMContext());
        //设置布局管理器
        pAymentRecyclerview.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        pAymentRecyclerview.setAdapter(new UnPaymentRecyclerviewAdapter(getMContext()));
    }

    @Override
    protected void setUpData() {

    }

}
