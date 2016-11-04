package wonderful.com.oneminute.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import wonderful.com.oneminute.R;
import wonderful.com.oneminute.entity.One;
import wonderful.com.oneminute.utils.JsonUtils;
import wonderful.com.oneminute.view.WaitView;

public class OneActivity extends Activity {

    private One one;
    private ImageView one_imageView_title;
    private TextView one_textView_content;
    private Handler handler = new Handler();
    private LinearLayout one_content;
    private FrameLayout one_wait;
    private WaitView one_waiting;
    private ImageView one_wait_failed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        //初始化view
        initView();
        initData();
    }

    /**
     * 初始化数据 把网络请求到的数据显示在activity当中
     */
    private void initData() {
        new Thread() {
            @Override
            public void run() {
                try {
                    one = JsonUtils.getOne();
                    if (one != null) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                one_content.setVisibility(View.VISIBLE);
                                one_wait.setVisibility(View.GONE);
                                Picasso.with(getBaseContext()).load(one.hp_img_url).into(one_imageView_title);
                                one_textView_content.setText(one.hp_content);
                            }
                        });
                    } else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                waitFailed();
                            }
                        });
                    }
                } catch (Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            waitFailed();
                        }
                    });
                }
            }
        }.start();
    }

    private void waitFailed() {
        Toast.makeText(getApplicationContext(), "网络连接失败,请检查网络", Toast.LENGTH_SHORT).show();
        one_waiting.setVisibility(View.INVISIBLE);
        one_wait_failed.setVisibility(View.VISIBLE);
    }

    private void initView() {
        one_imageView_title = (ImageView) findViewById(R.id.one_imageView_title);
        one_textView_content = (TextView) findViewById(R.id.one_textView_content);
        one_content = (LinearLayout) findViewById(R.id.one_content);
        one_wait = (FrameLayout) findViewById(R.id.one_wait);
        one_waiting = (WaitView) findViewById(R.id.one_waiting);
        one_wait_failed = (ImageView) findViewById(R.id.one_wait_failed);
    }
}
