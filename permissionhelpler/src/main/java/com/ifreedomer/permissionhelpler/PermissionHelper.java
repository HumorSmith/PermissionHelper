package com.ifreedomer.permissionhelpler;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;

/**
 * 检查权限的工具类
 * <p/>
 * Created by wangchenlong on 16/1/26.
 */
public class PermissionHelper {
    private Context mContext;
    static final String TAG = "RxPermissions";
    private PermissionsFragment permissionsFragment;

    public PermissionHelper(Activity activity) {
        permissionsFragment = getPermissionsFragment(activity);
    }

    // 启动当前权限页面的公开接口
    public void requestPermission(Callback permissionResultCallback, String[] permissions) {
        if (!isMarshmallow()) {
            if (permissionResultCallback!=null){
                permissionResultCallback.onPermissionResult(true);
            }
            return;
        }
        permissionsFragment.setPermissionResultCallback(permissionResultCallback);
        permissionsFragment.requestPermissions(permissions);
    }

    boolean isMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }


    public interface Callback {
        void onPermissionResult(boolean allGranted);
    }

    private PermissionsFragment getPermissionsFragment(Activity activity) {
        boolean isNewInstance = permissionsFragment == null;
        if (isNewInstance) {
            permissionsFragment = new PermissionsFragment();
            FragmentManager fragmentManager = activity.getFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(permissionsFragment, TAG)
                    .commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }
        return permissionsFragment;
    }


    @TargetApi(Build.VERSION_CODES.M)
    void requestPermissionsFromFragment(String[] permissions) {
        permissionsFragment.requestPermissions(permissions);
    }

    private PermissionsFragment findRxPermissionsFragment(Activity activity) {
        return (PermissionsFragment) activity.getFragmentManager().findFragmentByTag(TAG);
    }


}