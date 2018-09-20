package com.ifreedomer.permissionhelper;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.ifreedomer.permissionhelpler.PermissionHelper;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CAMERA
    };
    private PermissionHelper mPermissionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPermissionHelper = new PermissionHelper(this);
        Log.d(TAG,"allPermissionGranted before = "+ mPermissionHelper.isAllPermissionGranted(MainActivity.this, PERMISSIONS));
        new PermissionHelper(this).requestPermission( permissionResultCallback,PERMISSIONS);
    }

    private PermissionHelper.Callback permissionResultCallback = new PermissionHelper.Callback() {
        @Override
        public void onPermissionResult(boolean allGranted) {
            boolean allPermissionGranted = mPermissionHelper.isAllPermissionGranted(MainActivity.this, PERMISSIONS);
            Log.d(TAG,"allPermissionGranted after = "+allPermissionGranted);
            Toast.makeText(MainActivity.this, "allGranted = " + allGranted, Toast.LENGTH_SHORT).show();
        }
    };


}
