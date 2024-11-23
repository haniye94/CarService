package ir.servicea.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.servicea.R;
import ir.servicea.adapter.AdapterLogMessage;
import ir.servicea.app.G;
import ir.servicea.app.PreferenceUtil;
import ir.servicea.model.ModelML;
import ir.servicea.retrofit.Api;
import ir.servicea.retrofit.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class MessageLogActivity extends AppCompatActivity {

    TextView txt_tile_action_bar;
    RecyclerView msg_recycle, msg_timing_recycle;
    ImageView img_add_message, img_back;
    private SwipeRefreshLayout swipeRefreshLayout;
    private AdapterLogMessage adapterMessages;
    private AdapterLogMessage adapterTimingMessages;
    private Handler handler;

    public Button btn_tab1, btn_tab2;
    private ViewGroup tab1, tab2;

    private Boolean isTimingMessages = false;


    @Override
    public void onResume() {
        super.onResume();
        G.Activity = this;
        G.context = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_message);
        G.Activity = this;
        G.context = this;
        FindView();
        onClick();
        EditText search = findViewById(R.id.search);
        txt_tile_action_bar.setText("گزارش پیامک\u200Cها");
        txt_tile_action_bar.setTypeface(G.Bold);
        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.button));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        search.setText("");
                        getAllMessages("");
                    }
                }, 250);
            }
        });
        getAllMessages("");

        handler = new Handler();
        search.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (s.length() > 0) {


                    handler.removeCallbacksAndMessages(null);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getAllMessages(search.getText().toString());
                        }
                    }, 750);

                } else if (s.length() == 0) {
                    getAllMessages("");
                }

            }
        });
        setRecyclerViews();
    }

    private void getAllMessages(String key) {
        if(isTimingMessages){
            getTimingMessage(key);
        }else{
            getManageMessage(key);
        }
    }

    private void setRecyclerViews() {
        msg_recycle.setLayoutManager(new LinearLayoutManager(MessageLogActivity.this, RecyclerView.VERTICAL, false));
        adapterMessages = new AdapterLogMessage(MessageLogActivity.this, messagesList, position -> {
            ModelML modelML = messagesList.get(position);
            sendSMSText(String.valueOf(modelML.getId()), modelML.getUser_phone(), modelML.getContent());
        });
        msg_recycle.setAdapter(adapterMessages);

        msg_timing_recycle.setLayoutManager(new LinearLayoutManager(MessageLogActivity.this, RecyclerView.VERTICAL, false));
        adapterTimingMessages = new AdapterLogMessage(MessageLogActivity.this, timingMessagesList, position -> {
            ModelML modelML = timingMessagesList.get(position);
            sendSMSText(String.valueOf(modelML.getId()), modelML.getUser_phone(), modelML.getContent());
        });
        msg_timing_recycle.setAdapter(adapterTimingMessages);
    }


    public void sendSMSText(String id, String phone, String text) {
        G.loading(this);
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        String d_id = PreferenceUtil.getD_id();
        api.sendSMSText(id,text + "", phone, d_id).enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                G.stop_loading();
                G.Log(call.request().toString());

                String result = "";
                try {
                    result = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                G.Log(result);
                if (result.contains("The user does not have enough charge")) {
                    G.toast("پیامک ارسال نشد شارژ کافی ندارید");
                } else {
                    if (result.length() > 4 && result.length() < 30) {

                        G.toast("پیامک ارسال شد.");
                        getAllMessages("");
                        /*startActivity(new Intent(SendMessageActivity.this, MainActivity.class));
                        finishAffinity();*/
                    } else {

                        G.toast("مشکل در ارسال پیامک");

                    }
                }
                G.stop_loading();
            }


            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط با سرور");
            }
        });

    }

    public List<ModelML> messagesList = new ArrayList<>();
    public List<ModelML> timingMessagesList = new ArrayList<>();

    public void getManageMessage(String key) {

        swipeRefreshLayout.setRefreshing(true);

        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.msg_log("service_center_id,eq," + d_id, "user_fullname,cs," + key, "user_mobile,cs," + key, "id,desc");
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                Log.e("sdsdsd", call.request().toString());
                assert response.body() != null;

                try {
                    String result = response.body().string();
                    JSONObject object = G.StringtoJSONObject(result);
                    JSONArray records = object.getJSONArray("records");
                    messagesList.clear();
                    if (records.length() > 0) {


                        for (int i = 0; i < records.length(); i++) {
                            JSONObject obj = records.getJSONObject(i);
                            int id = obj.getInt("id");
                            int user_id = obj.getInt("user_id");
                            String content = obj.getString("content");
                            String create_at = obj.getString("send_at");
                            String send_at = obj.getString("send_at");
                            Log.d("Sms", "onResponse: "+ send_at);
                            int char_count = obj.getInt("char_count");
                            int total_price = obj.getInt("total_price");
                            if (total_price > 10) {
                                total_price = total_price / 10;
                            }
                            String user_fullname = "";
                            String user_phone = "";
                            if (obj.has("user_fullname")) {
                                user_fullname = obj.getString("user_fullname");
                            }
                            if (obj.has("user_mobile")) {
                                user_phone = obj.getString("user_mobile");
                            }
                            messagesList.add(new ModelML(id, user_id, content, create_at, send_at, char_count, total_price, user_fullname, user_phone));

                        }


                    } else {
                        G.toast("هیچ موردی یافت نشد!");
                    }
                } catch (JSONException | IOException e) {
                    G.toast("مشکل در تجزیه اطلاعات");
                    e.printStackTrace();
                }
                swipeRefreshLayout.setRefreshing(false);
                adapterMessages.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

    public void getTimingMessage(String key) {

        swipeRefreshLayout.setRefreshing(true);

        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.msg_timing("service_center_id,eq," + d_id, "id,desc");
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                Log.e("timing", call.request().toString());
                assert response.body() != null;

                try {
                    String result = response.body().string();
                    Log.e("timing", "result:" + result);
                    JSONObject object = G.StringtoJSONObject(result);
                    JSONArray records = object.getJSONArray("records");
                    timingMessagesList.clear();
                    if (records.length() > 0) {


                        for (int i = 0; i < records.length(); i++) {
                            JSONObject obj = records.getJSONObject(i);
                            int id = obj.getInt("id");
                            JSONObject user_object = obj.getJSONObject("user_id");
                            int user_id = user_object.getInt("id");
                            String content = obj.getString("content");
                            String create_at = obj.getString("send_at");
                            String send_at = obj.getString("send_at");
                            Log.d("Sms", "onResponse: "+ send_at);
                            int char_count = 0;
                            int total_price = obj.getInt("total_price");
                            if (total_price > 10) {
                                total_price = total_price / 10;
                            }
                            String user_fullname = "";
                            String user_phone = "";
                            if (user_object.has("f_name")) {
                                user_fullname = user_object.getString("f_name").concat(" " +user_object.getString("l_name") );
                            }
                            if (user_object.has("mobile")) {
                                user_phone = user_object.getString("mobile");
                            }
                            timingMessagesList.add(new ModelML(id, user_id, content, create_at, send_at, char_count, total_price, user_fullname, user_phone));

                        }


                    } else {
                        G.toast("هیچ موردی یافت نشد!");
                    }
                } catch (JSONException | IOException e) {
                    Log.e("timing", "IOException:" + e.getMessage().toString());
                    G.toast("مشکل در تجزیه اطلاعات");
                    e.printStackTrace();
                }
                swipeRefreshLayout.setRefreshing(false);
                adapterTimingMessages.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.e("timing", "onFailure:" +t.getMessage().toString());
                swipeRefreshLayout.setRefreshing(false);
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

    private void FindView() {
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        msg_recycle = findViewById(R.id.recycle_messages);
        msg_timing_recycle = findViewById(R.id.recycle_timing_messages);
        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        btn_tab1 = findViewById(R.id.btn_tab1);
        btn_tab2 = findViewById(R.id.btn_tab2);
        img_add_message = findViewById(R.id.img_add_message);
        img_back = findViewById(R.id.img_back);
    }

    private void onClick() {

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_tab1.setOnClickListener(view -> {
            resetTab();
            tab1.setVisibility(View.VISIBLE);
            btn_tab1.setBackgroundResource(R.drawable.btn_tab_active);
            btn_tab1.setTextColor(Color.parseColor("#ffffff"));
            isTimingMessages = false;
            getAllMessages("");
        });
        btn_tab2.setOnClickListener(view -> {
            resetTab();
            tab2.setVisibility(View.VISIBLE);
            btn_tab2.setBackgroundResource(R.drawable.btn_tab_active);
            btn_tab2.setTextColor(Color.parseColor("#ffffff"));
            isTimingMessages = true;
            getAllMessages("");
        });
    }

    public void resetTab() {
        btn_tab1.setBackgroundResource(R.drawable.btn_tab);
        btn_tab2.setBackgroundResource(R.drawable.btn_tab);
        btn_tab1.setTextColor(Color.parseColor("#646464"));
        btn_tab2.setTextColor(Color.parseColor("#646464"));
        tab1.setVisibility(View.GONE);
        tab2.setVisibility(View.GONE);
    }

    public void onclickAlamrs(View v) {
        startActivity(new Intent(MessageLogActivity.this, AlarmsActivity.class));
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }
}