package ui.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhonglian.battleworld.R;

import base.BasePresenter;
import base.MVPBaseActivity;
import contract.activity.PersonalityContract;
import presenter.activity.PersonalityPresenter;
import ui.widget.MyActionBar;
import utils.StatusUtils;
import ui.widget.TextEditTextView;

public class PersonalityActivity extends MVPBaseActivity<PersonalityContract.View, PersonalityPresenter> implements PersonalityContract.View,View.OnClickListener{
    private static final int MAX_COUNT = 30;
    private TextView pErsonalityCount;
    private TextEditTextView pErsonalityEdit;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_personality;
    }

    @Override
    protected void setUpView() {
        MyActionBar actionbar = (MyActionBar) findViewById(R.id.personality_actionbar);
        pErsonalityEdit = (TextEditTextView) findViewById(R.id.personality_edit);
        pErsonalityCount = (TextView) findViewById(R.id.personality_count);

        actionbar.setTitle(getString(R.string.persionality_change));
        actionbar.setEditShow(true);
        actionbar.setEditTitle(getString(R.string.email_finish));
        TextView aCtionEdit = (TextView) findViewById(R.id.action_edit);

        aCtionEdit.setOnClickListener(this);
        pErsonalityEdit.setOnKeyBoardHideListener(this);
        pErsonalityEdit.setOnEditorActionListener(this);
        pErsonalityEdit.addTextChangedListener(mTextWatcher);
        pErsonalityEdit.setSelection(pErsonalityEdit.length()); // 将光标移动最后一个字符后面
        //软键盘弹出监听
        pErsonalityEdit.setOnKeyBoardHideListener(new TextEditTextView.OnKeyBoardHideListener() {
            @Override
            public void onKeyHide(int keyCode, KeyEvent event) {
                StatusUtils.hideBottomUIMenu(PersonalityActivity.this);
            }
        });
        setLeftCount();
    }

    @Override
    protected void setUpData() {

    }

    private TextWatcher mTextWatcher = new TextWatcher() {

        private int editStart;

        private int editEnd;

        public void afterTextChanged(Editable s) {
            editStart = pErsonalityEdit.getSelectionStart();
            editEnd = pErsonalityEdit.getSelectionEnd();

            // 先去掉监听器，否则会出现栈溢出
            pErsonalityEdit.removeTextChangedListener(mTextWatcher);

            // 注意这里只能每次都对整个EditText的内容求长度，不能对删除的单个字符求长度
            // 因为是中英文混合，单个字符而言，calculateLength函数都会返回1
            while (calculateLength(s.toString()) > MAX_COUNT) { // 当输入字符个数超过限制的大小时，进行截断操作
                s.delete(editStart - 1, editEnd);
                editStart--;
                editEnd--;
            }
            // mEditText.setText(s);将这行代码注释掉就不会出现后面所说的输入法在数字界面自动跳转回主界面的问题了，多谢@ainiyidiandian的提醒
            pErsonalityEdit.setSelection(editStart);

            // 恢复监听器
            pErsonalityEdit.addTextChangedListener(mTextWatcher);

            setLeftCount();
        }

        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {

        }

    };


    private long calculateLength(CharSequence c) {
        double len = 0;
        for (int i = 0; i < c.length(); i++) {
            int tmp = (int) c.charAt(i);
            if (tmp > 0 && tmp < 127) {
                len += 0.5;
            } else {
                len++;
            }
        }
        return Math.round(len);
    }


    private void setLeftCount() {
        pErsonalityCount.setText(String.valueOf((MAX_COUNT - getInputCount()))+"/"+MAX_COUNT);
    }


    private long getInputCount() {
        return calculateLength(pErsonalityEdit.getText().toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_edit:
            /*
            * 保存网络
            * */
                Toast.makeText(this, "完成", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

}
