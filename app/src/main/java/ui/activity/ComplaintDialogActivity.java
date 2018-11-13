package ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import event.EventBusUtils;
import event.EventCode;
import event.EventMessage;
import utils.SoftKeyboardStateHelper;
import utils.StatusUtils;

public class ComplaintDialogActivity extends Activity implements View.OnClickListener {
    protected int activityCloseEnterAnimation;
    protected int activityCloseExitAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_complaint_reason);

        initSet();
        initView();
        //注册 eventbus
        EventBusUtils.register(this);
    }

    /**
     * 接收到分发的事件
     *
     * @param event 事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(EventMessage event) {
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销eventbus
        EventBusUtils.unregister(this);
    }


    private void initView() {
        //item点击事件
        TextView dialogUnpay = findViewById(R.id.dialog_unpay);
        TextView dialogUnrelease = findViewById(R.id.dialog_unrelease);
        TextView dialogUnanswer = findViewById(R.id.dialog_unanswer);
        TextView dialogFraud = findViewById(R.id.dialog_fraud);
        TextView dialogAnother = findViewById(R.id.dialog_another);
        TextView dialogClose = findViewById(R.id.dialog_close);


        dialogUnpay.setOnClickListener(this);
        dialogUnrelease.setOnClickListener(this);
        dialogUnanswer.setOnClickListener(this);
        dialogFraud.setOnClickListener(this);
        dialogAnother.setOnClickListener(this);
        dialogClose.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        EventMessage eventMessage = null;
        switch (v.getId()) {
            case R.id.dialog_unpay:
                //对方未支付
                //发送消息
                eventMessage = new EventMessage(EventCode.EVENT_A, 0);
                post(eventMessage);
                finish();
                break;
            case R.id.dialog_unrelease:
                //对方未放行
                //发送消息
                eventMessage = new EventMessage(EventCode.EVENT_A, 1);
                post(eventMessage);
                finish();
                break;
            case R.id.dialog_unanswer:
                //对方无应答
                eventMessage = new EventMessage(EventCode.EVENT_A, 2);
                post(eventMessage);
                finish();
                break;
            case R.id.dialog_fraud:
                //对方有欺诈行为
                eventMessage = new EventMessage(EventCode.EVENT_A, 3);
                post(eventMessage);
                finish();
                break;
            case R.id.dialog_another:
                //其他
                eventMessage = new EventMessage(EventCode.EVENT_A, 4);
                post(eventMessage);
                finish();
                break;
            case R.id.dialog_close:
                finish();
                break;
        }
    }

    /**
     * 发送事件消息
     *
     * @param event
     */
    public void post(EventMessage event) {
        EventBus.getDefault().post(event);
    }

    //重写finish方法
    @Override
    public void finish() {
        super.finish();
        StatusUtils.hideBottomUIMenu(this);
        //finish时调用退出动画
        overridePendingTransition(activityCloseEnterAnimation, activityCloseExitAnimation);
    }
}
