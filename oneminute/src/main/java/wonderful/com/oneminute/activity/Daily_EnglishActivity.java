package wonderful.com.oneminute.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import wonderful.com.oneminute.R;
import wonderful.com.oneminute.entity.Daily_English;
import wonderful.com.oneminute.utils.Contans;
import wonderful.com.oneminute.utils.HttpUtils;
import wonderful.com.oneminute.utils.JsonUtils;
import wonderful.com.oneminute.view.WaitView;

public class Daily_EnglishActivity extends Activity {
    private ImageView img;
    private TextView tltle, summary;
    private String dateStr, url, jsonStr;
    private Daily_English de = null;
    private Handler handler = new Handler();
    private RelativeLayout english_content;
    private FrameLayout english_wait;
    private WaitView english_waiting;
    private ImageView english_wait_failed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailyenglish);
        initView();
        initDatas();
    }

    /**
     * 1: 调用getDateStr()方法,得到日期字符串;
     * 2: 把字符串拼接到 requestURL中;
     * 3: 异步网络请求json数据;
     * 4: json 解析;
     * 5: 解析出来后与view 绑定;
     */
    private void initDatas() {
        dateStr = getDateStr();
        url = Contans.getEnglishUrl(dateStr);
        new Thread() {
            @Override
            public void run() {
                try {
                    jsonStr = HttpUtils.loadStringFromURL(url);
                    de = JsonUtils.getDaily_English(jsonStr, dateStr);
                } catch (Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            waitFailed();
                        }
                    });
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (de != null) {
                            english_content.setVisibility(View.VISIBLE);
                            english_wait.setVisibility(View.GONE);
                            tltle.setText(de.title);
                            summary.setText(de.summary);
                            Picasso.with(Daily_EnglishActivity.this).load(de.image_mobile2).into(img);
                        } else {
                            waitFailed();
                        }
                    }
                });
            }
        }.start();
    }

    private void waitFailed() {
        Toast.makeText(getApplicationContext(), "网络连接错误,请检查网络设置", Toast.LENGTH_SHORT).show();
        english_waiting.setVisibility(View.INVISIBLE);
        english_wait_failed.setVisibility(View.VISIBLE);
    }

    /**
     * 初始化view
     */
    private void initView() {
        img = (ImageView) findViewById(R.id.english_img);
        tltle = (TextView) findViewById(R.id.english_title);
        summary = (TextView) findViewById(R.id.english_summary);
        english_content = (RelativeLayout) findViewById(R.id.english_content);
        english_wait = (FrameLayout) findViewById(R.id.english_wait);
        english_waiting = (WaitView) findViewById(R.id.english_waiting);
        english_wait_failed = (ImageView) findViewById(R.id.english_wait_failed);
    }

    /**
     * 得到当前的日期;
     *
     * @return 当前日期的字符串;
     */
    public String getDateStr() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(d);
        return dateNowStr;
    }
}
