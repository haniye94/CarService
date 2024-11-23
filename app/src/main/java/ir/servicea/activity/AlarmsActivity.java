package ir.servicea.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import ir.servicea.adapter.AdapterTicket;
import ir.servicea.app.G;
import ir.servicea.app.PreferenceUtil;
import ir.servicea.model.Ticket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.servicea.R;

import ir.servicea.retrofit.Api;
import ir.servicea.retrofit.RetrofitClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class AlarmsActivity extends AppCompatActivity {


    TextView txt_tile_action_bar;
    RecyclerView recycle_notifications, recycle_tickets;
    private LinearLayout ly_empty_notification, ly_empty_ticket;
    ImageView img_add_message, img_back;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ViewGroup search_layout;
    private AdapterTicket adapter_notification, adapter_ticket;
    private Handler handler;
    private int notificationId = 0;
    public List<Ticket> notificationsList = new ArrayList<>();

    public List<Ticket> ticketsList = new ArrayList<>();

    public Button btn_tab1, btn_tab2;
    private ViewGroup tab1, tab2;

    private Boolean isNotification = true;
    Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);

    private static final String NOTIFICATION_ID = "notification_id";
    @Override
    public void onResume() {
        super.onResume();
        G.Activity = this;
        G.context = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarms);
        G.Activity = this;
        G.context = this;
        FindView();
        onClick();
        resetTab();
        btn_tab1.performClick();

        EditText search = findViewById(R.id.search);
        txt_tile_action_bar.setText("اعلان\u200Cهای سیستم");
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
                        if (isNotification) {
                            getNotificationsList("");
                        } else {
                            getManageMessage("");
                        }
                    }
                }, 250);
            }
        });

        handler = new Handler();
        search.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {


                    handler.removeCallbacksAndMessages(null);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getManageMessage(search.getText().toString());
                            if (isNotification) {
                                getNotificationsList(search.getText().toString());
                            } else {
                                getManageMessage(search.getText().toString());
                            }
                        }
                    }, 750);

                } else if (s.length() == 0) {
                    if (isNotification) {
                        getNotificationsList("");
                    } else {
                        getManageMessage("");
                    }
                }
            }
        });
        recycle_notifications.setLayoutManager(new LinearLayoutManager(AlarmsActivity.this, RecyclerView.VERTICAL, false));
        //adapter = new AdapterTicket(AlarmsActivity.this, modelMLS);
        adapter_notification = new AdapterTicket(AlarmsActivity.this, notificationsList);
        recycle_notifications.setAdapter(adapter_notification);

        recycle_tickets.setLayoutManager(new LinearLayoutManager(AlarmsActivity.this, RecyclerView.VERTICAL, false));
        //adapter = new AdapterTicket(AlarmsActivity.this, modelMLS);
        adapter_ticket = new AdapterTicket(AlarmsActivity.this, ticketsList);
        recycle_tickets.setAdapter(adapter_ticket);

        getNotificationsList("");
        getManageMessage("");

        Intent notificationIntent = getIntent();
        if (notificationIntent.hasExtra(NOTIFICATION_ID)) {
            notificationId = Integer.parseInt(notificationIntent.getStringExtra(NOTIFICATION_ID));
            G.Log("notificationId:" + notificationId);
        }
    }

    private void FindView() {
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        recycle_notifications = findViewById(R.id.recycle_notifications);
        recycle_tickets = findViewById(R.id.recycle_tickets);
        img_add_message = findViewById(R.id.img_add_message);
        img_back = findViewById(R.id.img_back);
        search_layout = findViewById(R.id.search_layout);
        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        btn_tab1 = findViewById(R.id.btn_tab1);
        btn_tab2 = findViewById(R.id.btn_tab2);
        ly_empty_notification = findViewById(R.id.ly_empty_notification);
        ly_empty_ticket = findViewById(R.id.ly_empty_ticket);
    }

    public void resetTab() {
        btn_tab1.setBackgroundResource(R.drawable.btn_tab);
        btn_tab2.setBackgroundResource(R.drawable.btn_tab);
        btn_tab1.setTextColor(Color.parseColor("#646464"));
        btn_tab2.setTextColor(Color.parseColor("#646464"));
        tab1.setVisibility(View.GONE);
        tab2.setVisibility(View.GONE);
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
            isNotification = true;
        });
        btn_tab2.setOnClickListener(view -> {
            resetTab();
            tab2.setVisibility(View.VISIBLE);
            btn_tab2.setBackgroundResource(R.drawable.btn_tab_active);
            btn_tab2.setTextColor(Color.parseColor("#ffffff"));
            isNotification = false;
        });
    }

    public void onclickAlamrs(View v) {

    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }

    public void getNotificationsList(String key) {
        G.loading(this);
        notificationsList = new ArrayList<>();
        String user_id = PreferenceUtil.getUser_id();
        Log.d("NotificationsList", "notificationsList: " + user_id);
        Call<ResponseBody> request = api.getNotificationsList(user_id, key);
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @SuppressLint({"StaticFieldLeak", "NotifyDataSetChanged"})
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                swipeRefreshLayout.setRefreshing(false);
                G.Log(call.request().toString());
                String result = G.getResult(response);
                G.Log(result);
                JSONArray array = G.StringtoJSONArray(result);
                //notificationsList.clear();
                //JSONArray array = G.ObjecttoArray(object, "records");
                if (array.length() > 0) {
                    ly_empty_notification.setVisibility(View.GONE);
                    try {
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject obj = array.getJSONObject(i);
                            Ticket notification = new Ticket();
                            notification.setId(obj.getInt("id"));
                            notification.setTitle(obj.getString("title"));
                            notification.setContent(obj.getString("content"));
//                          notification.setCreate_at(obj.getString("date_time"));
                            //notification.setDate_services(obj.getString("reserve_date_time").replace("00:00:00", "") + "");
                            notification.setCreate_at(obj.getString("date_time"));
                            notificationsList.add(notification);
                        }

                    } catch (JSONException e) {
                        G.stop_loading();
                        G.Log("Reserve:" + e.getMessage());
                        G.toast("مشکل در دریافت اطلاعات");
                        e.printStackTrace();
                    }
                } else{
                    ly_empty_notification.setVisibility(View.VISIBLE);
                }
                G.stop_loading();
                adapter_notification.swapData(notificationsList);
                //adapter_notification.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                G.stop_loading();
                Log.d("lastService", "updateServiceScoreState: mscs" + t.getMessage());
                G.toast("مشکل در برقراری ارتباط");
            }
        });
    }

    public void getManageMessage(String key) {

        swipeRefreshLayout.setRefreshing(true);

        String user_id = PreferenceUtil.getUser_id();
        Call<ResponseBody> request = api.getTicket("user_id,eq," + user_id, "title,cs," + key, "content,cs," + key, "id,desc");
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                Log.e("sdsdsd", call.request().toString());
                assert response.body() != null;

                try {
                    String result = response.body().string();
                    Log.e("sdsdsd:result", result);
                    JSONObject object = G.StringtoJSONObject(result);
                    JSONArray records = object.getJSONArray("records");
                    ticketsList.clear();
                    if (records.length() > 0) {
                        ly_empty_ticket.setVisibility(View.GONE);
                        for (int i = 0; i < records.length(); i++) {
                            JSONObject obj = records.getJSONObject(i);
                            int id = obj.getInt("id");
                            int user_id = obj.getInt("user_id");
                            String content = obj.getString("content");
                            String create_at = obj.getString("created_at");
                            String title = obj.getString("title");
                            ticketsList.add(new Ticket(id, user_id, content, create_at, title));
                        }
                    } else {
                        // G.toast("هیچ موردی یافت نشد!");
                        ly_empty_ticket.setVisibility(View.VISIBLE);

                    }
                } catch (JSONException | IOException e) {
                    G.stop_loading();
                    G.toast("مشکل در تجزیه اطلاعات");
                    e.printStackTrace();
                }
                G.stop_loading();
                swipeRefreshLayout.setRefreshing(false);
                adapter_ticket.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                swipeRefreshLayout.setRefreshing(false);
                G.toast("مشکل در برقراری ارتباط");
            }
        });
    }


}
