package com.ifreedomer.permissionhelper;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ifreedomer.permissionhelpler.PermissionHelper;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CAMERA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         PermissionHelper mPermissionHelper = new PermissionHelper(this);
        mPermissionHelper.requestPermission( permissionResultCallback,PERMISSIONS);
    }

    private PermissionHelper.Callback permissionResultCallback = new PermissionHelper.Callback() {
        @Override
        public void onPermissionResult(boolean allGranted) {
            Toast.makeText(MainActivity.this, "allGranted = " + allGranted, Toast.LENGTH_SHORT).show();
        }
    };


}
