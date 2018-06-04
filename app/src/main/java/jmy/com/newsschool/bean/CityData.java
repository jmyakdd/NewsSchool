package jmy.com.newsschool.bean;

import org.litepal.crud.DataSupport;

public class CityData extends DataSupport{
    private int id;
    private String name;
    private String parentName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
