package ir.tehranOil.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.servicea.R;
import ir.tehranOil.app.G;

public class FAQActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
    @Override
    public void onResume(){
        super.onResume();
        G.Activity = this;
        G.context = this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        G.Activity = this;
        G.context = this;
        setContentView(R.layout.activity_faqactivity);
        TextView txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        ViewGroup notifdiv = findViewById(R.id.notifdiv);
        txt_tile_action_bar.setText("حریم خصوصی و قوانین");
        txt_tile_action_bar.setTypeface(G.Bold);
        notifdiv.setVisibility(View.INVISIBLE);
    }

    public void onclickAlamrs(View v) {
        startActivity(new Intent(FAQActivity.this, AlarmsActivity.class));
    }

    public void onclickBack(View v) {
        finish();
    }

}