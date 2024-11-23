package ir.servicea.activity;

import static ir.servicea.app.G.context;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
import ir.servicea.adapter.AdapterJobCategory;
import ir.servicea.adapter.AdapterListProduceGroup;
import ir.servicea.app.DataBaseHelper;
import ir.servicea.app.G;
import ir.servicea.app.PreferenceUtil;
import ir.servicea.app.RecyclerItemClickListener;
import ir.servicea.model.ModelJobCategory;
import ir.servicea.model.SliderItem;
import ir.servicea.model.dbModel.ModelProduceGroup;
import ir.servicea.retrofit.Api;
import ir.servicea.retrofit.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class ProductGroupActivity extends AppCompatActivity {
    private TextView txt_tile_action_bar;
    private ImageView img_back, img_add_message;
    private RecyclerView recycle_produce_group;
    public static List<ModelProduceGroup> listGroup = new ArrayList<>();
    // public static List<ModelSaveKhadamat> save = new ArrayList<>();
    private AdapterListProduceGroup adapterListProduceGroup;
    private CheckBox checkAll;
    private boolean check = false;

    private Button btn_save;
    private AlertDialog alertDialogs_offer_group;
    private DataBaseHelper mDBHelper;
    private SQLiteDatabase mDatabase;
    String onvan = "";
    private AdapterListProduceGroup.OnItemClickListener onItemClickListener;
    private Handler handler;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Spinner spinner_job;
    private boolean fromrefresh = false;
    private EditText search;
    private RecyclerView recycle_done_service_type;

    public void onclickAlamrs(View v) {
        startActivity(new Intent(ProductGroupActivity.this, AlarmsActivity.class));
    }

    @Override
    public void onResume() {
        super.onResume();
        G.preference.edit().putInt("CasheSelectedJobCategory", -1).apply();
        G.Activity = this;
        context = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_group);
        G.Activity = this;
        context = this;
        FindView();
        onClick();
        txt_tile_action_bar.setText("انتخاب گروه کالاها");
        txt_tile_action_bar.setTypeface(G.Bold);
        mDBHelper = new DataBaseHelper(this);
        mDatabase = mDBHelper.getReadableDatabase();
        checkAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                check = b;
                for (int i = 0; i < listGroup.size(); i++) {
                    listGroup.get(i).setCheck(check);
                }
                adapterListProduceGroup.notifyDataSetChanged();
            }
        });
        onItemClickListener = new AdapterListProduceGroup.OnItemClickListener() {
            @Override
            public void onItemClick(ModelProduceGroup model, CheckBox item, AdapterListProduceGroup.ViewHolder holder, int position) {
                G.Log(model.getId() + " - " + model.isCheck());
                model.setCheck(item.isChecked());

                addServiceAvailable(position, model.isCheck());

            }
        };
        search = findViewById(R.id.search);
        handler = new Handler();
        search.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0 && !fromrefresh) {

                    handler.removeCallbacksAndMessages(null);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getJob_services(search.getText().toString(), listJobsIds.get(spinner_job.getSelectedItemPosition()));
                        }
                    }, 250);

                } else if (s.length() == 0) {
//                    getJob_services("", listJobsIds.get(spinner_job.getSelectedItemPosition()));
                    getJob_services("", G.preference.getInt("CasheSelectedJobCategory", 0));
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {


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
                        if (listJobsIds.size() > 0) {
                            fromrefresh = true;
//                            getJob_services("", listJobsIds.get(spinner_job.getSelectedItemPosition()));
                            getJob_services("", G.preference.getInt("CasheSelectedJobCategory", 0));
                        } else {

//                            getJob_services("", 0);
                        }
//                        search.setText("");
                    }
                }, 250);
            }
        });
        getJob_categories();

    }

    private List<String> listJobs = new ArrayList<>();
    private List<Integer> listJobsIds = new ArrayList<>();
    private ArrayAdapter spinnerAdapter;

    public class PG {
        public PG() {
        }

        public int id;
        public boolean status;
    }

    private List<PG> enables = new ArrayList<>();

    public void getJob_categories() {
        listJobs.clear();
        listJobsIds.clear();
        listJobs.add("دسته شغلی");
        listJobsIds.add(0);
        spinnerAdapter = new ArrayAdapter(ProductGroupActivity.this, R.layout.item_spiner, listJobs);
        ((ArrayAdapter) spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_job.setAdapter(spinnerAdapter);
        spinner_job.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!fromrefresh) {
//                    getJob_services("", listJobsIds.get(spinner_job.getSelectedItemPosition()));
                    getJob_services("", G.preference.getInt("CasheSelectedJobCategory", 0));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.getJob_categories();
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
                        int posi = 0;
                        List<ModelJobCategory> listJobCategory = new ArrayList<>();
                        ModelJobCategory modelJobCategory = new ModelJobCategory();
                        modelJobCategory.setId(-1);
                        modelJobCategory.setTitle("همه");
                        modelJobCategory.setStatus(1);

                        listJobCategory.add(modelJobCategory);
                        for (int i = 0; i < records.length(); i++) {
                            JSONObject obj = records.getJSONObject(i);
                            SliderItem sliderItem = new SliderItem();
                            int id = obj.getInt("id");
                            String title = obj.getString("title");
                            if (id == G.preference.getInt("job_category_id", 1)) {
                                posi = i + 1;
                            }
                            modelJobCategory = new ModelJobCategory();
                            modelJobCategory.setId(id);
                            modelJobCategory.setTitle(title);
                            modelJobCategory.setStatus(0);

                            listJobCategory.add(modelJobCategory);
                            listJobs.add(title);
                            listJobsIds.add(id);
                        }
                        recycle_done_service_type = findViewById(R.id.recycle_done_service_type);
                        recycle_done_service_type.setLayoutManager(new LinearLayoutManager(ProductGroupActivity.this, RecyclerView.HORIZONTAL, false));
                        AdapterJobCategory adapterJobCategory = new AdapterJobCategory(ProductGroupActivity.this, listJobCategory);
                        recycle_done_service_type.setAdapter(adapterJobCategory);
                        recycle_done_service_type.addOnItemTouchListener(
                                new RecyclerItemClickListener(context, recycle_done_service_type, new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        for (int i = 0; i < listJobCategory.size(); i++) {
                                            listJobCategory.get(i).setStatus(0);
                                        }
                                        saveIds();
                                        listJobCategory.get(position).setStatus(1);
                                        adapterJobCategory.notifyDataSetChanged();
                                        G.preference.edit().putInt("CasheSelectedJobCategory", listJobCategory.get(position).getId()).apply();
                                        getJob_services("", G.preference.getInt("CasheSelectedJobCategory", 0));
                                    }

                                    @Override
                                    public void onLongItemClick(View view, int position) {
                                        // do whatever
                                    }
                                })
                        );
                        spinnerAdapter.notifyDataSetChanged();
                        int finalPosi = posi;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                spinner_job.post(new Runnable() {
                                    public void run() {
                                        spinner_job.setSelection(finalPosi);
                                    }
                                });
                            }
                        }, 10);
                        getJob_services("", G.preference.getInt("CasheSelectedJobCategory", 0));
                    } else {
                        G.toast("هیچ دسته شغلی یافت نشد!");
                    }
                } catch (JSONException | IOException e) {
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

    public void saveIds() {
        for (int j = 0; j < listGroup.size(); j++) {
            for (int x = 0; x < enables.size(); x++) {
                PG pg = enables.get(x);
                if (pg.id == listGroup.get(j).getId()) {
                    enables.remove(x);
                }
            }
            PG pg = new PG();
            pg.id = listGroup.get(j).getId();
            pg.status = listGroup.get(j).isCheck();
            enables.add(pg);
        }
    }

    private void FindView() {
        spinner_job = findViewById(R.id.spinner_job);
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        img_back = findViewById(R.id.img_back);
        img_add_message = findViewById(R.id.img_add_message);
        checkAll = findViewById(R.id.checkAll);
        btn_save = findViewById(R.id.btn_save);
        recycle_produce_group = findViewById(R.id.recycle_produce_group);
    }

    private void onClick() {
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        img_add_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogOfferGroup(ProductGroupActivity.this);
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                G.stop_loading();
                G.toast("ثبت انجام شد");
                finish();
            }
        });
    }

    void DialogOfferGroup(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_item_dialog_offer_group, null);
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
        EditText edt_onvan_msg;
        Button btn_save;
        img_close = view.findViewById(R.id.img_close);
        edt_onvan_msg = view.findViewById(R.id.edt_onvan_msg);
        btn_save = view.findViewById(R.id.btn_save);
        onvan = edt_onvan_msg.getText().toString();
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogs_offer_group.dismiss();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_onvan_msg.getText().length() < 1) {
                    edt_onvan_msg.setError("متن را پر کنید");
                } else {

                    addJonService(edt_onvan_msg.getText().toString());
//                    listGroup.add(new ModelProduceGroup(edt_onvan_msg.getText().toString(), check));
                    adapterListProduceGroup.notifyDataSetChanged();
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

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }

    public void getJob_services(String key, int job_category_id) {
        if (job_category_id == 0) {
            job_category_id = G.preference.getInt("job_category_id", 1);
        }
        String where = "eq";
        if (job_category_id == -1) {
            where = "gt";
        }
        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.getProduct_groups("job_category_id," + where + "," + job_category_id);
        if (key.length() > 0) {
            swipeRefreshLayout.setRefreshing(true);
            request = api.getProduct_groupsBySearch("job_category_id," + where + "," + job_category_id, "title,cs," + key);

        } else {
            G.loading(this);
        }
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                assert response.body() != null;
                listGroup.clear();
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
                                String send_msgx = (obj.getString("send_msg") + "").replace("null", "0");
                                boolean send_msg = send_msgx.contains("1");
//                                check = checkProductGroup(id);
                                check = false;
                                listGroup.add(new ModelProduceGroup(id, title, km_usage, check, send_msg));


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
                recycle_produce_group.setLayoutManager(new LinearLayoutManager(ProductGroupActivity.this, RecyclerView.VERTICAL, false));
                adapterListProduceGroup = new AdapterListProduceGroup(ProductGroupActivity.this, listGroup, onItemClickListener);
//        adapterListProduceGroup = new AdapterListProduceGroup(ProductGroupActivity.this, mDBHelper.getListProductGroup(mDatabase));
                recycle_produce_group.setAdapter(adapterListProduceGroup);
                swipeRefreshLayout.setRefreshing(false);

                adapterListProduceGroup.notifyDataSetChanged();
                fromrefresh = false;


            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                fromrefresh = false;
                swipeRefreshLayout.setRefreshing(false);
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

    public boolean checkProductGroup(int id) {
        try {
            JSONArray array = new JSONArray(G.preference.getString("myProductGroups", "[]"));
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                if (object.getInt("id") == (id)) {
                    return true;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String ids_ServiceAvailable = "";

    public void listServiceAvailable() {

        G.loading(this);
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
                    G.Log(result);

                    JSONObject object = G.StringtoJSONObject(result);
                    JSONArray records = object.getJSONArray("records");
                    if (records.length() > 0) {
                        for (int i = 0; i < records.length(); i++) {
                            JSONObject obj = records.getJSONObject(i);
                            int job_id = obj.getInt("id");
                            ids_ServiceAvailable += "" + job_id + ",";
                            for (int j = 0; j < listGroup.size(); j++) {


                                if ((listGroup.get(j).getId() + "").equals(obj.getString("product_group_id"))) {
                                    boolean status = obj.getInt("status") == 1;
                                    listGroup.get(j).setCheck(status);
                                    listGroup.get(j).setJob_id(job_id);
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

                swipeRefreshLayout.setRefreshing(false);


            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                swipeRefreshLayout.setRefreshing(false);
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }


    public int countxx = 0;

    public void addServiceAvailable(int position, boolean status) {
        String id = listGroup.get(position).getId() + "";
        String d_id = PreferenceUtil.getD_id();
        String created_at = G.converToEn(DateFormat.format("yyyy-MM-dd HH:mm:ss", new Date()).toString());
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        JSONObject objectSend = new JSONObject();
        try {
            objectSend.put("service_center_id", d_id);
            objectSend.put("product_group_id", id);
            if (status) {
                objectSend.put("status", "1");
            } else {
                objectSend.put("status", "0");
            }
            objectSend.put("created_at", created_at);
            objectSend.put("updated_at", created_at);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Call<ResponseBody> request = api.checkProductGroupAvailable("service_center_id,eq," + d_id, "product_group_id,eq," + id);
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                try {
                    String result = G.getResult(response);
                    G.Log(call.request().toString());
                    G.Log(result);
                    JSONObject object = G.StringtoJSONObject(result);
                    JSONArray records = object.getJSONArray("records");
                    if (records.length() > 0) {
                        for (int i = 0; i < records.length(); i++) {
                            JSONObject obj = records.getJSONObject(i);
                            Call<ResponseBody> request = api.deleteProductGroupAvailable(obj.getInt("id") + "");
                            request.enqueue(new retrofit2.Callback<ResponseBody>() {
                                @Override
                                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                                    String result = G.getResult(response);
                                    G.Log(call.request().toString());
                                    G.Log(result);
                                    addServiceProductGroup(objectSend);
                                }

                                @Override
                                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                                }
                            });

                        }
                    } else {
                        addServiceProductGroup(objectSend);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
            }
        });
    }

    public void addServiceProductGroup(JSONObject object) {
        G.Log(object + "");
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.addProductGroupAvailable(G.returnBody(object.toString()));
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                String result = G.getResult(response);
                G.Log(call.request().toString());
                G.Log(result);
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
            }
        });
    }

    public void addJonService(String title) {

//        String d_id = PreferenceUtil.getD_id();
        G.loading(this);

        String created_at = G.converToEn(DateFormat.format("yyyy-MM-dd HH:mm:ss", new Date()).toString());
        JSONObject object = new JSONObject();
        try {
            int jobid = G.preference.getInt("job_category_id", 0);
            if (jobid <= 0) {
                G.toast("در قسمت پروفایل دسته شغلی خود را انتخاب کنید");
                return;
            }
            object.put("title", title);
            object.put("km_usage", 0);
            object.put("job_category_id", jobid + "");
            object.put("change_wage", "");
            object.put("send_msg", 0);
            object.put("status", 0);
            object.put("is_default", 0);
            object.put("created_at", created_at);
            object.put("updated_at", created_at);
            object.put("deleted_at", JSONObject.NULL);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);

        Call<ResponseBody> request = api.addProductGroup(G.returnBody(object.toString()));
        countxx++;
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                String result = G.getResult(response);
                G.stop_loading();
                if (result.length() > 0 && result.length() < 10) {

                    G.toast("با موفقیت ثبت شد!");
//                    getJob_services("", listJobsIds.get(spinner_job.getSelectedItemPosition()));
                    getJob_services("", G.preference.getInt("CasheSelectedJobCategory", 0));
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