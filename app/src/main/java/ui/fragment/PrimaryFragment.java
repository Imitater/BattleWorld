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
import contract.fragment.PrimaryContract;
import presenter.fragment.PrimaryPresenter;
import utils.StatusUtils;
import ui.widget.TextEditTextView;

public class PrimaryFragment extends MVPBaseFragment<PrimaryContract.View, PrimaryPresenter> implements PrimaryContract.View, View.OnClickListener{
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_common;
    }

    @Override
    protected void setUpView() {
        TextView title=getContentView().findViewById(R.id.title);
        Button back=getContentView().findViewById(R.id.back);
        TextEditTextView cOmmonName=getContentView().findViewById(R.id.common_name);
        TextEditTextView cOmmonNumber=getContentView().findViewById(R.id.common_number);
        Button cOmmonFinish=getContentView().findViewById(R.id.common_finish);


        title.setText(R.string.common_title);
        cOmmonFinish.setOnClickListener(this);
        back.setOnClickListener(this);
        cOmmonName.setOnKeyBoardHideListener(this);
        cOmmonNumber.setOnKeyBoardHideListener(this);
        cOmmonName.setOnEditorActionListener(this);
        cOmmonNumber.setOnEditorActionListener(this);
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                getback();
                break;
            case R.id.common_finish:

                break;
        }
    }

    private void getback() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment identity_common = fragmentManager.findFragmentByTag("identity_common");
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(identity_common);
        fragmentTransaction.commit();
    }

}
