package ui.activity;

import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.zhonglian.battleworld.R;
import base.MVPBaseOrderActivity;
import butterknife.BindView;
import contract.activity.OrderCancleContract;
import presenter.activity.OrderCanclePresenter;

public class OrderCancleActivity extends MVPBaseOrderActivity<OrderCancleContract.View, OrderCanclePresenter> implements OrderCancleContract.View {
    TextView ordercancleMoney;
    TextView ordercancleDealPrice;
    TextView ordercancleDealCount;
    LinearLayout ordercanclePrice;
    RelativeLayout ordercancleTop;
    TextView ordercancleCollectionName;
    @BindView(R.id.ordercancle_time)
    TextView ordercancleTime;
    @BindView(R.id.ordercancle_collection_number)
    TextView ordercancleCollectionNumber;
    @BindView(R.id.ordercancle_collection_reference)
    TextView ordercancleCollectionReference;
    @BindView(R.id.ordercancle_page)
    LinearLayout ordercanclePage;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_ordercancle;
    }

    @Override
    protected void setUpView() {
        ordercancleMoney=findViewById(R.id.ordercancle_money);
        ordercancleDealCount=findViewById(R.id.ordercancle_deal_count);
        ordercancleDealPrice=findViewById(R.id.ordercancle_deal_price);
        ordercanclePrice=findViewById(R.id.ordercancle_price);
        ordercancleTop=findViewById(R.id.ordercancle_top);
        ordercancleCollectionName=findViewById(R.id.ordercancle_collection_name);


        //获取页面跳转数据

    }

    protected void initData() {
        Intent intent = getIntent();
        String money = intent.getStringExtra("MONEY");
        String count = intent.getStringExtra("COUNT");

        ordercancleMoney.setText(money);
        ordercancleDealCount.setText(count);
    }

    @Override
    protected void setUpData() {
        //获取页面跳转数据
        initData();
    }


}
