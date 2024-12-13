package ir.tehranOil.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import ir.servicea.R;
import ir.tehranOil.adapter.AdapterNote;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import ir.tehranOil.app.G;

public class NotificationsActivity extends AppCompatActivity {

    private AdapterNote adapter;
    private View Empty;
    private Boolean Driver = G.preference.getBoolean("DRIVER", false);

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("WrongViewCast")
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
    public void onclickAlamrs(View v) {
//        startActivity(new Intent(NotificationsActivity.this, AlarmsActivity.class));
    }
    @Override
    public void onResume(){
        super.onResume();
        G.Activity = this;
        G.context = this;
    }
    public void onclickBack(View v) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        G.Activity = this;
        G.context = this;

        TextView txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        txt_tile_action_bar.setText("صندوق پیام");
        txt_tile_action_bar.setTypeface(G.Bold);

        adapter = new AdapterNote(G.tasks);
        ListView lsContent = findViewById(R.id.lstContent);
        Empty = findViewById(R.id.empty);
        lsContent.setAdapter(adapter);
        if (isOnline())
            give_list();
        else
            G.toast("اینترنت متصل نیست");
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    @SuppressLint("StaticFieldLeak")
    public void give_list() {
        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.button));
        pDialog.setTitleText("لطفا صبر کنید");
        pDialog.setCancelable(false);
        pDialog.show();
        G.tasks.clear();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pDialog.dismiss();
                Empty.setVisibility(View.VISIBLE);
            }
        },2500);
//        new AsyncTask<Object, Object, String>() {
//
//            @Override
//            protected String doInBackground(Object... parms) {
//                String url = "give_list";
//                if (Driver) {
//                    url = "give_list_driver";
//                }
//                return "Webservice.readUrl(url, params);";
//            }
//
//            @Override
//            protected void onPostExecute(String result) {
//                if (result != null) {
//                    if (!result.equals("[]")) {
//
//                        try {
//                            JSONArray array = new JSONArray(result);
//                            for (int i = 0; i < array.length(); i++) {
//                                StructTask s = new StructTask();
//                                JSONObject object = array.getJSONObject(i);
//                                s.id = object.getString("id");
//                                s.nam1 = object.getString("name1");
//                                s.nam2 = object.getString("name2");
//                                s.done = object.getString("done");
//                                s.peyk_id = object.getString("peyk_id");
//                                s.kind_vehicle = object.getString("kind_vehicle");
//                                G.tasks.add(s);
//
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//
//                        }
//                        adapter.notifyDataSetChanged();
//
//                    } else {
//                        Empty.setVisibility(View.VISIBLE);
//                    }
//                } else {
//                    Empty.setVisibility(View.VISIBLE);
//                }
//                pDialog.dismiss();
//
//            }
//        }.execute();
    }
}
