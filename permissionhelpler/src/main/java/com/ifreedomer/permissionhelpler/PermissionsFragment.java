package com.ifreedomer.permissionhelpler;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionsFragment extends Fragment {
    private final int PERMISSIONS_REQUEST_CODE = 1000;

    private PermissionHelper.PermissionResultCallback mPermissionResultCallback;

    public PermissionsFragment() {
    }

    public PermissionHelper.PermissionResultCallback getPermissionResultCallback() {
        return mPermissionResultCallback;
    }

    @TargetApi(Build.VERSION_CODES.M)
    void requestPermissions(@NonNull String[] permissions) {
        List<String> permission = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (lacksPermission(permissions[i])) {
                permission.add(permissions[i]);
            }
            Log.d("permission = ", permissions[i] + "");
        }

        if (permission.size() > 0) {
            requestPermissions(permission.toArray(new String[permission.size()]), PERMISSIONS_REQUEST_CODE);
        }

    }

    public void setPermissionResultCallback(PermissionHelper.PermissionResultCallback permissionResultCallback) {
        this.mPermissionResultCallback = permissionResultCallback;
    }

    // 判断权限集合
    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean lacksPermissions(String... permissions) {
        for (String permission : permissions) {
            if (lacksPermission(permission)) {
                return true;
            }
        }
        return false;
    }

    // 判断是否缺少权限
    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean lacksPermission(String permission) {
        return ContextCompat.checkSelfPermission(getContext(), permission) ==
                PackageManager.PERMISSION_DENIED;
    }


    // 含有全部的权限
    public boolean hasAllPermissionsGranted(@NonNull int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode != PERMISSIONS_REQUEST_CODE) {
            return;
        }


        if (mPermissionResultCallback != null) {
            mPermissionResultCallback.onPermissionResult(hasAllPermissionsGranted(grantResults));
        }
    }


}
