package jmy.com.newsschool.presenter;

import jmy.com.newsschool.bean.Movie;
import jmy.com.newsschool.net.ApiStore;
import jmy.com.newsschool.view.BaseView;
import jmy.com.newsschool.view.MainView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by CRTE-CD-13 on 2018/5/18.
 */

public class MainPresenter {

    private ApiStore apiStore;
    private Retrofit retrofit;
    private BaseView baseView;

    public MainPresenter(MainView mainView) {
        baseView = mainView;

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiStore = retrofit.create(ApiStore.class);
    }

    public void getData(){
        apiStore.getTopMovie(0,20).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                baseView.getDataSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                baseView.getDataFail(t.toString());
            }
        });
    }
}
