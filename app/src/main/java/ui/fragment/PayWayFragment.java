package ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhonglian.battleworld.R;

import base.BasePresenter;
import base.MVPBaseFragment;
import contract.fragment.PayWayFContract;
import presenter.fragment.PayWayFPresenter;

public class PayWayFragment extends MVPBaseFragment<PayWayFContract.View, PayWayFPresenter> implements PayWayFContract.View, View.OnClickListener{

    private FrameLayout pAyFrameLayout;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_payway;
    }

    @Override
    protected void setUpView() {
        TextView title=getContentView().findViewById(R.id.title);
        Button back= getContentView().findViewById(R.id.back);
        TextView pAyCard=getContentView().findViewById(R.id.pay_card);
        ImageView pAyCardNext=getContentView().findViewById(R.id.pay_card_next);
        TextView pAyAlipay=getContentView().findViewById(R.id.pay_alipay);
        ImageView pAyAlipayNext=getContentView().findViewById(R.id.pay_alipay_next);
        TextView pAyWeiXing=getContentView().findViewById(R.id.pay_weixing);
        ImageView pAyWeiXingNext = getContentView().findViewById(R.id.pay_weixing_next);
        pAyFrameLayout = getContentView().findViewById(R.id.pay_framelayout);
        title.setText(R.string.payway_title);


        initListener(back, pAyCard, pAyCardNext, pAyAlipay, pAyAlipayNext, pAyWeiXing, pAyWeiXingNext);
    }

    private void initListener(Button back, TextView pAyCard, ImageView pAyCardNext, TextView pAyAlipay, ImageView pAyAlipayNext, TextView pAyWeiXing, ImageView pAyWeiXingNext) {
        back.setOnClickListener(this);
        pAyCard.setOnClickListener(this);
        pAyCardNext.setOnClickListener(this);
        pAyAlipay.setOnClickListener(this);
        pAyAlipayNext.setOnClickListener(this);
        pAyWeiXing.setOnClickListener(this);
        pAyWeiXingNext.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                FragmentManager fragmentManager = getFragmentManager();
                Fragment pay_add = fragmentManager.findFragmentByTag("pay_add");
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(pay_add);
                fragmentTransaction.commit();
                break;
            case R.id.pay_card:
                //银行卡跳转
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.pay_framelayout,new CardFragment(),"pay_card").commit();
                break;
            case R.id.pay_card_next:
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.pay_framelayout,new CardFragment(),"pay_card").commit();
                break;
            case R.id.pay_alipay:
                //支付宝跳转
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.pay_framelayout,new AliPayFragment(),"pay_alipay").commit();
                break;
            case R.id.pay_alipay_next:
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.pay_framelayout,new AliPayFragment(),"pay_alipay").commit();
                break;
            case R.id.pay_weixing:
                //添加微信
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.pay_framelayout,new WeiXingFragment(),"pay_weixing").commit();
                break;
            case R.id.pay_weixing_next:
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.pay_framelayout,new WeiXingFragment(),"pay_weixing").commit();
                break;
        }
    }

}
