package ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import base.BasePresenter;
import base.MVPBaseFragment;
import contract.fragment.ForgetPasswordContract;
import presenter.fragment.ForgetPasswordPresenter;
import utils.StatusUtils;
import ui.widget.TextEditTextView;

public class ForgetPasswordFragment extends MVPBaseFragment<ForgetPasswordContract.View, ForgetPasswordPresenter> implements ForgetPasswordContract.View, View.OnClickListener {

    private FrameLayout fOrgetFramelayout;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.forgetpassword;
    }

    @Override
    protected void setUpView() {
        Button back=getContentView().findViewById(R.id.back);
        TextView title=getContentView().findViewById(R.id.title);
        TextEditTextView fOrgetNumber=getContentView().findViewById(R.id.forget_number);
        TextEditTextView fOrgetCodeEt=getContentView().findViewById(R.id.forget_code_et);
        Button fOrgetCodeBt=getContentView().findViewById(R.id.forget_code_bt);
        Button fOrgetNext=getContentView().findViewById(R.id.forget_next);
        fOrgetFramelayout = getContentView().findViewById(R.id.forget_framelayout);

        title.setText(R.string.forgetpassword_title);
        back.setOnClickListener(this);
        fOrgetCodeBt.setOnClickListener(this);
        fOrgetNext.setOnClickListener(this);
        //软键盘弹出
        fOrgetNumber.setOnKeyBoardHideListener(this);
        fOrgetCodeEt.setOnKeyBoardHideListener(this);

        fOrgetNumber.setOnEditorActionListener(this);
        fOrgetCodeEt.setOnEditorActionListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                //返回
                goback();
                break;
            case R.id.forget_code_bt:
                //获取验证码
                break;
            case R.id.forget_next:
                //下一步
                fOrgetFramelayout.setVisibility(View.VISIBLE);
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.forget_framelayout,new ReSetPasswordFragment(),"reset_password").commit();
                break;
        }
    }
    private void goback() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment forget_password = fragmentManager.findFragmentByTag("forget_password");
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(forget_password);
        fragmentTransaction.commit();
    }


}
