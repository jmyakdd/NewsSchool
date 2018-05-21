package jmy.com.newsschool.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by CRTE-CD-13 on 2018/5/18.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public abstract int contentView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(contentView());
    }
}
