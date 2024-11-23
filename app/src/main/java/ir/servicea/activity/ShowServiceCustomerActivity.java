package ir.servicea.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ir.servicea.R;
import ir.servicea.app.G;

public class ShowServiceCustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_service_customer);
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
        startActivity(new Intent(ShowServiceCustomerActivity.this, AlarmsActivity.class));
    }
}