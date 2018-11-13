package ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import java.lang.ref.WeakReference;

public class CustomCheckBox extends RelativeLayout implements View.OnClickListener {
    private float mSpacing;//文字与图标的间距值
    //	private int mImageRes;
    private int mTextSize;//文字字体大小
    private int mTextColor;//文字字体颜色
    private String mText;
    private boolean mChecked;

    private ImageView mCheckBox;
    //	private ImageView mImageView;
    private TextView mTextView;

    private WeakReference<OnCustomCheckBoxClickListener> mListener;

    public CustomCheckBox(Context context) {
        super(context);
    }

    public CustomCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomCheckBox);
        mChecked = a.getBoolean(R.styleable.CustomCheckBox_checked, false);
        mTextSize = a.getInt(R.styleable.CustomCheckBox_ctext_size, 14);
        mTextColor = a.getColor(R.styleable.CustomCheckBox_ctext_color, 0XFFFFFFFF);
        mText = a.getString(R.styleable.CustomCheckBox_ctext);
//		mImageRes = a.getResourceId(R.styleable.CustomCheckBox_src, -1);
        mSpacing = a.getDimension(R.styleable.CustomCheckBox_spacing, 22);
        a.recycle();

        LayoutInflater.from(context).inflate(R.layout.widget_my_checkbox, this, true);
        mCheckBox = (ImageView) this.findViewById(R.id.my_checkbox);
        mCheckBox.setSelected(mChecked);


        mTextView = (TextView) this.findViewById(R.id.my_checkbox_text);
        if (null != mText) {
            mTextView.setText(mText);
//			mTextView.setText("测试机");
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins((int) mSpacing, 0, 0, 0);
            mTextView.setLayoutParams(lp);
            mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize);
            mTextView.setTextColor(mTextColor);
            mTextView.setVisibility(View.VISIBLE);
        }


        View v = this.findViewById(R.id.my_checkbox_linearLayout);
        v.setOnClickListener(this);
    }

    public void setImageShow(boolean flag) {
        if (flag){
            mCheckBox.setVisibility(VISIBLE);
        }else{
            mCheckBox.setVisibility(INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        mCheckBox.setSelected(!mChecked);
        mChecked = !mChecked;
        if (null != mListener && null != mListener.get()) {
            mListener.get().onCustomCheckBoxClick(CustomCheckBox.this);
        }
    }

    public void setOnCustomCheckBoxClickListener(OnCustomCheckBoxClickListener listener) {
        mListener = new WeakReference<OnCustomCheckBoxClickListener>(listener);
    }

    public interface OnCustomCheckBoxClickListener {
        abstract void onCustomCheckBoxClick(View v);
    }

    public void setChecked(boolean isAllCheck) {
        mCheckBox.setSelected(isAllCheck);
        setImageShow(isAllCheck);
    }

    public boolean isChecked() {
        mChecked = mCheckBox.isSelected();
        return mChecked;
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String str){
        if (null != str) {
            mTextView.setText(str);
        }
    }
}
