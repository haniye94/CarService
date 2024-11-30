package ir.servicea.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import ir.servicea.app.Constants;
import ir.servicea.app.G;
import ir.servicea.model.SendZarinInfo;
import ir.servicea.model.ZarinMetadata;
import ir.servicea.retrofit.Api;
import ir.servicea.retrofit.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.servicea.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class buyCreditActivity extends AppCompatActivity {

    TextView txt_tile_action_bar, takhfif_darsad, takhfif_mablagh, ghablepardakht;
    Button btn_menage_notif;
    private String authority;
    ImageView img_back;
    private CardView pay25, pay50, pay100, pay200;
    private ViewGroup select25, select50, select100, select200;
    private AppCompatButton buy;
    private TextView p1, p2, p3, p4, d1, d2, d3, d4;
    public int id1 = 0, id2 = 0, id3 = 0, id4 = 0;
    public int amount1 = 0, amount2 = 0, amount3 = 0, amount4 = 0;
    public int discont1 = 0, discont2 = 0, discont3 = 0, discont4 = 0;

    @Override
    public void onResume() {
        super.onResume();
        G.Activity = this;
        G.context = this;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        G.Activity = this;
        G.context = this;
        setContentView(R.layout.activity_buycredit);
        FindView();
        onClick();
        txt_tile_action_bar.setText("خرید شارژ پیامک");
        txt_tile_action_bar.setTypeface(G.Bold);
        pay25.setVisibility(View.GONE);
        pay50.setVisibility(View.GONE);
        pay100.setVisibility(View.GONE);
        pay200.setVisibility(View.GONE);
        findViewById(R.id.details).setVisibility(View.GONE);
        findViewById(R.id.buy).setVisibility(View.GONE);
        ListSmsPackage();
        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void onclickAlamrs(View v) {
        startActivity(new Intent(buyCreditActivity.this, AlarmsActivity.class));
    }

    private void FindView() {
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        btn_menage_notif = findViewById(R.id.btn_menage_notif);
        img_back = findViewById(R.id.img_back);
        buy = findViewById(R.id.buy);
        p1 = findViewById(R.id.p1);
        p2 = findViewById(R.id.p2);
        p3 = findViewById(R.id.p3);
        p4 = findViewById(R.id.p4);
        d1 = findViewById(R.id.d1);
        d2 = findViewById(R.id.d2);
        d3 = findViewById(R.id.d3);
        d4 = findViewById(R.id.d4);
        pay25 = findViewById(R.id.pay25);
        pay50 = findViewById(R.id.pay50);
        pay100 = findViewById(R.id.pay100);
        pay200 = findViewById(R.id.pay200);
        select25 = findViewById(R.id.select25);
        select50 = findViewById(R.id.select50);
        select100 = findViewById(R.id.select100);
        select200 = findViewById(R.id.select200);
        takhfif_darsad = findViewById(R.id.takhfif_darsad);
        takhfif_mablagh = findViewById(R.id.takhfif_mablagh);
        ghablepardakht = findViewById(R.id.ghablepardakht);

    }

    private void onClick() {
        pay25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cost(amount1);
            }
        });
        pay50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cost(amount2);
            }
        });
        pay100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cost(amount3);
            }
        });
        pay200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cost(amount4);
            }
        });
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalCost > 0) {
                    G.toast("لطفأ صبر کنید...");
                    sendZarinPallInfo(finalCost, finalAmount, finalId);
                }
            }
        });
    }

    public int finalCost = 0;
    public int finalId = 0;
    public int finalAmount = 0;
    public int finalOff = 0;

    public void Cost(int price) {
        finalAmount = price;
        try {
            disableAll();
            if (price == amount1) {
                finalOff = discont1;
                finalId = id1;
                finalCost = price - ((price * finalOff) / 100);
                select25.setVisibility(View.VISIBLE);
            } else if (price == amount2) {
                finalOff = discont2;
                finalId = id2;
                finalCost = price - ((price * finalOff) / 100);
                select50.setVisibility(View.VISIBLE);
            } else if (price == amount3) {
                finalOff = discont3;
                finalId = id3;
                finalCost = price - ((price * finalOff) / 100);
                select100.setVisibility(View.VISIBLE);
            } else if (price == amount4) {
                finalOff = discont4;
                finalId = id4;
                finalCost = price - ((price * finalOff) / 100);
                select200.setVisibility(View.VISIBLE);
            }
            takhfif_darsad.setText("درصد تخفیف: " + finalOff + "%");
            takhfif_mablagh.setText("مبلغ تخفیف: " + G.getDecimalFormattedString((price - finalCost) + "") + " تومان ");
            ghablepardakht.setText("مبلغ قابل پرداخت: " + G.getDecimalFormattedString((finalCost) + "") + " تومان ");
        } catch (Exception e) {
            G.toast("مشکل در انتخاب طرح ها!!");
        }
    }

    public void disableAll() {
        select25.setVisibility(View.GONE);
        select50.setVisibility(View.GONE);
        select100.setVisibility(View.GONE);
        select200.setVisibility(View.GONE);
    }

//    private void requestPayment(int amount, int final_amount, int final_id) {
//        amount = amount * 10;
//        try {
//            G.preference.edit().putInt("amount_charge", final_amount).apply();
//            G.preference.edit().putInt("charge_package_id", final_id).apply();
//            ZarinPal purchase = ZarinPal.getPurchase(G.context);
//            PaymentRequest payment = ZarinPal.getPaymentRequest();
//            payment.setMerchantID(G.MerchantID);
//            if (G.debug) {
//                amount = 1000;
//            }
//            payment.setAmount(amount);
//            payment.setDescription("پرداخت آنلاین");
//            payment.setCallbackURL("paymentzarrin://app");
//            payment.setMobile(G.preference.getString("phone_user", ""));
//            //payment.setEmail("imannamix@gmail.com");
//            purchase.startPayment(payment, new OnCallbackRequestPaymentListener() {
//                @Override
//                public void onCallbackResultPaymentRequest(int status, String authority, Uri paymentGatewayUri, Intent intent) {
//                    if (status == 100) {
//                        try {
//                            startActivity(intent);
//                        } catch (Exception e) {
//                            G.toast("مرورگری برای پرداخت یافت نشد!");
//                        }
//                    } else {
//                        G.toast("پرداخت آنلاین به مشکل برخورده");
//                    }
//
//                }
//            });
//        } catch (Exception e) {
//            G.toast("مشکل در درگاه پرداخت!!");
//        }
//
//    }

    public void sendZarinPallInfo(int amount, int final_amount, int final_id) {

        finalAmount = amount * 10;
        G.preference.edit().putInt("amount_charge", final_amount).apply();
        G.preference.edit().putInt("charge_package_id", final_id).apply();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(G.zarinPallBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api apiService = retrofit.create(Api.class);
        ZarinMetadata zarinMetadata = new ZarinMetadata();

        zarinMetadata.setEmail("imannamix@gmail.com");
        zarinMetadata.setMobile(G.preference.getString("phone_user", ""));
        SendZarinInfo sendZarinInfo = new SendZarinInfo();
        sendZarinInfo.setMerchant_id(G.MerchantID);
        sendZarinInfo.setCurrency("IRR");
        sendZarinInfo.setAmount(finalAmount);
        sendZarinInfo.setZarinMetadata(zarinMetadata);
        sendZarinInfo.setDescription(Constants.reserve_description);
        sendZarinInfo.setCallback_url("paymentzarrin://app");

        Call<ResponseBody> call = apiService.sendZarinPallInfo(Constants.reserve_service_accept, Constants.reserve_service_content, sendZarinInfo);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                String result = G.getResult(response);
                try {
                    JSONObject getInfoResponse = new JSONObject(result);
                    JSONObject data = getInfoResponse.getJSONObject("data");
                    authority = data.getString("authority");
                    G.saveAuthority(authority, finalAmount, false);
                    String url = Constants.reserve_service_payment_base_url + authority;

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(browserIntent);


                } catch (JSONException e) {
                    G.toast("مشکل در درگاه پرداخت!!");
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


    }

    public void ListSmsPackage() {
        try {
            G.loading(this);
            Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
            Call<ResponseBody> request = api.listSmsPackage();
            request.enqueue(new retrofit2.Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    String result = G.getResult(response);
                    JSONObject object = G.StringtoJSONObject(result);
                    JSONArray array = G.ObjecttoArray(object, "records");

                    if (array != null && array.length() > 0) {

                        try {
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);
                                int msg_count = obj.getInt("msg_count");
                                String name = obj.getString("name");
                                String description = obj.getString("description");
                                int id = obj.getInt("id");
                                int price = obj.getInt("price");
                                price = price / 10;
                                int discount = obj.getInt("discount_percent");
                                int total_price = obj.getInt("total_price");
                                double persentx = ((discount * price) / 100);
                                String persent = (persentx + "").replace(".0", "");
                                persent = (persent + "").replace("null", "").replace(" ", "");
                                if (i == 0) {
                                    pay25.setVisibility(View.VISIBLE);
                                    amount1 = price;
                                    id1 = id;
                                    discont1 = discount;
                                    p1.setText((price / 1000) + " هزار تومان ");
                                    d1.setText((discount) + " درصد تخفیف ");
                                } else if (i == 1) {
                                    pay50.setVisibility(View.VISIBLE);
                                    amount2 = price;
                                    id2 = id;
                                    discont2 = discount;
                                    p2.setText((price / 1000) + " هزار تومان ");
                                    d2.setText((discount) + " درصد تخفیف ");
                                } else if (i == 2) {
                                    pay100.setVisibility(View.VISIBLE);
                                    amount3 = price;
                                    id3 = id;
                                    discont3 = discount;
                                    p3.setText((price / 1000) + " هزار تومان ");
                                    d3.setText((discount) + " درصد تخفیف ");
                                } else if (i == 3) {
                                    pay200.setVisibility(View.VISIBLE);
                                    amount4 = price;
                                    id4 = id;
                                    discont4 = discount;
                                    p4.setText((price / 1000) + " هزار تومان ");
                                    d4.setText((discount) + " درصد تخفیف ");
                                }

                            }

                            findViewById(R.id.details).setVisibility(View.VISIBLE);
                            findViewById(R.id.buy).setVisibility(View.VISIBLE);
                            Cost(amount1);

                        } catch (JSONException e) {
                            G.toast("مشکل در دریافت اطلاعات");
                            finish();
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
        } catch (Exception e) {
            G.toast("مشکل در انتخاب طرح ها!!");
        }

    }
}