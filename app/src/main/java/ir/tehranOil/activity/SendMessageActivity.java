package ir.tehranOil.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import ir.tehranOil.app.G;
import ir.tehranOil.model.dbModel.ModelAddMessage;
import ir.tehranOil.model.dbModel.ModelCustomer;

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
import ir.tehranOil.adapter.AdapterShowOnvanMessage;
import ir.tehranOil.model.ModelOnvanMssg;
import ir.tehranOil.app.PreferenceUtil;
import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class SendMessageActivity extends AppCompatActivity {

    private TextView txt_tile_action_bar, txt_send_taki, txt_send_anboh, txt_send_group, txt_name_family, txt_phone;
    private LinearLayout ly_taki, ly_anboh, ly_group, ly_info, ly_recycle1, ly_recycle2, ly_recycle3;
    private ImageView img_back, img_add_message, img_search1;
    private Button btn_detect_text_message, btn_detect_text_message2, btn_detect_text_message3;
    private Button btn_date_service1, btn_date_service2, btn_date_service3;
    private CheckBox chbox_add_message, chbox_add_messageG, chbox_add_messageA;
    private Spinner spinner_message;
    private EditText edt_text_message, edt_text_messageG, edt_text_messageA, edt_search_phone;
    private List<String> message = new ArrayList<>();
    private RecyclerView recycle_onvan_message, recycle_onvan_message2, recycle_onvan_message3;
    private AdapterShowOnvanMessage adapter;
    private List<ModelOnvanMssg> mssgList = new ArrayList<>();
    private AlertDialog alertDialogs_insert_onvan;
    private PersianDatePickerDialog mDatePicker;
    private DataBaseHelper mDBHelper;
    private SQLiteDatabase mDatabase;
    private List<ModelCustomer> listCustomers = new ArrayList();
    private AdapterShowOnvanMessage.OnItemClickListener onItemClickListener;
    String title = "";
    private Button btn_send;
    private SwipeRefreshLayout swipeRefreshLayout;

    public void onclickAlamrs(View v) {
        startActivity(new Intent(SendMessageActivity.this, AlarmsActivity.class));
    }
    @Override
    public void onResume(){
        super.onResume();
        G.Activity = this;
        G.context = this;
    }
    boolean beforeset = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        G.Activity = this;
        G.context = this;
        mDBHelper = new DataBaseHelper(this);
        mDatabase = mDBHelper.getReadableDatabase();
        FindView();
        onclick();
        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.button));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       swipeRefreshLayout.setRefreshing(false);
                   }
               },800);
            }
        });
        txt_tile_action_bar.setText("ارسال پیامک");
        txt_tile_action_bar.setTypeface(G.Bold);
        PersianCalendar initDate = new PersianCalendar();
        btn_date_service1.setText(initDate.getPersianYear() + "/" + initDate.getPersianMonth() + "/" + initDate.getPersianDay());
        btn_date_service2.setText(initDate.getPersianYear() + "/" + initDate.getPersianMonth() + "/" + initDate.getPersianDay());
        btn_date_service3.setText(initDate.getPersianYear() + "/" + initDate.getPersianMonth() + "/" + initDate.getPersianDay());

        message.add("انتخاب گروه");
        SpinnerAdapter spinnerAdapter = new ArrayAdapter(this, R.layout.item_spiner, message);
        ((ArrayAdapter) spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_message.setAdapter(spinnerAdapter);

        if (!getIntent().getExtras().getString("firstName").equals("null")) {
            beforeset = true;
            edt_search_phone.setText(getIntent().getExtras().getString("phone"));
            txt_phone.setText(getIntent().getExtras().getString("phone"));
            txt_name_family.setText(getIntent().getExtras().getString("firstName") + " " + getIntent().getExtras().getString("lastName"));
        } else {
            ly_info.setVisibility(View.GONE);
        }

        onItemClickListener = new AdapterShowOnvanMessage.OnItemClickListener() {
            @Override
            public void onItemClick(ModelAddMessage model, int position) {
                if (ly_taki.getVisibility() == View.VISIBLE) {
                    edt_text_message.setText(model.getText().toString());
                    title = model.getTitle().toString();
                } else if (ly_group.getVisibility() == View.VISIBLE) {
                    edt_text_messageG.setText(model.getText().toString());
                    title = model.getTitle().toString();
                } else if (ly_anboh.getVisibility() == View.VISIBLE) {
                    edt_text_messageA.setText(model.getText().toString());
                    title = model.getTitle().toString();
                }


            }
        };

    }
public int sms_type=1;
    private void FindView() {
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        img_back = findViewById(R.id.img_back);
        img_add_message = findViewById(R.id.img_add_message);
        recycle_onvan_message = findViewById(R.id.recycle_onvan_message);
        recycle_onvan_message2 = findViewById(R.id.recycle_onvan_message2);
        recycle_onvan_message3 = findViewById(R.id.recycle_onvan_message3);
        ly_recycle1 = findViewById(R.id.ly_recycle1);
        ly_recycle2 = findViewById(R.id.ly_recycle2);
        ly_recycle3 = findViewById(R.id.ly_recycle3);
        txt_send_taki = findViewById(R.id.txt_send_taki);
        txt_send_anboh = findViewById(R.id.txt_send_anboh);
        txt_send_group = findViewById(R.id.txt_send_group);
        ly_taki = findViewById(R.id.ly_taki);
        ly_anboh = findViewById(R.id.ly_anboh);
        ly_group = findViewById(R.id.ly_group);
        btn_detect_text_message = findViewById(R.id.btn_detect_text_message);
        btn_detect_text_message2 = findViewById(R.id.btn_detect_text_message2);
        btn_detect_text_message3 = findViewById(R.id.btn_detect_text_message3);
        spinner_message = findViewById(R.id.spinner_message);
        txt_name_family = findViewById(R.id.txt_name_family);
        txt_phone = findViewById(R.id.txt_phone);
        ly_info = findViewById(R.id.ly_info);
        btn_date_service1 = findViewById(R.id.btn_date_service1);
        btn_date_service2 = findViewById(R.id.btn_date_service2);
        btn_date_service3 = findViewById(R.id.btn_date_service3);
        chbox_add_message = findViewById(R.id.chbox_add_message);
        chbox_add_messageG = findViewById(R.id.chbox_add_messageG);
        chbox_add_messageA = findViewById(R.id.chbox_add_messageA);
        edt_text_message = findViewById(R.id.edt_text_message);
        edt_text_messageG = findViewById(R.id.edt_text_messageG);
        edt_text_messageA = findViewById(R.id.edt_text_messageA);
        edt_search_phone = findViewById(R.id.edt_search_phone);
        img_search1 = findViewById(R.id.img_search1);
        btn_send = findViewById(R.id.btn_save_service);
    }

    private void onclick() {
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SendSms", "onResponse:sms_type: " +sms_type );

                if(sms_type ==1) {
                    if ((edt_search_phone.getText().toString().length() != 11 && txt_phone.getText().toString().length() != 11)) {
                        G.toast("شماره موبایل را به درستی وارد کنید");
                    } else if (edt_text_message.getText().toString().length() < 3) {
                        G.toast("متن پیام را به درستی وارد کنید");
                    } else {
                        String phone = edt_search_phone.getText().toString();
                        if (phone.length() != 11) {
                            phone = txt_phone.getText().toString();
                        }
                        sendSMSText(phone, edt_text_message.getText().toString());
                    }
                }else {
                    String d_id = PreferenceUtil.getD_id();
                     if (edt_text_messageA.getText().toString().length() < 3) {
                        G.toast("متن پیام را به درستی وارد کنید");
                    } else {
                        sendSMSText("ALL_USER_"+d_id, edt_text_messageA.getText().toString());
                    }
                }
            }
        });
        txt_send_taki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_send_taki.setBackgroundResource(R.drawable.shap_btn_blue_select);
                txt_send_anboh.setBackgroundResource(R.drawable.shap_simple_rec);
                txt_send_group.setBackgroundResource(R.drawable.shap_simple_rec);
                ly_taki.setVisibility(View.VISIBLE);
                ly_anboh.setVisibility(View.GONE);
                ly_group.setVisibility(View.GONE);
                sms_type = 1;
            }
        });
        txt_send_anboh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_send_anboh.setBackgroundResource(R.drawable.shap_btn_blue_select);
                txt_send_taki.setBackgroundResource(R.drawable.shap_simple_rec);
                txt_send_group.setBackgroundResource(R.drawable.shap_simple_rec);
                ly_taki.setVisibility(View.GONE);
                ly_anboh.setVisibility(View.VISIBLE);
                ly_group.setVisibility(View.GONE);
                btn_send = findViewById(R.id.btn_send_anbouh);
                sms_type = 2;
                onclick();
            }
        });
        txt_send_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_send_group.setBackgroundResource(R.drawable.shap_btn_blue_select);
                txt_send_anboh.setBackgroundResource(R.drawable.shap_simple_rec);
                txt_send_taki.setBackgroundResource(R.drawable.shap_simple_rec);
                ly_taki.setVisibility(View.GONE);
                ly_anboh.setVisibility(View.GONE);
                ly_group.setVisibility(View.VISIBLE);
                sms_type=3;
            }
        });

        mssgList.add(new ModelOnvanMssg(1, "تبریک تولد"));
        mssgList.add(new ModelOnvanMssg(1, "خوش آمدگویی"));
        mssgList.add(new ModelOnvanMssg(1, "کارت سرویس"));
        mssgList.add(new ModelOnvanMssg(1, "یادآوری سرویس"));


        btn_detect_text_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recycle_onvan_message.setLayoutManager(new LinearLayoutManager(SendMessageActivity.this, RecyclerView.HORIZONTAL, false));
                adapter = new AdapterShowOnvanMessage(SendMessageActivity.this, messages, onItemClickListener);
                recycle_onvan_message.setAdapter(adapter);
                ListSmsDraft();

            }
        });
        btn_detect_text_message2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mDBHelper.getListMessage(mDatabase).isEmpty()) {
                    ly_recycle2.setVisibility(View.VISIBLE);
                    recycle_onvan_message2.setLayoutManager(new LinearLayoutManager(SendMessageActivity.this, RecyclerView.HORIZONTAL, false));
                    adapter = new AdapterShowOnvanMessage(SendMessageActivity.this, mDBHelper.getListMessage(mDatabase), onItemClickListener);
                    recycle_onvan_message2.setAdapter(adapter);
                } else {
                    ly_recycle2.setVisibility(View.GONE);
                }
            }
        });


        btn_detect_text_message3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mDBHelper.getListMessage(mDatabase).isEmpty()) {

                    ly_recycle3.setVisibility(View.VISIBLE);
                    recycle_onvan_message3.setLayoutManager(new LinearLayoutManager(SendMessageActivity.this, RecyclerView.HORIZONTAL, false));
                    adapter = new AdapterShowOnvanMessage(SendMessageActivity.this, mDBHelper.getListMessage(mDatabase), onItemClickListener);
                    recycle_onvan_message3.setAdapter(adapter);
                } else {
                    ly_recycle3.setVisibility(View.GONE);
                }
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        img_add_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SendMessageActivity.this, CreateTextMessageActivity.class));

            }
        });

        chbox_add_message.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    String textMsg = edt_text_message.getText().toString();
                    if (textMsg.length() < 1) {
                        chbox_add_message.setChecked(false);
                        edt_text_message.setError("متن پیامک را وارد کنید.");
                    } else
                        DialogInsertOnvan(SendMessageActivity.this, textMsg, title);
                }
            }
        });

        chbox_add_messageG.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    String textMsg = edt_text_messageG.getText().toString();
                    if (textMsg.length() < 1) {
                        chbox_add_messageG.setChecked(false);
                        edt_text_messageG.setError("متن پیامک را وارد کنید.");
                    } else
                        DialogInsertOnvan(SendMessageActivity.this, textMsg, title);
                }
            }
        });

        chbox_add_messageA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    String textMsg = edt_text_messageA.getText().toString();
                    if (textMsg.length() < 1) {
                        chbox_add_messageA.setChecked(false);
                        edt_text_messageA.setError("متن پیامک را وارد کنید.");
                    } else
                        DialogInsertOnvan(SendMessageActivity.this, textMsg, title);
                }
            }
        });

        btn_date_service1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initializeDatePicker(1);
            }
        });

        btn_date_service2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initializeDatePicker(2);
            }
        });

        btn_date_service3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initializeDatePicker(3);
            }
        });

        img_search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPhone(edt_search_phone.getText().toString());

            }
        });


        edt_search_phone.addTextChangedListener(new TextWatcher() {

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
                if(!beforeset) {
                    if (s.length() > 0) {
                        checkPhone(edt_search_phone.getText().toString());
                    }
                }else{
                    beforeset = false;
                }

            }
        });
    }

    public void sendSMSText(String phone, String text) {
        G.loading(this);
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        String d_id = PreferenceUtil.getD_id();
        api.sendSMSText(null,text + "", phone, d_id).enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                G.stop_loading();
                G.Log(call.request().toString());
                Log.d("SendSms", "onResponse:request: " +call.request().toString() );
                String result = "";
                try {
                    result = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("SendSms", "onResponse:result: " +result );
                G.Log(result);
                if (result.contains("The user does not have enough charge")) {
                    G.toast("پیامک ارسال نشد شارژ کافی ندارید");
                } else {
                    if (result.length() > 4) {
                        G.toast("پیامک ارسال شد.");
                        startActivity(new Intent(SendMessageActivity.this, MainActivity.class));
                        finishAffinity();
                    } else {
                        G.toast("مشکل در ارسال پیامک");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.d("Guid", "onFailure: " + t.getMessage());
                Log.d("Guid", "onFailure:call " + call.request().toString());
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط با سرور");
            }
        });

    }

    void DialogInsertOnvan(Context context, String textMsg, String title) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_item_dialog_insert_onvan_msg, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setCancelable(true);

        alertDialogs_insert_onvan = alertDialogBuilder.create();

        alertDialogs_insert_onvan.getWindow().setGravity(Gravity.CENTER_VERTICAL);
        alertDialogs_insert_onvan.setCancelable(false);
        WindowManager.LayoutParams layoutParams = alertDialogs_insert_onvan.getWindow().getAttributes();
        alertDialogs_insert_onvan.getWindow().setAttributes(layoutParams);
        alertDialogs_insert_onvan.getWindow().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.shap_simple_rec));
        alertDialogs_insert_onvan.show();
        ImageView img_close;
        EditText edt_onvan_msg, edt_text_msg;
        Button btn_save;
        img_close = view.findViewById(R.id.img_close);
        edt_onvan_msg = view.findViewById(R.id.edt_onvan_msg);
        edt_text_msg = view.findViewById(R.id.edt_text_msg);
        btn_save = view.findViewById(R.id.btn_save);

        edt_text_msg.setText(textMsg);
        edt_onvan_msg.setText(title);
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogs_insert_onvan.dismiss();
                if (chbox_add_message.isChecked()) {
                    chbox_add_message.setChecked(false);
                }
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String onvan = edt_onvan_msg.getText().toString();
                String text = edt_text_msg.getText().toString();
                if (textMsg.length() > 1 && onvan.length() > 1) {
                    addSmsDraft(onvan, text);
                    alertDialogs_insert_onvan.dismiss();
                } else {
                    if (onvan.isEmpty()) {
                        edt_onvan_msg.setError("عنوان را وارد کنید.");
                    }
                    if (text.isEmpty()) {
                        edt_text_msg.setError("متن را وارد کنید.");
                    }
                }
            }
        });

        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        width = (int) ((width) * ((double) 9 / 10));
        alertDialogs_insert_onvan.getWindow().setLayout(width, LinearLayout.LayoutParams.WRAP_CONTENT);

    }

    public void addSmsDraft(String title, String text) {
        G.loading(this);
        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        JSONObject object = new JSONObject();
        try {
            object.put("name", title);
            object.put("text", text);
            object.put("service_center_id",d_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<ResponseBody> request = api.addSmsDraft(G.returnBody(object.toString()));
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                G.stop_loading();
                String result = G.getResult(response);
                if (result.length() > 0 && result.length() < 10) {
                    G.toast("با موفقیت ثبت شد");

                    ListSmsDraft();
                } else {
                    G.toast("مشکل در ثبت اطلاعات");
                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

    public void checkPhone(String phone) {

        if (phone.length() > 0) {
            swipeRefreshLayout.setRefreshing(true);
            Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
            api.checkPhone("mobile,cs," + phone).enqueue(new retrofit2.Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    ly_info.setVisibility(View.GONE);
                    txt_name_family.setText("");
                    txt_phone.setText("");
                    String result = G.getResult(response);
                    JSONObject object = G.StringtoJSONObject(result);
                    JSONArray array = G.ObjecttoArray(object, "records");
                    if (array.length() > 0) {

                        try {
                            JSONObject info = array.getJSONObject(0);


                            String user_id = info.getString("cust_id");
                            String name = info.getString("f_name");
                            String lastname = info.getString("l_name");
                            String phone = info.getString("mobile");


                            ly_info.setVisibility(View.VISIBLE);
                            txt_name_family.setText(name + " " + lastname);
                            txt_phone.setText(phone);
                            ly_info.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    G.toast("انتخاب شد.");edt_search_phone.setText(phone);
                                }
                            });



                        } catch (JSONException e) {
                            G.toast("مشکل در دریافت اطلاعات");
                            e.printStackTrace();
                        }


                    } else {


                        G.toast("موردی یافت نشد");


                    }
                    swipeRefreshLayout.setRefreshing(false);
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    ly_info.setVisibility(View.GONE);
                    txt_name_family.setText("");
                    txt_phone.setText("");
                    swipeRefreshLayout.setRefreshing(false);
                    G.toast("مشکل در برقراری ارتباط");
                }
            });
        } else {
            ly_info.setVisibility(View.GONE);
            txt_name_family.setText("");
            txt_phone.setText("");
            G.toast("شماره موبایل را به درستی وارد کنید");
        }
    }

    private List<ModelAddMessage> messages = new ArrayList<>();

    public void ListSmsDraft() {

        G.loading(this);
        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.listSmsDraft("service_center_id,eq," + d_id);
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                G.stop_loading();
                String result = G.getResult(response);
                JSONObject object = G.StringtoJSONObject(result);
                JSONArray array = G.ObjecttoArray(object, "records");
                messages.clear();
                if (array.length() > 0) {

                    try {
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject obj = array.getJSONObject(i);
                            int id = obj.getInt("id");
                            String title = obj.getString("name");
                            int sms_type_id =1;
                            String text = obj.getString("text");
                            ModelAddMessage message = new ModelAddMessage(id, title, text, sms_type_id, "");
                            messages.add(message);

                        }

                        ly_recycle1.setVisibility(View.VISIBLE);
                        adapter.notifyDataSetChanged();



                    } catch (JSONException e) {
                        G.toast("مشکل در دریافت اطلاعات");
                        e.printStackTrace();
                    }


                } else {
                    G.toast("متن آماده یافت نشد!");
                    ly_recycle1.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }

    private void initializeDatePicker(int type) {
        PersianCalendar initDate = new PersianCalendar();
        // btn_date_service.setText(initDate.getPersianYear() + "/" + initDate.getPersianMonth() + "/" + initDate.getPersianDay());

        mDatePicker = new PersianDatePickerDialog(SendMessageActivity.this)
                .setPositiveButtonString("باشه")
                .setNegativeButton("بیخیال")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setMinYear(1400)
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
                        if (type == 1) {
                            btn_date_service1.setText(persianCalendar.getPersianYear() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay());
                        }
                        if (type == 2) {
                            btn_date_service2.setText(persianCalendar.getPersianYear() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay());
                        }
                        if (type == 3) {
                            btn_date_service3.setText(persianCalendar.getPersianYear() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianDay());
                        }
                    }

                    @Override
                    public void onDismissed() {
                    }
                });
        mDatePicker.show();
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }
}