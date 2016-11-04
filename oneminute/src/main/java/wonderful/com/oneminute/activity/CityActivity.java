package wonderful.com.oneminute.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import wonderful.com.oneminute.MyApplication;
import wonderful.com.oneminute.R;

public class CityActivity extends Activity {

    private TextView textView_city_now;
    private TextView textView_city_city;
    private TextView textView_city_hello_1;
    private TextView textView_city_hello_2;
    private TextView textView_city_hello_3;
    private ImageButton imageButton_city_phone;
    private List<View> list = new ArrayList<>();
    private int currentViewId = 0;
    private ImageView imageView_city_background;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        //如果当前城市没有定位到就提示检查权限
        if (MyApplication.locationCity != null) {
            initView();
        } else {
            Toast.makeText(getApplicationContext(), "没有定位到城市信息,请检查定位权限是否开启", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void initView() {
        //初始化控件
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        imageView_city_background = (ImageView) findViewById(R.id.city_background);
        imageView_city_background.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.main_background, options));
        textView_city_now = (TextView) findViewById(R.id.city_now);
        textView_city_city = (TextView) findViewById(R.id.city_city);
        textView_city_city.setText(MyApplication.locationCity);
        textView_city_hello_1 = (TextView) findViewById(R.id.city_hello_1);
        textView_city_hello_2 = (TextView) findViewById(R.id.city_hello_2);
        textView_city_hello_3 = (TextView) findViewById(R.id.city_hello_3);
        imageButton_city_phone = (ImageButton) findViewById(R.id.city_phone);
        list.add(textView_city_now);
        list.add(textView_city_city);
        list.add(textView_city_hello_1);
        list.add(textView_city_hello_2);
        list.add(textView_city_hello_3);
        list.add(imageButton_city_phone);
        startAnimation();
    }

    //开始控件动画
    private void startAnimation() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    final Animation alpha_long = AnimationUtils.loadAnimation(getBaseContext(), R.anim.anim_city_hello);
                    final View currentView = list.get(currentViewId);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            currentView.startAnimation(alpha_long);
                        }
                    });
                    if (currentViewId < list.size() - 1) {
                        currentViewId++;
                    } else {
                        break;
                    }
                    try {
                        Thread.sleep(1500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }

    //点击拨打电话就跳转到联系人界面
    public void click(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(ContactsContract.Contacts.CONTENT_URI);
        startActivity(intent);
        finish();
    }
}
