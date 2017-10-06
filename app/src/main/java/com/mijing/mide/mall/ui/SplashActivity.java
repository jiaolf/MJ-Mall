package com.mijing.mide.mall.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.mijing.mide.mall.R;
import com.mijing.mide.mall.base.BaseActivity;
import com.mijing.mide.mall.bean.WeatherVO;
import com.mijing.mide.mall.utils.RequestUtil;
import com.xinnian.http.HttpRequest;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //testHttp2();
        startActivity(new Intent(this, MainActivity.class));
    }

    private void testHttp2() {
        String host = "http://weather.51wnl.com";
        String path = "/weatherinfo/GetMoreWeather";
        String url = RequestUtil.buildUrl(host + path, "cityCode", 101020100, "weatherType", 0);

        HttpRequest request = new HttpRequest.Builder<WeatherVO>()
                .clazz(WeatherVO.class)
                .url(url)
                .tags("weather")
                .build();

        request.get(new com.xinnian.http.RestCallback<WeatherVO>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(WeatherVO o) {
                Log.i("jlf", o.getWeatherinfo().toString());
                showToast(o.getWeatherinfo().getCity() + o.getWeatherinfo().getWeather1());
            }

            @Override
            public void onFailure(String errMsg) {

            }

            @Override
            public void onFinish(Object... tags) {
                Log.i("jlf", "finish:" + tags[0]);
            }
        });

    }

    /*private void testHttp() {
        String url = "http://weather.51wnl.com/weatherinfo/GetMoreWeather?cityCode=101020100&weatherType=0";
        RestRequest request = new RestRequest.Builder<WeatherVO>()
                .clazz(WeatherVO.class)
                .method(HttpMethod.GET)
                .url(url)
                .build();

        request.request(new RestCallback<WeatherVO>() {
            @Override
            public void onStart() {
                Log.i("jlf", "onstart...");
            }

            @Override
            public void onSuccess(WeatherVO o) {
                Log.i("jlf", o.toString());
                Toast.makeText(SplashActivity.this, o.getWeatherinfo().getCity() + o.getWeatherinfo().getWeather1(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String errMsg) {

            }

            @Override
            public void onFinish(Object... tags) {
                Log.i("jlf", "on Finish");
            }
        });

    }*/
}
