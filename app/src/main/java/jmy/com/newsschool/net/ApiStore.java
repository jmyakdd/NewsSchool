package jmy.com.newsschool.net;

import jmy.com.newsschool.bean.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by CRTE-CD-13 on 2018/5/18.
 */

public interface ApiStore {
    @GET("top250")
    Call<Movie> getTopMovie(@Query("start") int start,@Query("count") int count);
}
