package ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhonglian.battleworld.R;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import base.MVPBaseActivity;
import contract.activity.SystemSetContract;
import presenter.activity.SystemSetPresenter;
import ui.widget.CircleBar;
import utils.DataCleanManager;
import utils.DialogUtils;
import ui.widget.MyActionBar;
import utils.StatusUtils;
import ui.fragment.DenominatedFragment;
import ui.fragment.LanguageFragment;

public class SystemSetActivity extends MVPBaseActivity<SystemSetContract.View, SystemSetPresenter> implements SystemSetContract.View, View.OnClickListener, View.OnTouchListener {

    private FrameLayout sYstemFramelayout;
     private AlertDialog dialog;
    private int totalProgress = 100;
    private int currentProgress;
    private double clearing = 0.0;
    private CircleBar mCircleBar;
    private FrameLayout sYstemCircleFramelayout;
    private Handler handler = new Handler();
    private File file;
    private TextView sYstemCache;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_systemset;
    }

    @Override
    protected void setUpView() {
        MyActionBar sYstemActionBar = findViewById(R.id.system_actionbar);
        LinearLayout sYstemLanguage = findViewById(R.id.system_language);
        LinearLayout sYstemRefresh = findViewById(R.id.system_refresh);
        LinearLayout sYstemWay = findViewById(R.id.system_way);
        LinearLayout sYstemClear = findViewById(R.id.system_clear);
        Button leave = findViewById(R.id.system_leave);
        sYstemCache = findViewById(R.id.system_cache);
        sYstemCircleFramelayout = findViewById(R.id.system_circle_framelayout);
        mCircleBar = (CircleBar) findViewById(R.id.system_circlerbar);
        sYstemFramelayout = findViewById(R.id.system_framelayout);


        sYstemCircleFramelayout.setOnTouchListener(this);

        //获取缓存大小
        file = new File(Environment.getExternalStorageDirectory().getPath() + "/BattleWorld/MyHead/");
        try {
            sYstemCache.setText(DataCleanManager.getCacheSize(file));

        } catch (Exception e) {
            e.printStackTrace();
        }


        sYstemActionBar.setTitle(getResources().getString(R.string.my_setting));
        sYstemLanguage.setOnClickListener(this);
        sYstemRefresh.setOnClickListener(this);
        sYstemWay.setOnClickListener(this);
        sYstemClear.setOnClickListener(this);
        leave.setOnClickListener(this);


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    //更新 清理缓存进度条
    class ProgressRunable implements Runnable {

        @Override
        public void run() {
            try {
                //设置缓存size 保留2位小数
                BigDecimal result4 = new BigDecimal(DataCleanManager.getRealSize(DataCleanManager.getFolderSize(file)));
                clearing = Double.parseDouble(result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
                Double clearCount = Double.parseDouble(result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
                DecimalFormat decimalFormat = new DecimalFormat("#.00");
                //设置百分比进度
                while (currentProgress < totalProgress) {
                    //获取当前百分比进度
                    int v = (int) (clearing / clearCount * 100);
                    //设置当前进度
                    currentProgress =100-v;
                    mCircleBar.setProgress(currentProgress);
                    mCircleBar.setCache(DataCleanManager.getCacheSize(file));
                    mCircleBar.setClearing(Double.parseDouble(decimalFormat.format(clearing = clearing - 0.01)));
                    //设置百分比进度条显示时长为 清除缓存时间
                    Thread.sleep(10);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        sYstemCache.setText(DataCleanManager.getCacheSize(file));
                        sYstemCircleFramelayout.setVisibility(View.GONE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    @Override
    protected void setUpData() {

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.system_language:
                sYstemFramelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.system_framelayout, new LanguageFragment(), "language").commit();
                //语言选择
                break;
            case R.id.system_refresh:
                //刷新频率
                showButtomDialog();
                break;
            case R.id.system_way:
                //计价方式
                sYstemFramelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.system_framelayout, new DenominatedFragment(), "denominated").commit();
                break;
            case R.id.system_clear:
                //清除缓存
                showClearDialog();
                break;
            case R.id.system_leave:
                //退出登录
                Toast.makeText(this, "退出账号", Toast.LENGTH_SHORT).show();
                break;
            case R.id.dialog_clear_cancel:
                StatusUtils.hideBottomUIMenu(this);
                dialog.dismiss();
                break;
            case R.id.dialog_clear_finish:
                dialog.dismiss();
                //弹出清理进度条
                showDialogClearCircle();
                break;
        }
    }

    private void showButtomDialog() {
        Intent intent = new Intent(this, RefreshDialogActivity.class);
        startActivity(intent);
    }

    private void showDialogClearCircle() {
        currentProgress = 0;
        sYstemCircleFramelayout.setVisibility(View.VISIBLE);
        //开始清除缓存
//        DataCleanManager.cleanDatabases(SystemSetActivity.this);
//        DataCleanManager.cleanSharedPreference(SystemSetActivity.this);
//        DataCleanManager.cleanInternalCache(SystemSetActivity.this);
//        DataCleanManager.cleanCustomCache(file + "");
        new Thread(new ProgressRunable()).start();
    }



    //显示居中弹出框
    private void showClearDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SystemSetActivity.this);
        builder.setView(R.layout.dialog_clear);
        dialog = builder.create();
        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.MyDialog);
        dialog.show();

        TextView cAncel = dialog.findViewById(R.id.dialog_clear_cancel);
        TextView fInish = dialog.findViewById(R.id.dialog_clear_finish);


        cAncel.setOnClickListener(this);
        fInish.setOnClickListener(this);

        //dialog 空白处点击事件
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                dialog.dismiss();
                StatusUtils.hideBottomUIMenu(SystemSetActivity.this);
            }
        });
    }
}
