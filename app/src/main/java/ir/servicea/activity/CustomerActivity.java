package ir.servicea.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.servicea.app.Constants;
import ir.servicea.app.DataBaseHelper;
import ir.servicea.app.G;
import ir.servicea.R;
import ir.servicea.retrofit.Api;
import ir.servicea.retrofit.RetrofitClient;
import ir.servicea.adapter.AdapterListCustomer;
import ir.servicea.model.dbModel.ModelCustomer;
import ir.servicea.app.PreferenceUtil;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class CustomerActivity extends AppCompatActivity {
    private TextView txt_tile_action_bar;
    private ImageView img_back, search_customer;
    private LinearLayout ly_empty;
    private RecyclerView recycle_list_customer;
    private AdapterListCustomer adapterListCustomer;
    private List<ModelCustomer> listCustomers = new ArrayList<>();
    private TextInputLayout edt_phone, edt_name;
    private TextInputEditText edt_phone_number, edt_nameFamily;
    private TextView txt_search_name, txt_search_phone;
    private DataBaseHelper mDBHelper;
    private SQLiteDatabase mDatabase;
    private AdapterListCustomer.OnItemClickListener onItemClickCustomer;
    public static Activity customer;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<ModelCustomer> productList = new ArrayList<>();
    private ProgressBar loadingPB;

    public int page = 1;

    boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        G.Activity = this;
        G.context = this;
        FindView();
        onClick();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                swipeRefreshLayout.setRefreshing(true);
                new Handler().postDelayed(() -> listCustomer(""), 200);
            }
        });
        mDBHelper = new DataBaseHelper(this);
        mDatabase = mDBHelper.getReadableDatabase();
        customer = this;
        txt_tile_action_bar.setText("لیست مشتریان");
        txt_tile_action_bar.setTypeface(G.Bold);
//        if (!mDBHelper.getListCustomers(mDatabase).isEmpty()) {
//            adapterListCustomer = new AdapterListCustomer(CustomerActivity.this, mDBHelper.getListCustomers(mDatabase), onItemClickCustomer);
//            recycle_list_customer.setAdapter(adapterListCustomer);
//        }
        productList = new ArrayList<>();
        recycle_list_customer.setLayoutManager(new LinearLayoutManager(CustomerActivity.this, RecyclerView.VERTICAL, false));
        adapterListCustomer = new AdapterListCustomer(CustomerActivity.this, productList, onItemClickCustomer);
        recycle_list_customer.setAdapter(adapterListCustomer);
        new Handler().postDelayed(() -> listCustomer(""), 200);


        recycle_list_customer.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading && productList.size() >= 20 && last_key.length()==0) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == productList.size() - 1) {
                        isLoading = true;
                        page++;
                        loadingPB.setVisibility(View.VISIBLE);
                        recycle_list_customer.post(new Runnable() {
                            @Override
                            public void run() {
                                recyclerView.smoothScrollToPosition(adapterListCustomer.getItemCount() - 1);
                            }
                        });
                        listCustomer(last_key);
                    }
                }
            }
        });

    }

    @Override
    public void onResume(){
        super.onResume();
        G.Activity = this;
        G.context = this;
    }

    private void FindView() {
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        img_back = findViewById(R.id.img_back);
        recycle_list_customer = findViewById(R.id.recycle_list_customer);
        txt_search_name = findViewById(R.id.txt_search_name);
        txt_search_phone = findViewById(R.id.txt_search_phone);
        edt_name = findViewById(R.id.edt_name);
        edt_phone = findViewById(R.id.edt_phone);
        ly_empty = findViewById(R.id.ly_empty);
        search_customer = findViewById(R.id.search_customer);
        edt_phone_number = findViewById(R.id.edt_phone_number);
        edt_nameFamily = findViewById(R.id.edt_nameFamily);
        loadingPB = findViewById(R.id.idPBLoading);

        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.button));
    }

    Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
String last_key = "";
    public void listCustomer(String key) {
        last_key = key;
        if (key.length() > 0) {
            productList.clear();
            key = G.converToEn(key);
            page = 0;
        } else {
            if (page == 0) {
                productList.clear();
                page = 1;
            }
        }
        if (page == 1) {
            swipeRefreshLayout.setRefreshing(true);
        }
        if (PreferenceUtil.getD_id() != null) {
            String d_id = PreferenceUtil.getD_id();
            Call<ResponseBody> request = api.listCustomer("center_id,eq," + d_id, "car_service_center_id,eq," + d_id, page);
            if (key.length() >= 2) {
//                request = api.searchCustomer("center_id,eq," + d_id, "f_name,cs," + key, "l_name,cs," + key, "mobile,cs," + key);
            }
            String finalKey = key;
            request.enqueue(new retrofit2.Callback<ResponseBody>() {
                @SuppressLint({"StaticFieldLeak", "NotifyDataSetChanged"})
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    if (finalKey.length() > 0) {
                        productList.clear();
                    }
//                    G.Log(call.request().toString());
                    String result = G.getResult(response);
                    JSONObject object = G.StringtoJSONObject(result);
                    JSONArray array = G.ObjecttoArray(object, "records");
                    if (array.length() > 0) {
                        try {
                            for (int l = 0; l < array.length(); l++) {
                                JSONObject info = array.getJSONObject(l);
                                int user_id = info.getInt("cust_id");
                                String name = info.getString("f_name");
                                String lastname = info.getString("l_name");
                                String sex = info.getString("sex");
                                if (sex.contains("M") || sex.contains("m")) {
                                    sex = "آقا";
                                }
                                if (sex.contains("F") || sex.contains("f")) {
                                    sex = "خانم";
                                }
                                String birth_date = info.getString("birthdate");
                                String phone = info.getString("mobile");
                                String register_date = info.getString("cust_created_at");

                                int car_id = 0;
                                String car_tag = "";
                                int car_plate_type = 1;
                                String car_name = "";
                                String car_model = "";
                                String car_type = "";
                                String fuel_type = "";
                                int car_name_id = 0, car_tip_id = 0, car_model_id = 0, fuel_type_id = 0,car_company_id=0;
                                if (info.has("car_id")) {
                                    car_id = info.getInt("car_id");
                                    car_tag = info.getString("car_plate");
                                    car_plate_type = info.getInt(Constants.CAR_PLATE_TYPE);
                                    String car_company_name = (info.getString("car_company_name") + "").replace("null", "");
                                    String car_tip = (info.getString("car_tip") + "").replace("null", "");
                                    car_name = info.getString("car_name");
                                    if (car_company_name.length() > 0) {
                                        car_name = car_company_name + " - " + car_name;
                                    }
                                    if (car_tip.length() > 0) {
                                        car_name = car_name + " - " + car_tip;
                                    }
                                    car_model = info.getString("car_model");
                                    car_type = info.getString("car_tip");
                                    fuel_type = info.getString("fuel_type");
                                    car_name_id = info.getInt("car_name_id");
                                    if (!(info.getString("car_tip_id") + "").contains("null")) {
                                        car_tip_id = info.getInt("car_tip_id");
                                    }
//                                    if(!info.getString("car_model_id").contains("null")) {
                                        car_model_id = info.getInt("car_model_id");
                                        car_company_id = info.getInt("car_company_id");
//                                    }
                                    fuel_type_id = info.getInt("fuel_type_id");
                                }
                                ModelCustomer modelCustomer = new ModelCustomer();
                                modelCustomer.setId(user_id);
                                modelCustomer.setFirst_name(name);
                                modelCustomer.setLast_name(lastname);
                                modelCustomer.setDate_birthday(birth_date);
                                modelCustomer.setGender(sex);
                                modelCustomer.setPhone(phone);
                                modelCustomer.setPlak(car_tag);
                                modelCustomer.setPlak_type(Constants.findById(car_plate_type));
                                modelCustomer.setName_car(car_name);
                                modelCustomer.setType_fuel(fuel_type);
                                modelCustomer.setCar_name_id(car_name_id);
                                modelCustomer.setCar_tip_id(car_tip_id);
                                modelCustomer.setCar_model_id(car_model_id);
                                modelCustomer.setCar_company_id(car_company_id);
                                modelCustomer.setFuel_type_id(fuel_type_id);
                                modelCustomer.setCar_id(car_id);
                                Log.d("Service!", "onClick: 1" + "id_car:"+car_id);

                                modelCustomer.setDate_save_customer(register_date);
                                String full_name = name+" "+lastname;
                                if (finalKey.length() >= 1) {
                                    if (full_name.contains(finalKey) || name.contains(finalKey) || lastname.contains(finalKey) || phone.contains(finalKey)) {
                                        productList.add(modelCustomer);
                                    }
                                } else {
                                    productList.add(modelCustomer);
                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    if (page == 1) {
                        if (productList.size() <= 0) {
                            ly_empty.setVisibility(View.VISIBLE);
                            recycle_list_customer.setVisibility(View.GONE);
                        } else {
                            recycle_list_customer.setVisibility(View.VISIBLE);
                            ly_empty.setVisibility(View.GONE);
                        }
                    }
                    if (page > 1) {
                        loadingPB.setVisibility(View.GONE);
                    } else {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                    adapterListCustomer.notifyDataSetChanged();
                    isLoading = false;

                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    swipeRefreshLayout.setRefreshing(false);
                    loadingPB.setVisibility(View.GONE);
                    G.toast("مشکل در برقراری ارتباط");
                }
            });

        }
    }

    public void deleteCustomer(String cust_id) {
        G.loading(CustomerActivity.this);
        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        JSONObject object = new JSONObject();
        try {
            String deleted_at = G.converToEn(DateFormat.format("yyyy-MM-dd HH:mm:ss", new Date()).toString());
            object.put("deleted_at", deleted_at);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        G.Log(cust_id);
        Call<ResponseBody> request = api.deleteCustomer(cust_id + "", G.returnBody(object.toString()));
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                assert response.body() != null;
                G.stop_loading();
                String result = G.getResult(response);
                Log.e("dsdsdsd", result);
                if (result.length() > 0 && result.length() < 10) {
                    finish();
                    startActivity(new Intent(CustomerActivity.this, CustomerActivity.class));
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

    private Handler mHandler = new Handler();

    private void onClick() {
        txt_search_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_name.setVisibility(View.VISIBLE);
                edt_phone.setVisibility(View.GONE);
                txt_search_phone.setTextColor(getResources().getColor(R.color.text_low_dark));
                txt_search_name.setTextColor(getResources().getColor(R.color.button));
            }
        });

        txt_search_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_name.setVisibility(View.GONE);
                edt_phone.setVisibility(View.VISIBLE);
                txt_search_phone.setTextColor(getResources().getColor(R.color.button));
                txt_search_name.setTextColor(getResources().getColor(R.color.text_low_dark));
            }
        });

        edt_nameFamily.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    listCustomer(edt_nameFamily.getText().toString());
                    return true;
                }
                return false;
            }
        });
        Runnable search = new Runnable() {
            @Override
            public void run() {
                if (edt_nameFamily.getText().toString().length() > 0) {
                    listCustomer(edt_nameFamily.getText().toString());
                } else if (edt_nameFamily.getText().toString().length() == 0) {
                    listCustomer("");
                }
            }
        };
        edt_nameFamily.addTextChangedListener(new TextWatcher() {

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
                swipeRefreshLayout.setRefreshing(true);
                if (mHandler != null) {
                    mHandler.removeCallbacks(search);
                    mHandler.postDelayed(search, 1000);
                }


            }
        });

        search_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = edt_phone_number.getText().toString();
                String nameFamily = edt_nameFamily.getText().toString();
                if (!TextUtils.isEmpty(phone) && TextUtils.isEmpty(nameFamily)) {
                    listCustomer(phone);
                } else if (TextUtils.isEmpty(phone) && !TextUtils.isEmpty(nameFamily)) {
                    listCustomer(nameFamily);
                } else {
                    Toast.makeText(CustomerActivity.this, "بر اساس شماره یا نام و نام خانوادگی جستجو کنید", Toast.LENGTH_SHORT).show();
                }
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        onItemClickCustomer = new AdapterListCustomer.OnItemClickListener() {
            @Override
            public void onItemClick(ModelCustomer model, ImageView item, AdapterListCustomer.ViewHolder holder, int position) {

                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(CustomerActivity.this, R.style.BottomSheetDialogTheme);
                View bottomSheetView = LayoutInflater.from(CustomerActivity.this)
                        .inflate(R.layout.layout_button_sheet_customer, (LinearLayout) holder.itemView.findViewById(R.id.ly_bottom_sheet_lang));
                bottomSheetDialog.setCancelable(true);
                bottomSheetDialog.setContentView(bottomSheetView);
                TextView txt_info_name, txt_info_plak;
                LinearLayout ly_show_info, ly_edit_customer, ly_service_customer, ly_save_service, ly_send_message, ly_call_customer, ly_delete_customer;
                txt_info_name = bottomSheetDialog.findViewById(R.id.txt_info_name);
                ly_show_info = bottomSheetDialog.findViewById(R.id.ly_show_info);
                ly_edit_customer = bottomSheetDialog.findViewById(R.id.ly_edit_customer);
                ly_service_customer = bottomSheetDialog.findViewById(R.id.ly_service_customer);
                ly_save_service = bottomSheetDialog.findViewById(R.id.ly_save_service);
                ly_send_message = bottomSheetDialog.findViewById(R.id.ly_send_message);
                ly_call_customer = bottomSheetDialog.findViewById(R.id.ly_call_customer);
                ly_delete_customer = bottomSheetDialog.findViewById(R.id.ly_delete_customer);
                txt_info_plak = bottomSheetDialog.findViewById(R.id.txt_info_plak);
                txt_info_name.setTypeface(G.ExtraBold);
                txt_info_plak.setTypeface(G.ExtraBold);
                txt_info_name.setText(model.getFirst_name() + " " + model.getLast_name());
                txt_info_plak.setText(model.getName_car());

                ly_show_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog.dismiss();
                        Intent intent = new Intent(CustomerActivity.this, InformationCustomersActivity.class);
                        intent.putExtra("idCustomer", model.getId() + "");

                        intent.putExtra("id_car", model.getCar_id() + "");
                        intent.putExtra("firstName", model.getFirst_name());
                        intent.putExtra("lastName", model.getLast_name());
                        intent.putExtra("phone", model.getPhone());
                        intent.putExtra("nameCar", model.getName_car());
                        intent.putExtra("plak", model.getPlak());
                        intent.putExtra(Constants.CAR_PLATE_TYPE, model.getPlak_type());
                        intent.putExtra("gender", model.getGender());
                        intent.putExtra("date_birthday", model.getDate_birthday());
                        intent.putExtra("type_fule", model.getType_fuel());
                        intent.putExtra("date_save", model.getDate_save_customer());
                        intent.putExtra("type_car", model.getType_car());
                        intent.putExtra("car_name_id", model.getCar_name_id());
                        intent.putExtra("car_tip_id", model.getCar_tip_id());
                        intent.putExtra("car_model_id", model.getCar_model_id());
                        intent.putExtra("car_company_id", model.getCar_company_id());
                        intent.putExtra("fuel_type_id", model.getFuel_type_id());

                        CustomerActivity.this.startActivity(intent);
                    }
                });

                ly_edit_customer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog.dismiss();
                        Intent intent = new Intent(CustomerActivity.this, AddCustomerActivity.class);
                        intent.putExtra("idCustomer", model.getId() + "");
                        intent.putExtra("id_car", model.getCar_id() + "");
                        intent.putExtra("firstName", model.getFirst_name());
                        intent.putExtra("lastName", model.getLast_name());
                        intent.putExtra("phone", model.getPhone());
                        intent.putExtra("nameCar", model.getName_car());
                        intent.putExtra("plak", model.getPlak());
                        intent.putExtra(Constants.CAR_PLATE_TYPE, model.getPlak_type());
                        intent.putExtra("gender", model.getGender());
                        intent.putExtra("date_birthday", model.getDate_birthday());
                        intent.putExtra("type_fule", model.getType_fuel());
                        intent.putExtra("date_save", model.getDate_save_customer());
                        intent.putExtra("type_car", model.getType_car());
                        intent.putExtra("car_name_id", model.getCar_name_id());
                        intent.putExtra("car_tip_id", model.getCar_tip_id());
                        intent.putExtra("car_model_id", model.getCar_model_id());
                        intent.putExtra("car_company_id", model.getCar_company_id());
                        intent.putExtra("fuel_type_id", model.getFuel_type_id());
                        CustomerActivity.this.startActivity(intent);
                    }
                });
                ly_service_customer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog.dismiss();
                        Intent intent = new Intent(CustomerActivity.this, LastServiseDoneActivity.class);
//                       G.Log(model.getId()+"");
                        intent.putExtra("idCustomer", model.getId());
                        intent.putExtra("idCar", model.getCar_id());
                        startActivity(intent);

                    }
                });

                ly_save_service.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog.dismiss();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (mDBHelper != null) {
                                    mDBHelper.deleteHistoryKhadamt(G.preference.getInt("idService", 0) + "");
                                }
                            }
                        }, 0);
                        Log.d("Service!", "onClick: " + "id_car:"+ model.getCar_id());
                        Intent intent = new Intent(CustomerActivity.this, AddServicesActivity.class);
                        intent.putExtra("idCustomer", model.getId() + "");
                        intent.putExtra(Constants.CAR_ID, model.getCar_id()+"");
                        Log.d("CARRRR", "onResponse:CustomerActivity:onClick " + model.getCar_id());
                        intent.putExtra("firstName", model.getFirst_name());
                        intent.putExtra("lastName", model.getLast_name());
                        intent.putExtra("phone", model.getPhone());
                        intent.putExtra("nameCar", model.getName_car());
                        intent.putExtra("plak", model.getPlak());
                        intent.putExtra(Constants.CAR_PLATE_TYPE, model.getPlak_type());
                        intent.putExtra("id_customer", model.getId());
                        intent.putExtra("description", "");
                        intent.putExtra("gender", model.getGender());
                        intent.putExtra("date_birthday", model.getDate_birthday());
                        intent.putExtra("type_fule", model.getType_fuel());
                        intent.putExtra("date_save", model.getDate_save_customer());
                        intent.putExtra("type_car", model.getType_car());
                        intent.putExtra("car_name_id", model.getCar_name_id());
                        intent.putExtra("car_tip_id", model.getCar_tip_id());
                        intent.putExtra("car_model_id", model.getCar_model_id());
                        intent.putExtra("car_company_id", model.getCar_company_id());
                        intent.putExtra("fuel_type_id", model.getFuel_type_id());
                        intent.putExtra("finish", "finish");
                        intent.putExtra("fromMain", false);
                        CustomerActivity.this.startActivity(intent);

                    }
                });

                ly_send_message.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog.dismiss();
                        Intent intent = new Intent(CustomerActivity.this, SendMessageActivity.class);
                        intent.putExtra("firstName", model.getFirst_name());
                        intent.putExtra("lastName", model.getLast_name());
                        intent.putExtra("phone", model.getPhone());
                        CustomerActivity.this.startActivity(intent);
                    }
                });

                ly_call_customer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog.dismiss();
                        String phone = model.getPhone();
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                        startActivity(intent);
                    }
                });

                ly_delete_customer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog.dismiss();
                        deleteCustomer(model.getId() + "");

                    }
                });

                bottomSheetDialog.show();

            }
        };
    }

    public void onclickAlamrs(View v) {
        startActivity(new Intent(CustomerActivity.this, AlarmsActivity.class));
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }

}