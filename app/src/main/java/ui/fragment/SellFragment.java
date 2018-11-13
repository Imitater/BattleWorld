package ui.fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.zhonglian.battleworld.R;


import base.BasePresenter;
import base.MVPBaseFragment;
import contract.fragment.SellContract;
import presenter.fragment.SellPresenter;
import utils.StatusUtils;
import ui.widget.TextEditTextView;

public class SellFragment extends MVPBaseFragment<SellContract.View, SellPresenter> implements SellContract.View,View.OnTouchListener, TextEditTextView.OnKeyBoardHideListener {
    TextView sellPrice;
    Spinner sellSpinner;
    TextEditTextView sellDealPrice;
    TextView sellType;
    TextEditTextView sellSellcount;
    TextEditTextView sellMoney;
    TextEditTextView sellSingleLimit;
    TextEditTextView sellDealInstructions;
    Button sellFinish;
    TextView sellMargin;
    TextEditTextView sellMoneyCount;
    public static SellFragment newInstance(String type) {
        SellFragment fragment = new SellFragment();
        Bundle args = new Bundle();
        args.putString("TYPE", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_sell;
    }

    @Override
    protected void setUpView() {
        sellPrice=getContentView().findViewById(R.id.sell_price);
        sellSpinner=getContentView().findViewById(R.id.sell_spinner);
        sellDealPrice=getContentView().findViewById(R.id.sell_deal_price);
        sellType=getContentView().findViewById(R.id.sell_type);
        sellSellcount=getContentView().findViewById(R.id.sell_sellcount);
        sellMoney=getContentView().findViewById(R.id.sell_money);
        sellSingleLimit=getContentView().findViewById(R.id.sell_single_limit);
        sellDealInstructions=getContentView().findViewById(R.id.sell_deal_instructions);
        sellFinish=getContentView().findViewById(R.id.sell_finish);
        sellMargin=getContentView().findViewById(R.id.sell_margin);
        sellMoneyCount=getContentView().findViewById(R.id.sell_money_count);

        //软件盘弹出监听
        initEtListener();
        //spinner设置
        initSpinner();

        //获取数据
        Bundle bundle = getArguments();
        String args = bundle.getString("TYPE");
        sellMargin.setText("交易保证金：100" + args);
    }

    private void initEtListener() {
        sellDealPrice.setOnKeyBoardHideListener(this);
        sellSellcount.setOnKeyBoardHideListener(this);
        sellMoney.setOnKeyBoardHideListener(this);
        sellSingleLimit.setOnKeyBoardHideListener(this);
        sellDealInstructions.setOnKeyBoardHideListener(this);
        sellMoneyCount.setOnKeyBoardHideListener(this);


        sellDealPrice.setOnEditorActionListener(this);
        sellSellcount.setOnEditorActionListener(this);
        sellMoney.setOnEditorActionListener(this);
        sellSingleLimit.setOnEditorActionListener(this);
        sellDealInstructions.setOnEditorActionListener(this);
        sellMoneyCount.setOnEditorActionListener(this);
    }

    @Override
    protected void setUpData() {

    }

    //spinner 设置
    private void initSpinner() {
        String[] curs = getResources().getStringArray(R.array.arr_price);
        //将写好的personal_spinner引用进来，此时改变的是选中后的情况，如果这里不想修改，可引用Android默认的布局，
        //比如android.R.layout.simple_spinner_item
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.personal_spinner, curs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sellSpinner.setAdapter(adapter);
        sellSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                position++;
                //点击处理事件
                StatusUtils.hideBottomUIMenu(getActivity());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                StatusUtils.hideBottomUIMenu(getActivity());
            }
        });
    }

    @Override
    public void onKeyHide(int keyCode, KeyEvent event) {
        StatusUtils.hideBottomUIMenu(getActivity());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }



}
