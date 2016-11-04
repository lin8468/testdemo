package wonderful.com.oneminute.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import wonderful.com.oneminute.R;
import wonderful.com.oneminute.entity.Joke;

/**
 * Created by Super-me on 2016/10/3.
 */

public class JokeAdapter extends BaseAdapter {
    private List<Joke> jokedata;
    private Context context;

    public JokeAdapter(List<Joke> jokedata, Context context) {
        this.jokedata = jokedata;
        this.context = context;
    }

    @Override
    public int getCount() {
        return jokedata.size();
    }

    @Override
    public Object getItem(int position) {
        return jokedata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_joke_contont, null);
            holder.joke_text = (TextView) convertView.findViewById(R.id.JokeText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //设置数据
        holder.joke_text.setText(jokedata.get(position).content);
        return convertView;
    }

    class ViewHolder {

        private TextView joke_text;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}
