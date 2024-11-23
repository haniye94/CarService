package ir.servicea.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import ir.servicea.R;
import ir.servicea.app.G;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        G.Activity = this;
        G.context = this;

    }
    @Override
    public void onResume(){
        super.onResume();
        G.Activity = this;
        G.context = this;
    }
    public void onclickAlamrs(View v) {
        startActivity(new Intent(ShopActivity.this, AlarmsActivity.class));
    }
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }
}