package ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.zhonglian.battleworld.R;

import base.MVPBaseFragment;
import contract.fragment.MyContract;
import presenter.fragment.MyPresenter;
import ui.adapter.MyRecyclerviewAdapter;
import ui.activity.InformationActivity;


public class MyFragment extends MVPBaseFragment<MyContract.View, MyPresenter> implements MyContract.View, View.OnClickListener {
    ImageView myHeadPortrait;
    RecyclerView myRecyclerview;
    private static String path = Environment.getExternalStorageDirectory().getPath() + "/BattleWorld/MyHead/";// sd路径

    private String[] strTitle = new String[9];

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_my;
    }

    @Override
    protected void setUpView() {
        myHeadPortrait = getContentView().findViewById(R.id.my_head_portrait);
        myRecyclerview = getContentView().findViewById(R.id.my_recyclerview);
        myHeadPortrait.setOnClickListener(this);

        setStrTitle();


        //设置Recyclerview 列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(getMContext());
        //设置布局管理器
        myRecyclerview.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        myRecyclerview.setAdapter(new MyRecyclerviewAdapter(getMContext(), strTitle));


    }

    @Override
    public void onStart() {
        super.onStart();
        Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");// 从SD卡中找头像，转换成Bitmap
        if (bt != null) {
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);// 转换成drawable
            myHeadPortrait.setImageDrawable(drawable);
        } else {
            /**
             * 如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
             *
             */
            myHeadPortrait.setBackgroundResource(R.mipmap.ic_launcher);
        }
    }


    private void setStrTitle() {
        strTitle[0] = getResources().getString(R.string.my_identity);
        strTitle[1] = getResources().getString(R.string.my_list);
        strTitle[2] = getResources().getString(R.string.my_safe);
        strTitle[3] = getResources().getString(R.string.my_pay);
        strTitle[4] = getResources().getString(R.string.my_visit);
        strTitle[5] = getResources().getString(R.string.my_user);
        strTitle[6] = getResources().getString(R.string.my_about);
        strTitle[7] = getResources().getString(R.string.my_feedback);
        strTitle[8] = getResources().getString(R.string.my_setting);
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击头像进入个人信息页面
            case R.id.my_head_portrait:
                Intent intent = new Intent(getMContext(), InformationActivity.class);
                startActivity(intent);
                break;
        }
    }

}
