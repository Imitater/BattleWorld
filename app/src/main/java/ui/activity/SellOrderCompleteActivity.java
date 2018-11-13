package ui.activity;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import base.BasePresenter;
import base.MVPBaseOrderActivity;
import contract.activity.SellOrderCompleteContract;
import presenter.activity.SellOrderCompletePresenter;

public class SellOrderCompleteActivity extends MVPBaseOrderActivity<SellOrderCompleteContract.View, SellOrderCompletePresenter> implements SellOrderCompleteContract.View  {
    TextView completeorderMoney;
    TextView completeorderDealPrice;
    TextView completeorderDealCount;
    LinearLayout completeorderPrice;
    RelativeLayout releaseorderTop;
    TextView completeorderCollectionName;
    TextView completeorderOrderTime;
    TextView completeorderOrderNumber;
    TextView completeorderCollectionReference;
    LinearLayout completeorderPage;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_sellordercomplete;
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
        completeorderPage=findViewById(R.id.completeorder_page);

        init();
    }

    private void init() {
        SellOrderInfoActivity.instance.finish();
    }

    @Override
    protected void setUpData() {

    }



}
