package ui.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import base.MVPBaseFragment;
import contract.fragment.LanguageContract;
import presenter.fragment.LanguagePresenter;
import ui.widget.CustomCheckBox;
import utils.LanguageUtil;
import ui.activity.MainActivity;

public class LanguageFragment extends MVPBaseFragment<LanguageContract.View, LanguagePresenter> implements LanguageContract.View,CustomCheckBox.OnCustomCheckBoxClickListener, View.OnClickListener {

    private CustomCheckBox lAnguageChinese;
    private CustomCheckBox lAnguageTraditional;
    private CustomCheckBox lAnguageEnglish;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_language;
    }

    @Override
    protected void setUpView() {
        lAnguageChinese = getContentView().findViewById(R.id.language_chinese);
        lAnguageTraditional = getContentView().findViewById(R.id.language_traditional);
        lAnguageEnglish = getContentView().findViewById(R.id.language_english);
        TextView title = getContentView().findViewById(R.id.title);
        Button back = getContentView().findViewById(R.id.back);


        title.setText(R.string.language_title);
        //设置为默认
        lAnguageChinese.setChecked(true);
        lAnguageTraditional.setChecked(false);
        lAnguageEnglish.setChecked(false);

        lAnguageChinese.setOnCustomCheckBoxClickListener(this);
        lAnguageTraditional.setOnCustomCheckBoxClickListener(this);
        lAnguageEnglish.setOnCustomCheckBoxClickListener(this);
        back.setOnClickListener(this);

        //获取上次选择语言
        //获取语言设置
        if (LanguageUtil.isSetValue(getActivity())) {
            String string = LanguageUtil.getString(getContext());
            if (string.equals("SIMPLIFIED_CHINESE")) {
                //中文
                lAnguageChinese.setChecked(true);
                lAnguageTraditional.setChecked(false);
                lAnguageEnglish.setChecked(false);
            } else if (string.equals("ENGLISH")) {
                //英文
                lAnguageChinese.setChecked(false);
                lAnguageTraditional.setChecked(false);
                lAnguageEnglish.setChecked(true);
            } else if (string.equals("TRADITIONAL_CHINESE")) {
                //繁体
                lAnguageChinese.setChecked(false);
                lAnguageTraditional.setChecked(true);
                lAnguageEnglish.setChecked(false);
            } else {
                //设置为默认
                lAnguageChinese.setChecked(true);
                lAnguageTraditional.setChecked(false);
                lAnguageEnglish.setChecked(false);
            }
        }
    }

    @Override
    protected void setUpData() {

    }



    @Override
    public void onCustomCheckBoxClick(View v) {
        switch (v.getId()) {
            case R.id.language_chinese:
                lAnguageChinese.setChecked(true);
                lAnguageTraditional.setChecked(false);
                lAnguageEnglish.setChecked(false);
                //设置语言为简体中文
                LanguageUtil.setChinese(getMContext());
                reLoad();
                break;
            case R.id.language_traditional:
                lAnguageChinese.setChecked(false);
                lAnguageTraditional.setChecked(true);
                lAnguageEnglish.setChecked(false);
                //设置语言为繁体中文
                LanguageUtil.setTraditional(getMContext());
                reLoad();
                break;
            case R.id.language_english:
                lAnguageChinese.setChecked(false);
                lAnguageTraditional.setChecked(false);
                lAnguageEnglish.setChecked(true);
                //设置语言为英文
                LanguageUtil.setEnglish(getMContext());
                reLoad();
                break;
        }
    }

    private void reLoad() {
        Intent intent = new Intent(getMContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        // 杀掉进程
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                FragmentManager fragmentManager = getFragmentManager();
                Fragment version = fragmentManager.findFragmentByTag("language");
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(version);
                fragmentTransaction.commit();
                break;
        }
    }
}
