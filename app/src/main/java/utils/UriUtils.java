package utils;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class UriUtils {

         public static String getRealPathFromURI(Uri contentURI, Activity activity) {
            String result;
            Cursor cursor = null;
            try {
                cursor =activity.getContentResolver().query(contentURI, null, null, null, null);
            } catch (Throwable e) {
                e.printStackTrace();
            }
            if (cursor == null) {
                result = contentURI.getPath();
            } else {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                result = cursor.getString(idx);
                cursor.close();
            }
            return result;
        }
}
