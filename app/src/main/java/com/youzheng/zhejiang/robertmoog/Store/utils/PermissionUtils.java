package com.youzheng.zhejiang.robertmoog.Store.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.necer.ndialog.NDialog;
import com.youzheng.zhejiang.robertmoog.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * 动态获取权限
 * Created by  on 2018/6/4.
 */

public class PermissionUtils {

    public static  final int CODE_SINGLE = 1;

    public static  final int CODE_MULTI = 2;

    /**
     * 判断权限是否开启
     *
     * @return
     */
    public static boolean permissionIsOpen(Activity activity, String permission) {
        if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED ) {//表示未授权
            return false;
        }
        return true;
    }

    /**
     * 请求单个授权
     *
     * @param activity
     * @param permission
     */
    public static void openSinglePermission(Activity activity, String permission, int requestCode) {
            //动态申请权限
            ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
    }

    /**
     * 多个授权
     * @param activity
     * @param permissions
     * @param requestCode
     */
    public static void openMultiPermission(Activity activity, List<String> permissions, int requestCode) {
        List<String> permission_no_open;
        if (permissions != null && permissions.size() > 0) {
            permission_no_open = new ArrayList<>();
            for (int i = 0; i < permissions.size(); i++) {
                if (!permissionIsOpen(activity, permissions.get(i))) {
                    permission_no_open.add(permissions.get(i));
                }
            }
            if (permission_no_open != null && permission_no_open.size() > 0) {
                ActivityCompat.requestPermissions(activity, permission_no_open.toArray(new String[permission_no_open.size()]), requestCode);
            }
        }
    }

    public static void jumpAppInfo(Context context){

        Intent intent=new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(intent);

    }

    public static void showCameraDialog(String contentStr , final Context context){

        new NDialog(context)
                .setTitle(context.getString(R.string.public_hint))
                .setTitleColor(context.getResources().getColor(R.color.text_drak_gray))
                .setTitleSize(18)
                .setTitleCenter(false)
                .setMessageCenter(false)
                .setMessage(context.getString(R.string.content_str)  + contentStr)
                .setMessageSize(16)
                .setMessageColor(context.getResources().getColor(R.color.text_main))
                .setNegativeTextColor(context.getResources().getColor(R.color.text_main))
                .setPositiveTextColor(context.getResources().getColor(R.color.text_main))
                .setButtonCenter(false)
                .setButtonSize(14)
                .setCancleable(true)
                .setOnConfirmListener(new NDialog.OnConfirmListener() {
                    @Override
                    public void onClick(int which) {
                        //which,0代表NegativeButton，1代表PositiveButton
                        if (which == 1){
                            PermissionUtils.jumpAppInfo(context);
                        }

                    }
                }).create(NDialog.CONFIRM).show();

    }

    //是否有通知权限
    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
    @SuppressLint("NewApi")
    public static boolean isNotificationEnabled(Context context) {
        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo(); String pkg = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid; Class appOpsClass = null; /* Context.APP_OPS_MANAGER */
        try { appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE, String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);
            int value = (Integer) opPostNotificationValue.get(Integer.class);
            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED); }
            catch (Exception e) { e.printStackTrace(); }
            return false; }

}
