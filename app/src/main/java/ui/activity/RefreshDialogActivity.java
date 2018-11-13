package ui.activity;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import utils.StatusUtils;


public class RefreshDialogActivity extends Activity implements View.OnClickListener {
    protected int activityCloseEnterAnimation;
    protected int activityCloseExitAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_refresh);

        initSet();
        initView();
    }

    private void initView() {
        //item点击事件
        TextView dIalogCommon = findViewById(R.id.dialog_common);
        TextView dIalogReal = findViewById(R.id.dialog_real);
        TextView dIalogWifi = findViewById(R.id.dialog_wifi);
        TextView dIalogCancel = findViewById(R.id.dialog_cancel);


        dIalogCommon.setOnClickListener(this);
        dIalogReal.setOnClickListener(this);
        dIalogWifi.setOnClickListener(this);
        dIalogCancel.setOnClickListener(this);


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
        //finish时调用退出动画
        StatusUtils.hideBottomUIMenu(this);
        overridePendingTransition(activityCloseEnterAnimation, activityCloseExitAnimation);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_common:
                StatusUtils.hideBottomUIMenu(this);
                finish();
                break;
            case R.id.dialog_real:
                StatusUtils.hideBottomUIMenu(this);
                finish();
                break;
            case R.id.dialog_wifi:
                StatusUtils.hideBottomUIMenu(this);
                finish();
                break;
            case R.id.dialog_cancel:
                StatusUtils.hideBottomUIMenu(this);
                finish();
                break;
        }
    }
}
