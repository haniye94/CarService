package ir.tehranOil.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.servicea.R;
import ir.tehranOil.retrofit.Api;
import ir.tehranOil.retrofit.RetrofitClient;
import ir.tehranOil.app.G;
import ir.tehranOil.app.PreferenceUtil;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class SupportActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    public void onResume(){
        super.onResume();
        G.Activity = this;
        G.context = this;
    }
    public void onclickAlamrs(View v) {
        startActivity(new Intent(SupportActivity.this, AlarmsActivity.class));
    }

    public void onclickBack(View v) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        G.Activity = this;
        G.context = this;
        TextView txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        txt_tile_action_bar.setText("پشتیبانی");
        txt_tile_action_bar.setTypeface(G.Bold);
        Button send = findViewById(R.id.send);
        final EditText text = findViewById(R.id.text);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text.getText().toString().length() < 5) {
                    text.requestFocus();
                    text.setError("متن پیام باید بیش از 5 کاراکتر باشد");
                } else {
//                    final ArrayList<NameValuePair> param = new ArrayList<>();
//                    param.add(new BasicNameValuePair("phone", "" + G.preferences.getString("PHONE", "")));
//                    param.add(new BasicNameValuePair("text", "" + text.getText().toString()));
                    if (isOnline()) {
                        G.loading(SupportActivity.this);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                G.stop_loading();
                                sendSupport(text.getText().toString());
                            }
                        }, 2500);
                    } else
                        G.toast("اینترنت متصل نیست");
                }
            }
        });

        findViewById(R.id.faq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                G.preference.edit().putString("textActivity", "faq").apply();
                startActivity(new Intent(SupportActivity.this, TextActivity.class));
            }
        });
        findViewById(R.id.call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "09184455585"));
                startActivity(intent);
            }
        });
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @SuppressLint("StaticFieldLeak")
    public void sendSupport(String text) {
        G.loading(this);
        String created_at =G.converToEn(DateFormat.format("yyyy-MM-dd HH:mm:ss", new Date()).toString());
        JSONObject object = new JSONObject();
        try {

            object.put("user_id",  PreferenceUtil.getUser_id()+"");
            object.put("role_id", G.RoleId+"");
            object.put("title", "پشتیبانی اپلیکیشن");
            object.put("content", text+"");
            object.put("image_dir", null);
            object.put("file_dir", null);
            object.put("forward", null);
            object.put("status", 0);
            object.put("created_at", created_at);
            object.put("updated_at", created_at);
            object.put("deleted_at", null);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        G.loading(this);
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.addTicket(G.returnBody(object.toString()));
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                G.stop_loading();
                if (G.getResult(response).length() > 0) {

                    SweetAlertDialog S = new SweetAlertDialog(SupportActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("ارسال شد")
                            .setContentText("از همراهی شما متشکریم!")

                            .setConfirmText("بستن")
                            .showCancelButton(false)
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismiss();
                                    finish();

                                }
                            })
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismiss();
                                    finish();
                                }
                            });

                    S.show();
                } else {
                    G.toast("مشکل در ثبت!");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط با سرور");
            }
        });

    }
}
