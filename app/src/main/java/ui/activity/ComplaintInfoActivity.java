package ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.zhonglian.battleworld.R;
import base.MVPBaseActivity;
import contract.activity.ComplaintInfoContract;
import presenter.activity.ComplaintInfoPresenter;
import ui.widget.MyActionBar;

public class ComplaintInfoActivity extends MVPBaseActivity<ComplaintInfoContract.View, ComplaintInfoPresenter> implements ComplaintInfoContract.View,View.OnClickListener{
    MyActionBar complaintinfoActionbar;
    RelativeLayout complaintinfoRl;
    TextView complaintinfoMoneyType;
    TextView complaintinfoTime;
    TextView complaintinfoMoney;
    TextView complaintinfoTypeMoney;
    TextView complaintinfoCount;
    TextView complaintinfoDealMoney;
    TextView complaintinfoComplaintType;
    TextView complaintinfoReason;
    ImageView complaintinfoImage;
    TextView complaintinfoComplaintTime;
    TextView complaintinfoType;
    TextView complaintinfoDealing;
    TextView complaintinfoDealingInfo;
    TextView complaintinfoDealResult;
    TextView complaintinfoDealResultInfo;
    TextView complaintinfoDealTime;
    TextView complaintinfoDealTimeInfo;
    Button complaintinfoFinish;
    private int type;


    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_complaintinfo;
    }

    @Override
    protected void setUpView() {
        complaintinfoActionbar=findViewById(R.id.complaintinfo_actionbar);
        complaintinfoRl=findViewById(R.id.complaintinfo_rl);
        complaintinfoMoneyType=findViewById(R.id.complaintinfo_money_type);
        complaintinfoTime=findViewById(R.id.complaintinfo_time);
        complaintinfoMoney=findViewById(R.id.complaintinfo_money);
        complaintinfoCount=findViewById(R.id.complaintinfo_count);
        complaintinfoTypeMoney=findViewById(R.id.complaintinfo_complaint_type);
        complaintinfoDealMoney=findViewById(R.id.complaintinfo_deal_money);
        complaintinfoComplaintType=findViewById(R.id.complaintinfo_complaint_type);
        complaintinfoReason=findViewById(R.id.complaintinfo_reason);
        complaintinfoImage=findViewById(R.id.complaintinfo_image);
        complaintinfoComplaintTime=findViewById(R.id.complaintinfo_complaint_time);
        complaintinfoType=findViewById(R.id.complaintinfo_type);
        complaintinfoDealing=findViewById(R.id.complaintinfo_dealing);
        complaintinfoDealingInfo=findViewById(R.id.complaintinfo_dealing_info);
        complaintinfoDealResult=findViewById(R.id.complaintinfo_deal_result);
        complaintinfoDealResultInfo=findViewById(R.id.complaintinfo_deal_result_info);
        complaintinfoDealTime=findViewById(R.id.complaintinfo_deal_time);
        complaintinfoDealTimeInfo=findViewById(R.id.complaintinfo_deal_time_info);
        complaintinfoFinish=findViewById(R.id.complaintinfo_finish);

        //设置title
        complaintinfoActionbar.setTitle("申诉");
        //获取类型
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        type = intent.getIntExtra("TYPE", 0);
        if (type ==0){
            //设置按钮是否显示
            complaintinfoFinish.setVisibility(View.GONE);
            //设置处理状态
            complaintinfoType.setTextColor(getResources().getColor(R.color.colorbuy));
            complaintinfoType.setText("已处理");
            //设置客服处理
            complaintinfoDealing.setTextColor(getResources().getColor(R.color.colorOrderMText));
            complaintinfoDealingInfo.setText("您说的这个问题我了解到了，我方目前已经联 系到对方卖家，他将在一小时内放币给您，请稍作等待。");

            //设置处理结果
            complaintinfoDealResult.setTextColor(getResources().getColor(R.color.colorOrderMText));
            complaintinfoDealResultInfo.setText("放行交易资产");
            complaintinfoDealResultInfo.setTextColor(getResources().getColor(R.color.colorbuy));
            //设置处理时间
            complaintinfoDealTime.setTextColor(getResources().getColor(R.color.colorOrderMText));
            complaintinfoDealTimeInfo.setText("2018.09.12 02:06:45");

        }else{
            //设置按钮是否显示
            complaintinfoFinish.setVisibility(View.VISIBLE);
            //设置处理状态
            complaintinfoType.setTextColor(getResources().getColor(R.color.colorsell));
            complaintinfoType.setText("待处理");
            //设置客服处理
            complaintinfoDealing.setTextColor(getResources().getColor(R.color.colorOrderText));
            complaintinfoDealingInfo.setText("");

            //设置处理结果
            complaintinfoDealResult.setTextColor(getResources().getColor(R.color.colorOrderText));
            complaintinfoDealResultInfo.setText("");
            complaintinfoDealResultInfo.setTextColor(getResources().getColor(R.color.colorbuy));
            //设置处理时间
            complaintinfoDealTime.setTextColor(getResources().getColor(R.color.colorOrderText));
            complaintinfoDealTimeInfo.setText("");
        }
    }
    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {

    }
}
