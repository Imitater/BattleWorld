package ui.activity;

import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import base.MVPBaseActivity;
import contract.activity.NameEditContract;
import presenter.activity.NameEditPresenter;
import ui.widget.MyActionBar;
import utils.StatusUtils;
import ui.widget.TextEditTextView;

public class NameEditActivity extends MVPBaseActivity<NameEditContract.View, NameEditPresenter> implements NameEditContract.View, View.OnClickListener{

    private TextView nEditName;
    private TextEditTextView nEditRename;
    private MyActionBar nEditActionBar;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_nameedit;
    }

    @Override
    protected void setUpView() {
        nEditActionBar = (MyActionBar) findViewById(R.id.nedit_actionbar);
        nEditName = (TextView) findViewById(R.id.nedit_name);
        ImageButton nEditClear = (ImageButton) findViewById(R.id.nedit_clear);
        nEditRename = (TextEditTextView) findViewById(R.id.nedit_rename);
        TextView aCtionEdit = (TextView) findViewById(R.id.action_edit);


        //设置actionbar title
        nEditActionBar.setTitle(getString(R.string.name_change));
        nEditRename.setOnKeyBoardHideListener(this);
        nEditRename.setOnEditorActionListener(this);
        nEditClear.setOnClickListener(this);
        aCtionEdit.setOnClickListener(this);

        //监听软键盘弹出
        nEditRename.setOnKeyBoardHideListener(new TextEditTextView.OnKeyBoardHideListener() {
            @Override
            public void onKeyHide(int keyCode, KeyEvent event) {
                StatusUtils.hideBottomUIMenu(NameEditActivity.this);
            }
        });
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nedit_clear:
                nEditActionBar.setEditShow(true);
                nEditActionBar.setEditTitle(getString(R.string.email_finish));
                nEditName.setVisibility(View.GONE);
                nEditRename.setVisibility(View.VISIBLE);
                break;
            case R.id.action_edit:
                if (!TextUtils.isEmpty(nEditRename.getText().toString().trim())){
                    nEditActionBar.setEditShow(false);
                    nEditName.setVisibility(View.VISIBLE);
                    nEditRename.setVisibility(View.GONE);
                    nEditName.setText(nEditRename.getText().toString().trim());
                    /*
                    * 存入网络....
                    * */
                    finish();
                }else{
                    Snackbar.make(nEditRename,"昵称不能为空",Snackbar.LENGTH_SHORT).show();
                }
                break;
        }
    }


}
