package wonderful.com.oneminute.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import wonderful.com.oneminute.R;

public class SuggestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);
    }

    //点击提交按钮回调方法
    public void suggestCommit(View view) {
        EditText mEditText = (EditText) findViewById(R.id.suggest_editText);
        String suggest = mEditText.getText().toString();
        if (suggest.length() > 0){
            Toast.makeText(getApplicationContext(), "您的意见我们已收到", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(getApplicationContext(), "您必须要提意见呦！", Toast.LENGTH_SHORT).show();
        }
    }


}
