package archmages.github.dyweather.support.global;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:lmyu@wisorg.com">David.Yu</a>
 * @version V1.0, 12/12/15
 */
public final class DYApplication extends Application{

    private static DYApplication application;

    private static final String TAG = "DYWeather";

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        initLogger();
    }

    private void initLogger() {
//        Logger.init(TAG)                 // default PRETTYLOGGER or use just init()
//                .methodCount(3)                 // default 2
//                .hideThreadInfo()               // default shown
//                .logLevel(LogLevel.FULL)        // default LogLevel.FULL, LogLevel.NONE for release
//                .methodOffset(2)                // default 0
//                .logTool(new AndroidLogTool()); // custom log tool, optional
        Logger.init(TAG);
    }

    public static DYApplication getInstance(){
        return application;
    }
}
