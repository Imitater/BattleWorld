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

import base.MVPBaseFragment;
import contract.fragment.PurchaseFContract;
import presenter.fragment.PurchaseFPresenter;
import utils.StatusUtils;
import ui.widget.TextEditTextView;


public class PurchaseFragment extends MVPBaseFragment<PurchaseFContract.View, PurchaseFPresenter> implements PurchaseFContract.View, View.OnTouchListener{
    TextView purchasePrice;
    Spinner purchaseSpinner;
    TextEditTextView purchaseDealPrice;
    TextView purchaseType;
    TextEditTextView purchaseSellcount;
    TextEditTextView purchaseMoney;
    TextEditTextView purchaseSingleLimit;
    TextEditTextView purchaseDealInstructions;
    Button purchaseFinish;
    TextView purchaseMargin;
    TextEditTextView purchaseMoneyCount;

    public static PurchaseFragment newInstance(String type) {
        PurchaseFragment fragment = new PurchaseFragment();
        Bundle args = new Bundle();
        args.putString("TYPE", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_purchase;
    }

    @Override
    protected void setUpView() {
        purchasePrice = getContentView().findViewById(R.id.purchase_price);
        purchaseSpinner = getContentView().findViewById(R.id.purchase_spinner);
        purchaseDealPrice = getContentView().findViewById(R.id.purchase_deal_price);
        purchaseType = getContentView().findViewById(R.id.purchase_type);
        purchaseSellcount = getContentView().findViewById(R.id.purchase_sellcount);
        purchaseMoney = getContentView().findViewById(R.id.purchase_money);
        purchaseSingleLimit = getContentView().findViewById(R.id.purchase_single_limit);
        purchaseDealInstructions = getContentView().findViewById(R.id.purchase_deal_instructions);
        purchaseFinish = getContentView().findViewById(R.id.purchase_finish);
        purchaseMargin = getContentView().findViewById(R.id.purchase_margin);
        purchaseMoneyCount = getContentView().findViewById(R.id.purchase_money_count);


        //获取数据
        Bundle bundle = getArguments();
        String args = bundle.getString("TYPE");
        purchaseMargin.setText("交易保证金：100" + args);

        //设置spinner
        initSpinner();
        //edittext 键盘弹出监听
        initEtListener();

    }

    private void initEtListener() {
        purchaseDealPrice.setOnKeyBoardHideListener(this);
        purchaseSellcount.setOnKeyBoardHideListener(this);
        purchaseMoney.setOnKeyBoardHideListener(this);
        purchaseSingleLimit.setOnKeyBoardHideListener(this);
        purchaseDealInstructions.setOnKeyBoardHideListener(this);
        purchaseMoneyCount.setOnKeyBoardHideListener(this);

        purchaseDealPrice.setOnEditorActionListener(this);
        purchaseSellcount.setOnEditorActionListener(this);
        purchaseMoney.setOnEditorActionListener(this);
        purchaseSingleLimit.setOnEditorActionListener(this);
        purchaseDealInstructions.setOnEditorActionListener(this);
        purchaseMoneyCount.setOnEditorActionListener(this);
    }

    //spinner 设置
    private void initSpinner() {
        String[] curs = getResources().getStringArray(R.array.arr_price);
        //将写好的personal_spinner引用进来，此时改变的是选中后的情况，如果这里不想修改，可引用Android默认的布局，
        //比如android.R.layout.simple_spinner_item
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.personal_spinner, curs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        purchaseSpinner.setAdapter(adapter);
        purchaseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
    protected void setUpData() {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
