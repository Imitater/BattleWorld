package ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import base.BasePresenter;
import base.MVPBaseFragment;
import contract.fragment.CardContract;
import presenter.fragment.CardPresenter;
import ui.widget.BankCardTextWatcher;
import utils.StatusUtils;
import ui.widget.TextEditTextView;

public class CardFragment extends MVPBaseFragment<CardContract.View, CardPresenter> implements CardContract.View, View.OnClickListener{

    private TextView cardCardNumbertv;
    private TextEditTextView cArdCardNumber;
    private TextEditTextView cardBank;
    private TextEditTextView cardName;
    private TextView cardBankNameTv;
    private TextView cardUserNameTv;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_card;
    }

    @Override
    protected void setUpView() {
        Button cArdFinish=getContentView().findViewById(R.id.card_finish);
        TextView title = getContentView().findViewById(R.id.title);
        Button back = getContentView().findViewById(R.id.back);
        title.setText(R.string.card_title);

        cardBankNameTv = getContentView().findViewById(R.id.card_bank_name_tv);
        cardUserNameTv = getContentView().findViewById(R.id.card_user_name_tv);

        cArdCardNumber = getContentView().findViewById(R.id.card_card_number);
        cardCardNumbertv = getContentView().findViewById(R.id.card_card_number_tv);
        BankCardTextWatcher.bind(cArdCardNumber);
        cardName = getContentView().findViewById(R.id.card_name);
        cardBank = getContentView().findViewById(R.id.card_bank);
        TextEditTextView cardBankanother= getContentView().findViewById(R.id.card_pank_another);


        back.setOnClickListener(this);
        cArdFinish.setOnClickListener(this);
        //软键盘监听
        cardName.setOnKeyBoardHideListener(this);
        cArdCardNumber.setOnKeyBoardHideListener(this);
        cardBank.setOnKeyBoardHideListener(this);
        cardBankanother.setOnKeyBoardHideListener(this);

        cardName.setOnEditorActionListener(this);
        cArdCardNumber.setOnEditorActionListener(this);
        cardBank.setOnEditorActionListener(this);
        cardBankanother.setOnEditorActionListener(this);

        //设置实时输入
        setCardText();
    }

    private void setCardText() {
        //卡号
        cArdCardNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                cardCardNumbertv.setText(cArdCardNumber.getText());
            }
        });
        //银行
         cardBank.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {

             }

             @Override
             public void afterTextChanged(Editable s) {
                 cardBankNameTv.setText(cardBank.getText());
             }
         });
         //持卡人
        cardName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                cardUserNameTv.setText(cardName.getText());
            }
        });
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_finish:
                goBack();
                break;
            case R.id.back:
                goBack();
                break;
        }
    }
    //关闭页面
    private void goBack() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment pay_card = fragmentManager.findFragmentByTag("pay_card");
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(pay_card);
        fragmentTransaction.commit();
    }

}
