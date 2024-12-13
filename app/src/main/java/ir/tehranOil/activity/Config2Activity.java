package ir.tehranOil.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ir.tehranOil.app.G;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.servicea.R;

import me.aflak.libraries.dialog.FingerprintDialog;


public class Config2Activity extends AppCompatActivity {
    private TextView txt_tile_action_bar;
    @Override
    public void onResume(){
        super.onResume();
        G.Activity = this;
        G.context = this;
    }
    public void onclickAlamrs(View v) {
        startActivity(new Intent(Config2Activity.this, AlarmsActivity.class));
    }
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }

    public void onclickBack(View v) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config2);
        G.Activity = this;
        G.context = this;
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        txt_tile_action_bar.setText("تنظیمات حریم شخصی");
        txt_tile_action_bar.setTypeface(G.Bold);
        Switch AccessPassword =findViewById(R.id.map_style);
        Switch AccessFingerprint =findViewById(R.id.map_trafic);
        AccessPassword.setTypeface(G.Bold);
        AccessFingerprint.setTypeface(G.Bold);
        AccessPassword.setChecked((G.preference.getBoolean("AccessPassword",false)));

        AccessFingerprint.setChecked((G.preference.getBoolean("AccessFingerprint",false)));
        if(android.os.Build.VERSION.SDK_INT >= 23 && FingerprintDialog.isAvailable(G.context)){
            AccessFingerprint.setVisibility(View.VISIBLE);
        }else{
            AccessFingerprint.setChecked(false);
            AccessFingerprint.setVisibility(View.GONE);
        }
        AccessPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){ String now_password = G.preference.getString("PASSWORD", "");

                    if(now_password.length()>3){
                        G.preference.edit().putBoolean("AccessPassword",true).apply();
                    }else{
                        AccessPassword.setChecked(false);
                        G.toast("ابتدا رمز عبور خود را تعیین کنید");
                        startActivity(new Intent(Config2Activity.this,passwordActivity.class));

                    }

                }else {
                    G.preference.edit().putBoolean("AccessPassword",false).apply();
                }
            }
        });
        AccessFingerprint.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    G.preference.edit().putBoolean("AccessFingerprint",true).apply();
                }else {
                    G.preference.edit().putBoolean("AccessFingerprint",false).apply();
                }
            }
        });
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}
