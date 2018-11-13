package ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import base.BasePresenter;
import base.MVPBaseFragment;
import contract.fragment.DenominatedContract;
import presenter.fragment.DenominatedPresenter;
import ui.widget.CustomCheckBox;

public class DenominatedFragment extends MVPBaseFragment<DenominatedContract.View, DenominatedPresenter> implements DenominatedContract.View , CustomCheckBox.OnCustomCheckBoxClickListener,View.OnClickListener {

    private String[] strTitle= new String[7];
    private CustomCheckBox check1;
    private CustomCheckBox check2;
    private CustomCheckBox check3;
    private CustomCheckBox check4;
    private CustomCheckBox check5;
    private CustomCheckBox check6;
    private CustomCheckBox check7;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_denominated;
    }

    @Override
    protected void setUpView() {

        Button back=getContentView().findViewById(R.id.back);
        TextView title=getContentView().findViewById(R.id.title);
        check1 = getContentView().findViewById(R.id.denominated_checkbox1);
        check2 = getContentView().findViewById(R.id.denominated_checkbox2);
        check3 = getContentView().findViewById(R.id.denominated_checkbox3);
        check4 = getContentView().findViewById(R.id.denominated_checkbox4);
        check5 = getContentView().findViewById(R.id.denominated_checkbox5);
        check6 = getContentView().findViewById(R.id.denominated_checkbox6);
        check7 = getContentView().findViewById(R.id.denominated_checkbox7);
        //初始化选中状态
        setChecked();

        //选中监听
        initCheckListener();

        title.setText(getString(R.string.system_way));
        back.setOnClickListener(this);
    }

    private void initCheckListener() {
        check1.setOnCustomCheckBoxClickListener(this);
        check2.setOnCustomCheckBoxClickListener(this);
        check3.setOnCustomCheckBoxClickListener(this);
        check4.setOnCustomCheckBoxClickListener(this);
        check5.setOnCustomCheckBoxClickListener(this);
        check6.setOnCustomCheckBoxClickListener(this);
        check7.setOnCustomCheckBoxClickListener(this);
    }

    private void setChecked() {
        check1.setChecked(true);
        check2.setChecked(false);
        check3.setChecked(false);
        check4.setChecked(false);
        check5.setChecked(false);
        check6.setChecked(false);
        check7.setChecked(false);
    }

    @Override
    protected void setUpData() {

    }



    @Override
    public void onCustomCheckBoxClick(View v) {
        switch (v.getId()){
            case R.id.denominated_checkbox1:
                setChecked(true,false,false,false,false,false,false);
                break;
            case R.id.denominated_checkbox2:
                setChecked(false,true,false,false,false,false,false);
                break;
            case R.id.denominated_checkbox3:
                setChecked(false,false,true,false,false,false,false);
                break;
            case R.id.denominated_checkbox4:
                setChecked(false,false,false,true,false,false,false);
                break;
            case R.id.denominated_checkbox5:
                setChecked(false,false,false,false,true,false,false);
                break;
            case R.id.denominated_checkbox6:
                setChecked(false,false,false,false,false,true,false);
                break;
            case R.id.denominated_checkbox7:
                setChecked(false,false,false,false,false,false,true);
                break;
        }
    }

    public void setChecked(boolean flag1,boolean flag2,boolean flag3,boolean flag4,boolean flag5,boolean flag6,boolean flag7) {
        check1.setChecked(flag1);
        check2.setChecked(flag2);
        check3.setChecked(flag3);
        check4.setChecked(flag4);
        check5.setChecked(flag5);
        check6.setChecked(flag6);
        check7.setChecked(flag7);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                FragmentManager fragmentManager = getFragmentManager();
                Fragment denominated = fragmentManager.findFragmentByTag("denominated");
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(denominated);
                fragmentTransaction.commit();
                break;
        }
    }
}
