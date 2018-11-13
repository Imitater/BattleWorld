package base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.ParameterizedType;

import ui.widget.TextEditTextView;
import utils.StatusUtils;


public abstract class MVPBaseFragment<V extends BaseView,T extends BasePresenterImpl<V>> extends Fragment implements BaseView,View.OnTouchListener,
        EditText.OnEditorActionListener,TextEditTextView.OnKeyBoardHideListener{
    public T mPresenter;
    private View mContentView;
    private Context mContext;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter= getInstance(this,1);
        mPresenter.attachView((V) this);
        mPresenter.onCreate();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(setLayoutResourceID(), container, false);
        mContentView.setOnTouchListener(this);
        mContext = getContext();
        init();
        setUpView();
        setUpData();
        return mContentView;
    }

    /**
     * 此方法用于返回Fragment设置ContentView的布局文件资源ID
     *
     * @return 布局文件资源ID
     */
    protected abstract int setLayoutResourceID();

    /**
     * 一些View的相关操作
     */
    protected abstract void setUpView();

    /**
     * 一些Data的相关操作
     */
    protected abstract void setUpData();

    /**
     * 此方法用于初始化成员变量及获取Intent传递过来的数据
     * 注意：这个方法中不能调用所有的View，因为View还没有被初始化，要使用View在initView方法中调用
     */
    protected void init() {
    }

    public View getContentView() {
        return mContentView;
    }

    public Context getMContext() {
        return mContext;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null) {
            mPresenter.detachView();
            mPresenter.onStop();
        }
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }

    public  <T> T getInstance(Object o, int i) {
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
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            }
            return null;
        }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_DONE){
            StatusUtils.hideBottomUIMenu(getActivity());
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
        StatusUtils.hideBottomUIMenu(getActivity());
    }
}
