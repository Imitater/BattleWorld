package ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhonglian.battleworld.R;

import java.util.Random;

import base.BasePresenter;
import base.MVPBaseActivity;
import base.MVPBaseOrderActivity;
import contract.activity.ComplaintOrderContract;
import presenter.activity.ComplaintOrderPresenter;
import ui.widget.MyActionBar;


public class ComplaintOrderActivity extends MVPBaseActivity<ComplaintOrderContract.View, ComplaintOrderPresenter> implements ComplaintOrderContract.View, View.OnClickListener {
    TextView complaintorderMoney;
    TextView complaintorderDealPrice;
    TextView complaintorderDealCount;
    LinearLayout complaintorderPrice;
    RelativeLayout complaintorderTop;
    TextView complaintorderCollectionName;
    TextView complaintorderNumber;
    TextView complaintorderOrderTime;
    TextView complaintorderOrderNumber;
    TextView complaintorderCollectionReference;
    Button complaintorderCancle;
    Button complaintorderComplaint;
    LinearLayout complaintorderPage;
    LinearLayout complaintorderShow;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_complaintorder;
    }

    @Override
    protected void setUpView() {
        MyActionBar completeorderActionbar = findViewById(R.id.complaintorder_actionbar);
        complaintorderMoney=findViewById(R.id.complaintorder_money);
        complaintorderDealPrice=findViewById(R.id.complaintorder_deal_price);
        complaintorderDealCount=findViewById(R.id.complaintorder_deal_count);
        complaintorderPrice=findViewById(R.id.complaintorder_price);
        complaintorderTop=findViewById(R.id.complaintorder_top);
        complaintorderCollectionName=findViewById(R.id.complaintorder_collection_name);
        complaintorderNumber=findViewById(R.id.complaintorder_number);
        complaintorderOrderTime=findViewById(R.id.complaintorder_order_time);
        complaintorderOrderNumber=findViewById(R.id.complaintorder_order_number);
        complaintorderCollectionReference=findViewById(R.id.complaintorder_collection_reference);
        complaintorderCancle=findViewById(R.id.complaintorder_cancle);
        complaintorderComplaint=findViewById(R.id.complaintorder_complaint);
        complaintorderPage=findViewById(R.id.complaintorder_page);
        complaintorderShow=findViewById(R.id.complaintorder_show);


        complaintorderCancle.setOnClickListener(this);
        complaintorderComplaint.setOnClickListener(this);

        //设置title
        completeorderActionbar.setTitle("订单详情");
        //获取跳转数据
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        int type = intent.getIntExtra("BUYORSELL", 0);
        //关闭上一页面
        if (type==0){
            ReleaseOrderActivity.instance.finish();
        }else{
            SellOrderInfoActivity.instance.finish();
        }
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.complaintorder_cancle:
                //取消申诉

            case R.id.complaintorder_complaint:
                //申述详情
                Random random=new Random();
                Intent intent1 = new Intent(ComplaintOrderActivity.this, ComplaintInfoActivity.class);
                intent1.putExtra("TYPE",random.nextInt()/2);
                startActivity(intent1);
                break;
        }
    }
}
