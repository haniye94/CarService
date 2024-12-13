package ir.tehranOil.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import ir.tehranOil.app.CalendarTool;
import ir.tehranOil.app.G;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.servicea.R;
import ir.tehranOil.retrofit.Api;
import ir.tehranOil.retrofit.RetrofitClient;
import ir.tehranOil.app.PreferenceUtil;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class ReportSalesActivity extends AppCompatActivity {
    TextView txt_tile_action_bar;
    TextView txt_count_service, txt_all_price_sale, sumservice, sumprice;
    ImageView img_back;
    BarChart barChart, barChart2;
    List<BarEntry> data1 = new ArrayList<>();
    List<BarEntry> data2 = new ArrayList<>();
    List<String> ListHafte1 = new ArrayList<>();
    List<String> ListHafte2 = new ArrayList<>();
    List<Integer> color = new ArrayList<>();
    TextView txt_week, txt_month, txt_year;
    @Override
    public void onResume(){
        super.onResume();
        G.Activity = this;
        G.context = this;
    }
    public void onclickAlamrs(View v) {
        startActivity(new Intent(ReportSalesActivity.this, AlarmsActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        G.Activity = this;
        G.context = this;
        FindView();
        onClick();

        txt_tile_action_bar.setText("گزارش فروش");
        txt_tile_action_bar.setTypeface(G.Bold);
        Typeface bold = Typeface.createFromAsset(getResources().getAssets(), "Fonts/IRANSans-Bold-web.ttf");
        txt_count_service.setTypeface(bold);
        txt_all_price_sale.setTypeface(bold);


        getReportService("WEEK");


    }

    private void setDataChart1() {

        BarDataSet barDataSet = new BarDataSet(data1, "");
//        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        Description description = new Description();
        description.setText("روزهای هفته");
        barDataSet.setColor(getResources().getColor(R.color.button));

        barChart.setDescription(description);
        BarData barData = new BarData(barDataSet);
        barData.setValueTypeface(G.Bold);
        barChart.setData(barData);
        barChart.getAxisLeft().setTypeface(G.Bold);
        barChart.getAxisRight().setTypeface(G.Bold);
        barChart.getDescription().setTypeface(G.Bold);
        barChart.getLegend().setTypeface(G.Bold);
        barChart.getXAxis().setTypeface(G.Bold);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(ListHafte1));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setDrawAxisLine(true);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(ListHafte1.size());
        xAxis.setLabelRotationAngle(330);


        barChart.animateY(2000);
        barChart.invalidate();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                barChart.getLegend().setEnabled(false);
            }
        }, 100);
    }

    private void setDataChart2() {


        BarDataSet barDataSet = new BarDataSet(data2, "");
        barDataSet.setColor(getResources().getColor(R.color.button));

//        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        Description description = new Description();
        description.setText("روزهای هفته");
        barChart2.setDescription(description);
        BarData barData = new BarData(barDataSet);
        barData.setValueTypeface(G.Bold);
        barChart2.setData(barData);
        barChart2.getAxisLeft().setTypeface(G.Bold);
        barChart2.getAxisRight().setTypeface(G.Bold);
        barChart2.getDescription().setTypeface(G.Bold);
        barChart2.getLegend().setTypeface(G.Bold);

        barChart2.getXAxis().setTypeface(G.Bold);
        XAxis xAxis = barChart2.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(ListHafte2));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setDrawAxisLine(true);

        xAxis.setGranularity(1f);
        xAxis.setLabelCount(ListHafte2.size());
        xAxis.setLabelRotationAngle(330);

        barChart2.animateY(2000);
        barChart2.invalidate();
        BarDataSet set1;

        Paint p = barChart2.getPaint(Chart.PAINT_DESCRIPTION);
        p.setColor(getResources().getColor(R.color.float_transparent));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                barChart2.getLegend().setEnabled(false);
            }
        }, 100);
    }

    private void FindView() {
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        img_back = findViewById(R.id.img_back);
        barChart = findViewById(R.id.barChart);
        barChart2 = findViewById(R.id.barChart2);
        barChart.setNoDataText("در حال دریافت اطلاعات ...");
        barChart.setNoDataTextTypeface(G.Bold);
        barChart2.setNoDataText("در حال دریافت اطلاعات ...");
        barChart2.setNoDataTextTypeface(G.Bold);
        txt_count_service = findViewById(R.id.txt_count_service);
        txt_all_price_sale = findViewById(R.id.txt_all_price_sale);
        txt_week = findViewById(R.id.txt_week);
        txt_month = findViewById(R.id.txt_month);
        txt_year = findViewById(R.id.txt_year);
        sumprice = findViewById(R.id.sumprice);
        sumservice = findViewById(R.id.sumservice);
        txt_week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getReportService("WEEK");
            }
        });
        txt_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getReportService("MONTH");
            }
        });
        txt_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getReportService("YEAR");
            }
        });

    }

    private void onClick() {
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }


    public void getReportService(String duration) {
        txt_year.setTextColor(getResources().getColor(R.color.text_dark_gray));
        txt_month.setTextColor(getResources().getColor(R.color.text_dark_gray));
        txt_week.setTextColor(getResources().getColor(R.color.text_dark_gray));
        if (duration.contains("WEEK")) {
            txt_week.setTextColor(getResources().getColor(R.color.button));
        } else if (duration.contains("MONTH")) {
            txt_month.setTextColor(getResources().getColor(R.color.button));
        } else if (duration.contains("YEAR")) {
            txt_year.setTextColor(getResources().getColor(R.color.button));
        }
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.getReportService(PreferenceUtil.getD_id(), duration);
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                G.Log(call.request().toString());
                assert response.body() != null;
                try {
                    G.Log(call.request().toString());
                    String result = response.body().string();

                    if (result.startsWith("{")) {
                        JSONObject obj = G.StringtoJSONObject(result);
                        JSONArray array = obj.getJSONArray("data");
                        if(obj.has("sum")) {
                            JSONObject sum = obj.getJSONObject("sum");
                            if(sum.has("count")){
                                String count = sum.getString("count");
                                sumservice.setText(G.getDecimalFormattedString(count + ""));
                            }
                            if(sum.has("price")){
                                String price = sum.getString("price");
                                sumprice.setText(G.getDecimalFormattedString(price + ""));
                            }

                        }
                        ListHafte2.clear();
                        ListHafte1.clear();
                        data2.clear();
                        data1.clear();
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            String date = object.getString("date_time");
                            int count = object.getInt("count");
                            long price = object.getLong("price");
                            CalendarTool calendarTool = new CalendarTool();
                            G.Log(date);
                            if (date.contains("-") && date.contains(":") && date.contains(" ")) {

                                String[] date_time = date.split(" ");
                                String[] dates = date_time[0].split("-");
                                calendarTool.setGregorianDate(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
                                if (duration.equals("YEAR")){
                                    calendarTool.setGregorianDate(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), 1);

                                }
                                date = calendarTool.getIranianDate();

                            } else if (date.contains("-")) {
                                date = date.replace(" ", "");
                                String[] dates = date.split("-");
                                calendarTool.setGregorianDate(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
                                if (duration.equals("YEAR")){
                                    calendarTool.setGregorianDate(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), 1);

                                }
                                date = calendarTool.getIranianDate();

                            }
                            G.Log(date);
                            if (duration.equals("WEEK")) {
                                date = calendarTool.getIranianWeekDayStr() + "\n" + calendarTool.getIranianMonth() + "/" + calendarTool.getIranianDay();
                            } else if (duration.equals("MONTH")) {
                                date = "هفته " + (i + 1) + "";
                            } else if (duration.equals("YEAR")) {
                                date = calendarTool.getIranianMonthStr() + "";
                            }
                            ListHafte1.add(date);
                            ListHafte2.add(date);

                            data1.add(new BarEntry(i, (price)));
                            data2.add(new BarEntry(i, (count)));
                        }


                        if(array.length()==0){
                            barChart.setNoDataText("موردی یافت نشد!");
                            barChart.invalidate();
                            barChart2.setNoDataText("موردی یافت نشد!");
                            barChart2.invalidate();
                        }else{
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    setDataChart1();
                                    setDataChart2();
                                }
                            }, 100);
                            setDataChart1();
                            setDataChart2();
                        }


                    } else {
                        G.toast("مشکل در دریافت اطلاعات");
                    }
                } catch (JSONException | IOException e) {
                    G.toast("مشکل در تجزیه اطلاعات");
                    e.printStackTrace();
                }
                G.stop_loading();
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                G.toast("مشکل در برقراری ارتباط");
            }
        });


    }
}