package ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import utils.DialogUtils;
import utils.StatusUtils;
import ui.widget.TextEditTextView;
import ui.adapter.UserInfoRecyclerviewAdapter;


public class UserInfoActivity extends Activity implements View.OnClickListener,TextEditTextView.OnKeyBoardHideListener,EditText.OnEditorActionListener{
    Button back;
    TextView userinfoHeadText;
    ImageView userinfoCircle;
    TextView userinfoUsername;
    TextView userinfoCertification;
    TextView userinfoTime;
    TextView userinfoDealCount;
    TextView userinfoComplaintCount;
    TextView userinfo30Count;
    TextView userinfoWinCount;
    TextView userinfoCompletionRate;
    TextView userinfoAvg;
    LinearLayout userinfoPhone;
    LinearLayout userinfoEmail;
    LinearLayout userinfoIdentity;
    LinearLayout userinfoHigh;
    RecyclerView userinfoRecyclerview;
    private Dialog dialog;
    private TextEditTextView dialogMoneyEt;
    private TextEditTextView dialogCountEt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        back = (Button) findViewById(R.id.back);
        userinfoHeadText = findViewById(R.id.userinfo_head_text);
        userinfoCircle = findViewById(R.id.userinfo_circle);
        userinfoUsername = findViewById(R.id.userinfo_username);
        userinfoCertification = findViewById(R.id.userinfo_certification);
        userinfoTime = findViewById(R.id.userinfo_time);
        userinfoDealCount = findViewById(R.id.userinfo_deal_count);
        userinfoComplaintCount = findViewById(R.id.userinfo_complaint_count);
        userinfo30Count = findViewById(R.id.userinfo_30_count);
        userinfoWinCount = findViewById(R.id.userinfo_win_count);
        userinfoCompletionRate = findViewById(R.id.userinfo_completion_rate);
        userinfoAvg = findViewById(R.id.userinfo_avg);
        userinfoPhone = findViewById(R.id.userinfo_phone);
        userinfoEmail = findViewById(R.id.userinfo_email);
        userinfoIdentity = findViewById(R.id.userinfo_identity);
        userinfoHigh = findViewById(R.id.userinfo_high);
        userinfoRecyclerview = findViewById(R.id.userinfo_recyclerview);


        //设置Recyclerview 列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        userinfoRecyclerview.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        final UserInfoRecyclerviewAdapter userInfoRecyclerviewAdapter = new UserInfoRecyclerviewAdapter(UserInfoActivity.this);
        userinfoRecyclerview.setAdapter(userInfoRecyclerviewAdapter);

        //adapter item点击事件
        userInfoRecyclerviewAdapter.buttonSetOnclick(new UserInfoRecyclerviewAdapter.ButtonInterface(){

            @Override
            public void onclick(View view, int position) {
                //显示底部弹出框
                showButtomDialog(userInfoRecyclerviewAdapter.getBuyOrSell());
            }
        });


        //监听事件
        initListener();

    }



    //显示底部弹出框
    private void showButtomDialog(boolean buyOrSell) {
        dialog = new DialogUtils(this,this, R.layout.dialog_deal, true, true);
        dialog.show();
        Button dialogClose=dialog.findViewById(R.id.dialog_close);
        TextView dialogTitle=dialog.findViewById(R.id.dialog_title);
        //设置 购买or出售
        if (buyOrSell){
            dialogTitle.setText("购买USDT");
        }else{
            dialogTitle.setText("出售USDT");
        }
        dialogClose.setOnClickListener(this);
        dialogMoneyEt = dialog.findViewById(R.id.dialog_money_et);
        dialogCountEt = dialog.findViewById(R.id.dialog_count_et);
        //输入法返回
        dialogMoneyEt.setOnKeyBoardHideListener(this);
        dialogCountEt.setOnKeyBoardHideListener(this);
        dialogMoneyEt.setOnEditorActionListener(this);
        dialogCountEt.setOnEditorActionListener(this);
        //dialog 空白处点击事件
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                dialog.dismiss();
                StatusUtils.hideBottomUIMenu(UserInfoActivity.this);
            }
        });
    }

    private void initListener() {
        back.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        StatusUtils.hideBottomUIMenu(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.dialog_close:
                dialog.dismiss();
                //缩回底部导航
                StatusUtils.hideBottomUIMenu(UserInfoActivity.this);
                break;
        }
    }

    @Override
    public void onKeyHide(int keyCode, KeyEvent event) {
        StatusUtils.hideBottomUIMenu(this);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_DONE){
            StatusUtils.hideBottomUIMenu(this);
            //隐藏软键盘
            InputMethodManager imm = (InputMethodManager) v
                    .getContext().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(
                        v.getApplicationWindowToken(), 0);
            }
            return true;
        }
        return false;
    }
}
