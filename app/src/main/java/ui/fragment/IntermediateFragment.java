package ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import base.MVPBaseFragment;
import contract.fragment.IntermediateContract;
import presenter.fragment.IntermediatePresenter;

public class IntermediateFragment extends MVPBaseFragment<IntermediateContract.View, IntermediatePresenter> implements IntermediateContract.View, View.OnClickListener,View.OnTouchListener{
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_intermediate;
    }

    @Override
    protected void setUpView() {
        Button back=getContentView().findViewById(R.id.back);
        TextView title=getContentView().findViewById(R.id.title);
        TextView right=getContentView().findViewById(R.id.right_bt);
        ScrollView iNtermediateScroll=getContentView().findViewById(R.id.intermediate_scroll);


        iNtermediateScroll.setOnTouchListener(this);
        title.setText(getString(R.string.identity_intermediate));
        right.setVisibility(View.VISIBLE);
        right.setText(getString(R.string.password_finish));
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
                Fragment identity_intermediate = fragmentManager.findFragmentByTag("identity_intermediate");
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(identity_intermediate);
                fragmentTransaction.commit();
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

}
