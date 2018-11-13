package ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import base.BasePresenter;
import base.MVPBaseFragment;
import contract.fragment.BindEmailContract;
import presenter.fragment.BindEmailPresenter;
import utils.StatusUtils;
import ui.widget.TextEditTextView;

public class BindEmailFragment extends MVPBaseFragment<BindEmailContract.View, BindEmailPresenter> implements BindEmailContract.View, View.OnClickListener{
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_bindemail;
    }

    @Override
    protected void setUpView() {
        TextEditTextView eMailNumber=getContentView().findViewById(R.id.email_number);
        TextEditTextView eMailCodeEt=getContentView().findViewById(R.id.email_code_et);
        Button eMailCodeBt=getContentView().findViewById(R.id.email_code_bt);
        Button eMailPhoneCodeBt=getContentView().findViewById(R.id.email_phone_code_bt);
        TextEditTextView eMailPhoneCodeEt=getContentView().findViewById(R.id.email_phone_code_et);
        Button eMailFinish=getContentView().findViewById(R.id.email_finish);
        Button back=getContentView().findViewById(R.id.back);
        TextView title=getContentView().findViewById(R.id.title);


        eMailCodeBt.setOnClickListener(this);
        eMailPhoneCodeBt.setOnClickListener(this);
        back.setOnClickListener(this);

        title.setText(R.string.bindemail_title);

        //软键盘弹出监听
        eMailNumber.setOnKeyBoardHideListener(this);
        eMailCodeEt.setOnKeyBoardHideListener(this);
        eMailPhoneCodeEt.setOnKeyBoardHideListener(this);

        eMailNumber.setOnEditorActionListener(this);
        eMailCodeEt.setOnEditorActionListener(this);
        eMailPhoneCodeEt.setOnEditorActionListener(this);
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.email_code_bt:
                //邮箱验证
                break;
            case R.id.email_phone_code_bt:
                //手机验证
                break;
            case R.id.back:
                goback();
                break;
        }
    }

    private void goback() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment email_bind = fragmentManager.findFragmentByTag("email_bind");
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(email_bind);
        fragmentTransaction.commit();
    }
}
