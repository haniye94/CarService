package ir.servicea.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.servicea.R;
import ir.servicea.app.G;
import ir.servicea.app.PreferenceUtil;
import ir.servicea.retrofit.Api;
import ir.servicea.retrofit.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivateActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }
    @Override
    public void onResume(){
        super.onResume();
        G.Activity = this;
        G.context = this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate);
        G.Activity = this;
        G.context = this;
        G.preference.edit().putBoolean("ActivateRad", false).apply();
        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(ActivateActivity.this, MainActivity.class);
                startActivity(intent);*/
                finishAffinity();
            }
        });
        findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(ActivateActivity.this, MainActivity.class);
                startActivity(intent);*/
                finishAffinity();
            }
        });
        findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivateActivity.this, LoginInfoActivity.class);
                intent.putExtra("editPro", "");
                startActivity(intent);
                finish();
            }
        });
        PreferenceUtil.cashPhone(G.preference.getString("phoneAgain", ""));
        if (PreferenceUtil.getUser_id().length() <= 0) {
            sendOperatorInfo(G.preference.getString("phoneAgain", ""));
        }

    }

    @Override
    public void onBackPressed() {
    }


    public void sendOperatorInfo(String phone) {
        G.loading(ActivateActivity.this);
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        JSONObject object = new JSONObject();


        try {

            String created_at = G.converToEn(DateFormat.format("yyyy-MM-dd HH:mm:ss", new Date()).toString());
            created_at = G.converToEn(created_at);
            object.put("username", "U" + phone);
            object.put("f_name", "");
            object.put("l_name", "");
            object.put("mobile", phone);
            object.put("user_type", G.UserType);
            object.put("role_id", G.RoleId);
            object.put("province_id", 8);
            object.put("city_id", 118);
            object.put("created_at", "" + created_at);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        G.Log(object.toString());
        Call<ResponseBody> request = api.newOperator(G.returnBody(object.toString()));
        request.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                G.Log(call.request().toString());
                String result = G.getResult(response);
                G.Log(result);
                if (result.length() > 0 && result.length() < 10) {
                    PreferenceUtil.cashUser_id(result);
                    PreferenceUtil.cachLogin();
                }
                G.stop_loading();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
            }
        });

    }

}