package ir.servicea.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ir.servicea.app.G;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import ir.servicea.R;

public class SettingActivity extends AppCompatActivity {

    TextView txt_tile_action_bar;
    TextView txt_charge_total, txt_charge_mande;
    Button btn_produce_group, btn_manage_message, btn_intro,btn_msglog, btn_menage_notif;
    ImageView img_back;
    @Override
    public void onResume(){
        super.onResume();
        G.Activity = this;
        G.context = this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        G.Activity = this;
        G.context = this;
        FindView();
        onClick();
        txt_tile_action_bar.setText("تنظیمات");
        txt_tile_action_bar.setTypeface(G.Bold);
        Typeface bold = Typeface.createFromAsset(getResources().getAssets(), "Fonts/IRANSans-Bold-web.ttf");
        txt_charge_total.setTypeface(bold);
        txt_charge_mande.setTypeface(bold);
        txt_charge_total.setText(G.getDecimalFormattedString(G.preference.getInt("charge_total",0)+""));
        txt_charge_mande.setText(G.getDecimalFormattedString(G.preference.getInt("charge_remain",0)+""));

    }

    public void onclickAlamrs(View v) {
        startActivity(new Intent(SettingActivity.this, AlarmsActivity.class));
    }

    private void FindView() {
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        btn_produce_group = findViewById(R.id.btn_produce_group);
        btn_manage_message = findViewById(R.id.btn_manage_message);
        btn_intro = findViewById(R.id.btn_intro);
        btn_msglog = findViewById(R.id.btn_msglog);
        btn_menage_notif = findViewById(R.id.btn_menage_notif);
        img_back = findViewById(R.id.img_back);
        txt_charge_total = findViewById(R.id.txt_charge_total);
        txt_charge_mande = findViewById(R.id.txt_charge_mande);
        txt_charge_mande.setText(G.getDecimalFormattedString(txt_charge_mande.getText().toString()+""));
        txt_charge_total.setText(G.getDecimalFormattedString(txt_charge_total.getText().toString()+""));

    }

    private void onClick() {
        findViewById(R.id.buycredit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, buyCreditActivity.class));
            }
        });
        findViewById(R.id.gotolog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, MessageLogActivity.class));
            }
        });
        btn_produce_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this, ProductsActivity.class));
            }
        });

        btn_manage_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this, ManageMessageActivity.class));
            }
        });  btn_msglog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this, MessageLogActivity.class));
            }
        });
        btn_intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this, IntroductionActivity.class));
//                try {
//                    Intent i = new Intent(Intent.ACTION_SEND);
//                    i.setType("text/plain");
//                    i.putExtra(Intent.EXTRA_SUBJECT, "Servica");
//                    String sAux = "\n\nبرای دانلود اپلیکیشن از بازار برروی لینک زیر کلیک کنید :\n\n";
//                    sAux = sAux + "https://cafebazaar.ir/app/ir.baadbaanapp.app/\n\n";
//                    i.putExtra(Intent.EXTRA_TEXT, sAux);
//                    startActivity(Intent.createChooser(i, "Share Servica Download Link To"));
//                } catch (Exception e) {
//                    G.toast("مشکل در ارسال لینک");
//                    e.printStackTrace();
//                }


            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_menage_notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }

}