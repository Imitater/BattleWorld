package ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.zhonglian.battleworld.R;
import org.json.JSONArray;
import java.util.ArrayList;

import base.MVPBaseFragment;
import contract.activity.AddAddressContract;
import presenter.activity.AddAddressPresenter;
import model.bean.ProvinceBean;
import ui.widget.TextEditTextView;
import utils.JsonFileReader;
import utils.StatusUtils;

public class AddAddressFragment extends MVPBaseFragment<AddAddressContract.View, AddAddressPresenter> implements AddAddressContract.View,View.OnClickListener{
    private TextView aDdCity;
    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private TextEditTextView aDdName;
    private TextEditTextView aDdNumber;
    private TextEditTextView aDdAddress;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_addaddress;
    }

    @Override
    protected void setUpView() {
        aDdName = getContentView().findViewById(R.id.add_name);
        aDdNumber = getContentView().findViewById(R.id.add_number);
        aDdCity = getContentView().findViewById(R.id.add_city);
        aDdAddress = getContentView().findViewById(R.id.add_address);
        Button bAck = getContentView().findViewById(R.id.back);
        TextView tItle = getContentView().findViewById(R.id.title);
        Button aDdFinish = (Button) getContentView().findViewById(R.id.add_finish);

        tItle.setText(R.string.addaddress_title);


        bAck.setOnClickListener(this);
        aDdFinish.setOnClickListener(this);
        aDdCity.setOnClickListener(this);
        aDdName.setOnKeyBoardHideListener(this);
        aDdNumber.setOnKeyBoardHideListener(this);
        aDdAddress.setOnKeyBoardHideListener(this);

        aDdName.setOnEditorActionListener(this);
        aDdNumber.setOnEditorActionListener(this);
        aDdAddress.setOnEditorActionListener(this);
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                closeFragment();
                break;
            case R.id.add_finish:
                /*
                 * 网络保存
                 * */
                closeFragment();
                break;
            case R.id.add_city:
                //显示省市三级联动
                parseJson();
                mPresenter.showCity(getMContext(),options1Items,options2Items,options3Items,aDdCity);
                break;
        }
    }
    //三级联动数据解析

    public void parseJson() {
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        //  获取json数据
        String JsonData = JsonFileReader.getJson(getMContext(), "province_data.json");
        ArrayList<ProvinceBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
    }

    public ArrayList<ProvinceBean> parseData(String result) {//Gson 解析
        ArrayList<ProvinceBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                ProvinceBean entity = gson.fromJson(data.optJSONObject(i).toString(), ProvinceBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }


    //关闭当前页面
    public void closeFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment add = fragmentManager.findFragmentByTag("add");
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(add);
        fragmentTransaction.commit();
    }

}
