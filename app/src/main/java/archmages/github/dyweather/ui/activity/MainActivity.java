package archmages.github.dyweather.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.orhanobut.logger.Logger;

import org.apache.commons.lang3.StringUtils;

import archmages.github.dyweather.R;
import archmages.github.dyweather.api.WeatherApi;
import archmages.github.dyweather.bean.WeatherBean;
import archmages.github.dyweather.support.constants.Constant;
import archmages.github.dyweather.support.factory.ApiFactory;
import archmages.github.dyweather.support.utils.ImageResMapUtil;
import archmages.github.dyweather.ui.interfaces.AbstractAppActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.GsonConverterFactory;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends AbstractAppActivity
        implements NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind((R.id.drawer_layout))
    DrawerLayout drawer;

    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @Bind(R.id.tvStats)
    TextView tvStats;
    @Bind(R.id.tvTmp)
    TextView tvTmp;
    @Bind(R.id.tvTmpCurrent)
    TextView tvTmpCurrent;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout mSwipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        initSwipeLayout();
//        requestLocation();
    }

    private void initSwipeLayout() {
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLocation();
    }

    @Override
    protected void handleLocation(AMapLocation aMapLocation) {
        String city = aMapLocation.getCity();
        if (StringUtils.endsWithIgnoreCase(city, "市")) {
            city = StringUtils.removeEndIgnoreCase(city, "市");
        }
        toolbar.setTitle(city);
        Logger.d(city);
        getWeatherByCity(city);
    }

    private void getWeatherByCity(final String city) {
        WeatherApi weatherService = ApiFactory
                .createRetrofitApi(WeatherApi.class, Constant.WEATHER_BASE_URL,
                        GsonConverterFactory.create(), RxJavaCallAdapterFactory.create());
        weatherService.getWeatherByCityName(city).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Observer<WeatherBean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Logger.e(e.toString());
                                mSwipeLayout.setRefreshing(false);
                            }

                            @Override
                            public void onNext(WeatherBean weatherBean) {
                                mSwipeLayout.setRefreshing(false);
                                tvStats.setText(
                                        weatherBean.getHeWeatherDataService30().get(0).getNow()
                                                .getCond().getTxt());
                                int drawableLeft = ImageResMapUtil.mapCondImageRes(weatherBean.getHeWeatherDataService30().get(0).getNow()
                                        .getCond().getTxt());
                                tvStats.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, 0, 0, 0);
                                tvTmp.setText(getResources().getString(R.string.tmp_range, weatherBean.getHeWeatherDataService30().get(0)
                                        .getDailyForecast().get(0).getTmp()
                                        .getMax(), weatherBean
                                        .getHeWeatherDataService30().get(0).getDailyForecast()
                                        .get(0).getTmp().getMin()));
                                tvTmpCurrent.setText(
                                        weatherBean.getHeWeatherDataService30().get(0).getNow()
                                                .getTmp() + "℃");
                                Logger.d(weatherBean.getHeWeatherDataService30().get(0).getNow()
                                        .getCond().getTxt());
                            }
                        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onRefresh() {
        requestLocation();
    }
}
