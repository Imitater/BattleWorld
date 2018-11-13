package ui.activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.zhonglian.battleworld.R;

import base.MVPBaseActivity;
import contract.activity.AboutContract;
import presenter.activity.AboutPresenter;
import ui.widget.MyActionBar;
import ui.fragment.AddGroupFragment;
import ui.fragment.ServiceLineFragment;
import ui.fragment.VersionFragment;
import ui.fragment.VersionUpFragment;

public class AboutActivity extends MVPBaseActivity<AboutContract.View, AboutPresenter> implements AboutContract.View ,View.OnClickListener{

    private FrameLayout aBoutFragment;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_about;
    }

    @Override
    protected void setUpView() {
        MyActionBar aBoutActionBar=findViewById(R.id.about_actionbar);
        LinearLayout aBoutServer=findViewById(R.id.about_server);
        LinearLayout aBoutGroup=findViewById(R.id.about_group);
        LinearLayout aBoutVersion=findViewById(R.id.about_version);
        LinearLayout aBoutVersionUp=findViewById(R.id.about_version_up);
        aBoutFragment = findViewById(R.id.about_fragment);

        aBoutActionBar.setTitle(getResources().getString(R.string.my_about));

        aBoutServer.setOnClickListener(this);
        aBoutGroup.setOnClickListener(this);
        aBoutVersion.setOnClickListener(this);
        aBoutVersionUp.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.about_group:
                //加入群
                aBoutFragment.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.about_fragment,new AddGroupFragment(),"group").commit();
                break;
            case R.id.about_server:
                //在线服务
                aBoutFragment.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.about_fragment,new ServiceLineFragment(),"service").commit();
                break;
            case R.id.about_version:
                //版本号
                aBoutFragment.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.about_fragment,new VersionFragment(),"version").commit();
                break;
            case R.id.about_version_up:
                //版本升级
                aBoutFragment.setVisibility(View.VISIBLE);
                new VersionUpFragment().show(getSupportFragmentManager(), "up");
                break;
        }
    }
}
