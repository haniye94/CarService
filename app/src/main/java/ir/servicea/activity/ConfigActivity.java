package ir.servicea.activity;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import ir.servicea.app.G;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import ir.servicea.R;


public class ConfigActivity extends AppCompatActivity {
    private TextView txt_tile_action_bar;
    @Override
    public void onResume(){
        super.onResume();
        G.Activity = this;
        G.context = this;
    }
    public void onclickAlamrs(View v) {
        startActivity(new Intent(ConfigActivity.this, AlarmsActivity.class));
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
        setContentView(R.layout.activity_config);
        G.Activity = this;
        G.context = this;
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        txt_tile_action_bar.setText("تنظیمات اعلان\u200Cها");
        txt_tile_action_bar.setTypeface(G.Bold);
        Switch PayamakAlarm =findViewById(R.id.map_style);
        Switch NotificationAlarm =findViewById(R.id.map_trafic);
        PayamakAlarm.setTypeface(G.Bold);
        NotificationAlarm.setTypeface(G.Bold);
        PayamakAlarm.setChecked((G.preference.getBoolean("PayamakAlarm",false)));
        NotificationAlarm.setChecked((G.preference.getBoolean("MAP_TRAFFIC",false)));
        PayamakAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                   G.preference.edit().putBoolean("PayamakAlarm",true).apply();
                }else {
                    G.preference.edit().putBoolean("PayamakAlarm",false).apply();
                }
            }
        });
        NotificationAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    G.preference.edit().putBoolean("MAP_TRAFFIC",true).apply();
                }else {
                    G.preference.edit().putBoolean("MAP_TRAFFIC",false).apply();
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
