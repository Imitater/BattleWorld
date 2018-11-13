package ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import base.MVPBaseActivity;
import contract.fragment.PayWayContract;
import presenter.fragment.PayWayPresenter;
import ui.adapter.PayWayRecyclerviewAdapter;
import ui.widget.MyActionBar;
import ui.fragment.PayWayFragment;

public class PayWayActivity extends MVPBaseActivity<PayWayContract.View, PayWayPresenter> implements PayWayContract.View , View.OnClickListener{
    private TextView edit;
    private FrameLayout vIsitFrameLayout;
    private RecyclerView vIsityRecyclerview;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_payway;
    }

    @Override
    protected void setUpView() {
        MyActionBar vIsitActionBar = findViewById(R.id.visit_actionbar);
        vIsitFrameLayout = findViewById(R.id.visity_framelayout);
        vIsityRecyclerview = findViewById(R.id.visity_recyclerview);
        vIsitActionBar.setEditTitle(getString(R.string.pay_add));
        vIsitActionBar.setEditShow(true);
        vIsitActionBar.setTitle(getString(R.string.pay_way));

        //添加
        edit=findViewById(R.id.action_edit);
        edit.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {
        //设置Recyclerview 列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        vIsityRecyclerview.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        vIsityRecyclerview.setAdapter(new PayWayRecyclerviewAdapter(this));
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_edit:
                vIsitFrameLayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.visity_framelayout,new PayWayFragment(),"pay_add").commit();
                break;
        }
    }
}
