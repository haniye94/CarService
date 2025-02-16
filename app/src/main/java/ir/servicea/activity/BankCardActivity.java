package ir.servicea.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.servicea.R;
import ir.servicea.adapter.AdapterListAddedProduct;
import ir.servicea.adapter.AdapterListBankCard;
import ir.servicea.app.G;
import ir.servicea.app.PreferenceUtil;
import ir.servicea.model.ModelAddedProductCenter;
import ir.servicea.model.ModelBankCard;
import ir.servicea.retrofit.Api;
import ir.servicea.retrofit.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankCardActivity extends AppCompatActivity {

    private EditText edt_card_no, edt_sheba_no, edt_bank_name, edt_national_code;
    private Button btn_add_bank_card;
    private RecyclerView rv_bank_card;
    private TextView txt_tile_action_bar;
    private ImageView iv_back;

    private AdapterListBankCard adapterListBankCard;

    private SwipeRefreshLayout swipeRefreshLayout;
    int service_center_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_card);

        FindViews();
        InitActivity();
        OnClick();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        FetchBankCardList();
                    }
                }, 250);
            }
        });

        edt_card_no.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private final String dash = "-";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed before text change
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String cleanString = s.toString().replaceAll("[^\\d]", "");
                    StringBuilder formattedString = new StringBuilder();

                    for (int i = 0; i < cleanString.length(); i++) {
                        // Add the digit
                        formattedString.append(cleanString.charAt(i));
                        // Add a dash every four digits (if not end of string)
                        if ((i + 1) % 4 == 0 && (i + 1) < cleanString.length()) {
                            formattedString.append(dash);
                        }
                    }

                    current = formattedString.toString();
                    edt_card_no.setText(current);
                    edt_card_no.setSelection(current.length()); // Move cursor to the end
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No action needed after text change
            }
        });
        edt_sheba_no.setOnClickListener(view -> {
            if (edt_sheba_no.getText().toString().isEmpty()) {
                edt_sheba_no.setText("IR ");
                edt_sheba_no.setSelection(edt_sheba_no.length());
                // Make it editable
                edt_sheba_no.setFocusableInTouchMode(true);
                edt_sheba_no.setFocusable(true);
            }
        });
    }

    private void InitActivity() {
        service_center_id = Integer.parseInt(PreferenceUtil.getD_id());
        txt_tile_action_bar.setText("کارت های بانکی");
        txt_tile_action_bar.setTypeface(G.Bold);
        txt_tile_action_bar.setTextSize(22);
        rv_bank_card.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.button));

    }

    private void OnClick() {
        btn_add_bank_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String card_no = edt_card_no.getText().toString().trim(); // Trim whitespace
                String sheba_no = edt_sheba_no.getText().toString().trim(); // Trim whitespace
                String bank_name = edt_bank_name.getText().toString().trim(); // Trim whitespace
                String national_code = edt_national_code.getText().toString().trim(); // Trim whitespace
                if (TextUtils.isEmpty(card_no) || card_no.length() < 19) {
                    edt_card_no.setError("لطفا شماره کارت را به درستی وارد کنید");
                } else if (TextUtils.isEmpty(sheba_no) || sheba_no.length() < 27) {
                    edt_sheba_no.setError("لطفا شماره شبا را به درستی وارد کنید");
                } else if (TextUtils.isEmpty(bank_name)) {
                    edt_bank_name.setError("لطفا نام بانک را وارد کنید");
                } else if (TextUtils.isEmpty(national_code) || national_code.length() < 10) {
                    edt_national_code.setError("لطفا کد ملی دارنده حساب را به درستی وارد کنید");
                } else addCard(card_no, sheba_no, national_code);

            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void addCard(String cardNo, String shebaNo, String national_code) {
        G.loading(this);
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);

        String card_no = (cardNo.replace("-", ""));
        String sheba_no = (shebaNo.replace("IR ", ""));

        JSONObject object = new JSONObject();
        try {
            object.put("service_center_id", service_center_id);
            object.put("national_code", national_code);
            object.put("card_no", card_no);
            object.put("sheba_no", sheba_no);
            object.put("bank_name", edt_bank_name.getText().toString());
            object.put("default_bank_account", 1);
            object.put("status", 1);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Call<ResponseBody> request = api.addBankCard(G.returnBody(object.toString()));
        request.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                if (response.code() == 200) {
                    G.toast("کارت بانکی با موفقیت افزوده شد.");
                    G.stop_loading();
                    edt_card_no.setText("");
                    edt_sheba_no.setText("");
                    edt_bank_name.setText("");
                    edt_national_code.setText("");
                    edt_card_no.setFocusable(true);
//                        FetchBankCardList();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط با سرور");
            }
        });
    }


    private void FindViews() {
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        edt_card_no = findViewById(R.id.edt_card_no);
        edt_sheba_no = findViewById(R.id.edt_sheba_no);
        edt_bank_name = findViewById(R.id.edt_bank_name);
        edt_national_code = findViewById(R.id.edt_national_code);
        btn_add_bank_card = findViewById(R.id.btn_add_card);
        rv_bank_card = findViewById(R.id.rv_bank_card);
        swipeRefreshLayout = findViewById(R.id.swipe);
        iv_back = findViewById(R.id.img_back);

    }

    private void FetchBankCardList() {
        swipeRefreshLayout.setRefreshing(true);
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);

        Call<ResponseBody> request = api.getBankCardList(service_center_id);
        request.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                List<ModelBankCard> bankCardList = new ArrayList<>();

                String result = G.getResult(response);

                adapterListBankCard = new AdapterListBankCard(bankCardList, BankCardActivity.this, new AdapterListBankCard.OnSwitchClick() {
                    @Override
                    public void onSwitchClickClick(ModelBankCard modelBankCard, int position) {

                    }
                });

                G.stop_loading();
                rv_bank_card.setAdapter(adapterListBankCard);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط با سرور");
            }
        });
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }
}