package ir.tehranOil.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.servicea.R;
import ir.tehranOil.app.G;

public class TextActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }
    @Override
    public void onResume(){
        super.onResume();
        G.Activity = this;
        G.context = this;
    }
    public void onclickAlamrs(View v) {
        startActivity(new Intent(TextActivity.this, AlarmsActivity.class));
    }

    public void onclickBack(View v) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        G.Activity = this;
        G.context = this;
        TextView txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        txt_tile_action_bar.setTypeface(G.Bold);
        TextView text = findViewById(R.id.text);
        if (G.preference.getString("textActivity", "").equals("faq")) {
            txt_tile_action_bar.setText("سوالات متداول");
            text.setText(getResources().getString(R.string.faq));
        } else if (G.preference.getString("textActivity", "").equals("learn")) {
            txt_tile_action_bar.setText("معرفی و آموزش");
            text.setText(getResources().getString(R.string.aboutus));
        }
        else if (G.preference.getString("textActivity", "").equals("aboutus")) {
            txt_tile_action_bar.setText("درباره ما");
            text.setText(getResources().getString(R.string.aboutus));
        }
    }
}