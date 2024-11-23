package ir.servicea;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import ir.servicea.activity.AddCustomerActivity;
import ir.servicea.activity.AddServicesActivity;
import ir.servicea.activity.AlarmsActivity;
import ir.servicea.activity.ListReserveActivity;
import ir.servicea.activity.SimpleScannerActivity;
import ir.servicea.activity.WebViewActivity;
import ir.servicea.activity.buyCreditActivity;
import ir.servicea.adapter.AdapterListAdvertise;
import ir.servicea.adapter.AdapterListAdvertiseTwo;
import ir.servicea.adapter.AdapterListGridMain;
import ir.servicea.adapter.SliderAdapterExample;
import ir.servicea.app.Constants;
import ir.servicea.app.DataBaseHelper;
import ir.servicea.app.G;
import ir.servicea.app.PlakTextWatcher;
import ir.servicea.app.PreferenceUtil;
import ir.servicea.app.StateOpenHelper;
import ir.servicea.app.Utils;
import ir.servicea.model.ModelAdvertise;
import ir.servicea.model.ModelAdvertise2;
import ir.servicea.model.ModelItemMain;
import ir.servicea.model.SliderItem;
import ir.servicea.retrofit.Api;
import ir.servicea.retrofit.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;
import ir.servicea.app.Constants.*;

public class FragmentMain extends Fragment {
    private RecyclerView gridView_main, recycle_advertise_one, recycle_advertise_two, recycle_advertise_top;
    private List<ModelItemMain> number = new ArrayList<>();
    private List<ModelAdvertise> advertises = new ArrayList<>();
    private List<ModelAdvertise> advertisess = new ArrayList<>();
    private List<ModelAdvertise2> advertises2 = new ArrayList<>();
    private TextView txt_tile_action_bar;
    private ImageView ic_reserve_list;

    private EditText edt1, edt2, edt3, edt4, edt5, edt6, edt7, edt8;
    private TextView charge, buysharge, txt_search_plak, txt_search_phone, txt_title_advertise1, txt_title_advertise2, txt_title_advertisetop;
    private TextInputLayout edt_phone;
    private TextInputEditText edt_phone_number;
    private LinearLayout ly_plak;
    private DataBaseHelper mDBHelper;
    private SQLiteDatabase mDatabase;
    private SliderView sliderView;
    private SliderAdapterExample adapter;
    PreferenceUtil preferenceUtil;
    private AdapterListAdvertise blogCar;
    private AdapterListAdvertise blogEdu;
    public ViewGroup searchpelak;
    private ViewGroup chargepanel;

    private ViewGroup ly_plk_general,ly_plk_azad_new,ly_plk_azad_old, ly_pelak_type;

    private PLAK_TYPE plak_type = PLAK_TYPE.PLAK_GENERAL;
    private ViewGroup plak_layout;

    private TextView tv_plk_type_menu, tv_plk_type_general, tv_plk_type_azad_new, tv_plk_type_azad_old;

    private boolean isVisiblePlakLayout = false;

    private Spinner spinner_plk_azad_old;

    private String selectedCityForPlk;
    private int selectedCityForPlkIndex;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        preferenceUtil = new PreferenceUtil(getContext());
        number.add(new ModelItemMain(4, "اطلاعات خودرو", R.drawable.ic_car_info, R.drawable.ic_bg_car_info));
        number.add(new ModelItemMain(3, "پیامک", R.drawable.ic_mail, R.drawable.ic_main_bg_message));
        number.add(new ModelItemMain(1, "سرویس\u200Cها", R.drawable.ic_services, R.drawable.ic_bg_services));
        number.add(new ModelItemMain(5, "مشتریان", R.drawable.ic_customer, R.drawable.ic_bg_customer));
        number.add(new ModelItemMain(8, "تنظیمات", R.drawable.ic_setting, R.drawable.ic_bg_setting));
        number.add(new ModelItemMain(7, "فروشگاه", R.drawable.ic_repeat, R.drawable.ic_bg_store));
//        number.add(new ModelItemMain(10, "گروه کالا", R.drawable.ic_product_group, R.drawable.ic_bg_store));
        number.add(new ModelItemMain(6, "گزارش مالی", R.drawable.ic_coin, R.drawable.ic_bg_coin));
        number.add(new ModelItemMain(2, "گزارش", R.drawable.ic_report, R.drawable.ic_bg_report));
//            number.add(new ModelItemMain(7, "گروه کالا", R.drawable.ic_product_group, R.drawable.ic_bg_produce));
        mDBHelper = new DataBaseHelper(getContext());
        mDatabase = mDBHelper.getReadableDatabase();

        findView(view);
        PlakListener();
        onClick();
        setPlakAzadOldSpinner();

//        txt_tile_action_bar.setTypeface(G.ExtraBold);
        txt_title_advertise1.setTypeface(G.Bold);
        txt_title_advertise2.setTypeface(G.Bold);
        txt_title_advertisetop.setTypeface(G.Bold);
        edt1.requestFocus();

//      txt_tile_action_bar.setText(preferenceUtil.getName_auto_service());
        gridView_main.setLayoutManager(new GridLayoutManager(getContext(), 4));
        gridView_main.setAdapter(new AdapterListGridMain(getContext(), number));
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recycle_advertise_one.setLayoutManager(horizontalLayoutManagaer);
        blogEdu = new AdapterListAdvertise(getContext(), advertises);
        recycle_advertise_one.setAdapter(blogEdu);

        LinearLayoutManager horizontalLayoutManagaer2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recycle_advertise_two.setLayoutManager(horizontalLayoutManagaer2);
        blogCar = new AdapterListAdvertise(getContext(), advertisess);
        recycle_advertise_two.setAdapter(blogCar);
        getBlogs();

        advertises2.add(new ModelAdvertise2("فست فود کلیز", R.drawable.slider1));
        advertises2.add(new ModelAdvertise2("فست فود کلیز", R.drawable.slider2));
        advertises2.add(new ModelAdvertise2("فست فود کلیز", R.drawable.slider3));
        advertises2.add(new ModelAdvertise2("فست فود کلیز", R.drawable.slider4));
        advertises2.add(new ModelAdvertise2("فست فود کلیز", R.drawable.slider1));
        advertises2.add(new ModelAdvertise2("فست فود کلیز", R.drawable.slider2));
        advertises2.add(new ModelAdvertise2("فست فود کلیز", R.drawable.slider3));
        advertises2.add(new ModelAdvertise2("فست فود کلیز", R.drawable.slider4));

        LinearLayoutManager horizontalLayoutManagaer3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recycle_advertise_top.setLayoutManager(horizontalLayoutManagaer3);
        recycle_advertise_top.setAdapter(new AdapterListAdvertiseTwo(getContext(), advertises2));


        adapter = new SliderAdapterExample(getContext());
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
        getSlider();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
            }
        });
        view.findViewById(R.id.txt_title_advertise2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), WebViewActivity.class)
                        .putExtra("LINK", G.LINK_BLOG_Educational)
                        .putExtra("TITLE", "مجله آموزشی"));
            }
        });
        view.findViewById(R.id.txt_title_advertise1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), WebViewActivity.class)
                        .putExtra("LINK", G.LINK_BLOG_Car)
                        .putExtra("TITLE", "مجله خودرو"));
            }
        });
        G.PusherBeam(PreferenceUtil.getUser_id());

        return view;
    }

    public void onclickAlamrs(View v) {
        startActivity(new Intent(getContext(), AlarmsActivity.class));
    }

    public void checkTag(String tag) {
        tag = G.CreateSyntaxPlak(tag);
        G.loading(getContext());
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);

        api.checkTag("car_plate,cs," + tag).enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.body() != null) {
                    String result = G.getResult(response);

                    JSONObject object = G.StringtoJSONObject(result);
                    JSONArray array = G.ObjecttoArray(object, "records");
                    G.Log(result);
                    if (array.length() > 0) {

                        try {
                            JSONObject info = array.getJSONObject(0);

                            String user_id = "";
                            String name = "";
                            String lastname = "";
                            String sex = "";
                            String birth_date = "";
                            String phone = "";
                            String register_date = "";

                            if (info != null && info.length() > 0 && info.has("cust_id")) {
                                user_id = info.getInt("cust_id") + "";
                                name = info.getString("f_name");
                                lastname = info.getString("l_name");
                                sex = info.getString("sex");
                                if (sex.contains("M") || sex.contains("m")) {
                                    sex = "آقا";
                                }
                                if (sex.contains("F") || sex.contains("f")) {
                                    sex = "خانم";
                                }
                                birth_date = info.getString("birthdate");
                                phone = info.getString("mobile");
                                register_date = info.getString("cust_created_at");
                            }
                            int car_id = 0;
                            String car_tag = "";
                            String car_name = "";
                            String car_company_name = "";
                            String car_model = "";
                            String car_type = "";
                            String fuel_type = "";
                            int car_name_id = 0, car_tip_id = 0, car_model_id = 0, fuel_type_id = 0, car_company_id = 0;
                            if (info.has("car_id")) {
                                car_id = info.getInt("car_id");
                                car_tag = info.getString("car_plate");
                                car_company_name = (info.getString("car_company_name") + "").replace("null", "");
                                car_type = (info.getString("car_tip") + "").replace("null", "");
                                car_name = (info.getString("car_name") + "").replace("null", "");
                                if (car_company_name.length() > 0) {
                                    car_name = car_company_name + " - " + car_name;
                                }
                                if (car_type.length() > 0) {
                                    car_name = car_name + " - " + car_type;
                                }
                                car_model = info.getString("car_model");
                                car_type = info.getString("car_tip");
                                fuel_type = info.getString("fuel_type");
                                car_name_id = info.getInt("car_name_id");
                                car_company_id = info.getInt("car_company_id");

                                if (!(info.getString("car_tip_id") + "").contains("null")) {
                                    car_tip_id = info.getInt("car_tip_id");
                                }
                                car_model_id = info.getInt("car_model_id");
                                fuel_type_id = info.getInt("fuel_type_id");
                            }

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (mDBHelper != null) {
                                        mDBHelper.deleteHistoryKhadamt(G.preference.getInt("idService", 0) + "");
                                    }
                                }
                            }, 1500);
                            Intent intent = new Intent(getContext(), AddServicesActivity.class);
                            intent.putExtra("idCustomer", user_id + "");

                            intent.putExtra("firstName", name);
                            intent.putExtra("lastName", lastname);
                            intent.putExtra("phone", phone);
                            intent.putExtra("plak", car_tag);
                            intent.putExtra(Constants.CAR_PLATE_TYPE, plak_type);
                            Log.d("TestPlakLayout", "setPlakLayout2: " + plak_type );
                            intent.putExtra("nameCar", car_name);
                            intent.putExtra("id_customer", user_id + "");

                            intent.putExtra(Constants.CAR_ID, car_id + "" );
                            Log.d("CARRRR", "onResponse:FragmentMain:CheckTag " + car_id);

                            intent.putExtra("gender", sex);
                            intent.putExtra("date_birthday", birth_date);
                            intent.putExtra("type_fule", fuel_type);
                            intent.putExtra("date_save", "");
                            intent.putExtra("type_car", car_type);
                            intent.putExtra("model_car", car_model);
                            intent.putExtra("description", "");
                            intent.putExtra("finish", "");
                            intent.putExtra("car_name_id", car_name_id);
                            intent.putExtra("car_tip_id", car_tip_id);
                            intent.putExtra("car_model_id", car_model_id);
                            intent.putExtra("car_company_id", car_company_id);
                            intent.putExtra("fuel_type_id", fuel_type_id);
                            intent.putExtra("fromMain", true);
                            startActivity(intent);


                        } catch (JSONException e) {
                            Toast.makeText(getContext(), "مشکل در دریافت اطلاعات", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    } else {
                        G.stop_loading();
                        Intent intent = new Intent(getContext(), AddCustomerActivity.class);
                        intent.putExtra("firstName", "null");
                        intent.putExtra("plak", getPlakValue());
                        intent.putExtra(Constants.PLAK_AZAD_OLD_CITY_INDEX, selectedCityForPlkIndex);
                        intent.putExtra(Constants.CAR_PLATE_TYPE, plak_type);
                        startActivity(intent);
                        preferenceUtil.cashNewCustomerPlak(edt1.getText().toString(), edt2.getText().toString(), edt3.getText().toString(), edt4.getText().toString(), edt5.getText().toString(), edt6.getText().toString(), edt7.getText().toString(), edt8.getText().toString());
                        G.toast("موردی یافت نشد، مشتری جدید را ثبت کنید.");

                    }
                }
                G.stop_loading();
                clearPlakEditText();


            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                Toast.makeText(getContext(), "مشکل در برقراری ارتباط", Toast.LENGTH_SHORT).show();
                clearPlakEditText();

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (G.preference.getBoolean("ActivateRad", false)) {
                    getProfile();
                }
            }
        }, 1000);


    }

    public void checkPhone(String phone) {

        G.loading(getContext());
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        api.checkPhone("mobile,cs," + phone).enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                String result = G.getResult(response);
                JSONObject object = G.StringtoJSONObject(result);
                JSONArray array = G.ObjecttoArray(object, "records");
//                Log.e(G.TAG, result);
                if (array.length() > 0) {

                    try {
                        JSONObject info = array.getJSONObject(0);
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
                        String car_name = "";
                        String car_model = "";
                        String car_type = "";
                        String fuel_type = "";
                        String car_company_name = "";

                        if (info.has("car_id")) {
                            car_id = info.getInt("car_id");
                            car_tag = info.getString("car_plate");
                            car_company_name = (info.getString("car_company_name") + "").replace("null", "");
                            car_type = (info.getString("car_tip") + "").replace("null", "");
                            car_name = (info.getString("car_name") + "").replace("null", "");
                            if (car_company_name.length() > 0) {
                                car_name = car_company_name + " - " + car_name;
                            }
                            if (car_type.length() > 0) {
                                car_name = car_name + " - " + car_type;
                            }
                            car_model = info.getString("car_model");
                            car_type = info.getString("car_tip");
                            fuel_type = info.getString("fuel_type");
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (mDBHelper != null) {
                                    mDBHelper.deleteHistoryKhadamt(G.preference.getInt("idService", 0) + "");
                                }
                            }
                        }, 0);
                        Intent intent = new Intent(getContext(), AddServicesActivity.class);
                        intent.putExtra("idCustomer", user_id + "");
                        intent.putExtra("firstName", name);
                        intent.putExtra("lastName", lastname);
                        intent.putExtra("phone", phone);
                        intent.putExtra("plak", car_tag);
                        intent.putExtra(Constants.CAR_PLATE_TYPE, plak_type);
                        intent.putExtra("nameCar", car_name);
                        intent.putExtra("id_customer", user_id + "");
                        intent.putExtra("id_car", car_id);
                        intent.putExtra("gender", sex);
                        intent.putExtra("date_birthday", birth_date);
                        intent.putExtra("type_fule", fuel_type);
                        intent.putExtra("date_save", "");
                        intent.putExtra("type_car", car_type);
                        intent.putExtra("model_car", car_model);
                        intent.putExtra("description", "");
                        intent.putExtra("finish", "");
                        intent.putExtra("fromMain", true);
                        startActivity(intent);

                    } catch (JSONException e) {
                        Toast.makeText(getContext(), "مشکل در دریافت اطلاعات", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    G.stop_loading();

                } else {
                    G.stop_loading();
                    Intent intent = new Intent(getContext(), AddCustomerActivity.class);
                    intent.putExtra("firstName", "null");
                    startActivity(intent);
                    preferenceUtil.cashNewCustomerPhone(phone);
                    Toast.makeText(getContext(), "موردی یافت نشد، مشتری جدید را ثبت کنید.", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                Toast.makeText(getContext(), "مشکل در برقراری ارتباط", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onClick() {
        ic_reserve_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ListReserveActivity.class));

            }
        });

        txt_search_plak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String plak = getPlakValueForSearch();

               // String plak = edt1.getText().toString() + edt2.getText().toString() + edt3.getText().toString() + edt4.getText().toString() + edt5.getText().toString() + edt6.getText().toString() + edt7.getText().toString() + edt8.getText().toString();
                String phone = edt_phone_number.getText().toString();
                Log.d("PLAK", "onClick:plkk: " + plak );
//                String endPlak = edt8.getText().toString();
                if (!TextUtils.isEmpty(phone) || !TextUtils.isEmpty(plak)) {
                    if (phone.equals("")) {
                        if ((plak.length()>=8)) {
                            checkTag(plak);
                        } else if ((plak.length()<8)) edt1.setError("پلاک را به درستی وارد کنید");
                    } else if (plak.equals("")) {
                        if (isValidMobile(phone)) {
                            checkPhone(phone);
                        } else {
                            edt_phone_number.setError("شماره موبایل را به درستی وارد کنید");
                        }
                    }
                } else if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(plak)) {
                    Toast.makeText(getContext(), "پلاک را وارد کنید", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(), "پلاک را وارد کنید", Toast.LENGTH_SHORT).show();
                }


            }
        });

        txt_search_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SimpleScannerActivity.class));
//                if (txt_search_phone.getText().equals("جستجو شماره موبایل")) {
//                    txt_search_phone.setText("جستجو پلاک");
//                    edt1.setText("");
//                    edt2.setText("");
//                    edt3.setText("");
//                    edt4.setText("");
//                    edt5.setText("");
//                    edt6.setText("");
//                    edt7.setText("");
//                    edt8.setText("");
//                    ly_plak.setVisibility(View.GONE);
//                    edt_phone.setVisibility(View.VISIBLE);
//                    txt_search_phone.setTextColor(getResources().getColor(R.color.text_low_dark));
//
//                } else if (txt_search_phone.getText().equals("جستجو پلاک")) {
//                    edt_phone_number.setText("");
//                    txt_search_phone.setText("جستجو شماره موبایل");
//                    ly_plak.setVisibility(View.VISIBLE);
//                    edt_phone.setVisibility(View.GONE);
//                    txt_search_phone.setTextColor(getResources().getColor(R.color.text_low_dark));
//                }
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

     private void setPlakAzadOldSpinner(){

         ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
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
                 selectedCityForPlkIndex = 0;
                 selectedCityForPlk = parentView.getItemAtPosition(0).toString();
             }
         });
     }

    private boolean keydel = false;

    private void PlakListener() {
        List<EditText> editTextsInPlakLayout = findEditTextsInLayout(plak_layout);
        Log.d("Maaain", "PlakListener: " + editTextsInPlakLayout.toString());
        Log.d("Maaain", "PlakListener: " + plak_layout.toString());
        setDeletionListener(editTextsInPlakLayout);
        for (int i = 0; i < editTextsInPlakLayout.size() - 1; i++) {
            EditText currentEditText = editTextsInPlakLayout.get(i);
            EditText nextEditText = editTextsInPlakLayout.get(i + 1);
            currentEditText.addTextChangedListener(new PlakTextWatcher(currentEditText, nextEditText));
        }

        edt8.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                edt1.requestFocus();
                txt_search_plak.performClick();
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

    void findView(View view) {
        edt1 = view.findViewById(R.id.edt1);
        edt2 = view.findViewById(R.id.edt2);
        edt3 = view.findViewById(R.id.edt3);
        edt4 = view.findViewById(R.id.edt4);
        edt5 = view.findViewById(R.id.edt5);
        edt6 = view.findViewById(R.id.edt6);
        edt7 = view.findViewById(R.id.edt7);
        edt8 = view.findViewById(R.id.edt8);
        gridView_main = view.findViewById(R.id.gridView_main);
//        txt_tile_action_bar = view.findViewById(R.id.txt_tile_action_bar);
        txt_search_plak = view.findViewById(R.id.txt_search_plak);
        txt_search_phone = view.findViewById(R.id.txt_search_phone);
        edt_phone = view.findViewById(R.id.edt_phone);
        ly_plak = view.findViewById(R.id.ly_plak);
        edt_phone_number = view.findViewById(R.id.edt_phone_number);
        recycle_advertise_two = view.findViewById(R.id.recycle_advertise_two);
        recycle_advertise_one = view.findViewById(R.id.recycle_advertise_one);
        txt_title_advertise1 = view.findViewById(R.id.txt_title_advertise1);
        txt_title_advertise2 = view.findViewById(R.id.txt_title_advertise2);
        txt_title_advertisetop = view.findViewById(R.id.txt_title_advertisetop);
        recycle_advertise_top = view.findViewById(R.id.recycle_advertise_top);
        sliderView = view.findViewById(R.id.sliderView);
        searchpelak = view.findViewById(R.id.searchpelak);
        chargepanel = view.findViewById(R.id.chargepanel);
        charge = view.findViewById(R.id.charge);
        buysharge = view.findViewById(R.id.buysharge);
        ic_reserve_list = view.findViewById(R.id.ic_reserve_list);
        tv_plk_type_menu = view.findViewById(R.id.tv_plk_type_menu);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ShowCase();
            }
        }, 1200);

        ly_plk_general = view.findViewById(R.id.ly_plk_general);
        ly_plk_azad_new = view.findViewById(R.id.ly_plk_azad_new);
        ly_plk_azad_old = view.findViewById(R.id.ly_plk_azad_old);
        plak_layout = ly_plk_general;
        ly_pelak_type = view.findViewById(R.id.ly_pelak_type);
        tv_plk_type_general = view.findViewById(R.id.tv_plk_type_general);
        tv_plk_type_azad_new = view.findViewById(R.id.tv_plk_type_azad_new);
        tv_plk_type_azad_old = view.findViewById(R.id.tv_plk_type_azad_old);
        spinner_plk_azad_old = view.findViewById(R.id.spinner_plk_azad_old);
    }

    public void ShowCase() {
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view
        config.setShapePadding(50);
        config.setMaskColor(Color.parseColor("#cc222222"));

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(getActivity(), "ShoowCaseMain");

        sequence.setConfig(config);

        android.app.Activity activity = getActivity();
        sequence.addSequenceItem(Utils.createCustomShowcaseView(activity,searchpelak, getString(R.string.showcase_search_plak), getString(R.string.next_showcase)));
        sequence.addSequenceItem(Utils.createCustomShowcaseView(activity,AdapterListGridMain.listCustView, getString(R.string.showcase_list_cust_view), getString(R.string.next_showcase)));
        sequence.addSequenceItem(Utils.createCustomShowcaseView(activity,AdapterListGridMain.listServiceView, getString(R.string.showcase_list_service_view), getString(R.string.close_showcase)));
        sequence.start();
    }

    public void getSlider() {

//        G.loading(G.Activity);
        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.getSlider();
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.body() != null) {
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
                                String image = obj.getString("image");
                                String url = obj.getString("url");
                                sliderItem.setDescription(title);
                                sliderItem.setUrl(url);
                                sliderItem.setImageUrl(G.PreImagesURL + "slides/" + image);
                                sliderItemList.add(sliderItem);
                            }
                            adapter.renewItems(sliderItemList);
                        } else {
                            G.toast("مشکل در دریافت اطلاعات");
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
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

    public void getProfile() {

//        G.loading(G.Activity);
        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.getProfile("id,eq," + d_id);
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

                                }
                                if (obj.has("header_photo")) {
                                    String header_photo = obj.getString("header_photo");
                                    G.preference.edit().putString("header_photo", header_photo).apply();

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
                                String opentime = services_center.getString("f_open");
                                String closetime = services_center.getString("f_close");
                                String opentime2 = services_center.getString("l_open");
                                String closetime2 = services_center.getString("l_close");
                                int num_branch = services_center.getInt("numOfBranch");
                                int waiting_space = services_center.getBoolean("waiting_place") ? 1 : 0;
                                int catering_service = services_center.getBoolean("bar_serv") ? 1 : 0;
                                boolean status = services_center.getBoolean("status");
                                G.preference.edit().putBoolean("ServiceCenterStatus", status).apply();
//                            if (!status) {
//                                G.toast(getResources().getString(R.string.statusfalse));
//                            }
                                if (services_center.has("charge_total") && services_center.has("charge_remain")) {
                                    int charge_total = services_center.getInt("charge_total");
                                    int charge_remain = services_center.getInt("charge_remain");
                                    if(charge_total>10){
                                        charge_total = charge_total/10;
                                    }
                                    if(charge_remain>10){
                                        charge_remain = charge_remain/10;
                                    }
                                    G.preference.edit().putInt("charge_total", charge_total).apply();
                                    G.preference.edit().putInt("charge_remain", charge_remain).apply();
                                } else {
                                    G.preference.edit().putInt("charge_total", 0).apply();
                                    G.preference.edit().putInt("charge_remain", 0).apply();
                                }
                                int charge_remain = G.preference.getInt("charge_remain", 0);
                                chargepanel.setVisibility(View.VISIBLE);
                                charge.setText(G.getDecimalFormattedString(charge_remain + ""));
                                buysharge.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startActivity(new Intent(getContext(), buyCreditActivity.class));
                                    }
                                });
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

                                PreferenceUtil.cashD_id(d_id);
                                PreferenceUtil.cashPhone(phone);
                                PreferenceUtil.cashInfo(name, lastname, address, shop_name);
                                StateOpenHelper openHelper = new StateOpenHelper(G.Activity);
                                openHelper.openDatabase();
                                SQLiteDatabase mDatabase = openHelper.getReadableDatabase();
//                            if (province.length() > 0) {
//                                Cursor cursor = mDatabase.query("Tbl_Ostan", null, null, null, null, null, null);
//                                cursor.moveToFirst();
//                                List<String> stateName = new ArrayList<>();
//                                for (int x = 0; x < cursor.getCount(); x++) {
//                                    State state = new State();
//                                    state.setCityName(cursor.getString(cursor.getColumnIndex("Title")));
//                                    state.setCityId(cursor.getInt(cursor.getColumnIndex("ID")));
//                                    if ((state.getCityId() + "").equals(province)) {
//                                        PreferenceUtil.cashOstan(Integer.parseInt(province), state.getCityName());
//                                    }
//                                    stateName.add(state.getCityName());
//                                    cursor.moveToNext();
//                                }
//
//
//                            }
//                            if (city.length() > 0) {
//                                Cursor cursor = mDatabase.query("Tbl_shahrestan", null, "PK_Ostan = ?", new String[]{province}, null, null, null);
//                                cursor.moveToFirst();
//                                for (int x = 0; x < cursor.getCount(); x++) {
//                                    State state = new State();
//                                    state.setCityName(cursor.getString(cursor.getColumnIndex("Title")));
//                                    state.setCityId(cursor.getInt(cursor.getColumnIndex("ID")));
//                                    if ((state.getCityId() + "").equals(city)) {
//                                        PreferenceUtil.cashCity(Integer.parseInt(city), state.getCityName());
//                                    }
//                                    cursor.moveToNext();
//                                }
//
//
//                            }


                            }
                        } else {
                            G.toast("مشکل در دریافت اطلاعات");
                        }
                    } catch (JSONException | IOException e) {
                        G.toast("مشکل در تجزیه اطلاعات");
                        e.printStackTrace();
                    }
                }
                G.stop_loading();
                getSlider();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

    public void removeLastItem(View view) {
        if (adapter.getCount() - 1 >= 0)
            adapter.deleteItem(adapter.getCount() - 1);
    }

    private Boolean isValidMobile(String mobile_number) {
        String MOBILE_NUM_PATTERN_ST = "(\\+98|0)?9\\d{9}";
        Pattern MOBILE_NUM_PATTERN_REX = Pattern.compile(MOBILE_NUM_PATTERN_ST);
        return MOBILE_NUM_PATTERN_REX.matcher(mobile_number).matches();
    }

    @Override
    public void onStart() {
        super.onStart();
        edt1.setText("");
        edt2.setText("");
        edt3.setText("");
        edt4.setText("");
        edt5.setText("");
        edt6.setText("");
        edt7.setText("");
        edt8.setText("");
        edt1.requestFocus();
        edt_phone_number.setText("");
    }

    public void getBlogs() {
        G.loading(getActivity());
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.blogs("");
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.body() != null) {
                    String result = G.getResult(response);
                    JSONObject object = G.StringtoJSONObject(result);
                    JSONArray array = G.ObjecttoArray(object, "records");
                    if (array.length() > 0) {
                        try {
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);
                                int id = obj.getInt("id");
                                int type = obj.getInt("type");
                                String title = obj.getString("title");
                                String summary = obj.getString("summary");
                                String content = obj.getString("content");
                                String image = obj.getString("image");
                                String slug = obj.getString("slug");
                                if (type == 1)
                                    advertises.add(new ModelAdvertise(title, image, slug));
                                if (type == 2)
                                    advertisess.add(new ModelAdvertise(title, image, slug));


                            }

                            blogEdu.notifyDataSetChanged();
                            blogCar.notifyDataSetChanged();

                            G.stop_loading();

                        } catch (JSONException e) {
                            G.toast("مشکل در دریافت اطلاعات");
                            e.printStackTrace();
                        }


                    } else {

                        G.stop_loading();

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
            } else {
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
        Log.d("getPlakValue22:", "getPlakValue: "+plak );
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

    private void clearPlakEditText() {
        List<EditText> editTextsInPlakLayout = findEditTextsInLayout(plak_layout);
        for (int i = 0; i < editTextsInPlakLayout.size() ; i++) {
            editTextsInPlakLayout.get(i).setText("");
        }
    }

    private void setPlakTypeBasedEndPlak(String endPlak) {
        if(endPlak.equals("ع") || endPlak.equals("ت")) plak_type = PLAK_TYPE.PLAK_TAXI;
        else if (endPlak.equals("ا"))  plak_type = PLAK_TYPE.PLAK_EDARI;
        else if (endPlak.equals("پ") || endPlak.equals("ث"))  plak_type = PLAK_TYPE.PLAK_ENTEZAMI;
        else if (endPlak.equals("#"))  plak_type = PLAK_TYPE.PLAK_MAOLOIN;
        else plak_type = PLAK_TYPE.PLAK_GENERAL;
        Log.d("TestPlakLayout", "setPlakLayout:1 " + plak_type );

    }

}