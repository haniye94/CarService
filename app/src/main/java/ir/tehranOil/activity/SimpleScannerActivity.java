package ir.tehranOil.activity;


//    public class SimpleScannerActivity extends Activity implements ZXingScannerView.ResultHandler {
//        private ZXingScannerView mScannerView;
//        @Override
//        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//            int[] permissionCheck = {
//                    ContextCompat.checkSelfPermission(SimpleScannerActivity.this, Manifest.permission.CAMERA),
//                    ContextCompat.checkSelfPermission(SimpleScannerActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)};
//            for (int aPermissionCheck : permissionCheck) {
//                if (aPermissionCheck == -1) {
//                    finish();
//                } else {
//                    setContentView(mScannerView);
//                }
//            }
//        }
//
//        @Override
//        public void onCreate(Bundle state) {
//            super.onCreate(state);
//            if (ActivityCompat.checkSelfPermission(SimpleScannerActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SimpleScannerActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SimpleScannerActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//                if  (android.os.Build.VERSION.SDK_INT >= 23){
//                    Log.d("SimpleScanner", "onCreate: 23");
//                    mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
//                    setContentView(mScannerView);
//                }
//            } else {
//                checkRunTimePermission();
//            }
//
//            mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
//            setContentView(mScannerView);
//            G.Activity = this;
//            G.context = this;// Set the scanner view as the content view
//        }
//
//        @Override
//        public void onResume() {
//            super.onResume();
//            G.Activity = this;
//            G.context = this;
//            mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
//            mScannerView.startCamera();          // Start camera on resume
//        }
//
//        @Override
//        public void onPause() {
//            super.onPause();
//            mScannerView.stopCamera();           // Stop camera on pause
//        }
//
//        @Override
//        public void handleResult(Result rawResult) {
//            // Do something with the result here
//            Log.v("TAG", rawResult.getText()); // Prints scan results
//            Log.v("TAG", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
//
//            // If you would like to resume scanning, call this method below:
//            mScannerView.resumeCameraPreview(this);
//        }
//        private void checkRunTimePermission() {
//            String[] permissionArrays = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.CAMERA};
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                requestPermissions(permissionArrays, 11111);
//            }
//        }
//    }
