package wonderful.com.oneminute.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import wonderful.com.oneminute.R;

import static com.baidu.location.d.j.i;

/**
 * Created by Super-me on 2016/10/4.
 */

public class RecondAdapter extends BaseAdapter {

    ArrayList<File> datas;
    public Context context;

    public RecondAdapter(ArrayList<File> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_recond_contont, null);
            holder.holder_text = (TextView) convertView.findViewById(R.id.Recond_textView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.holder_text.setText(datas.get(position).toString());
        holder.holder_text.setTextSize(20);

        return convertView;
    }

    class ViewHolder {
        private TextView holder_text;
    }
}
