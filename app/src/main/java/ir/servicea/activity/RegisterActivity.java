package ir.servicea.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Pattern;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.servicea.app.DataBaseHelper;
import ir.servicea.R;
import ir.servicea.retrofit.Api;
import ir.servicea.retrofit.RetrofitClient;
import ir.servicea.app.G;
import ir.servicea.app.PreferenceUtil;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText edt_phone_number;
    Button btn_register;
    TextView text_login, txt_return_pass, txt_forget_pass, license, privacy;
    PreferenceUtil preferenceUtil;

    public void onclickAlamrs(View v) {
        startActivity(new Intent(RegisterActivity.this, AlarmsActivity.class));
    }

    @Override
    public void onResume() {
        super.onResume();
        G.Activity = this;
        G.context = this;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        G.Activity = this;
        G.context = this;
        setContentView(R.layout.activity_register);
        preferenceUtil = new PreferenceUtil(this);
        FindView();
        onClick();

        DataBaseHelper openHelper = new DataBaseHelper(this);
        openHelper.openDatabase();

//        if (copyDatabase(RegisterActivity.this)) {
//            Toast.makeText(RegisterActivity.this, "", Toast.LENGTH_SHORT).show();
//
//        }else {
//            Toast.makeText(RegisterActivity.this, "خطای دیتابیس", Toast.LENGTH_SHORT).show();
//
//        }

        edt_phone_number.setTypeface(G.Bold);
        text_login.setTypeface(G.Bold);
        txt_forget_pass.setTypeface(G.Bold);
        txt_return_pass.setTypeface(G.Bold);
        btn_register.setTypeface(G.Bold);
        license.setTypeface(G.Normal);

        PreferenceUtil.cashFirstRun(true);


        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, FAQActivity.class));
            }
        });
    }

    private void FindView() {
        edt_phone_number = findViewById(R.id.edt_phone_number);
        btn_register = findViewById(R.id.btn_register);
        text_login = findViewById(R.id.text_login);
        txt_return_pass = findViewById(R.id.txt_return_pass);
        txt_forget_pass = findViewById(R.id.txt_forget_pass);
        license = findViewById(R.id.license);
        privacy = findViewById(R.id.privacy);
    }

    private void onClick() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = Objects.requireNonNull(edt_phone_number.getText()).toString();
                if (!TextUtils.isEmpty(phone)) {
                    if (isValidMobile(phone)) {
                        sendCode(phone);

                    } else {
                        edt_phone_number.setError("شماره موبایل را به درستی وارد کنید");
                    }
                } else {
                    edt_phone_number.setError("شماره موبایل را به درستی وارد کنید");

                }

            }
        });
        edt_phone_number.setText(G.preference.getString("phoneAgain", ""));
        if (G.preference.getBoolean("sendAgain", false)) {
            edt_phone_number.setText(G.preference.getString("phoneAgain", ""));
            btn_register.performClick();
        }
        if (G.preference.getBoolean("changeNumber", false)) {
            edt_phone_number.setText(G.preference.getString("phoneAgain", ""));
        }
    }


    public void sendCode(String phone) {


        G.loading(RegisterActivity.this);
        int codesend = new Random().nextInt(999999 - 100000 + 1) + 100000;
        G.preference.edit().putInt("codesend", codesend).apply();

        if (G.debug) {
            Log.e("codesend", codesend + "");
            Log.e("codesend", codesend + "");
            G.toast(codesend + "");
        }
        Log.d("codesend", codesend + "");

        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        String phone_send = phone;
        if (G.debug) {
            phone_send = "09398116292";
        }

        api.sendCode(codesend + "", phone_send).enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                String result = "";
                try {
                    result = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (result.length() > 4 && result.length() < 30) {
                    G.stop_loading();
                    G.toast("پیامک تأیید ارسال شد.");
                    PreferenceUtil.cashPhone(phone);
                    Intent intent = new Intent(RegisterActivity.this, SendSmsActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    G.stop_loading();
                    G.toast("مشکل در ارسال کد");

                }
            }


            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.Log(call.request().toString());
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط با سرور");
            }
        });

    }

    private Boolean isValidMobile(String mobile_number) {
        //String MOBILE_NUM_PATTERN_ST = "(\\+98|0)?9\\d{9}" "^(\\+98|0)?9\\d{9}$";
        String MOBILE_NUM_PATTERN_ST = "^09\\d{9}$";
        // String MOBILE_NUM_PATTERN_ST = "(0|98)9(0[1-9]|[1 3]\\d|2[0-2]|98)\\d{7}";
        Pattern MOBILE_NUM_PATTERN_REX = Pattern.compile(MOBILE_NUM_PATTERN_ST);
        return MOBILE_NUM_PATTERN_REX.matcher(mobile_number).matches();
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }
}