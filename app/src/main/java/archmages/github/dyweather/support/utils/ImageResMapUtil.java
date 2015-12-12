package archmages.github.dyweather.support.utils;

import org.apache.commons.lang3.StringUtils;

import archmages.github.dyweather.R;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:lmyu@wisorg.com">David.Yu</a>
 * @version V1.0, 12/12/15
 */
public class ImageResMapUtil {

    public static int mapCondImageRes(String txt) {
        if (StringUtils.contains(txt, "晴")){
            return R.mipmap.sunny;
        }else if (StringUtils.contains(txt, "多云")){
            return R.mipmap.cloudy;
        }else if (StringUtils.contains(txt, "阴")){
            return R.mipmap.cloudy_;
        }else if (StringUtils.contains(txt, "雪")){
            return R.mipmap.snow;
        }else if (StringUtils.contains(txt, "雷")){
            return R.mipmap.thunderstorm;
        }
        return R.mipmap.sunny;
    }
}
