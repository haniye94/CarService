package ir.tehranOil.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.servicea.R;
import ir.tehranOil.app.G;
import ir.tehranOil.app.PreferenceUtil;
import ir.tehranOil.app.StateOpenHelper;
import ir.tehranOil.model.State;
import ir.tehranOil.retrofit.Api;
import ir.tehranOil.retrofit.RetrofitClient;
import me.aflak.libraries.callback.FingerprintDialogSecureCallback;
import me.aflak.libraries.callback.PasswordCallback;
import me.aflak.libraries.dialog.DialogAnimation;
import me.aflak.libraries.dialog.FingerprintDialog;
import me.aflak.libraries.dialog.PasswordDialog;
import me.aflak.libraries.utils.FingerprintToken;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity implements PasswordCallback, FingerprintDialogSecureCallback {

    PreferenceUtil preferenceUtil;
    DilatingDotsProgressBar mDilatingDotsProgressBar;
    private SQLiteDatabase mDatabase;
    private String PASSWORD = "";

    private static final String TAG = "SplashActivity";

    @Override
    public void onResume() {
        super.onResume();
        G.Activity = this;
        G.context = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new PreferenceUtil(this);
        G.context = this;
        G.Activity = this;
        G.preference.edit().putInt("AvgKm", 0).apply();
        String d_id = G.preference.getString("d_id", "");
        if (d_id.length() > 0 && PreferenceUtil.getcachLogin()) {
            if (G.preference.getBoolean("ActivateRad", false)) {
                Log.d("ActivateRad", "onCreate: ActivateRad1:" + G.preference.getBoolean("ActivateRad", false));
                getProfile(d_id);
            }

        }
        PASSWORD = (G.preference.getString("PASSWORD", "") + "");
        if (PASSWORD.length() < 3) {
            PASSWORD = "kfjdhgdfghjkghuihgerhguerhgruhg";
        }
        setContentView(R.layout.activity_splash);
        mDilatingDotsProgressBar = findViewById(R.id.progress);
//        mDilatingDotsProgressBar.showNow();
//        getProfile();
        new Handler().postDelayed(() -> {
            G.Log(PreferenceUtil.getcachLogin() + "  PreferenceUtil.getcachLogin()");
            G.Log(d_id.length() + "  d_id.length()");
            if (!PreferenceUtil.getcachLogin()) {

                Intent mainIntent = new Intent(SplashActivity.this, RegisterActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                mDilatingDotsProgressBar.hideNow();
                SplashActivity.this.finish();
            } else if (d_id.length() <= 0) {
                G.preference.edit().putBoolean("ActivateRad", false).apply();
                Log.d("ActivateRad", "onCreate: ActivateRad2:" + G.preference.getBoolean("ActivateRad", false));

//                Intent mainIntent = new Intent(SplashActivity.this, ActivateActivity.class);
                Intent intent = new Intent(SplashActivity.this, LoginInfoActivity.class);
                intent.putExtra("editPro", "");
                intent.putExtra(G.CAN_EDIT_MOBILE, true);
                SplashActivity.this.startActivity(intent);
                mDilatingDotsProgressBar.hideNow();
                SplashActivity.this.finish();
            } else {


                if (G.preference.getBoolean("AccessFingerprint", false) && android.os.Build.VERSION.SDK_INT >= 23 && FingerprintDialog.isAvailable(G.context)) {
                    FingerprintDialog.initialize(G.context)
                            .title(R.string.fingerprint_title)
                            .tryLimit(4, fingerprintDialog -> {
                                Toast.makeText(SplashActivity.this, "شما بیش از 4 بار اثرانگشت اشتباه زدید", Toast.LENGTH_SHORT).show();
                                G.preference.edit().clear().apply();
                                fingerprintDialog.dismiss();
                                finish();
                            })

                            .enterAnimation(DialogAnimation.Enter.RIGHT)
                            .exitAnimation(DialogAnimation.Exit.RIGHT)
                            .circleScanningColor(R.color.button)
                            .fingerprintScanningColor(R.color.white)
                            .cancelOnPressBack(false)
                            .cancelOnTouchOutside(false)
                            .usePasswordButton(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    PasswordDialog.initialize(SplashActivity.this)
                                            .title(R.string.password_title)
                                            .message(R.string.password_message)
                                            .callback(SplashActivity.this)
                                            .cancelOnPressBack(false)
                                            .cancelOnTouchOutside(false)
                                            .dimBackground(true)
                                            .enterAnimation(DialogAnimation.Enter.RIGHT)
                                            .exitAnimation(DialogAnimation.Exit.RIGHT)
                                            .passwordType(PasswordDialog.PASSWORD_TYPE_TEXT)
                                            .show();
                                }
                            })
                            .message(R.string.fingerprint_message)
                            .callback(SplashActivity.this, "KeyName1")
                            .show();

                } else if (G.preference.getBoolean("AccessPassword", false)) {
                    PasswordDialog.initialize(SplashActivity.this)
                            .title(R.string.password_title)
                            .message(R.string.password_message)
                            .callback(SplashActivity.this)
                            .dimBackground(true)
                            .cancelOnPressBack(false)
                            .cancelOnTouchOutside(false)
                            .enterAnimation(DialogAnimation.Enter.RIGHT)
                            .exitAnimation(DialogAnimation.Exit.RIGHT)
                            .passwordType(PasswordDialog.PASSWORD_TYPE_TEXT)
                            .show();
                } else {
                    G.preference.edit().putBoolean("ActivateRad", true).apply();
                    Log.d("ActivateRad", "onCreate: ActivateRad3:" + G.preference.getBoolean("ActivateRad", false));

                    Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    mDilatingDotsProgressBar.hideNow();
                    SplashActivity.this.finish();
                }

            }
        }, 2300);
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }


    public void getProfile(String d_id) {
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.getProfile("id,eq," + d_id);
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        String result = response.body().string();
                        G.Log(call.request().toString());
                        Log.d(TAG, "onResponse: request:" + call.request().toString());
                        Log.d(TAG, "onResponse: result" + result);
                        G.Log(result);
                        JSONObject object = G.StringtoJSONObject(result);
                        JSONArray records = object.getJSONArray("records");
                        if (records.length() > 0) {
                            for (int i = 0; i < records.length(); i++) {
                                JSONObject services_center = records.getJSONObject(i);

                                JSONObject obj = services_center.getJSONObject("user_id");

                                String user_id = obj.getString("id");
                                PreferenceUtil.cashUser_id(user_id);
                                String name = obj.getString("f_name");
                                String lastname = obj.getString("l_name");
                                String phone = obj.getString("mobile");
                                String sex = obj.getString("sex");
                                String b_date = obj.getString("birthdate");
                                String province_id = obj.getString("province_id");
                                String city_id = obj.getString("city_id");
                                G.preference.edit().putString("province_id", province_id).apply();
                                G.preference.edit().putString("city_id", city_id).apply();
                                if (obj.has("profile_photo")) {
                                    String profile_photo = obj.getString("profile_photo");
                                    G.preference.edit().putString("profile_photo", profile_photo).apply();
                                }
                                if (obj.has("header_photo")) {
                                    String header_photo = obj.getString("header_photo");
                                    G.preference.edit().putString("header_photo", header_photo).apply();
                                }
                                if (obj.has("password")) {
                                    String password = obj.getString("password");
                                    String now_password = G.preference.getString("PASSWORD", "");
                                    if (now_password.length() < 3) {
                                        G.preference.edit().putString("PASSWORD", password).apply();
                                    }
                                }
                                if (services_center.has("latitude") && services_center.has("longitude")) {
                                    String latitude = services_center.getString("latitude");
                                    String longitude = services_center.getString("longitude");
                                    G.preference.edit().putString("location_lat", latitude).apply();
                                    G.preference.edit().putString("location_lon", longitude).apply();
                                }
                                String d_id = services_center.getString("id");
                                PreferenceUtil.cashD_id(d_id);
                                String shop_name = services_center.getString("center_name");
                                String shop_phone = services_center.getString("phone");
                                String address = services_center.getString("address");
                                String job_category = services_center.getString("job_category_id");
                                String opentime = services_center.getString("f_open");
                                String closetime = services_center.getString("f_close");
                                String opentime2 = services_center.getString("l_open");
                                String closetime2 = services_center.getString("l_close");
                                int num_branch = services_center.getInt("numOfBranch");
                                int waiting_space = services_center.getBoolean("waiting_place") ? 1 : 0;
                                int catering_service = services_center.getBoolean("bar_serv") ? 1 : 0;
                                boolean status = services_center.getBoolean("status");
                                G.preference.edit().putBoolean("ServiceCenterStatus", status).apply();
                                if (!status) {
                                    G.toast(getResources().getString(R.string.statusfalse));
                                }
                                G.preference.edit().putBoolean("sayar", services_center.getBoolean("mobile_services")).apply();
                                G.preference.edit().putBoolean("karshenasi", services_center.getBoolean("checking")).apply();
                                G.preference.edit().putBoolean("holiday", services_center.getBoolean("holidays")).apply();
                                if (services_center.has("charge_total") && services_center.has("charge_remain")) {
                                    int charge_total = services_center.getInt("charge_total");
                                    int charge_remain = services_center.getInt("charge_remain");
                                    if(charge_total>10){
                                        charge_total = charge_total/10;
                                    }
                                    if(charge_remain>10){
                                        charge_remain = charge_remain/10;
                                    }
                                    G.preference.edit().putInt("charge_total", charge_total).apply();
                                    G.preference.edit().putInt("charge_remain", charge_remain).apply();
                                } else {
                                    G.preference.edit().putInt("charge_total", 0).apply();
                                    G.preference.edit().putInt("charge_remain", 0).apply();
                                }

                                PreferenceUtil.preferenceUtil.edit().putString("openTime", opentime).apply();
                                PreferenceUtil.preferenceUtil.edit().putString("closeTime", closetime).apply();
                                PreferenceUtil.preferenceUtil.edit().putString("openTime2", opentime2).apply();
                                PreferenceUtil.preferenceUtil.edit().putString("closeTime2", closetime2).apply();
                                PreferenceUtil.preferenceUtil.edit().putInt("numOfBranch", num_branch).apply();
                                PreferenceUtil.preferenceUtil.edit().putInt("waiting", waiting_space).apply();
                                PreferenceUtil.preferenceUtil.edit().putInt("catering", catering_service).apply();

                                if (job_category.length() > 0) {
                                    try {
                                        int jobcat = Integer.parseInt(job_category);
                                        G.preference.edit().putInt("job_category_id", jobcat).apply();
                                    } catch (NumberFormatException nfe) {
                                        System.out.println("Could not parse " + nfe);
                                    }

                                }

                                PreferenceUtil.cashD_id(d_id);
                                PreferenceUtil.cashPhone(phone);
                                PreferenceUtil.cashInfo(name, lastname, address, shop_name);
                                StateOpenHelper openHelper = new StateOpenHelper(G.Activity);
                                openHelper.openDatabase();
                                SQLiteDatabase mDatabase = openHelper.getReadableDatabase();
                                if (province_id.length() > 0) {
                                    Cursor cursor = mDatabase.query("Tbl_Ostan", null, null, null, null, null, null);
                                    cursor.moveToFirst();
                                    List<String> stateName = new ArrayList<>();
                                    for (int x = 0; x < cursor.getCount(); x++) {
                                        State state = new State();
                                        state.setCityName(cursor.getString(cursor.getColumnIndex("Title")));
                                        state.setCityId(cursor.getInt(cursor.getColumnIndex("ID")));
                                        if ((state.getCityId() + "").equals(province_id)) {
                                            PreferenceUtil.cashOstan(Integer.parseInt(province_id), state.getCityName());
                                        }
                                        stateName.add(state.getCityName());
                                        cursor.moveToNext();
                                    }
                                }
                                if (city_id.length() > 0) {
                                    Cursor cursor = mDatabase.query("Tbl_shahrestan", null, "PK_Ostan = ?", new String[]{province_id}, null, null, null);
                                    cursor.moveToFirst();
                                    for (int x = 0; x < cursor.getCount(); x++) {
                                        State state = new State();
                                        state.setCityName(cursor.getString(cursor.getColumnIndex("Title")));
                                        state.setCityId(cursor.getInt(cursor.getColumnIndex("ID")));
                                        if ((state.getCityId() + "").equals(city_id)) {
                                            PreferenceUtil.cashCity(Integer.parseInt(city_id), state.getCityName());
                                        }
                                        cursor.moveToNext();
                                    }


                                }


                            }
                        } else {
                            G.toast("مشکل در دریافت اطلاعات");
                        }
                    } catch (JSONException | IOException e) {
                        G.toast("مشکل در تجزیه اطلاعات");
                        e.printStackTrace();
                    }
                }

                G.stop_loading();

            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

    @Override
    public void onPasswordSucceeded() {
        G.preference.edit().putBoolean("ActivateRad", true).apply();
        Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
        SplashActivity.this.startActivity(mainIntent);
        if (mDilatingDotsProgressBar != null)
            mDilatingDotsProgressBar.hideNow();
        SplashActivity.this.finish();
    }

    @Override
    public boolean onPasswordCheck(String password) {
        return password.equals(PASSWORD);

    }

    @Override
    public void onPasswordCancel() {
        finish();
    }

    @Override
    public void onAuthenticationSucceeded() {
        G.preference.edit().putBoolean("ActivateRad", true).apply();
        Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
        SplashActivity.this.startActivity(mainIntent);
        if (mDilatingDotsProgressBar != null)
            mDilatingDotsProgressBar.hideNow();
        SplashActivity.this.finish();
    }

    @Override
    public void onAuthenticationCancel() {
        finish();
    }

    @Override
    public void onNewFingerprintEnrolled(FingerprintToken token) {
        PasswordDialog.initialize(SplashActivity.this)
                .title(R.string.password_title)
                .message(R.string.password_message)
                .callback(SplashActivity.this)
                .cancelOnPressBack(false)
                .cancelOnTouchOutside(false)
                .enterAnimation(DialogAnimation.Enter.RIGHT)
                .exitAnimation(DialogAnimation.Exit.RIGHT)
                .passwordType(PasswordDialog.PASSWORD_TYPE_TEXT)
                .show();
    }
}