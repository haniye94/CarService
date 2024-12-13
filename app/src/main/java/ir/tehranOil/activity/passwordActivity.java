package ir.tehranOil.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import ir.tehranOil.app.G;

import java.io.IOException;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import ir.servicea.R;
import ir.tehranOil.retrofit.Api;
import ir.tehranOil.retrofit.RetrofitClient;
import ir.tehranOil.app.PreferenceUtil;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class passwordActivity extends AppCompatActivity {

    TextView txt_tile_action_bar;
    Button btn_menage_notif;
    ViewGroup divlastpass;
    AppCompatButton send;
    EditText password, password2, lastpassword;
    ImageView img_back;

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
        setContentView(R.layout.activity_password);
        G.Activity = this;
        G.context = this;
        FindView();
        onClick();
        txt_tile_action_bar.setText("رمز عبور");
        txt_tile_action_bar.setTypeface(G.Bold);
        if (G.preference.getString("PASSWORD", "").length() > 3) {
            divlastpass.setVisibility(View.VISIBLE);

        } else {
            divlastpass.setVisibility(View.GONE);
        }
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text_lastpassword = lastpassword.getText().toString();
                String text_password = password.getText().toString();
                String text_password2 = password2.getText().toString();
                String now_password = G.preference.getString("PASSWORD", "");

                if (now_password.length() > 3 && !text_lastpassword.equals(text_lastpassword)) {
                    G.toast("رمز عبور قبلی اشتباه است!");
                } else if (text_password.length()<=3) {
                    G.toast("رمز عبور جدید باید بیش از 3 کرکتر باشد!");
                }else if (!text_password.equals(text_password2)) {
                    G.toast("رمز عبور با تکرار آن تطابق ندارد!");
                }else {
                    G.preference.edit().putString("PASSWORD",text_password).apply();
                    G.toast("رمز عبور ثبت شد");
                    finish();
                }
            }
        });
    }

    public void onclickAlamrs(View v) {
        startActivity(new Intent(passwordActivity.this, AlarmsActivity.class));
    }

    public void onclickBack(View v) {
        finish();
    }

    public void sendSMSText(String phone, String text) {
        G.loading(this);
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        String d_id = PreferenceUtil.getD_id();
        api.sendSMSText(null,text + "", phone, d_id).enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                String result = "";
                try {
                    result = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (result.contains("The user does not have enough charge")) {
                    G.toast("پیامک ارسال نشد شارژ کافی ندارید");
                } else {
                    if (result.length() > 4 && result.length() < 30) {
                        G.stop_loading();
                        G.toast("پیامک ارسال شد.");
                        startActivity(new Intent(passwordActivity.this, MainActivity.class));
                        finishAffinity();
                    } else {
                        G.stop_loading();
                        G.toast("مشکل در ارسال پیامک");

                    }
                }
            }


            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط با سرور");
            }
        });

    }


    private void FindView() {
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        btn_menage_notif = findViewById(R.id.btn_menage_notif);
        img_back = findViewById(R.id.img_back);

        send = findViewById(R.id.save);

        lastpassword = findViewById(R.id.lastpassword);
        password = findViewById(R.id.password);
        password2 = findViewById(R.id.password2);
        divlastpass = findViewById(R.id.divlastpass);
    }

    private void onClick() {

    }

}