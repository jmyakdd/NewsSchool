package jmy.com.newsschool.view;

/**
 * Created by CRTE-CD-13 on 2018/5/18.
 */

public interface BaseView<T> {
    void getDataSuccess(T data);

    void getDataFail(String e);
}
