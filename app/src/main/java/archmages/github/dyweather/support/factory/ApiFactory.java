package archmages.github.dyweather.support.factory;

import retrofit.CallAdapter;
import retrofit.Converter;
import retrofit.Retrofit;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:lmyu@wisorg.com">David.Yu</a>
 * @version V1.0, 12/12/15
 */
public class ApiFactory {

    /**
     *
     * @param clazz Java interface of the retrofit service
     * @param endPoint REST endpoint url
     * @param convertFactory
     * @param callAdapterFactory
     * @param <T> retrofit service with defined endpoint
     * @return
     */
    public static <T> T createRetrofitApi(final Class<T> clazz, final String endPoint, Converter.Factory convertFactory, CallAdapter.Factory callAdapterFactory) {
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(endPoint).addConverterFactory(convertFactory).addCallAdapterFactory(callAdapterFactory).build();
        return retrofit.create(clazz);
    }

    public static <T> T createRetrofitApi(final Class<T> clazz, final String endPoint, Converter.Factory convertFactory) {
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(endPoint).addConverterFactory(convertFactory).build();
        return retrofit.create(clazz);
    }
}
