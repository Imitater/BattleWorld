package ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import base.MyApplication;
import utils.StatusUtils;

/**
 *  拦截键盘向下的 EditTextView
 */
@SuppressLint("AppCompatCustomView")
public class TextEditTextView extends EditText {
    public TextEditTextView(Context context) {
        super(context);
    }

    public TextEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
//        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
//            super.onKeyPreIme(keyCode, event);
//            onKeyBoardHideListener.onKeyHide(keyCode, event);
//            return false;
//        }
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == 1) {
            super.onKeyPreIme(keyCode, event);
            onKeyBoardHideListener.onKeyHide(keyCode, event);
            return false;
        }
        return super.onKeyPreIme(keyCode, event);
    }



    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
    }

    /**
     *键盘监听接口
     */
    OnKeyBoardHideListener onKeyBoardHideListener;
    public void setOnKeyBoardHideListener(OnKeyBoardHideListener onKeyBoardHideListener) {
        this.onKeyBoardHideListener = onKeyBoardHideListener;
    }

    public interface OnKeyBoardHideListener{
        void onKeyHide(int keyCode, KeyEvent event);
    }
    public boolean isBack(boolean flag){
        return  flag;
    }
}
