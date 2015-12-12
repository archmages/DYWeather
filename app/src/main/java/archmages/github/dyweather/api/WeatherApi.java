package archmages.github.dyweather.api;

import archmages.github.dyweather.bean.WeatherBean;
import archmages.github.dyweather.support.constants.Constant;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;
import rx.Observable;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:lmyu@wisorg.com">David.Yu</a>
 * @version V1.0, 12/12/15
 */
public interface WeatherApi {

    @Headers("apikey: " + Constant.BAIDU_API_KEY)
    @GET("/heweather/weather/free")
    Observable<WeatherBean> getWeatherByCityName(@Query("city") String cityName);

}
