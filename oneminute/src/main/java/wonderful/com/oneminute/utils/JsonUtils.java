package wonderful.com.oneminute.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import wonderful.com.oneminute.entity.Daily_English;
import wonderful.com.oneminute.entity.Joke;
import wonderful.com.oneminute.entity.Life;
import wonderful.com.oneminute.entity.One;
import wonderful.com.oneminute.entity.Weather;

import static com.alibaba.fastjson.JSON.parseObject;

/**
 * Json解析工具类
 */

public class JsonUtils {


    /**
     * 通过一个文章列表的api接口返回随机的文章对象
     *
     * @return 随机的文章对象
     */
    public static One getOne() {
        List<String> list = null;
        String oneListUrl = Contans.getOneListUrl();
        String json = HttpUtils.loadStringFromURL(oneListUrl);
        if (json != null) {
            list = new ArrayList<>();
            JSONObject jsonObject = parseObject(json);
            JSONArray data = jsonObject.getJSONArray("data");
            for (Object o : data) {
                list.add(o.toString());
            }
        }
        int size = list.size();
        Random random = new Random();
        int i = random.nextInt(size);
        String id = list.get(i);
        String oneDetailUrl = Contans.getOneDetailUrl(id);
        return getOneDetail(oneDetailUrl);
    }


    /**
     * 通过文章的api解析文章对象
     *
     * @param url
     * @return 文章对象
     */
    public static One getOneDetail(String url) {
        One one = null;
        String json = HttpUtils.loadStringFromURL(url);
        if (json != null) {
            JSONObject jsonObject = parseObject(json);
            JSONObject data = jsonObject.getJSONObject("data");
            one = parseObject(data.toJSONString(), One.class);
        }
        return one;
    }

    /**
     * 1: 通过json字符串 解析到 Daily_English(英语美文) 的具体数据;
     *
     * @param jsonStr http请求回来的json字符串;
     * @param dateStr 当前日期的字符串;
     * @return Daily_English的对象;
     */
    public static Daily_English getDaily_English(String jsonStr, String dateStr) {
        Daily_English de = null;
        if (jsonStr != null) {
            JSONObject jb1 = JSON.parseObject(jsonStr);
            JSONArray jsonArray = jb1.getJSONArray(dateStr);
            JSONObject jb3 = jsonArray.getJSONObject(0);
            de = parseObject(jb3.toJSONString(), Daily_English.class);
            de.image_mobile2 = jb3.getString("image-mobile2");
        }
        return de;
    }

    //通过天气接口将数据解析为天气对象集合
    public static List<Weather> getWeather(String url) {
        List<Weather> list = null;
        String json = HttpUtils.loadStringFromURL(url);
        if (json != null) {
            list = new ArrayList<>();
            JSONObject jsonObject = JSON.parseObject(json);
            JSONObject result = jsonObject.getJSONObject("result");
            JSONObject data = result.getJSONObject("data");
            JSONArray weather = data.getJSONArray("weather");
            for (Object o : weather) {
                JSONObject total = JSONObject.parseObject(o.toString());
                Weather weather_entity = new Weather();
                weather_entity.date = total.getString("date");
                weather_entity.week = total.getString("week");
                weather_entity.nongli = total.getString("nongli");
                JSONObject info = total.getJSONObject("info");
                JSONArray day = info.getJSONArray("day");
                JSONArray night = info.getJSONArray("night");
                weather_entity.day = new ArrayList<>();
                weather_entity.night = new ArrayList<>();
                weather_entity.day.add(day.getString(0));
                weather_entity.day.add(day.getString(1));
                weather_entity.day.add(day.getString(2));
                weather_entity.day.add(day.getString(4));
                weather_entity.day.add(day.getString(5));
                weather_entity.night.add(night.getString(0));
                weather_entity.night.add(night.getString(1));
                weather_entity.night.add(night.getString(2));
                weather_entity.night.add(night.getString(4));
                weather_entity.night.add(night.getString(5));
                list.add(weather_entity);
            }
        }
        return list;
    }

    //得到生活精选的集合
    public static Life getLife(String jsonStr) {
        JSONObject jb1 = JSONObject.parseObject(jsonStr);
        JSONObject jb2 = jb1.getJSONObject("data");
        JSONArray ja1 = jb2.getJSONArray("items");
        JSONObject jb3 = ja1.getJSONObject(0);
        Life life = parseObject(jb3.toString(), Life.class);
        return life;
    }


    //通过笑话接口解析笑话集合
    public static List<Joke> getJoke(String url) {
        List<Joke> list = null;
        String json = HttpUtils.loadStringFromURL(url);
        if (json != null) {
            list = new ArrayList<>();
            JSONObject jsonObject = JSON.parseObject(json);
            JSONArray result = jsonObject.getJSONArray("result");
            for (Object o : result) {
                String s = o.toString();
                Joke joke = JSONObject.parseObject(s, Joke.class);
                list.add(joke);
            }
        }
        return list;
    }
}
