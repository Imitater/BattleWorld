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
import contract.fragment.LoginPasswordContract;
import presenter.fragment.LoginPasswordPresenter;
import utils.StatusUtils;
import ui.widget.TextEditTextView;

public class LoginPasswordFragment extends MVPBaseFragment<LoginPasswordContract.View, LoginPasswordPresenter> implements LoginPasswordContract.View,View.OnClickListener{
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_loginpassword;
    }

    @Override
    protected void setUpView() {
        TextEditTextView pAsswordOld=getContentView().findViewById(R.id.password_old);
        TextEditTextView pAsswordNew=getContentView().findViewById(R.id.password_new);
        TextEditTextView pAsswordReNew=getContentView().findViewById(R.id.password_renew);
        Button pAsswordFinish=getContentView().findViewById(R.id.password_finish);
        Button back=getContentView().findViewById(R.id.back);
        TextView title=getContentView().findViewById(R.id.title);

        title.setText(R.string.loginpassword_title);


        back.setOnClickListener(this);
        pAsswordFinish.setOnClickListener(this);
        //软键盘弹出监听
        pAsswordOld.setOnKeyBoardHideListener(this);
        pAsswordNew.setOnKeyBoardHideListener(this);
        pAsswordReNew.setOnKeyBoardHideListener(this);

        pAsswordOld.setOnEditorActionListener(this);
        pAsswordNew.setOnEditorActionListener(this);
        pAsswordReNew.setOnEditorActionListener(this);
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                goback();
                break;
            case R.id.password_finish:
                //提交

                break;
        }
    }

    private void goback() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment login_password = fragmentManager.findFragmentByTag("login_password");
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(login_password);
        fragmentTransaction.commit();
    }


}
