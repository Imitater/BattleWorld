package utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

public class LanguageUtil {
    public static void setChinese(Context context) {
        putString(context, "SIMPLIFIED_CHINESE");
    }

    public static void setEnglish(Context context){
        putString(context, "ENGLISH");
    }

    public static void setTraditional(Context context){
        putString(context, "TRADITIONAL_CHINESE");
    }

    private static void putString(Context context, String code) {
        SharedPreferences preference = context.getSharedPreferences("language", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preference.edit();
        edit.putString("language",code);
        edit.commit();
    }

    public static String getString(Context context){
        SharedPreferences preference = context.getSharedPreferences("language", Context.MODE_PRIVATE);
        String language = preference.getString("language", "");
        return language;
    }


    /**
     * 是否是设置值
     *
     * @return 是否是设置值
     */
    public static boolean isSetValue(Context context) {
        Locale currentLocale =context.getResources().getConfiguration().locale;
        return currentLocale.equals(getSetLocale(context));
    }


    public static Locale getSetLocale(Context context){
        SharedPreferences preference = context.getSharedPreferences("language", Context.MODE_PRIVATE);
        String language=preference.getString("language", "").trim();
        Locale locale =null;
        if (language.equals("TRADITIONAL_CHINESE")){
            locale = Locale.TRADITIONAL_CHINESE;
        }else if(language.equals("ENGLISH")){
            locale = Locale.ENGLISH;
        }else if(language.equals("CHINESE")){
            locale = Locale.SIMPLIFIED_CHINESE;
        }
        return locale;
    }


    //设置语言
    public static void resetDefaultLanguage(Context context){
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        // 应用用户选择语言
        config.locale = getSetLocale(context);
        resources.updateConfiguration(config, dm);
    }
}
