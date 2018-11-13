package ui.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import base.MVPBaseActivity;
import base.MVPBaseOrderActivity;
import contract.activity.ReleaseOrderContract;
import presenter.activity.ReleaseOrderPresenter;
import utils.StatusUtils;

public class ReleaseOrderActivity extends MVPBaseOrderActivity<ReleaseOrderContract.View, ReleaseOrderPresenter> implements ReleaseOrderContract.View, View.OnClickListener{

    TextView releaseorderTime;
    TextView releaseorderMoney;
    TextView releaseorderDealPrice;
    TextView releaseorderDealCount;
    LinearLayout releaseorderPrice;
    TextView releaseorderCollectionName;
    TextView releaseorderNumber;
    TextView releaseorderOrderTime;
    TextView releaseorderOrderNumber;
    TextView releaseorderCollectionReference;
    Button releaseorderComplaint;
    LinearLayout releaseorderPage;
    LinearLayout releaseorderShow;
    private Dialog complainDialog;
    public static ReleaseOrderActivity instance = null;
    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_releasorder;
    }

    @Override
    protected void setUpView() {
        releaseorderTime=findViewById(R.id.releaseorder_time);
        releaseorderMoney=findViewById(R.id.releaseorder_money);
        releaseorderDealPrice=findViewById(R.id.releaseorder_deal_price);
        releaseorderDealCount=findViewById(R.id.releaseorder_deal_count);
        releaseorderPrice=findViewById(R.id.releaseorder_price);
        releaseorderCollectionName=findViewById(R.id.releaseorder_collection_name);
        releaseorderNumber=findViewById(R.id.releaseorder_number);
        releaseorderOrderTime=findViewById(R.id.releaseorder_order_time);
        releaseorderOrderNumber=findViewById(R.id.releaseorder_order_number);
        releaseorderCollectionReference=findViewById(R.id.releaseorder_collection_reference);
        releaseorderComplaint=findViewById(R.id.releaseorder_complaint);
        releaseorderPage=findViewById(R.id.releaseorder_page);
        releaseorderShow=findViewById(R.id.releaseorder_show);
        instance=this;

        releaseorderComplaint.setOnClickListener(this);

    }

    @Override
    protected void setUpData() {

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.releaseorder_complaint:
                //申诉
                showComplaintDialog();
                break;
            case R.id.dialog_complaint_cancel:
                complainDialog.dismiss();
                StatusUtils.hideBottomUIMenu(ReleaseOrderActivity.this);
                //取消
                break;
            case R.id.dialog_complaint_finish:
                //确认申诉
                Intent intent = new Intent(ReleaseOrderActivity.this, ComplaintActivity.class);
                startActivity(intent);
                complainDialog.dismiss();
                StatusUtils.hideBottomUIMenu(ReleaseOrderActivity.this);
                break;
        }
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
                StatusUtils.hideBottomUIMenu(ReleaseOrderActivity.this);
            }
        });
    }
}
