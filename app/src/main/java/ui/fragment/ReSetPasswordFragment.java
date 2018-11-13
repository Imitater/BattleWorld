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
import contract.fragment.ReSetPasswordContract;
import presenter.fragment.ReSetPasswordPresenter;
import utils.StatusUtils;
import ui.widget.TextEditTextView;

public class ReSetPasswordFragment extends MVPBaseFragment<ReSetPasswordContract.View, ReSetPasswordPresenter> implements ReSetPasswordContract.View, View.OnClickListener, TextEditTextView.OnKeyBoardHideListener{
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_resetpassword;
    }

    @Override
    protected void setUpView() {
        TextEditTextView rEsetPut=getContentView().findViewById(R.id.reset_put);
        TextEditTextView rEsetReput=getContentView().findViewById(R.id.reset_reput);
        Button rEsetBt=getContentView().findViewById(R.id.reset_bt);
        Button back=getContentView().findViewById(R.id.back);
        TextView title=getContentView().findViewById(R.id.title);

        title.setText(R.string.resetpassword_title);
        back.setOnClickListener(this);
        rEsetBt.setOnClickListener(this);
        //软键盘监听
        rEsetPut.setOnKeyBoardHideListener(this);
        rEsetReput.setOnKeyBoardHideListener(this);
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
            case R.id.reset_bt:
                break;
        }
    }
    private void goback() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment forget_password = fragmentManager.findFragmentByTag("reset_password");
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(forget_password);
        fragmentTransaction.commit();
    }

    @Override
    public void onKeyHide(int keyCode, KeyEvent event) {
        StatusUtils.hideBottomUIMenu(getActivity());
    }
}
