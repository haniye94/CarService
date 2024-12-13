package ir.tehranOil.activity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.tehranOil.app.DataBaseHelper;

import ir.servicea.R;
import ir.tehranOil.retrofit.Api;
import ir.tehranOil.retrofit.RetrofitClient;
import ir.tehranOil.adapter.AdapterListDetectGroup;
import ir.tehranOil.app.G;
import ir.tehranOil.model.ModelProduct;
import ir.tehranOil.model.SliderItem;
import ir.tehranOil.app.PreferenceUtil;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class DetectServiceActivity extends AppCompatActivity {

    private TextView txt_tile_action_bar;
    private ImageView img_back;
    private RecyclerView recycle_produce_group;
    private List<ModelProduct> listGroup = new ArrayList<>();
    private EditText edt_time_start, edt_time_end;
    private EditText edt_time_start2, edt_time_end2;
    private AdapterListDetectGroup adapterListProduceGroup;
    private CheckBox checkAll;
    private boolean check = false;
    private Button btn_confirm;
    private Spinner spin_count_pos, spin_entezar, spin_paziraee;
    private CheckBox holiday;
    List<Integer> count = new ArrayList<>();
    List<String> entezar = new ArrayList<>();
    List<String> paziraee = new ArrayList<>();
    private DataBaseHelper mDBHelper;
    private SQLiteDatabase mDatabase;

    String entezarr, pazirae, khadamat;
    int countt;
    AdapterListDetectGroup.OnItemClickListener onItemClickListener;
    public static boolean isLoading = true;
    @Override
    public void onResume(){
        super.onResume();
        G.Activity = this;
        G.context = this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_service);
        G.Activity = this;
        G.context = this;
        FindView();
        onClick();
        mDBHelper = new DataBaseHelper(this);
        mDatabase = mDBHelper.getReadableDatabase();
        txt_tile_action_bar.setText("خدمات قابل ارائه");
        txt_tile_action_bar.setTypeface(G.Bold);
        onItemClickListener = new AdapterListDetectGroup.OnItemClickListener() {
            @Override
            public void onItemClick(ModelProduct model, CheckBox item, AdapterListDetectGroup.ViewHolder holder, int position) {
                if (!DetectServiceActivity.isLoading) {
                    model.setCheck(item.isChecked());
                }


            }
        };
        checkAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                check = b;
                List<ModelProduct> templistGroup = listGroup;
                for (int i = 0; i < listGroup.size(); i++) {
                    ModelProduct modelProduct = listGroup.get(i);
                    modelProduct.setCheck(check);
                    templistGroup.add(modelProduct);
                }
                listGroup = templistGroup;
                adapterListProduceGroup.notifyDataSetChanged();
            }
        });
        getJob_services();


        count.add(1);
        count.add(2);
        count.add(3);
        count.add(4);
        count.add(5);
        count.add(6);
        count.add(7);
        count.add(8);
        count.add(9);
        count.add(10);


        entezar.add("ندارد");
        entezar.add("دارد");

        paziraee.add("ندارد");
        paziraee.add("دارد");


        SpinnerAdapter spinnerAdapter = new ArrayAdapter(DetectServiceActivity.this, R.layout.item_spiner, count);
        ((ArrayAdapter) spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_count_pos.setAdapter(spinnerAdapter);


        SpinnerAdapter spinnerAdapter2 = new ArrayAdapter(DetectServiceActivity.this, R.layout.item_spiner, entezar);
        ((ArrayAdapter) spinnerAdapter2).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_entezar.setAdapter(spinnerAdapter2);

        SpinnerAdapter spinnerAdapter3 = new ArrayAdapter(DetectServiceActivity.this, R.layout.item_spiner, paziraee);
        ((ArrayAdapter) spinnerAdapter3).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_paziraee.setAdapter(spinnerAdapter3);

        spin_count_pos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                countt = count.get(i);
                if (access)
                    PreferenceUtil.preferenceUtil.edit().putInt("numOfBranch", countt).apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spin_entezar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                entezarr = entezar.get(i);
                if (access)
                    PreferenceUtil.preferenceUtil.edit().putInt("waiting", i).apply();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spin_paziraee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pazirae = paziraee.get(i);
                if (access)
                    PreferenceUtil.preferenceUtil.edit().putInt("catering", i).apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        edt_time_end2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
//                    spin_count_pos.performClick();
                    if (edt_time_end2 != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(edt_time_end2.getWindowToken(), 0);
                    }
                    edt_time_end2.clearFocus();
                    return true;
                }
                return false;
            }
        });
        holiday.setChecked(G.preference.getBoolean("holiday", false));
    }

    public void getJob_services() {
        listGroup.clear();
        isLoading = true;
        recycle_produce_group.setLayoutManager(new LinearLayoutManager(DetectServiceActivity.this, RecyclerView.VERTICAL, false));
        adapterListProduceGroup = new AdapterListDetectGroup(DetectServiceActivity.this, listGroup, onItemClickListener);
        recycle_produce_group.setAdapter(adapterListProduceGroup);
        G.loading(this);
        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.getJob_services("job_category_id,eq," + G.preference.getInt("job_category_id", 1));
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                assert response.body() != null;
                try {
                    String result = response.body().string();
                    JSONObject object = G.StringtoJSONObject(result);
                    JSONArray records = object.getJSONArray("records");
                    if (records.length() > 0) {
                        List<SliderItem> sliderItemList = new ArrayList<>();

                        for (int i = 0; i < records.length(); i++) {
                            JSONObject obj = records.getJSONObject(i);
                            SliderItem sliderItem = new SliderItem();
                            String id = obj.getString("id");
                            String title = obj.getString("title");
                            String cash = G.preference.getString("selectedServiceInRegister","");
//                            if(cash.contains(","+id)){
//                                check = true;
//                            }
                            listGroup.add(new ModelProduct(id, title, check));
                            check = false;
                        }


                        adapterListProduceGroup.notifyDataSetChanged();
                        listServiceAvailable();
                    } else {
                        G.stop_loading();
                        G.toast("هیچ خدمت قابل ارائه\u200Cای در این دسته شغلی یافت نشد");
                    }
                } catch (JSONException | IOException e) {
                    G.stop_loading();
                    G.toast("مشکل در تجزیه اطلاعات");
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

    public void onclickAlamrs(View v) {
        startActivity(new Intent(DetectServiceActivity.this, AlarmsActivity.class));
    }

    public boolean access = false;

    private void FindView() {
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        img_back = findViewById(R.id.img_back);
        checkAll = findViewById(R.id.checkAll);
        recycle_produce_group = findViewById(R.id.recycle_produce_group);
        btn_confirm = findViewById(R.id.btn_confirm);
        edt_time_start = findViewById(R.id.edt_time_start);
        edt_time_end = findViewById(R.id.edt_time_end);
        edt_time_start2 = findViewById(R.id.edt_time_start2);
        edt_time_end2 = findViewById(R.id.edt_time_end2);
        spin_count_pos = findViewById(R.id.spin_count_pos);
        spin_entezar = findViewById(R.id.spin_entezar);
        spin_paziraee = findViewById(R.id.spin_paziraee);
        holiday = findViewById(R.id.holiday);


        edt_time_start.setOnClickListener(v -> G.TimePIcker(DetectServiceActivity.this, edt_time_start));
        edt_time_end.setOnClickListener(v -> G.TimePIcker(DetectServiceActivity.this, edt_time_end));
        edt_time_start2.setOnClickListener(v -> G.TimePIcker(DetectServiceActivity.this, edt_time_start2));
        edt_time_end2.setOnClickListener(v -> G.TimePIcker(DetectServiceActivity.this, edt_time_end2));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String openTime = PreferenceUtil.preferenceUtil.getString("openTime", "");
                String closeTime = PreferenceUtil.preferenceUtil.getString("closeTime", "");
                String openTime2 = PreferenceUtil.preferenceUtil.getString("openTime2", "");
                String closeTime2 = PreferenceUtil.preferenceUtil.getString("closeTime2", "");
                int numOfBranch = PreferenceUtil.preferenceUtil.getInt("numOfBranch", 0);
                int waiting = PreferenceUtil.preferenceUtil.getInt("waiting", 0);
                int catering = PreferenceUtil.preferenceUtil.getInt("catering", 0);
                if (!openTime.equals("null") && openTime.length() > 0 )
                    edt_time_start.setText(openTime + "");
                if (!closeTime.equals("null") && closeTime.length() > 0)
                    edt_time_end.setText(closeTime + "");
                if (!openTime2.equals("null") && openTime2.length() > 0)
                    edt_time_start2.setText(openTime2 + "");
                if (!closeTime2.equals("null") && closeTime2.length() > 0)
                    edt_time_end2.setText(closeTime2 + "");
                spin_count_pos.setSelection(numOfBranch - 1);
                spin_count_pos.getPrompt();
                spin_entezar.setSelection(waiting);
                spin_entezar.getPrompt();
                spin_paziraee.setSelection(catering);
                spin_paziraee.getPrompt();
                G.Log(catering + " _ " + numOfBranch + " _ " + waiting);
                access = true;
            }
        }, 500);


    }

    private void onClick() {
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time_start = edt_time_start.getText().toString();
                String time_end = edt_time_end.getText().toString();
                String time_start2 = edt_time_start2.getText().toString();
                String time_end2 = edt_time_end2.getText().toString();
                StringBuilder service = new StringBuilder();
                String cash = "";
                service.append("[");
                for (ModelProduct modelx : listGroup) {
                    if (modelx.isCheck()) {
                        cash += ","+modelx.getId();
                        if (!service.toString().equals("")) {
                            service.append(",");
                        }
                        service.append(modelx.getNameKala());
                    }
                }
                G.preference.edit().putString("selectedServiceInRegister",cash).apply();
                service.append("]");
                PreferenceUtil.preferenceUtil.edit().putString("service", service.toString()).apply();
                if (!TextUtils.isEmpty(time_start) && !TextUtils.isEmpty(time_end) && !TextUtils.isEmpty(time_start2) && !TextUtils.isEmpty(time_end2)) {
                    PreferenceUtil.preferenceUtil.edit().putString("openTime", (time_start)).apply();
                    PreferenceUtil.preferenceUtil.edit().putString("closeTime", (time_end)).apply();
                    PreferenceUtil.preferenceUtil.edit().putString("openTime2", (time_start2)).apply();
                    PreferenceUtil.preferenceUtil.edit().putString("closeTime2", (time_end2)).apply();

                    G.preference.edit().putBoolean("holiday", holiday.isChecked()).apply();
                    mDBHelper.insertInfoGroup(time_start, time_end, time_start2, time_end2, countt, entezarr, pazirae, khadamat);
                    if (ids_ServiceAvailable.length() > 0) {
                        deleteServiceAvailables();
                    } else {
                        addServiceAvailable();
                    }


                } else {
                    if (time_start.equals("") || time_start.length() > 2)
                        edt_time_start.setError("مقادیر صحیح وارد کنید");
                    if (time_end.equals("") || time_end.length() > 2)
                        edt_time_end.setError("مقادیر صحیح وارد کنید");
                    if (time_start2.equals("") || time_start2.length() > 2)
                        edt_time_start2.setError("مقادیر صحیح وارد کنید");
                    if (time_end2.equals("") || time_end2.length() > 2)
                        edt_time_end2.setError("مقادیر صحیح وارد کنید");
                }

            }
        });
    }

    public String ids_ServiceAvailable = "";

    public void listServiceAvailable() {

        ids_ServiceAvailable = "";
        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.listServiceAvailable("service_center_id,eq," + d_id);
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                try {

                    String result = G.getResult(response);
                    JSONObject object = G.StringtoJSONObject(result);
                    JSONArray records = object.getJSONArray("records");
                    if (records.length() > 0) {
                        for (int i = 0; i < records.length(); i++) {
                            JSONObject obj = records.getJSONObject(i);
                            ids_ServiceAvailable += "" + obj.getString("id") + ",";
                            for (int j = 0; j < listGroup.size(); j++) {


                                G.Log(obj.getString("job_service_id") + "");
                                if ((listGroup.get(j).getId() + "").equals(obj.getString("job_service_id"))) {
                                    boolean status = obj.getBoolean("status");
                                    listGroup.get(j).setCheck(status);
                                    G.Log(status+": "+j);
                                }

                            }
                        }
//                        ids_ServiceAvailable = ids_ServiceAvailable.substring(0,ids_ServiceAvailable.length()-1);
                        ids_ServiceAvailable = ids_ServiceAvailable.replace(",,", "");
                        adapterListProduceGroup.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    G.toast("مشکل در تجزیه اطلاعات");
                    e.printStackTrace();
                }
                G.stop_loading();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isLoading = false;
                    }
                }, 1000);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

    public void deleteServiceAvailables() {

        G.loading(this);
        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.deleteServiceAvailable(ids_ServiceAvailable);
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                String result = G.getResult(response);

                if (result.length() > 0 && result.length() < 10) {

                }

                addServiceAvailable();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

    public int countxx = 0;

    public void addServiceAvailable() {

        if (countxx < listGroup.size()) {

            String d_id = PreferenceUtil.getD_id();
            G.loading(this);


            JSONObject object = new JSONObject();
            try {
                object.put("service_center_id", d_id);
                object.put("job_service_id", listGroup.get(countxx).getId() + "");
                if (listGroup.get(countxx).isCheck()) {
                    object.put("status", "1");
                } else {
                    object.put("status", "0");
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);

            Call<ResponseBody> request = api.addServiceAvailable(G.returnBody(object.toString()));
            countxx++;
            request.enqueue(new retrofit2.Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    String result = G.getResult(response);

                    if (result.length() > 0 && result.length() < 10) {

                        addServiceAvailable();

                    } else {
                        G.stop_loading();
                        finish();

                    }

//                    G.Log(object.toString());
//                    G.Log(call.request().toString());
//                    G.Log(result);
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

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }
}