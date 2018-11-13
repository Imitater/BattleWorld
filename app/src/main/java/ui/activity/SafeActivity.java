package ui.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.kyleduo.switchbutton.SwitchButton;
import com.zhonglian.battleworld.R;

import base.MVPBaseActivity;
import contract.activity.SafeContract;
import presenter.activity.SafePresenter;
import utils.DialogUtils;
import ui.widget.MyActionBar;
import utils.StatusUtils;
import ui.widget.TextEditTextView;
import ui.fragment.BindEmailFragment;
import ui.fragment.FrenchPasswordFragment;
import ui.fragment.LoginPasswordFragment;
import ui.fragment.PhoneBindFragment;

public class SafeActivity extends MVPBaseActivity<SafeContract.View, SafePresenter> implements SafeContract.View, View.OnClickListener{

    private Dialog dialogUtils;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_safe;
    }

    @Override
    protected void setUpView() {
        MyActionBar sAfeAcitonBar=findViewById(R.id.safe_actionbar);
        LinearLayout sAfeEmail = (LinearLayout) findViewById(R.id.safe_email);
        LinearLayout sAfePhone = (LinearLayout) findViewById(R.id.safe_phone);
        LinearLayout sAfeLoginPassword = (LinearLayout) findViewById(R.id.safe_login_password);
        LinearLayout sAfeFrenchPassword = (LinearLayout) findViewById(R.id.safe_franch_password);
        LinearLayout sAfeGesturePassword = (LinearLayout) findViewById(R.id.safe_gesture_password);
        final SwitchButton sAfeSwitchButton = (SwitchButton) findViewById(R.id.safe_switchbutton);
        //点击事件
        sAfeEmail.setOnClickListener(this);
        sAfePhone.setOnClickListener(this);
        sAfeLoginPassword.setOnClickListener(this);
        sAfeFrenchPassword.setOnClickListener(this);
        sAfeGesturePassword.setOnClickListener(this);
        sAfeSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //显示dialog 关闭手势密码
                if (!isChecked) {
                    showDialog();
                    sAfeSwitchButton.setBackColor(ColorStateList.valueOf(getResources().getColor(R.color.colorSwitchBackOff)));
                }else{
                    showDialog();
                    sAfeSwitchButton.setBackColor(ColorStateList.valueOf(getResources().getColor(R.color.colorSwitchBackOn)));
                }
            }
        });
        //设置默认switchbutton 开关
        sAfeSwitchButton.setCheckedNoEvent(true);
        sAfeAcitonBar.setTitle(getString(R.string.my_safe));
    }

    private void showDialog() {
        Intent intent = new Intent(SafeActivity.this, LoginDialogActivity.class);
        startActivity(intent);
    }


    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.safe_email:
                getSupportFragmentManager().beginTransaction().add(R.id.safe_framelayout,new BindEmailFragment(),"email_bind").commit();
                break;
            case R.id.safe_phone:
                getSupportFragmentManager().beginTransaction().add(R.id.safe_framelayout,new PhoneBindFragment(),"phone_bind").commit();
                break;
            case R.id.safe_login_password:
                getSupportFragmentManager().beginTransaction().add(R.id.safe_framelayout,new LoginPasswordFragment(),"login_password").commit();
                break;
            case R.id.safe_franch_password:
                getSupportFragmentManager().beginTransaction().add(R.id.safe_framelayout,new FrenchPasswordFragment(),"french_password").commit();
                break;
        }
    }
}
