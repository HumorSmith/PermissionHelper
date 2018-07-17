# PermissionHelper
this library use to android dynamic permission . no need rxjava.

## How to use

```
compile 'com.ifreedomer:com.ifreedomer.permissionhelper:1.0.1'
```

First step
```
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CAMERA
    };

 private PermissionHelper.Callback permissionResultCallback = new PermissionHelper.Callback() {
        @Override
        public void onPermissionResult(boolean allGranted) {
            Toast.makeText(MainActivity.this, "allGranted = " + allGranted, Toast.LENGTH_SHORT).show();
        }
    };

 ```
second step
```
new PermissionHelper(this).requestPermission( permissionResultCallback,PERMISSIONS);
```
everything is ok~let's beer
