package ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

public class OrderActionBar extends LinearLayout {

    private View back;
    private TextView title;
    private TextView edit;
    private Button service;

    public OrderActionBar(Context context) {
        super(context);
    }

    public OrderActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OrderActionBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void init() {
        View inflate = inflate(getContext(), R.layout.actionbar_order, this);
        View back=inflate.findViewById(R.id.order_back);
        TextView title=inflate.findViewById(R.id.order_title);
        service = inflate.findViewById(R.id.order_service);
    }


    public void setServiceShow(boolean flag){
        if (flag){
            service.setVisibility(VISIBLE);
        }else{
            service.setVisibility(GONE);
        }
    }

}
