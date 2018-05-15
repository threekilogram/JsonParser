package com.example.wuxio.jsonparserlib.listener;

import com.example.jsonparser.JsonParser;
import com.example.jsonparser.ValueHolder;
import com.example.wuxio.jsonparserlib.bean.WeatherBean;

import java.util.List;

/**
 * @author wuxio 2018-05-15:16:02
 */
public class WeatherParseListener implements JsonParser.OnParseListener {

    private WeatherBean mWeatherBean = new WeatherBean();


    public void setWeatherBean(WeatherBean weatherBean) {

        mWeatherBean = weatherBean;
    }


    @Override
    public void onParseTo(List< JsonParser.Node > nodes, String key, ValueHolder valueHolder) {

        if ("status".equals(key)) {
            mWeatherBean.setStatus(valueHolder.value());

        } else if ("msg".equals(key)) {
            mWeatherBean.setMsg(valueHolder.value());


            /* result */
        } else if ("city".equals(key)) {
            mWeatherBean.getResult().setCity(valueHolder.value());

        } else if ("cityid".equals(key)) {
            mWeatherBean.getResult().setCityid(valueHolder.value());

        } else if ("citycode".equals(key)) {
            mWeatherBean.getResult().setCitycode(valueHolder.value());

        } else if ("date".equals(key)) {
            mWeatherBean.getResult().setDate(valueHolder.value());

        } else if ("week".equals(key)) {
            mWeatherBean.getResult().setWeek(valueHolder.value());

        } else if ("weather".equals(key)) {
            mWeatherBean.getResult().setWeather(valueHolder.value());

        } else if ("temp".equals(key)) {
            mWeatherBean.getResult().setTemp(valueHolder.value());

        } else if ("temphigh".equals(key)) {
            mWeatherBean.getResult().setTemphigh(valueHolder.value());

        } else if ("templow".equals(key)) {
            mWeatherBean.getResult().setTemplow(valueHolder.value());

        } else if ("img".equals(key)) {
            mWeatherBean.getResult().setImg(valueHolder.value());

        } else if ("humidity".equals(key)) {
            mWeatherBean.getResult().setHumidity(valueHolder.value());

        } else if ("pressure".equals(key)) {
            mWeatherBean.getResult().setPressure(valueHolder.value());

        } else if ("windspeed".equals(key)) {
            mWeatherBean.getResult().setWindspeed(valueHolder.value());

        } else if ("winddirect".equals(key)) {
            mWeatherBean.getResult().setWinddirect(valueHolder.value());

        } else if ("windpower".equals(key)) {
            mWeatherBean.getResult().setWindpower(valueHolder.value());

        } else if ("updatetime".equals(key)) {
            mWeatherBean.getResult().setUpdatetime(valueHolder.value());


            /* index array */
        } else if ("iname".equals(key)) {
            mWeatherBean.getResult().getIndex().add(new WeatherBean.ResultBean.IndexBean());
            mWeatherBean.getResult().getIndex().get(nodes.get(nodes.size() - 2).getIndex()).setIname
                    (valueHolder.value());

        } else if ("ivalue".equals(key)) {
            mWeatherBean.getResult().getIndex().add(new WeatherBean.ResultBean.IndexBean());
            mWeatherBean.getResult().getIndex().get(nodes.get(nodes.size() - 2).getIndex()).setIvalue
                    (valueHolder.value());

        } else if ("detail".equals(key)) {
            mWeatherBean.getResult().getIndex().add(new WeatherBean.ResultBean.IndexBean());
            mWeatherBean.getResult().getIndex().get(nodes.get(nodes.size() - 2).getIndex()).setDetail
                    (valueHolder.value());


            /* aqi */
        } else if ("so2".equals(key)) {
            mWeatherBean.getResult().getAqi().setSo2(valueHolder.value());

        } else if ("so224".equals(key)) {
            mWeatherBean.getResult().getAqi().setSo224(valueHolder.value());

        } else if ("no2".equals(key)) {
            mWeatherBean.getResult().getAqi().setNo2(valueHolder.value());

        } else if ("no224".equals(key)) {
            mWeatherBean.getResult().getAqi().setNo224(valueHolder.value());

        } else if ("co".equals(key)) {
            mWeatherBean.getResult().getAqi().setCo(valueHolder.value());

        } else if ("co24".equals(key)) {
            mWeatherBean.getResult().getAqi().setCo24(valueHolder.value());

        } else if ("o3".equals(key)) {
            mWeatherBean.getResult().getAqi().setO3(valueHolder.value());

        } else if ("o38".equals(key)) {
            mWeatherBean.getResult().getAqi().setO38(valueHolder.value());

        } else if ("o324".equals(key)) {
            mWeatherBean.getResult().getAqi().setO324(valueHolder.value());

        } else if ("pm10".equals(key)) {
            mWeatherBean.getResult().getAqi().setPm10(valueHolder.value());

        } else if ("pm1024".equals(key)) {
            mWeatherBean.getResult().getAqi().setPm1024(valueHolder.value());

        } else if ("pm2_5".equals(key)) {
            mWeatherBean.getResult().getAqi().setPm2_5(valueHolder.value());

        } else if ("pm2_524".equals(key)) {
            mWeatherBean.getResult().getAqi().setPm2_524(valueHolder.value());

        } else if ("iso2".equals(key)) {
            mWeatherBean.getResult().getAqi().setIso2(valueHolder.value());

        } else if ("ino2".equals(key)) {
            mWeatherBean.getResult().getAqi().setIno2(valueHolder.value());

        } else if ("ico".equals(key)) {
            mWeatherBean.getResult().getAqi().setIco(valueHolder.value());

        } else if ("io3".equals(key)) {
            mWeatherBean.getResult().getAqi().setIo3(valueHolder.value());

        } else if ("io38".equals(key)) {
            mWeatherBean.getResult().getAqi().setIo38(valueHolder.value());

        } else if ("ipm10".equals(key)) {
            mWeatherBean.getResult().getAqi().setIpm10(valueHolder.value());

        } else if ("ipm2_5".equals(key)) {
            mWeatherBean.getResult().getAqi().setIpm2_5(valueHolder.value());

        } else if ("aqi".equals(key)) {
            mWeatherBean.getResult().getAqi().setAqi(valueHolder.value());

        } else if ("primarypollutant".equals(key)) {
            mWeatherBean.getResult().getAqi().setPrimarypollutant(valueHolder.value());

        } else if ("quality".equals(key)) {
            mWeatherBean.getResult().getAqi().setQuality(valueHolder.value());

        } else if ("timepoint".equals(key)) {
            mWeatherBean.getResult().getAqi().setTimepoint(valueHolder.value());


            /* aqiinfo */
        } else if ("level".equals(key)) {
            mWeatherBean.getResult().getAqi().getAqiinfo().setLevel(valueHolder.value());

        } else if ("color".equals(key)) {
            mWeatherBean.getResult().getAqi().getAqiinfo().setColor(valueHolder.value());

        } else if ("affect".equals(key)) {
            mWeatherBean.getResult().getAqi().getAqiinfo().setAffect(valueHolder.value());

        } else if ("measure".equals(key)) {
            mWeatherBean.getResult().getAqi().getAqiinfo().setMeasure(valueHolder.value());
        }
    }
}
