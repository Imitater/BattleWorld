package ui.activity;


import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureSelectionConfig;
import com.yalantis.ucrop.UCrop;
import com.zhonglian.battleworld.R;
import java.io.File;
import java.io.FileInputStream;

import base.MVPBaseDialogActivity;
import contract.activity.HeadDialogContract;
import presenter.activity.HeadDialogPresenter;
import utils.CameraUtils;
import utils.StatusUtils;
import utils.UriUtils;

public class HeadDialogActivity extends MVPBaseDialogActivity<HeadDialogContract.View, HeadDialogPresenter> implements HeadDialogContract.View, View.OnClickListener {
    protected int activityCloseEnterAnimation;
    protected int activityCloseExitAnimation;
    private Bitmap head;// 头像Bitmap
    protected PictureSelectionConfig config;
    protected String originalPath;
    protected String cameraPath;
    private static String path = Environment.getExternalStorageDirectory().getPath() + "/BattleWorld/MyHead/";// sd路径

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            config = savedInstanceState.getParcelable(PictureConfig.EXTRA_CONFIG);
            cameraPath = savedInstanceState.getString(PictureConfig.BUNDLE_CAMERA_PATH);
            originalPath = savedInstanceState.getString(PictureConfig.BUNDLE_ORIGINAL_PATH);
        } else {
            config = PictureSelectionConfig.getInstance();
        }
        super.onCreate(savedInstanceState);
    }

    private void initView() {
        //item点击事件
        TextView dIalogTakePic = (TextView) findViewById(R.id.dialog_takepic);
        TextView dIalogPicAlbum = (TextView) findViewById(R.id.dialog_picalbum);
        TextView dIalogClose = (TextView) findViewById(R.id.dialog_close);

        dIalogTakePic.setOnClickListener(this);
        dIalogPicAlbum.setOnClickListener(this);
        dIalogClose.setOnClickListener(this);
    }

    private void initSet() {
        TypedArray activityStyle = getTheme().obtainStyledAttributes(new int[]{android.R.attr.windowAnimationStyle});
        int windowAnimationStyleResId = activityStyle.getResourceId(0, 0);
        activityStyle.recycle();
        activityStyle = getTheme().obtainStyledAttributes(windowAnimationStyleResId, new int[]{android.R.attr.activityCloseEnterAnimation, android.R.attr.activityCloseExitAnimation});
        activityCloseEnterAnimation = activityStyle.getResourceId(0, 0);
        activityCloseExitAnimation = activityStyle.getResourceId(1, 0);
        activityStyle.recycle();

        //设置布局在底部
        getWindow().setGravity(Gravity.BOTTOM);
        //设置布局填充满宽度
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(layoutParams);
    }

    @Override
    protected void onStart() {
        super.onStart();
        StatusUtils.hideBottomUIMenu(this);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.dialog_head;
    }

    @Override
    protected void setUpView() {
        initSet();
        initView();
    }

    @Override
    protected void setUpData() {

    }

    //重写finish方法
    @Override
    public void finish() {
        super.finish();
        //finish时调用退出动画
        StatusUtils.hideBottomUIMenu(this);
        overridePendingTransition(activityCloseEnterAnimation, activityCloseExitAnimation);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //拍照
            case R.id.dialog_takepic:
                //打开相机
                // 相机权限
                mPresenter.openCamera(this);
                break;
            //从相册中选择
            case R.id.dialog_picalbum:
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                break;
            //取消
            case R.id.dialog_close:
                finish();
                StatusUtils.hideBottomUIMenu(this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    CameraUtils cameraUtils = new CameraUtils(getApplicationContext());
                    cameraUtils.cropPhoto(data.getData(),config,this,this);

                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(path + "/head.jpg");
                    CameraUtils cameraUtils = new CameraUtils(getApplicationContext());
                    cameraUtils.cropPhoto(Uri.fromFile(temp),config,this,this);// 裁剪图片
                }
                break;
            default:
                break;
        }
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            Bitmap bt = BitmapFactory.decodeFile(UriUtils.getRealPathFromURI(resultUri,this));
            CameraUtils cameraUtils = new CameraUtils(getApplicationContext());
            cameraUtils.setPicToView(bt);// 保存在SD卡中
            finish();
         } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
