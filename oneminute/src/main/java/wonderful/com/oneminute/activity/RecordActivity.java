package wonderful.com.oneminute.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Date;

import wonderful.com.oneminute.R;
import wonderful.com.oneminute.utils.Contans;
import wonderful.com.oneminute.view.WaitView;

public class RecordActivity extends Activity {
    private MediaRecorder mediaRecorder;
    private String path;
    private TextView record_say;
    private WaitView record_running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        initView();


    }

    private void initView() {
        mediaRecorder = new MediaRecorder();
        record_say = (TextView) findViewById(R.id.record_say);
        record_running = (WaitView) findViewById(R.id.record_running);
    }

    public void click(View v) {
        Intent it = new Intent(this, RecondList.class);
        finish();
        startActivity(it);
    }

    public void start(View view) {
        Button button = (Button) view;
        if (button.getText().equals("开始")) {
            //得到当前时间，用作录音命名
            Date date = new Date();
            //设置音频来源
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            //设置格式
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            //设置编码
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            //存储路径
            path = Contans.getAppRecordDir() + File.separator + date + ".mp3";
            //设置路径
            mediaRecorder.setOutputFile(path);
            try {
                //准备开始
                mediaRecorder.prepare();
                mediaRecorder.start();
                record_say.setVisibility(View.INVISIBLE);
                record_running.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "开始录音", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
            }
            button.setText("停止");
            button.setBackgroundResource(R.drawable.record_running);
        } else if (button.getText().equals("停止")) {
            mediaRecorder.stop();
            record_running.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(), "录音完成", Toast.LENGTH_SHORT).show();
            button.setText("开始");
            button.setBackgroundResource(R.drawable.record_stop);
        }
    }
}
