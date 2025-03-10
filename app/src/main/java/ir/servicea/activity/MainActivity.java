package ir.servicea.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.servicea.FragmentMain;
import ir.servicea.Fragmentprofile;
import ir.servicea.R;
import ir.servicea.adapter.AdapterTabLayout;
import ir.servicea.app.Constants;
import ir.servicea.app.CustomViewPager;
import ir.servicea.app.G;
import ir.servicea.app.PreferenceUtil;
import ir.servicea.model.ZarinVerify;
import ir.servicea.retrofit.Api;
import ir.servicea.retrofit.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    PreferenceUtil preferenceUtil;
    public static boolean profileFragment = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        G.Activity = this;
        G.context = this;
        preferenceUtil = new PreferenceUtil(this);
        preferenceUtil.cashFirstRun(false);
        // Check if the fragment_container is empty to avoid adding the fragment multiple times
        if (savedInstanceState == null) {
            FragmentMain fragmentMain = new FragmentMain();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.add(R.id.fragment_container, fragmentMain);
            fragmentTransaction.commit();
        }


        getZarinPallVerify();
        cheack_update();
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }

    public void onclickAlamrs(View v) {
        startActivity(new Intent(MainActivity.this, AlarmsActivity.class));
    }

    @Override
    public void onResume() {
        super.onResume();
        G.Activity = this;
        G.context = this;
        G.stop_loading();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (profileFragment) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (fragmentManager.getBackStackEntryCount() > 0) {
                fragmentManager.popBackStack();
                profileFragment=false;
            }
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            G.toast("برای خروج دوباره کلیک کنید");
            this.doubleBackToExitPressedOnce = true;
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }


    }


    public int temp_charge_remain = 0;

    public void changeCharge(int charge) {
//        G.loading(MainActivity.this);
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        JSONObject object = new JSONObject();
//        name, lastName, shop_name, shop_phone, phone, email, sex,
//                b_date, category, service, openTime, closeTime, numOfBranch, waiting, catering, province, city, address
        temp_charge_remain = G.preference.getInt("charge_remain", 0) + charge;
        try {
            object.put("charge_total", charge * 10);
            object.put("charge_remain", temp_charge_remain * 10);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String d_id = PreferenceUtil.getD_id();
        Call<ResponseBody> request = api.changeCharge(d_id, G.returnBody(object.toString()));
        request.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                String result = G.getResult(response);
                if (result.length() > 0 && result.length() < 10) {
                    G.toast("اطلاعات با موفقیت ثبت شد");
                    G.preference.edit().putInt("charge_total", charge).apply();
                    G.preference.edit().putInt("charge_remain", temp_charge_remain).apply();

                } else {
                    G.toast("مشکل در ثبت اطلاعات");
                }
                G.stop_loading();
                int package_id = G.preference.getInt("charge_package_id", 0);
                addChargingPackageLog(package_id, charge * 10);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                Toast.makeText(MainActivity.this, "مشکل در برقراری ارتباط", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addChargingPackageLog(int package_id, int amount) {
        String d_id = PreferenceUtil.getD_id();
        String created_at = G.converToEn(DateFormat.format("yyyy-MM-dd HH:mm:ss", new Date()).toString());
        JSONObject object = new JSONObject();
        try {
            object.put("service_center_id", d_id);
            object.put("charging_package_id", package_id);
            object.put("amount", amount);
            object.put("pay_type", 1);
            object.put("created_at", created_at);
            object.put("updated_at", created_at);
            object.put("deleted_at", JSONObject.NULL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        G.Log(object.toString());
        G.loading(this);
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.addChargingPackageLog(G.returnBody(object.toString()));
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                G.Log(call.request().toString() + "");
                G.Log(G.getResult(response) + "");
                G.stop_loading();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
            }
        });
    }

    public void cheack_update() {
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.check_update();
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        String result = G.getResult(response);
                        G.Log(result);
                        JSONObject object = G.StringtoJSONObject(result);
                        if (object.has("records")) {
                            JSONArray records = object.getJSONArray("records");
                            if (records.length() > 0) {
                                JSONObject obj = records.getJSONObject(0);
                                String file_dir = (obj.getString("file_dir") + "").replace("null", "");
                                String version = (obj.getString("version") + "").replace("null", "");
                                Boolean major = (obj.getBoolean("major"));
                                if (G.AndroidAppVersion.length() > 0 && !G.AndroidAppVersion.equals(version)) {
                                    dialogUpdate(file_dir, major);
                                }

                            }

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
            }
        });


    }

    public void dialogUpdate(String link, boolean major) {

        SweetAlertDialog s = new SweetAlertDialog(G.context, SweetAlertDialog.WARNING_TYPE)

                .setTitleText("بروزرسانی برنامه").setContentText("لطفا نسخه جدید برنامه را نصب کنید").setCancelText(" بستن ").setConfirmText(" دانلود ").showCancelButton(false)

                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismiss();
                        if (major) {
                            finish();
                        }
                    }
                })

                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismiss();

                        SweetAlertDialog pDialog = new SweetAlertDialog(G.context, SweetAlertDialog.PROGRESS_TYPE);
                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                        pDialog.setTitleText("لطفا صبر کنید");
                        pDialog.setCancelable(false);
                        pDialog.show();
                        openBrowser(link, pDialog);
//                        if (isStoragePermissionGranted()) {
//                            install();
//                        }
                    }
                });
        if (major) {
            s.setCancelable(false);
        } else {
            s.setCancelable(true);
        }
        s.show();

    }

    public void openBrowser(String url, SweetAlertDialog pDialog) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pDialog.dismiss();
            }
        }, 1000);

    }

    public void getZarinPallVerify() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(G.zarinPallBaseUrl).addConverterFactory(GsonConverterFactory.create()).build();

        SharedPreferences prefs = getSharedPreferences("AUTHORITY_PREFS_NAME", MODE_PRIVATE);
        String authority = prefs.getString("authority", "");
        int amount = prefs.getInt("amount", 0);
        Boolean verified = prefs.getBoolean("verified", false);

        Api apiService = retrofit.create(Api.class);
        ZarinVerify zarinVerify = new ZarinVerify();
        zarinVerify.setMerchant_id(G.MerchantID);
        zarinVerify.setAmount(amount);
        zarinVerify.setAuthority(authority);
        Call<ResponseBody> call = apiService.verifyZarinPall(Constants.reserve_service_accept, Constants.reserve_service_content, zarinVerify);
        if (!verified) {
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    String result = G.getResult(response);
        /*        if (result.equals("") && !verified) {
                    try {
                        result = G.getErrorResult(response);
                        JSONObject errorResponse = new JSONObject(result);
                        JSONObject errors = errorResponse.getJSONObject("errors");
                        int code = errors.getInt("code");
                        G.Log("zarin Error code = " + code);
                        G.saveAuthority(authority, amount, true);
                        startActivity(new Intent(MainActivity.this, ReservePaymentResultActivity.class).putExtra(Constants.reserve_payment_result, false));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                } else {*/
                    try {
                        JSONObject verifyResponse = new JSONObject(result);
                        JSONObject data = verifyResponse.getJSONObject("data");
                        String message = data.getString("message");
                        int code = data.getInt("code");

                        if (code == 100) {
                            if (message.equals("Paid")) {
                                G.saveAuthority(authority, amount, true);
                                changeCharge(G.preference.getInt("amount_charge", 0));
                                G.toast("پرداخت با موفقیت انجام شد");
                            }
                        } else {
                            G.toast("پرداخت انجام نشد");
                        }
                    } catch (JSONException e) {
                    }
                }

//            }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }
    }
}