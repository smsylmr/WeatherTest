package com.example.lmr.weathertest.util;

import java.io.InputStream;

/**
 * Created by LMR on 2017/2/15.
 */

public interface HttpCallbackListener {
    void onFinish(InputStream in);

    void onError(Exception e);
}
