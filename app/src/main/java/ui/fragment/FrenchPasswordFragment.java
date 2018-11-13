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
import contract.fragment.FrenchPasswordContract;
import presenter.fragment.FrenchPasswordPresenter;
import utils.StatusUtils;
import ui.widget.TextEditTextView;

public class FrenchPasswordFragment extends MVPBaseFragment<FrenchPasswordContract.View, FrenchPasswordPresenter> implements FrenchPasswordContract.View, View.OnClickListener{

    private FrameLayout fRenchFramelayout;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_frenchpassword;
    }

    @Override
    protected void setUpView() {
        TextEditTextView pAsswordOld=getContentView().findViewById(R.id.password_old);
        TextEditTextView pAsswordNew=getContentView().findViewById(R.id.password_new);
        TextEditTextView pAsswordReNew=getContentView().findViewById(R.id.password_renew);
        Button pAsswordFinish=getContentView().findViewById(R.id.password_finish);
        Button back=getContentView().findViewById(R.id.back);
        TextView title=getContentView().findViewById(R.id.title);
        TextView right=getContentView().findViewById(R.id.right_bt);
        fRenchFramelayout = getContentView().findViewById(R.id.french_framelayout);


        title.setText(R.string.frenchpassword_title);
        right.setVisibility(View.VISIBLE);
        right.setText(R.string.frenchpassword_right);

        back.setOnClickListener(this);
        pAsswordFinish.setOnClickListener(this);
        right.setOnClickListener(this);
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
                goback();
                break;
            case R.id.right_bt:
                //忘记密码
                fRenchFramelayout.setVisibility(View.VISIBLE);
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.french_framelayout,new ForgetPasswordFragment(),"forget_password").commit();
                break;
        }
    }
    private void goback() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment french_password = fragmentManager.findFragmentByTag("french_password");
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(french_password);
        fragmentTransaction.commit();
    }


}
