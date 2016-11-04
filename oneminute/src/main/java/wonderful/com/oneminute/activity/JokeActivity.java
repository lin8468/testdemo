package wonderful.com.oneminute.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import wonderful.com.oneminute.R;
import wonderful.com.oneminute.adapter.JokeAdapter;
import wonderful.com.oneminute.entity.Joke;
import wonderful.com.oneminute.utils.Contans;
import wonderful.com.oneminute.utils.JsonUtils;
import wonderful.com.oneminute.view.WaitView;

public class JokeActivity extends Activity {

    private List<Joke> jokedata = new ArrayList<>();
    private Handler handler = new Handler();
    private JokeAdapter adapter;
    private FrameLayout joke_wait;
    private WaitView joke_waiting;
    private ImageView joke_wait_failed;
    private ListView joke_listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        //初始化View
        initView();
        //准备数据源
        initData();
    }

    private void initData() {
        new Thread() {
            @Override
            public void run() {
                try {
                    jokedata.addAll(JsonUtils.getJoke(Contans.getJokeUrl()));
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                            joke_wait.setVisibility(View.GONE);
                            joke_listView.setVisibility(View.VISIBLE);
                        }
                    });
                } catch (Exception e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            joke_waiting.setVisibility(View.INVISIBLE);
                            joke_wait_failed.setVisibility(View.VISIBLE);
                            Toast.makeText(getApplicationContext(), "网络连接错误,请检查网络设置", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }.start();

    }


    private void initView() {
        joke_listView = (ListView) findViewById(R.id.JokeListView);
        //设置适配器
        adapter = new JokeAdapter(jokedata, getBaseContext());
        joke_listView.setAdapter(adapter);
        joke_wait = (FrameLayout) findViewById(R.id.joke_wait);
        joke_waiting = (WaitView) findViewById(R.id.joke_waiting);
        joke_wait_failed = (ImageView) findViewById(R.id.joke_wait_failed);
    }

}
