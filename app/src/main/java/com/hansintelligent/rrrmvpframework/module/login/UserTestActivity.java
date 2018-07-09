package com.hansintelligent.rrrmvpframework.module.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hansintelligent.rrrmvpframework.R;
import com.hansintelligent.rrrmvpframework.bean.UserInfo;
import com.hansintelligent.rrrmvpframework.utils.Logger;
import com.hansintelligent.rrrmvpframework.utils.UserUtil;

public class UserTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_test);

        UserInfo userInfo = UserUtil.getInstance().get();
        if (userInfo != null) {
            Logger.d("userInfo111", userInfo.toString());
        } else {
            Logger.d("userInfo111", "塔变空了");
        }



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
