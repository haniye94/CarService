package ir.tehranOil.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;
import ir.tehranOil.app.CalendarTool;
import ir.tehranOil.app.Constants;
import ir.tehranOil.app.DataBaseHelper;
import ir.tehranOil.app.G;
import ir.tehranOil.app.PLakUtils;
import ir.tehranOil.app.PlakTextWatcher;
import ir.tehranOil.app.PreferenceUtil;
import ir.tehranOil.model.CarModel;
import ir.tehranOil.model.CarName;
import ir.tehranOil.model.CarTip;
import ir.tehranOil.model.FuelType;
import ir.tehranOil.model.SliderItem;
import ir.tehranOil.retrofit.Api;
import ir.tehranOil.retrofit.RetrofitClient;
import ir.servicea.R;
import kr.ry4nkim.objectspinner.ObjectSpinner;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import ir.tehranOil.app.Constants.*;

public class AddCustomerActivity extends AppCompatActivity {
    private EditText edt1, edt2, edt3, edt4, edt5, edt6, edt7, edt8;
    private Button btn_save, btn_cancle;
    private TextView txt_tile_action_bar;
    private ImageView img_back;
    private Spinner spin_gender;
    private TextView txt_benzini, txt_dezeli, txt_doghane, text_hibrid, txt_date_customer;
    private PersianDatePickerDialog mDatePicker;
    private TextInputEditText edt_first_name, edt_last_name, edt_phone_number;

    private DataBaseHelper mDBHelper;
    private String type = "1";
    private PreferenceUtil preferenceUtil;
    public ScrollView scrollView;
    private PersianCalendar persianCalendar;
    private String typeFuel = "1";
    private long id = 0;
    private List<String> listgen = new ArrayList<>();
    private List<String> list_type_car = new ArrayList<>();
    private String gender, type_car;
    private TextWatcher tt;
    private ArrayAdapter<String> liveSearch;
    private String[] cars_name = {};
    private String[] cars_name_id = {};

    private String[] cars_company = {};
    private String[] cars_company_id = {};
    private boolean tryfirst = false;
    private ObjectSpinner spin_type_car, fuel_typesx, car_modelsx;

    private ViewGroup ly_plk_general,ly_plk_taxi, ly_plk_edari,ly_plk_entezami,ly_plk_malolin,ly_plk_azad_new,ly_plk_azad_old,ly_pelak_type ;


    private PLAK_TYPE plak_type = PLAK_TYPE.PLAK_GENERAL;

    private ViewGroup plak_layout = ly_plk_general;

    private Intent intentThatOpenAddCustomer;

    private String plakString;

    private int selectedCityForPlkIndex;

    private String selectedCityForPlk;

    private TextView iv_plk_type_menu, tv_plk_type_general, tv_plk_type_azad_new, tv_plk_type_azad_old;

    private boolean isVisiblePlakLayout = false;

    private boolean isEditCustomer = false;

    private Spinner spinner_plk_azad_old;


    @Override
    public void onResume() {
        super.onResume();
        G.Activity = this;
        G.context = this;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        G.Activity = this;
        G.context = this;
        boolean status = G.preference.getBoolean("ServiceCenterStatus", false);
        if (!status) {
            G.toast(getResources().getString(R.string.statusfalse));
            finish();
        }
        G.context = this;
        G.Activity = this;
        mDBHelper = new DataBaseHelper(this);
        preferenceUtil = new PreferenceUtil(this);
        intentThatOpenAddCustomer = getIntent();
        if (intentThatOpenAddCustomer.hasExtra(Constants.CAR_PLATE_TYPE)){
            plak_type = (PLAK_TYPE) intentThatOpenAddCustomer.getSerializableExtra(Constants.CAR_PLATE_TYPE);
            plakString =  intentThatOpenAddCustomer.getStringExtra("plak");
            selectedCityForPlkIndex =  intentThatOpenAddCustomer.getIntExtra(Constants.PLAK_AZAD_OLD_CITY_INDEX, 0);
            Log.d("getPlakValue22", "setPlakForEdit: plakString:" + plakString );
            Log.d("PLAK", "setPlakForEdit: selectedCityForPlkIndex1:" + selectedCityForPlkIndex );
        }
        FindView();
        onClick();
        changePlakLayout();
        setPlakAzadOldSpinner();
        setPlakForEdit(plakString);

        txt_tile_action_bar.setText("ثبت مشتریان");
        txt_tile_action_bar.setTypeface(G.Bold);
        getcars_company();

        scrollView = findViewById(R.id.scrollviewx);
        G.disableScroll(scrollView);
        /*edt_phone_number.setText(preferenceUtil.getNewCustomerPhone());
        edt1.setText(preferenceUtil.getNewCustomerPlak1());
        edt2.setText(preferenceUtil.getNewCustomerPlak2());
        edt3.setText(preferenceUtil.getNewCustomerPlak3());
        edt4.setText(preferenceUtil.getNewCustomerPlak4());
        edt5.setText(preferenceUtil.getNewCustomerPlak5());
        edt6.setText(preferenceUtil.getNewCustomerPlak6());
        edt7.setText(preferenceUtil.getNewCustomerPlak7());
        edt8.setText(preferenceUtil.getNewCustomerPlak8());
*/

        String plak = getPlakValueForSearch();
        //String plak = edt1.getText().toString() + edt2.getText().toString() + edt3.getText().toString() + edt4.getText().toString() + edt5.getText().toString() + edt6.getText().toString() + edt7.getText().toString() + edt8.getText().toString();
        if(true){
            checkExitCar(plak);
        }
        onClickPlak();
        TextView descheader = findViewById(R.id.descheader);
        descheader.setText("این مشتری تا کنون ثبت نشده است");
        if (!getIntent().getExtras().getString("firstName").equals("null")) {
            isEditCustomer = true;
            descheader.setText("این مشتری قبلأ ثبت شده است");
            edt_first_name.setText(getIntent().getExtras().getString("firstName"));
            edt_last_name.setText(getIntent().getExtras().getString("lastName"));
            edt_phone_number.setText(getIntent().getExtras().getString("phone"));
            if (getIntent().getExtras().getInt("car_name_id") > 0) {
                car_name_id = getIntent().getExtras().getInt("car_name_id");
                car_tip_id = getIntent().getExtras().getInt("car_tip_id");
                car_model_id = getIntent().getExtras().getInt("car_model_id");
                car_company_id = getIntent().getExtras().getInt("car_company_id");
                fuel_type_id = getIntent().getExtras().getInt("fuel_type_id");
            }


            String date = getIntent().getExtras().getString("date_birthday");
            if (date.contains("-") && date.contains(":") && date.contains(" ")) {
                CalendarTool calendarTool = new CalendarTool();
                String[] date_time = date.split(" ");
                String[] dates = date_time[0].split("-");
                calendarTool.setGregorianDate(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
                date = calendarTool.getIranianDate() + " " + date_time[1];

            } else if (date.contains("-")) {
                date = date.replace(" ", "");
                CalendarTool calendarTool = new CalendarTool();
                String[] dates = date.split("-");
                calendarTool.setGregorianDate(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
                date = calendarTool.getIranianDate();

            }
            txt_date_customer.setText(date.replace("-", "/"));
            String sex = getIntent().getExtras().getString("gender");
            G.Log("Gender " + sex);
            if (sex.contains("آقا") || sex.contains("مرد") || sex.contains("M") || sex.contains("m")) {
                spin_gender.post(new Runnable() {
                    public void run() {
                        spin_gender.setSelection(1);
                    }
                });
            } else if (sex.equals("خانم") || sex.equals("زن") || sex.contains("F") || sex.contains("f")) {
                spin_gender.post(new Runnable() {
                    public void run() {
                        spin_gender.setSelection(2);
                    }
                });
            }


            if (getIntent().getExtras().getString("type_carcccccc") != null) {
                if (getIntent().getExtras().getString("type_car").equals("سواری")) {
                    spin_type_car.post(new Runnable() {
                        public void run() {
                            spin_type_car.setSelection(0);
                        }
                    });
                } else if (getIntent().getExtras().getString("type_car").equals("باری")) {
                    spin_type_car.post(new Runnable() {
                        public void run() {
                            spin_type_car.setSelection(1);
                        }
                    });
                }
                if (getIntent().getExtras().getString("type_car").equals("شاسی")) {
                    spin_type_car.post(new Runnable() {
                        public void run() {
                            spin_type_car.setSelection(2);
                        }
                    });
                } else if (getIntent().getExtras().getString("type_car").equals("ماشین سنگین")) {
                    spin_type_car.post(new Runnable() {
                        public void run() {
                            spin_type_car.setSelection(3);
                        }
                    });
                }
            } else {
//                spin_type_car.post(new Runnable() {
//                    public void run() {
//                        spin_type_car.setSelection(0);
//                    }
//                });
            }

//            edt_name_car.setText(getIntent().getExtras().getString("nameCar"));

            String plakString = getIntent().getExtras().getString("plak");
            if (plakString.length() >= 6) {
                setPlakForEdit(plakString);
            }

            if (getIntent().getExtras().getString("type_fule").equals("1") || fuel_type_id == 1) {
                txt_benzini.setBackgroundResource(R.drawable.shap_select);
                txt_doghane.setBackgroundResource(R.drawable.shap_un_select);
                txt_dezeli.setBackgroundResource(R.drawable.shap_un_select);
                txt_benzini.setTextColor(getResources().getColor(R.color.text_light));
                txt_doghane.setTextColor(getResources().getColor(R.color.text_low_dark));
                txt_dezeli.setTextColor(getResources().getColor(R.color.text_low_dark));
                text_hibrid.setBackgroundResource(R.drawable.shap_un_select);
                text_hibrid.setTextColor(getResources().getColor(R.color.text_low_dark));
            } else if (getIntent().getExtras().getString("type_fule").equals("2") || fuel_type_id == 2) {
                txt_benzini.setBackgroundResource(R.drawable.shap_un_select);

                txt_doghane.setBackgroundResource(R.drawable.shap_un_select);
                txt_dezeli.setBackgroundResource(R.drawable.shap_select);
                text_hibrid.setBackgroundResource(R.drawable.shap_un_select);
                text_hibrid.setTextColor(getResources().getColor(R.color.text_low_dark));
                txt_benzini.setTextColor(getResources().getColor(R.color.text_low_dark));
                txt_doghane.setTextColor(getResources().getColor(R.color.text_low_dark));
                txt_dezeli.setTextColor(getResources().getColor(R.color.text_light));

            } else if (getIntent().getExtras().getString("type_fule").equals("3") || fuel_type_id == 3) {
                txt_benzini.setBackgroundResource(R.drawable.shap_un_select);
                txt_doghane.setBackgroundResource(R.drawable.shap_select);
                txt_dezeli.setBackgroundResource(R.drawable.shap_un_select);
                txt_benzini.setTextColor(getResources().getColor(R.color.text_low_dark));
                txt_doghane.setTextColor(getResources().getColor(R.color.text_light));
                txt_dezeli.setTextColor(getResources().getColor(R.color.text_low_dark));
                text_hibrid.setBackgroundResource(R.drawable.shap_un_select);
                text_hibrid.setTextColor(getResources().getColor(R.color.text_low_dark));
            } else if (getIntent().getExtras().getString("type_fule").equals("4") || fuel_type_id == 4) {
                txt_benzini.setBackgroundResource(R.drawable.shap_un_select);
                text_hibrid.setBackgroundResource(R.drawable.shap_select);
                txt_dezeli.setBackgroundResource(R.drawable.shap_un_select);
                txt_benzini.setTextColor(getResources().getColor(R.color.text_low_dark));
                text_hibrid.setTextColor(getResources().getColor(R.color.text_light));
                txt_dezeli.setTextColor(getResources().getColor(R.color.text_low_dark));
                txt_doghane.setBackgroundResource(R.drawable.shap_un_select);
                txt_doghane.setTextColor(getResources().getColor(R.color.text_low_dark));
            }
        }


        listgen.add("جنسیت");
        listgen.add("مرد");
        listgen.add("زن");

        SpinnerAdapter spinnerAdapter = new ArrayAdapter(AddCustomerActivity.this, R.layout.item_spiner, listgen);
        ((ArrayAdapter) spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_gender.setAdapter(spinnerAdapter);


        spin_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gender = listgen.get(i);
                if (tryfirst) {
//                    if(txt_date_customer.getText().toString().contains("تاریخ")) {
//                        txt_date_customer.performClick();
//
//                        G.toast("تاریخ تولد را وارد کنید");
//                    }
                } else {
                    tryfirst = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        list_type_car.add("سواری");
        list_type_car.add("باری");
        list_type_car.add("شاسی");
        list_type_car.add("ماشین سنگین");

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

                    closeKeyboard();
                    spin_gender.performClick();

                    return true;
                }
                return false;
            }
        });

        edt_phone_number.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {

                   edt_first_name.requestFocus();

                    return true;
                }
                return false;
            }
        });
        edt_phone_number.addTextChangedListener(new TextWatcher() {

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
                String phone = edt_phone_number.getText().toString();
                if (phone.length() == 11) {
                    checkExitUser(phone);
                }

            }
        });
    }

    public void closeKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void openKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            }
        }
    }

    public int car_name_id = 0, car_tip_id = 0, car_model_id = 0, fuel_type_id = 1,car_company_id=0;
    public String car_name_selected = "";
    public String car_company_selected = "";
    public String car_tip_selected = "";

    public void getcars_name(String car_company_id) {

//        G.loading(G.Activity);
        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.cars_name();
        if (car_company_id.length() > 0) {
            request = api.cars_name("car_company_id,eq," + car_company_id);
        }
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                assert response.body() != null;
                try {
                    String result = response.body().string();
                    JSONObject object = G.StringtoJSONObject(result);
                    JSONArray records = object.getJSONArray("records");
                    G.Log(records.toString());
                    if (records.length() > 0) {
                        List<SliderItem> sliderItemList = new ArrayList<>();
                        cars_name = new String[records.length()];
                        cars_name_id = new String[records.length()];
                        List<CarName> carNames = new ArrayList<>();
                        int positionSelected = 0;
                        for (int i = 0; i < records.length(); i++) {
                            JSONObject obj = records.getJSONObject(i);
                            SliderItem sliderItem = new SliderItem();
                            int id = obj.getInt("id");
                            String title = obj.getString("name");
//                            if (obj.has("car_company_id")) {
//                                JSONObject car_company = obj.getJSONObject("car_company_id");
//                                if (car_company.has("name")) {
//                                    title = car_company.getString("name") + " - " + title;
//                                }
//                            }
                            sliderItem.setDescription(title);
                            sliderItemList.add(sliderItem);
                            cars_name[i] = title;
                            cars_name_id[i] = id + "";
                            if (id == car_name_id) {
                                positionSelected = i;
                                car_name_selected = title;
                            }
                            carNames.add(new CarName(id, title));
                        }
//                        edt_name_car.setAdapter(liveSearch);
                        ObjectSpinner searchableSpinner = findViewById(R.id.car_names);
                        searchableSpinner.setItemList(carNames);
                        if (car_name_id > 0) {
                            searchableSpinner.setSelection(positionSelected);
                            getcars_tip(car_name_id + "");
                        }
                        searchableSpinner.setOnItemSelectedListener((view, position, item) -> {
                            car_name_id = carNames.get(position).getId();
                            car_name_selected = "";
                            for (int c = 0; c < carNames.size(); c++) {
                                if (carNames.get(c).id == car_name_id) {
                                    car_name_selected = carNames.get(c).name + "";
                                }
                            }
                            getcars_tip(car_name_id + "");
                        });
                        searchableSpinner.setOnShowListener((view, show) -> {
                            if (show) {
                                G.scrollToView(scrollView, view);
                            } else {
                                G.scrollToView(scrollView, edt_phone_number);
                            }
                        });
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
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }


    public void getcars_tip(String car_name_id) {

//        G.loading(G.Activity);
        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.cars_tip("car_name_id,eq," + car_name_id);
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                assert response.body() != null;
                try {
                    String result = response.body().string();
                    JSONObject object = G.StringtoJSONObject(result);
                    JSONArray records = object.getJSONArray("records");
                    List<CarTip> carTips = new ArrayList<>();
                    carTips.add(new CarTip(-1, "بدون تیپ"));
                    int positionSelected = 0;
                    if (records.length() > 0) {
                        List<SliderItem> sliderItemList = new ArrayList<>();
                        cars_name = new String[records.length()];
                        cars_name_id = new String[records.length()];


                        for (int i = 0; i < records.length(); i++) {
                            JSONObject obj = records.getJSONObject(i);
                            SliderItem sliderItem = new SliderItem();
                            int id = obj.getInt("id");
                            String title = obj.getString("tip");
                            sliderItem.setDescription(title);
                            sliderItemList.add(sliderItem);
                            cars_name[i] = title;
                            cars_name_id[i] = id + "";

                            if (id == car_tip_id) {
                                positionSelected = i+1;
                                car_tip_selected = title;

                            }
                            carTips.add(new CarTip(id, title));
                        }

//                        edt_name_car.setAdapter(liveSearch);


                        findViewById(R.id.spin_type_car).setVisibility(View.VISIBLE);

                    } else {
                        findViewById(R.id.spin_type_car).setVisibility(View.GONE);
                    }

                    spin_type_car.setItemList(carTips);
                    spin_type_car.setOnItemSelectedListener((view, position, item) -> {
                        car_tip_id = carTips.get(position).getId();
                        car_tip_selected = "";
                        for (int c = 0; c < carTips.size(); c++) {
                            if (carTips.get(c).id == car_tip_id) {
                                car_tip_selected = carTips.get(c).name + "";
                            }
                        }
                    });

                    if (car_tip_id > 0) {
                        spin_type_car.setSelection(positionSelected);
                    }
                    spin_type_car.setOnNothingSelectedListener(view -> {

                    });
                    spin_type_car.setOnShowListener((view, show) -> {
                        if (show) {
                            G.scrollToView(scrollView, view);
                        } else {

                            G.scrollToView(scrollView, edt_phone_number);
                        }
                    });
                } catch (JSONException | IOException e) {
                    G.toast("مشکل در تجزیه اطلاعات");
                    e.printStackTrace();
                }
                G.stop_loading();
//                getcars_models();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

    public void getcars_models() {

//        G.loading(G.Activity);
        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.cars_model();
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
                        cars_name = new String[records.length()];
                        cars_name_id = new String[records.length()];
                        List<CarModel> carModels = new ArrayList<>();
                        int positionSelected = 0;
                        for (int i = 0; i < records.length(); i++) {
                            JSONObject obj = records.getJSONObject(i);
                            SliderItem sliderItem = new SliderItem();
                            int id = obj.getInt("id");
                            String title = obj.getString("year");
                            sliderItem.setDescription(title);
                            sliderItemList.add(sliderItem);
                            cars_name[i] = title;
                            cars_name_id[i] = id + "";
                            if (id == car_model_id) {
                                positionSelected = i;

                            }
                            carModels.add(new CarModel(id, title));
                        }

//                        edt_name_car.setAdapter(liveSearch);


                        car_modelsx.setItemList(carModels);
                        car_modelsx.setOnItemSelectedListener((view, position, item) -> {
                            car_model_id = carModels.get(position).getId();
                        });
                        if (car_name_id > 0) {
                            car_modelsx.setSelection(positionSelected);
                        }
                        car_modelsx.setOnNothingSelectedListener(view -> {

                        });
                        car_modelsx.setOnShowListener((view, show) -> {
                            if (show) {
                                G.scrollToView(scrollView, view);
                            } else {

                                G.scrollToView(scrollView, edt_phone_number);
                            }
                        });

                    } else {
                        G.toast("مشکل در دریافت اطلاعات");
                    }
                } catch (JSONException | IOException e) {
                    G.toast("مشکل در تجزیه اطلاعات");
                    e.printStackTrace();
                }
                G.stop_loading();
//                getfuel_type();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

    public void getcars_company() {

//        G.loading(G.Activity);
        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.cars_company();
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
                        cars_company = new String[records.length()];
                        cars_company_id = new String[records.length()];
                        List<CarModel> carModels = new ArrayList<>();
                        int positionSelected = 0;
                        for (int i = 0; i < records.length(); i++) {
                            JSONObject obj = records.getJSONObject(i);
                            SliderItem sliderItem = new SliderItem();
                            int id = obj.getInt("id");
//                            String title = obj.getString("year");
                            String title = obj.getString("name");
                            sliderItem.setDescription(title);
                            sliderItemList.add(sliderItem);
                            cars_company[i] = title;
                            cars_company_id[i] = id + "";
                            if (id == car_company_id) {
                                positionSelected = i;
                                car_company_selected = title;
                            }
                            if (!title.contains("نامشخص")) {
                                carModels.add(new CarModel(id, title));
                            }
                        }

//                        edt_name_car.setAdapter(liveSearch);


                        car_modelsx.setItemList(carModels);
                        car_modelsx.setOnItemSelectedListener((view, position, item) -> {
                            car_model_id = carModels.get(position).getId();
                            car_company_selected = "";
                            for (int c = 0; c < carModels.size(); c++) {
                                if (carModels.get(c).id == car_model_id) {
                                    car_company_selected = carModels.get(c).name + "";
                                }
                            }
                            getcars_name(car_model_id + "");
                        });
                        if (car_company_id > 0) {
                            car_modelsx.setSelection(positionSelected);
                        }
                        car_modelsx.setOnNothingSelectedListener(view -> {

                        });
                        car_modelsx.setOnShowListener((view, show) -> {
                            if (show) {
                                G.scrollToView(scrollView, view);
                            } else {

                                G.scrollToView(scrollView, edt_phone_number);
                            }
                        });

                    } else {
                        G.toast("مشکل در دریافت اطلاعات");
                    }
                } catch (JSONException | IOException e) {
                    G.toast("مشکل در تجزیه اطلاعات");
                    e.printStackTrace();
                }
                G.stop_loading();
//                getfuel_type();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

    public void getfuel_type() {

//        G.loading(G.Activity);
        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.fuels_type();
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
                        cars_name = new String[records.length()];
                        cars_name_id = new String[records.length()];
                        List<FuelType> fuelTypes = new ArrayList<>();
                        int positionSelected = 0;
                        for (int i = 0; i < records.length(); i++) {
                            JSONObject obj = records.getJSONObject(i);
                            SliderItem sliderItem = new SliderItem();
                            int id = obj.getInt("id");
                            String title = obj.getString("type");
                            sliderItem.setDescription(title);
                            sliderItemList.add(sliderItem);
                            cars_name[i] = title;
                            cars_name_id[i] = id + "";
                            if (id == fuel_type_id) {
                                positionSelected = i;
                            }
                            fuelTypes.add(new FuelType(id, title));
                        }

//                        edt_name_car.setAdapter(liveSearch);


                        fuel_typesx.setItemList(fuelTypes);
                        fuel_typesx.setOnItemSelectedListener((view, position, item) -> {
                            fuel_type_id = fuelTypes.get(position).getId();
                        });
                        if (fuel_type_id > 0) {
                            fuel_typesx.setSelection(positionSelected);
                        }
                        fuel_typesx.setOnNothingSelectedListener(view -> {

                        });

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
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

    public void onclickAlamrs(View v) {
        startActivity(new Intent(AddCustomerActivity.this, AlarmsActivity.class));
    }
public String CarId = "";
    public void addCustomer(String updateCustomer, String car_id, String name, String lastName, String phone, String sex, String b_date, String car_tag, String car_name, String car_type, String fuel_type) {
G.Log(car_id+"Mashin");
        car_tag = G.CreateSyntaxPlak(car_tag);
        String d_id = PreferenceUtil.getD_id();
        G.loading(this);

        String created_at = G.converToEn(DateFormat.format("yyyy-MM-dd HH:mm:ss", new Date()).toString());

        if (sex.contains("مرد")) {
            sex = "M";
        } else {
            sex = "F";
        }
        JSONObject object = new JSONObject();
        try {
//            object.put("d_id", d_id);
            object.put("username", phone);
            object.put("f_name", name);
            object.put("l_name", lastName);
            object.put("mobile", phone);
            object.put("national_code", "");
            object.put("user_type", 4);
            object.put("role_id", 4);
            object.put("profile_photo", "");
            object.put("header_photo", "");
            object.put("password", "");
            object.put("sex", sex);
//            object.put("birthdate", b_date);
            object.put("province_id", G.preference.getString("province_id", ""));
            object.put("city_id", G.preference.getString("city_id", ""));
            if (!(updateCustomer != null && updateCustomer.length() > 0)) {
                object.put("created_at", created_at);
            }
            object.put("updated_at", created_at);
            object.put("deleted_at", JSONObject.NULL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        G.Log(object.toString());
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);

        Call<ResponseBody> request = api.newCustomer(G.returnBody(object.toString()));
        if (updateCustomer != null && updateCustomer.length() > 0) {
            request = api.editCustomer(updateCustomer, G.returnBody(object.toString()));
        }

        String finalCar_tag = car_tag;
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                String result = G.getResult(response);
                G.Log(result);

                if (result.length() > 0 && result.length() < 30) {

                    JSONObject car = new JSONObject();
                    String user_id;
                    if (updateCustomer != null && updateCustomer.length() > 0) {
                        user_id = updateCustomer;
                    } else {
                        user_id = result.replace(" ", "");
                    }
                    try {

                        car.put("service_center_id", d_id);
                        car.put("user_id", user_id);
                        car.put("car_name_id", car_name_id);
                        if (car_tip_id > 0) {
                            car.put("car_tip_id", car_tip_id);
                        } else {
                            car.put("car_tip_id", null);
                        }
                        car.put("car_model_id", 54);
                        car.put("fuel_type_id", fuel_type_id);
                        car.put("car_plate", finalCar_tag);
                        car.put(Constants.CAR_PLATE_TYPE, plak_type.id);
                        car.put("chassis_num", "");
                        car.put("created_at", created_at);
                        car.put("updated_at", created_at);
                        car.put("deleted_at", JSONObject.NULL);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

//                    if (updateCustomer != null && updateCustomer.length() > 0) {
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                finish();
//                            }
//                        }, 1000);
//                    } else {
                    G.Log(car.toString());
                    requestCar(user_id, car_id, b_date, car, updateCustomer);
//                    }

                } else {
                    G.toast("مشکل در ثبت اطلاعات!");
                    G.stop_loading();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط با سرور");
            }
        });

    }

    public void requestCar(String user_id, String car_id, String b_date, JSONObject car, String updateCustomer) {
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), car.toString());
        Call<ResponseBody> request = api.newCar(body);
        boolean update = false;
        if (updateCustomer != null && updateCustomer.length() > 0) {
            if (car_id.length() > 0) {
                update = true;
                G.Log("update");
                request = api.editCar(car_id, body);
            }
        }

        boolean finalUpdate = update;
        Log.d("RequestCar", "requestCar:request: " + request.request().toString());
        Log.d("RequestCar", "requestCar:bode: " + body.toString());
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                G.Log(call.request().toString());
                if (response.body() != null) {
                    String result = "";
                    try {
                        result = G.getResult(response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.d("RequestCar", "requestCar:result: " + result);
                    G.Log(result);
                    if (result.length() > 0 && result.length() < 15) {
                        G.toast("اطلاعات با موفقیت ثبت شد");
                        if (!isEditCustomer) G.sendSMSProv(true,edt_phone_number.getText().toString(), G.PROV_WELLCOME, "0", 0);
                        G.stop_loading();
                        String car_id = result.replace(" ", "");
                        Log.d("RequestCar", "requestCar:result: " + result);

                        if (!finalUpdate) {
                            showBottomSheetDialog(b_date, user_id, car_id);
                        } else {
                            finish();
                        }
                    }
                } else {
                    G.toast("مشکل در ثبت خودرو");
                    G.stop_loading();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.d("RequestCar", "requestCar:e: " + t.getMessage().toString());

                G.Log(t.getMessage());
                G.Log(t.getLocalizedMessage());
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط با سرور");
            }
        });
    }

    public void showBottomSheetDialog(String dateBirthday, String user_id, String car_id) {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(AddCustomerActivity.this, R.style.BottomSheetDialogTheme);
        View bottomSheetView = LayoutInflater.from(AddCustomerActivity.this)
                .inflate(R.layout.layout_button_sheet_main, (LinearLayout) findViewById(R.id.ly_bottom_sheet_lang));
        bottomSheetDialog.setCancelable(false);

        bottomSheetDialog.setContentView(bottomSheetView);
        Button btn_cancle, btn_save_service;
        TextView txt_name_family, txt_car, txt_phone;
        TextView txt_plak_customer1, txt_plak_customer2, txt_plak_customer3, txt_plak_customer4;
        ViewGroup plaks;
        btn_cancle = bottomSheetDialog.findViewById(R.id.btn_cancle);
        btn_save_service = bottomSheetDialog.findViewById(R.id.btn_save_service);
        txt_name_family = bottomSheetDialog.findViewById(R.id.txt_name_family);

        txt_car = bottomSheetDialog.findViewById(R.id.txt_car);

        txt_phone = bottomSheetDialog.findViewById(R.id.txt_phone);


        plaks = bottomSheetDialog.findViewById(R.id.plaks);
        ly_plk_general = bottomSheetDialog.findViewById(R.id.ly_plk_general);
        ly_plk_taxi = bottomSheetDialog.findViewById(R.id.ly_plk_taxi);
        ly_plk_edari = bottomSheetDialog.findViewById(R.id.ly_plk_edari);
        ly_plk_entezami = bottomSheetDialog.findViewById(R.id.ly_plk_entezami);
        ly_plk_malolin = bottomSheetDialog.findViewById(R.id.ly_plk_malolin);
        ly_plk_azad_new = bottomSheetDialog.findViewById(R.id.ly_plk_azad_new);
        ly_plk_azad_old = bottomSheetDialog.findViewById(R.id.ly_plk_azad_old);
        setPlakLayout();
        setPlakBasedViewType(plakString, plak_type);
        txt_name_family.setText(edt_first_name.getText().toString() + " " + edt_last_name.getText().toString());
        txt_car.setText(car_company_selected+" - "+car_name_selected);
        txt_phone.setText(edt_phone_number.getText().toString());
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
                finish();
            }
        });

        btn_save_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = edt_first_name.getText().toString();
                String lastName = edt_last_name.getText().toString();
                String phone = edt_phone_number.getText().toString();
//                String nameCar = edt_name_car.getText().toString();
                String plak = getPlakValue();
                Log.d("PLAK", "onResponse:AddCustomer:showBottomDialog: " + plak);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mDBHelper != null) {
                            mDBHelper.deleteHistoryKhadamt(G.preference.getInt("idService", 0) + "");
                        }
                    }
                }, 0);
                Intent intent = new Intent(AddCustomerActivity.this, AddServicesActivity.class);
                intent.putExtra("idCustomer", user_id + "");
                intent.putExtra(Constants.CAR_ID, car_id );
                Log.d("CARRRR", "onResponse:AddCustomer:showBottomDialog " + car_id);

                intent.putExtra("firstName", firstName);
                intent.putExtra("lastName", lastName);
                intent.putExtra("phone", phone);
                intent.putExtra("nameCar", car_company_selected+" - "+car_name_selected+" - "+car_tip_selected);
                intent.putExtra("id_customer", user_id + "");
                intent.putExtra("plak", plakString);
                intent.putExtra(Constants.CAR_PLATE_TYPE, plak_type);
                intent.putExtra("description", "");
                intent.putExtra("type_fule", typeFuel);
                intent.putExtra("gender", gender);
                intent.putExtra("type_car", type_car);
//                intent.putExtra("date_birthday", txt_date_customer.getText().toString());
                intent.putExtra("finish", "");


                intent.putExtra("date_save", "");


                intent.putExtra("car_name_id", car_name_id);
                intent.putExtra("car_tip_id", car_tip_id);
                intent.putExtra("car_model_id", car_model_id);
                intent.putExtra("car_company_id", car_company_id);
                intent.putExtra("fuel_type_id", fuel_type_id);

                intent.putExtra("fromMain", false);
                bottomSheetDialog.dismiss();
                startActivity(intent);
                finish();
            }
        });

        txt_name_family.setTypeface(G.Bold);
        txt_car.setTypeface(G.Bold);
        txt_phone.setTypeface(G.Bold);


        bottomSheetDialog.show();
    }

    private void onClick() {
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = edt_first_name.getText().toString();
                String lastName = edt_last_name.getText().toString();
                String dateBirthday = "";
//                String dateBirthday = txt_date_customer.getText().toString();
//                dateBirthday = dateBirthday.replace("/", "-");
                String phone = edt_phone_number.getText().toString();
//                String nameCar = edt_name_car.getText().toString();
              //  String plak = edt1.getText().toString() + edt2.getText().toString() + edt3.getText().toString() + edt4.getText().toString() + edt5.getText().toString() + edt6.getText().toString() + edt7.getText().toString() + edt8.getText().toString();
                 plakString = getPlakValue();

                typeFuel = "1";
                switch (type) {
                    case "1":
                        typeFuel = "1";
                        break;
                    case "2":
                        typeFuel = "2";

                        break;
                    case "3":
                        typeFuel = "3";
                        break;
                    case "4":
                        typeFuel = "4";
                        break;
                }
                fuel_type_id = Integer.parseInt(typeFuel);

                if (car_name_id > 0 && car_model_id > 0 && fuel_type_id > 0 && !TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName)  && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty("nameCar") && !TextUtils.isEmpty("type_car") && !TextUtils.isEmpty(plakString)) {
                    persianCalendar = new PersianCalendar();
                    String date_save_customer = persianCalendar.getPersianYear() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay();
                    if (dateBirthday.contains("-") && dateBirthday.length() >= 7) {
                        String[] dates = dateBirthday.split("-");
                        CalendarTool calendarTool = new CalendarTool();
                        calendarTool.setIranianDate(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
                        dateBirthday = calendarTool.getGregorianDate();
                    }
                    if (!getIntent().getExtras().getString("firstName").equals("null") || find_user > 0) {
                        String cust_id = (getIntent().getExtras().getString("idCustomer") + "").replace("null", "");

                        String car_id = (getIntent().getExtras().getString("id_car") + "").replace("null", "");
                        if (!(cust_id != null && cust_id.length() > 0)) {
                            if (find_user > 0) {
                                cust_id = find_user + "";
                            }
                        }
                        if(car_id.length()<=0 && CarId.length()>0){
                            car_id=CarId;
                        }
                        addCustomer(cust_id + "", car_id + "", firstName, lastName, phone, gender, dateBirthday, plakString, "nameCar", type_car, typeFuel);

//                        mDBHelper.updateCustomers(getIntent().getExtras().getInt("idCustomer"), firstName, lastName, gender, dateBirthday, phone, plak, nameCar, type_car, typeFuel, date_save_customer);
//                        finish();
                    } else {
//                        id = mDBHelper.insertCustomers(firstName, lastName, gender, dateBirthday, phone, plak, nameCar, type_car, typeFuel, date_save_customer);

                        addCustomer("", CarId, firstName, lastName, phone, gender, dateBirthday, plakString, "nameCar", type_car, typeFuel);

                    }
                } else if (firstName.equals("")) {
                    edt_first_name.setError("نام را به درستی وارد کنید");

                } else if (lastName.equals("")) {
                    edt_last_name.setError("نام خانوادگی را به درستی وارد کنید");

                }
//                else if (dateBirthday.equals("")) {
//                    txt_date_customer.setError("تاریخ تولد را به درستی وارد کنید");
//
//                }
                else if (phone.equals("")) {
                    edt_phone_number.setError("شماره موبایل را به درستی وارد کنید");

                } else if (car_name_id <= 0) {
                    G.toast("نام خودرو را وارد نکرده اید!");

                } else if (car_model_id <= 0) {
                    G.toast("مدل خودرو را وارد نکرده اید!");

                } else if (fuel_type_id <= 0) {
                    G.toast("نوع سوخت را وارد نکرده اید!");

                }
//                else if (nameCar.equals("")) {
//                    edt_name_car.setError("نام خودرو را به درستی وارد کنید");
//
//                }
                else if (plakString.equals("")) {
                    edt1.setError("پلاک را به درستی وارد کنید");

                }
            }
        });

        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        txt_benzini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "1";

                txt_benzini.setBackgroundResource(R.drawable.shap_select);
                txt_dezeli.setBackgroundResource(R.drawable.shap_un_select);
                txt_doghane.setBackgroundResource(R.drawable.shap_un_select);
                text_hibrid.setBackgroundResource(R.drawable.shap_un_select);
                text_hibrid.setTextColor(getResources().getColor(R.color.text_low_dark));
                txt_benzini.setTextColor(getResources().getColor(R.color.text_light));
                txt_dezeli.setTextColor(getResources().getColor(R.color.text_low_dark));
                txt_doghane.setTextColor(getResources().getColor(R.color.text_low_dark));
            }
        });

        txt_dezeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "2";
                txt_benzini.setBackgroundResource(R.drawable.shap_un_select);
                txt_dezeli.setBackgroundResource(R.drawable.shap_select);
                txt_doghane.setBackgroundResource(R.drawable.shap_un_select);
                text_hibrid.setBackgroundResource(R.drawable.shap_un_select);
                text_hibrid.setTextColor(getResources().getColor(R.color.text_low_dark));
                txt_dezeli.setTextColor(getResources().getColor(R.color.text_light));
                txt_benzini.setTextColor(getResources().getColor(R.color.text_low_dark));
                txt_doghane.setTextColor(getResources().getColor(R.color.text_low_dark));
            }
        });

        txt_doghane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "3";
                txt_benzini.setBackgroundResource(R.drawable.shap_un_select);
                txt_dezeli.setBackgroundResource(R.drawable.shap_un_select);
                txt_doghane.setBackgroundResource(R.drawable.shap_select);
                text_hibrid.setBackgroundResource(R.drawable.shap_un_select);
                text_hibrid.setTextColor(getResources().getColor(R.color.text_low_dark));
                txt_doghane.setTextColor(getResources().getColor(R.color.text_light));
                txt_dezeli.setTextColor(getResources().getColor(R.color.text_low_dark));
                txt_benzini.setTextColor(getResources().getColor(R.color.text_low_dark));
            }
        });
        text_hibrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "4";
                txt_benzini.setBackgroundResource(R.drawable.shap_un_select);
                txt_dezeli.setBackgroundResource(R.drawable.shap_un_select);
                text_hibrid.setBackgroundResource(R.drawable.shap_select);
                txt_doghane.setBackgroundResource(R.drawable.shap_un_select);
                txt_doghane.setTextColor(getResources().getColor(R.color.text_low_dark));
                text_hibrid.setTextColor(getResources().getColor(R.color.text_light));
                txt_dezeli.setTextColor(getResources().getColor(R.color.text_low_dark));
                txt_benzini.setTextColor(getResources().getColor(R.color.text_low_dark));
            }
        });

        txt_date_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initializeDatePicker();
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        iv_plk_type_menu.setOnClickListener(v->{
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

    private void onClickPlak() {
        List<EditText> editTextsInPlakLayout = findEditTextsInLayout(plak_layout);
        Log.d("Maaain", "PlakListener: " + editTextsInPlakLayout.toString());
        Log.d("Maaain", "PlakListener: " + plak_layout.toString());
        for (int i = 0; i < editTextsInPlakLayout.size() - 1; i++) {
            EditText currentEditText = editTextsInPlakLayout.get(i);
            EditText nextEditText = editTextsInPlakLayout.get(i + 1);
            currentEditText.addTextChangedListener(new PlakTextWatcher(currentEditText, nextEditText));
        }
    }

    private void FindView() {
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        edt3 = findViewById(R.id.edt3);
        edt4 = findViewById(R.id.edt4);
        edt5 = findViewById(R.id.edt5);
        edt6 = findViewById(R.id.edt6);
        edt7 = findViewById(R.id.edt7);
        edt8 = findViewById(R.id.edt8);
        btn_cancle = findViewById(R.id.btn_cancle);
        btn_save = findViewById(R.id.btn_save);
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        img_back = findViewById(R.id.img_back);
        txt_benzini = findViewById(R.id.txt_benzini);
        txt_dezeli = findViewById(R.id.txt_dezeli);
        text_hibrid = findViewById(R.id.txt_hibrid);
        txt_doghane = findViewById(R.id.txt_doghane);
        txt_date_customer = findViewById(R.id.txt_date_customer);
        spin_gender = findViewById(R.id.spin_gender);
        spin_type_car = findViewById(R.id.spin_type_car);
        car_modelsx = findViewById(R.id.car_modelsx);
        fuel_typesx = findViewById(R.id.fuel_typesx);

        edt_first_name = findViewById(R.id.edt_first_name);
        edt_last_name = findViewById(R.id.edt_last_name);
//        edt_name_car = findViewById(R.id.edt_name_car);
        edt_phone_number = findViewById(R.id.edt_phone_number);
        iv_plk_type_menu = findViewById(R.id.tv_plk_type_menu);

        ly_plk_general = findViewById(R.id.ly_plk_general);
        ly_plk_malolin = findViewById(R.id.ly_plk_malolin);
        ly_plk_azad_new = findViewById(R.id.ly_plk_azad_new);
        ly_plk_azad_old = findViewById(R.id.ly_plk_azad_old);
        plak_layout = ly_plk_general;
        ly_pelak_type = findViewById(R.id.ly_pelak_type);
        tv_plk_type_general = findViewById(R.id.tv_plk_type_general);
        tv_plk_type_azad_new = findViewById(R.id.tv_plk_type_azad_new);
        tv_plk_type_azad_old = findViewById(R.id.tv_plk_type_azad_old);
        spinner_plk_azad_old = findViewById(R.id.spinner_plk_azad_old);

    }

    private void initializeDatePicker() {
        PersianCalendar initDate = new PersianCalendar();
        initDate.setPersianDate(1370, 1, 1);

        mDatePicker = new PersianDatePickerDialog(AddCustomerActivity.this)
                .setPositiveButtonString("تأیید")
                .setNegativeButton("بستن")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setMinYear(1340)
                .setMaxYear(1410)
                .setInitDate(initDate)
                .setTitleColor(getResources().getColor(R.color.text_dark))
                .setActionTextColor(Color.GRAY)
                //  .setPickerBackgroundColor(Color.DKGRAY)
                //.setBackgroundColor(Color.DKGRAY)
                .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
                .setShowInBottomSheet(true)

                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(PersianCalendar persianCalendar) {

                        txt_date_customer.setText(persianCalendar.getPersianYear() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay());
                        txt_date_customer.setTextColor(getResources().getColor(R.color.text_dark));
                        car_modelsx.performClick();


                    }

                    @Override
                    public void onDismissed() {
                    }
                });
        mDatePicker.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        preferenceUtil.deleteCashPhone();
        preferenceUtil.deleteCashPlak();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        preferenceUtil.deleteCashPhone();
        preferenceUtil.deleteCashPlak();
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }

    public int find_user = 0;
    public void checkExitCar(String tag) {
        tag = G.CreateSyntaxPlak(tag);
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        api.checkExitCar("car_plate,cs," + tag).enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                String result = G.getResult(response);
                JSONObject object = G.StringtoJSONObject(result);
                JSONArray array = G.ObjecttoArray(object, "records");
                G.Log(result);
                if (array.length() > 0) {
                    JSONObject car = null;
                    try {
                        car = array.getJSONObject(0);
                        CarId = car.getString("id");
                        if(CarId.length()>0){
                            G.Log("یافت شد");
                            G.Log(CarId);
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }
                G.stop_loading();


            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
            }
        });
    }
    public void checkExitUser(String phone) {//09398232464    49t578  41

        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.checkExitUser("mobile,eq," + phone);
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                find_user = 0;
                edt_first_name.setText("");
                edt_last_name.setText("");
                edt_last_name.setText("");
                txt_date_customer.setText("تاریخ تولد");
                tryfirst = false;
                spin_gender.setSelection(0);

                try {
                    String result = G.getResult(response);
                    JSONObject obj = G.StringtoJSONObject(result);
                    G.Log(result);
                    if (obj.has("records")) {
                        JSONArray records = obj.getJSONArray("records");
                        if (records.length() > 0) {
                            JSONObject record = records.getJSONObject(0);

                            find_user = record.getInt("id");
                            edt_first_name.setText(record.getString("f_name") + "");
                            edt_last_name.setText(record.getString("l_name") + "");
                            edt_last_name.setText(record.getString("l_name") + "");
//                            txt_date_customer.setText(G.toShamsi(record.getString("birthdate")) + "");
                            String sex = record.getString("sex") + "";
                            tryfirst = false;
                            G.Log("Gender "+sex);
                            if (sex.contains("آقا")||sex.contains("مرد")||sex.contains("M") || sex.contains("m")) {
                                spin_gender.post(new Runnable() {
                                    public void run() {
                                        spin_gender.setSelection(1);
                                    }
                                });
                            } else {
                                spin_gender.post(new Runnable() {
                                    public void run() {
                                        spin_gender.setSelection(2);
                                    }
                                });
                            }
                            G.toast("کاربر یافت شد!");
                        }
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

    private void showPlakTypeMenu(Boolean isVisible) {
        ly_pelak_type.setVisibility((isVisible ? View.VISIBLE : View.GONE));
    }

    private void onClickPlakType(int id){
        switch (id){
            case R.id.tv_plk_type_general: {
                plak_type = PLAK_TYPE.PLAK_GENERAL;
                plak_layout = ly_plk_general;
                break;
            }
            case R.id.tv_plk_type_azad_old:{
                plak_type = PLAK_TYPE.PLAK_AZAD_OLD;
                plak_layout = ly_plk_azad_old;
                break;
            }
            case R.id.tv_plk_type_azad_new:{
                plak_type = PLAK_TYPE.PLAK_AZAD_NEW;
                plak_layout = ly_plk_azad_new;
                break;
            }
        }
        showPlakTypeMenu(false);
        changePlakLayout();
        onClickPlak();
    }

    private void changePlakLayout() {
        switch (plak_type){
            case PLAK_GENERAL:
            case PLAK_TAXI:
            case PLAK_EDARI:
            case PLAK_ENTEZAMI: {
                plak_layout = ly_plk_general;
                ly_plk_general.setVisibility(View.VISIBLE);
                ly_plk_malolin.setVisibility(View.GONE);
                ly_plk_azad_new.setVisibility(View.GONE);
                ly_plk_azad_old.setVisibility(View.GONE);
                break;
            }

            case PLAK_MAOLOIN: {
                plak_layout = ly_plk_malolin;
                ly_plk_general.setVisibility(View.GONE);
                ly_plk_malolin.setVisibility(View.VISIBLE);
                ly_plk_azad_new.setVisibility(View.GONE);
                ly_plk_azad_old.setVisibility(View.GONE);
                break;
            }
            case PLAK_AZAD_NEW: {
                plak_layout = ly_plk_azad_new;
                ly_plk_general.setVisibility(View.GONE);
                ly_plk_malolin.setVisibility(View.GONE);
                ly_plk_azad_new.setVisibility(View.VISIBLE);
                ly_plk_azad_old.setVisibility(View.GONE);
                break;
            }
            case PLAK_AZAD_OLD: {
                plak_layout = ly_plk_azad_old;
                ly_plk_general.setVisibility(View.GONE);
                ly_plk_malolin.setVisibility(View.GONE);
                ly_plk_azad_new.setVisibility(View.GONE);
                ly_plk_azad_old.setVisibility(View.VISIBLE);
                break;
            }
        }
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

    private String getPlakValue() {
        List<EditText> editTextsInPlakLayout = findEditTextsInLayout(plak_layout);
        StringBuilder plak = new StringBuilder("");
        String endPlak = "";
        for (int i = 0; i < editTextsInPlakLayout.size() ; i++) {
            EditText currentEditText = editTextsInPlakLayout.get(i);
            if(i == 2 && (plak_type!= PLAK_TYPE.PLAK_AZAD_NEW && plak_type!= PLAK_TYPE.PLAK_AZAD_OLD)){
                endPlak = currentEditText.getText().toString();
            }else if ((plak_type == PLAK_TYPE.PLAK_AZAD_NEW || plak_type == PLAK_TYPE.PLAK_AZAD_OLD) && i == 2) {
                plak.append(currentEditText.getText().toString());
            } else if ((plak_type == PLAK_TYPE.PLAK_AZAD_NEW || plak_type == PLAK_TYPE.PLAK_AZAD_OLD) && i == editTextsInPlakLayout.size()) {
                 break;
            } else{
                plak.append(currentEditText.getText().toString());
            }
        }
        if (plak_type != PLAK_TYPE.PLAK_AZAD_NEW && plak_type != PLAK_TYPE.PLAK_AZAD_OLD) {
            plak.append(endPlak);
            setPlakTypeBasedEndPlak(endPlak);
        }
        if(plak_type == PLAK_TYPE.PLAK_AZAD_OLD ){
            plak.append(selectedCityForPlk);
        }
        //String plak = edt1.getText().toString() + edt2.getText().toString() + edt4.getText().toString() + edt5.getText().toString() + edt6.getText().toString() + edt7.getText().toString() + edt8.getText().toString() + edt3.getText().toString();

        return plak.toString();
    }

    private String getPlakValueForSearch() {
        List<EditText> editTextsInPlakLayout = findEditTextsInLayout(plak_layout);
        StringBuilder plak = new StringBuilder("");
        String endPlak = "";
        for (int i = 0; i < editTextsInPlakLayout.size() ; i++) {
            EditText currentEditText = editTextsInPlakLayout.get(i);
             if ((plak_type == PLAK_TYPE.PLAK_AZAD_NEW || plak_type == PLAK_TYPE.PLAK_AZAD_OLD)) {
                plak.append(currentEditText.getText().toString());
            } else if ((plak_type == PLAK_TYPE.PLAK_AZAD_NEW || plak_type == PLAK_TYPE.PLAK_AZAD_OLD) && i == editTextsInPlakLayout.size()) {
                break;
            } else{
                plak.append(currentEditText.getText().toString());
            }
        }
        if(plak_type == PLAK_TYPE.PLAK_AZAD_OLD ){
            plak.append(selectedCityForPlk);
        }
        //String plak = edt1.getText().toString() + edt2.getText().toString() + edt4.getText().toString() + edt5.getText().toString() + edt6.getText().toString() + edt7.getText().toString() + edt8.getText().toString() + edt3.getText().toString();
        return plak.toString();
    }
    private void setPlakTypeBasedEndPlak(String endPlak) {
        if(endPlak.equals("ع") || endPlak.equals("ت")) plak_type = PLAK_TYPE.PLAK_TAXI;
        else if (endPlak.equals("ا"))  plak_type = PLAK_TYPE.PLAK_EDARI;
        else if (endPlak.equals("پ") || endPlak.equals("ث"))  plak_type = PLAK_TYPE.PLAK_ENTEZAMI;
        else if (endPlak.equals("#"))  plak_type = PLAK_TYPE.PLAK_MAOLOIN;
        else plak_type = PLAK_TYPE.PLAK_GENERAL;

    }

    private void setPlakForEdit(String plak) {
        List<EditText> editTextsInPlakLayout = findEditTextsInLayout(plak_layout);
        String endPlak = "";
        int endPlakIndex = 0;
        switch (plak_type){
            case PLAK_GENERAL:
            case PLAK_TAXI:
            case PLAK_EDARI:
            case PLAK_ENTEZAMI:
            case PLAK_MAOLOIN: {
                endPlakIndex = 7;
                break;
            }
            case PLAK_AZAD_NEW: {
                endPlakIndex = 6;
                break;
            }
            case PLAK_AZAD_OLD: {
                endPlakIndex = 6;
                break;
            }
        }
        for (int i = 0; i <= editTextsInPlakLayout.size() - 1; i++) {
            EditText currentEditText = editTextsInPlakLayout.get(i);
            if(i < endPlakIndex){
                if((plak_type!= PLAK_TYPE.PLAK_AZAD_NEW && plak_type!= PLAK_TYPE.PLAK_AZAD_OLD)){
                    if (i == 2) {
                        currentEditText.setText(plak.substring(plak.length()-1));
                    }
                    else if (i >= 3) {
                        currentEditText.setText(plak.substring(i-1));
                    }else{
                        currentEditText.setText(plak.substring(i));
                    }
                }else{
                    if(i != plak.length()-1) {
                        currentEditText.setText(plak.substring(i, i+1));
                    }
                    else{
                        currentEditText.setText(plak.substring(i));
                    }
                }
            }else{
                if(plak_type == PLAK_TYPE.PLAK_AZAD_NEW){
                    currentEditText.setText(plak.substring(i, plak.length()));
                } else currentEditText.setText(plak.substring(i-1));
            }
        }
        if(plak_type == PLAK_TYPE.PLAK_AZAD_OLD ){
            Log.d("PLAK", "setPlakForEdit: selectedCityForPlkIndex:" + selectedCityForPlkIndex );
            spinner_plk_azad_old.setSelection(selectedCityForPlkIndex);
        }
    }

    private void setPlakLayout() {
        switch (plak_type){
            case PLAK_GENERAL: {
                plak_layout = ly_plk_general;
                ly_plk_general.setVisibility(View.VISIBLE);
                ly_plk_taxi.setVisibility(View.GONE);
                ly_plk_edari.setVisibility(View.GONE);
                ly_plk_entezami.setVisibility(View.GONE);
                ly_plk_malolin.setVisibility(View.GONE);
                ly_plk_azad_new.setVisibility(View.GONE);
                ly_plk_azad_old.setVisibility(View.GONE);
                break;
            }
            case PLAK_TAXI: {
                plak_layout = ly_plk_taxi;
                ly_plk_general.setVisibility(View.GONE);
                ly_plk_taxi.setVisibility(View.VISIBLE);
                ly_plk_edari.setVisibility(View.GONE);
                ly_plk_entezami.setVisibility(View.GONE);
                ly_plk_malolin.setVisibility(View.GONE);
                ly_plk_azad_new.setVisibility(View.GONE);
                ly_plk_azad_old.setVisibility(View.GONE);
                break;
            }
            case PLAK_EDARI: {
                plak_layout = ly_plk_edari;
                ly_plk_general.setVisibility(View.GONE);
                ly_plk_taxi.setVisibility(View.GONE);
                ly_plk_edari.setVisibility(View.VISIBLE);
                ly_plk_entezami.setVisibility(View.GONE);
                ly_plk_malolin.setVisibility(View.GONE);
                ly_plk_azad_new.setVisibility(View.GONE);
                ly_plk_azad_old.setVisibility(View.GONE);
                break;
            }
            case PLAK_ENTEZAMI: {
                plak_layout = ly_plk_entezami;
                ly_plk_general.setVisibility(View.GONE);
                ly_plk_taxi.setVisibility(View.GONE);
                ly_plk_edari.setVisibility(View.GONE);
                ly_plk_entezami.setVisibility(View.VISIBLE);
                ly_plk_malolin.setVisibility(View.GONE);
                ly_plk_azad_new.setVisibility(View.GONE);
                ly_plk_azad_old.setVisibility(View.GONE);
                break;
            }
            case PLAK_MAOLOIN: {
                plak_layout = ly_plk_malolin;
                ly_plk_general.setVisibility(View.GONE);
                ly_plk_taxi.setVisibility(View.GONE);
                ly_plk_edari.setVisibility(View.GONE);
                ly_plk_entezami.setVisibility(View.GONE);
                ly_plk_malolin.setVisibility(View.VISIBLE);
                ly_plk_azad_new.setVisibility(View.GONE);
                ly_plk_azad_old.setVisibility(View.GONE);
                break;
            }
            case PLAK_AZAD_NEW: {
                plak_layout = ly_plk_azad_new;
                ly_plk_general.setVisibility(View.GONE);
                ly_plk_taxi.setVisibility(View.GONE);
                ly_plk_edari.setVisibility(View.GONE);
                ly_plk_entezami.setVisibility(View.GONE);
                ly_plk_malolin.setVisibility(View.GONE);
                ly_plk_azad_new.setVisibility(View.VISIBLE);
                ly_plk_azad_old.setVisibility(View.GONE);
                break;
            }
            case PLAK_AZAD_OLD: {
                plak_layout = ly_plk_azad_old;
                ly_plk_general.setVisibility(View.GONE);
                ly_plk_taxi.setVisibility(View.GONE);
                ly_plk_edari.setVisibility(View.GONE);
                ly_plk_entezami.setVisibility(View.GONE);
                ly_plk_malolin.setVisibility(View.GONE);
                ly_plk_azad_new.setVisibility(View.GONE);
                ly_plk_azad_old.setVisibility(View.VISIBLE);
                break;
            }
        }

    }

    private void setPlakAzadOldSpinner(){

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(AddCustomerActivity.this,
                R.array.plk_azad_old_cities, R.layout.plak_spinner_item);

        adapter.setDropDownViewResource(R.layout.plak_spinner_item);

        spinner_plk_azad_old.setAdapter(adapter);

        spinner_plk_azad_old.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedCityForPlk = parentView.getItemAtPosition(position).toString();
                selectedCityForPlkIndex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }


    private void setPlakBasedViewType(String plak, Constants.PLAK_TYPE plakType) {
        // plaks.setVisibility(View.VISIBLE);

        List<TextView> textViewsInPlakLayout = findTextsInLayout(plak_layout);
        Log.d("PLAK", "setPlakBasedViewType:addService: " + plak_layout );

        switch (plakType) {
            case PLAK_GENERAL:
            case PLAK_TAXI:
            case PLAK_EDARI:
            case PLAK_ENTEZAMI: {
                String c1 = plak.substring(0, 2);
                String c2 = plak.substring(2, plak.length() - 3);
                String c3 = plak.substring(plak.length() - 3, plak.length() - 1);
                String c4 = plak.substring(plak.length() - 1);
                textViewsInPlakLayout.get(0).setText(c1);
                textViewsInPlakLayout.get(1).setText(c4);
                textViewsInPlakLayout.get(2).setText(c2);
                textViewsInPlakLayout.get(3).setText(c3);
                Log.d("PLAK", "setPlakBasedViewType:addService: " + plak_layout );

                break;
            }
            case PLAK_MAOLOIN:
            {
                String c1 = plak.substring(0, 2);
                String c2 = plak.substring(2, plak.length() - 3);
                String c3 = plak.substring(plak.length() - 3, plak.length() - 1);
                textViewsInPlakLayout.get(0).setText(c1);
                textViewsInPlakLayout.get(1).setVisibility(View.GONE);
                textViewsInPlakLayout.get(2).setText(c2);
                textViewsInPlakLayout.get(3).setText(c3);
                break;
            }
            case PLAK_AZAD_NEW: {
                String c1 = plak.substring(0, 6);
                String c4 = plak.substring(6, plak.length());
                textViewsInPlakLayout.get(0).setText(c1);
                textViewsInPlakLayout.get(1).setText(c4);
                textViewsInPlakLayout.get(2).setText(PLakUtils.convertPersianToEnglish(c1));
                textViewsInPlakLayout.get(3).setText(c4);
                break;
            }
            case PLAK_AZAD_OLD: {
                String c1 = plak.substring(0, 6);
                String c4 = plak.substring(6, plak.length());
                textViewsInPlakLayout.get(0).setText(c4);
                textViewsInPlakLayout.get(1).setText(c1);
                textViewsInPlakLayout.get(2).setText(PLakUtils.convertPersianToEnglish(c1));
                textViewsInPlakLayout.get(3).setVisibility(View.GONE);
                break;
            }
        }
      }

    private List<TextView> findTextsInLayout(ViewGroup layout) {
        List<TextView> textViews = new ArrayList<>();
        for (int i = 0; i < layout.getChildCount(); i++) {
            View view = layout.getChildAt(i);
            if (view instanceof TextView) {
                textViews.add((TextView) view);
            } else if (view instanceof ViewGroup) {
                textViews.addAll(findTextsInLayout((ViewGroup) view));
            }
        }
        return textViews;
    }

}