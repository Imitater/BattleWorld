package ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
  import android.view.View;
import android.widget.Button;
 import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.zhonglian.battleworld.R;

import java.util.List;

import base.MVPBaseFragment;
import contract.fragment.AliPayContract;
import presenter.fragment.AliPayPresenter;
import utils.PicLoadUtil;
 import ui.widget.TextEditTextView;

import static android.app.Activity.RESULT_OK;

public class AliPayFragment extends MVPBaseFragment<AliPayContract.View, AliPayPresenter> implements AliPayContract.View, View.OnClickListener{

    private Button addCode;
    private List<LocalMedia> selectList;
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_alipay;
    }

    @Override
    protected void setUpView() {
        Button back=getContentView().findViewById(R.id.back);
        TextView title=getContentView().findViewById(R.id.title);
        Button finish=getContentView().findViewById(R.id.finish);
        TextEditTextView name=getContentView().findViewById(R.id.name);
        TextEditTextView number=getContentView().findViewById(R.id.number);
        addCode = getContentView().findViewById(R.id.add_code);
        title.setText(R.string.ailpay_title);

        finish.setOnClickListener(this);
        back.setOnClickListener(this);
        addCode.setOnClickListener(this);
        //监听软键盘
        name.setOnKeyBoardHideListener(this);
        number.setOnKeyBoardHideListener(this);

        name.setOnEditorActionListener(this);
        number.setOnEditorActionListener(this);
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                goBack();
                break;
            case R.id.finish:
                goBack();
                break;
            case R.id.add_code:
                PicLoadUtil.LoadPic(this,selectList);
                break;
        }
    }

    private void goBack() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment pay_alipay = fragmentManager.findFragmentByTag("pay_alipay");
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(pay_alipay);
        fragmentTransaction.commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                     Bitmap bt = BitmapFactory.decodeFile(selectList.get(0).getCutPath());// 从SD卡中找头像，转换成Bitmap
                    if (bt != null) {
                        @SuppressWarnings("deprecation")
                        Drawable drawable = new BitmapDrawable(bt);// 转换成drawable
                        addCode.setBackground(drawable);
                    }
                    break;
            }
        }
    }
}
