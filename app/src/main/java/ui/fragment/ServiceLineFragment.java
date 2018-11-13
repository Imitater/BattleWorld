package ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import base.MVPBaseFragment;
import contract.fragment.ServiceLineContact;
import presenter.fragment.ServiceLinePresenter;

public class ServiceLineFragment extends MVPBaseFragment<ServiceLineContact.View, ServiceLinePresenter> implements ServiceLineContact.View,View.OnClickListener{
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_serviceline;
    }

    @Override
    protected void setUpView() {
        TextView title=getContentView().findViewById(R.id.title);
        Button back=getContentView().findViewById(R.id.back);

        title.setText(R.string.service_title);
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
                Fragment service = fragmentManager.findFragmentByTag("service");
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(service);
                fragmentTransaction.commit();
                break;
        }
    }
}
