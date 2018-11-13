package ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.zhonglian.battleworld.R;


import java.util.List;

import base.MVPBaseActivity;
import contract.activity.FeedBackContract;
import presenter.activity.FeedBackPresenter;
import ui.widget.MyActionBar;
import utils.PicLoadUtil;
import utils.StatusUtils;
import ui.widget.TextEditTextView;

public class FeedBackActivity extends MVPBaseActivity<FeedBackContract.View, FeedBackPresenter> implements FeedBackContract.View, View.OnClickListener {
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private RelativeLayout iMageItem1;
    private RelativeLayout iMageItem2;
    private RelativeLayout iMageItem3;
    private static final int MAX_COUNT = 1000;
    private TextEditTextView fEedEt;
    private TextView fEedEtCount;
    private TextEditTextView feedContact;
    private List<LocalMedia> selectList;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void setUpView() {
        MyActionBar fEedActionbar = findViewById(R.id.feed_actionbar);
        fEedEt = (TextEditTextView) findViewById(R.id.feed_et);
        feedContact = (TextEditTextView) findViewById(R.id.feed_contact);
        ImageView fEedAdd = (ImageView) findViewById(R.id.feed_add);
        Button fEedFinish = (Button) findViewById(R.id.feed_finish);
        fEedEtCount = findViewById(R.id.feed_et_count);
        iMageItem1 = findViewById(R.id.image_itme1);
        iMageItem2 = findViewById(R.id.image_itme2);
        iMageItem3 = findViewById(R.id.image_itme3);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);

        ImageView iMage1Close = findViewById(R.id.image1_close);
        ImageView iMage2Close = findViewById(R.id.image2_close);
        ImageView iMage3Close = findViewById(R.id.image3_close);


        fEedActionbar.setTitle(getString(R.string.my_feedback));
        String feed = fEedEt.getText().toString().trim();
        fEedEt.addTextChangedListener(mTextWatcher);
        fEedEt.setSelection(fEedEt.length()); // 将光标移动最后一个字符后面

        iMage1Close.setOnClickListener(this);
        iMage2Close.setOnClickListener(this);
        iMage3Close.setOnClickListener(this);
        fEedFinish.setOnClickListener(this);
        fEedAdd.setOnClickListener(this);

        //软键盘弹出监听
        fEedEt.setOnKeyBoardHideListener(this);
        feedContact.setOnKeyBoardHideListener(this);
        fEedEt.setOnEditorActionListener(this);
        feedContact.setOnEditorActionListener(this);

    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.feed_finish:
                //提交反馈
                break;
            case R.id.feed_add:
                //添加图片
                addImage();
                break;
            case R.id.image1_close:
                //关闭图片1
                iMageItem1.setVisibility(View.GONE);
                selectList.remove(0);
                break;
            case R.id.image2_close:
                iMageItem2.setVisibility(View.GONE);
                selectList.remove(1);
                //关闭图片2
                break;
            case R.id.image3_close:
                iMageItem3.setVisibility(View.GONE);
                selectList.remove(2);
                //关闭图片3
                break;
        }
    }

    private void addImage() {
        PicLoadUtil.LoadMorePic(this, selectList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    if (selectList.size() >= 3) {
                        for (int i = 0; i < selectList.size(); i++) {
                            Bitmap bt = BitmapFactory.decodeFile(selectList.get(i).getPath());
                            if (bt != null) {
                                if (i == 0) {
                                    image1.setImageBitmap(bt);
                                    iMageItem1.setVisibility(View.VISIBLE);
                                }
                                if (i == 1) {
                                    image2.setImageBitmap(bt);
                                    iMageItem2.setVisibility(View.VISIBLE);
                                }
                                if (i == 2) {
                                    image3.setImageBitmap(bt);
                                    iMageItem3.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    } else if (selectList.size() >= 2) {
                        for (int i = 0; i < selectList.size(); i++) {
                            Bitmap bt = BitmapFactory.decodeFile(selectList.get(i).getPath());
                            if (bt != null) {
                                if (i == 0) {
                                    image1.setImageBitmap(bt);
                                    iMageItem1.setVisibility(View.VISIBLE);
                                }
                                if (i == 1) {
                                    image2.setImageBitmap(bt);
                                    iMageItem2.setVisibility(View.VISIBLE);
                                }
                                iMageItem3.setVisibility(View.GONE);
                            }
                        }
                    } else if (selectList.size() >= 1) {
                        for (int i = 0; i < selectList.size(); i++) {
                            Bitmap bt = BitmapFactory.decodeFile(selectList.get(i).getPath());
                            if (bt != null) {
                                image1.setImageBitmap(bt);
                                iMageItem1.setVisibility(View.VISIBLE);
                                iMageItem2.setVisibility(View.GONE);
                                iMageItem3.setVisibility(View.GONE);
                            }
                        }
                    }else{
                        iMageItem1.setVisibility(View.GONE);
                        iMageItem2.setVisibility(View.GONE);
                        iMageItem3.setVisibility(View.GONE);
                    }
                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    //textview 监听
    private TextWatcher mTextWatcher = new TextWatcher() {

        private int editStart;

        private int editEnd;

        public void afterTextChanged(Editable s) {
            editStart = fEedEt.getSelectionStart();
            editEnd = fEedEt.getSelectionEnd();

            // 先去掉监听器，否则会出现栈溢出
            fEedEt.removeTextChangedListener(mTextWatcher);

            // 注意这里只能每次都对整个EditText的内容求长度，不能对删除的单个字符求长度
            // 因为是中英文混合，单个字符而言，calculateLength函数都会返回1
            while (calculateLength(s.toString()) > MAX_COUNT) { // 当输入字符个数超过限制的大小时，进行截断操作
                s.delete(editStart - 1, editEnd);
                editStart--;
                editEnd--;
            }
            // mEditText.setText(s);将这行代码注释掉就不会出现后面所说的输入法在数字界面自动跳转回主界面的问题了，多谢@ainiyidiandian的提醒
            fEedEt.setSelection(editStart);

            // 恢复监听器
            fEedEt.addTextChangedListener(mTextWatcher);

            setLeftCount();
        }

        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {

        }

    };


    private long calculateLength(CharSequence c) {
        double len = 0;
        for (int i = 0; i < c.length(); i++) {
            int tmp = (int) c.charAt(i);
            if (tmp > 0 && tmp < 127) {
                len += 0.5;
            } else {
                len++;
            }
        }
        return Math.round(len);
    }


    private void setLeftCount() {
        fEedEtCount.setText(String.valueOf((MAX_COUNT - getInputCount())) + "/" + MAX_COUNT);
    }


    private long getInputCount() {
        return calculateLength(fEedEt.getText().toString());
    }

}
