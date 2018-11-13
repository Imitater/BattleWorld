package ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import base.MVPBaseFragment;
import contract.fragment.HighContract;
import presenter.fragment.HighPresenter;

public class HighFragment extends MVPBaseFragment<HighContract.View, HighPresenter> implements HighContract.View, View.OnClickListener,View.OnTouchListener{
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_high;
    }

    @Override
    protected void setUpView() {
        TextView title=getContentView().findViewById(R.id.title);
        Button back=getContentView().findViewById(R.id.back);



        //设置title
        title.setText("高级认证");
        back.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                FragmentManager fragmentManager = getFragmentManager();
                Fragment identity_high = fragmentManager.findFragmentByTag("identity_high");
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(identity_high);
                fragmentTransaction.commit();
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

}
