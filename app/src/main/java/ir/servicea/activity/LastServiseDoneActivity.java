package ir.servicea.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.servicea.app.CalendarTool;
import ir.servicea.app.Constants;
import ir.servicea.app.DataBaseHelper;
import ir.servicea.app.G;
import ir.servicea.R;
import ir.servicea.app.PlakTextWatcher;
import ir.servicea.retrofit.Api;
import ir.servicea.retrofit.RetrofitClient;
import ir.servicea.adapter.AdapterListServices;
import ir.servicea.model.ModelServicesCustomer;
import ir.servicea.app.PreferenceUtil;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class LastServiseDoneActivity extends AppCompatActivity {
    TextView txt_tile_action_bar;
    RecyclerView recycle_services;
    AdapterListServices adapterListService;
    EditText edt1, edt2, edt3, edt4, edt5, edt6, edt7, edt8;
    List<ModelServicesCustomer> listCustomers = new ArrayList<>();
    TextView txt_type_search, send_service_sms_txt;
    TextInputLayout edt_name, edt_phone;
    LinearLayout ly_plak, ly_empty, ly_search;
    AlertDialog alertDialogs_detect_type_search;
    ImageView img_back, img_search_service;
    DataBaseHelper mDBHelper;
    private SQLiteDatabase mDatabase;
    TextInputEditText edt_phone_number, edt_nameFamily;
    private AdapterListServices.OnItemClickListener onItemClickService;
    int idCustomer;
    private String carId = "0";
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean keydel = false;
    private ProgressBar loadingPB;

    public int page = 1;
    boolean isLoading = false;

    boolean fromCustomerActivity = false;

    private String last_filter = "";
    private String last_key = "";

    private ViewGroup ly_plk_general,ly_plk_azad_new,ly_plk_azad_old, ly_pelak_type;

    private Constants.PLAK_TYPE plak_type = Constants.PLAK_TYPE.PLAK_GENERAL;
    private ViewGroup plak_layout;

    private TextView tv_plk_type_menu, tv_plk_type_general, tv_plk_type_azad_new, tv_plk_type_azad_old;

    private boolean isVisiblePlakLayout = false;

    View v_search_separator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_servise_done);
        G.Activity = this;
        G.context = this;
        FindView();
        onClick();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                new Handler().postDelayed(() -> listService(last_filter, last_key), 200);
            }
        });
        mDBHelper = new DataBaseHelper(this);
        mDatabase = mDBHelper.getReadableDatabase();

        txt_tile_action_bar.setText("لیست سرویس\u200Cها");
        txt_tile_action_bar.setTypeface(G.Bold);
        PlakListener();

        onItemClickService = new AdapterListServices.OnItemClickListener() {
            @Override
            public void onItemClick(ModelServicesCustomer model, ImageView item, AdapterListServices.ViewHolder holder, int position, boolean isMenuClicked ) {

                if(isMenuClicked){
                    showBottomSheetDialog(model, holder, position);
                }else{
                    showServiceInfoActivity(position, model);
                }
            }
        };
        G.services.clear();
        G.mscs.clear();

        recycle_services.setLayoutManager(new LinearLayoutManager(LastServiseDoneActivity.this, RecyclerView.VERTICAL, false));
        adapterListService = new AdapterListServices(LastServiseDoneActivity.this, LastServiseDoneActivity.this, G.mscs, onItemClickService);
        recycle_services.setAdapter(adapterListService);
        if (getIntent().getExtras().getInt("idCustomer") > 0) {
            fromCustomerActivity = true;
            carId = String.valueOf(getIntent().getIntExtra("idCar", 0));
            Log.d("LastServiceDone", "onCreate: " + carId);
            new Handler().postDelayed(() -> listService("customer_car_id",String.valueOf(getIntent().getExtras().getInt("idCar"))), 200);
        } else {
            new Handler().postDelayed(() -> listService("", ""), 200);
        }
        recycle_services.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading && G.mscs.size() >= 20) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == G.mscs.size() - 1) {
                        page++;
                        loadingPB.setVisibility(View.VISIBLE);
                        recycle_services.post(new Runnable() {
                            @Override
                            public void run() {
                                recyclerView.smoothScrollToPosition(adapterListService.getItemCount() - 1);
                            }
                        });
                        isLoading = true;
                        listService(last_filter, last_key);
                    }
                }
            }
        });
        if(fromCustomerActivity){
            hideSearchBar();
        }
        getServiceCenterMsgProv();
    }

    private void hideSearchBar() {
        ly_search.setVisibility(View.GONE);
    }

    private void showBottomSheetDialog(ModelServicesCustomer model, AdapterListServices.ViewHolder holder, int position) {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(LastServiseDoneActivity.this, R.style.BottomSheetDialogTheme);
        View bottomSheetView = LayoutInflater.from(LastServiseDoneActivity.this).inflate(R.layout.layout_button_sheet_service, (LinearLayout) holder.itemView.findViewById(R.id.ly_bottom_sheet_lang));
        bottomSheetDialog.setCancelable(true);
        LinearLayout ly_show_info, ly_edit_service, ly_delete_service, ly_send_sms_service;
        bottomSheetDialog.setContentView(bottomSheetView);
        ly_show_info = bottomSheetDialog.findViewById(R.id.ly_show_info);
        ly_edit_service = bottomSheetDialog.findViewById(R.id.ly_edit_service);
        ly_delete_service = bottomSheetDialog.findViewById(R.id.ly_delete_service);
        ly_send_sms_service = bottomSheetDialog.findViewById(R.id.ly_send_sms_service);
        send_service_sms_txt = bottomSheetDialog.findViewById(R.id.send_service_sms_txt);
        boolean is_active_send_sms = G.preference.getInt(Constants.PROV_ADD_SERVICE, 0) != 0;
        if (is_active_send_sms)
            send_service_sms_txt.setTextColor(getResources().getColor(R.color.gray));
        else send_service_sms_txt.setTextColor(getResources().getColor(R.color.graymenu));

        ly_show_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
                showServiceInfoActivity(position, model);
            }
        });
        ly_edit_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mDBHelper != null) {
                            mDBHelper.deleteHistoryKhadamt(String.valueOf(G.preference.getInt("idService", 0)));
                        }
                    }
                }, 0);
                editService(model);
            }
        });

        ly_delete_service.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
                deleteService(String.valueOf(model.getId()));

            }
        });

        ly_send_sms_service.setOnClickListener(v -> {
            Log.d("is_active_send_sms", "onItemClick: " + is_active_send_sms);
            if (is_active_send_sms) {
                Log.d("is_active_send_sms", "onItemClick:1 " + is_active_send_sms);
                G.sendSMSProv(true, model.getPhone(), G.PROV_ADD_Service, String.valueOf(model.getCar_id()), model.getId());
                bottomSheetDialog.dismiss();
            } else {
                bottomSheetDialog.dismiss();
                Log.d("is_active_send_sms", "onItemClick:2 " + is_active_send_sms);
                showSnackbar(bottomSheetDialog);
            }
        });
        bottomSheetDialog.show();
    }

    private void editService(ModelServicesCustomer model) {
        Intent intent = new Intent(LastServiseDoneActivity.this, AddServicesActivity.class);
        intent.putExtra("idCustomer", String.valueOf(model.getId_customer()));
        intent.putExtra(Constants.CAR_ID, model.getCar_id() + "");
        Log.d("CARRRR", "onResponse:editService:onClick " + model.getCar_id());
        intent.putExtra("firstName", model.getFirst_name());
        intent.putExtra("lastName", model.getLast_name());
        intent.putExtra("km_now", model.getKm_now());
        intent.putExtra("nameCar", model.getName_car());
        intent.putExtra("km_next", model.getKm_next());
        intent.putExtra("phone", model.getPhone());
        intent.putExtra("plak", model.getPlak());
        intent.putExtra(Constants.CAR_PLATE_TYPE, model.getPlak_type());
        intent.putExtra("date_service", model.getDate_services());
        intent.putExtra("description", model.getDescription());
        intent.putExtra("services_price", model.getAll_services_price());
        intent.putExtra("avg_function", model.getAvg_function());
        intent.putExtra("id_service", String.valueOf(model.getId()));
        intent.putExtra("car_name_id", model.getCar_name_id());
        intent.putExtra("car_tip_id", model.getCar_tip_id());
        intent.putExtra("car_model_id", model.getCar_model_id());
        intent.putExtra("fuel_type_id", model.getFuel_type_id());

        intent.putExtra("finish", "");
        intent.putExtra("fromMain", false);
        //    Toast.makeText(LastServiseDoneActivity.this, getIntent().getExtras().getInt("idCustomer")+"", Toast.LENGTH_SHORT).show();
        //    intent.putExtra("type_fule", mDBHelper.getCustomersInfo(mDatabase,getIntent().getExtras().getInt("idCustomer")).get(0).getType_fuel());
        intent.putExtra("type_fule", model.getType_fuel());
        intent.putExtra("type_car", model.getType_car());
        intent.putExtra("date_birthday", model.getDate_birthday());
        intent.putExtra("gender", model.getGender());
        startActivity(intent);
    }

    private void showServiceInfoActivity(int position, ModelServicesCustomer model) {
        Intent intent = new Intent(LastServiseDoneActivity.this, InformationServiceCarActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("idCustomer", String.valueOf(model.getId_customer()));
        Log.e("idCustomer:", String.valueOf(model.getId_customer()));
        Log.e("idservice:", String.valueOf(model.getId()));
        intent.putExtra(Constants.CAR_ID, String.valueOf(model.getCar_id()));
        intent.putExtra("idservice", String.valueOf(model.getId()));
        intent.putExtra("id_service", String.valueOf(model.getId()));
        intent.putExtra("firstName", model.getFirst_name());
        intent.putExtra("lastName", model.getLast_name());
        intent.putExtra("phone", model.getPhone());
        intent.putExtra("plak", model.getPlak());
        intent.putExtra(Constants.CAR_PLATE_TYPE, model.getPlak_type());
        intent.putExtra(Constants.CAR_ID, model.getCar_id() + "");
        intent.putExtra("km_now", model.getKm_now());
        intent.putExtra("nameCar", model.getName_car());
        intent.putExtra("km_next", model.getKm_next());
        intent.putExtra("avg_function", model.getAvg_function());
        intent.putExtra("description", model.getDescription());
        intent.putExtra("date_service", model.getDate_services());
        intent.putExtra("services_price", model.getAll_services_price());
        intent.putExtra("detail_service", model.getDetail_service());
        intent.putExtra("car_name_id", model.getCar_name_id());
        intent.putExtra("car_tip_id", model.getCar_tip_id());
        intent.putExtra("car_model_id", model.getCar_model_id());
        intent.putExtra("fuel_type_id", model.getFuel_type_id());

        startActivity(intent);
    }

    private void showSnackbar(BottomSheetDialog bottomSheetDialog) {
        Snackbar snackbar = Snackbar.make(recycle_services, R.string.enable_service_msg_prov, Snackbar.LENGTH_LONG).setAction(R.string.snackbar_action, view -> {
            bottomSheetDialog.dismiss();
        });
        snackbar.setTextColor(getResources().getColor(R.color.white));
        View snackbarView = snackbar.getView();
        TextView contentText = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
        TextView actionText = snackbarView.findViewById(com.google.android.material.R.id.snackbar_action);
        contentText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        contentText.setGravity(Gravity.CENTER);
        contentText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        actionText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        snackbar.show();
    }

    public void onclickAlamrs(View v) {
        startActivity(new Intent(LastServiseDoneActivity.this, AlarmsActivity.class));
    }

    @Override
    public void onResume() {
        G.Activity = this;
        G.context = this;
        super.onResume();
    }

    public void deleteService(String service_id) {
        Log.e(G.TAG, service_id);
        G.loading(this);
        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        JSONObject object = new JSONObject();
        try {
            String deleted_at = G.converToEn(DateFormat.format("yyyy-MM-dd HH:mm:ss", new Date()).toString());
            object.put("deleted_at", deleted_at);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<ResponseBody> request = api.deleteService(service_id, G.returnBody(object.toString()));
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                G.stop_loading();
                assert response.body() != null;
                String result = G.getResult(response);
                if (result.length() > 0 && result.length() < 10) {
                    finish();
                    startActivity(new Intent(G.Activity, LastServiseDoneActivity.class).putExtra("idCustomer", 0));
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

    Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);

    int last_page = 1;

    public void listService(String filter, String key) {

        if (!last_key.equals(key)) {
            G.services.clear();
            G.mscs.clear();
            key = G.converToEn(key);
            page = 1;
        }
        adapterListService.notifyDataSetChanged();
        last_filter = filter;
        last_key = key;
        if (page == 1) {
            swipeRefreshLayout.setRefreshing(true);
        }
        String d_id = PreferenceUtil.getD_id();
        Call<ResponseBody> request = api.listService("service_center_id,eq," + d_id, page);
        if (key.length() >= 1) {
             if(!Objects.equals(carId, "0" )){
                 request = api.searchService("service_center_id,eq," + d_id , "customer_car_id,eq," + carId , page);
            }else{
                request = api.searchService("service_center_id,eq," + d_id , filter + ",cs," + key , page);
            }
        }
        String finalKey = key;
        Log.d("LastServiceDone", "listService: " + request.request().toString());
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @SuppressLint({"StaticFieldLeak", "NotifyDataSetChanged"})
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (!last_key.equals(finalKey) || last_page == page) {
                    G.services.clear();
                    G.mscs.clear();
                }
                last_page = page;
                G.Log("LastServiceDone:" + call.request().toString());
                String result = G.getResult(response);
                Log.d("LastServiceDone", "listService:result: " + result);
                JSONObject object = G.StringtoJSONObject(result);
                JSONArray array = G.ObjecttoArray(object, "records");
                if (array.length() > 0) {
                    try {
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject obj = array.getJSONObject(i);
                            ModelServicesCustomer msc = new ModelServicesCustomer();
                            msc.setId(obj.getInt("service_id"));
                            G.services.add(msc.getId());
                            msc.setId_khadamat(obj.getInt("service_id"));

                            msc.setCar_id(obj.getInt("car_id"));
                            String car_company_name = (obj.getString("car_company_name")).replace("null", "");
                            String car_tip = (obj.getString("car_tip")).replace("null", "");
                            String car_name = obj.getString("car_name");
                            if (car_company_name.length() > 0) {
                                car_name = car_company_name + " - " + car_name;
                            }
                            if (car_tip.length() > 0) {
                                car_name = car_name + " - " + car_tip;
                            }
                            msc.setName_car(car_name);
                            msc.setPlak(obj.getString("car_plate"));
                            msc.setPlak_type(Constants.findById(obj.getInt(Constants.CAR_PLATE_TYPE)));
                            msc.setType_fuel(obj.getString("fuel_type"));
                            int car_name_id = 0, car_tip_id = 0, car_model_id = 0, fuel_type_id = 0;
                            if (obj.has("car_name_id")) {
                                car_name_id = obj.getInt("car_name_id");
                                if (!(obj.getString("car_tip_id")).contains("null")) {
                                    car_tip_id = obj.getInt("car_tip_id");
                                }
//                                if(!obj.getString("car_model_id").contains("null")) {
                                car_model_id = obj.getInt("car_model_id");
//                                }

                                fuel_type_id = obj.getInt("fuel_type_id");
                            }
                            msc.setCar_name_id(car_name_id);
                            msc.setCar_tip_id(car_tip_id);
                            msc.setCar_model_id(car_model_id);
                            msc.setFuel_type_id(fuel_type_id);
                            msc.setIdc(obj.getInt("user_id"));
                            msc.setId_customer(obj.getInt("user_id"));
                            msc.setFirst_name(obj.getString("f_name"));
                            msc.setLast_name(obj.getString("l_name"));
                            msc.setGender(obj.getString("sex"));
                            msc.setPhone(obj.getString("mobile"));
                            msc.setPhonec(obj.getString("mobile"));
                            msc.setDate_birthday(obj.getString("birthdate"));
                            msc.setDate_save_customer(obj.getString("cust_created_at"));


                            msc.setKm_next(obj.getString("km_next"));
                            msc.setDetail_service(obj.getString("detail_service"));
                            msc.setKm_now(obj.getString("km_now"));
                            msc.setDate_services(obj.getString("service_date_time").replace("00:00:00", ""));
                            String date = msc.getDate_services();
                            if (date.contains("-") && date.contains(":") && date.contains(" ")) {
                                CalendarTool calendarTool = new CalendarTool();
                                String[] date_time = date.split(" ");
                                String[] dates = date_time[0].split("-");
                                calendarTool.setGregorianDate(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
                                date = calendarTool.getIranianDate() + " " + date_time[1];
                                msc.setDate_services(date);
                            } else if (date.contains("-")) {
                                date = date.replace(" ", "");
                                CalendarTool calendarTool = new CalendarTool();
                                String[] dates = date.split("-");
                                calendarTool.setGregorianDate(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
                                date = calendarTool.getIranianDate();
                                msc.setDate_services(date);
                            }
                            msc.setAvg_function(obj.getString("avg_function"));

                            msc.setDescription(obj.getString("description"));

                            msc.setAll_services_price(obj.getString("price"));

                            G.mscs.add(msc);

                        }

                    } catch (JSONException e) {
                        G.toast("مشکل در دریافت اطلاعات");
                        e.printStackTrace();
                    }
                }
                if (page == 1) {
                    if (G.mscs.size() <= 0) {
                        ly_empty.setVisibility(View.VISIBLE);
                        recycle_services.setVisibility(View.GONE);
                    } else {
                        recycle_services.setVisibility(View.VISIBLE);
                        ly_empty.setVisibility(View.GONE);

                    }
                }
                if (page > 1) {
                    loadingPB.setVisibility(View.GONE);
                }
                swipeRefreshLayout.setRefreshing(false);
                adapterListService.notifyDataSetChanged();
                isLoading = false;
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
//                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });

    }

    public void getServiceCenterMsgProv() {
        G.saveProveService(0);
        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.getServiceCenterMsgProv("serv_center_id,eq," + d_id);
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                assert response.body() != null;
                Log.d("ManageMessageActivity", "onResponse: request:" + request);
                try {
                    String result = response.body().string();
                    Log.d("ManageMessageActivity", "onResponse: result:" + result);

                    JSONObject objectx = G.StringtoJSONObject(result);
                    JSONArray records = objectx.getJSONArray("records");
                    if (records.length() > 0) {
                        for (int i = 0; i < records.length(); i++) {
                            JSONObject obj = records.getJSONObject(i);
                            JSONObject object = new JSONObject();
                            object.put("msg_title_id", obj.getInt("msg_title_id"));
                            object.put("msg_prov_id", obj.getInt("msg_prov_id"));
                            Log.d("msg_prov_id", "onResponse:msg_prov_id" + obj.getInt("msg_prov_id"));
                            Log.d("msg_prov_id", "onResponse:msg_title_id " + obj.getInt("msg_title_id"));
                            if (obj.getInt("msg_title_id") == G.PROV_ADD_Service) {
                                G.saveProveService(obj.getInt("msg_prov_id"));
                            }
                        }
                    }

                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
            }
        });
    }

    private void FindView() {
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        img_back = findViewById(R.id.img_back);
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        edt3 = findViewById(R.id.edt3);
        edt4 = findViewById(R.id.edt4);
        edt5 = findViewById(R.id.edt5);
        edt6 = findViewById(R.id.edt6);
        edt7 = findViewById(R.id.edt7);
        edt8 = findViewById(R.id.edt8);
        recycle_services = findViewById(R.id.recycle_services);
        txt_type_search = findViewById(R.id.txt_type_search);
        edt_phone = findViewById(R.id.edt_phone);
        edt_name = findViewById(R.id.edt_name);
        ly_plak = findViewById(R.id.ly_plak);
        ly_empty = findViewById(R.id.ly_empty);
        ly_search = findViewById(R.id.ly_search);
        img_search_service = findViewById(R.id.img_search_service);
        edt_phone_number = findViewById(R.id.edt_phone_number);
        edt_nameFamily = findViewById(R.id.edt_nameFamily);
        loadingPB = findViewById(R.id.idPBLoading);
        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.button));
        tv_plk_type_menu = findViewById(R.id.tv_plk_type_menu);

        ly_plk_general = findViewById(R.id.ly_plk_general);
        ly_plk_azad_new = findViewById(R.id.ly_plk_azad_new);
        ly_plk_azad_old = findViewById(R.id.ly_plk_azad_old);
        plak_layout = ly_plk_general;
        ly_pelak_type = findViewById(R.id.ly_pelak_type);
        tv_plk_type_general = findViewById(R.id.tv_plk_type_general);
        tv_plk_type_azad_new = findViewById(R.id.tv_plk_type_azad_new);
        tv_plk_type_azad_old = findViewById(R.id.tv_plk_type_azad_old);

        v_search_separator = findViewById(R.id.v_search_separator);
    }

    private final Handler mHandler = new Handler();

    private void onClick() {
        txt_type_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogDetectTypeSearch(LastServiseDoneActivity.this, txt_type_search, ly_plak, edt_name, edt_phone, edt_phone_number, edt_nameFamily, edt1, edt2, edt3, edt4, edt5, edt6, edt7, edt8);
            }
        });

        img_search_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serachService();
            }
        });

        Runnable searchPhone = new Runnable() {
            @Override
            public void run() {
                if (edt_phone_number.getText().toString().length() > 0) {
                    String phone = edt_phone_number.getText().toString();
                    listService("mobile", phone);
                } else if (edt_phone_number.getText().toString().length() == 0) {
                    page = 1;
                    listService(last_filter, last_key);
                }
            }
        };
        edt_phone_number.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                swipeRefreshLayout.setRefreshing(true);
                if (mHandler != null) {
                    mHandler.removeCallbacks(searchPhone);
                    mHandler.postDelayed(searchPhone, 1000);
                }


            }
        });
        Runnable searchName = new Runnable() {
            @Override
            public void run() {
                if (edt_nameFamily.getText().toString().length() > 0) {
                    String nameFamily = edt_nameFamily.getText().toString();
                    listService("fullname", nameFamily);
                } else if (edt_nameFamily.getText().toString().length() == 0) {
                    page = 1;
                    listService(last_filter, last_key);
                }
            }
        };
        edt_nameFamily.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                swipeRefreshLayout.setRefreshing(true);
                if (mHandler != null) {
                    mHandler.removeCallbacks(searchName);
                    mHandler.postDelayed(searchName, 1000);
                }
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tv_plk_type_menu.setOnClickListener(v->{
            isVisiblePlakLayout = !isVisiblePlakLayout;
            showPlakTypeMenu(isVisiblePlakLayout);
        });

        tv_plk_type_general.setOnClickListener(v->{
            onClickPlakType(R.id.tv_plk_type_general);
        });
        tv_plk_type_azad_new.setOnClickListener(v->{
            onClickPlakType(R.id.tv_plk_type_azad_new);
        });
        tv_plk_type_azad_old.setOnClickListener(v->{
            onClickPlakType(R.id.tv_plk_type_azad_old);
        });
    }

    private void serachService() {
        String phone = edt_phone_number.getText().toString();
        String nameFamily = edt_nameFamily.getText().toString();
        //String plak = edt1.getText().toString() + edt2.getText().toString() + edt3.getText().toString() + edt4.getText().toString() + edt5.getText().toString() + edt6.getText().toString() + edt7.getText().toString() + edt8.getText().toString();
        String plak = getPlakValue();
        if (!TextUtils.isEmpty(phone) && TextUtils.isEmpty(nameFamily) && TextUtils.isEmpty(plak)) {
            listService("mobile", phone);
        } else if (TextUtils.isEmpty(phone) && !TextUtils.isEmpty(nameFamily) && TextUtils.isEmpty(plak)) {
            listService("fullname", nameFamily);
        } else if (TextUtils.isEmpty(phone) && TextUtils.isEmpty(nameFamily) && !TextUtils.isEmpty(plak)) {
            Log.d("PLAK", "onClick: " + plak);
            listService(Constants.CAR_PLATE, (plak));
           // listService("pelak", (plak));
        } else {
            G.toast("بر اساس شماره یا نام و نام خانوادگی جستجو کنید");
        }
    }

    private void PlakListener() {
        List<EditText> editTextsInPlakLayout = findEditTextsInLayout(plak_layout);
        Log.d("Maaain", "PlakListener: " + editTextsInPlakLayout.toString());
        Log.d("Maaain", "PlakListener: " + plak_layout.toString());
        setDeletionListener(editTextsInPlakLayout);
        EditText nextEditText;
        for (int i = 0; i <= editTextsInPlakLayout.size() - 1; i++) {
            EditText currentEditText = editTextsInPlakLayout.get(i);
            if(i== editTextsInPlakLayout.size() - 1){
                nextEditText = editTextsInPlakLayout.get(i);
            } else {
                nextEditText = editTextsInPlakLayout.get(i + 1);
            }
            PlakTextWatcher textWatcher = new PlakTextWatcher(currentEditText, nextEditText);
            currentEditText.addTextChangedListener(textWatcher);
            if(i == editTextsInPlakLayout.size() - 1){
                currentEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        serachService();

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
            }
        }

        edt8.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                edt1.requestFocus();
                //img_search_service.performClick();
                serachService();
                return true;
            }
            return false;
        });
    }

    private void setDeletionListener(List<EditText> editTexts) {
        for (final EditText editText : editTexts) {
            editText.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_DEL && !keydel) {
                        keydel = true;
                        EditText currentEditText = editText;
                        EditText nextEditText = null;

                        if (v == editTexts.get(0)) {
                            // If the current EditText is the first one in the list
                            nextEditText = editTexts.get(0);
                        } else {
                            // Otherwise, find the previous EditText in the list
                            int currentIndex = editTexts.indexOf(editText);
                            if (currentIndex > 0) {
                                nextEditText = editTexts.get(currentIndex - 1);
                            }
                        }

                        if (currentEditText != null && nextEditText != null) {
                            if (currentEditText.getText().toString().length() > 0) {
                                currentEditText.setText("");
                                currentEditText.requestFocus();
                            } else {
                                currentEditText.setText("");
                                nextEditText.requestFocus();
                            }
                        }
                    } else {
                        keydel = false;
                    }
                    return false;
                }
            });
        }
    }
    void DialogDetectTypeSearch(Context context, TextView textView, LinearLayout ly_plak, TextInputLayout edt_name, TextInputLayout edt_phone, EditText edt_phone_number, EditText edt_nameFamily, EditText ed1, EditText ed2, EditText edt3, EditText edt4, EditText edt5, EditText edt6, EditText edt7, EditText edt8) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_item_dialog_type_search, null);

        final BottomSheetDialog alertDialogBuilder = new BottomSheetDialog(LastServiseDoneActivity.this, R.style.BottomSheetDialogTheme);
        alertDialogBuilder.setContentView(view);
        alertDialogBuilder.setCancelable(true);


        alertDialogBuilder.show();
        ImageView img_close;
        TextView txt_search_plak, txt_search_phone, txt_search_name;
        img_close = view.findViewById(R.id.img_close);
        txt_search_plak = view.findViewById(R.id.txt_search_plak);
        txt_search_phone = view.findViewById(R.id.txt_search_phone);
        txt_search_name = view.findViewById(R.id.txt_search_name);
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialogBuilder.dismiss();
            }
        });

        txt_search_plak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(txt_search_plak.getText().toString());
                tv_plk_type_menu.setVisibility(View.VISIBLE);
                v_search_separator.setVisibility(View.VISIBLE);
                ly_plak.setVisibility(View.VISIBLE);
                edt_name.setVisibility(View.GONE);
                edt_phone.setVisibility(View.GONE);
                alertDialogBuilder.dismiss();
                edt_nameFamily.setText("");
                edt_phone_number.setText("");
                showPlakTypeMenu(isVisiblePlakLayout);
            }
        });
        txt_search_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_plk_type_menu.setVisibility(View.GONE);
                v_search_separator.setVisibility(View.GONE);
                textView.setText(txt_search_phone.getText().toString());
                ly_plak.setVisibility(View.GONE);
                edt_name.setVisibility(View.GONE);
                edt_phone.setVisibility(View.VISIBLE);
                alertDialogBuilder.dismiss();
                edt_nameFamily.setText("");
                ed1.setText("");
                ed2.setText("");
                edt3.setText("");
                edt4.setText("");
                edt5.setText("");
                edt6.setText("");
                edt7.setText("");
                edt8.setText("");

            }
        });
        txt_search_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_plk_type_menu.setVisibility(View.GONE);
                v_search_separator.setVisibility(View.GONE);
                textView.setText(txt_search_name.getText().toString());
                ly_plak.setVisibility(View.GONE);
                edt_name.setVisibility(View.VISIBLE);
                edt_phone.setVisibility(View.GONE);
                alertDialogBuilder.dismiss();
                edt_phone_number.setText("");
                ed1.setText("");
                ed2.setText("");
                edt3.setText("");
                edt4.setText("");
                edt5.setText("");
                edt6.setText("");
                edt7.setText("");
                edt8.setText("");

            }
        });
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        width = (int) ((width) * ((double) 9 / 10));

    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Cust", "onRestart: ");
        swipeRefreshLayout.setRefreshing(true);
        listService(last_filter, last_key);
    }

    private void showPlakTypeMenu(Boolean isVisible) {
        ly_pelak_type.setVisibility((isVisible ? View.VISIBLE : View.GONE));
    }

    private void onClickPlakType(int id){
        switch (id){
            case R.id.tv_plk_type_general: {
                plak_type = Constants.PLAK_TYPE.PLAK_GENERAL;
                plak_layout = ly_plk_general;
                break;
            }
            case R.id.tv_plk_type_azad_old:{
                plak_type = Constants.PLAK_TYPE.PLAK_AZAD_OLD;
                plak_layout = ly_plk_azad_old;
                break;
            }
            case R.id.tv_plk_type_azad_new:{
                plak_type = Constants.PLAK_TYPE.PLAK_AZAD_NEW;
                plak_layout = ly_plk_azad_new;
                break;
            }
        }
        changePlakLayout();
        PlakListener();
    }

    private void changePlakLayout() {
        switch (plak_type){
            case PLAK_GENERAL: {
                ly_plk_general.setVisibility(View.VISIBLE);
                ly_plk_azad_new.setVisibility(View.GONE);
                ly_plk_azad_old.setVisibility(View.GONE);
                break;
            }
            case PLAK_AZAD_NEW: {
                ly_plk_azad_new.setVisibility(View.VISIBLE);
                ly_plk_general.setVisibility(View.GONE);
                ly_plk_azad_old.setVisibility(View.GONE);
                break;
            }
            case PLAK_AZAD_OLD: {
                ly_plk_azad_old.setVisibility(View.VISIBLE);
                ly_plk_general.setVisibility(View.GONE);
                ly_plk_azad_new.setVisibility(View.GONE);
                break;
            }
        }
    }

    private String getPlakValue() {
        List<EditText> editTextsInPlakLayout = findEditTextsInLayout(plak_layout);
        StringBuilder plak = new StringBuilder("");
        String endPlak = "";
        for (int i = 0; i < editTextsInPlakLayout.size() ; i++) {
            EditText currentEditText = editTextsInPlakLayout.get(i);
            if(i == 2 && (plak_type!= Constants.PLAK_TYPE.PLAK_AZAD_NEW && plak_type!= Constants.PLAK_TYPE.PLAK_AZAD_OLD)){
                endPlak = currentEditText.getText().toString();
            }else if ((plak_type == Constants.PLAK_TYPE.PLAK_AZAD_NEW || plak_type == Constants.PLAK_TYPE.PLAK_AZAD_OLD) && i == 2) {
                plak.append(currentEditText.getText().toString());
            } else if ((plak_type == Constants.PLAK_TYPE.PLAK_AZAD_NEW || plak_type == Constants.PLAK_TYPE.PLAK_AZAD_OLD) && i == editTextsInPlakLayout.size()) {
                break;
            } else{
                plak.append(currentEditText.getText().toString());
            }
        }
        if (plak_type != Constants.PLAK_TYPE.PLAK_AZAD_NEW && plak_type != Constants.PLAK_TYPE.PLAK_AZAD_OLD) {
            plak.append(endPlak);
        }
        return plak.toString();
    }

    private String getPlakValueForSearch() {
        List<EditText> editTextsInPlakLayout = findEditTextsInLayout(plak_layout);
        StringBuilder plak = new StringBuilder("");
        String endPlak = "";
        for (int i = 0; i < editTextsInPlakLayout.size() ; i++) {
            EditText currentEditText = editTextsInPlakLayout.get(i);
            if ((plak_type == Constants.PLAK_TYPE.PLAK_AZAD_NEW || plak_type == Constants.PLAK_TYPE.PLAK_AZAD_OLD) ) {
                plak.append(currentEditText.getText().toString());
            } else if ((plak_type == Constants.PLAK_TYPE.PLAK_AZAD_NEW || plak_type == Constants.PLAK_TYPE.PLAK_AZAD_OLD) && i == editTextsInPlakLayout.size()) {
                break;
            } else{
                plak.append(currentEditText.getText().toString());
            }
        }
        return plak.toString();
    }

    private List<EditText> findEditTextsInLayout(ViewGroup layout) {
        List<EditText> editTexts = new ArrayList<>();
        for (int i = 0; i < layout.getChildCount(); i++) {
            View view = layout.getChildAt(i);
            if (view instanceof EditText) {
                editTexts.add((EditText) view);
            } else if (view instanceof ViewGroup) {
                editTexts.addAll(findEditTextsInLayout((ViewGroup) view));
            }
        }
        return editTexts;
    }
}