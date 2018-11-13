package base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import java.lang.reflect.ParameterizedType;

import ui.widget.TextEditTextView;
import utils.StatusUtils;

public abstract class MVPBaseDialogActivity <V extends BaseView,T extends BasePresenterImpl<V>> extends FragmentActivity
        implements BaseView,EditText.OnEditorActionListener,TextEditTextView.OnKeyBoardHideListener {
    public T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);//用于初始化view之前做一些事情
        setContentView(setLayoutResourceID());
        mPresenter = getInstance(this, 1);
        mPresenter.attachView((V) this);
        mPresenter.onCreate();
        setUpView();
        setUpData();


    }



    @Override
    protected void onStart() {
        super.onStart();
        StatusUtils.hideBottomUIMenu(this);
    }


    protected abstract int setLayoutResourceID();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.onStop();
        }
        ;
    }

    protected void init(Bundle savedInstanceState) {
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            StatusUtils.hideBottomUIMenu(this);
        }
    }

    //绑定view
    protected abstract void setUpView();

    //绑定数据
    protected abstract void setUpData();

    @Override
    public Context getContext() {
        return this;
    }

    public <T> T getInstance(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            StatusUtils.hideBottomUIMenu(this);
            //隐藏软键盘
            InputMethodManager imm = (InputMethodManager) v
                    .getContext().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(
                        v.getApplicationWindowToken(), 0);
            }
            return true;
        }

        return false;
    }

    @Override
    public void onKeyHide(int keyCode, KeyEvent event) {
        StatusUtils.hideBottomUIMenu(this);
    }
}
