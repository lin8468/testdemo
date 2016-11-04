package wonderful.com.oneminute.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wei41 on 2016/10/4.
 */

public class MyPagerAdapter extends PagerAdapter {

    private List<View> list;

    public MyPagerAdapter(List<View> list) {
        this.list = list;
    }

    @Override

    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }
}
