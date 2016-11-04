package wonderful.com.oneminute;

import android.app.Application;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.umeng.socialize.PlatformConfig;

import org.lasque.tusdk.core.TuSdk;

import java.io.File;

import wonderful.com.oneminute.utils.Contans;


public class MyApplication extends Application {

    public static String locationCity = null;

    @Override
    public void onCreate() {
        super.onCreate();
        TuSdk.init(this.getApplicationContext(), "a9079562a8a8d142-00-s4g0q1");
        //初始化新浪微博分享、QQ空间分享
        PlatformConfig.setSinaWeibo("435804108", "8a638502ee4045fd5edbe31c9a59b054");
        PlatformConfig.setQQZone("1105735406", "S6IixSdDEjmRjUz7");
        startLocation();
        initDir();
    }

    private void initDir() {
        File recordDir = new File(Contans.getAppRecordDir());
        File photoDir = new File(Contans.getAppPhotoDir());
        if (!recordDir.exists()) {
            recordDir.mkdirs();
        }
        if (!photoDir.exists()) {
            photoDir.mkdirs();
        }
    }

    /**
     * 初始化定位服务
     */
    private void startLocation() {
        final LocationClient client = new LocationClient(getApplicationContext());
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd0911");
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setScanSpan(1000);
        option.setLocationNotify(true);
        client.setLocOption(option);
        client.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                locationCity = bdLocation.getCity();
                client.stop();
            }
        });
        client.start();
    }
}
