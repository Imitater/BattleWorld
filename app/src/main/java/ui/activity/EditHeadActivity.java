package ui.activity;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import java.io.File;

import base.MVPBaseActivity;
import contract.activity.EditHeadContract;
import presenter.activity.EditHeadPresenter;
import utils.CameraUtils;
import utils.DialogUtils;
import ui.widget.MyActionBar;
import utils.PermissionUtils;
import utils.StatusUtils;


public class EditHeadActivity extends MVPBaseActivity<EditHeadContract.View, EditHeadPresenter> implements EditHeadContract.View ,View.OnClickListener{
    private static String path = Environment.getExternalStorageDirectory().getPath() + "/BattleWorld/MyHead/";// sd路径
     private ImageView eDitHead;


    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_edithead;
    }


    @Override
    protected void setUpView() {
        MyActionBar eDitAction = findViewById(R.id.edit_actionbar);
        eDitAction.setEditShow(true);
        eDitAction.setEditTitle(getString(R.string.head_change));
        TextView action_title = findViewById(R.id.action_title);
        action_title.setText(R.string.head_user);
        TextView aCtionEdit = (TextView) findViewById(R.id.action_edit);
        aCtionEdit.setOnClickListener(this);
        eDitHead = findViewById(R.id.edit_head);

     }

    @Override
    protected void onResume() {
        super.onResume();
        //获取本地缓存头像
        initHead();
     }

    private void initHead() {
        Bitmap bt = BitmapFactory.decodeFile(path + "/head.jpg");// 从SD卡中找头像，转换成Bitmap
        if (bt != null) {
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);// 转换成drawable
            eDitHead.setImageDrawable(drawable);
        } else {
            /**
             * 如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
             *
             */
            eDitHead.setBackgroundResource(R.mipmap.ic_launcher);
        }
    }

    @Override
    protected void setUpData() {

    }




    //显示底部弹出框
    private void showButtomDialog() {
        Intent intent = new Intent(EditHeadActivity.this, HeadDialogActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_edit:
                //底部弹出框
                showButtomDialog();
                break;
        }
    }
}
