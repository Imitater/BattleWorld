package ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import ui.widget.TextEditTextView;
import utils.StatusUtils;

public class SellOrderDialogActivity extends Activity implements DialogInterface.OnDismissListener, View.OnClickListener, TextEditTextView.OnKeyBoardHideListener, TextView.OnEditorActionListener {
    private int item;
    protected int activityCloseEnterAnimation;
    protected int activityCloseExitAnimation;
    private String money;
    private String count;
    private TextEditTextView sAfeDialogPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sign_control);

        initData();
        initSet();
        initView();

    }

    private void initData() {
        Intent intent = getIntent();
        money = intent.getStringExtra("MONEY");
        count = intent.getStringExtra("COUNT");
    }

    private void initView() {
        //获取数据
        Intent intent = getIntent();
        item = intent.getIntExtra("Type", 0);

        Button sAfeDialogClose = findViewById(R.id.safe_dialog_close);
        sAfeDialogPassword = findViewById(R.id.safe_dialog_password);
        Button sAfeDialogFinish = findViewById(R.id.safe_dialog_finish);
        //安全验证框内部点击事件
        sAfeDialogClose.setOnClickListener(this);
        sAfeDialogFinish.setOnClickListener(this);
        sAfeDialogPassword.setOnKeyBoardHideListener(this);


        //获取手势密码：
        String password = sAfeDialogPassword.getText().toString().trim();

        sAfeDialogPassword.setOnKeyBoardHideListener(this);
        sAfeDialogPassword.setOnEditorActionListener(this);
    }

    private void initSet() {
        TypedArray activityStyle = getTheme().obtainStyledAttributes(new int[]{android.R.attr.windowAnimationStyle});
        int windowAnimationStyleResId = activityStyle.getResourceId(0, 0);
        activityStyle.recycle();
        activityStyle = getTheme().obtainStyledAttributes(windowAnimationStyleResId, new int[]{android.R.attr.activityCloseEnterAnimation, android.R.attr.activityCloseExitAnimation});
        activityCloseEnterAnimation = activityStyle.getResourceId(0, 0);
        activityCloseExitAnimation = activityStyle.getResourceId(1, 0);
        activityStyle.recycle();

        //设置布局在底部
        getWindow().setGravity(Gravity.BOTTOM);
        //设置布局填充满宽度
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(layoutParams);
    }

    @Override
    protected void onStart() {
        super.onStart();
        StatusUtils.hideBottomUIMenu(this);
    }

    //重写finish方法
    @Override
    public void finish() {
        super.finish();
        //关闭虚拟键盘底部导航栏
        closePutDialog();
        StatusUtils.hideBottomUIMenu(this);
        //finish时调用退出动画
        overridePendingTransition(activityCloseEnterAnimation, activityCloseExitAnimation);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        dialog.dismiss();
        StatusUtils.hideBottomUIMenu(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.safe_dialog_close:
                finish();
                break;
            case R.id.safe_dialog_finish:
                finish();
                //进行页面跳转
                Intent intent = new Intent(SellOrderDialogActivity.this, SellOrderCompleteActivity.class);
                startActivity(intent);
                finish();
                break;

        }
    }

    //关闭软键盘 底部导航栏
    private void closePutDialog() {
        //关闭输入键盘
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(sAfeDialogPassword.getWindowToken(), 0);
    }

    @Override
    public void onKeyHide(int keyCode, KeyEvent event) {
        StatusUtils.hideBottomUIMenu(this);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            StatusUtils.hideBottomUIMenu(this);
            //隐藏软键盘
            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
            }
            return true;
        }

        return false;
    }


}
