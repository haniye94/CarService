package ir.servicea.activity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import ir.servicea.app.CalendarTool;
import ir.servicea.app.DataBaseHelper;
import ir.servicea.app.G;
import ir.servicea.R;
import ir.servicea.retrofit.Api;
import ir.servicea.retrofit.RetrofitClient;
import ir.servicea.app.PreferenceUtil;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class ReportCustomerActivity extends AppCompatActivity implements OnChartValueSelectedListener {
    TextView txt_tile_action_bar;
    TextView txt_count_all_customer, txt_ignore_customer, txt_lost_customer, count_customer;
    TextView txt_week, txt_month, txt_year;
    List<BarEntry> dataMoshkelat = new ArrayList<>();
    List<BarEntry> chrtt3 = new ArrayList<>();
    List<String> ListChartAge = new ArrayList<>();
    List<String> dataOnvanMoshkel = new ArrayList<>();
    BarChart ChartDay, chartAge;
    PieChart chartGender;
    String[] parties;
    ArrayList<PieEntry> entries = new ArrayList<>();
    ImageView img_back;
    private DataBaseHelper mDBHelper;
    private SQLiteDatabase mDatabase;
    private TextView textcount1, textcount2;
    @Override
    public void onResume(){
        super.onResume();
        G.Activity = this;
        G.context = this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_costomer);
        G.Activity = this;
        G.context = this;
        FindView();
        onClick();
        mDBHelper = new DataBaseHelper(this);
        mDatabase = mDBHelper.getReadableDatabase();

        txt_tile_action_bar.setText("گزارش مشتریان");
        txt_tile_action_bar.setTypeface(G.Bold);
        txt_count_all_customer.setTypeface(G.Bold);
        txt_ignore_customer.setTypeface(G.Bold);
        txt_lost_customer.setTypeface(G.Bold);
        chartGender.setUsePercentValues(true);
        chartGender.getDescription().setEnabled(false);
        chartGender.setExtraOffsets(5, 10, 5, 5);

        chartGender.setDragDecelerationFrictionCoef(0.95f);

//        chart.setCenterTextTypeface(tfLight);
//        chart.setCenterText(generateCenterSpannableText());

        chartGender.setDrawHoleEnabled(true);
        chartGender.setHoleColor(Color.WHITE);

        chartGender.setTransparentCircleColor(Color.WHITE);
        chartGender.setTransparentCircleAlpha(110);

        chartGender.setHoleRadius(58f);
        chartGender.setTransparentCircleRadius(61f);

        chartGender.setDrawCenterText(true);

        chartGender.setRotationAngle(0);
        // enable rotation of the chart by touch
        chartGender.setRotationEnabled(true);
        chartGender.setHighlightPerTapEnabled(true);

        // chart.setUnit(" €");
        //chart.setDrawUnitsInChart(true);

        // add a selection listener
        chartGender.setOnChartValueSelectedListener(ReportCustomerActivity.this);

//        seekBarX.setProgress(4);
//        seekBarY.setProgress(10);

        chartGender.animateY(1400, Easing.EaseInOutQuad);
        // chart.spin(2000, 0, 360);

        Legend l = chartGender.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        chartGender.setEntryLabelColor(Color.WHITE);
        //  chart.setEntryLabelTypeface(tfRegular);
        chartGender.setEntryLabelTextSize(12f);

        getNewUser("WEEK");
        getGroupGender();
        getUsersAge();

        //   count_customer.setText(mDBHelper.getCountCustomers(mDatabase));

    }

    public void onclickAlamrs(View v) {
        startActivity(new Intent(ReportCustomerActivity.this, AlarmsActivity.class));
    }

    private void FindView() {
        txt_tile_action_bar = findViewById(R.id.txt_tile_action_bar);
        ChartDay = findViewById(R.id.barChart);
        chartGender = findViewById(R.id.barChart2);
        chartAge = findViewById(R.id.barChart3);
        ChartDay.setNoDataTextTypeface(G.Bold);
        ChartDay.setNoDataText("در حال دریافت اطلاعات ...");
        chartGender.setNoDataText("در حال دریافت اطلاعات ...");
        chartGender.setNoDataTextTypeface(G.Bold);
        chartAge.setNoDataText("در حال دریافت اطلاعات ...");
        chartAge.setNoDataTextTypeface(G.Bold);
        img_back = findViewById(R.id.img_back);
        txt_count_all_customer = findViewById(R.id.txt_count_all_customer);
        txt_ignore_customer = findViewById(R.id.txt_ignore_customer);
        txt_lost_customer = findViewById(R.id.txt_lost_customer);
        txt_week = findViewById(R.id.txt_week);
        txt_month = findViewById(R.id.txt_month);
        txt_year = findViewById(R.id.txt_year);
        count_customer = findViewById(R.id.count_customer);
        textcount1 = findViewById(R.id.textcount1);
        textcount2 = findViewById(R.id.textcount2);
        txt_week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getNewUser("WEEK");
            }
        });
        txt_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNewUser("MONTH");
            }
        });
        txt_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNewUser("YEAR");
            }
        });
    }

    public void getGroupGender() {

//        G.loading(G.Activity);
        String d_id = PreferenceUtil.getD_id();
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.getGroupGender(PreferenceUtil.getD_id());
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                assert response.body() != null;
                try {
                    String result = response.body().string();
                    Log.e("getGroupGender", result);
                    if (result.startsWith("[")) {
                        JSONArray array = G.StringtoJSONArray(result);
                        int[] counts = {0, 0};
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            int count = object.getInt("count");
                            if (i < 2) {
                                counts[i] = count;
                            }
                            String sex = object.getString("sex");
                        }

                        if (array.length() == 0) {
                            chartGender.setNoDataText("موردی یافت نشد!");
                            chartGender.invalidate();
                        } else {
                            setDataChartGender(counts[0], counts[1]);
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

    public void getUsersAge() {
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        Call<ResponseBody> request = api.getUsersAge(PreferenceUtil.getD_id());
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                assert response.body() != null;
                try {
                    String result = response.body().string();

                    if (result.startsWith("[")) {
                        JSONArray array = G.StringtoJSONArray(result);
                        int sum0 = 0, sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0, sum5 = 0, sum6 = 0;
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            if(((object.getString("age")+"").replace("null","")).length()>0) {
                                double age = object.getDouble("age");

                                if (age <= 17) {
                                    sum0++;

                                    chrtt3.add(new BarEntry(0, sum0));
                                } else if (age <= 24) {
                                    sum1++;

                                    chrtt3.add(new BarEntry(1, sum1));
                                } else if (age <= 34) {
                                    sum2++;

                                    chrtt3.add(new BarEntry(2, sum2));
                                } else if (age <= 44) {
                                    sum3++;
                                    chrtt3.add(new BarEntry(3, sum3));
                                } else if (age <= 54) {
                                    sum4++;
                                    chrtt3.add(new BarEntry(4, sum4));
                                } else if (age <= 64) {
                                    sum5++;
                                    chrtt3.add(new BarEntry(5, sum5));
                                } else if (age > 65) {
                                    sum6++;
                                    chrtt3.add(new BarEntry(6, sum6));
                                }
                            }
                        }

                        if (array.length() == 0) {
                            chartAge.setNoDataText("موردی یافت نشد!");
                            chartAge.invalidate();
                        } else {
                            setChartAge();
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

    public void getNewUser(String duration) {
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
        Call<ResponseBody> request = api.getNewUser(PreferenceUtil.getD_id(), duration);
        request.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                assert response.body() != null;
                try {
                    String result = response.body().string();
                    Log.e("getNewUser", result);

                    if (result.startsWith("[")) {
                        JSONArray array = G.StringtoJSONArray(result);
                        dataOnvanMoshkel.clear();
                        dataMoshkelat.clear();
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            String register_date = object.getString("register_date");
                            int count = object.getInt("count");

                            CalendarTool calendarTool = new CalendarTool();

                            if (register_date.contains("-") && register_date.contains(":") && register_date.contains(" ")) {
                                String[] date_time = register_date.split(" ");
                                String[] dates = date_time[0].split("-");
                                calendarTool.setGregorianDate(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
                                register_date = calendarTool.getIranianDate() + " " + date_time[1];
                            } else if (register_date.contains("-")) {
                                register_date = register_date.replace(" ", "");
                                String[] dates = register_date.split("-");
                                calendarTool.setGregorianDate(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
                                register_date = calendarTool.getIranianDate();

                            }


                            if (duration.equals("WEEK")) {
                                register_date = calendarTool.getIranianWeekDayStr() + "\n" + calendarTool.getIranianMonth() + "/" + calendarTool.getIranianDay();
                            } else if (duration.equals("MONTH")) {
                                register_date = "هفته " + (i + 1) + "";
                            } else if (duration.equals("YEAR")) {
                                register_date = calendarTool.getIranianMonthStr() + "";
                            }


                            dataOnvanMoshkel.add(register_date);
                            dataMoshkelat.add(new BarEntry(i, (count)));
                        }

                        if (array.length() == 0) {
                            ChartDay.setNoDataText("موردی یافت نشد");
                            ChartDay.invalidate();
                        } else {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    setChartDay();
                                }
                            }, 100);
                            setChartDay();
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

    private void onClick() {
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void setDataChartGender(int count1, int count2) {

        int sum = count1 + count2;
        count_customer.setText(G.getDecimalFormattedString(sum + ""));
        int persent_count1 = count1 * 100 / sum;
        int persent_count2 = count2 * 100 / sum;
        textcount1.setText(persent_count1 + "%");
        textcount2.setText(persent_count2 + "%");

        entries.add(new PieEntry((float) (count1)));
        entries.add(new PieEntry((float) (count2)));
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);
        PieData data = new PieData(dataSet);

        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);
        data.setValueTypeface(G.Bold);

        chartGender.setData(data);

        chartGender.getDescription().setTypeface(G.Bold);
        chartGender.getLegend().setTypeface(G.Bold);

        // undo all highlights
        chartGender.highlightValues(null);

        chartGender.invalidate();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                chartGender.getLegend().setEnabled(false);
            }
        }, 100);
    }

    private void setChartAge() {
        ListChartAge.add("13-17");
        ListChartAge.add("18-24");
        ListChartAge.add("25-34");
        ListChartAge.add("35-44");
        ListChartAge.add("45-54");
        ListChartAge.add("55-64");
        ListChartAge.add("65+");


        BarDataSet barDataSet = new BarDataSet(chrtt3, "");
        barDataSet.setValueTypeface(G.Bold);
//        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        Description description = new Description();
        description.setText("");
        chartAge.setDescription(description);
        chartAge.setNoDataTextTypeface(G.Bold);
        BarData barData = new BarData(barDataSet);
        barData.setValueTypeface(G.Bold);
        chartAge.setData(barData);
        chartAge.getAxisLeft().setTypeface(G.Bold);
        chartAge.getAxisRight().setTypeface(G.Bold);
        chartAge.getDescription().setTypeface(G.Bold);
        chartAge.getLegend().setTypeface(G.Bold);
        chartAge.getXAxis().setTypeface(G.Bold);
        barDataSet.setColor(getResources().getColor(R.color.button));
        barDataSet.setValueTypeface(G.Bold);
        XAxis xAxis = chartAge.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(ListChartAge));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setDrawAxisLine(true);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(ListChartAge.size());

        xAxis.setLabelRotationAngle(300);
        chartAge.animateY(2000);
        chartAge.setNoDataTextTypeface(G.Bold);

        chartAge.invalidate();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                chartAge.getLegend().setEnabled(false);
            }
        }, 100);
    }

    private void setChartDay() {

        BarDataSet barDataSet = new BarDataSet(dataMoshkelat, "");
        barDataSet.setValueTypeface(G.Bold);
//        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        Description description = new Description();
        description.setText("");
        ChartDay.setDescription(description);
        BarData barData = new BarData(barDataSet);
        barData.setValueTypeface(G.Bold);
        ChartDay.setData(barData);

        ChartDay.getAxisLeft().setTypeface(G.Bold);
        ChartDay.getAxisRight().setTypeface(G.Bold);
        ChartDay.getDescription().setTypeface(G.Bold);
        ChartDay.getLegend().setTypeface(G.Bold);
        ChartDay.getXAxis().setTypeface(G.Bold);


        barDataSet.setColor(getResources().getColor(R.color.button));
        barDataSet.setValueTypeface(G.Bold);

        XAxis xAxis = ChartDay.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(dataOnvanMoshkel));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setDrawAxisLine(true);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(dataOnvanMoshkel.size());
        xAxis.setLabelRotationAngle(330);
        ChartDay.animateY(2000);

        ChartDay.invalidate();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ChartDay.getLegend().setEnabled(false);
            }
        }, 100);

    }

    private void setMargins(View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context));
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}