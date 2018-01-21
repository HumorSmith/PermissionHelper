package com.ifreedomer.permissionhelper;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ifreedomer.permissionhelpler.PermissionHelper;

public class MainActivity extends AppCompatActivity {
    private PermissionHelper mPermissionHelper = new PermissionHelper();
    private String TAG = MainActivity.class.getSimpleName();
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPermissionHelper.requestPermission(this, permissionResultCallback,PERMISSIONS);
    }

    private PermissionHelper.PermissionResultCallback permissionResultCallback = new PermissionHelper.PermissionResultCallback() {
        @Override
        public void onPermissionResult(boolean allGranted) {
            Toast.makeText(MainActivity.this, "allGranted = " + allGranted, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPermissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
