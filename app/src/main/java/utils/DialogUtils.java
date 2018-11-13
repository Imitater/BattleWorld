package utils;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zhonglian.battleworld.R;


public class DialogUtils extends Dialog {

    private final Activity activity;
    private boolean iscancelable;//控制点击dialog外部是否dismiss
    private boolean isBackCancelable;//控制返回键是否dismiss
    private int view;
    private Context context;

    public DialogUtils(Context context, Activity activity, int view, boolean isCancelable, boolean isBackCancelable) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.activity = activity;
        this.view = view;
        this.iscancelable = isCancelable;
        this.isBackCancelable = isBackCancelable;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(view);
        setCancelable(iscancelable);
        setCanceledOnTouchOutside(isBackCancelable);
        final Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_FULLSCREEN;
                if (Build.VERSION.SDK_INT >= 19) {
                    uiOptions |= 0x00001000;
                } else {
                    uiOptions |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
                }
                window.getDecorView().setSystemUiVisibility(uiOptions);
            }
        });

        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);

    }
}
