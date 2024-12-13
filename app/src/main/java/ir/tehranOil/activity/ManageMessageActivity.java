package ir.tehranOil.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
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
import java.util.Date;
import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.tehranOil.app.G;
import ir.servicea.R;
import ir.tehranOil.retrofit.Api;
import ir.tehranOil.retrofit.RetrofitClient;
import ir.tehranOil.adapter.AdapterManageMessage;
import ir.tehranOil.model.ModelMM;
import ir.tehranOil.model.SliderItem;
import ir.tehranOil.app.PreferenceUtil;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class ManageMessageActivity extends AppCompatActivity {

    TextView txt_tile_action_bar;
    RecyclerView recycle;
    ImageView img_add_message, img_back;
    private SwipeRefreshLayout swipeRefreshLayout;
    private AdapterManageMessage adapter;
    public static JSONArray jsonSave = new JSONArray();

    private static final String TAG = "ManageMessageActivity";
public static TextView show_msg ;
    @Override
    public void onResume(){
        super.onResume();
        G.Activity = this;
        G.context = this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_message);
        G.Activity = this;
        G.context = this;
        FindView();
        onClick();
        txt_tile_action_bar.setText("مدیریت پیامک\u200Cbtn_saveها");
        txt_tile_action_bar.setTypeface(G.Bold);
        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.button));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                new Handler().postDelayed(() -> getServiceCenterMsgProv(), 200);
            }
        });


        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteServiceCenterMsgProv();
            }
        });

        getServiceCenterMsgProv();
    }

    public String idsServiceCenterMsgProv = "";

    public void getServiceCenterMsgProv() {
        idsServiceCenterMsgProv = "";
        swipeRefreshLayout.setRefreshing(true);

        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.getServiceCenterMsgProv("serv_center_id,eq," + d_id);
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                assert response.body() != null;
                Log.d("ManageMessageActivity", "onResponse: request:" + request.toString());
                try {
                    String result = response.body().string();
                    Log.d("ManageMessageActivity", "onResponse: result:" + result);

                    JSONObject objectx = G.StringtoJSONObject(result);
                    JSONArray records = objectx.getJSONArray("records");
                    if (records.length() > 0) {
                        for (int i = 0; i < records.length(); i++) {
                            JSONObject obj = records.getJSONObject(i);
                            JSONObject object = new JSONObject();
                            idsServiceCenterMsgProv += "" + obj.getString("id") + ",";
                            object.put("msg_title_id", obj.getInt("msg_title_id"));
                            object.put("msg_prov_id", obj.getInt("msg_prov_id"));
                            jsonSave.put(i, object);
                        }
                    }

                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
                idsServiceCenterMsgProv = idsServiceCenterMsgProv.replace(",,", "");
//                swipeRefreshLayout.setRefreshing(false);
                getManageMessage();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void deleteServiceCenterMsgProv() {

        G.loading(this);
        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.deleteServiceCenterMsgProv(idsServiceCenterMsgProv);
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                String result = G.getResult(response);

                if (result.length() > 0 && result.length() < 10) {

                }

                addServiceCenterMsgProv();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

    public int countxx = 0;

    public void addServiceCenterMsgProv() {
        String created_at = G.converToEn(DateFormat.format("yyyy-MM-dd HH:mm:ss", new Date()).toString());
        if (countxx < jsonSave.length()) {
            String d_id = PreferenceUtil.getD_id();
            G.loading(this);
            JSONObject object = new JSONObject();
            try {
                object.put("serv_center_id", d_id);
                object.put("msg_title_id", jsonSave.getJSONObject(countxx).getInt("msg_title_id") + "");
                object.put("msg_prov_id", jsonSave.getJSONObject(countxx).getInt("msg_prov_id") + "");
                object.put("created_at", created_at + "");
                object.put("updated_at", created_at + "");
                object.put("deleted_at", JSONObject.NULL);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            G.Log(object.toString());
            Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
            Call<ResponseBody> request = api.addServiceCenterMsgProv(G.returnBody(object.toString()));
            countxx++;
            Log.d(TAG, "addServiceCenterMsgProv:request "+ request.toString());
            Log.d(TAG, "addServiceCenterMsgProv:msg_title_id "+ object.toString() );

            request.enqueue(new retrofit2.Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    String result = G.getResult(response);
                    if (result.length() > 0 && result.length() < 10) {
                        addServiceCenterMsgProv();
                    } else {
                        G.stop_loading();
                        finish();
                    }
                }
                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    G.stop_loading();
                    G.toast("مشکل در برقراری ارتباط با سرور");
                }
            });
        } else {
            G.stop_loading();
            G.toast("ثبت انجام شد");
            finish();
        }
    }

    public void getManageMessage() {

        swipeRefreshLayout.setRefreshing(true);

        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.manageMessage();
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                assert response.body() != null;
                try {

                    String result = response.body().string();
                    JSONObject object = G.StringtoJSONObject(result);
                    JSONArray records = object.getJSONArray("records");
                    if (records.length() > 0) {
                        List<ModelMM> modelMMS = new ArrayList<>();

                        for (int i = 0; i < records.length(); i++) {
                            JSONObject obj = records.getJSONObject(i);
                            SliderItem sliderItem = new SliderItem();
                            int id = obj.getInt("id");
                            String title = obj.getString("name");
                            JSONArray msg_prov = new JSONArray();
                            if (obj.has("msg_prov")) {
                                msg_prov = obj.getJSONArray("msg_prov");

                                for (int j = msg_prov.length() - 1; j >= 0; j--) {

                                    JSONObject msgProvObject = (JSONObject) msg_prov.get(j);
                                    Log.d(TAG, "onResponse: getManageMessage:status:" + msgProvObject.getBoolean("status"));

                                    if(!msgProvObject.getBoolean("status")){
                                        msg_prov.remove(j);
                                        Log.d(TAG, "onResponse: getManageMessage:2:" + "deleted");

                                    }
                                }
                            }
                            modelMMS.add(new ModelMM(id, title, msg_prov));
                        }
                        recycle.setLayoutManager(new LinearLayoutManager(ManageMessageActivity.this, RecyclerView.VERTICAL, false));
                        adapter = new AdapterManageMessage(ManageMessageActivity.this, modelMMS);
                        recycle.setAdapter(adapter);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                show_msg.setVisibility(View.GONE);
                                show_msg.setText("");
                            }
                        },90);

                    } else {
                        G.toast("هیچ موردی یافت نشد!");
                    }
                } catch (JSONException | IOException e) {
                    G.toast("مشکل در تجزیه اطلاعات");
                    e.printStackTrace();
                }
                swipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

    private void FindView() {
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        recycle = findViewById(R.id.recycle_done_service_type);
        img_add_message = findViewById(R.id.img_add_message);
        img_back = findViewById(R.id.img_back);
        show_msg = findViewById(R.id.show_msg);
    }

    private void onClick() {

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void onclickAlamrs(View v) {
        startActivity(new Intent(ManageMessageActivity.this, AlarmsActivity.class));
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }
}