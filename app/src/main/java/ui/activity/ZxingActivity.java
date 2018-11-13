package ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.ImageView;

import com.zhonglian.battleworld.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import base.MVPBaseActivity;
import contract.activity.ZxingContract;
import presenter.activity.ZxingPresenter;
import ui.widget.MyActionBar;
import utils.ZxingUtils;

public class ZxingActivity extends MVPBaseActivity<ZxingContract.View, ZxingPresenter> implements ZxingContract.View {
    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_zxing;
    }

    @Override
    protected void setUpView() {
        ImageView zXingCode = (ImageView) findViewById(R.id.zxing_code);
        MyActionBar zXingActionbar = (MyActionBar) findViewById(R.id.zxing_actionbar);
        zXingActionbar.setTitle(getString(R.string.zxing_code));
        try {
            boolean success = ZxingUtils.createQRImage("灭霸",
                    getWindowManager().getDefaultDisplay().getWidth(),
                    getWindowManager().getDefaultDisplay().getHeight() / 2,
                    BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher),
                    Environment.getExternalStorageDirectory().getPath() + "/BattleWorld/MyHead/Code/");
            if (success) {
                Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(Environment.getExternalStorageDirectory().getPath() + "/BattleWorld/MyHead/Code/"));
                zXingCode.setImageBitmap(bitmap);
            }else{
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setUpData() {

    }

}
