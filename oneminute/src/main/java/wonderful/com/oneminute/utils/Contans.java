package wonderful.com.oneminute.utils;

import android.os.Environment;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 提供一些常量获取接口
 */

public class Contans {

    //得到笑话接口
    public static String getJokeUrl() {
        return "http://v.juhe.cn/joke/randJoke.php?key=1acafae5ba58b64b9aa041cdc0f2ead6&type=";
    }

    //得到one文章集合的接口
    public static String getOneListUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append("http://v3.wufazhuce.com:8000/api/hp/idlist/0?platform=android");
        return sb.toString();
    }

    //得到one文章详情的接口
    public static String getOneDetailUrl(String id) {
        StringBuilder sb = new StringBuilder();
        sb.append("http://v3.wufazhuce.com:8000/api/hp/detail/");
        sb.append(id);
        sb.append("?platform=android");
        return sb.toString();
    }

    //文字转为url编码
    public static String stringToUrlEncode(String s) {
        String urlEncode = null;
        try {
            urlEncode = URLEncoder.encode(s, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return urlEncode;
    }

    //得到当前城市天气接口
    public static String getWeatherUrl(String cityName) {
        StringBuilder sb = new StringBuilder();
        sb.append("http://op.juhe.cn/onebox/weather/query?cityname=");
        sb.append(stringToUrlEncode(cityName));
        sb.append("&key=f320c084315eeae91f13c4b4460b8311");
        return sb.toString();
    }

    //拼接 英语的每日一句 的URL;
    public static String getEnglishUrl(String datesStr) {
        StringBuilder sb = new StringBuilder();
        sb.append("http://dict.youdao.com/infoline?apiversion=2&mode=publish&client=deskdict&style=head&startDate=");
        sb.append(datesStr);
        sb.append("&limit=1");
        return sb.toString();
    }

    //拼接 创意生活的 url ;
    public static String getLifeUrl(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("http://api.liwushuo.com/v2/channels/125/items_v2?generation=2&gender=2&limit=1&offset=");
        sb.append(i);
        return sb.toString();
    }

    //得到录音文件夹
    public static String getAppRecordDir() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "OneMinute" + File.separator + "record";
    }

    //得到照片文件夹
    public static String getAppPhotoDir() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "OneMinute" + File.separator + "photo";
    }
}
