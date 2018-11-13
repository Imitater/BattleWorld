package contract.activity;


import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;

import base.BasePresenter;
import base.BaseView;
import model.bean.ProvinceBean;

public class AddAddressContract {
    public interface View extends BaseView {
        
    }

    public interface  Presenter extends BasePresenter<View> {
        void showCity(Context context,final ArrayList<ProvinceBean> options1Items,final ArrayList<ArrayList<String>> options2Items,final ArrayList<ArrayList<ArrayList<String>>> options3Items,final TextView view);
    }
}
