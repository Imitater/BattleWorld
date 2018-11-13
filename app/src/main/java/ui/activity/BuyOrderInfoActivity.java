package ui.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import base.MVPBaseActivity;
import base.MVPBaseOrderActivity;
import contract.activity.BuyOrderInfoContract;
import presenter.activity.BuyOrderInfoPresenter;
import utils.DialogUtils;
import utils.StatusUtils;

public class BuyOrderInfoActivity extends MVPBaseOrderActivity<BuyOrderInfoContract.View, BuyOrderInfoPresenter> implements BuyOrderInfoContract.View,View.OnClickListener{
    TextView buyorderPay;
    TextView buyorderTime;
    TextView buyorderMoney;
    TextView buyorderDealPrice;
    TextView buyorderDealCount;
    LinearLayout buyorderPrice;
    RelativeLayout buyorderTop;
    LinearLayout buyorderPage;
    LinearLayout buyorderShow;
    TextView buyorderBankInfo;
    TextView buyorderCollectionName;
    TextView buyorderCollectionNumber;
    TextView buyorderCollectionReference;
    Button buyorderCancelDeal;
    Button buyorderHavepay;
    TextView buyorderChangeCard;
    ImageView buyorderChangeCardBt;
    private ImageView buyorderWay;
    private DialogUtils dialogUtils;
    private TextView buyorderWayType;
    private Dialog dialog;
    private Dialog paydialog;
    private String money;
    private String count;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_buyorderinfo;
    }

    @Override
    protected void setUpView() {
        buyorderChangeCard = findViewById(R.id.buyorder_change_card);
        buyorderChangeCardBt = findViewById(R.id.buyorder_change_card_bt);
        buyorderWay = findViewById(R.id.buyorder_way);
        buyorderWayType = findViewById(R.id.buyorder_way_type);
        buyorderCancelDeal=findViewById(R.id.buyorder_cancel_deal);
        buyorderHavepay=findViewById(R.id.buyorder_havepay);
        buyorderDealPrice=findViewById(R.id.buyorder_deal_price);
        buyorderDealCount=findViewById(R.id.buyorder_deal_count);
        buyorderMoney=findViewById(R.id.buyorder_money);
        buyorderTime=findViewById(R.id.buyorder_time);
        buyorderPay=findViewById(R.id.buyorder_pay);
        buyorderPrice=findViewById(R.id.buyorder_price);
        buyorderTop=findViewById(R.id.buyorder_top);
        buyorderPage=findViewById(R.id.buyorder_page);
        buyorderShow=findViewById(R.id.buyorder_show);
        buyorderBankInfo=findViewById(R.id.buyorder_bank_info);
        buyorderCollectionName=findViewById(R.id.buyorder_collection_name);
        buyorderCollectionNumber=findViewById(R.id.buyorder_collection_number);
        buyorderCollectionReference=findViewById(R.id.buyorder_collection_reference);
        //点击事件监听
        initListener();
        //获取页面跳转数据
        initData();
    }

    protected void initData() {
        Intent intent = getIntent();
        money = intent.getStringExtra("MONEY");
        count = intent.getStringExtra("COUNT");
        buyorderMoney.setText(money);
        buyorderDealCount.setText(count);
    }

    private void initListener() {
        buyorderChangeCardBt.setOnClickListener(this);
        buyorderChangeCard.setOnClickListener(this);
        buyorderCancelDeal.setOnClickListener(this);
        buyorderHavepay.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {
        initData();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buyorder_change_card:
                //切换银行卡
                changeCard();
                break;
            case R.id.buyorder_change_card_bt:
                //切换银行卡
                changeCard();
                break;
            case R.id.dialog_alipay:
                buyorderWay.setBackgroundResource(R.mipmap.mipmap_alipay);
                buyorderWayType.setText("支付宝");
                dialogUtils.dismiss();
                StatusUtils.hideBottomUIMenu(BuyOrderInfoActivity.this);
                break;
            case R.id.dialog_weixing:
                buyorderWay.setBackgroundResource(R.mipmap.mipmap_weixing);
                buyorderWayType.setText("微信");
                dialogUtils.dismiss();
                StatusUtils.hideBottomUIMenu(BuyOrderInfoActivity.this);
                break;
            case R.id.dialog_card:
                buyorderWay.setBackgroundResource(R.mipmap.mipmap_card);
                buyorderWayType.setText("银行卡");
                dialogUtils.dismiss();
                StatusUtils.hideBottomUIMenu(BuyOrderInfoActivity.this);
                break;
            case R.id.dialog_close:
                dialogUtils.dismiss();
                StatusUtils.hideBottomUIMenu(BuyOrderInfoActivity.this);
                break;
            case R.id.buyorder_cancel_deal:
                //取消交易
                showDealDialog();
                break;
            case R.id.dialog_deal_cancel:
                //交易取消弹出框  我再想想
                dialog.dismiss();
                StatusUtils.hideBottomUIMenu(BuyOrderInfoActivity.this);
                break;
            case R.id.dialog_deal_finish:
                //交易取消弹出框  确定
                Intent intent = new Intent(BuyOrderInfoActivity.this, OrderCancleActivity.class);
                intent.putExtra("MONEY",money);
                intent.putExtra("COUNT",count);
                startActivity(intent);
                finish();
                dialog.dismiss();
                StatusUtils.hideBottomUIMenu(BuyOrderInfoActivity.this);
                break;
            case R.id.buyorder_havepay:
                //我已付款
                showHavePayDialog();
                break;
            case R.id.dialog_pay_cancel:
                //我已付款 框    取消
                paydialog.dismiss();
                StatusUtils.hideBottomUIMenu(BuyOrderInfoActivity.this);
                break;
            case R.id.dialog_pay_finish:
                //我已付款 框   确认
                Intent intent1 = new Intent(BuyOrderInfoActivity.this, ReleaseOrderActivity.class);
                startActivity(intent1);
                finish();
                paydialog.dismiss();
                StatusUtils.hideBottomUIMenu(BuyOrderInfoActivity.this);
                break;
        }
    }

    private void showHavePayDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.dialog_have_pay);
        paydialog = builder.create();
        Window window = paydialog.getWindow();
        window.setWindowAnimations(R.style.MyDialog);
        paydialog.show();

        TextView cAncel = paydialog.findViewById(R.id.dialog_pay_cancel);
        TextView fInish = paydialog.findViewById(R.id.dialog_pay_finish);


        cAncel.setOnClickListener(this);
        fInish.setOnClickListener(this);

        //dialog 空白处点击事件
        paydialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                dialog.dismiss();
                StatusUtils.hideBottomUIMenu(BuyOrderInfoActivity.this);
            }
        });
    }

    //显示居中弹出框
    private void showDealDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.dialog_cancel_deal);
        dialog = builder.create();
        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.MyDialog);
        dialog.show();

        TextView cAncel = dialog.findViewById(R.id.dialog_deal_cancel);
        TextView fInish = dialog.findViewById(R.id.dialog_deal_finish);


        cAncel.setOnClickListener(this);
        fInish.setOnClickListener(this);

        //dialog 空白处点击事件
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                dialog.dismiss();
                StatusUtils.hideBottomUIMenu(BuyOrderInfoActivity.this);
            }
        });
    }


    //显示底部弹出框
    private void changeCard() {
        dialogUtils = new DialogUtils(this,this, R.layout.dialog_change_card, true, true);
        dialogUtils.show();
        //item点击事件
        TextView dIalogCard = (TextView) dialogUtils.findViewById(R.id.dialog_card);
        TextView dIalogAlipay = (TextView) dialogUtils.findViewById(R.id.dialog_alipay);
        TextView dIalogWeixing = (TextView) dialogUtils.findViewById(R.id.dialog_weixing);
        TextView dIalogClose = (TextView) dialogUtils.findViewById(R.id.dialog_close);

        dIalogCard.setOnClickListener(this);
        dIalogAlipay.setOnClickListener(this);
        dIalogWeixing.setOnClickListener(this);
        dIalogClose.setOnClickListener(this);
        //dialog 空白处点击事件
        dialogUtils.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                dialog.dismiss();
                StatusUtils.hideBottomUIMenu(BuyOrderInfoActivity.this);
            }
        });
    }


}
