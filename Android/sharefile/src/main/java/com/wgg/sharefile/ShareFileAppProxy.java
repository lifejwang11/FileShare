package com.wgg.sharefile;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;

import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

import io.dcloud.weex.AppHookProxy;

public class ShareFileAppProxy implements AppHookProxy {
    @Override
    public void onCreate(Application application) {
        try {
            WXSDKEngine.registerModule("FileShare",ShareFileModule.class);
        } catch (WXException e) {
            e.printStackTrace();
        }
    }



}
