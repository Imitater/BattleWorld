package ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhonglian.battleworld.R;


import java.util.ArrayList;

import base.MVPBaseActivity;
import contract.fragment.AddressContract;
import presenter.fragment.AddressPresenter;
import ui.adapter.AddressRecyclerviewAdapter;
import ui.widget.AddressRecyclerview;
import ui.widget.MyActionBar;
import listener.OnItemClickListener;
import ui.fragment.AddAddressFragment;

public class AddressActivity extends MVPBaseActivity<AddressContract.View, AddressPresenter> implements AddressContract.View ,View.OnClickListener{

    private FrameLayout aDdressFramelayout;
    private ArrayList<String> mList;
    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_address;
    }

    @Override
    protected void setUpView() {
        MyActionBar aDdressActionBar = (MyActionBar) findViewById(R.id.address_actionbar);
        AddressRecyclerview aDdressRecyclerview = (AddressRecyclerview) findViewById(R.id.address_recyclerview);
        aDdressFramelayout = (FrameLayout) findViewById(R.id.address_framelayout);
        TextView edit = findViewById(R.id.action_edit);

        aDdressActionBar.setTitle(getString(R.string.my_address));
        aDdressActionBar.setEditShow(true);
        aDdressActionBar.setEditTitle(getString(R.string.pay_add));
        edit.setOnClickListener(this);


        /*
        *
        * test
        * */
        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mList.add(i + "");
        }

        //设置Recyclerview 列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        aDdressRecyclerview.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        final AddressRecyclerviewAdapter addressRecyclerviewAdapter = new AddressRecyclerviewAdapter(this,mList);
        //设置Adapter
        aDdressRecyclerview.setAdapter(addressRecyclerviewAdapter);
        aDdressRecyclerview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(AddressActivity.this, "** " + mList.get(position) + " **", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDeleteClick(int position) {
                Toast.makeText(AddressActivity.this, "** 删除 **", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDefualClick(int position) {
                Toast.makeText(AddressActivity.this, "** 默认 **", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEditClick(int position) {
                Toast.makeText(AddressActivity.this, "** 修改 **", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_edit:
                //显示添加地址fragment
                aDdressFramelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.address_framelayout,new AddAddressFragment(),"add").commit();
                break;
        }
    }
}
