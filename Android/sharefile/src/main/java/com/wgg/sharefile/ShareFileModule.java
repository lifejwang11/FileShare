package com.wgg.sharefile;

import android.app.Activity;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;

import java.util.Map;

public class ShareFileModule extends WXSDKEngine.DestroyableModule {

    private String TYPE="type";

    private String FILEPATH="filePath";

    @JSMethod(uiThread = true)
    public void render(JSONObject options, JSCallback jsCallback){
        String type = options.getString(TYPE);
        String filePath = options.getString(FILEPATH);
        Map map = new Share.Builder((Activity) mWXSDKInstance.getContext())
                .setType(type)
                .setFilePath(filePath)
                .build()
                .shareBySystem();
        map.put("type",type);
        map.put("filePath",filePath);
        jsCallback.invoke(map);
        jsCallback.invokeAndKeepAlive(map);
    }


    @Override
    public void destroy() {

    }
}
