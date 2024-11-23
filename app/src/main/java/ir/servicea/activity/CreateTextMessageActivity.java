package ir.servicea.activity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ir.servicea.app.DataBaseHelper;
import ir.servicea.app.G;
import ir.servicea.retrofit.Api;
import ir.servicea.retrofit.RetrofitClient;
import ir.servicea.model.dbModel.ModelAddMessage;
import ir.servicea.app.PreferenceUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.servicea.R;

import ir.servicea.adapter.AdapterMessageAmade;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class CreateTextMessageActivity extends AppCompatActivity {
    private TextView txt_tile_action_bar;
    private ImageView img_back;
    private RecyclerView recycle_message_amade;
    private AdapterMessageAmade adapterMessageAmade;
    private Button btn_save_message;
    private EditText edt_title_msg, edt_text_msg;
    private LinearLayout ly_empty;
    private DataBaseHelper mDBHelper;
    private SQLiteDatabase mDatabase;
    private List<ModelAddMessage> messages = new ArrayList<>();
    @Override
    public void onResume(){
        super.onResume();
        G.Activity = this;
        G.context = this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        G.Activity = this;
        G.context = this;
        FindView();
        onClick();

        mDBHelper = new DataBaseHelper(this);
        mDatabase = mDBHelper.getReadableDatabase();
        recycle_message_amade.setLayoutManager(new LinearLayoutManager(CreateTextMessageActivity.this, RecyclerView.VERTICAL, false));

        txt_tile_action_bar.setText("ایجاد متن آماده");
        txt_tile_action_bar.setTypeface(G.Bold);

        adapterMessageAmade = new AdapterMessageAmade(CreateTextMessageActivity.this, messages);
        recycle_message_amade.setAdapter(adapterMessageAmade);
        ListSmsDraft();

    }

    public void onclickAlamrs(View v) {
        startActivity(new Intent(CreateTextMessageActivity.this, AlarmsActivity.class));
    }

    private void FindView() {
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        img_back = findViewById(R.id.img_back);
        recycle_message_amade = findViewById(R.id.recycle_message_amade);
        btn_save_message = findViewById(R.id.btn_save_message);
        edt_text_msg = findViewById(R.id.edt_text_msg);
        edt_title_msg = findViewById(R.id.edt_title_msg);
        ly_empty = findViewById(R.id.ly_empty);
    }

    private void onClick() {
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_save_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edt_text_msg.getText().toString();
                String title = edt_title_msg.getText().toString();
                if (!TextUtils.isEmpty(text) && !TextUtils.isEmpty(title)) {
                    addSmsDraft(title,text);

                } else {
                    if (title.isEmpty()) {
                        edt_text_msg.setError("عنوان پیام را وراد کنید.");
                    }
                    if (text.isEmpty()) {
                        edt_text_msg.setError("متن پیام را وراد کنید.");
                    }
                }
            }
        });
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
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

                String result = G.getResult(response);
                if (result.length() > 0 && result.length() < 10) {
                    G.toast("با موفقیت ثبت شد");
                    edt_title_msg.setText("");
                    edt_text_msg.setText("");
                    G.stop_loading();
                    ListSmsDraft();
                }else {
                    G.toast("مشکل در ثبت اطلاعات");
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

    public void ListSmsDraft() {

        G.loading(this);
        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.listSmsDraft("service_center_id,eq," + d_id);
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
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
//                            int sms_type_id = obj.getInt("sms_type_id");
                            String text = obj.getString("text");
//                            int type = obj.getInt("type");
                            ModelAddMessage message = new ModelAddMessage(id, title, text, 1, "");
                            messages.add(message);

                        }
                        ly_empty.setVisibility(View.GONE);
                        recycle_message_amade.setVisibility(View.VISIBLE);

                        adapterMessageAmade.notifyDataSetChanged();
                        G.stop_loading();

                    } catch (JSONException e) {
                        G.toast("مشکل در دریافت اطلاعات");
                        e.printStackTrace();
                    }



                } else {
                    G.stop_loading();
                    recycle_message_amade.setVisibility(View.GONE);
                    ly_empty.setVisibility(View.VISIBLE);

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