package ui.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhonglian.battleworld.R;
import base.MVPBaseOrderActivity;
import contract.activity.SellOrderInfoContract;
import presenter.activity.SellOrderInfoPresenter;
import utils.StatusUtils;
import ui.widget.TextEditTextView;

public class SellOrderInfoActivity extends MVPBaseOrderActivity<SellOrderInfoContract.View, SellOrderInfoPresenter> implements SellOrderInfoContract.View, View.OnClickListener {
    TextView sellorderMoney;
    TextView sellorderDealPrice;
    TextView sellorderDealCount;
    LinearLayout sellorderPrice;
    RelativeLayout sellorderTop;
    ImageView sellorderUserimage;
    TextView sellorderCollectionName;
    TextView sellorderCollectionReference;
    Button sellorderComplaintDeal;
    Button sellorderHavepay;
    LinearLayout sellorderPage;
    LinearLayout sellorderShow;
    private Dialog complainDialog;
    private Dialog safeDialog;
    private Dialog releaseDialog;
    public static SellOrderInfoActivity instance = null;
    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_sellorderinfo;
    }

    @Override
    protected void setUpView() {
        sellorderMoney = findViewById(R.id.sellorder_money);
        sellorderDealPrice = findViewById(R.id.sellorder_deal_price);
        sellorderDealCount = findViewById(R.id.sellorder_deal_count);
        sellorderPrice = findViewById(R.id.sellorder_price);
        sellorderTop = findViewById(R.id.sellorder_top);
        sellorderUserimage = findViewById(R.id.sellorder_userimage);
        sellorderCollectionReference = findViewById(R.id.sellorder_collection_reference);
        sellorderCollectionName = findViewById(R.id.sellorder_collection_name);
        sellorderComplaintDeal = findViewById(R.id.sellorder_complaint_deal);
        sellorderHavepay = findViewById(R.id.sellorder_havepay);
        sellorderPage = findViewById(R.id.sellorder_page);
        sellorderShow = findViewById(R.id.sellorder_show);

        instance = this;
        //点击事件
        //申诉
        sellorderComplaintDeal.setOnClickListener(this);
        //确认放行
        sellorderHavepay.setOnClickListener(this);
        //获取数据
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String money = intent.getStringExtra("MONEY");
        String count = intent.getStringExtra("COUNT");
        sellorderMoney.setText(money);
        sellorderDealCount.setText(count);
    }


    @Override
    protected void setUpData() {

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sellorder_complaint_deal:
                //申诉
                showComplaintDialog();
                break;
            case R.id.sellorder_havepay:
                //确认放行
                showReleaseDialog();
                break;
            case R.id.dialog_complaint_finish:
                //申述确认
                Intent intent = new Intent(SellOrderInfoActivity.this, ComplaintActivity.class);
                intent.putExtra("TYPE",1);
                startActivity(intent);
                complainDialog.dismiss();
                StatusUtils.hideBottomUIMenu(SellOrderInfoActivity.this);
                break;
            case R.id.dialog_complaint_cancel:
                //取消申诉
                complainDialog.dismiss();
                StatusUtils.hideBottomUIMenu(SellOrderInfoActivity.this);
                break;
            case R.id.dialog_release_cancel:
                //确认放行 取消
                releaseDialog.dismiss();
                StatusUtils.hideBottomUIMenu(SellOrderInfoActivity.this);
                break;
            case R.id.dialog_release_finish:
                //确定付款 确定
                releaseDialog.dismiss();
                StatusUtils.hideBottomUIMenu(SellOrderInfoActivity.this);
                showSafeDialog();
                break;

        }
    }
    //确认放行框
    private void showReleaseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.dialog_release);
        releaseDialog = builder.create();
        Window window = releaseDialog.getWindow();
        window.setWindowAnimations(R.style.MyDialog);
        releaseDialog.show();

        TextView cAncel = releaseDialog.findViewById(R.id.dialog_release_cancel);
        TextView fInish = releaseDialog.findViewById(R.id.dialog_release_finish);


        cAncel.setOnClickListener(this);
        fInish.setOnClickListener(this);

        //dialog 空白处点击事件
        releaseDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                dialog.dismiss();
                StatusUtils.hideBottomUIMenu(SellOrderInfoActivity.this);
            }
        });
    }

    //申诉框
    private void showComplaintDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.dialog_complaint);
        complainDialog = builder.create();
        Window window = complainDialog.getWindow();
        window.setWindowAnimations(R.style.MyDialog);
        complainDialog.show();

        TextView cAncel = complainDialog.findViewById(R.id.dialog_complaint_cancel);
        TextView fInish = complainDialog.findViewById(R.id.dialog_complaint_finish);


        cAncel.setOnClickListener(this);
        fInish.setOnClickListener(this);

        //dialog 空白处点击事件
        complainDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                dialog.dismiss();
                StatusUtils.hideBottomUIMenu(SellOrderInfoActivity.this);
            }
        });
    }


    //显示安全验证框
    private void showSafeDialog() {
        Intent intent = new Intent(SellOrderInfoActivity.this, SellOrderDialogActivity.class);
        startActivity(intent);
    }
}
