package contract.fragment;


import base.BasePresenter;
import base.BaseView;

public class MyContract {
    public interface View extends BaseView {
        
    }

    public interface  Presenter extends BasePresenter<View> {
        
    }
}
