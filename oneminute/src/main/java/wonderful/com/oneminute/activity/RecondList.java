package wonderful.com.oneminute.activity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

import wonderful.com.oneminute.R;
import wonderful.com.oneminute.adapter.RecondAdapter;
import wonderful.com.oneminute.utils.Contans;

/**
 * Created by Super-me on 2016/10/6.
 */

public class RecondList extends Activity {

    //定义一个数组放置录音文件
    ArrayList<File> datas = new ArrayList<File>();
    private File[] files;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recond_list);
        initData();
        initView();
    }

    private void initView() {
        //初始化ListView
        ListView listv = (ListView) findViewById(R.id.RecordListView);
        //设置监听
        listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = datas.get(position).toString();
                MediaPlayer mp = new MediaPlayer();
                try {
                    mp.setDataSource(s);
                    mp.prepare();
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //适配器准备
        RecondAdapter adapter = new RecondAdapter(datas,getBaseContext());
        //设置适配器
        listv.setAdapter(adapter);
    }

    private void initData() {
        File f = new File(Contans.getAppRecordDir());
        files = f.listFiles();
        //将数组中的元素转移到集合中，以便适配器的使用
        for (int i = 0; i < files.length; i++) {
            datas.add(files[i]);
        }

    }
}
