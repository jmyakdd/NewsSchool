package jmy.com.newsschool.bean;

import java.util.List;

public class ProviceData{
    private String proviceKey;
    private String proviceValue;
    private boolean isLoad;
    private boolean isLoading;
    private List<CityData>cityData;

    public List<CityData> getCityData() {
        return cityData;
    }

    public void setCityData(List<CityData> cityData) {
        this.cityData = cityData;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public String getProviceKey() {
        return proviceKey;
    }

    public void setProviceKey(String proviceKey) {
        this.proviceKey = proviceKey;
    }

    public String getProviceValue() {
        return proviceValue;
    }

    public void setProviceValue(String proviceValue) {
        this.proviceValue = proviceValue;
    }

    public boolean isLoad() {
        return isLoad;
    }

    public void setLoad(boolean load) {
        isLoad = load;
    }
}
