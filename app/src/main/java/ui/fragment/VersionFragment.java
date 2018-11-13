package ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import base.MVPBaseFragment;
import contract.fragment.VersionContract;
import presenter.fragment.VersionPresenter;
import ui.adapter.VersionRecyclerviewAdapter;

public class VersionFragment extends MVPBaseFragment<VersionContract.View, VersionPresenter> implements VersionContract.View,View.OnClickListener{
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_version;
    }

    @Override
    protected void setUpView() {
        Button back=getContentView().findViewById(R.id.back);
        TextView title=getContentView().findViewById(R.id.title);
        RecyclerView vErsionRecyclerview=getContentView().findViewById(R.id.version_recyclerview);

        title.setText(R.string.version_title);

        //设置Recyclerview 列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(getMContext());
        //设置布局管理器
        vErsionRecyclerview.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        vErsionRecyclerview.setAdapter(new VersionRecyclerviewAdapter(getMContext()));

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
                Fragment version = fragmentManager.findFragmentByTag("version");
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(version);
                fragmentTransaction.commit();
                break;
        }
    }
}
