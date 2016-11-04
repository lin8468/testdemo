package wonderful.com.oneminute.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import wonderful.com.oneminute.MyApplication;
import wonderful.com.oneminute.R;
import wonderful.com.oneminute.entity.Weather;
import wonderful.com.oneminute.utils.Contans;
import wonderful.com.oneminute.utils.JsonUtils;
import wonderful.com.oneminute.view.WaitView;

public class WeatherActivity extends Activity {

    private TextView weather_city;
    private TextView weather_today;
    private TextView weather_tomorrow;
    private FrameLayout weather_wait;
    private WaitView weather_waiting;
    private ImageView weather_wait_failed;
    private LinearLayout weather_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        //初始化view
        initView();
        //如果没有定位到当前城市就提示检查定位权限
        if (MyApplication.locationCity != null) {
            initData();
        } else {
            Toast.makeText(getApplicationContext(), "没有定位到城市信息,请检查定位权限是否开启", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void initData() {
        weather_city.setText(MyApplication.locationCity);
        new Thread() {
            @Override
            public void run() {
                try {
                    final List<Weather> weather;
                    String weatherUrl = Contans.getWeatherUrl(MyApplication.locationCity);
                    weather = JsonUtils.getWeather(weatherUrl);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (weather != null) {
                                //将请求下载的数据显示在view当中
                                String day_temp = weather.get(0).day.get(2);
                                String night_temp = weather.get(0).night.get(2);
                                String temp = null;
                                if (Integer.parseInt(day_temp) > Integer.parseInt(night_temp)) {
                                    temp = night_temp + "~" + day_temp + "℃";
                                } else {
                                    temp = day_temp + "~" + night_temp + "℃";
                                }
                                String weather_weather = weather.get(0).day.get(1) + "转" + weather.get(0).night.get(1) + "　" + weather.get(0).day.get(3);
                                weather_today.setText(temp + "　" + weather_weather);
                                day_temp = weather.get(1).day.get(2);
                                night_temp = weather.get(1).night.get(2);
                                temp = null;
                                if (Integer.parseInt(day_temp) > Integer.parseInt(night_temp)) {
                                    temp = night_temp + "~" + day_temp + "℃";
                                } else {
                                    temp = day_temp + "~" + night_temp + "℃";
                                }
                                weather_weather = weather.get(1).day.get(1) + "转" + weather.get(1).night.get(1) + "　" + weather.get(1).day.get(3);
                                weather_tomorrow.setText(temp + "　" + weather_weather);
                                weather_wait.setVisibility(View.GONE);
                                weather_content.setVisibility(View.VISIBLE);
                            } else {
                                waitFailed();
                            }
                        }
                    });
                } catch (Exception e) {
                    waitFailed();
                }
            }
        }.start();
    }

    private void waitFailed() {
        Toast.makeText(getApplicationContext(), "网络连接错误,请检查网络设置", Toast.LENGTH_SHORT).show();
        weather_waiting.setVisibility(View.INVISIBLE);
        weather_wait_failed.setVisibility(View.VISIBLE);
    }

    private void initView() {
        weather_city = (TextView) findViewById(R.id.weather_city);
        weather_today = (TextView) findViewById(R.id.weather_today);
        weather_tomorrow = (TextView) findViewById(R.id.weathertomorrow);
        weather_wait = (FrameLayout) findViewById(R.id.weather_wait);
        weather_waiting = (WaitView) findViewById(R.id.weather_waiting);
        weather_wait_failed = (ImageView) findViewById(R.id.weather_wait_failed);
        weather_content = (LinearLayout) findViewById(R.id.weather_content);

    }
}
