# PermissionHelper
this library use to android dynamic permission . no need rxjava.

##How to use
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
 ### second
```
new PermissionHelper(this).requestPermission( permissionResultCallback,PERMISSIONS);
```
everything is ok~
