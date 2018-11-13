package ui.activity;

import android.view.View;

import com.zhonglian.battleworld.R;

import base.MVPBaseActivity;
import contract.activity.SexContract;
import presenter.activity.SexPresenter;
import ui.widget.CustomCheckBox;
import ui.widget.MyActionBar;

public class SexActivity extends MVPBaseActivity<SexContract.View, SexPresenter> implements SexContract.View {
    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_sex;
    }

    @Override
    protected void setUpView() {
        final CustomCheckBox box1 = (CustomCheckBox) findViewById(R.id.sex_box1);
        final CustomCheckBox box2 = (CustomCheckBox) findViewById(R.id.sex_box2);
        final MyActionBar actionbar = (MyActionBar) findViewById(R.id.sex_actionbar);
        actionbar.setTitle(getString(R.string.sex_change));
        box1.setChecked(false);
        box1.setOnCustomCheckBoxClickListener(new CustomCheckBox.OnCustomCheckBoxClickListener() {
            @Override
            public void onCustomCheckBoxClick(View v) {
                box1.setChecked(true);
                box2.setChecked(false);
            }
        });
        box2.setOnCustomCheckBoxClickListener(new CustomCheckBox.OnCustomCheckBoxClickListener() {
            @Override
            public void onCustomCheckBoxClick(View v) {
                box1.setChecked(false);
                box2.setChecked(true);
            }
        });
    }

    @Override
    protected void setUpData() {

    }


}
