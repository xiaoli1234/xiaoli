package login.example.com.myapplication6;

import android.content.Context;

import com.gisinfo.android.core.base.AppData;
import com.gisinfo.android.lib.base.BaseApplication;

/**
 * @author Asen
 * @version v2.0
 * @email houlian@gisinfo.com
 * @date 2017/10/13 11:03
 */
public class MyApplication extends BaseApplication {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        AppData.APP_PROJECT = "gisinfo_shp";
        context=getApplicationContext();
    }
    public static Context getContext() {
        return context;
    }
}
