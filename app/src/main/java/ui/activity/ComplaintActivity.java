package ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.zhonglian.battleworld.R;


import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import base.MVPBaseActivity;
import contract.activity.ComplaintContract;
import event.EventBusUtils;
import event.EventMessage;
import presenter.activity.ComplaintPresenter;
import utils.DialogUtils;
import ui.widget.MyActionBar;
import utils.PicLoadUtil;
import utils.StatusUtils;
import ui.widget.TextEditTextView;

public class ComplaintActivity extends MVPBaseActivity<ComplaintContract.View, ComplaintPresenter> implements ComplaintContract.View, View.OnClickListener, TextEditTextView.OnKeyBoardHideListener {
    RelativeLayout rl;
    TextView complaintType;
    TextView complaintTime;
    TextView complaintMoney;
    TextView complaintTypeMoney;
    TextView complaintCount;
    TextView complaintDealMoney;
    LinearLayout complaintReason;
    ImageView complaintAdd;
    ImageView image1;
    ImageView image1Close;
    RelativeLayout imageItme1;
    ImageView image2;
    ImageView image2Close;
    RelativeLayout imageItme2;
    ImageView image3;
    ImageView image3Close;
    RelativeLayout imageItme3;
    LinearLayout releaseorderShow;
    Button complaintFinish;
    TextEditTextView complaintEt;
    private TextView complaintReasontv;
    private int type;
    private int item;
    private List<LocalMedia> selectList;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_complaint;
    }

    @Override
    protected void setUpView() {
        MyActionBar complaintActionbar = findViewById(R.id.complaint_actionbar);
        rl = findViewById(R.id.rl);
        complaintType = findViewById(R.id.complaint_type);
        complaintTime = findViewById(R.id.complaint_time);
        complaintMoney = findViewById(R.id.complaint_money);
        complaintTypeMoney = findViewById(R.id.complaint_type_money);
        complaintCount = findViewById(R.id.complaint_count);
        complaintDealMoney = findViewById(R.id.complaint_deal_money);
        complaintReason = findViewById(R.id.complaint_reason);
        complaintAdd = findViewById(R.id.complaint_add);
        image1 = findViewById(R.id.image1);
        image1Close = findViewById(R.id.image1_close);
        imageItme1 = findViewById(R.id.image_itme1);
        image2 = findViewById(R.id.image2);
        image2Close = findViewById(R.id.image2_close);
        imageItme2 = findViewById(R.id.image_itme2);
        image3 = findViewById(R.id.image3);
        image3Close = findViewById(R.id.image3_close);
        imageItme3 = findViewById(R.id.image_itme3);
        releaseorderShow = findViewById(R.id.releaseorder_show);
        complaintFinish = findViewById(R.id.complaint_finish);
        complaintEt = findViewById(R.id.complaint_et);
        complaintReasontv = findViewById(R.id.complaint_reason_tv);

        complaintActionbar.setTitle("申诉");
        initListener();

        //获取页面跳转数据
        initData();
        //注册 eventbus
        EventBusUtils.register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //注销 eventbus
        EventBusUtils.unregister(this);
    }

    private void initData() {
        Intent intent = getIntent();
        type = intent.getIntExtra("TYPE", 0);
        if (type == 0) {
            rl.setBackgroundColor(getResources().getColor(R.color.colorbuy));
        } else {
            rl.setBackgroundColor(getResources().getColor(R.color.colorsell));
        }
    }

    private void initListener() {
        //选择申诉原因
        complaintReason.setOnClickListener(this);
        complaintEt.setOnKeyBoardHideListener(this);
        complaintAdd.setOnClickListener(this);
        image1Close.setOnClickListener(this);
        image2Close.setOnClickListener(this);
        image3Close.setOnClickListener(this);
        complaintFinish.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.complaint_reason:
                //申诉原因框
                showReasonDialog();
                break;
            case R.id.complaint_add:
                //添加图片
                addImage();
                break;
            case R.id.image1_close:
                //删除图片1
                imageItme1.setVisibility(View.GONE);
                selectList.remove(0);
                break;
            case R.id.image2_close:
                imageItme2.setVisibility(View.GONE);
                //删除图片2
                selectList.remove(1);
                break;
            case R.id.image3_close:
                //删除图片3
                imageItme3.setVisibility(View.GONE);
                selectList.remove(2);
                break;
            case R.id.complaint_finish:
                //提交申诉
                Intent intent = new Intent(ComplaintActivity.this, ComplaintOrderActivity.class);
                intent.putExtra("BUYORSELL", type);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void showReasonDialog() {
        Intent intent = new Intent(ComplaintActivity.this, ComplaintDialogActivity.class);
        startActivity(intent);
    }

    @Override
    public void onKeyHide(int keyCode, KeyEvent event) {
        StatusUtils.hideBottomUIMenu(this);
    }


    //添加申诉图片
    private void addImage() {
        PicLoadUtil.LoadMorePic(this, selectList);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    if (selectList.size() >= 3) {
                        for (int i = 0; i < selectList.size(); i++) {
                            Bitmap bt = BitmapFactory.decodeFile(selectList.get(i).getPath());
                            if (bt != null) {
                                if (i == 0) {
                                    image1.setImageBitmap(bt);
                                    imageItme1.setVisibility(View.VISIBLE);
                                }
                                if (i == 1) {
                                    image2.setImageBitmap(bt);
                                    imageItme2.setVisibility(View.VISIBLE);
                                }
                                if (i == 2) {
                                    image3.setImageBitmap(bt);
                                    imageItme3.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    } else if (selectList.size() >= 2) {
                        for (int i = 0; i < selectList.size(); i++) {
                            Bitmap bt = BitmapFactory.decodeFile(selectList.get(i).getPath());
                            if (bt != null) {
                                if (i == 0) {
                                    image1.setImageBitmap(bt);
                                    imageItme1.setVisibility(View.VISIBLE);
                                }
                                if (i == 1) {
                                    image2.setImageBitmap(bt);
                                    imageItme2.setVisibility(View.VISIBLE);
                                }
                                 imageItme3.setVisibility(View.GONE);
                            }
                        }
                    } else if (selectList.size() >= 1) {
                        for (int i = 0; i < selectList.size(); i++) {
                            Bitmap bt = BitmapFactory.decodeFile(selectList.get(i).getPath());
                            if (bt != null) {
                                image1.setImageBitmap(bt);
                                imageItme1.setVisibility(View.VISIBLE);
                                imageItme3.setVisibility(View.GONE);
                                imageItme2.setVisibility(View.GONE);
                            }
                        }
                    }else{
                        imageItme1.setVisibility(View.GONE);
                        imageItme3.setVisibility(View.GONE);
                        imageItme2.setVisibility(View.GONE);
                    }
                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * 接收到分发的事件
     *
     * @param event 事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(EventMessage event) {
        if (event != null) {
            item = (int) event.getData();
            switch (item) {
                case 0:
                    complaintReasontv.setText("对方未支付");
                    break;
                case 1:
                    complaintReasontv.setText("对方未放行");
                    break;
                case 2:
                    complaintReasontv.setText("对方无应答");
                    break;
                case 3:
                    complaintReasontv.setText("对方有欺诈行为");
                    break;
                case 4:
                    complaintReasontv.setText("其他");
                    break;
            }

        }
    }
}
