package ir.servicea.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ir.servicea.app.G;
import ir.servicea.app.PLakUtils;
import ir.servicea.app.Constants;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import ir.servicea.R;

public class InformationCustomersActivity extends AppCompatActivity {
    TextView txt_benzini, txt_doghane, txt_dezeli,txt_hibrid,txt_fuel_car;
    TextView txt_name_customer, txt_phone_customer, txt_date_birthday, txt_gender, txt_type_customer, txt_date_save, txt_name_car;
    TextView txt_tile_action_bar;
    ImageView img_back;
    TextView txt_plak_customer1,txt_plak_customer2,txt_plak_customer3,txt_plak_customer4;
    ViewGroup plaks;

    private String carId;
    private ViewGroup ly_plk_general,ly_plk_taxi,ly_plk_edari,ly_plk_entezami,ly_plk_malolin,ly_plk_azad_new,ly_plk_azad_old;

    Constants.PLAK_TYPE plak_type = Constants.PLAK_TYPE.PLAK_GENERAL;
    ViewGroup plak_layout;

    private Intent intentThatOpenInformationCustomer;

    @Override
    public void onResume(){
        super.onResume();
        G.Activity = this;
        G.context = this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_customers);
        G.Activity = this;
        G.context = this;

        intentThatOpenInformationCustomer = getIntent();
        FindView();
        onClick();
        setPlakLayout();

        txt_tile_action_bar.setText("اطلاعات مشتری و خودرو");
        txt_tile_action_bar.setTypeface(G.Bold);
        txt_name_customer.setTypeface(G.ExtraBold);
        txt_name_customer.setText(getIntent().getExtras().getString("firstName") + " " + getIntent().getExtras().getString("lastName"));
        txt_phone_customer.setText(getIntent().getExtras().getString("phone"));
        txt_phone_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = txt_phone_customer.getText().toString();
                if (phone.length() >= 10) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                    startActivity(intent);
                }
            }
        });
        String date_birthday = getIntent().getExtras().getString("date_birthday");
        if(date_birthday==null || date_birthday.equals("null")) date_birthday = getString(R.string.no_value);
        txt_date_birthday.setText(date_birthday);
        if(txt_date_birthday.getText().toString().contains("-")){
            txt_date_birthday.setText(G.toShamsi(txt_date_birthday.getText().toString()));
        }
        txt_fuel_car.setText(getIntent().getExtras().getString("type_fule"));
        txt_gender.setText(getIntent().getExtras().getString("gender"));
        //  txt_type_customer.setText(getIntent().getExtras().getString(""));
        txt_date_save.setText(G.toShamsi(getIntent().getExtras().getString("date_save")));
        txt_name_car.setText(getIntent().getExtras().getString("nameCar"));
      
        String plak =  (getIntent().getExtras().getString("plak")+"").replace(" ","").replace("null","");
        if (plak.length() > 3) {
           /* String c1 = plak.substring(0,2);
            String c2 = plak.substring(2,plak.length()-3);
            String c3 = plak.substring(plak.length()-3,plak.length()-1);
            String c4 = plak.substring(plak.length()-1);
            txt_plak_customer1.setText(c1);
            txt_plak_customer2.setText(c4);
            txt_plak_customer3.setText(c2);
            txt_plak_customer4.setText(c3);
            txt_plak_customer1.setTypeface(G.ExtraBold);
            txt_plak_customer2.setTypeface(G.ExtraBold);
            txt_plak_customer3.setTypeface(G.ExtraBold);
            txt_plak_customer4.setTypeface(G.ExtraBold);*/
            setPlakBasedViewType(plak, plak_type);

        }else {
            plaks.setVisibility(View.GONE);
        }

        if (getIntent().getExtras().getString("type_fule").contains("بنزین")) {
            txt_benzini.setBackgroundResource(R.drawable.shap_select);
            txt_doghane.setBackgroundResource(R.drawable.shap_un_select);
            txt_dezeli.setBackgroundResource(R.drawable.shap_un_select);
            txt_benzini.setTextColor(getResources().getColor(R.color.text_light));
            txt_doghane.setTextColor(getResources().getColor(R.color.text_low_dark));
            txt_dezeli.setTextColor(getResources().getColor(R.color.text_low_dark));
            txt_hibrid.setBackgroundResource(R.drawable.shap_un_select);
            txt_hibrid.setTextColor(getResources().getColor(R.color.text_low_dark));
        } else if (getIntent().getExtras().getString("type_fule").contains("دیزل")) {
            txt_benzini.setBackgroundResource(R.drawable.shap_un_select);
            txt_doghane.setBackgroundResource(R.drawable.shap_un_select);
            txt_dezeli.setBackgroundResource(R.drawable.shap_select);
            txt_benzini.setTextColor(getResources().getColor(R.color.text_low_dark));
            txt_doghane.setTextColor(getResources().getColor(R.color.text_low_dark));
            txt_dezeli.setTextColor(getResources().getColor(R.color.text_light));
            txt_hibrid.setBackgroundResource(R.drawable.shap_un_select);
            txt_hibrid.setTextColor(getResources().getColor(R.color.text_low_dark));
        } else if (getIntent().getExtras().getString("type_fule").contains("دوگان")) {
            txt_benzini.setBackgroundResource(R.drawable.shap_un_select);
            txt_doghane.setBackgroundResource(R.drawable.shap_select);
            txt_dezeli.setBackgroundResource(R.drawable.shap_un_select);
            txt_benzini.setTextColor(getResources().getColor(R.color.text_low_dark));
            txt_doghane.setTextColor(getResources().getColor(R.color.text_light));
            txt_dezeli.setTextColor(getResources().getColor(R.color.text_low_dark));
            txt_hibrid.setBackgroundResource(R.drawable.shap_un_select);
            txt_hibrid.setTextColor(getResources().getColor(R.color.text_low_dark));
        }
        else if (getIntent().getExtras().getString("type_fule").contains("هیبرید")) {
            txt_benzini.setBackgroundResource(R.drawable.shap_un_select);
            txt_hibrid.setBackgroundResource(R.drawable.shap_select);
            txt_dezeli.setBackgroundResource(R.drawable.shap_un_select);
            txt_benzini.setTextColor(getResources().getColor(R.color.text_low_dark));
            txt_hibrid.setTextColor(getResources().getColor(R.color.text_light));
            txt_dezeli.setTextColor(getResources().getColor(R.color.text_low_dark));
            txt_doghane.setBackgroundResource(R.drawable.shap_un_select);
            txt_doghane.setTextColor(getResources().getColor(R.color.text_low_dark));
        }

        findViewById(R.id.editCust).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InformationCustomersActivity.this, AddCustomerActivity.class);
                intent.putExtra("idCustomer", getIntent().getExtras().getString("idCustomer")+"");
                intent.putExtra("car_id", getIntent().getExtras().getString("car_id"));
                intent.putExtra("id_car", getIntent().getExtras().getString("id_car"));
                intent.putExtra("firstName", getIntent().getExtras().getString("firstName"));
                intent.putExtra("lastName",getIntent().getExtras().getString("lastName"));
                intent.putExtra("phone",getIntent().getExtras().getString("phone"));
                intent.putExtra("nameCar", getIntent().getExtras().getString("nameCar"));
                intent.putExtra("plak",getIntent().getExtras().getString("plak"));
                intent.putExtra(Constants.CAR_PLATE_TYPE,plak_type);
                intent.putExtra("gender", getIntent().getExtras().getString("gender"));
                intent.putExtra("date_birthday", getIntent().getExtras().getString("date_birthday"));
                intent.putExtra("type_fule", getIntent().getExtras().getString("type_fule"));
                intent.putExtra("date_save", getIntent().getExtras().getString("date_save"));
                intent.putExtra("type_car",getIntent().getExtras().getString("type_car"));
                intent.putExtra("car_name_id",getIntent().getExtras().getInt("car_name_id"));
                intent.putExtra("car_tip_id", getIntent().getExtras().getInt("car_tip_id"));
                intent.putExtra("car_model_id", getIntent().getExtras().getInt("car_model_id"));
                intent.putExtra("car_company_id", getIntent().getExtras().getInt("car_company_id"));
                intent.putExtra("fuel_type_id", getIntent().getExtras().getInt("fuel_type_id"));
                startActivity(intent);
                finish();
            }
        });
    }
    private void FindView() {
        txt_benzini = findViewById(R.id.txt_benzini);
        txt_doghane = findViewById(R.id.txt_doghane);
        txt_fuel_car = findViewById(R.id.txt_fuel_car);
        txt_dezeli = findViewById(R.id.txt_dezeli);
        txt_hibrid = findViewById(R.id.txt_hibrid);
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        img_back = findViewById(R.id.img_back);

        txt_name_customer = findViewById(R.id.txt_name_customer);
        txt_phone_customer = findViewById(R.id.txt_phone_customer);
        txt_date_birthday = findViewById(R.id.txt_date_birthday);
        txt_gender = findViewById(R.id.txt_gender);
        txt_type_customer = findViewById(R.id.txt_type_customer);
        txt_date_save = findViewById(R.id.txt_date_save);
        txt_name_car = findViewById(R.id.txt_name_car);
        plaks =findViewById(R.id.plaks);
        txt_phone_customer =findViewById(R.id.txt_phone_customer);
        txt_plak_customer1 =findViewById(R.id.txt_plak_customer1);
        txt_plak_customer2 =findViewById(R.id.txt_plak_customer2);
        txt_plak_customer3 =findViewById(R.id.txt_plak_customer3);
        txt_plak_customer4 =findViewById(R.id.txt_plak_customer4);

        ly_plk_general = findViewById(R.id.ly_plk_general);
        ly_plk_taxi = findViewById(R.id.ly_plk_taxi);
        ly_plk_edari= findViewById(R.id.ly_plk_edari);
        ly_plk_entezami = findViewById(R.id.ly_plk_entezami);
        ly_plk_malolin = findViewById(R.id.ly_plk_malolin);
        ly_plk_azad_new = findViewById(R.id.ly_plk_azad_new);
        ly_plk_azad_old = findViewById(R.id.ly_plk_azad_old);
        plak_layout = ly_plk_general;
    }

    private void onClick() {

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    public void onclickAlamrs(View v) {
        startActivity(new Intent(InformationCustomersActivity.this, AlarmsActivity.class));
    }
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }

    private void setPlakLayout() {
        if(intentThatOpenInformationCustomer.hasExtra(Constants.CAR_PLATE_TYPE)){
            plak_type = (Constants.PLAK_TYPE) intentThatOpenInformationCustomer.getSerializableExtra(Constants.CAR_PLATE_TYPE);
        }

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