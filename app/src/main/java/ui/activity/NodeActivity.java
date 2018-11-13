package ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import base.MVPBaseActivity;
import contract.activity.NodeContract;
import presenter.activity.NodePresenter;
import ui.widget.MyActionBar;
import ui.adapter.NodeRecyclerviewAdapter;

public class NodeActivity extends MVPBaseActivity<NodeContract.View, NodePresenter> implements NodeContract.View, View.OnClickListener {
    private boolean flag = false;
    private LinearLayout nOdeShow;
    private RecyclerView nOdeRecyclerview;
    private ImageView nOdeImage;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_node;
    }

    @Override
    protected void setUpView() {
        MyActionBar nOdeActionbar = findViewById(R.id.node_actionbar);
        TextView nOdeCount = findViewById(R.id.node_count);
        TextView nOdeNickName = findViewById(R.id.node_nickname);
        nOdeShow = findViewById(R.id.node_show);
        nOdeRecyclerview = findViewById(R.id.node_recyclerview);
        nOdeImage = findViewById(R.id.node_image);
        flag = false;

        nOdeShow.setOnClickListener(this);
        nOdeActionbar.setTitle(getString(R.string.my_user));

        //设置Recyclerview 列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        nOdeRecyclerview.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        nOdeRecyclerview.setAdapter(new NodeRecyclerviewAdapter(NodeActivity.this));

    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.node_show:
                if (!flag) {
                    //下拉显示中
                    flag = true;
                    nOdeImage.setBackgroundResource(R.mipmap.mipmap_down);
                    nOdeRecyclerview.setVisibility(View.VISIBLE);
                } else {
                    //下拉不显示
                    flag = false;
                    nOdeImage.setBackgroundResource(R.mipmap.mipmap_up);
                    nOdeRecyclerview.setVisibility(View.GONE);
                }
                break;
        }
    }


}
