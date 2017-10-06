package com.mijing.mall;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.mijing.mall", appContext.getPackageName());
    }

    @Test
    public void testHttp() throws Exception{
        Context appContext = InstrumentationRegistry.getTargetContext();

        String url = "http://weather.51wnl.com/weatherinfo/GetMoreWeather?cityCode=101020100&weatherType=0";
        /*RestRequest request = new RestRequest.Builder<WeatherVO>()
                .clazz(WeatherVO.class)
                .method(HttpMethod.GET)
                .setContext(appContext)
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
            }

            @Override
            public void onFailure(String errMsg) {

            }

            @Override
            public void onFinish(Object... tags) {
                Log.i("jlf", "on Finish");
            }
        });*/
    }
}
