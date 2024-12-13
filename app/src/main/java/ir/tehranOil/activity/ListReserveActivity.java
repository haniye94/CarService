package ir.tehranOil.activity;

import static ir.tehranOil.activity.ListReserveActivity.RESERVE_TYPE.*;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import ir.tehranOil.adapter.AdapterListServices;
import ir.tehranOil.app.CalendarTool;
import ir.tehranOil.app.G;
import ir.tehranOil.app.PreferenceUtil;
import ir.tehranOil.model.ModelServicesCustomer;
import ir.tehranOil.retrofit.Api;
import ir.tehranOil.retrofit.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.servicea.R;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class ListReserveActivity extends AppCompatActivity {
    private TextView txt_tile_action_bar, txt_empty_text;
    private ImageView img_back;

    private Button btn_tab1, btn_tab2, btn_tab3, btn_tab4;


    private ViewGroup ly_empty_notification;
    private SwipeRefreshLayout swipeRefreshLayout;

    private AdapterListServices adapterListService;

    private RecyclerView recycle_services;

    private List<ModelServicesCustomer> doingReserve = new ArrayList<>();
    private List<ModelServicesCustomer> doneReserve = new ArrayList<>();
    private List<ModelServicesCustomer> canceledReserve = new ArrayList<>();
    private List<ModelServicesCustomer> expiredReserve = new ArrayList<>();

    private final Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
    private AdapterListServices.OnItemClickListener onItemClickService;

    enum RESERVE_TYPE {
        DOING, EXPIRED, DONE, CANCELED

    }

    private RESERVE_TYPE reserve_type = DOING;

    private static final String TAG = "ListReserveActivity";

    public void onclickAlamrs(View v) {
        startActivity(new Intent(ListReserveActivity.this, AlarmsActivity.class));
    }

    @Override
    public void onResume() {
        super.onResume();
        G.Activity = this;
        G.context = this;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reserve);
        G.Activity = this;
        G.context = this;
        FindView();
        onClick();
        txt_tile_action_bar.setText("سرویس های رزرو شده");
        txt_tile_action_bar.setTypeface(G.Bold);
        onItemClickService = new AdapterListServices.OnItemClickListener() {
            @Override
            public void onItemClick(ModelServicesCustomer model, ImageView item, AdapterListServices.ViewHolder holder, int position, boolean isFromMenu) {
                showServiceInformationActivity(model, position);
            }
        };
        recycle_services.setLayoutManager(new LinearLayoutManager(ListReserveActivity.this, RecyclerView.VERTICAL, false));
        adapterListService = new AdapterListServices(ListReserveActivity.this, ListReserveActivity.this, doingReserve, onItemClickService);
        recycle_services.setAdapter(adapterListService);
        listReserve();
    }


    private void FindView() {
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        img_back = findViewById(R.id.img_back);
        recycle_services = findViewById(R.id.recycle_reserve);
        btn_tab1 = findViewById(R.id.btn_tab1);
        btn_tab2 = findViewById(R.id.btn_tab2);
        btn_tab3 = findViewById(R.id.btn_tab3);
        btn_tab4 = findViewById(R.id.btn_tab4);
        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.button));
        ly_empty_notification = findViewById(R.id.ly_empty_notification);
        txt_empty_text = findViewById(R.id.txt_empty_text);
        txt_empty_text.setTypeface(G.Normal);
    }

    private void onClick() {
        img_back.setOnClickListener(view -> finish());
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            new Handler().postDelayed(() -> listReserve(), 250);
        });

        btn_tab1.setOnClickListener(view -> {
            resetTab();
            btn_tab1.setBackgroundResource(R.drawable.btn_tab_active);
            btn_tab1.setTextColor(Color.parseColor("#ffffff"));
            adapterListService.swapList(doingReserve);
            reserve_type = DOING;
            showEmptyView(reserve_type, doingReserve);

        });
        btn_tab2.setOnClickListener(view -> {
            resetTab();
            btn_tab2.setBackgroundResource(R.drawable.btn_tab_active);
            btn_tab2.setTextColor(Color.parseColor("#ffffff"));
            adapterListService.swapList(doneReserve);
            reserve_type = RESERVE_TYPE.DONE;
            showEmptyView(reserve_type, doneReserve);
        });
        btn_tab3.setOnClickListener(view -> {
            resetTab();
            btn_tab3.setBackgroundResource(R.drawable.btn_tab_active);
            btn_tab3.setTextColor(Color.parseColor("#ffffff"));
            adapterListService.swapList(canceledReserve);
            reserve_type = RESERVE_TYPE.CANCELED;
            showEmptyView(reserve_type, canceledReserve);
        });
        btn_tab4.setOnClickListener(view -> {
            resetTab();
            btn_tab4.setBackgroundResource(R.drawable.btn_tab_active);
            btn_tab4.setTextColor(Color.parseColor("#ffffff"));
            adapterListService.swapList(expiredReserve);
            reserve_type = RESERVE_TYPE.EXPIRED;
            showEmptyView(reserve_type, expiredReserve);
        });
    }

    public void resetTab() {
        btn_tab1.setBackgroundResource(R.drawable.btn_tab);
        btn_tab2.setBackgroundResource(R.drawable.btn_tab);
        btn_tab3.setBackgroundResource(R.drawable.btn_tab);
        btn_tab4.setBackgroundResource(R.drawable.btn_tab);
        btn_tab1.setTextColor(Color.parseColor("#646464"));
        btn_tab2.setTextColor(Color.parseColor("#646464"));
        btn_tab3.setTextColor(Color.parseColor("#646464"));
        btn_tab4.setTextColor(Color.parseColor("#646464"));
    }

    public void listReserve() {
        G.loading(this);
        clearReservedList();

        String user_id = PreferenceUtil.getUser_id();
        Log.d("ReserveList", "listReserve: " + user_id);
        Call<ResponseBody> request = api.getReservedServices(user_id);
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @SuppressLint({"StaticFieldLeak", "NotifyDataSetChanged"})
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                swipeRefreshLayout.setRefreshing(false);
                G.Log(call.request().toString());
                Log.d(TAG, "onResponse:request: " + call.request());
                String result = G.getResult(response);
                Log.d(TAG, "onResponse:result: " + result);
                G.Log(result);
                JSONArray array = G.StringtoJSONArray(result);
                //JSONArray array = G.ObjecttoArray(object, "records");
                String currentTime = G.converToEn(DateFormat.format("yyyy-MM-dd HH:mm:ss", new Date()).toString());

                if (array.length() > 0) {
                    try {
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject obj = array.getJSONObject(i);
                            ModelServicesCustomer msc = new ModelServicesCustomer();
                            msc.setId(obj.getInt("id"));
                            msc.setFirst_name(obj.getString("f_name"));
                            msc.setLast_name(obj.getString("l_name"));
                            msc.setPhone(obj.getString("mobile"));
                            msc.setCar_id(obj.getInt("customer_car_id"));
                            //  String car_company_name = (obj.getString("car_company_name") + "").replace("null", "");
                            String car_tip = (obj.getString("car_tip")).replace("null", "");
                            String car_name = obj.getString("car_name");
                            String car_company = obj.getString("car_company");
                            msc.setName_car(car_company + "-" + car_name + "-" + car_tip);
                            msc.setCenter_name(obj.getString("center_name"));
                            msc.setDetail_service(obj.getString("detail_service"));

                            int car_name_id = 0, car_tip_id = 0, car_model_id = 0, fuel_type_id = 0;
                           /* if (obj.has("car_name_id")) {
                                car_name_id = obj.getInt("car_name_id");
                                if (!(obj.getString("car_tip_id") + "").contains("null")) {
                                    car_tip_id = obj.getInt("car_tip_id");
                                }
//                                if(!obj.getString("car_model_id").contains("null")) {
                                car_model_id = obj.getInt("car_model_id");
//                                }

                                fuel_type_id = obj.getInt("fuel_type_id");
                            }*/
                           /* msc.setCar_name_id(car_name_id);
                            msc.setCar_tip_id(car_tip_id);
                            msc.setCar_model_id(car_model_id);*/
                            msc.setId_customer(obj.getInt("user_id"));
                            msc.setPlak(obj.getString("car_plate"));

                            msc.setDate_services(obj.getString("reserve_date_time").replace("00:00:00", ""));
                            String date = msc.getDate_services();
                            if (date.contains("-") && date.contains(":") && date.contains(" ")) {
                                CalendarTool calendarTool = new CalendarTool();
                                String[] date_time = date.split(" ");
                                String[] dates = date_time[0].split("-");
                                calendarTool.setGregorianDate(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
                                if (date_time.length > 2) {
                                    date = calendarTool.getIranianDate();
                                    msc.setTime_services(date_time[1] + " الی " + date_time[2]);
                                } else {
                                    date = calendarTool.getIranianDate() + " " + date_time[1];
                                }
                                msc.setDate_services(date);

                            } else if (date.contains("-")) {
                                date = date.replace(" ", "");
                                CalendarTool calendarTool = new CalendarTool();
                                String[] dates = date.split("-");
                                calendarTool.setGregorianDate(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
                                date = calendarTool.getIranianDate();
                                msc.setDate_services(date);
                            }

                            msc.setDescription((obj.getString("description")).replace("null", ""));
                            msc.setAll_services_price(obj.getString("prepayment_amount"));
                            msc.setCenter_id((obj.getString("service_center_id")).replace("null", ""));
                            //  msc.setCenter_id((obj.getString("center_id") + ""));
                            msc.setCenter_name((obj.getString("center_name")));
                            msc.setJob_category_id(obj.getString("job_category_id").replace("null", "0"));
                            msc.setKm_now("");
                            msc.setKm_next("");
                            String deleted_at = obj.getString("deleted_at");
                            String status = obj.getString("status");
                            String reserve_date_time = obj.getString("reserve_date_time");
                            String reserve_time = reserve_date_time.substring(0, reserve_date_time.lastIndexOf(" "));
                            Log.d("Reserve:", "reserve_time:" + reserve_time);
                            Log.d("Reserve:", "currentTime:" + currentTime);
                            if (deleted_at.equals("null") && status.equals("2")) {
                                doneReserve.add(msc);
                            }else if ((reserve_time.compareTo(currentTime) < 0) && deleted_at.equals("null")) {
                                expiredReserve.add(msc);
                            } else if (deleted_at.equals("null") && status.equals("1")) {
                                doingReserve.add(msc);
                            } else if (!deleted_at.equals("null")) {
                                canceledReserve.add(msc);
                            }
                        }

                    } catch (JSONException e) {
                        G.stop_loading();
                        G.Log("Reserve:" + e.getMessage());
                        Log.d("Reserve:", e.getMessage());
                        G.toast("مشکل در دریافت اطلاعات");
                        e.printStackTrace();
                    }
                }
                G.stop_loading();
                updateCurrentTab();
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

    private void clearReservedList() {
        doneReserve = new ArrayList<>();
        doingReserve = new ArrayList<>();
        canceledReserve = new ArrayList<>();
        expiredReserve = new ArrayList<>();
    }

    private void updateCurrentTab() {
        switch (reserve_type) {
            case DOING:
                adapterListService.swapList(doingReserve);
                showEmptyView(DOING, doingReserve);
                break;
            case DONE:
                adapterListService.swapList(doneReserve);
                showEmptyView(DONE, doneReserve);
                break;
            case CANCELED:
                adapterListService.swapList(canceledReserve);
                showEmptyView(CANCELED,canceledReserve);
                break;
            case EXPIRED:
                adapterListService.swapList(expiredReserve);
                showEmptyView(EXPIRED, expiredReserve);
                break;
        }
    }

    private void showEmptyView(RESERVE_TYPE reserve_type, List<ModelServicesCustomer> reserve_list) {
        if(reserve_list.isEmpty()) {
            String empty_text = getString(R.string.doing_reserve);
            switch (reserve_type) {
                case DOING:
                    empty_text = getString(R.string.doing_reserve);
                    break;
                case DONE:
                    empty_text = getString(R.string.done_reserve);
                    break;
                case CANCELED:
                    empty_text = getString(R.string.canceled_reserve);
                    break;
                case EXPIRED:
                    empty_text = getString(R.string.expired_reserve);
                    break;
            }
            hideRecyclerView();
            txt_empty_text.setText(getResources().getString(R.string.empty_view_text, empty_text));
        }else{
            showRecyclerView();
        }
    }

    private void showRecyclerView() {
        recycle_services.setVisibility(View.VISIBLE);
        ly_empty_notification.setVisibility(View.GONE);
    }

    private void hideRecyclerView() {
        recycle_services.setVisibility(View.GONE);
        ly_empty_notification.setVisibility(View.VISIBLE);
    }

    private void showServiceInformationActivity(ModelServicesCustomer model, int position) {
        Intent intent = new Intent(ListReserveActivity.this, InformationServiceCarActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("is_reserve_list", true);
        Log.d(TAG, "onCreate: is_reserve_list1:" + intent.getBooleanExtra("is_reserve_list", false));
        intent.putExtra("idCustomer", String.valueOf(model.getId_customer()));
        Log.e("idCustomer:", String.valueOf(model.getId_customer()));
        Log.e("idservice:", String.valueOf(model.getId()));
        intent.putExtra("id_car", String.valueOf(model.getCar_id()));
        intent.putExtra("id", String.valueOf(model.getId()));
        //  intent.putExtra("idservice", model.getId() + "");
        //  intent.putExtra("id_service", model.getId() + "");
        intent.putExtra("firstName", model.getFirst_name());
        intent.putExtra("lastName", model.getLast_name());
        intent.putExtra("phone", model.getPhone());
        intent.putExtra("plak", model.getPlak());
        intent.putExtra("km_now", model.getKm_now());
        intent.putExtra("nameCar", model.getName_car());
        intent.putExtra("km_next", model.getKm_next());
        intent.putExtra("avg_function", model.getAvg_function());
        intent.putExtra("description", model.getDescription());
        intent.putExtra("date_service", model.getDate_services());
        intent.putExtra("time_service", model.getTime_services());
        intent.putExtra("services_price", model.getAll_services_price());
        intent.putExtra("detail_service", model.getDetail_service());
        Log.e("detail_service:", model.getDetail_service());
       /* intent.putExtra("car_name_id", model.getCar_name_id());
        intent.putExtra("car_tip_id", model.getCar_tip_id());
        intent.putExtra("car_model_id", model.getCar_model_id());
        intent.putExtra("fuel_type_id", model.getFuel_type_id());*/
        intent.putExtra("center_id", model.getCenter_id());
        intent.putExtra("center_name", model.getCenter_name());
        //intent.putExtra("rate_saved", model.getRate_state());
        startActivity(intent);
    }

}

