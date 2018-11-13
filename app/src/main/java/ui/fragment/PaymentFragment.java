package ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.zhonglian.battleworld.R;

import base.BasePresenter;
import base.MVPBaseFragment;
import contract.fragment.PaymentContract;
import presenter.fragment.PaymentPresenter;
import ui.adapter.PaymentRecyclerviewAdapter;

public class PaymentFragment extends MVPBaseFragment<PaymentContract.View, PaymentPresenter> implements PaymentContract.View{
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_payment;
    }

    @Override
    protected void setUpView() {
        RecyclerView pAymentRecyclerview=getContentView().findViewById(R.id.payment_recyclerview);

        //设置Recyclerview 列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(getMContext());
        //设置布局管理器
        pAymentRecyclerview.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        pAymentRecyclerview.setAdapter(new PaymentRecyclerviewAdapter(getMContext()));
    }

    @Override
    protected void setUpData() {

    }

}
