package wonderful.com.oneminute.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * 基于HttpURLConnection的联网工具类
 *
 * @author Fu MengWei
 */
public class HttpUtils {


    /**
     * 判断网络是否连接
     *
     * @param context 上下文
     * @return 网络连接成功返回true 失败返回false
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            return true;
        }
        return false;
    }

    /**
     * 实现网络访问，将获取到的数据储存在文件流中
     *
     * @param urlString 访问网络的URL地址
     * @return 访问成功返回InputStream 失败返回null
     */
    public static InputStream loadFileFromURL(String urlString) {
        HttpURLConnection conn = null;
        BufferedInputStream bis = null;
        try {
            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.connect();
            if (conn.getResponseCode() == 200) {
                bis = new BufferedInputStream(conn.getInputStream());
                return bis;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    /**
     * 实现网络访问，将获取到的数据储存在文件流中
     *
     * @param urlString 访问网络的URL地址
     * @return 访问成功返回byte[] 失败返回null
     */
    public static byte[] loadByteFromURL(String urlString) {
        InputStream is = loadFileFromURL(urlString);
        return streamToByte(is);
    }

    /**
     * 实现网络访问，将获取到的数据储存在字符串中
     *
     * @param urlString 访问网络的URL地址
     * @return 访问成功返回String 失败返回null
     */
    public static String loadStringFromURL(String urlString) {
        InputStream is = loadFileFromURL(urlString);
        byte[] data = streamToByte(is);
        if (data != null) {
            try {
                return new String(data, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 将输入流流转化为数组
     *
     * @param is 输入流
     * @return byte 数组
     */
    public static byte[] streamToByte(InputStream is) {
        ByteArrayOutputStream baos = null;
        if (is != null) {
            baos = new ByteArrayOutputStream();
            int len = 0;
            byte[] arr = new byte[1024];
            try {
                while ((len = is.read(arr)) != -1) {
                    baos.write(arr, 0, len);
                    baos.flush();
                }
                return baos.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (baos != null) {
                    try {
                        baos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                try {
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    /**
     * 实现网络访问，将获取到的数据储存在指定目录中
     *
     * @param url 访问网络的URL地址
     * @param filePath 保存文件的路径
     * @return 保存成功返回true 失败返回false
     */
    public static boolean saveFileFromURL(String url, String filePath) {
        HttpURLConnection conn = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        if (!new File(filePath).isFile()) {
            try {
                conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setConnectTimeout(5000);
                conn.connect();
                if (conn.getResponseCode() == 200) {
                    bis = new BufferedInputStream(conn.getInputStream());
                    bos = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                    int len = 0;
                    byte[] arr = new byte[1024];
                    while ((len = bis.read(arr)) != -1) {
                        bos.write(arr, 0, len);
                        bos.flush();
                    }
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bos != null) {
                        bos.close();
                    }
                    if (bis != null) {
                        bis.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
