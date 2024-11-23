package ir.servicea.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.pushnotifications.PushNotifications;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import ir.map.sdk_map.Mapir;
import ir.servicea.R;
import ir.servicea.activity.AlarmsActivity;
import ir.servicea.activity.MainActivity;
import ir.servicea.model.ModelServicesCustomer;
import ir.servicea.model.StructTask;
import ir.servicea.retrofit.Api;
import ir.servicea.retrofit.RetrofitClient;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;

public class G extends Application {
    //09188116930 test user for service center
    public static String AndroidAppVersion;
    public static boolean debug = false;
    public static String PreImagesURL = "https://autoservicea.ir/public/image/";
    public static String LINK_BLOG = "https://autoservicea.ir/blog/";
    public static String LINK_CAR_INFO = "https://autoservicea.ir/car-tech-info";

    public static String LINK_SHOP = "https://shop.servicea.ir";
    public static String LINK_Introduction = "https://autoservicea.ir/introduction-training";
    public static String LINK_BLOG_Educational = "https://autoservicea.ir/blog/";
    public static String LINK_BLOG_Car = "https://autoservicea.ir/blog/";
    public static String server = "https://api.autoservicea.ir/";
    public static String api_upload = "https://autoservicea.ir/public/image/api_upload.php?pushe=";

    public static String Pusher_Cluster = "ap2";

    public static String Pusher_Key = "35bc4227e019d6953e8d";
    public static String font = "Fonts/IRANYekanMobileMedium.ttf";
    public static String bold = "Fonts/IRANYekanMobileBold.ttf";
    public static String extrabold = "Fonts/IRANYekanMobileExtraBold.ttf";
    public static Typeface font_type;
    public static Context context;
    public static ArrayList<Integer> services = new ArrayList<>();
    public static List<ModelServicesCustomer> mscs = new ArrayList<>();
    // public static String API_BASE_URL = "https://schseraj.ir/panel/api/";
    public static double defLat = 34.7952758;
    public static double defLon = 48.5138073;
    public static SweetAlertDialog pDialog;
    public static String TAG = "GuidCar_ArenaTeam_ ";
    public static String api_username = "admin";
    public static String api_password = "VdxyyQ1gS5LUf97xRuEP";
    public static LayoutInflater inflater;
    public static Activity Activity;
    public static int id;
    public static SharedPreferences preference;
    public static Typeface Bold, ExtraBold, Normal;
    //public static String MerchantID = "59897c8b-e2cb-4954-8102-a651ada45e38";
    public static String MerchantID = "bbdb616f-3868-4399-94f6-0dc026c98ee4";
    public static int UserType = 3;
    public static int RoleId = 3;
    public static ArrayList<StructTask> tasks = new ArrayList<>();
    public static int PROV_WELLCOME = 1;
    public static int PROV_ADD_Service = 2;

    public static String CAN_EDIT_MOBILE = "can_edit_mobile";


    @Override
    public void onCreate() {
        super.onCreate();
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Mapir.getInstance(this, getResources().getString(R.string.mapir_token));
        }

        Normal = Typeface.createFromAsset(getAssets(), font);
        Bold = Typeface.createFromAsset(getAssets(), bold);
        ExtraBold = Typeface.createFromAsset(getAssets(), extrabold);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        context = getApplicationContext();
        preference = context.getSharedPreferences("preference", MODE_PRIVATE);
        ViewPump.init(ViewPump.builder().addInterceptor(new CalligraphyInterceptor(new CalligraphyConfig.Builder().setDefaultFontPath(font).setFontAttrId(R.attr.fontPath).build())).build());

        FontOverride.setDefaultFont(this, "DEFAULT", font);
        FontOverride.setDefaultFont(this, "MONOSPACE", font);
        FontOverride.setDefaultFont(this, "SERIF", font);
        FontOverride.setDefaultFont(this, "SANS_SERIF", font);
        TypefaceUtil.overrideFont(getApplicationContext());
        font_type = Typeface.createFromAsset(context.getAssets(), font);
        PackageManager manager = this.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            AndroidAppVersion = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        FirebaseApp.initializeApp(context);
        G.Pusher();
    }

    @SuppressLint("ObsoleteSdkInt")
    public static void setLocale(Locale locale, Context context) {
        // optional - Helper method to save the selected language to SharedPreferences in case you might need to attach to activity context (you will need to code this)
        //apbox.getInstance(this, "pk.eyJ1IjoiZXJmYW5zYiIsImEiOiJjamk3b2J3ZmkwZGx6M3dxdDh1amlucGZnIn0.Mosn7V7-cbhxC2Wzs9omZA");
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.getApplicationContext().createConfigurationContext(configuration);
        } else {
            resources.updateConfiguration(configuration, displayMetrics);
        }
    }

    public static final String PUSH_NOTIFICATION = "pushNotification";

    public static String getValue(JSONObject object, String key) {
        try {
            if (object.has(key)) {
                return (object.getString(key)).replace("null", "");
            } else {
                return "";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void PusherBeam(String user_id) {
        String regID = FirebaseInstanceId.getInstance().getToken();
        Log.e("regID", regID);
        Log.e("user_id", user_id);
        PushNotifications.start(context, "980efbd9-4e23-443a-b224-8f2dc5b4fcd4");
        PushNotifications.addDeviceInterest(user_id);
    }

    public static void Pusher() {
        PusherOptions options = new PusherOptions();
        options.setCluster(G.Pusher_Cluster);
        Pusher pusher = new Pusher(G.Pusher_Key, options);
        pusher.connect();
        Channel channel = pusher.subscribe("ServiceA");
        channel.bind("NotificationEvent", new SubscriptionEventListener() {
            @Override
            public void onEvent(PusherEvent event) {
                String dataS = event.getData();
                Log.d("PUSHERN", "Notifi:" + dataS);
                if (dataS.startsWith("{")) {
                    try {
                        JSONObject data = new JSONObject(dataS);
                        String title = "";
                        String content = "";
                        String user_id = "";
                        String province_id = "";
                        String city_id = "";
                        if (data.has("title")) {
                            title = data.getString("title");
                        }
                        if (data.has("content")) {
                            content = data.getString("content");
                        }
                        G.preference.edit().putString("province_id", province_id).apply();
                        G.preference.edit().putString("city_id", city_id).apply();
                        if (data.has("user_id")) {
                            user_id = data.getString("user_id");

                            if (PreferenceUtil.getUser_id().equals(user_id)) {
                                sendNotif(title, content);
                            }
                        } else if (data.has("province_id")) {
                            province_id = data.getString("province_id");
                            if (G.preference.getString("province_id", "xx").equals(province_id)) {
                                sendNotif(title, content);
                            }
                        } else if (data.has("city_id")) {
                            city_id = data.getString("user_id");
                            if (G.preference.getString("city_id", "xx").equals(city_id)) {
                                sendNotif(title, content);
                            }
                        } else {
                            sendNotif(title, content);
                        }

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        });


    }

    public static void sendNotif(String title, String content) {
        if (title.length() > 0 && content.length() > 0) {
            final Intent notificationIntent = new Intent(context, AlarmsActivity.class);
            notificationIntent.putExtra("push_notification", true);
            NotificationUtils notificationUtils = new NotificationUtils(context);
            notificationUtils.showNotificationMessage(title, content, "", notificationIntent);
        }
    }

    public static void sendNotif(String title, String content, String notificationId) {
        if (title.length() > 0 && content.length() > 0) {
            final Intent notificationIntent = new Intent(context, AlarmsActivity.class);
            notificationIntent.putExtra("notification_id", notificationId);
            notificationIntent.putExtra("push_notification", true);
            /*notificationIntent.setAction(Intent.ACTION_MAIN);
            notificationIntent.addCategory(Intent.CATEGORY_DEFAULT);*/
            notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            NotificationUtils notificationUtils = new NotificationUtils(context);
            notificationUtils.showNotificationMessage(title, content, "", notificationIntent);
        }
    }

    public static String getDecimalFormattedString(String value) {
        value = value.replace(".0", "");
        value = value.replace("-", "");
        if ((value).contains("null")) {
            return "";
        }
        value = value.replace(".0", "");
        value = value;
        StringTokenizer lst = new StringTokenizer(value, ".");
        String str1 = value;
        String str2 = "";
        if (lst.countTokens() > 1) {
            str1 = lst.nextToken();
            str2 = lst.nextToken();
        }
        StringBuilder str3 = new StringBuilder();
        int i = 0;
        int j = -1 + str1.length();
        if (str1.charAt(-1 + str1.length()) == '.') {
            j--;
            str3 = new StringBuilder(".");
        }
        for (int k = j; ; k--) {
            if (k < 0) {
                if (str2.length() > 0) str3.append(".").append(str2);
                return str3.toString().replace("1", "۱").replace("2", "۲").replace("3", "۳").replace("4", "۴").replace("5", "۵").replace("6", "۶").replace("7", "۷").replace("8", "۸").replace("9", "۹").replace("0", "۰");
            }
            if (i == 3) {
                str3.insert(0, ",");
                i = 0;
            }
            str3.insert(0, str1.charAt(k));
            i++;
        }
    }

    public static String converToEn(String s) {


        s = s.replace("٬", "");
        s = s.replace(",", "");
        s = s.replace("`", "");
        s = s.replace(",", "");
        s = s.replace("٫", ".");

        s = s.replace("۰", "0");
        s = s.replace("۱", "1");
        s = s.replace("۲", "2");
        s = s.replace("۳", "3");
        s = s.replace("۴", "4");
        s = s.replace("۵", "5");
        s = s.replace("۶", "6");
        s = s.replace("۷", "7");
        s = s.replace("۸", "8");
        s = s.replace("۹", "9");
        s = s.replace("٩", "9");
        s = s.replace("٨", "8");
        s = s.replace("٧", "7");
        s = s.replace("٦", "6");
        s = s.replace("٥", "5");
        s = s.replace("٤", "4");
        s = s.replace("٣", "3");
        s = s.replace("٢", "2");
        s = s.replace("١", "1");
        s = s.replace("٠", "0");
        return s;
    }

    public static String mahShamsi(int i) {
        String output = "";
        switch (i) {
            case 1:
                output = "فروردین";
                break;
            case 2:
                output = "اردیبهشت";
                break;
            case 3:
                output = "خرداد";
                break;
            case 4:
                output = "تیر";
                break;
            case 5:
                output = "مرداد";
                break;
            case 6:
                output = "شهریور";
                break;
            case 7:
                output = "مهر";
                break;
            case 8:
                output = "آبان";
                break;
            case 9:
                output = "آذر";
                break;
            case 10:
                output = "دی";
                break;
            case 11:
                output = "بهمن";
                break;
            case 12:
                output = "اسفند";
                break;
            default:
                output = String.valueOf(i);
        }
        return output;
    }

    public static void TimePIcker(Context context, EditText edt) {
        String text = edt.getText().toString();

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);

        if (text.contains(":")) {
            String[] texts = text.split(":");
            String hourX = texts[0];
            String minuteX = texts[1];
            if (hourX.length() <= 2) {
                hour = Integer.parseInt(hourX);
            }
            if (minuteX.length() <= 2) {
                minute = Integer.parseInt(minuteX);
            }
        }

        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                String selectedMinuteX = String.valueOf(selectedMinute);
                if (selectedMinuteX.length() == 1) {
                    selectedMinuteX = "0" + selectedMinute;
                }

                String selectedHourX = String.valueOf(selectedHour);
                if (selectedHourX.length() == 1) {
                    selectedHourX = "0" + selectedHour;
                }
                edt.setText(selectedHourX + ":" + selectedMinuteX + ":00");
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("ساعت را انتخاب کنید");
        mTimePicker.show();
    }

    public static String NumToEn(String a) {
        String[] pNum = new String[]{"۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹"};
        a = a.replace(pNum[0], "0");
        a = a.replace(pNum[1], "1");
        a = a.replace(pNum[2], "2");
        a = a.replace(pNum[3], "3");
        a = a.replace(pNum[4], "4");
        a = a.replace(pNum[5], "5");
        a = a.replace(pNum[6], "6");
        a = a.replace(pNum[7], "7");
        a = a.replace(pNum[8], "8");
        a = a.replace(pNum[9], "9");
        return a;
    }

    public static void loading(Context context) {
        if (context != null && !((Activity) context).isFinishing()) {
            if (!(pDialog != null && pDialog.isShowing())) {
                try {
                    pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#ED1651"));
                    pDialog.setTitleText("در حال دریافت اطلاعات ");
                    pDialog.setCancelable(false);

                    pDialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static void stop_loading() {
        if (!G.Activity.isFinishing() && pDialog != null) {
            if (pDialog.isShowing()) {
                try {
                    pDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    public static void Log(String log) {


        if (debug) {
            Log.e(G.TAG, log);
        }
    }

    public static RequestBody returnBody(String object) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), object);
        return body;
    }

    public static JSONArray ObjecttoArray(JSONObject object, String input) {
        JSONArray array = null;
        try {
            array = object.getJSONArray(input);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array;
    }

    public static JSONObject JSONObjecttoJSONObject(JSONObject obj, String input) {
        JSONObject object = null;
        try {
            object = obj.getJSONObject(input);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static JSONObject StringtoJSONObject(String input) {
        JSONObject object = null;
        input = input;
        try {
            object = new JSONObject(input);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static JSONArray StringtoJSONArray(String input) {
        JSONArray array = null;
        try {
            array = new JSONArray(input);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array;
    }

    public static String JSONgetString(JSONObject object, String key) {
        if (object != null && object.has(key)) {
            try {
                return object.getString(key);
            } catch (JSONException e) {

                e.printStackTrace();
            }
        }
        return "";
    }

    public static String getResult(Response<ResponseBody> response) {
        if (response != null && response.body() != null) {
            String result = "";
            try {
                result = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }
        return "";
    }

    public static String CreateSyntaxPlak(String plak) {
        String charset = plak.replaceAll("[0-9]", "");
        String numeric = plak.replaceAll("[^0-9]", "");
        return numeric + charset;
    }

    public static void toast(String text) {
        if (G.Activity != null && text.length() > 0) {
            LayoutInflater inf = inflater;
            View layout = inf.inflate(R.layout.toast, (ViewGroup) G.Activity.findViewById(R.id.toast_layout_root));
            TextView matn = layout.findViewById(R.id.text);
            matn.setTypeface(font_type);
            matn.setText(text);
            Toast toast = new Toast(G.context);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();
        }
    }

    public static String toShamsi(String date) {
        date = date.replace("/", "-");
        if (date.contains("-") && date.contains(":") && date.contains(" ")) {
            CalendarTool calendarTool = new CalendarTool();
            String[] date_time = date.split(" ");
            String[] dates = date_time[0].split("-");
            calendarTool.setGregorianDate(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
            date = calendarTool.getIranianDate() + " " + date_time[1];

        } else if (date.contains("-")) {
            date = date.replace(" ", "");
            CalendarTool calendarTool = new CalendarTool();
            String[] dates = date.split("-");
            calendarTool.setGregorianDate(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
            date = calendarTool.getIranianDate();

        }

        return date;
    }

    public static long dateDifference(Date startDate, Date endDate) {
        G.Log(startDate.toString());
        G.Log(endDate.toString());
        long different = endDate.getTime() - startDate.getTime();
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;
        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        return elapsedDays;
    }

    public static Date StringToDate(String dtStart) {
        dtStart = converToEn(dtStart);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(dtStart);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static String addDaysToDate(int days, String dt) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, days);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String output = sdf1.format(c.getTime());
        output = converToEn(output);
        return output;
    }

    @SuppressLint("ClickableViewAccessibility")
    public static void disableScroll(ScrollView scrollView) {
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    public static void scrollToView(ScrollView scrollView, View view) {
        G.Activity.getWindow().getDecorView().findViewById(android.R.id.content).clearFocus();
        int vTop = view.getTop();

        while (!(view.getParent() instanceof ScrollView)) {
            view = (View) view.getParent();
            vTop += view.getTop();
        }
        final int scrollPosition = vTop;
        new Handler().post(() -> scrollView.smoothScrollTo(0, scrollPosition));

    }

    public static void sendSMSProv(boolean from_service_list, String phone, int msg_id, String car_id, int service_id) {
//        G.loading(this);
        Api api = RetrofitClient.createService(Api.class, G.api_username, G.api_password);
        String d_id = PreferenceUtil.getD_id();

        api.sendSMSProv("", phone, d_id, String.valueOf(msg_id), car_id, String.valueOf(service_id)).enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                G.Log(call.request().toString());
                Log.d(TAG, "onResponse:sendSMSProv:call " + call.request().toString());
                Log.d(TAG, "onResponse:sendSMSProv:response " + response.body().toString());

//              G.stop_loading();
                if (response.body() != null) {
                    String result = "";
                    try {
                        result = response.body().string();
                        Log.d(TAG, "onResponse:sendSMSProv " + call.request());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    G.Log(result);
                    Log.d(TAG, "onResponse:sendSMSProv:result: " + result);
                    if (result.contains("The user does not have enough charge")) {
                        //Toast.makeText(G.context, "\"پیامک ارسال نشد شارژ کافی ندارید\"", Toast.LENGTH_SHORT).show();
                        if (from_service_list) Toast.makeText(G.context, "\"پیامک ارسال نشد شارژ کافی ندارید\"", Toast.LENGTH_SHORT).show();
                        else G.Log("پیامک ارسال نشد شارژ کافی ندارید");


                    } else {
                        if (result.length() > 4) {
                            if (from_service_list) {
                                Toast.makeText(G.context, "پیامک ارسال شد", Toast.LENGTH_SHORT).show();
                            } else {
                                G.Log("پیامک ارسال شد.");
                            }
                        } else {
                            if (from_service_list) {
                                Toast.makeText(G.context, "مشکل در ارسال پیامک", Toast.LENGTH_SHORT).show();
                            } else {
                                G.Log("مشکل در ارسال پیامک");
                            }
                        }
                    }
                }
            }


            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                G.stop_loading();
                Log.d(TAG, "onResponse:sendSMSProv: error " + call.request());
                Log.d(TAG, "onResponse:sendSMSProv:error " + t.getMessage());

//                G.Log("مشکل در برقراری ارتباط با سرور");
            }
        });
    }

    public static void ShowCase(String check, Activity activity, View view, String text) {
        if (!G.preference.getBoolean(check, false)) {
            new MaterialShowcaseView.Builder(activity).setTarget(view).setDismissText("بستن").setContentText(text).setDelay(500).singleUse(String.valueOf(123)).show();
//            G.preference.edit().putBoolean(check,true).apply();
        }
    }

    public static void saveProveService(int msg_id) {
        G.preference.edit().putInt(Constants.PROV_ADD_SERVICE, msg_id).apply();
    }
}
