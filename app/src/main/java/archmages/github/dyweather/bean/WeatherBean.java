package archmages.github.dyweather.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:lmyu@wisorg.com">David.Yu</a>
 * @version V1.0, 12/12/15
 */
public class WeatherBean {

    @SerializedName("HeWeather data service 3.0")
    @Expose
    private List<archmages.github.dyweather.bean.HeWeatherDataService30> HeWeatherDataService30 = new ArrayList<archmages.github.dyweather.bean.HeWeatherDataService30>();

    /**
     *
     * @return
     * The HeWeatherDataService30
     */
    public List<archmages.github.dyweather.bean.HeWeatherDataService30> getHeWeatherDataService30() {
        return HeWeatherDataService30;
    }

    /**
     *
     * @param HeWeatherDataService30
     * The HeWeather data service 3.0
     */
    public void setHeWeatherDataService30(List<archmages.github.dyweather.bean.HeWeatherDataService30> HeWeatherDataService30) {
        this.HeWeatherDataService30 = HeWeatherDataService30;
    }

}
