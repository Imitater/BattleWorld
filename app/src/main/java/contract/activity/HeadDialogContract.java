package contract.activity;

import android.app.Activity;

import base.BasePresenter;
import base.BaseView;

public class HeadDialogContract {
    public interface View extends BaseView {

    }

    public interface  Presenter extends BasePresenter<HeadDialogContract.View> {
        void openCamera(Activity activity);
    }
}
