package ui.activity;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import base.MVPBaseOrderActivity;
import contract.activity.BuyCompleteOrderContract;
import presenter.activity.BuyCompleteOrderPresenter;

public class BuyCompleteOrderActivity extends MVPBaseOrderActivity<BuyCompleteOrderContract.View, BuyCompleteOrderPresenter> implements BuyCompleteOrderContract.View {

    TextView completeorderMoney;
    TextView completeorderDealPrice;
    TextView completeorderDealCount;
    LinearLayout completeorderPrice;
    RelativeLayout releaseorderTop;
    TextView completeorderCollectionName;
    TextView completeorderOrderTime;
    TextView completeorderOrderNumber;
    TextView completeorderCollectionReference;
    Button completeorderComplaint;
    LinearLayout completeorderPage;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_completeorder;
    }

    @Override
    protected void setUpView() {
        completeorderMoney=findViewById(R.id.completeorder_money);
        completeorderDealPrice=findViewById(R.id.completeorder_deal_price);
        completeorderDealCount=findViewById(R.id.completeorder_deal_count);
        completeorderPrice=findViewById(R.id.completeorder_price);
        releaseorderTop=findViewById(R.id.releaseorder_top);
        completeorderCollectionName=findViewById(R.id.completeorder_collection_name);
        completeorderOrderTime=findViewById(R.id.completeorder_order_time);
        completeorderOrderNumber=findViewById(R.id.completeorder_order_number);
        completeorderCollectionReference=findViewById(R.id.completeorder_collection_reference);
        completeorderComplaint=findViewById(R.id.completeorder_complaint);
        completeorderPage=findViewById(R.id.completeorder_page);
    }

    @Override
    protected void setUpData() {

    }

}
