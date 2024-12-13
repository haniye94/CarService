package ir.tehranOil.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
import ir.servicea.R;
import ir.tehranOil.adapter.AdapterUserAccess;
import ir.tehranOil.app.G;
import ir.tehranOil.app.PreferenceUtil;
import ir.tehranOil.model.UserAccess;
import ir.tehranOil.retrofit.Api;
import ir.tehranOil.retrofit.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class UserAccessActivity extends AppCompatActivity {

    TextView txt_tile_action_bar;
    RecyclerView recycle;
    ImageView img_add_message, img_back;
    public static SwipeRefreshLayout swipeRefreshLayout;
    private AdapterUserAccess adapter;
    private Handler handler;
    private AlertDialog alertDialogs_offer_group;

    @Override
    public void onResume() {
        super.onResume();
        G.Activity = this;
        G.context = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_access);
        G.Activity = this;
        G.context = this;
        FindView();
        onClick();
        EditText search = findViewById(R.id.search);
        txt_tile_action_bar.setText("دسترسی کاربران");
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
                        getUserAccess("");
                    }
                }, 250);
            }
        });
        swipeRefreshLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swipeRefreshLayout.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        search.setText("");
                        getUserAccess("");
                    }
                }, 250);
            }
        });

        findViewById(R.id.img_add_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAddUserAccess(UserAccessActivity.this);
            }
        });
        getUserAccess("");

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
                            getUserAccess(search.getText().toString());
                        }
                    }, 750);

                } else if (s.length() == 0) {
                    getUserAccess("");
                }

            }
        });
        recycle.setLayoutManager(new LinearLayoutManager(UserAccessActivity.this, RecyclerView.VERTICAL, false));
        adapter = new AdapterUserAccess(UserAccessActivity.this, UserAccesses);
        recycle.setAdapter(adapter);
    }

    public void DialogAddUserAccess(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_add_user_access, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setCancelable(true);
        alertDialogs_offer_group = alertDialogBuilder.create();
        alertDialogs_offer_group.getWindow().setGravity(Gravity.CENTER_VERTICAL);
        alertDialogs_offer_group.setCancelable(false);
        WindowManager.LayoutParams layoutParams = alertDialogs_offer_group.getWindow().getAttributes();
        alertDialogs_offer_group.getWindow().setAttributes(layoutParams);
        alertDialogs_offer_group.getWindow().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.shap_simple_rec));
        alertDialogs_offer_group.show();
        ImageView img_close;
        EditText name, phone;
        Button btn_save;
        img_close = view.findViewById(R.id.img_close);
        name = view.findViewById(R.id.name);
        phone = view.findViewById(R.id.phone);
        btn_save = view.findViewById(R.id.btn_save);
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogs_offer_group.dismiss();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().length() < 3) {
                    name.setError("نام را به درستی وارد کنید");
                } else if (phone.getText().length() != 11) {
                    phone.setError("شماره موبایل را به درستی وارد کنید");
                } else {

                    adddUserAccess(name.getText().toString()+"", phone.getText().toString()+"");

                    alertDialogs_offer_group.dismiss();
                }
            }
        });

        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        width = (int) ((width) * ((double) 9 / 10));
        alertDialogs_offer_group.getWindow().setLayout(width, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public void adddUserAccess(String name, String phone) {
        String created_at = G.converToEn(DateFormat.format("yyyy-MM-dd HH:mm:ss", new Date()).toString());
        String d_id = PreferenceUtil.getD_id();
        String user_id = PreferenceUtil.getUser_id();
        G.loading(this);
        JSONObject object = new JSONObject();
        try {
            object.put("user_id", user_id);
            object.put("service_center_id", d_id);
            object.put("name", name);
            object.put("phone", phone);
            object.put("created_at", created_at + "");
            object.put("updated_at", created_at + "");
            object.put("deleted_at", JSONObject.NULL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        G.Log(object.toString());
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.addUserAccess(G.returnBody(object.toString()));
        Log.d(G.TAG, "adddUserAccess: " + request.request().toString());
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                String result = G.getResult(response);
                Log.d(G.TAG, "adddUserAccess: result:" + result);
                if (result.length() > 0 && result.length() < 10) {
                    G.stop_loading();
                    swipeRefreshLayout.performClick();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.d(G.TAG, "adddUserAccess: onFailure:" + t.getMessage().toString());
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط با سرور");
            }
        });

    }


    public List<UserAccess> UserAccesses = new ArrayList<>();

    public void getUserAccess(String key) {

        swipeRefreshLayout.setRefreshing(true);

        String d_id = PreferenceUtil.getD_id();
        String user_id = PreferenceUtil.getUser_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.getUserAccess("service_center_id,eq," + d_id, "user_id,eq," + user_id);
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                Log.e("sdsdsd", call.request().toString());
                assert response.body() != null;

                try {
                    String result = response.body().string();
                    JSONObject object = G.StringtoJSONObject(result);
                    JSONArray records = object.getJSONArray("records");
                    UserAccesses.clear();
                    if (records.length() > 0) {


                        for (int i = 0; i < records.length(); i++) {
                            JSONObject obj = records.getJSONObject(i);
                            long id = obj.getLong("id");
                            String phone = obj.getString("phone");
                            String name = obj.getString("name");
                            String created_at = obj.getString("created_at");
                            UserAccess userAccess = new UserAccess();
                            userAccess.setName(name + "");
                            userAccess.setPhone(phone + "");
                            userAccess.setCreated_at(created_at + "");
                            userAccess.setId(id);
                            UserAccesses.add(userAccess);

                        }


                    } else {
                        G.toast("هیچ موردی یافت نشد!");
                    }
                } catch (JSONException | IOException e) {
                    G.toast("مشکل در تجزیه اطلاعات");
                    e.printStackTrace();
                }
                swipeRefreshLayout.setRefreshing(false);
                adapter.notifyDataSetChanged();
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
        startActivity(new Intent(UserAccessActivity.this, AlarmsActivity.class));
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }
}