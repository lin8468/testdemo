package wonderful.com.oneminute.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wei41 on 2016/10/3.
 */

public class Weather implements Serializable {

    public String date;
    public List<String> day;
    public List<String> night;
    public String week;
    public String nongli;
}
