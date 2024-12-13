package ir.tehranOil.activity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
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
import ir.tehranOil.adapter.AdapterListServiceCar;
import ir.tehranOil.app.Constants;
import ir.tehranOil.app.DataBaseHelper;
import ir.tehranOil.app.G;
import ir.tehranOil.app.PreferenceUtil;
import ir.tehranOil.app.Utils;
import ir.tehranOil.model.SliderItem;
import ir.tehranOil.model.dbModel.ModelKhadamat;
import ir.tehranOil.retrofit.Api;
import ir.tehranOil.retrofit.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class ListServiceCarActivity extends AppCompatActivity {
    RecyclerView Recycler_service_car;
    TextView txt_tile_action_bar;
    AdapterListServiceCar adapterListServiceCar;
    List<ModelKhadamat> modelKhadamats = new ArrayList<>();
    List<ModelKhadamat> tempmodelKhadamats = new ArrayList<>();
    Button btn_save;
    TextView add_text;
    ImageView img_back;
    DataBaseHelper mDBHelper;
    private SQLiteDatabase mDatabase;
    private AdapterListServiceCar.OnItemClickListener onItemClickService;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Handler handler;

    public void onclickAlamrs(View v) {
        startActivity(new Intent(ListServiceCarActivity.this, AlarmsActivity.class));
    }

    String nextid = "", previous = "";
    boolean isEditService = false;

    private Button btnShowSavabegh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_service_car);
        G.Activity = this;
        G.context = this;
        FindView();
        onClick();
        if(getIntent().hasExtra(Constants.IS_EDIT_SERVICE)){
            isEditService = getIntent().getBooleanExtra(Constants.IS_EDIT_SERVICE, false);
        }
        if(isEditService){
            txt_tile_action_bar.setText(getString(R.string.edit_service_car_list));
            btnShowSavabegh.setVisibility(View.GONE);

        }else{
            txt_tile_action_bar.setText(getString(R.string.show_service_car_list));
            btnShowSavabegh.setVisibility(View.VISIBLE);
        }
        txt_tile_action_bar.setTypeface(G.Bold);
        add_text.setTypeface(G.Bold);
        btnShowSavabegh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                G.preference.edit().putBoolean("ShowSavabegh", true).apply();
                finish();
            }
        });
        findViewById(R.id.add_div).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                G.preference.edit().putBoolean("changeProductGroup", true).apply();
                startActivity(new Intent(ListServiceCarActivity.this, ProductGroupActivity.class));
            }
        });
        mDBHelper = new DataBaseHelper(this);
        mDatabase = mDBHelper.getReadableDatabase();

        onItemClickService = new AdapterListServiceCar.OnItemClickListener() {
            @Override
            public void onItemClickB(ModelKhadamat model, TextView itemT, TextView itemB, TextView center, TextView txt_detect_type, AdapterListServiceCar.ViewHolder holder, int position) {
                if (model.getSelectB().equals("true")) {
                    itemB.setBackgroundResource(R.drawable.shap_btn_simple_right);
                    itemT.setBackgroundResource(R.drawable.shap_btn_simple_left_off);
                    model.setSelectB("false");
                    model.setSelectT("true");
                    mDBHelper.deleteRow(getIntent().getExtras().getInt("idService") + "", model.getTitle());
                    //  mDBHelper.updateKhadamat(models.get(position).getId(), 1, idCustomer);
                    mDBHelper.insertdetectProGroup(model.getTitle(), 1, getIntent().getExtras().getInt("idService"),model.getType(),model.getValue());
                } else {
                    itemB.setBackgroundResource(R.drawable.shap_btn_simple_right_off);
                    model.setSelectB("true");
                    Log.e("dddcdc", "delet1");
                    mDBHelper.deleteRow(getIntent().getExtras().getInt("idService") + "", model.getTitle());
                }

                txt_detect_type.setVisibility(View.INVISIBLE);
                center.setVisibility(View.GONE);
            }

            @Override
            public void onItemClickT(ModelKhadamat model, TextView itemT, TextView itemB, TextView center, TextView txt_detect_type, AdapterListServiceCar.ViewHolder holder, int position) {
                if (model.getSelectT().equals("true")) {
                    itemT.setBackgroundResource(R.drawable.shap_btn_simple_left);
                    itemB.setBackgroundResource(R.drawable.shap_btn_simple_right_off);
                    model.setSelectB("true");
                    model.setSelectT("false");
                    mDBHelper.deleteRow(getIntent().getExtras().getInt("idService") + "", model.getTitle());
                    // mDBHelper.updateKhadamat(models.get(position).getId(), 2, idCustomer);
                    mDBHelper.insertdetectProGroup(model.getTitle(), 2, getIntent().getExtras().getInt("idService"),model.getType(),model.getValue());

                } else {
                    itemT.setBackgroundResource(R.drawable.shap_btn_simple_left_off);
                    model.setSelectT("true");
                    Log.e("dddcdc", "delet2");
                    mDBHelper.deleteRow(getIntent().getExtras().getInt("idService") + "", model.getTitle());
                }
                txt_detect_type.setVisibility(View.VISIBLE);
                center.setVisibility(View.GONE);
            }
        };


        EditText search = findViewById(R.id.search);
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

                            getJob_services(search.getText().toString(), 0);
                        }
                    }, 750);

                } else if (s.length() == 0) {
                    getJob_services("", 0);
                }

            }
        });
        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.button));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        getJob_services("", 0);
                        search.setText("");
                    }
                }, 250);
            }
        });
        G.preference.edit().putBoolean("changeProductGroup", false).apply();
        getJob_services("", 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        G.Activity = this;
        G.context = this;

        boolean changeProductGroup = G.preference.getBoolean("changeProductGroup", false);
        if (changeProductGroup) {
            G.preference.edit().putBoolean("changeProductGroup", false).apply();
            getJob_services("", 0);
        }


    }

    String ids_ServiceAvailable = "";

    public void getJob_services(String key, int job_category_id) {
        disableBack = true;
        if (job_category_id == 0) {
            job_category_id = G.preference.getInt("job_category_id", 1);
        }
        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
//        Call<ResponseBody> request = api.getProduct_groups("job_category_id,eq," + job_category_id);
        Call<ResponseBody> request = api.getProduct_groups("id,gt,0");
        if (key.length() > 0) {
            swipeRefreshLayout.setRefreshing(true);
//            request = api.getProduct_groupsBySearch("job_category_id,eq," + job_category_id, "title,cs," + key);
            request = api.getProduct_groupsBySearch("id,gt,0", "title,cs," + key);

        } else {
//            G.loading(this);
            swipeRefreshLayout.setRefreshing(true);
        }
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                assert response.body() != null;
                modelKhadamats.clear();
                try {
                    String result = response.body().string();
                    G.Log(call.request().toString());
                    JSONObject object = G.StringtoJSONObject(result);
                    if (object != null) {
                        JSONArray records = object.getJSONArray("records");
                        if (records.length() > 0) {
                            List<SliderItem> sliderItemList = new ArrayList<>();

                            for (int i = 0; i < records.length(); i++) {
                                JSONObject obj = records.getJSONObject(i);
                                SliderItem sliderItem = new SliderItem();
                                int id = obj.getInt("id");
                                String km_usagex = obj.getString("km_usage") + "";
                                int km_usage = 0;
                                if (!km_usagex.contains("null")) {
                                    km_usage = obj.getInt("km_usage");
                                }
                                String title = obj.getString("title");

                                boolean send_msg = obj.getBoolean("send_msg");
                                G.Log("send_msgx: "+send_msg);

//                                check = checkProductGroup(id);
//                                check = false;
//                                listGroup.add(new ModelProduceGroup(id, title, km_usage, check, send_msg));
                                String product_name ="";
                                int product_name_id = 0;
                                String value = "";
                                try {
                                    JSONArray historyKhadamat = new JSONArray(G.preference.getString("historyKhadamat", "[]"));
                                    G.Log("historyKhadamat: "+historyKhadamat);
                                    for (int j= 0; j < historyKhadamat.length();j++) {
                                        JSONObject objx = historyKhadamat.getJSONObject(j);
                                        String product_group_title = "";
                                        int product_group_id = 0;
                                        if (objx.has("product_group_id")) {
                                            JSONObject product_group = objx.getJSONObject("product_group_id");
                                            product_group_id = product_group.getInt("id");
                                            product_group_title = product_group.getString("title");
                                        }
                                        if(product_group_id==id) {
                                            product_name = (objx.getString("product_name")+"").replace("null","");
                                            product_name_id =0;
                                            if((objx.getString("product_name_id")+"").replace("null","").replace(" ","").length()>0) {
                                                product_name_id = objx.getInt("product_name_id");
                                            }
                                            value = (objx.getString("value")+"").replace("null","");
                                            String visited_change = (objx.getString("visited_change")+"").replace("null","");
                                        }
                                    }
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                                G.Log("product_name_id: "+product_name_id);
                                G.Log("product_name: "+product_name);
                                G.Log("value: "+value);
                                if (key.length() > 0) {
                                    if (title.contains(key)) {
                                        modelKhadamats.add(new ModelKhadamat(id, title, "true", "true", product_name, product_name_id, value, km_usage,send_msg));
                                    }
                                } else {
                                    modelKhadamats.add(new ModelKhadamat(id, title, "true", "true", product_name, product_name_id, value, km_usage,send_msg));
                                }

                            }


                            listServiceAvailable();

                        } else {
                            G.stop_loading();
                            G.toast("هیچ گروه کالا\u200Cای در این دسته شغلی یافت نشد");
                        }
                    }
                } catch (JSONException | IOException e) {
                    G.stop_loading();
                    G.toast("مشکل در تجزیه اطلاعات");
                    e.printStackTrace();
                }
                disableBack = false;
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                disableBack = false;
//                fromrefresh = false;
                swipeRefreshLayout.setRefreshing(false);
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }



    public void listServiceAvailable() {
//        G.loading(this);
        disableBack = true;
        swipeRefreshLayout.setRefreshing(true);
        ids_ServiceAvailable = "";
        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.listProductGroupAvailable("service_center_id,eq," + d_id);
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                try {
                    G.Log(call.request().toString());
                    String result = G.getResult(response);
                    JSONObject object = G.StringtoJSONObject(result);
                    JSONArray records = object.getJSONArray("records");
                    tempmodelKhadamats.clear();
                    tempmodelKhadamats.addAll(modelKhadamats);
                    modelKhadamats.clear();
                    if (records.length() > 0) {
                        for (int i = 0; i < records.length(); i++) {
                            JSONObject obj = records.getJSONObject(i);
                            ids_ServiceAvailable += "" + obj.getString("id") + ",";


                            for (int j = 0; j < tempmodelKhadamats.size(); j++) {

                                if ((tempmodelKhadamats.get(j).getId() + "").equals(obj.getString("product_group_id"))) {
                                    boolean status = obj.getInt("status") == 1;
                                    if (status) {
                                        modelKhadamats.add(tempmodelKhadamats.get(j));
                                    }
                                }

                            }
                        }
//                        ids_ServiceAvailable = ids_ServiceAvailable.substring(0,ids_ServiceAvailable.length()-1);
                        ids_ServiceAvailable = ids_ServiceAvailable.replace(",,", "");
//                        adapterListProduceGroup.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    G.toast("مشکل در تجزیه اطلاعات");
                    e.printStackTrace();
                }
                G.stop_loading();

                swipeRefreshLayout.setRefreshing(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Recycler_service_car.setLayoutManager(new LinearLayoutManager(ListServiceCarActivity.this, RecyclerView.VERTICAL, false));
                        // adapterListServiceCar = new AdapterListServiceCar(ListServiceCarActivity.this, mDBHelper.getListKhadamat(mDatabase),getIntent().getExtras().getInt("idCustomer"));
                        adapterListServiceCar = new AdapterListServiceCar(ListServiceCarActivity.this, modelKhadamats, onItemClickService, getIntent().getExtras().getInt("idService"));
                        Recycler_service_car.setAdapter(adapterListServiceCar);
                        adapterListServiceCar.notifyDataSetChanged();
                        G.stop_loading();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 100);
                if (modelKhadamats.size() == 0) {
                    G.toast("هیچ گروه کالایی یافت نشد!");
                    G.toast("لطفأ ابتدا گروه کالای خود را مشخص کنید!");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            G.preference.edit().putBoolean("changeProductGroup", true).apply();
                            startActivity(new Intent(ListServiceCarActivity.this, ProductGroupActivity.class));
                        }
                    }, 2000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ShowCase();
                        }
                    }, 1200);

                }
                disableBack = false;
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                disableBack = false;
                G.stop_loading();
                swipeRefreshLayout.setRefreshing(false);
                G.toast("مشکل در برقراری ارتباط");
            }
        });

    }

    public void ShowCase() {
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view
        config.setShapePadding(50);
        config.setMaskColor(Color.parseColor("#cc222222"));

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, "ShoowCaseMain");

        sequence.setConfig(config);

        sequence.addSequenceItem(Utils.createCustomShowcaseView(this,AdapterListServiceCar.baztaviz, getString(R.string.showcase_baztaviz), getString(R.string.next_showcase)));
        sequence.addSequenceItem(Utils.createCustomShowcaseView(this, AdapterListServiceCar.noet, getString(R.string.showcase_note), getString(R.string.next_showcase)));
        sequence.start();
    }

    public void getJob_services(String key) {
        swipeRefreshLayout.setRefreshing(true);
        modelKhadamats.clear();
        try {
            JSONArray array = new JSONArray(G.preference.getString("myProductGroups", "[]"));
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                int id = obj.getInt("id");
                String title = obj.getString("title");
                int km_usage = obj.getInt("km_usage");
                if (key.length() > 0) {
                    if (title.contains(key)) {
                        modelKhadamats.add(new ModelKhadamat(id, title, "true", "true", "", 0, "", 0,false));
                    }
                } else {
                    modelKhadamats.add(new ModelKhadamat(id, title, "true", "true", "", 0, "", 0,false));
                }

            }
            if (array.length() == 0) {
                G.toast("هیچ گروه کالایی یافت نشد!");
                G.toast("لطفأ ابتدا گروه کالای خود را مشخص کنید!");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        G.preference.edit().putBoolean("changeProductGroup", true).apply();
                        startActivity(new Intent(ListServiceCarActivity.this, ProductGroupActivity.class));
                    }
                }, 2000);
            } else {
                if (modelKhadamats.size() == 0) {
                    G.toast("هیچ موردی یافت نشد!");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Recycler_service_car.setLayoutManager(new LinearLayoutManager(ListServiceCarActivity.this, RecyclerView.VERTICAL, false));
                // adapterListServiceCar = new AdapterListServiceCar(ListServiceCarActivity.this, mDBHelper.getListKhadamat(mDatabase),getIntent().getExtras().getInt("idCustomer"));
                adapterListServiceCar = new AdapterListServiceCar(ListServiceCarActivity.this, modelKhadamats, onItemClickService, getIntent().getExtras().getInt("idService"));
                Recycler_service_car.setAdapter(adapterListServiceCar);
                adapterListServiceCar.notifyDataSetChanged();
                G.stop_loading();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);

    }

    public void getJob_servicesOnline(String key) {
        disableBack = true;
        modelKhadamats.clear();
        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
//        Call<ResponseBody> request = api.getProduct_groups("job_category_id,eq," + G.preference.getInt("job_category_id", 1));
        Call<ResponseBody> request = api.getProduct_groups("id,gt,0");
        if (key.length() > 0) {
            request = api.getProduct_groupsBySearch("id,gt,0", "title,cs," + key);
//            request = api.getProduct_groupsBySearch("job_category_id,eq," + G.preference.getInt("job_category_id", 1), "title,cs," + key);

        } else {
//            G.loading(this);
            swipeRefreshLayout.setRefreshing(true);
        }
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
                            int id = obj.getInt("id");
                            String title = obj.getString("title");
                            if (checkProductGroup(id)) {
                                modelKhadamats.add(new ModelKhadamat(id, title, "true", "true", "", 0, "", 0,false));
                            }

                        }


                        if (key.equals("") && recordsServiceAvailable.length() <= 0) {

//                            listServiceAvailable();
                            G.stop_loading();
                        } else {
                            tempmodelKhadamats.clear();
                            if (recordsServiceAvailable.length() > 0) {
                                tempmodelKhadamats.addAll(modelKhadamats);
                                modelKhadamats.clear();
                                records = recordsServiceAvailable;
                                if (records.length() > 0) {
                                    for (int i = 0; i < records.length(); i++) {
                                        JSONObject obj = records.getJSONObject(i);
                                        for (int j = 0; j < tempmodelKhadamats.size(); j++) {
                                            if ((tempmodelKhadamats.get(j).getId() + "").equals(obj.getString("job_service_id"))) {
                                                boolean status = obj.getBoolean("status");
                                                if (status) {
                                                    modelKhadamats.add(tempmodelKhadamats.get(j));
                                                }

                                            }

                                        }
                                    }


                                }
                            }
                            G.Log(modelKhadamats.size() + "_2");
                            G.stop_loading();
                            swipeRefreshLayout.setRefreshing(false);
                        }


                        adapterListServiceCar.notifyDataSetChanged();

                    } else {
                        if (key.equals("")) {
                            G.toast("هیچ خدمت قابل ارائه\u200Cای در این دسته شغلی یافت نشد");
                        }
                    }
                } catch (JSONException | IOException e) {
                    G.toast("مشکل در تجزیه اطلاعات");
                    e.printStackTrace();
                }
                disableBack = false;
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                disableBack = false;
                G.stop_loading();
                swipeRefreshLayout.setRefreshing(false);
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

    public boolean checkProductGroup(int id) {
        try {
            JSONArray array = new JSONArray(G.preference.getString("myProductGroups", "[]"));
            for (int i = 0; i < array.length(); i++) {
                if (array.get(i).equals(id)) {
                    return true;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public JSONArray recordsServiceAvailable = new JSONArray();


    private void FindView() {
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        img_back = findViewById(R.id.img_back);
        Recycler_service_car = findViewById(R.id.Recycler_service_car);
        btn_save = findViewById(R.id.btn_save);
        add_text = findViewById(R.id.add_text);
        btnShowSavabegh = findViewById(R.id.showsavabegh);

    }

    private void onClick() {
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int AvgKm = 1000000000;
                int CountKm = 0;
                JSONArray array = new JSONArray();
                for (int c = 0; c < modelKhadamats.size(); c++) {
                    JSONObject object = new JSONObject();
                    ModelKhadamat model = modelKhadamats.get(c);
                    try {
                        object.put("id", model.getId());
                        object.put("name", model.getTitle());

                        object.put("selt", model.getSelectB());
                        object.put("selb", model.getSelectT());
                        object.put("type", model.getType());
                        object.put("type_id", model.getType_id());
                        object.put("km_usage", model.getKm_usage());
                        object.put("value", model.getValue());
                        object.put("send_msg", model.isSend_msg());
                        if (!model.getSelectB().equals(model.getSelectT())) {
                            CountKm++;
                            if (model.getKm_usage() < AvgKm) {
                                AvgKm = model.getKm_usage();
                            }

                            G.Log(model.getKm_usage() + "");

                            array.put(object);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                G.Log("detail_service: " + array.toString());
                G.Log("AvgKm: " + AvgKm);
                G.preference.edit().putBoolean("ChangeAvgKm",true).apply();
                G.preference.edit().putString("detail_service", array.toString()).apply();
                if(AvgKm<1000000000) {
                    G.preference.edit().putInt("AvgKm", AvgKm).apply();
                }else{
                    G.preference.edit().putInt("AvgKm", 5000).apply();
                }
                G.preference.edit().putInt("CountKm", CountKm).apply();


                finish();
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }
    public boolean disableBack=false;
    @Override
    public void onBackPressed() {
        if (!disableBack) {
            super.onBackPressed();
        }
    }
}