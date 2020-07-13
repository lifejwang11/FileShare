package com.wgg.sharefile;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;


public class FileUtils {

    public static Uri getUri(Context context,String filePath){
        File tempFile = new File(filePath);
        //判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
            try{
                return FileProvider.getUriForFile(context, context.getPackageName() + ".provider", tempFile);
//                return FileProvider.getUriForFile(context, "uni.UNI899E01B.fileprovider", tempFile);
            }catch (Exception e){
                e.printStackTrace();
            }
        } else {    //否则使用Uri.fromFile(file)方法获取Uri
            return Uri.fromFile(tempFile);
        }
        return null;
    }


}
