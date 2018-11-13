package presenter.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import base.BasePresenterImpl;
import contract.activity.HeadDialogContract;
import utils.CameraUtils;
import utils.PermissionUtils;

public class HeadDialogPresenter extends BasePresenterImpl<HeadDialogContract.View> implements HeadDialogContract.Presenter {
    @Override
    public void openCamera(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            int hasWriteContactsPermission = activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (hasWriteContactsPermission!= PackageManager.PERMISSION_GRANTED) {
                PermissionUtils.verifyStoragePermissions(activity);
                return;
            }else{
                int checkCameraPermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
                if (checkCameraPermission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity, PermissionUtils.PERMISSION_REQUEST, PermissionUtils.INTENT_CAMERA_REQUEST_CODE);
                    return;
                } else {
                    CameraUtils cameraUtils = new CameraUtils(activity);
                    Intent intent = cameraUtils.openCamera();
                    // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA  
                    activity.startActivityForResult(intent, 2);
                }
            }
        } else {
            CameraUtils cameraUtils = new CameraUtils(activity);
            Intent intent = cameraUtils.openCamera();
            // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA  
            activity.startActivityForResult(intent, 2);
        }
    }
}
