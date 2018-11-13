package ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import base.MVPBaseFragment;
import contract.fragment.PhoneBindContract;
import presenter.fragment.PhoneBindPresenter;
import utils.StatusUtils;
import ui.widget.TextEditTextView;

public class PhoneBindFragment extends MVPBaseFragment<PhoneBindContract.View, PhoneBindPresenter> implements PhoneBindContract.View , View.OnClickListener{
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_phonbind;
    }

    @Override
    protected void setUpView() {
        TextEditTextView bIndNumber=getContentView().findViewById(R.id.bind_number);
        TextEditTextView bIndCodeEt=getContentView().findViewById(R.id.bind_code_et);
        Button bIndCodeBt=getContentView().findViewById(R.id.bind_code_bt);
        Button bIndFinish=getContentView().findViewById(R.id.bind_finish);
        Button back=getContentView().findViewById(R.id.back);
        TextView title=getContentView().findViewById(R.id.title);

        //获取手机号 验证码
        String number = bIndNumber.getText().toString().trim();
        String code = bIndCodeEt.getText().toString().trim();
        title.setText(R.string.phonebind_title);

        back.setOnClickListener(this);
        bIndCodeBt.setOnClickListener(this);
        bIndFinish.setOnClickListener(this);

        //软键盘监听
        bIndNumber.setOnKeyBoardHideListener(this);
        bIndCodeEt.setOnKeyBoardHideListener(this);

        bIndNumber.setOnEditorActionListener(this);
        bIndCodeEt.setOnEditorActionListener(this);
    }


    @Override
    protected void setUpData() {

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bind_code_bt:
                //获取验证码
                break;
            case R.id.bind_finish:
                //确认按钮
                goback();
                break;
            case R.id.back:
                //返回按钮
                goback();
                break;
        }
    }

    private void goback() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment phone_bind = fragmentManager.findFragmentByTag("phone_bind");
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(phone_bind);
        fragmentTransaction.commit();
    }


}
