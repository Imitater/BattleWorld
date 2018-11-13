package ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import base.MVPBaseFragment;
import contract.fragment.AddGroupContract;
import presenter.fragment.AddGroupPresenter;

public class AddGroupFragment extends MVPBaseFragment<AddGroupContract.View, AddGroupPresenter> implements AddGroupContract.View,View.OnClickListener {
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_addgroup;
    }

    @Override
    protected void setUpView() {
        Button back=getContentView().findViewById(R.id.back);
        TextView title=getContentView().findViewById(R.id.title);

        title.setText(R.string.addgroup_title);
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
                Fragment group = fragmentManager.findFragmentByTag("group");
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(group);
                fragmentTransaction.commit();
                break;
        }
    }
}
