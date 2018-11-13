package ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
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
import utils.SoftKeyboardStateHelper;
import utils.StatusUtils;

public class DealDialgActivity extends Activity implements DialogInterface.OnDismissListener, View.OnClickListener, TextEditTextView.OnKeyBoardHideListener, TextView.OnEditorActionListener {
    protected int activityCloseEnterAnimation;
    protected int activityCloseExitAnimation;
    private TextEditTextView dialogMoneyEt;
    private TextEditTextView dialogCountEt;
    private int item;
    private CardView dealDialogCardview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_deal);

        initSet();
        initView();
    }

    private void initView() {
        //获取数据
        Intent intent = getIntent();
        item = intent.getIntExtra("Type", 0);


        Button dialogClose = findViewById(R.id.dialog_close);
        TextView dialogTitle = findViewById(R.id.dialog_title);
        Button dialogFinish = findViewById(R.id.dialog_finish);
        dialogMoneyEt = findViewById(R.id.dialog_money_et);
        dialogCountEt = findViewById(R.id.dialog_count_et);
        dealDialogCardview = findViewById(R.id.deal_dialog_cardview);

        //输入法返回
        dialogMoneyEt.setOnKeyBoardHideListener(this);
        dialogCountEt.setOnKeyBoardHideListener(this);
        dialogMoneyEt.setOnEditorActionListener(this);
        dialogCountEt.setOnEditorActionListener(this);

        //设置 购买or出售
        if (item == 0) {
            dialogTitle.setText("购买USDT");
            dialogFinish.setText("买入");
        } else if (item == 1) {
            dialogTitle.setText("出售USDT");
            dialogFinish.setText("出售");
        }

        dialogClose.setOnClickListener(this);
        dialogFinish.setOnClickListener(this);

//
//        SoftKeyboardStateHelper softKeyboardStateHelper = new SoftKeyboardStateHelper(dealDialogCardview);
//        softKeyboardStateHelper.addSoftKeyboardStateListener(new SoftKeyboardStateHelper.SoftKeyboardStateListener() {
//            @Override
//            public void onSoftKeyboardOpened(int keyboardHeightInPx) {
//
//            }
//
//            @Override
//            public void onSoftKeyboardClosed() {
//                StatusUtils.hideBottomUIMenu(DealDialgActivity.this);
//            }
//        });
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

    @Override
    protected void onResume() {
        super.onResume();
        StatusUtils.hideBottomUIMenu(this);
    }


    private void closePutDialog() {
        //关闭输入键盘
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(dialogMoneyEt.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(dialogCountEt.getWindowToken(), 0);
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
            case R.id.dialog_close:
                finish();
                break;
            case R.id.dialog_finish:
                if (item == 0) {
                    //跳转购买详情
                    Intent intent = new Intent(this, BuyOrderInfoActivity.class);
                    intent.putExtra("MONEY", dialogMoneyEt.getText().toString().trim());
                    intent.putExtra("COUNT", dialogCountEt.getText().toString().trim());
                    startActivity(intent);
                } else {
                    //弹出安全验证框
                    Intent intent = new Intent(this, SafeDialogActivity.class);
                    intent.putExtra("MONEY", dialogMoneyEt.getText().toString().trim());
                    intent.putExtra("COUNT", dialogCountEt.getText().toString().trim());
                    startActivity(intent);
                }
                finish();
                break;

        }
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
