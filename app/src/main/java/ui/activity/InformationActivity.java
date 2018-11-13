package ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import base.MVPBaseActivity;
import contract.activity.InformationContract;
import presenter.activity.InformationPresenter;

public class InformationActivity extends MVPBaseActivity<InformationContract.View, InformationPresenter> implements InformationContract.View ,View.OnClickListener {
    private static String path = Environment.getExternalStorageDirectory().getPath() + "/BattleWorld/MyHead/";// sd路径
    private ImageView iNfoHead;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_info;
    }

    @Override
    protected void setUpView() {
        iNfoHead = (ImageView) findViewById(R.id.info_head);
        ImageButton iNfoHeadNext = (ImageButton) findViewById(R.id.info_head_next);
        ImageButton iNfoNameNext = (ImageButton) findViewById(R.id.info_name_next);
        RelativeLayout iNfoNameItem = (RelativeLayout) findViewById(R.id.info_name_item);
        RelativeLayout iNfoSexItem = (RelativeLayout) findViewById(R.id.info_sex_item);
        ImageButton iNfoSexNext = (ImageButton) findViewById(R.id.info_sex_next);
        RelativeLayout iNfoPersonalityItem = (RelativeLayout) findViewById(R.id.info_personality_item);
        ImageButton iNfoPersonalityNext = (ImageButton) findViewById(R.id.info_personality_next);
        ImageButton iNfoCodeNext = (ImageButton) findViewById(R.id.info_code_next);
        ImageView iNfoCode = (ImageView) findViewById(R.id.info_code);
        RelativeLayout iNfoCodeItem = (RelativeLayout) findViewById(R.id.info_code_item);
        RelativeLayout iNfoAddressItem = (RelativeLayout) findViewById(R.id.info_address_item);
        ImageView iNforAddressNext = (ImageView) findViewById(R.id.info_address_next);


        initListener(iNfoHeadNext, iNfoNameNext, iNfoNameItem, iNfoSexItem, iNfoSexNext, iNfoPersonalityItem, iNfoPersonalityNext, iNfoCodeNext, iNfoCode, iNfoCodeItem, iNfoAddressItem, iNforAddressNext);

    }

    private void initListener(ImageButton iNfoHeadNext, ImageButton iNfoNameNext, RelativeLayout iNfoNameItem, RelativeLayout iNfoSexItem, ImageButton iNfoSexNext, RelativeLayout iNfoPersonalityItem, ImageButton iNfoPersonalityNext, ImageButton iNfoCodeNext, ImageView iNfoCode, RelativeLayout iNfoCodeItem, RelativeLayout iNfoAddressItem, ImageView iNforAddressNext) {
        TextView action_title = findViewById(R.id.action_title);
        action_title.setText(R.string.info_center);
        iNfoHead.setOnClickListener(this);
        iNfoHeadNext.setOnClickListener(this);
        iNfoNameNext.setOnClickListener(this);
        iNfoNameItem.setOnClickListener(this);
        iNfoSexNext.setOnClickListener(this);
        iNfoSexItem.setOnClickListener(this);
        iNfoPersonalityItem.setOnClickListener(this);
        iNfoPersonalityNext.setOnClickListener(this);
        iNfoCodeNext.setOnClickListener(this);
        iNfoCode.setOnClickListener(this);
        iNfoCodeItem.setOnClickListener(this);
        iNfoAddressItem.setOnClickListener(this);
        iNforAddressNext.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setUserHead();
    }

    private void setUserHead() {
        Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");// 从SD卡中找头像，转换成Bitmap
        if (bt != null) {
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);// 转换成drawable
            iNfoHead.setImageDrawable(drawable);
        } else {
            /**
             * 如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
             *
             */
            iNfoHead.setBackgroundResource(R.mipmap.ic_launcher);
        }
    }


    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.info_head:
                Intent intent = new Intent(this, EditHeadActivity.class);
                startActivity(intent);
                break;
            case R.id.info_head_next:
                Intent intent1 = new Intent(this, EditHeadActivity.class);
                startActivity(intent1);
                break;
            case R.id.info_name_item:
                Intent intent2 = new Intent(this, NameEditActivity.class);
                startActivity(intent2);
                break;
            case R.id.info_name_next:
                Intent intent3 = new Intent(this, NameEditActivity.class);
                startActivity(intent3);
                break;
            case R.id.info_sex_item:
                Intent intent4 = new Intent(this, SexActivity.class);
                startActivity(intent4);
                break;
            case R.id.info_sex_next:
                Intent intent5 = new Intent(this, SexActivity.class);
                startActivity(intent5);
                break;
            case R.id.info_personality_item:
                Intent intent6 = new Intent(this, PersonalityActivity.class);
                startActivity(intent6);
                break;
            case R.id.info_personality_next:
                Intent intent7 = new Intent(this, PersonalityActivity.class);
                startActivity(intent7);
                break;
            case R.id.info_code:
                Intent intent8 = new Intent(this, ZxingActivity.class);
                startActivity(intent8);
                break;
            case R.id.info_code_item:
                Intent intent9 = new Intent(this, ZxingActivity.class);
                startActivity(intent9);
                break;
            case R.id.info_code_next:
                Intent intent10 = new Intent(this, ZxingActivity.class);
                startActivity(intent10);
                break;
            case R.id.info_address_item:
                Intent intent11 = new Intent(this, AddressActivity.class);
                startActivity(intent11);
                break;
            case R.id.info_address_next:
                Intent intent12 = new Intent(this, AddressActivity.class);
                startActivity(intent12);
                break;
        }
    }
}
