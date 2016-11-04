package wonderful.com.oneminute.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import wonderful.com.oneminute.R;
import wonderful.com.oneminute.adapter.MyPagerAdapter;

public class GuidActivity extends Activity {

    private List<View> list = new ArrayList<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guid);
        initView();
    }

    //初始化引导页,将准备好的图片放进引导页中
    private void initView() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 3;
        ViewPager viewPager = (ViewPager) findViewById(R.id.guid_viewPager);
        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.guid_1, options));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        list.add(imageView);
        imageView = new ImageView(this);
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.guid_2, options));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        list.add(imageView);
        View view = View.inflate(this, R.layout.activity_guid_3, null);
        ImageView guid_3 = (ImageView) view.findViewById(R.id.guid_3);
        ImageView guid_3_jump = (ImageView) view.findViewById(R.id.guid_3_jump);
        guid_3.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.guid_3, options));
        guid_3_jump.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.guid_3_jump));
        list.add(view);
        viewPager.setAdapter(new MyPagerAdapter(list));
    }

    //点击开始，跳转到主界面
    public void click(View view) {
        startActivity(new Intent(this, MainActivity.class));
        final SharedPreferences sp = getSharedPreferences("isGuid", Context.MODE_PRIVATE);
        final SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("isGuid", true);
        edit.commit();
        finish();
    }
}
