package ir.tehranOil.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ir.tehranOil.app.G;
import ir.tehranOil.retrofit.Api;
import ir.tehranOil.retrofit.RetrofitClient;
import ir.tehranOil.app.PreferenceUtil;

import java.io.IOException;
import java.util.Date;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import ir.servicea.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class IntroductionActivity extends AppCompatActivity {

    TextView txt_tile_action_bar;
    Button  btn_menage_notif;
    TextView count;
    AppCompatButton send;
    EditText phone;
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
        setContentView(R.layout.activity_introduction);
        G.Activity = this;
        G.context = this;
        FindView();
        onClick();
        txt_tile_action_bar.setText("معرفی به دوستان");
        txt_tile_action_bar.setTypeface(G.Bold);
        count.setText("تعداد ارسال شده: "+0);
        listIntroduce();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phones = phone.getText().toString();
                if(phones.length()==11){
                    String text = "همکار عزیز، با استفاده از اپلیکیشن سرویسا میتونی سوابق خودرو مشتری رو ذخیره کنی و موعد سرویس دورهای رو بهش یادآوری کنی\n" +
                            "https://b2n.ir/q17824";
                    Uri uriSms = Uri.parse("smsto:"+phones);
                    Intent intentSMS = new Intent(Intent.ACTION_SENDTO, uriSms);
                    intentSMS.putExtra("sms_body", text);
                    startActivity(intentSMS);
                    G.preference.edit().putInt("count_introduction",G.preference.getInt("count_introduction",0)+1).apply();
                }else {
                    G.toast("شماره موبایل را به درستی وارد کنید");
                }
            }
        });
    }
    public void onclickAlamrs(View v) {
        startActivity(new Intent(IntroductionActivity.this, AlarmsActivity.class));
    }

    public void onclickBack(View v) {
        finish();
    }
    public void sendSMSText(String phone,String text) {
        G.loading(this);
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        String d_id = PreferenceUtil.getD_id();
        api.sendSMSText(null,text + "", phone,d_id).enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                String result = "";
                try {
                    result = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(result.contains("The user does not have enough charge")){
                    G.stop_loading();
                    G.toast("پیامک ارسال نشد شارژ کافی ندارید");
                    addIntroduce(phone);
                }else {
                    if (result.length() > 4 && result.length() < 30) {
                       addIntroduce(phone);
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
    public void addIntroduce(String num_reciver) {

            String user_id = PreferenceUtil.getUser_id();
            G.loading(this);

            String created_at = G.converToEn(DateFormat.format("yyyy-MM-dd HH:mm:ss", new Date()).toString());

            JSONObject object = new JSONObject();
            try {
                if(user_id.length()>0){
                    int user_idx = Integer.parseInt(user_id);
                    object.put("user_id", user_idx);
                }else {
                    object.put("user_id", 1);
                }

                object.put("num_reciver", num_reciver);
                object.put("install_app", 0);
                object.put("install_date", created_at);
                object.put("app_type", G.UserType);
                object.put("created_at", created_at);
                object.put("updated_at", created_at);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
            Call<ResponseBody> request = api.addIntroduce(G.returnBody(object.toString()));

            request.enqueue(new retrofit2.Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    String result = G.getResult(response);
                    G.Log(object.toString());
                    G.Log(call.request().toString());
                    G.Log(result);
                    G.stop_loading();
                    G.toast("پیامک ارسال شد.");
                    startActivity(new Intent(IntroductionActivity.this, MainActivity.class));
                    finishAffinity();

                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    G.stop_loading();
                    G.toast("مشکل در برقراری ارتباط با سرور");
                }
            });


    }
    public void listIntroduce() {
        G.loading(this);
        String user_id = PreferenceUtil.getUser_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.listIntroduce("user_id,eq," + user_id);
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                try {

                    String result = G.getResult(response);
                    JSONObject object = G.StringtoJSONObject(result);
                    if(object.has("records")) {
                        JSONArray records = object.getJSONArray("records");
                        count.setText("تعداد ارسال شده: " + records.length());
                    }
                } catch (JSONException e) {
                    G.toast("مشکل در تجزیه اطلاعات");
                    e.printStackTrace();
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
    private void FindView() {
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        btn_menage_notif = findViewById(R.id.btn_menage_notif);
        img_back = findViewById(R.id.img_back);

        send = findViewById(R.id.send);
        count = findViewById(R.id.count);
        phone = findViewById(R.id.phone);
    }

    private void onClick() {

    }

}