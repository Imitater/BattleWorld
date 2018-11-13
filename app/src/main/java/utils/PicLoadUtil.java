package utils;

 import android.app.Activity;
 import android.support.v4.app.Fragment;


 import com.luck.picture.lib.PictureSelector;
 import com.luck.picture.lib.config.PictureConfig;
 import com.luck.picture.lib.config.PictureMimeType;
 import com.luck.picture.lib.entity.LocalMedia;
 import com.zhonglian.battleworld.R;

 import java.util.List;

public class PicLoadUtil {
    public static void LoadPic(Fragment fragment, List<LocalMedia> selectList){
        PictureSelector.create(fragment)
                .openGallery(PictureMimeType.ofImage())
                .theme(R.style.PicWirteStyle)
                .enableCrop(true)
                .hideBottomControls(true)
                .maxSelectNum(1)
                .isCamera(true)
                .setOutputCameraPath("/CustomPath")
                .imageFormat(PictureMimeType.PNG)
                .hideBottomControls(false)
                .selectionMode(PictureConfig.SINGLE)
                .isGif(true)
                .previewImage(true)
                .rotateEnabled(true)
                .freeStyleCropEnabled(true)
                .showCropFrame(true)
                .showCropGrid(true)
                .rotateEnabled(true)
                .previewEggs(true)
                .selectionMedia(selectList)
                .withAspectRatio(16,9)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }
    public static void LoadMorePic(Activity activity, List<LocalMedia> selectList){
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())
                .theme(R.style.PicWirteStyle)
                .hideBottomControls(true)
                .maxSelectNum(3)
                .isCamera(true)
                .setOutputCameraPath("/CustomPath")
                .imageFormat(PictureMimeType.PNG)
                .hideBottomControls(false)
                .selectionMode(PictureConfig.MULTIPLE)
                .isGif(true)
                .enableCrop(false)
                .previewImage(true)
                .rotateEnabled(true)
                .previewEggs(true)
                .selectionMedia(selectList)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }
}
