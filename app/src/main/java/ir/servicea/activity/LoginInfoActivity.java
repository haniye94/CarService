package ir.servicea.activity;

import static android.os.Build.VERSION.SDK_INT;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;
import com.yalantis.ucrop.UCrop;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.servicea.R;
import ir.servicea.app.CircleTransform;
import ir.servicea.app.G;
import ir.servicea.app.PreferenceUtil;
import ir.servicea.app.RuntimePermissionsActivity;
import ir.servicea.app.StateOpenHelper;
import ir.servicea.app.UploadTask;
import ir.servicea.model.SliderItem;
import ir.servicea.model.State;
import ir.servicea.retrofit.Api;
import ir.servicea.retrofit.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInfoActivity extends RuntimePermissionsActivity {
    private Button btn_save_info;
    private TextInputLayout edt_phone;
    private TextInputEditText edt_first_name, edt_last_name, edt_address, edt_phone_number, edt_name_auto_service;
    private TextView txt_tile_action_bar, license, txt_detect_service;
    private Spinner spinner_ostan, spinner_city, spinner_job;
    private int province_id, city_id;
    private List<String> citiesList;
    private List<Integer> citiesListIDS;
    private final List<String> listJobs = new ArrayList<>();
    private final List<Integer> listJobsIds = new ArrayList<>();
    private final List<Boolean> listJobsReservation = new ArrayList<>();
    private SQLiteDatabase mDatabase;
    private List<State> stateList;
    private List<Integer> stateListIDS;
    private String citys, provinces;
    private PreferenceUtil preferenceUtil;
    private ImageView profile_iv, profile_iv2;
    private static final int SELECT_PICTURE = 100;
    private final int READ_EXTERNAL_REQUEST_CODE = 30;
    private final int RESULT_LOAD_IMAGE = 10;
    boolean image_value = false;
    Uri selectedImageUri;
    private String NameImage;

    private String imagepath1;
    private ImagePicker imagePicker;
    private CheckBox sayar, karshenasi, reservation;
    private ArrayAdapter spinnerAdapter;
    private String user_id = "0";
    private String d_id = "0";
    private ViewGroup location, change_number;
    private ImageView img_loc, edit_loc;
    private TextView txt_loc;

    private boolean isNewUser = true;

    private static final int PICK_IMAGE = 1;

    private File croppedImageFile;
    int spSelectedPosition = 0;

    boolean reserve_status = false;

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }

    public void onclickAlamrs(View v) {
        startActivity(new Intent(LoginInfoActivity.this, AlarmsActivity.class));
    }

    boolean first_rty = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_info);
        G.Activity = this;
        G.context = this;
        preferenceUtil = new PreferenceUtil(this);
        FindView();
        Typeface bold = Typeface.createFromAsset(getResources().getAssets(), "Fonts/IRANSans-Bold-web.ttf");
        Typeface eng = Typeface.createFromAsset(getResources().getAssets(), "Fonts/Core Mellow 35 Light.otf");
        btn_save_info.setTypeface(bold);
        license.setTypeface(eng);
        txt_tile_action_bar.setText("ثبت اطلاعات استادکار");
        txt_tile_action_bar.setTypeface(G.Bold);
        onClick();

        if (getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().getString("editPro") != null && getIntent().getExtras().getString("editPro").equals("editPro")) {
            edt_phone.setVisibility(View.VISIBLE);
        } else {
            edt_phone.setVisibility(View.GONE);
        }
        if (getIntent().hasExtra(G.CAN_EDIT_MOBILE)) {
            change_number.setVisibility(View.VISIBLE);
        } else {
            change_number.setVisibility(View.VISIBLE);
        }
        StateOpenHelper openHelper = new StateOpenHelper(this);
        openHelper.openDatabase();
        mDatabase = openHelper.getReadableDatabase();


        spinner_ostan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getCitiesOfState(stateList.get(position).getCityId());
                provinces = stateList.get(position).getCityName();
                province_id = stateListIDS.get(position);
                PreferenceUtil.cashOstan(position, parent.getSelectedItem().toString());

                if (PreferenceUtil.getCityPosition() > 0) {
//                    spinner_city.setSelection(preferenceUtil.getCityPosition());
                }
                if (!first_rty) {
                    closeKeyboard();
                    spinner_city.performClick();
                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            first_rty = false;
                        }
                    }, 1000);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                citys = citiesList.get(position);
                city_id = citiesListIDS.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getJob_categories();

        if (G.preference.getString("resultUri", "").length() > 3) {
            Uri resultUri = Uri.parse(G.preference.getString("resultUri", ""));
            Picasso.get().load(resultUri).error(R.drawable.ic_user).placeholder(R.drawable.ic_user).transform(new CircleTransform()).into(profile_iv);
        }
        if (G.preference.getString("resultUri2", "").length() > 3) {
            Uri resultUri2 = Uri.parse(G.preference.getString("resultUri2", ""));
            Picasso.get().load(resultUri2).error(R.drawable.backpro).placeholder(R.drawable.backpro).into(profile_iv2);
        }
        edt_phone_number.setEnabled(false);
        if (preferenceUtil != null || PreferenceUtil.getD_id() != null || PreferenceUtil.getD_id().length() <= 0) {
            edt_address.setText("");
            edt_first_name.setText("");
            edt_last_name.setText("");
            edt_phone_number.setText(PreferenceUtil.getPhone());
            edt_name_auto_service.setText("");
        } else {
            edt_address.setText(PreferenceUtil.getAddress());
            edt_first_name.setText(PreferenceUtil.getName());
            edt_last_name.setText(PreferenceUtil.getFamily());
            edt_phone_number.setText(PreferenceUtil.getPhone());
            spinner_job.setSelection(PreferenceUtil.getJobPosition());
//            spinner_city.setSelection(preferenceUtil.getCityPosition());
//            spinner_ostan.setSelection(preferenceUtil.getOstanPosition());
            edt_name_auto_service.setText(PreferenceUtil.getName_auto_service());

        }
        edt_first_name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {

                    edt_last_name.requestFocus();

                    return true;
                }
                return false;
            }
        });
        edt_last_name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {

                    edt_name_auto_service.requestFocus();

                    return true;
                }
                return false;
            }
        });

        edt_name_auto_service.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    closeKeyboard();
                    spinner_ostan.performClick();
                    return true;
                }
                return false;
            }
        });
        edt_address.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    closeKeyboard();
                    spinner_job.performClick();
                    return true;
                }
                return false;
            }
        });
        Log.d("ActivateRad", "onCreate: ActivateRad6:" + G.preference.getBoolean("ActivateRad", false));

        if (G.preference.getBoolean("ActivateRad", false)) {
            Log.d("ActivateRad", "onCreate: ActivateRad5:" + G.preference.getBoolean("ActivateRad", false));
            getProfile();
        } else {
            getStatesList();
        }

//        if (!preferenceUtil.getType_service().equals("null")) {
//            txt_detect_service.setText(preferenceUtil.getType_service());
//        } else {
//            txt_detect_service.setText("انتخاب نوع خدمت");
//        }
    }

    public void closeKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void getJob_categories() {
        listJobs.clear();
        listJobsIds.clear();
        listJobsReservation.clear();
        spinnerAdapter = new ArrayAdapter(LoginInfoActivity.this, R.layout.item_spiner, listJobs);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_job.setAdapter(spinnerAdapter);

        spinner_job.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                G.preference.edit().putString("job_category_name", listJobs.get(position)).apply();
                G.preference.edit().putInt("job_category_id", listJobsIds.get(position)).apply();
                if (!listJobsReservation.get(position)) {
                    reservation.setChecked(false);
                    reservation.setEnabled(false);
                }
                else  {
                    reservation.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        G.loading(G.Activity);
        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.getJob_categories();
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        String result = response.body().string();

                        JSONObject object = G.StringtoJSONObject(result);
                        JSONArray records = object.getJSONArray("records");
                        if (records.length() > 0) {

                            for (int i = 0; i < records.length(); i++) {
                                JSONObject obj = records.getJSONObject(i);
                                int id = obj.getInt("id");
                                String title = obj.getString("title");
                                boolean reserve_status = obj.getBoolean("reserve_status");
                                listJobs.add(title);
                                listJobsIds.add(id);
                                listJobsReservation.add(reserve_status);
                            }

                            spinnerAdapter.notifyDataSetChanged();
                        } else {
                            G.toast("هیچ دسته شغلی یافت نشد!");
                        }
                    } catch (JSONException | IOException e) {
                        G.toast("مشکل در تجزیه اطلاعات");
                        e.printStackTrace();
                    }
                }
                G.stop_loading();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.Log("LoginInfoActivity:onFailur3:" + t.getMessage());

                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

    public void getProfile() {

        String d_id = PreferenceUtil.getD_id();
//G.toast(d_id);
//G.toast(PreferenceUtil.getUser_id());
        if (d_id != null && d_id.length() > 0) {
            G.loading(this);
            Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
            Call<ResponseBody> request = api.getProfile("id,eq," + d_id);
            request.enqueue(new retrofit2.Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    if (response.body() != null) {
                        assert response.body() != null;
                        try {
                            String result = response.body().string();
                            JSONObject object = G.StringtoJSONObject(result);
                            JSONArray records = object.getJSONArray("records");
                            if (records.length() > 0) {
                                for (int i = 0; i < records.length(); i++) {
                                    JSONObject services_center = records.getJSONObject(i);

                                    JSONObject obj = services_center.getJSONObject("user_id");

                                    String user_id = obj.getString("id");
                                    PreferenceUtil.cashUser_id(user_id);
                                    String name = obj.getString("f_name");
                                    String lastname = obj.getString("l_name");
                                    String phone = obj.getString("mobile");
                                    String sex = obj.getString("sex");
                                    String b_date = obj.getString("birthdate");
                                    String province_id = obj.getString("province_id");
                                    String city_id = obj.getString("city_id");
                                    G.preference.edit().putString("province_id", province_id).apply();
                                    G.preference.edit().putString("city_id", city_id).apply();
                                    if (obj.has("profile_photo")) {
                                        String profile_photo = obj.getString("profile_photo");
                                        G.preference.edit().putString("profile_photo", profile_photo).apply();
                                        Picasso.get().load(G.PreImagesURL + "profiles/" + profile_photo).error(R.drawable.ic_user).placeholder(R.drawable.ic_user).transform(new CircleTransform()).into(profile_iv);
                                    }
                                    if (obj.has("header_photo")) {
                                        String header_photo = obj.getString("header_photo");
                                        G.preference.edit().putString("header_photo", header_photo).apply();
                                        Picasso.get().load(G.PreImagesURL + "headers/" + header_photo).error(R.drawable.backpro).placeholder(R.drawable.backpro).into(profile_iv2);
                                    }
                                    if (obj.has("password")) {
                                        String password = obj.getString("password");
                                        String now_password = G.preference.getString("PASSWORD", "");
                                        if (now_password.length() < 3) {
                                            G.preference.edit().putString("PASSWORD", password).apply();
                                        }
                                    }
                                    if (services_center.has("latitude") && services_center.has("longitude")) {
                                        String latitude = services_center.getString("latitude");
                                        String longitude = services_center.getString("longitude");
                                        G.preference.edit().putString("location_lat", latitude).apply();
                                        G.preference.edit().putString("location_lon", longitude).apply();
                                    }
                                    String d_id = services_center.getString("id");
                                    PreferenceUtil.cashD_id(d_id);
                                    String shop_name = services_center.getString("center_name");
                                    String shop_phone = services_center.getString("phone");
                                    String address = services_center.getString("address");
                                    String job_category = services_center.getString("job_category_id");
                                    String opentime = services_center.getString("f_open").replace("null", "");
                                    String closetime = services_center.getString("f_close").replace("null", "");
                                    String opentime2 = services_center.getString("l_open").replace("null", "");
                                    String closetime2 = services_center.getString("l_close").replace("null", "");
                                    Log.d("LoginInfoActivity:", "onResponse: " + opentime);
                                    int num_branch = services_center.getInt("numOfBranch");
                                    int waiting_space = services_center.getBoolean("waiting_place") ? 1 : 0;
                                    int catering_service = services_center.getBoolean("bar_serv") ? 1 : 0;
                                    boolean status = services_center.getBoolean("status");
                                    G.preference.edit().putBoolean("ServiceCenterStatus", status).apply();

                                    G.preference.edit().putBoolean("sayar", services_center.getBoolean("mobile_services")).apply();
                                    G.preference.edit().putBoolean("karshenasi", services_center.getBoolean("checking")).apply();
                                    G.preference.edit().putBoolean("reservation", services_center.getBoolean("reservation")).apply();
                                    G.preference.edit().putBoolean("holiday", services_center.getBoolean("holidays")).apply();
                                    sayar.setChecked(G.preference.getBoolean("sayar", false));
                                    karshenasi.setChecked(G.preference.getBoolean("karshenasi", false));
                                    reservation.setChecked(G.preference.getBoolean("reservation", false));
                                    if (services_center.has("charge_total") && services_center.has("charge_remain")) {
                                        int charge_total = services_center.getInt("charge_total");
                                        int charge_remain = services_center.getInt("charge_remain");
                                        if (charge_total > 10) {
                                            charge_total = charge_total / 10;
                                        }
                                        if (charge_remain > 10) {
                                            charge_remain = charge_remain / 10;
                                        }
                                        G.preference.edit().putInt("charge_total", charge_total).apply();
                                        G.preference.edit().putInt("charge_remain", charge_remain).apply();
                                    } else {
                                        G.preference.edit().putInt("charge_total", 0).apply();
                                        G.preference.edit().putInt("charge_remain", 0).apply();
                                    }

                                    PreferenceUtil.preferenceUtil.edit().putString("openTime", opentime).apply();
                                    PreferenceUtil.preferenceUtil.edit().putString("closeTime", closetime).apply();
                                    PreferenceUtil.preferenceUtil.edit().putString("openTime2", opentime2).apply();
                                    PreferenceUtil.preferenceUtil.edit().putString("closeTime2", closetime2).apply();
                                    PreferenceUtil.preferenceUtil.edit().putInt("numOfBranch", num_branch).apply();
                                    PreferenceUtil.preferenceUtil.edit().putInt("waiting", waiting_space).apply();
                                    PreferenceUtil.preferenceUtil.edit().putInt("catering", catering_service).apply();

                                    if (job_category.length() > 0) {
                                        try {
                                            int jobcat = Integer.parseInt(job_category);
                                            G.preference.edit().putInt("job_category_id", jobcat).apply();
                                        } catch (NumberFormatException nfe) {
                                            System.out.println("Could not parse " + nfe);
                                        }

                                    }

                                    for (int j = 0; j < listJobsIds.size(); j++) {
                                        if (Integer.parseInt(job_category) == listJobsIds.get(j)) {
                                            spSelectedPosition = j;
                                            spinner_job.setSelection(spSelectedPosition);
                                            G.preference.edit().putString("job_category_name", listJobs.get(j)).apply();
                                            if (!listJobsReservation.get(j)) {
                                                reservation.setChecked(false);
                                                reservation.setEnabled(false);
                                            }
                                            else  {
                                                reservation.setEnabled(true);
                                            }

                                        }

                                    }


                                    edt_address.setText(address);
                                    edt_first_name.setText(name);
                                    edt_last_name.setText(lastname);
                                    edt_phone_number.setText(phone);
                                    edt_name_auto_service.setText(shop_name);

//                                        sayar.setChecked(G.preference.getBoolean("sayar", false));
//                                        karshenasi.setChecked(G.preference.getBoolean("karshenasi", false));

//                                G.preference.edit().putString("location_lat", "34.8037818").apply();
//                                G.preference.edit().putString("location_lon", "48.5292809").apply();

                                    txt_loc.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            edit_loc.performClick();
                                        }
                                    });
                                    edit_loc.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            startActivity(new Intent(LoginInfoActivity.this, MapxActivity.class));
                                        }
                                    });
                                    location.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            startActivity(new Intent(LoginInfoActivity.this, MapxActivity.class));
                                        }
                                    });
                                    PreferenceUtil.cashPhone(phone);
                                    PreferenceUtil.cashInfo(name, lastname, address, shop_name);
                                    StateOpenHelper openHelper = new StateOpenHelper(G.Activity);
                                    openHelper.openDatabase();
                                    SQLiteDatabase mDatabase = openHelper.getReadableDatabase();
//                                if (province.length() > 0) {
//                                    Cursor cursor = mDatabase.query("Tbl_Ostan", null, null, null, null, null, null);
//                                    cursor.moveToFirst();
//                                    List<String> stateName = new ArrayList<>();
//                                    for (int x = 0; x < cursor.getCount(); x++) {
//                                        State state = new State();
//                                        state.setCityName(cursor.getString(cursor.getColumnIndex("Title")));
//                                        state.setCityId(cursor.getInt(cursor.getColumnIndex("ID")));
//                                        if ((state.getCityId() + "").equals(province)) {
//                                            PreferenceUtil.cashOstan(Integer.parseInt(province), state.getCityName());
////                                            spinner_ostan.setSelection(state.getCityId() - 1);
//                                            PreferenceUtil.cashCity(Integer.parseInt(city) - 1, state.getCityName());
//
//                                            getCitiesOfState(state.getCityId());
////                                            G.Log(state.getCityId() + " " + state.getCityName());
//                                        }
//
//                                        stateName.add(state.getCityName());
//                                        cursor.moveToNext();
//                                    }
//
//
//                                }
//                                if (city.length() > 0) {
//                                    Cursor cursor = mDatabase.query("Tbl_shahrestan", null, "PK_Ostan = ?", new String[]{province}, null, null, null);
//                                    cursor.moveToFirst();
//                                    for (int x = 0; x < cursor.getCount(); x++) {
//                                        State state = new State();
//                                        state.setCityName(cursor.getString(cursor.getColumnIndex("Title")));
//                                        state.setCityId(cursor.getInt(cursor.getColumnIndex("ID")));
//                                        if ((state.getCityId() + "").equals(city)) {
//                                            PreferenceUtil.cashCity(Integer.parseInt(city) - 1, state.getCityName());
//
////                                            G.Log(state.getCityId() + " " + state.getCityName());
//
//                                        }
//                                        cursor.moveToNext();
//                                    }
//
//
//                                }


                                }
                            } else {
                                G.toast("مشکل در دریافت اطلاعات");
                            }

                        } catch (JSONException | IOException e) {
                            G.toast("مشکل در تجزیه اطلاعات");
                            e.printStackTrace();
                        }
                        getStatesList();
                    }
                    G.stop_loading();

                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    G.Log("LoginInfoActivity:onFailur5:" + t.getMessage());

                    G.stop_loading();
                    G.toast("مشکل در برقراری ارتباط");
                }
            });

        } else {
            getStatesList();
        }


    }

    private void FindView() {
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        btn_save_info = findViewById(R.id.btn_save_info);
        license = findViewById(R.id.license);
        spinner_ostan = findViewById(R.id.spinner_ostan);
        spinner_city = findViewById(R.id.spinner_city);
        spinner_job = findViewById(R.id.spinner_job);
        edt_first_name = findViewById(R.id.edt_first_name);
        edt_last_name = findViewById(R.id.edt_last_name);
        edt_phone_number = findViewById(R.id.edt_phone_number);
        edt_address = findViewById(R.id.edt_address);
        txt_detect_service = findViewById(R.id.txt_detect_service);
        edt_phone = findViewById(R.id.edt_phone);

        profile_iv = findViewById(R.id.profile_iv);
        profile_iv2 = findViewById(R.id.profile_iv2);
        sayar = findViewById(R.id.sayar);
        karshenasi = findViewById(R.id.karshenasi);
        reservation = findViewById(R.id.reservation);
        edt_name_auto_service = findViewById(R.id.edt_name_auto_service);
        location = findViewById(R.id.location);
        img_loc = findViewById(R.id.img_loc);
        txt_loc = findViewById(R.id.txt_loc);
        edit_loc = findViewById(R.id.edit_loc);
        change_number = findViewById(R.id.changeNumber);
    }

    public void checkExitUser(String phone) {


    }

    public void sendOperatorInfo(String name, String lastName, String shop_name, String shop_phone, String phone, String email, String sex, String b_date, int category, String service, String openTime, String closeTime, String openTime2, String closeTime2, int numOfBranch, int waiting, int catering, int province, int city, String address) {
        G.loading(LoginInfoActivity.this);
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        JSONObject object = new JSONObject();
        try {

            String created_at = G.converToEn(DateFormat.format("yyyy-MM-dd HH:mm:ss", new Date()).toString());
            created_at = G.converToEn(created_at);
            object.put("username", "U" + phone);
            object.put("f_name", name);
            object.put("l_name", lastName);
            object.put("mobile", phone);
            object.put("national_code", "");
            object.put("user_type", G.UserType);
            object.put("role_id", G.RoleId);
            object.put("profile_photo", G.preference.getString("profile_photo", ""));
            object.put("header_photo", G.preference.getString("header_photo", ""));
            object.put("password", G.preference.getString("PASSWORD", ""));
            object.put("sex", sex);
            object.put("birthdate", b_date);
            object.put("province_id", province);
            object.put("city_id", city);
            if (user_id.length() > 0) {
                object.put("updated_at", created_at);
            } else {
                object.put("created_at", created_at);
                object.put("updated_at", created_at);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        G.Log(object.toString());
        d_id = PreferenceUtil.getD_id();
        user_id = PreferenceUtil.getUser_id();
        Call<ResponseBody> request = api.newOperator(G.returnBody(object.toString()));
        if (user_id.length() > 0) {
            request = api.editOperator(user_id, G.returnBody(object.toString()));
            isNewUser = false;
        }
        request.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                G.Log(call.request().toString());
                String result = G.getResult(response);
                G.Log(result);
                Log.d("LoginInfoActivity", "object: " + object);
                Log.d("LoginInfoActivity", "request: " + call.request());
                Log.d("LoginInfoActivity", "result: " + result);
                if (result.length() > 0 && result.length() < 10) {
                    G.preference.edit().putBoolean("ActivateRad", true).apply();
                    Log.d("ActivateRad", "onCreate: ActivateRad4:" + G.preference.getBoolean("ActivateRad", false));

                    if (user_id.length() <= 0) {
                        user_id = result;
                    }
                    PreferenceUtil.cashUser_id(user_id);
                    JSONObject object = new JSONObject();
                    try {
                        String created_at = G.converToEn(DateFormat.format("yyyy-MM-dd HH:mm:ss", new Date()).toString());
                        object.put("user_id", user_id);
                        object.put("center_name", shop_name);
                        object.put("job_category_id", category);
                        object.put("phone", phone);
                        object.put("address", address);
                        if (openTime.length() > 0) object.put("f_open", openTime);
                        if (closeTime.length() > 0) object.put("f_close", closeTime);
                        if (openTime2.length() > 0) object.put("l_open", openTime2);
                        if (closeTime2.length() > 0) object.put("l_close", closeTime2);
                        object.put("numOfBranch", numOfBranch);
                        object.put("waiting_place", waiting);
                        object.put("bar_serv", catering);
                        object.put("holidays", G.preference.getBoolean("holiday", false) ? 1 : 0);
                        object.put("mobile_services", sayar.isChecked() ? 1 : 0);
                        object.put("checking", karshenasi.isChecked() ? 1 : 0);
                        object.put("reservation", reservation.isChecked() ? 1 : 0);
                        if (d_id.length() > 0) {
                            object.put("updated_at", created_at);
                        } else {
                            object.put("created_at", created_at);
                            object.put("updated_at", created_at);
                        }

                        object.put("app_type", 1);
//                        if (G.preference.getString("location_lat", "").length() > 4 && G.preference.getString("location_lon", "").length() > 4) {
//                            object.put("status", 0);
//                        } else {
//                        }
                        object.put("latitude", G.preference.getString("location_lat", ""));
                        object.put("longitude", G.preference.getString("location_lon", ""));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    G.Log(object.toString());
                    sendServicCenterInfo(object, phone);
                } else {
                    G.stop_loading();
                    G.toast("مشکل در ثبت اطلاعات کاربر");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.Log("LoginInfoActivity:onFailur6:" + t.getMessage());

                G.Log(t.getMessage());
                G.stop_loading();
                Toast.makeText(LoginInfoActivity.this, "مشکل در برقراری ارتباط", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addScoreForServiceCenter(String phone) {

        String created_at = G.converToEn(DateFormat.format("yyyy-MM-dd HH:mm:ss", new Date()).toString());

        JSONObject object = new JSONObject();
        try {
            object.put("service_center_id", 0);
            object.put("phone", phone);
            object.put("created_at", created_at);
            object.put("updated_at", created_at);
            object.put("deleted_at", null);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.addScoreForServiceCenter(G.returnBody(object.toString()));

        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                G.Log("addScoreForServiceCenter:success");
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط با سرور");
                G.Log("addScoreForServiceCenter:failed/" + t.getMessage());
            }
        });


    }


    public void sendServicCenterInfo(JSONObject object, String phone) {
        G.loading(LoginInfoActivity.this);
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        d_id = PreferenceUtil.getD_id();
        Call<ResponseBody> request = api.newServicesCenter(G.returnBody(object.toString()));
        if (d_id.length() > 0) {
            request = api.editServicesCenter(d_id, G.returnBody(object.toString()));
        }
        request.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                G.Log("LoginInfoActivity:request" + call.request());
                String result = G.getResult(response);
                G.Log("LoginInfoActivity:result:" + result);
                Log.d("LoginInfoActivity", "request: " + call.request());
                Log.d("LoginInfoActivity", "result: " + result);
                Log.d("LoginInfoActivity", "object: " + object);

                if (result.length() > 0 && result.length() < 10) {
                    if (d_id.length() <= 0) {
                        d_id = result;
                    }

                    if (!isNewUser) {
                        addScoreForServiceCenter(phone);
                    }

                    PreferenceUtil.cashD_id(d_id);

                    if (G.preference.getString("location_lat", "").length() > 4 && G.preference.getString("location_lon", "").length() > 4) {
                        G.toast("اطلاعات با موفقیت ثبت شد");
                        PreferenceUtil.cachLogin();

                        Intent intent = new Intent(LoginInfoActivity.this, MainActivity.class);
                        startActivity(intent);
                        finishAffinity();
                    } else {
                        G.preference.edit().putBoolean("edt_loc", true).apply();
                        Intent intent = new Intent(LoginInfoActivity.this, MapxActivity.class);
                        startActivity(intent);
                    }
//                    finish();
                } else {
                    G.toast("مشکل در ثبت اطلاعات");
                }
                G.stop_loading();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.Log("LoginInfoActivity:onFailur7:" + t.getMessage());
                Log.d("LoginInfoActivity", "onFailure7: " + t.getMessage());

                G.stop_loading();
                Toast.makeText(LoginInfoActivity.this, "مشکل در برقراری ارتباط", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onClick() {
        if (G.preference.getString("location_lat", "").length() > 4 && G.preference.getString("location_lon", "").length() > 4) {
            btn_save_info.setText("ذخیره تغییرات");
        } else {
            btn_save_info.setText("ثبت موقعیت مکانی");
        }
        btn_save_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = PreferenceUtil.getPhone();
                String first_name = edt_first_name.getText().toString();
                String last_name = edt_last_name.getText().toString();
                String address = edt_address.getText().toString();
//                String phone = edt_phone_number.getText().toString();
                String shop_phone = edt_phone_number.getText().toString();
                String name_auto = edt_name_auto_service.getText().toString();
                boolean checkvalid = true;
                if (first_name.equals("")) {
                    edt_first_name.setError(" نام را به درستی وارد کنید");
                    checkvalid = false;
                }
                if (last_name.equals("")) {
                    edt_last_name.setError("نام خانوادگی را به درستی وارد کنید");
                    checkvalid = false;
                }
                if (address.equals("")) {
                    edt_address.setError("آدرس را به درستی وارد کنید");
                    checkvalid = false;
                }

                if (phone.equals("")) {
                    edt_phone_number.setError("شماره موبایل را به درستی وارد کنید");
                    checkvalid = false;
                }
                if (checkvalid) {
                    String email = "example@gmail.com";
                    String b_date = "2020-10-20";

                    String service = PreferenceUtil.preferenceUtil.getString("service", "");

                    String openTime = PreferenceUtil.preferenceUtil.getString("openTime", "");
                    String closeTime = PreferenceUtil.preferenceUtil.getString("closeTime", "");
                    String openTime2 = PreferenceUtil.preferenceUtil.getString("openTime2", "");
                    String closeTime2 = PreferenceUtil.preferenceUtil.getString("closeTime2", "");
                    int numOfBranch = PreferenceUtil.preferenceUtil.getInt("numOfBranch", 0);
                    int waiting = PreferenceUtil.preferenceUtil.getInt("waiting", 0);
                    int catering = PreferenceUtil.preferenceUtil.getInt("catering", 0);
                    int category = PreferenceUtil.preferenceUtil.getInt("job_category_id", 1);
                    String sex = "M";
                    if (reservation.isChecked() && (openTime.isEmpty() || closeTime.isEmpty() || openTime2.isEmpty() || closeTime2.isEmpty())) {
                        showReservationSnackbar();
                    } else {
                        sendOperatorInfo(first_name, last_name, name_auto, shop_phone, phone, email, sex, b_date, category, service, openTime, closeTime, openTime2, closeTime2, numOfBranch, waiting, catering, province_id, city_id, address);
                        PreferenceUtil.cashInfo(first_name, last_name, address, name_auto);
                    }
                }

            }
        });
        spinner_job.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                PreferenceUtil.cashJob(i, String.valueOf(adapterView.getSelectedItemId()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        txt_detect_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginInfoActivity.this, DetectServiceActivity.class));
            }
        });


        profile_iv.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                selectTypeImage(1);
            }
        });
        profile_iv2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                selectTypeImage(2);
            }
        });

        change_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                G.preference.edit().putBoolean("sendAgain", false).apply();
                G.preference.edit().putBoolean("changeNumber", true).apply();
                G.preference.edit().putString("phoneAgain", PreferenceUtil.getPhone()).apply();
                startActivity(new Intent(LoginInfoActivity.this, RegisterActivity.class));
                finish();
            }
        });
    }

    private void showReservationSnackbar() {
        Snackbar snackbar = Snackbar.make(reservation, "در صورت فعالسازی امکان رزرو خدمات،\nاز منوی نوع خدمت ساعت کاری را مشخص نمایید", Snackbar.LENGTH_LONG).setAction("متوجه شدم", view1 -> {
                    startActivity(new Intent(LoginInfoActivity.this, DetectServiceActivity.class));
                })
                /*.setAction("منصرف شدم", v -> {
                    reservation.setChecked(false);
                })*/
                // .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                .setAnchorView(btn_save_info);
        final View snackbarView = snackbar.getView();
        snackbarView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        final TextView tv = snackbarView.findViewById(R.id.snackbar_text);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, 24);
        snackbar.show();
    }


    public int typeid = 1;

    public void selectTypeImage(int type) {
        typeid = type;
        G.preference.edit().putInt("imagetype", type).apply();
        if (ActivityCompat.checkSelfPermission(LoginInfoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else {
            checkRunTimePermission();
        }
    }

    public void selImage() {
        ImagePicker.with(this).compress(1024).maxResultSize(1080, 1080).start();

    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    //    public void selImage() {
//
//        imagePicker = new ImagePicker(this, /* activity non null*/
//                null, /* fragment nullable*/
//                imageUri -> {/*on image picked */
//
//
//                    UCrop.Options options = new UCrop.Options();
//                    options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
//                    options.setCompressionQuality(75);
////                    options.setHideBottomControls(true);
//                    options.setFreeStyleCropEnabled(true);
//                    options.setToolbarColor(ContextCompat.getColor(getApplication(), R.color.white));
//                    options.setStatusBarColor(ContextCompat.getColor(getApplication(), R.color.white));
////                    options.setActiveWidgetColor(ContextCompat.getColor(getApplication(), R.color.colorPrimary));
//                    options.setToolbarTitle("ویرایش تصویر");
//                    Uri destinationUri = Uri.fromFile(new File(getFilename()));
//                    imagepath1 = destinationUri.getPath();
//                    UCrop.of(imageUri, destinationUri)
//                            .withOptions(options)
//                            .withMaxResultSize(750, 750)
//                            .start(LoginInfoActivity.this);
//                });
//
//
//        imagePicker.choosePicture(true);
//    }
    public void goUcrop(Uri uri) {

        UCrop.Options options = new UCrop.Options();
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        options.setCompressionQuality(75);
//                    options.setHideBottomControls(true);
        options.setFreeStyleCropEnabled(true);
        options.setToolbarColor(ContextCompat.getColor(getApplication(), R.color.button));
        options.setStatusBarColor(ContextCompat.getColor(getApplication(), R.color.button));

        options.setRootViewBackgroundColor(ContextCompat.getColor(getApplication(), R.color.white));
        options.setActiveControlsWidgetColor(Color.parseColor("#2489ed"));
        options.setToolbarWidgetColor(Color.parseColor("#ffffff"));
        options.setCropFrameColor(Color.parseColor("#2489ed"));
//        options.setCropGridCornerColor(Color.parseColor("#2489ed"));
//                    options.setActiveWidgetColor(ContextCompat.getColor(getApplication(), R.color.colorPrimary));
        options.setToolbarTitle("ویرایش تصویر");
        Uri destinationUri = Uri.fromFile(new File(getFilename()));
        imagepath1 = destinationUri.getPath();
        UCrop.of(uri, destinationUri).withOptions(options).withMaxResultSize(750, 750).start(LoginInfoActivity.this);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectTypeImage(typeid);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            if (uri != null) {
                goUcrop(uri);
            }
        } else if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            if (resultUri != null) {
                croppedImageFile = new File(resultUri.getPath());

                // Handle different image types
                if (G.preference.getInt("imagetype", 1) == 1) {
                    G.preference.edit().putString("resultUri", resultUri.toString()).apply();
                    Picasso.get().load(resultUri).error(R.drawable.guy).placeholder(R.drawable.guy).transform(new CircleTransform()).into(profile_iv);
                    G.preference.edit().putString("upload_pushe", "profiles").apply();
                } else if (G.preference.getInt("imagetype", 1) == 2) {
                    G.preference.edit().putString("resultUri2", resultUri.toString()).apply();
                    Picasso.get().load(resultUri).into(profile_iv2);
                    G.preference.edit().putString("upload_pushe", "headers").apply();
                }
                new UploadTask().execute(imagepath1);

                new UploadTask().execute(croppedImageFile.getPath());
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            Toast.makeText(this, "خطایی رخ داده است", Toast.LENGTH_SHORT).show();
            final Throwable cropError = UCrop.getError(data);
            if (cropError != null) {
                G.Log("ProfileActivity" + "Crop error: " + cropError.getMessage() + cropError);
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private String getFilename() {
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//        File file = new File(Environment.getExternalStorageDirectory().getPath(), "" + getResources().getString(R.string.app_name));
        if (!file.exists()) {
            file.mkdirs();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentDateandTime = sdf.format(new Date());
        Random r = new Random();
        int i1 = r.nextInt(9999999 - 1000000 + 1) + 1000000;
        currentDateandTime = currentDateandTime + "_" + i1;
        String uriSting = (file.getAbsolutePath() + "/" + currentDateandTime + ".jpg");
        currentDateandTime = G.converToEn(currentDateandTime);
        NameImage = currentDateandTime;
        if (G.preference.getInt("imagetype", 1) == 1) {
            G.preference.edit().putString("profile_photo", NameImage + ".jpg").apply();
        } else if (G.preference.getInt("imagetype", 1) == 2) {
            G.preference.edit().putString("header_photo", NameImage + ".jpg").apply();
        }
        uriSting = G.converToEn(uriSting);
        uriSting = G.NumToEn(uriSting);
        return uriSting;
    }

    private void checkRunTimePermission() {

        boolean firstAct = false;
        G.toast("لطفأ دسترسی لازم برای انتخاب تصویر را به اپلیکیشن بدهید!");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                firstAct = true;
                startActivity(intent);

            }
        }
        if (!firstAct) {
            String[] permissionArrays = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissionArrays, 11111);
            }
        }

    }

    private void getStatesList() {

        stateList = new ArrayList<>();
        stateListIDS = new ArrayList<>();
        List<String> stateName = new ArrayList<>();
        SpinnerAdapter spinnerAdapter = new ArrayAdapter(this, R.layout.item_spiner, stateName);
        ((ArrayAdapter) spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_ostan.setAdapter(spinnerAdapter);


        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.getProvince();
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                assert response.body() != null;
                try {
                    String result = response.body().string();
                    JSONObject object = G.StringtoJSONObject(result);
                    JSONArray records = object.getJSONArray("records");
                    int position = 0;

                    if (records.length() > 0) {

                        for (int i = 0; i < records.length(); i++) {
                            State state = new State();
                            JSONObject obj = records.getJSONObject(i);
                            int id = obj.getInt("id");
                            String name = obj.getString("name");
                            state.setCityName(name);
                            state.setCityId(id);
                            if ((String.valueOf(id)).equals(G.preference.getString("province_id", "0"))) {
                                position = i;
                            }
                            stateList.add(state);
                            stateListIDS.add(state.getCityId());
                            stateName.add(state.getCityName());
                        }
                        ((ArrayAdapter<?>) spinnerAdapter).notifyDataSetChanged();
                        spinner_ostan.setSelection(position);
                    } else {
                        G.toast("مشکل در دریافت اطلاعات");
                    }
                } catch (JSONException | IOException e) {
                    G.toast("مشکل در تجزیه اطلاعات");
                    e.printStackTrace();
                }
                G.stop_loading();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.Log("LoginInfoActivity:onFailur1:" + t.getMessage());
                Log.d("LoginInfoActivity", "onFailure1: " + t.getMessage());

                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

    private void getCitiesOfState(int stateId) {
        if (stateId > 0) {

            citiesList = new ArrayList<>();
            citiesListIDS = new ArrayList<>();

            SpinnerAdapter spinnerAdapter = new ArrayAdapter(LoginInfoActivity.this, R.layout.item_spiner, citiesList);
            ((ArrayAdapter) spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_city.setAdapter(spinnerAdapter);

            Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
            Call<ResponseBody> request = api.getCity("province_id,eq," + stateId);
            request.enqueue(new retrofit2.Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    assert response.body() != null;
                    try {
                        String result = response.body().string();
                        JSONObject object = G.StringtoJSONObject(result);
                        JSONArray records = object.getJSONArray("records");
                        int position = 0;

                        if (records.length() > 0) {

                            for (int i = 0; i < records.length(); i++) {
                                State state = new State();
                                JSONObject obj = records.getJSONObject(i);
                                int id = obj.getInt("id");
                                String name = obj.getString("name");
                                state.setCityName(name);
                                state.setCityId(id);
                                if ((String.valueOf(id)).equals(G.preference.getString("city_id", "0"))) {
                                    position = i;
                                }
                                citiesList.add(state.getCityName());
                                citiesListIDS.add(state.getCityId());

                            }
                            ((ArrayAdapter<?>) spinnerAdapter).notifyDataSetChanged();
                            spinner_city.setSelection(position);
                            G.Log(G.preference.getString("city_id", "0"));
                            G.Log(String.valueOf(position));
                        } else {
                            G.toast("مشکل در دریافت اطلاعات");
                        }
                    } catch (JSONException | IOException e) {
                        G.toast("مشکل در تجزیه اطلاعات");
                        e.printStackTrace();
                    }
                    G.stop_loading();
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    G.Log("LoginInfoActivity:onFailur2:" + t.getMessage());
                    Log.d("LoginInfoActivity", "onFailure2: " + t.getMessage());

                    G.stop_loading();
                    G.toast("مشکل در برقراری ارتباط");
                }
            });
        }

    }

    void openImageChooser() {
        selectedImageUri = null;
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }


//    public String saveImage(Bitmap myBitmap) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
//        File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
//        if (!wallpaperDirectory.exists()) {  // have the object build the directory structure, if needed.
//            wallpaperDirectory.mkdirs();
//        }
//
//        try {
//            File f = new File(wallpaperDirectory, Calendar.getInstance().getTimeInMillis() + ".jpg");
//            f.createNewFile();
//            FileOutputStream fo = new FileOutputStream(f);
//            fo.write(bytes.toByteArray());
//            MediaScannerConnection.scanFile(this,
//                    new String[]{f.getPath()},
//                    new String[]{"image/jpeg"}, null);
//            fo.close();
//            Log.d("TAG", "File Saved::---&gt;" + f.getAbsolutePath());
//
//            return f.getAbsolutePath();
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
//        return "";
//    }

    @Override
    public void onPermissionsGranted(int requestCode) {
        if (requestCode == READ_EXTERNAL_REQUEST_CODE) openImageChooser();
    }

    @Override
    public void onPermissionsDeny(int requestCode) {
        Toast.makeText(LoginInfoActivity.this, "شما به برنامه مجور استفاده از فایل را نداده اید.", Toast.LENGTH_SHORT).show();
        checkRunTimePermission();
    }

    @Override
    public void onResume() {

        super.onResume();
        G.Activity = this;
        G.context = this;


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                G.preference.edit().putBoolean("edt_loc", false).apply();

                if (G.preference.getString("location_lat", "").length() > 4 && G.preference.getString("location_lon", "").length() > 4) {
                    String lat = G.preference.getString("location_lat", "");
                    String lon = G.preference.getString("location_lon", "");
//                    String address = G.preference.getString("formatted_address", "");
//                    txt_loc.setText(address);
                    location.setVisibility(View.VISIBLE);
                    String loc_imgl = "https://api.mapbox.com/styles/v1/mapbox/outdoors-v11/static/pin-m-star+23a2cd(" + lon + "," + lat + ")/" + lon + "," + lat + ",15,0,30/600x300@2x?attribution=false&logo=false&access_token=pk.eyJ1IjoiZXJmYW5zYiIsImEiOiJjbGJraXQzZWcwMHFpM3ZtZTR4cXMxcGpkIn0.b5C8oRkOZzqvpZh2EStNwg";
//                    Log.e("loc_imgl", loc_imgl);

                    getMapAddress(lat, lon);
                    Picasso.get().load(loc_imgl).error(R.drawable.noimage).placeholder(R.drawable.noimage).into(img_loc);
                    btn_save_info.setText("ثبت اطلاعات");
                } else {
                    location.setVisibility(View.GONE);
                }
            }
        }, 1000);

    }

    public void getMapAddress(String lat, String lng) {
        G.loading(this);
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.getMapAddress(lat, lng);
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                G.Log(call.request().toString());
                G.stop_loading();
                if (response.body() != null) {
                    try {
                        String result = response.body().string();
                        G.Log(result);
                        JSONObject object = G.StringtoJSONObject(result);
                        if (object.has("status")) {
                            String status = object.getString("status");
                            if (status.equals("OK")) {
                                String neighbourhood = (object.getString("neighbourhood") + "").replace("null", "");
                                String formatted_address = (object.getString("formatted_address") + "").replace("null", "");
                                if (neighbourhood.length() == 0 && object.has("formatted_address")) {
                                    String[] addressTitle = object.getString("formatted_address").split("،");
                                    neighbourhood = addressTitle[1] + "،" + addressTitle[2];
                                }
                                String city = object.getString("city").trim();
                                txt_loc.setText(formatted_address);
                            } else {
                                G.toast("اطلاعات آدرس دریافت نشد!");
                            }
                        }
                    } catch (JSONException | IOException e) {
                        G.toast("مشکل در تجزیه اطلاعات آدرس");
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

}