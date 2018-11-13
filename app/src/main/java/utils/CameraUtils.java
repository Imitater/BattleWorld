package utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.config.PictureSelectionConfig;
import com.luck.picture.lib.tools.AttrsUtils;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.yalantis.ucrop.UCrop;
import com.zhonglian.battleworld.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import ui.activity.UCropActivity;


public class CameraUtils {
    private static String path = Environment.getExternalStorageDirectory().getPath() + "/BattleWorld/MyHead/";// sd路径
    private final Context context;

    public CameraUtils(Context context) {
        this.context = context;
    }

    /**
     * 调用系统的裁剪功能
     */
    public void cropPhoto(Uri originalPathuri, PictureSelectionConfig config, Context context, Activity activity) {
        String originalPath = UriUtils.getRealPathFromURI(originalPathuri,activity);
        UCrop.Options options = new UCrop.Options();
        int toolbarColor = AttrsUtils.getTypeValueColor(context, com.luck.picture.lib.R.attr.picture_crop_toolbar_bg);
        int statusColor = AttrsUtils.getTypeValueColor(context, com.luck.picture.lib.R.attr.picture_crop_status_color);
        int titleColor = AttrsUtils.getTypeValueColor(context, com.luck.picture.lib.R.attr.picture_crop_title_color);
        options.setToolbarColor(context.getResources().getColor(R.color.ucrop_bar_grey));
        options.setStatusBarColor(context.getResources().getColor(R.color.ucrop_bar_grey));
        options.setToolbarWidgetColor(titleColor);
        options.setCircleDimmedLayer(config.circleDimmedLayer);
        options.setShowCropFrame(config.showCropFrame);
        options.setShowCropGrid(config.showCropGrid);
        options.setDragFrameEnabled(config.isDragFrame);
        options.setScaleEnabled(config.scaleEnabled);
        options.setRotateEnabled(config.rotateEnabled);
        options.setCompressionQuality(config.cropCompressQuality);
        options.setHideBottomControls(config.hideBottomControls);
        options.setFreeStyleCropEnabled(true);
        options.setShowCropFrame(true);
        options.setShowCropGrid(true);
         boolean isHttp = PictureMimeType.isHttp(originalPath);
        String imgType = PictureMimeType.getLastImgType(originalPath);
        Uri uri = isHttp ? Uri.parse(originalPath) : Uri.fromFile(new File(originalPath));
        UCrop.of(uri, Uri.fromFile(new File(PictureFileUtils.getDiskCacheDir(context),
                System.currentTimeMillis() + imgType)))
                .withAspectRatio(config.aspect_ratio_x, config.aspect_ratio_y)
                .withMaxResultSize(config.cropWidth, config.cropHeight)
                .withOptions(options)
                .start(activity);

    }

    //保存图片到本地
    public void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "/head.jpg";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static File tempFile;

    //打开相机
    public Intent openCamera() {
        int currentapiVersion = Build.VERSION.SDK_INT;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (hasSdcard()) {
            tempFile = new File(path, "head.jpg");
            if (currentapiVersion < 24) {
                // 从文件中创建uri  
                Uri uri = Uri.fromFile(tempFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            } else {
                //兼容android7.0 使用共享文件的形式  
                File file = new File(path + "/head.jpg");
                if (!file.getParentFile().exists())
                    file.getParentFile().mkdirs();
                Uri imageUri = FileProvider.getUriForFile(context, "com.zhonglian.battleworld.fileprovider", file);//通过FileProvider创建一个content类型的Uri
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
            }
        }
        return intent;
    }


    public static boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

}
