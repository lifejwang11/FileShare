package com.wgg.sharefile;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Share {

    private String type;

    private Activity activity;

    private String TAG = "Share";

    private String title;

    private Uri shareFileUri;

    /**
     * Share complete onActivityResult requestCode
     */
    private int requestCode = -1;

    static Map map = new HashMap();

    private Share(@NonNull Builder builder) {
        this.activity = builder.activity;
        this.title = builder.title;
        this.shareFileUri = builder.shareFileUri;
        this.requestCode = builder.requestCode;
        this.type=builder.type;
    }


    private boolean checkShareParam() {
        if (this.activity == null) {
            Log.e(TAG, "activity is null.");
            return false;
        }
        return true;
    }

    /**
     * shareBySystem
     */
    public Map shareBySystem () {
        if (checkShareParam()) {
            Intent shareIntent = createShareIntent();
//            if (type.equals("SYSTEM")){
                this.activity.startActivity(Intent.createChooser(shareIntent, "分享到"));
//            }else{
//                if (shareIntent == null) {
//                    return map;
//                }
//                if (title == null) {
//                    title = "222222";
//                }
//                if (shareIntent.resolveActivity(activity.getPackageManager()) != null) {
//                    try {
//                        if (requestCode != -1) {
//                            activity.startActivityForResult(shareIntent, requestCode);
//                        } else {
//                            activity.startActivity(shareIntent);
//                        }
//                    } catch (Exception e) {
//                        Log.e(TAG, Log.getStackTraceString(e));
//                        map.put("分享异常",e.getMessage());
//                    }
//                }
//            }
        }
        return map;
    }


    private Intent createShareIntent() {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shareIntent.addCategory("android.intent.category.DEFAULT");
        if(type.equals("QQ")){
            ComponentName comp = new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity");
            shareIntent.setComponent(comp);
        }else if(type.equals("WX")){
            ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
            shareIntent.setComponent(comp);
        }

        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.addCategory("android.intent.category.DEFAULT");
        shareIntent.setType("*/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, shareFileUri);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            List<ResolveInfo> resInfoList = activity.getPackageManager().queryIntentActivities(shareIntent, PackageManager.MATCH_DEFAULT_ONLY);
            for (ResolveInfo resolveInfo : resInfoList) {
                String packageName = resolveInfo.activityInfo.packageName;
                activity.grantUriPermission(packageName, shareFileUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
        }
        return shareIntent;
    }

    public static class Builder {
        private Activity activity;
        private String title;
        private Uri shareFileUri;
        private int requestCode = -1;
        private String type="SYSTEM";

        public Builder(Activity activity) {
            this.activity = activity;
        }


        public Builder setType(String type){
            this.type = type;
            return this;
        }


        /**
         * Set Title
         * @param title title
         * @return Builder
         */
        public Builder setTitle(@NonNull String title) {
            this.title = title;
            return this;
        }

        /**
         * Set share file path
         * @param shareFileUri shareFileUri
         * @return Builder
         */
        public Builder setShareFileUri(Uri shareFileUri) {
            this.shareFileUri = shareFileUri;
            return this;
        }


        /**
         * Set onActivityResult requestCode, default value is -1
         * @param requestCode requestCode
         * @return Builder
         */
        public Builder setOnActivityResult (int requestCode) {
            this.requestCode = requestCode;
            return this;
        }


        public Builder setFilePath (String filePath) {
            this.shareFileUri = FileUtils.getUri(this.activity,filePath);
            return this;
        }

        /**
         * build
         * @return Share2
         */
        public Share build() {
            return new Share(this);
        }

    }

}
