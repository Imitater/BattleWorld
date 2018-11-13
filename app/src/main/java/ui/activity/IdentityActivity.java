package ui.activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.zhonglian.battleworld.R;

import base.MVPBaseActivity;
import contract.activity.IdentityContract;
import presenter.activity.IdentityPresenter;
import ui.widget.MyActionBar;
import ui.fragment.HighFragment;
import ui.fragment.IntermediateFragment;
import ui.fragment.PrimaryFragment;

public class

IdentityActivity extends MVPBaseActivity<IdentityContract.View, IdentityPresenter> implements IdentityContract.View,View.OnClickListener{

    private FrameLayout iDentityFrameLayout;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_identity;
    }

    @Override
    protected void setUpView() {
        MyActionBar iDentityActionBar = (MyActionBar) findViewById(R.id.identity_actionbar);
        LinearLayout iDentityPrimary = (LinearLayout) findViewById(R.id.identity_primary);
        LinearLayout iDentityIntermediate = (LinearLayout) findViewById(R.id.identity_intermediate);
        LinearLayout iDentityHigh = (LinearLayout) findViewById(R.id.identity_high);
        iDentityFrameLayout = findViewById(R.id.identity_framelayout);
        iDentityActionBar.setTitle(getString(R.string.my_identity));


        iDentityPrimary.setOnClickListener(this);
        iDentityIntermediate.setOnClickListener(this);
        iDentityHigh.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.identity_primary:
                //初级认证
                iDentityFrameLayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.identity_framelayout,new PrimaryFragment(),"identity_common").commit();
                break;
            case R.id.identity_intermediate:
                //中级认证
                iDentityFrameLayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.identity_framelayout,new IntermediateFragment(),"identity_intermediate").commit();
                break;
            case R.id.identity_high:
                //高级认证
                iDentityFrameLayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.identity_framelayout,new HighFragment(),"identity_high").commit();
                break;
        }
    }
}
