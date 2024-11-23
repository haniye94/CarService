package ir.servicea.app;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtil {
    public static SharedPreferences preferenceUtil;
    Context context;

    public PreferenceUtil(Context context) {
        this.context = context;
        preferenceUtil = context.getSharedPreferences("preference", MODE_PRIVATE);
    }

    public static void cashFirstRun(boolean first) {
        SharedPreferences.Editor editor_first = preferenceUtil.edit();
        editor_first.putBoolean("first_run", first);
        editor_first.apply();
    }

    public static boolean getFirstRun() {
        return preferenceUtil.getBoolean("first_run", true);
    }

    public static void cashPhone(String phone) {
        SharedPreferences.Editor editor_phone = preferenceUtil.edit();
        editor_phone.putString("phone_user", phone);
        // editor_phone.putString("code_random",code_random);
        editor_phone.apply();
    }

    public static void cashNewCustomerPhone(String phone) {
        SharedPreferences.Editor editor_phone = preferenceUtil.edit();
        editor_phone.putString("new_customer_phone", phone);
        editor_phone.apply();
    }

    public static void cashNewCustomerPlak(String edt1, String edt2, String edt3, String edt4, String edt5, String edt6, String edt7, String edt8) {
        SharedPreferences.Editor editor_phone = preferenceUtil.edit();
        editor_phone.putString("new_customer_plak_edt1", edt1);
        editor_phone.putString("new_customer_plak_edt2", edt2);
        editor_phone.putString("new_customer_plak_edt3", edt3);
        editor_phone.putString("new_customer_plak_edt4", edt4);
        editor_phone.putString("new_customer_plak_edt5", edt5);
        editor_phone.putString("new_customer_plak_edt6", edt6);
        editor_phone.putString("new_customer_plak_edt7", edt7);
        editor_phone.putString("new_customer_plak_edt8", edt8);
        editor_phone.apply();
    }

    public static void cashAddCustomers(String name, String family, String gender, String birthday, String name_car, String type_car, String type_fule) {
        SharedPreferences.Editor editor_phone = preferenceUtil.edit();
        editor_phone.putString("add_customer_name", name);
        editor_phone.putString("add_customer_family", family);
        editor_phone.putString("add_customer_gender", gender);
        editor_phone.putString("add_customer_birthday", birthday);
        editor_phone.putString("add_customer_name_car", name_car);
        editor_phone.putString("add_customer_type_car", type_car);
        editor_phone.putString("add_customer_type_fule", type_fule);
        editor_phone.apply();
    }


    public static void cashInfo(String first_name, String last_name, String address, String name_auto_service) {
        SharedPreferences.Editor editor_info = preferenceUtil.edit();
        editor_info.putString("first_name", first_name);
        editor_info.putString("last_name", last_name);
        editor_info.putString("address", address);
        editor_info.putString("name_auto_service", name_auto_service);
        editor_info.apply();
    }

    public static void cachLogin() {
        SharedPreferences.Editor editor_id_user = preferenceUtil.edit();
        editor_id_user.putBoolean("cachLogin", true);
        editor_id_user.apply();
    }

    public static void cashD_id(String d_id) {
        SharedPreferences.Editor editor_id_user = preferenceUtil.edit();
        editor_id_user.putString("d_id", d_id);
        editor_id_user.apply();
    }
    public static void cashUser_id(String d_id) {
        SharedPreferences.Editor editor_id_user = preferenceUtil.edit();
        editor_id_user.putString("user_id", d_id);
        editor_id_user.apply();
    }
    public static void cashS_id(String d_id) {
        SharedPreferences.Editor editor_id_user = preferenceUtil.edit();
        editor_id_user.putString("s_id", d_id);
        editor_id_user.apply();
    }
    public static void cashIdUser(int id) {
        SharedPreferences.Editor editor_id_user = preferenceUtil.edit();
        editor_id_user.putInt("id_user", id);
        editor_id_user.apply();
    }

    public static void cashJob(int pos, String job) {
        SharedPreferences.Editor editor_job_user = preferenceUtil.edit();
        editor_job_user.putString("job_user", job);
        editor_job_user.putInt("job_position", pos);
        editor_job_user.apply();
    }

    public static void cashCity(int pos, String city) {
        SharedPreferences.Editor editor_city_user = preferenceUtil.edit();
        editor_city_user.putString("city_user", city);
        editor_city_user.putInt("city_position", pos);
        editor_city_user.apply();
    }

    public static void cashOstan(int pos, String ostan) {
        SharedPreferences.Editor editor_ostan_user = preferenceUtil.edit();
        editor_ostan_user.putString("ostan_user", ostan);
        editor_ostan_user.putInt("ostan_position", pos);
        editor_ostan_user.apply();
    }

    public static void cashType_service(String type_service) {
        SharedPreferences.Editor editor_Type_service = preferenceUtil.edit();
        editor_Type_service.putString("Type_service", type_service);
        editor_Type_service.apply();
    }

    public static String getType_service() {
        return preferenceUtil.getString("Type_service", "null");
    }

    public static String getPhone() {
        return preferenceUtil.getString("phone_user", null);
    }

    public static String getD_id() {
        if(preferenceUtil!=null)
        return preferenceUtil.getString("d_id", "");
        else return "";
    }
    public static String getUser_id() {
        return preferenceUtil.getString("user_id", "");
    }
    public static String getS_id() {
        return preferenceUtil.getString("s_id", "");
    }

    public static boolean getcachLogin() {
        return preferenceUtil.getBoolean("cachLogin", false);
    }

    public static String getName() {
        return preferenceUtil.getString("first_name", "null");
    }

    public static String getFamily() {
        return preferenceUtil.getString("last_name", null);
    }

    public static String getName_auto_service() {
        return preferenceUtil.getString("name_auto_service", null);
    }

    public static String getAddress() {
        return preferenceUtil.getString("address", null);
    }

    public static String getCity() {
        return preferenceUtil.getString("city_user", null);
    }

    public static int getCityPosition() {
        return preferenceUtil.getInt("city_position", 0);
    }

    public static String getOstan() {
        return preferenceUtil.getString("ostan_user", null);
    }

    public static int getOstanPosition() {
        return preferenceUtil.getInt("ostan_position", 0);
    }

    public static String getJob() {
        return preferenceUtil.getString("job_user", null);
    }

    public static int getJobPosition() {
        return preferenceUtil.getInt("job_position", 0);
    }

    public static String getNewCustomerPhone() {
        return preferenceUtil.getString("new_customer_phone", null);
    }

    public static String getNewCustomerPlak1() {
        return preferenceUtil.getString("new_customer_plak_edt1", null);
    }

    public static String getNewCustomerPlak2() {
        return preferenceUtil.getString("new_customer_plak_edt2", null);
    }

    public static String getNewCustomerPlak3() {
        return preferenceUtil.getString("new_customer_plak_edt3", null);
    }

    public static String getNewCustomerPlak4() {
        return preferenceUtil.getString("new_customer_plak_edt4", null);
    }

    public static String getNewCustomerPlak5() {
        return preferenceUtil.getString("new_customer_plak_edt5", null);
    }

    public static String getNewCustomerPlak6() {
        return preferenceUtil.getString("new_customer_plak_edt6", null);
    }

    public static String getNewCustomerPlak7() {
        return preferenceUtil.getString("new_customer_plak_edt7", null);
    }

    public static String getNewCustomerPlak8() {
        return preferenceUtil.getString("new_customer_plak_edt8", null);
    }

    public static void deleteCashPhone() {
        SharedPreferences.Editor editor_phone = preferenceUtil.edit();
        editor_phone.remove("new_customer_phone");
        editor_phone.apply();
    }

    public static void deleteCashPlak() {
        SharedPreferences.Editor editor_phone = preferenceUtil.edit();
        editor_phone.remove("new_customer_plak_edt1");
        editor_phone.remove("new_customer_plak_edt2");
        editor_phone.remove("new_customer_plak_edt3");
        editor_phone.remove("new_customer_plak_edt4");
        editor_phone.remove("new_customer_plak_edt5");
        editor_phone.remove("new_customer_plak_edt6");
        editor_phone.remove("new_customer_plak_edt7");
        editor_phone.remove("new_customer_plak_edt8");
        editor_phone.apply();
    }
}
